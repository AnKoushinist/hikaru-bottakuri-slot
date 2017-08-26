package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import jp.tjkapp.adfurikunsdk.moviereward.AdnetworkWorker.AdnetworkWorkerListener;
import jp.tjkapp.adfurikunsdk.moviereward.GetInfo.GetInfoListener;
import org.cocos2dx.lib.GameControllerDelegate;
import twitter4j.TwitterResponse;

class MovieMediater {
    private ArrayList<AdnetworkWorker> a;
    private LinkedList<AdnetworkWorker> b;
    private Activity c;
    private String d;
    private String e;
    private GetInfo f;
    private IMediator g;
    private HashMap<Integer, IMediator> h;
    private LogUtil i;
    private Handler j;
    private AdfurikunMovieListener k;
    private WorkerListener l;
    private int m;
    public boolean mPrevPlaying;
    private boolean n;
    private boolean o;
    private MediatorCycleState p;
    private GetInfoListener q = new GetInfoListener(this) {
        final /* synthetic */ MovieMediater a;

        {
            this.a = r1;
        }

        public void updateSuccess(AdInfo adInfo) {
            if (adInfo != null && this.a.g != null) {
                this.a.g.setAdInfo(adInfo);
            }
        }

        public void updateFail(int i, String str, Exception exception) {
            this.a.i.detail_i(Constants.TAG, "\u914d\u4fe1\u60c5\u5831\u304c\u3042\u308a\u307e\u305b\u3093\u3002" + str);
            if (!this.a.needTaskStop()) {
                this.a.i.detail(Constants.TAG, "GetInfo\u306e\u518d\u53d6\u5f97\u3092\u958b\u59cb");
                this.a.m = this.a.m + 1;
                this.a.j.postDelayed(this.a.r, (long) (this.a.m * GameControllerDelegate.THUMBSTICK_LEFT_X));
            }
        }
    };
    private Runnable r = new Runnable(this) {
        final /* synthetic */ MovieMediater a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.needTaskStop()) {
                this.a.i.detail(Constants.TAG, "\u30a2\u30d7\u30ea\u505c\u6b62\u4e2d: GetInfoRetryTask\u3092\u7d42\u4e86");
            } else if (this.a.f != null) {
                this.a.f.forceUpdate();
            }
        }
    };

    enum MediatorCycleState {
        INIT,
        START,
        RESUME,
        PAUSE,
        STOP,
        DESTROY
    }

    private class WorkerListener implements AdnetworkWorkerListener {
        final /* synthetic */ MovieMediater a;

        private WorkerListener(MovieMediater movieMediater) {
            this.a = movieMediater;
        }

        public void onFinishedPlaying(AdnetworkWorker adnetworkWorker, MovieData movieData) {
            this.a.i.debug(Constants.TAG, "\u52d5\u753b\u518d\u751f\u7d42\u4e86:" + movieData.adnetworkName);
            if (!(!this.a.n || this.a.b.isEmpty() || this.a.k == null)) {
                this.a.c.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ WorkerListener a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.a.k != null) {
                            this.a.a.k.onPrepareSuccess();
                        }
                    }
                });
                this.a.n = false;
                this.a.g.setNeedNotify(this.a.n);
            }
            this.a.i.detail(Constants.TAG, "\u30cd\u30c3\u30c8\u30ef\u30fc\u30af\u63a5\u7d9a\u5148\u3092\u78ba\u8a8d\u3059\u308b");
            this.a.a();
            this.a.i.detail_i(Constants.TAG, "requestNextPrepare call: " + movieData.adnetworkKey + ", " + movieData.adnetworkName);
        }
    }

    public MovieMediater(Activity activity, String str, String str2) {
        this.c = activity;
        this.d = str;
        this.e = str2;
        this.i = LogUtil.getInstance(this.c);
        this.f = new GetInfo(this.c, this.d, this.e);
        HandlerThread handlerThread = new HandlerThread("adfurikun_movie_reward");
        handlerThread.start();
        this.j = new Handler(handlerThread.getLooper());
        this.a = new ArrayList();
        this.b = new LinkedList();
        this.h = new HashMap();
        this.l = new WorkerListener();
        this.n = true;
        this.p = MediatorCycleState.INIT;
    }

    public void setAdfurikunMovieListener(AdfurikunMovieListener adfurikunMovieListener) {
        this.k = adfurikunMovieListener;
        for (int i = 0; i < this.a.size(); i++) {
            AdnetworkWorker adnetworkWorker = (AdnetworkWorker) this.a.get(i);
            if (adnetworkWorker != null) {
                adnetworkWorker.setAdfurikunMovieListener(this.k);
            }
        }
        if (this.g != null) {
            this.g.setAdfurikunMovieListener(this.k);
        }
    }

    public LinkedList<AdnetworkWorker> getPlayableList() {
        return this.b;
    }

    public AdnetworkWorker getPlayableWorker() {
        if (!this.b.isEmpty()) {
            for (int i = 0; i < this.a.size(); i++) {
                AdnetworkWorker adnetworkWorker = (AdnetworkWorker) this.a.get(i);
                if (adnetworkWorker != null) {
                    int indexOf = this.b.indexOf(adnetworkWorker);
                    if (indexOf != -1) {
                        this.n = true;
                        this.g.setNeedNotify(this.n);
                        return (AdnetworkWorker) this.b.remove(indexOf);
                    }
                }
            }
        }
        return null;
    }

    public boolean isTestMode() {
        if (!this.b.isEmpty()) {
            this.o = ((AdnetworkWorker) this.b.peek()).g;
        }
        return this.o;
    }

    private synchronized boolean a() {
        boolean z;
        IMediator iMediator;
        int b = b();
        IMediator iMediator2 = (IMediator) this.h.get(Integer.valueOf(b));
        if (iMediator2 == null) {
            switch (b) {
                case TwitterResponse.READ /*1*/:
                    iMediator2 = new MediatorWifi();
                    this.i.detail(Constants.TAG, "Wifi\u306b\u63a5\u7d9a\u3057\u3066\u3044\u308b");
                    break;
                default:
                    iMediator2 = new MediatorMobile();
                    this.i.detail(Constants.TAG, "\u30ad\u30e3\u30ea\u30a2\u306b\u63a5\u7d9a\u3057\u3066\u3044\u308b");
                    break;
            }
            iMediator2.init(this.c, this.d, this.e, this.j, this.a, this.b, this.l, this);
            this.h.put(Integer.valueOf(b), iMediator2);
            iMediator = iMediator2;
        } else {
            iMediator = iMediator2;
        }
        if (this.g == iMediator) {
            z = false;
        } else {
            this.i.detail(Constants.TAG, b == 0 ? "\u30ad\u30e3\u30ea\u30a2\u306b\u5207\u308a\u66ff\u3048\u305f" : "Wifi\u306b\u5207\u308a\u66ff\u3048\u305f");
            if (this.g != null) {
                this.i.detail(Constants.TAG, "\u4ee5\u524d\u306e\u30e1\u30c7\u30a3\u30a8\u30fc\u30bf\u3092\u505c\u6b62");
                this.g.stop();
            }
            this.i.detail(Constants.TAG, "\u30e1\u30c7\u30a3\u30a8\u30fc\u30bf\u3092\u4ea4\u63db");
            this.g = iMediator;
            this.g.setNeedNotify(this.n);
            this.g.setAdfurikunMovieListener(this.k);
            this.g.setAdInfo(this.f.getAdInfo());
            this.i.detail(Constants.TAG, "\u30e1\u30c7\u30a3\u30a8\u30fc\u30bf\u3092\u958b\u59cb");
            this.g.start();
            z = true;
        }
        return z;
    }

    private int b() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.c.getSystemService("connectivity")).getActiveNetworkInfo();
        return (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) ? 0 : 1;
    }

    public boolean needTaskStop() {
        return this.p == MediatorCycleState.STOP || this.p == MediatorCycleState.DESTROY;
    }

    public void start() {
        this.p = MediatorCycleState.START;
        this.mPrevPlaying = false;
        this.f.setGetInfoListener(this.q);
        for (int i = 0; i < this.a.size(); i++) {
            AdnetworkWorker adnetworkWorker = (AdnetworkWorker) this.a.get(i);
            if (adnetworkWorker != null) {
                adnetworkWorker.start();
            }
        }
    }

    public void resume() {
        this.p = MediatorCycleState.RESUME;
        this.mPrevPlaying = false;
        for (int i = 0; i < this.a.size(); i++) {
            AdnetworkWorker adnetworkWorker = (AdnetworkWorker) this.a.get(i);
            if (adnetworkWorker != null) {
                adnetworkWorker.resume(this.c);
            }
        }
        if (!a()) {
            this.i.detail(Constants.TAG, "\u30cd\u30c3\u30c8\u30ef\u30fc\u30af\u304c\u5909\u66f4\u3055\u308c\u3066\u3044\u306a\u3044\u306e\u3067\u3001\u65e2\u5b58\u306e\u30e1\u30c7\u30a3\u30a8\u30fc\u30bf\u3092\u958b\u59cb");
            this.g.start();
        }
    }

    public void pause() {
        this.p = MediatorCycleState.PAUSE;
        if (this.g != null) {
            this.g.stop();
        }
        for (int i = 0; i < this.a.size(); i++) {
            AdnetworkWorker adnetworkWorker = (AdnetworkWorker) this.a.get(i);
            if (adnetworkWorker != null) {
                adnetworkWorker.pause();
            }
        }
    }

    public void stop() {
        this.p = MediatorCycleState.STOP;
        if (this.f != null) {
            this.f.setGetInfoListener(null);
        }
        for (int i = 0; i < this.a.size(); i++) {
            AdnetworkWorker adnetworkWorker = (AdnetworkWorker) this.a.get(i);
            if (adnetworkWorker != null) {
                adnetworkWorker.stop();
            }
        }
    }

    public void destroy() {
        this.p = MediatorCycleState.DESTROY;
        try {
            if (this.f != null) {
                this.f.destroy();
            }
            if (this.g != null) {
                this.g.destroy();
            }
            for (int i = 0; i < this.a.size(); i++) {
                AdnetworkWorker adnetworkWorker = (AdnetworkWorker) this.a.get(i);
                if (adnetworkWorker != null) {
                    adnetworkWorker.destroy();
                }
            }
            this.b.clear();
            this.a.clear();
        } catch (Exception e) {
        }
    }
}
