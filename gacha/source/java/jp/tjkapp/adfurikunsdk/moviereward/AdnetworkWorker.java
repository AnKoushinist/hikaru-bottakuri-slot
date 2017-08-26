package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import org.cocos2dx.lib.BuildConfig;

abstract class AdnetworkWorker {
    private static String l;
    protected Activity a;
    protected String b;
    protected Bundle c;
    protected String d;
    protected String e;
    protected String f;
    protected boolean g;
    protected AdfurikunMovieListener h;
    protected Handler i;
    protected LogUtil j;
    protected AdnetworkWorkerListener k;

    public interface AdnetworkWorkerListener {
        void onFinishedPlaying(AdnetworkWorker adnetworkWorker, MovieData movieData);
    }

    public abstract String getAdnetworkKey();

    public abstract MovieData getMovieData();

    protected abstract void initWorker();

    public abstract boolean isEnable();

    public abstract boolean isPrepared();

    public abstract boolean isProvideTestMode();

    public abstract void play(MovieMediater movieMediater);

    AdnetworkWorker() {
    }

    static AdnetworkWorker a(String str, int i) {
        if (i == 14) {
            try {
                l = AdnetworkWorker.class.getPackage().getName() + ".MovieInter_";
            } catch (Exception e) {
            }
        } else if (i == 12) {
            l = AdnetworkWorker.class.getPackage().getName() + ".MovieReward_";
        }
        Object newInstance = Class.forName(l + str).newInstance();
        if (newInstance instanceof AdnetworkWorker) {
            return (AdnetworkWorker) newInstance;
        }
        return null;
    }

    public void setAdnetworkWorkerListener(AdnetworkWorkerListener adnetworkWorkerListener) {
        this.k = adnetworkWorkerListener;
    }

    public void setAdfurikunMovieListener(AdfurikunMovieListener adfurikunMovieListener) {
        this.h = adfurikunMovieListener;
    }

    public void init(Activity activity, String str, AdInfoDetail adInfoDetail, String str2, Handler handler) {
        this.a = activity;
        this.b = str;
        this.c = adInfoDetail.convertParamToBundle();
        this.d = str2;
        this.e = adInfoDetail.userAdId;
        this.f = FileUtil.getGaid(this.a);
        this.g = "1".equals(adInfoDetail.testMode);
        this.i = handler;
        this.j = LogUtil.getInstance(this.a);
        initWorker();
    }

    protected void a() {
        new Thread(new Runnable(this) {
            final /* synthetic */ AdnetworkWorker a;

            {
                this.a = r1;
            }

            public void run() {
                ApiAccessUtil.recFinished(this.a.b, this.a.e, this.a.j, this.a.d, this.a.f, BuildConfig.FLAVOR);
            }
        }).start();
    }

    protected void b() {
        new Thread(new Runnable(this) {
            final /* synthetic */ AdnetworkWorker a;

            {
                this.a = r1;
            }

            public void run() {
                ApiAccessUtil.recImpression(this.a.b, this.a.e, this.a.j, this.a.d, this.a.f, BuildConfig.FLAVOR, BuildConfig.FLAVOR);
            }
        }).start();
    }

    protected void a(AdnetworkWorker adnetworkWorker, MovieData movieData) {
        if (this.k != null) {
            this.k.onFinishedPlaying(adnetworkWorker, movieData);
        }
    }

    protected void a(final MovieData movieData) {
        if (this.h != null && this.a != null) {
            this.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ AdnetworkWorker b;

                public void run() {
                    if (this.b.h != null) {
                        this.b.h.onStartPlaying(movieData);
                    }
                }
            });
        }
    }

    protected void b(final MovieData movieData) {
        if (this.h != null && this.a != null) {
            this.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ AdnetworkWorker b;

                public void run() {
                    if (this.b.h != null) {
                        this.b.h.onFailedPlaying(movieData);
                    }
                }
            });
        }
    }

    protected void c(final MovieData movieData) {
        if (this.h != null && this.a != null) {
            this.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ AdnetworkWorker b;

                public void run() {
                    if (this.b.h != null) {
                        this.b.h.onFinishedPlaying(movieData);
                    }
                }
            });
        }
    }

    protected void d(final MovieData movieData) {
        if (this.h != null && this.a != null) {
            this.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ AdnetworkWorker b;

                public void run() {
                    if (this.b.h != null) {
                        this.b.h.onAdClose(movieData);
                    }
                }
            });
        }
    }

    public void preload() {
    }

    public void start() {
    }

    public void resume(Activity activity) {
    }

    public void pause() {
    }

    public void stop() {
    }

    public void destroy() {
    }
}
