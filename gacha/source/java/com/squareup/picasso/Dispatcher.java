package com.squareup.picasso;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import org.cocos2dx.lib.BuildConfig;

class Dispatcher {
    static final int AIRPLANE_MODE_CHANGE = 10;
    private static final int AIRPLANE_MODE_OFF = 0;
    private static final int AIRPLANE_MODE_ON = 1;
    private static final int BATCH_DELAY = 200;
    private static final String DISPATCHER_THREAD_NAME = "Dispatcher";
    static final int HUNTER_BATCH_COMPLETE = 8;
    static final int HUNTER_COMPLETE = 4;
    static final int HUNTER_DECODE_FAILED = 6;
    static final int HUNTER_DELAY_NEXT_BATCH = 7;
    static final int HUNTER_RETRY = 5;
    static final int NETWORK_STATE_CHANGE = 9;
    static final int REQUEST_BATCH_RESUME = 13;
    static final int REQUEST_CANCEL = 2;
    static final int REQUEST_GCED = 3;
    static final int REQUEST_SUBMIT = 1;
    private static final int RETRY_DELAY = 500;
    static final int TAG_PAUSE = 11;
    static final int TAG_RESUME = 12;
    boolean airplaneMode;
    final List<BitmapHunter> batch;
    final Cache cache;
    final Context context;
    final DispatcherThread dispatcherThread = new DispatcherThread();
    final Downloader downloader;
    final Map<Object, Action> failedActions;
    final Handler handler;
    final Map<String, BitmapHunter> hunterMap;
    final Handler mainThreadHandler;
    final Map<Object, Action> pausedActions;
    final Set<Object> pausedTags;
    final NetworkBroadcastReceiver receiver;
    final boolean scansNetworkChanges;
    final ExecutorService service;
    final Stats stats;

    private static class DispatcherHandler extends Handler {
        private final Dispatcher dispatcher;

        public DispatcherHandler(Looper looper, Dispatcher dispatcher) {
            super(looper);
            this.dispatcher = dispatcher;
        }

        public void handleMessage(final Message message) {
            boolean z = true;
            switch (message.what) {
                case Dispatcher.REQUEST_SUBMIT /*1*/:
                    this.dispatcher.performSubmit((Action) message.obj);
                    return;
                case Dispatcher.REQUEST_CANCEL /*2*/:
                    this.dispatcher.performCancel((Action) message.obj);
                    return;
                case Dispatcher.HUNTER_COMPLETE /*4*/:
                    this.dispatcher.performComplete((BitmapHunter) message.obj);
                    return;
                case Dispatcher.HUNTER_RETRY /*5*/:
                    this.dispatcher.performRetry((BitmapHunter) message.obj);
                    return;
                case Dispatcher.HUNTER_DECODE_FAILED /*6*/:
                    this.dispatcher.performError((BitmapHunter) message.obj, false);
                    return;
                case Dispatcher.HUNTER_DELAY_NEXT_BATCH /*7*/:
                    this.dispatcher.performBatchComplete();
                    return;
                case Dispatcher.NETWORK_STATE_CHANGE /*9*/:
                    this.dispatcher.performNetworkStateChange((NetworkInfo) message.obj);
                    return;
                case Dispatcher.AIRPLANE_MODE_CHANGE /*10*/:
                    Dispatcher dispatcher = this.dispatcher;
                    if (message.arg1 != Dispatcher.REQUEST_SUBMIT) {
                        z = false;
                    }
                    dispatcher.performAirplaneModeChange(z);
                    return;
                case Dispatcher.TAG_PAUSE /*11*/:
                    this.dispatcher.performPauseTag(message.obj);
                    return;
                case Dispatcher.TAG_RESUME /*12*/:
                    this.dispatcher.performResumeTag(message.obj);
                    return;
                default:
                    Picasso.HANDLER.post(new Runnable() {
                        public void run() {
                            throw new AssertionError("Unknown handler message received: " + message.what);
                        }
                    });
                    return;
            }
        }
    }

    static class DispatcherThread extends HandlerThread {
        DispatcherThread() {
            super("Picasso-Dispatcher", Dispatcher.AIRPLANE_MODE_CHANGE);
        }
    }

    static class NetworkBroadcastReceiver extends BroadcastReceiver {
        static final String EXTRA_AIRPLANE_STATE = "state";
        private final Dispatcher dispatcher;

        NetworkBroadcastReceiver(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        void register() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
            if (this.dispatcher.scansNetworkChanges) {
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            }
            this.dispatcher.context.registerReceiver(this, intentFilter);
        }

