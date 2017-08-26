package jp.tjkapp.adfurikunsdk.moviereward;

import android.text.TextUtils;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import java.util.Map;

class MovieReward_6000 extends AdnetworkWorker {
    public static final String ADNETWORK_KEY = "6000";
    public static final String ADNETWORK_NAME = "Applovin";
    private static final MovieRewardData q = new MovieRewardData(ADNETWORK_KEY, ADNETWORK_NAME);
    AppLovinAdLoadListener l;
    AppLovinAdRewardListener m;
    AppLovinAdVideoPlaybackListener n;
    AppLovinAdDisplayListener o;
    AppLovinAdClickListener p;
    private boolean r;
    private boolean s;
    private AppLovinIncentivizedInterstitial t;
    private String u;
    private String v;

    MovieReward_6000() {
    }

    AppLovinAdLoadListener c() {
        if (this.l == null) {
            this.l = new AppLovinAdLoadListener(this) {
                final /* synthetic */ MovieReward_6000 a;

                {
                    this.a = r1;
                }

                public void adReceived(AppLovinAd appLovinAd) {
                    this.a.j.debug(Constants.TAG, "6000: preload.adReceived");
                    this.a.r = true;
                }

                public void failedToReceiveAd(int i) {
                    this.a.j.debug(Constants.TAG, "6000: preload.failedToReceiveAd");
                    this.a.r = false;
                }
            };
        }
        return this.l;
    }

    private AppLovinAdRewardListener e() {
        if (this.m == null) {
            this.m = new AppLovinAdRewardListener(this) {
                final /* synthetic */ MovieReward_6000 a;

                {
                    this.a = r1;
                }

                public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
                    this.a.j.detail(Constants.TAG, "6000: rewardListener.validationRequestFailed");
                }

                public void userRewardVerified(AppLovinAd appLovinAd, Map map) {
                    this.a.j.detail(Constants.TAG, "6000: rewardListener.userRewardVerified");
                }

                public void userRewardRejected(AppLovinAd appLovinAd, Map map) {
                    this.a.j.detail(Constants.TAG, "6000: rewardListener.userRewardRejected");
                }

                public void userOverQuota(AppLovinAd appLovinAd, Map map) {
                    this.a.j.detail(Constants.TAG, "6000: rewardListener.userOverQuota");
                }

                public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
                    this.a.j.detail(Constants.TAG, "6000: rewardListener.userDeclinedToViewAd");
                }
            };
        }
        return this.m;
    }

    private AppLovinAdVideoPlaybackListener f() {
        if (this.n == null) {
            this.n = new AppLovinAdVideoPlaybackListener(this) {
                final /* synthetic */ MovieReward_6000 a;

                {
                    this.a = r1;
                }

                public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
                    this.a.j.debug(Constants.TAG, "6000: playbackListener.videoPlaybackEnded");
                    if (((int) d) == 100 && z) {
                        this.a.a();
                        this.a.c(MovieReward_6000.q);
                        return;
                    }
                    this.a.b(MovieReward_6000.q);
                }

                public void videoPlaybackBegan(AppLovinAd appLovinAd) {
                    this.a.j.debug(Constants.TAG, "6000: playbackListener.videoPlaybackBegan");
                }
            };
        }
        return this.n;
    }

    private AppLovinAdDisplayListener g() {
        if (this.o == null) {
            this.o = new AppLovinAdDisplayListener(this) {
                final /* synthetic */ MovieReward_6000 a;

                {
                    this.a = r1;
                }

                public void adHidden(AppLovinAd appLovinAd) {
                    if (this.a.t != null) {
                        this.a.t.preload(this.a.c());
                        this.a.s = false;
                        this.a.j.debug(Constants.TAG, "6000: displayListener.adHidden");
                        this.a.d(MovieReward_6000.q);
                        this.a.a(this.a, MovieReward_6000.q);
                    }
                }

                public void adDisplayed(AppLovinAd appLovinAd) {
                    this.a.s = true;
                    this.a.j.debug(Constants.TAG, "6000: displayListener.adDisplayed");
                    this.a.b();
                }
            };
        }
        return this.o;
    }

    private AppLovinAdClickListener h() {
        if (this.p == null) {
            this.p = new AppLovinAdClickListener(this) {
                final /* synthetic */ MovieReward_6000 a;

                {
                    this.a = r1;
                }

                public void adClicked(AppLovinAd appLovinAd) {
                    this.a.j.debug(Constants.TAG, "6000: clickListener.adClicked");
                }
            };
        }
        return this.p;
    }

    public void preload() {
        if (!this.s && this.t != null) {
            this.t.preload(c());
        }
    }

    public boolean isPrepared() {
        if (!this.u.equals(this.v)) {
            this.j.debug_w(Constants.TAG, "*******************************************************");
            this.j.debug_w(Constants.TAG, "It is different from the package name that is declared.");
            this.j.debug_w(Constants.TAG, "Please AppID is sure not wrong.");
            this.j.debug_w(Constants.TAG, "\u30a2\u30d7\u30ea\u306e\u30d1\u30c3\u30b1\u30fc\u30b8\u540d\u304c\u7533\u544a\u3055\u308c\u3066\u3044\u308b\u3082\u306e\u3068\u9055\u3046\u3088\u3046\u3067\u3059\u3002");
            this.j.debug_w(Constants.TAG, "\u5e83\u544a\u67a0ID\u304c\u9593\u9055\u3063\u3066\u3044\u306a\u3044\u304b\u78ba\u8a8d\u3057\u3066\u304f\u3060\u3055\u3044\u3002");
            this.j.debug_w(Constants.TAG, "\u30a2\u30d7\u30ea\u306e\u30d1\u30c3\u30b1\u30fc\u30b8\u540d: " + this.u);
            this.j.debug_w(Constants.TAG, "\u7533\u8acb\u4e2d\u306e\u30d1\u30c3\u30b1\u30fc\u30b8\u540d: " + this.v);
            this.j.debug_w(Constants.TAG, "*******************************************************");
        }
        boolean z = this.t != null && this.r && this.t.isAdReadyToDisplay();
        this.j.debug(Constants.TAG, "6000: try isPrepared: " + z);
        return z;
    }

    public void play(MovieMediater movieMediater) {
        this.j.debug(Constants.TAG, "6000: play");
        if (isPrepared()) {
            a(q);
            this.t.show(this.a, e(), f(), g(), h());
            this.r = false;
        }
    }

    public boolean isProvideTestMode() {
        return false;
    }

    public void initWorker() {
        this.j.debug(Constants.TAG, "6000: Applovin init");
        this.s = false;
        String str = this.e;
        AppLovinSdk.initializeSdk(this.a);
        this.t = AppLovinIncentivizedInterstitial.create(this.a);
        this.t.setUserIdentifier(str);
        this.u = this.a.getApplication().getPackageName().toLowerCase();
        Object string = this.c.getString("package_name");
        this.v = TextUtils.isEmpty(string) ? "\u30d1\u30c3\u30b1\u30fc\u30b8\u540d\u304c\u672a\u8a2d\u5b9a" : string.toLowerCase();
    }

    public String getAdnetworkKey() {
        return ADNETWORK_KEY;
    }

    public void destroy() {
        if (this.t != null) {
            this.t.dismiss();
            this.t = null;
        }
        this.a = null;
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("com.applovin.sdk.AppLovinSdk") != null;
            if (z) {
                return z;
            }
            this.j.debug_w(Constants.TAG, "6000: sdk not found.");
            return z;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public MovieRewardData getMovieData() {
        return q;
    }
}
