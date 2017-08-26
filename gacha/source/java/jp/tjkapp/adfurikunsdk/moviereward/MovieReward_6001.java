package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAds.FinishState;
import com.unity3d.ads.UnityAds.UnityAdsError;
import twitter4j.TwitterResponse;

class MovieReward_6001 extends AdnetworkWorker {
    public static final String ADNETWORK_KEY = "6001";
    public static final String ADNETWORK_NAME = "UnityAds";
    private static final MovieRewardData l = new MovieRewardData(ADNETWORK_KEY, ADNETWORK_NAME);
    private String m;
    private IUnityAdsListener n;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[FinishState.values().length];

        static {
            try {
                a[FinishState.COMPLETED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[FinishState.SKIPPED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[FinishState.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    MovieReward_6001() {
    }

    private IUnityAdsListener d() {
        if (this.n == null) {
            this.n = new IUnityAdsListener(this) {
                final /* synthetic */ MovieReward_6001 a;

                {
                    this.a = r1;
                }

                public void onUnityAdsReady(String str) {
                    this.a.j.debug(Constants.TAG, "6001: IUnityAdsListener.onUnityAdsReady zoneId:" + str);
                }

                public void onUnityAdsStart(String str) {
                    this.a.j.debug(Constants.TAG, "6001: IUnityAdsListener.onUnityAdsStart zoneId:" + str);
                    this.a.a(MovieReward_6001.l);
                    this.a.b();
                }

                public void onUnityAdsFinish(String str, FinishState finishState) {
                    this.a.j.debug(Constants.TAG, "6001: IUnityAdsListener.onUnityAdsFinish zoneId:" + str + ", result:" + finishState.toString());
                    switch (AnonymousClass2.a[finishState.ordinal()]) {
                        case TwitterResponse.READ /*1*/:
                            this.a.c(MovieReward_6001.l);
                            this.a.a();
                            break;
                        default:
                            this.a.b(MovieReward_6001.l);
                            break;
                    }
                    this.a.a(this.a, MovieReward_6001.l);
                    this.a.d(MovieReward_6001.l);
                }

                public void onUnityAdsError(UnityAdsError unityAdsError, String str) {
                    this.a.j.debug(Constants.TAG, "6001: IUnityAdsListener.onUnityAdsError error:" + unityAdsError.toString() + ", message:" + str);
                }
            };
        }
        return this.n;
    }

    public boolean isPrepared() {
        boolean isReady = UnityAds.isReady();
        this.j.debug(Constants.TAG, "6001: try isPrepared: " + isReady);
        return isReady;
    }

    public void play(MovieMediater movieMediater) {
        this.j.debug(Constants.TAG, "6001: play");
        if (isPrepared()) {
            UnityAds.show(this.a);
            this.j.detail(Constants.TAG, "6001: \u52d5\u753b\u8868\u793aOK");
        }
    }

    public boolean isProvideTestMode() {
        return true;
    }

    public void initWorker() {
        this.j.debug(Constants.TAG, "6001: UnityAds init");
        this.m = this.c.getString("game_id");
        UnityAds.initialize(this.a, this.m, d(), this.g);
    }

    public String getAdnetworkKey() {
        return ADNETWORK_KEY;
    }

    public void resume(Activity activity) {
    }

    public void pause() {
    }

    public void destroy() {
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("com.unity3d.ads.UnityAds") != null;
            if (z) {
                return z;
            }
            this.j.debug_w(Constants.TAG, "6001: sdk not found.");
            return z;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public MovieRewardData getMovieData() {
        return l;
    }
}