        void unregister() {
            this.dispatcher.context.unregisterReceiver(this);
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if ("android.intent.action.AIRPLANE_MODE".equals(action)) {
                    if (intent.hasExtra(EXTRA_AIRPLANE_STATE)) {
                        this.dispatcher.dispatchAirplaneModeChange(intent.getBooleanExtra(EXTRA_AIRPLANE_STATE, false));
                    }
                } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                    this.dispatcher.dispatchNetworkStateChange(((ConnectivityManager) Utils.getService(context, "connectivity")).getActiveNetworkInfo());
                }
            }
        }
    }

    Dispatcher(Context context, ExecutorService executorService, Handler handler, Downloader downloader, Cache cache, Stats stats) {
        this.dispatcherThread.start();
        this.context = context;
        this.service = executorService;
        this.hunterMap = new LinkedHashMap();
        this.failedActions = new WeakHashMap();
        this.pausedActions = new WeakHashMap();
        this.pausedTags = new HashSet();
        this.handler = new DispatcherHandler(this.dispatcherThread.getLooper(), this);
        this.downloader = downloader;
        this.mainThreadHandler = handler;
        this.cache = cache;
        this.stats = stats;
        this.batch = new ArrayList(HUNTER_COMPLETE);
        this.airplaneMode = Utils.isAirplaneModeOn(this.context);
        this.scansNetworkChanges = Utils.hasPermission(context, "android.permission.ACCESS_NETWORK_STATE");
        this.receiver = new NetworkBroadcastReceiver(this);
        this.receiver.register();
    }

    void shutdown() {
        if (this.service instanceof PicassoExecutorService) {
            this.service.shutdown();
        }
        this.downloader.shutdown();
        this.dispatcherThread.quit();
        Picasso.HANDLER.post(new Runnable() {
            public void run() {
                Dispatcher.this.receiver.unregister();
            }
        });
    }

    void dispatchSubmit(Action action) {
        this.handler.sendMessage(this.handler.obtainMessage(REQUEST_SUBMIT, action));
    }

    void dispatchCancel(Action action) {
        this.handler.sendMessage(this.handler.obtainMessage(REQUEST_CANCEL, action));
    }

    void dispatchPauseTag(Object obj) {
        this.handler.sendMessage(this.handler.obtainMessage(TAG_PAUSE, obj));
    }

    void dispatchResumeTag(Object obj) {
        this.handler.sendMessage(this.handler.obtainMessage(TAG_RESUME, obj));
    }

    void dispatchComplete(BitmapHunter bitmapHunter) {
        this.handler.sendMessage(this.handler.obtainMessage(HUNTER_COMPLETE, bitmapHunter));
    }

    void dispatchRetry(BitmapHunter bitmapHunter) {
        this.handler.sendMessageDelayed(this.handler.obtainMessage(HUNTER_RETRY, bitmapHunter), 500);
    }

    void dispatchFailed(BitmapHunter bitmapHunter) {
        this.handler.sendMessage(this.handler.obtainMessage(HUNTER_DECODE_FAILED, bitmapHunter));
    }

    void dispatchNetworkStateChange(NetworkInfo networkInfo) {
        this.handler.sendMessage(this.handler.obtainMessage(NETWORK_STATE_CHANGE, networkInfo));
    }

    void dispatchAirplaneModeChange(boolean z) {
        this.handler.sendMessage(this.handler.obtainMessage(AIRPLANE_MODE_CHANGE, z ? REQUEST_SUBMIT : AIRPLANE_MODE_OFF, AIRPLANE_MODE_OFF));
    }

    void performSubmit(Action action) {
        performSubmit(action, true);
    }

    void performSubmit(Action action, boolean z) {
        if (this.pausedTags.contains(action.getTag())) {
            this.pausedActions.put(action.getTarget(), action);
            if (action.getPicasso().loggingEnabled) {
                Utils.log(DISPATCHER_THREAD_NAME, String.VIDEO_PAUSED, action.request.logId(), "because tag '" + action.getTag() + "' is paused");
                return;
            }
            return;
        }
        BitmapHunter bitmapHunter = (BitmapHunter) this.hunterMap.get(action.getKey());
        if (bitmapHunter != null) {
            bitmapHunter.attach(action);
        } else if (!this.service.isShutdown()) {
            Object forRequest = BitmapHunter.forRequest(action.getPicasso(), this, this.cache, this.stats, action);
            forRequest.future = this.service.submit(forRequest);
            this.hunterMap.put(action.getKey(), forRequest);
            if (z) {
                this.failedActions.remove(action.getTarget());
            }
            if (action.getPicasso().loggingEnabled) {
                Utils.log(DISPATCHER_THREAD_NAME, "enqueued", action.request.logId());
            }
        } else if (action.getPicasso().loggingEnabled) {
            Utils.log(DISPATCHER_THREAD_NAME, "ignored", action.request.logId(), "because shut down");
        }
    }

    void performCancel(Action action) {
        String key = action.getKey();
        BitmapHunter bitmapHunter = (BitmapHunter) this.hunterMap.get(key);
        if (bitmapHunter != null) {
            bitmapHunter.detach(action);
            if (bitmapHunter.cancel()) {
                this.hunterMap.remove(key);
                if (action.getPicasso().loggingEnabled) {
                    Utils.log(DISPATCHER_THREAD_NAME, "canceled", action.getRequest().logId());
                }
            }
        }
        if (this.pausedTags.contains(action.getTag())) {
            this.pausedActions.remove(action.getTarget());
            if (action.getPicasso().loggingEnabled) {
                Utils.log(DISPATCHER_THREAD_NAME, "canceled", action.getRequest().logId(), "because paused request got canceled");
            }
        }
        Action action2 = (Action) this.failedActions.remove(action.getTarget());
        if (action2 != null && action2.getPicasso().loggingEnabled) {
            Utils.log(DISPATCHER_THREAD_NAME, "canceled", action2.getRequest().logId(), "from replaying");
        }
    }

    void performPauseTag(Object obj) {
        if (this.pausedTags.add(obj)) {
            Iterator it = this.hunterMap.values().iterator();
            while (it.hasNext()) {
                BitmapHunter bitmapHunter = (BitmapHunter) it.next();
                boolean z = bitmapHunter.getPicasso().loggingEnabled;
                Action action = bitmapHunter.getAction();
                List actions = bitmapHunter.getActions();
                Object obj2 = (actions == null || actions.isEmpty()) ? null : REQUEST_SUBMIT;
                if (action != null || obj2 != null) {
                    if (action != null && action.getTag().equals(obj)) {
                        bitmapHunter.detach(action);
                        this.pausedActions.put(action.getTarget(), action);
                        if (z) {
                            Utils.log(DISPATCHER_THREAD_NAME, String.VIDEO_PAUSED, action.request.logId(), "because tag '" + obj + "' was paused");
                        }
                    }
                    if (obj2 != null) {
                        for (int size = actions.size() - 1; size >= 0; size--) {
                            Action action2 = (Action) actions.get(size);
                            if (action2.getTag().equals(obj)) {
                                bitmapHunter.detach(action2);
                                this.pausedActions.put(action2.getTarget(), action2);
                                if (z) {
                                    Utils.log(DISPATCHER_THREAD_NAME, String.VIDEO_PAUSED, action2.request.logId(), "because tag '" + obj + "' was paused");
                                }
                            }
                        }
                    }
                    if (bitmapHunter.cancel()) {
                        it.remove();
                        if (z) {
                            Utils.log(DISPATCHER_THREAD_NAME, "canceled", Utils.getLogIdsForHunter(bitmapHunter), "all actions paused");
                        }
                    }
                }
            }
        }
    }

    void performResumeTag(Object obj) {
        if (this.pausedTags.remove(obj)) {
            Object obj2 = null;
            Iterator it = this.pausedActions.values().iterator();
            while (it.hasNext()) {
                Action action = (Action) it.next();
                if (action.getTag().equals(obj)) {
                    if (obj2 == null) {
                        obj2 = new ArrayList();
                    }
                    obj2.add(action);
                    it.remove();
                }
            }
            if (obj2 != null) {
                this.mainThreadHandler.sendMessage(this.mainThreadHandler.obtainMessage(REQUEST_BATCH_RESUME, obj2));
            }
        }
    }

    void performRetry(BitmapHunter bitmapHunter) {
        boolean z = true;
        if (!bitmapHunter.isCancelled()) {
            if (this.service.isShutdown()) {
                performError(bitmapHunter, false);
                return;
            }
            NetworkInfo activeNetworkInfo;
            if (this.scansNetworkChanges) {
                activeNetworkInfo = ((ConnectivityManager) Utils.getService(this.context, "connectivity")).getActiveNetworkInfo();
            } else {
                activeNetworkInfo = null;
            }
            boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnected();
            boolean shouldRetry = bitmapHunter.shouldRetry(this.airplaneMode, activeNetworkInfo);
            boolean supportsReplay = bitmapHunter.supportsReplay();
            if (!shouldRetry) {
                if (!(this.scansNetworkChanges && supportsReplay)) {
                    z = false;
                }
                performError(bitmapHunter, z);
                if (z) {
                    markForReplay(bitmapHunter);
                }
            } else if (!this.scansNetworkChanges || z2) {
                if (bitmapHunter.getPicasso().loggingEnabled) {
                    Utils.log(DISPATCHER_THREAD_NAME, "retrying", Utils.getLogIdsForHunter(bitmapHunter));
                }
                bitmapHunter.future = this.service.submit(bitmapHunter);
            } else {
                performError(bitmapHunter, supportsReplay);
                if (supportsReplay) {
                    markForReplay(bitmapHunter);
                }
            }
        }
    }

    void performComplete(BitmapHunter bitmapHunter) {
        if (!bitmapHunter.shouldSkipMemoryCache()) {
            this.cache.set(bitmapHunter.getKey(), bitmapHunter.getResult());
        }
        this.hunterMap.remove(bitmapHunter.getKey());
        batch(bitmapHunter);
        if (bitmapHunter.getPicasso().loggingEnabled) {
            Utils.log(DISPATCHER_THREAD_NAME, "batched", Utils.getLogIdsForHunter(bitmapHunter), "for completion");
        }
    }

    void performBatchComplete() {
        List arrayList = new ArrayList(this.batch);
        this.batch.clear();
        this.mainThreadHandler.sendMessage(this.mainThreadHandler.obtainMessage(HUNTER_BATCH_COMPLETE, arrayList));
        logBatch(arrayList);
    }

    void performError(BitmapHunter bitmapHunter, boolean z) {
        if (bitmapHunter.getPicasso().loggingEnabled) {
            Utils.log(DISPATCHER_THREAD_NAME, "batched", Utils.getLogIdsForHunter(bitmapHunter), "for error" + (z ? " (will replay)" : BuildConfig.FLAVOR));
        }
        this.hunterMap.remove(bitmapHunter.getKey());
        batch(bitmapHunter);
    }

    void performAirplaneModeChange(boolean z) {
        this.airplaneMode = z;
    }

    void performNetworkStateChange(NetworkInfo networkInfo) {
        if (this.service instanceof PicassoExecutorService) {
            ((PicassoExecutorService) this.service).adjustThreadCount(networkInfo);
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            flushFailedActions();
        }
    }

    private void flushFailedActions() {
        if (!this.failedActions.isEmpty()) {
            Iterator it = this.failedActions.values().iterator();
            while (it.hasNext()) {
                Action action = (Action) it.next();
                it.remove();
                if (action.getPicasso().loggingEnabled) {
                    Utils.log(DISPATCHER_THREAD_NAME, "replaying", action.getRequest().logId());
                }
                performSubmit(action, false);
            }
        }
    }

    private void markForReplay(BitmapHunter bitmapHunter) {
        Action action = bitmapHunter.getAction();
        if (action != null) {
            markForReplay(action);
        }
        List actions = bitmapHunter.getActions();
        if (actions != null) {
            int size = actions.size();
            for (int i = AIRPLANE_MODE_OFF; i < size; i += REQUEST_SUBMIT) {
                markForReplay((Action) actions.get(i));
            }
        }
    }

    private void markForReplay(Action action) {
        Object target = action.getTarget();
        if (target != null) {
            action.willReplay = true;
            this.failedActions.put(target, action);
        }
    }

    private void batch(BitmapHunter bitmapHunter) {
        if (!bitmapHunter.isCancelled()) {
            this.batch.add(bitmapHunter);
            if (!this.handler.hasMessages(HUNTER_DELAY_NEXT_BATCH)) {
                this.handler.sendEmptyMessageDelayed(HUNTER_DELAY_NEXT_BATCH, 200);
            }
        }
    }

    private void logBatch(List<BitmapHunter> list) {
        if (list != null && !list.isEmpty() && ((BitmapHunter) list.get(AIRPLANE_MODE_OFF)).getPicasso().loggingEnabled) {
            StringBuilder stringBuilder = new StringBuilder();
            for (BitmapHunter bitmapHunter : list) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(Utils.getLogIdsForHunter(bitmapHunter));
            }
            Utils.log(DISPATCHER_THREAD_NAME, "delivered", stringBuilder.toString());
        }
    }
}
