package com.vungle.publisher;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.vungle.log.Logger;
import dagger.Lazy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import twitter4j.TwitterResponse;

@Singleton
/* compiled from: vungle */
public final class bt {
    final c a;
    final c b;
    @Inject
    Lazy<com.vungle.publisher.gm.a> c;
    @Inject
    agt d;
    private final a e;
    private final c f;
    private final BlockingQueue<Runnable> g = new PriorityBlockingQueue();

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        static {
            try {
                a[b.clientEvent.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.externalNetworkRequest.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* compiled from: vungle */
    class a extends Handler {
        final /* synthetic */ bt a;

        /* compiled from: vungle */
        class a implements Comparable<a>, Runnable {
            final /* synthetic */ a a;
            private final Runnable b;
            private final long c;
            private final b d;

            public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
                return this.d.compareTo(((a) obj).d);
            }

            a(a aVar, Runnable runnable, b bVar) {
                this(aVar, runnable, bVar, (byte) 0);
            }

            private a(a aVar, Runnable runnable, b bVar, byte b) {
                this.a = aVar;
                this.b = runnable;
                this.c = -1;
                this.d = bVar;
            }

            public final void run() {
                try {
                    this.b.run();
                    try {
                        if (this.c > 0) {
                            this.a.postDelayed(this, this.c);
                        }
                    } catch (Throwable e) {
                        ((com.vungle.publisher.gm.a) this.a.a.c.get()).a(Logger.ASYNC_TAG, "error rescheduling " + this, e);
                    }
                } catch (Throwable e2) {
                    ((com.vungle.publisher.gm.a) this.a.a.c.get()).a(Logger.ASYNC_TAG, "error executing " + this, e2);
                    try {
                        if (this.c > 0) {
                            this.a.postDelayed(this, this.c);
                        }
                    } catch (Throwable e22) {
                        ((com.vungle.publisher.gm.a) this.a.a.c.get()).a(Logger.ASYNC_TAG, "error rescheduling " + this, e22);
                    }
                } catch (Throwable e222) {
                    Throwable th = e222;
                    try {
                        if (this.c > 0) {
                            this.a.postDelayed(this, this.c);
                        }
                    } catch (Throwable e2222) {
                        ((com.vungle.publisher.gm.a) this.a.a.c.get()).a(Logger.ASYNC_TAG, "error rescheduling " + this, e2222);
                    }
                }
            }

            public final boolean equals(Object obj) {
                if (obj != null && (obj instanceof a)) {
                    if (this.b.equals(((a) obj).b)) {
                        return true;
                    }
                }
                return false;
            }

            public final int hashCode() {
                return this.b.hashCode();
            }

            public final String toString() {
                return "{PriorityRunnable:: taskType: " + this.d + ", repeatMillis: " + this.c + "}";
            }
        }

        a(bt btVar, Looper looper) {
            this.a = btVar;
            super(looper);
        }

        public final void handleMessage(Message message) {
            Object obj = message.obj;
            if (obj == null || !(obj instanceof a)) {
                Logger.w(Logger.ASYNC_TAG, "unhandled message " + message);
                return;
            }
            c cVar;
            b a = ((a) obj).d;
            if (a != null) {
                switch (AnonymousClass1.a[a.ordinal()]) {
                    case TwitterResponse.READ /*1*/:
                        cVar = this.a.a;
                        break;
                }
            }
            cVar = this.a.b;
            Logger.d(Logger.ASYNC_TAG, "processing " + obj);
            Logger.v(Logger.ASYNC_TAG, cVar + ", max pool size " + cVar.getMaximumPoolSize() + ", largest pool size " + cVar.getLargestPoolSize());
            cVar.execute((Runnable) obj);
        }
    }

    /* compiled from: vungle */
    public enum b {
        deviceId,
        databaseWrite,
        requestStreamingAd,
        reportAd,
        reportInstall,
        requestLocalAd,
        prepareLocalAd,
        prepareLocalViewable,
        downloadLocalAd,
        requestConfig,
        sessionEndTimer,
        sessionEnd,
        sessionStart,
        unfilledAd,
        deleteExpiredAds,
        otherTask,
        externalNetworkRequest,
        clientEvent,
        appFingerprint,
        reportExceptions,
        sleepWakeup
    }

    /* compiled from: vungle */
    class c extends ThreadPoolExecutor {
        final /* synthetic */ bt a;

        /* compiled from: vungle */
        class AnonymousClass1 implements ThreadFactory {
            int a = 0;
            final /* synthetic */ bt b;
            final /* synthetic */ String c;

            AnonymousClass1(bt btVar, String str) {
                this.b = btVar;
                this.c = str;
            }

            public final Thread newThread(Runnable runnable) {
                StringBuilder append = new StringBuilder().append(this.c);
                int i = this.a;
                this.a = i + 1;
                String stringBuilder = append.append(i).toString();
                Logger.v(Logger.ASYNC_TAG, "starting " + stringBuilder);
                return new Thread(runnable, stringBuilder);
            }
        }

        c(bt btVar, int i, String str) {
            this.a = btVar;
            super(2, 2, 30, TimeUnit.SECONDS, i, new AnonymousClass1(btVar, str));
            allowCoreThreadTimeOut(true);
        }

        protected final void afterExecute(Runnable runnable, Throwable th) {
            super.afterExecute(runnable, th);
            if (th != null) {
                ((com.vungle.publisher.gm.a) this.a.c.get()).a(Logger.ASYNC_TAG, "error after executing runnable", th);
            }
        }
    }

    @Inject
    bt() {
        HandlerThread handlerThread = new HandlerThread("VungleAsyncMasterThread");
        handlerThread.start();
        this.a = new c(this, new LinkedBlockingQueue(), "VungleAsyncClientEventThread-");
        this.a.allowCoreThreadTimeOut(true);
        this.f = new c(this, new LinkedBlockingQueue(), "VungleAsyncExternalNetworkRequestThread-");
        this.f.allowCoreThreadTimeOut(true);
        this.e = new a(this, handlerThread.getLooper());
        this.b = new c(this, this.g, "VungleAsyncMainThread-");
        this.b.allowCoreThreadTimeOut(true);
    }

    public final void a(Runnable runnable, b bVar) {
        this.e.sendMessage(b(runnable, bVar));
    }

    public final void a(Runnable runnable, long j) {
        a(runnable, b.otherTask, j);
    }

    public final void a(Runnable runnable, b bVar, long j) {
        Logger.d(Logger.ASYNC_TAG, "scheduling " + bVar + " delayed " + j + " ms");
        this.e.sendMessageDelayed(b(runnable, bVar), j);
    }

    private Message b(Runnable runnable, b bVar) {
        a aVar = this.e;
        int ordinal = bVar.ordinal();
        aVar.getClass();
        return aVar.obtainMessage(ordinal, new a(aVar, runnable, bVar));
    }

    public final void a(b bVar) {
        this.e.removeMessages(bVar.ordinal());
    }
}
