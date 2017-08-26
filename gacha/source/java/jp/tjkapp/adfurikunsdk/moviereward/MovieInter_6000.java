package jp.tjkapp.adfurikunsdk.moviereward;

import android.text.TextUtils;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

class MovieInter_6000 extends AdnetworkWorker {
    public static final String ADNETWORK_KEY = "6000";
    public static final String ADNETWORK_NAME = "Applovin";
    private static final MovieInterData p = new MovieInterData(ADNETWORK_KEY, ADNETWORK_NAME);
    AppLovinAdLoadListener l;
    AppLovinAdVideoPlaybackListener m;
    AppLovinAdDisplayListener n;
    AppLovinAdClickListener o;
    private boolean q;
    private AppLovinInterstitialAdDialog r;
    private String s;
    private String t;
    private MovieMediater u;

    MovieInter_6000() {
    }

    AppLovinAdLoadListener c() {
        if (this.l == null) {
            this.l = new AppLovinAdLoadListener(this) {
                final /* synthetic */ MovieInter_6000 a;

                {
                    this.a = r1;
                }

                public void adReceived(AppLovinAd appLovinAd) {
                    this.a.j.debug(Constants.TAG, "MovieInter_6000: preload.adReceived");
                }

                public void failedToReceiveAd(int i) {
                    this.a.j.debug(Constants.TAG, "MovieInter_6000: preload.failedToReceiveAd");
                }
            };
        }
        return this.l;
    }

    private AppLovinAdVideoPlaybackListener e() {
        if (this.m == null) {
            this.m = new AppLovinAdVideoPlaybackListener(this) {
                final /* synthetic */ MovieInter_6000 a;

                {
                    this.a = r1;
                }

                public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
                    this.a.j.debug(Constants.TAG, "MovieInter_6000: playbackListener.videoPlaybackEnded");
                    if (((int) d) == 100 && z) {
                        this.a.a();
                        this.a.c(MovieInter_6000.p);
                    }
                }

                public void videoPlaybackBegan(AppLovinAd appLovinAd) {
                    this.a.j.debug(Constants.TAG, "MovieInter_6000: playbackListener.videoPlaybackBegan");
                }
            };
        }
        return this.m;
    }

    private AppLovinAdDisplayListener f() {
        if (this.n == null) {
            this.n = new AppLovinAdDisplayListener(this) {
                final /* synthetic */ MovieInter_6000 a;

                {
                    this.a = r1;
                }

                public void adHidden(AppLovinAd appLovinAd) {
                    this.a.q = false;
                    this.a.j.debug(Constants.TAG, "MovieInter_6000: displayListener.adHidden");
                    this.a.d(MovieInter_6000.p);
                    this.a.a(this.a, MovieInter_6000.p);
                    this.a.u.mPrevPlaying = false;
                }

                public void adDisplayed(AppLovinAd appLovinAd) {
                    this.a.q = true;
                    this.a.j.debug(Constants.TAG, "MovieInter_6000: displayListener.adDisplayed");
                    this.a.b();
                }
            };
        }
        return this.n;
    }

    private AppLovinAdClickListener g() {
        if (this.o == null) {
            this.o = new AppLovinAdClickListener(this) {
                final /* synthetic */ MovieInter_6000 a;

                {
                    this.a = r1;
                }

                public void adClicked(AppLovinAd appLovinAd) {
                    this.a.j.debug(Constants.TAG, "MovieInter_6000: clickListener.adClicked");
                }
            };
        }
        return this.o;
    }

    public void preload() {
        if (!this.q) {
        }
    }

    public boolean isPrepared() {
        if (!this.s.equals(this.t)) {
            this.j.debug_w(Constants.TAG, "*******************************************************");
            this.j.debug_w(Constants.TAG, "It is different from the package name that is declared.");
            this.j.debug_w(Constants.TAG, "Please AppID is sure not wrong.");
            this.j.debug_w(Constants.TAG, "\u30a2\u30d7\u30ea\u306e\u30d1\u30c3\u30b1\u30fc\u30b8\u540d\u304c\u7533\u544a\u3055\u308c\u3066\u3044\u308b\u3082\u306e\u3068\u9055\u3046\u3088\u3046\u3067\u3059\u3002");
            this.j.debug_w(Constants.TAG, "\u5e83\u544a\u67a0ID\u304c\u9593\u9055\u3063\u3066\u3044\u306a\u3044\u304b\u78ba\u8a8d\u3057\u3066\u304f\u3060\u3055\u3044\u3002");
            this.j.debug_w(Constants.TAG, "\u30a2\u30d7\u30ea\u306e\u30d1\u30c3\u30b1\u30fc\u30b8\u540d: " + this.s);
            this.j.debug_w(Constants.TAG, "\u7533\u8acb\u4e2d\u306e\u30d1\u30c3\u30b1\u30fc\u30b8\u540d: " + this.t);
            this.j.debug_w(Constants.TAG, "*******************************************************");
        }
        this.j.debug(Constants.TAG, "6000: try isAdReadyToDisplay: " + AppLovinInterstitialAd.isAdReadyToDisplay(this.a));
        boolean isAdReadyToDisplay = AppLovinInterstitialAd.isAdReadyToDisplay(this.a);
        this.j.debug(Constants.TAG, "6000: try isPrepared: " + isAdReadyToDisplay);
        return isAdReadyToDisplay;
    }

    public void play(MovieMediater movieMediater) {
        this.j.debug(Constants.TAG, "6000: play");
        if (isPrepared()) {
            this.u = movieMediater;
            a(p);
            if (AppLovinInterstitialAd.isAdReadyToDisplay(this.a)) {
                this.r.setAdLoadListener(c());
            }
            this.r.show();
        }
    }

    public boolean isProvideTestMode() {
        return false;
    }

    public void initWorker() {
        this.j.debug(Constants.TAG, "6000: Applovin init");
        this.q = false;
        this.r = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(this.a.getApplicationContext()), this.a);
        this.r.setAdClickListener(g());
        this.r.setAdDisplayListener(f());
        this.r.setAdVideoPlaybackListener(e());
        this.s = this.a.getApplication().getPackageName().toLowerCase();
        Object string = this.c.getString("package_name");
        this.t = TextUtils.isEmpty(string) ? "\u30d1\u30c3\u30b1\u30fc\u30b8\u540d\u304c\u672a\u8a2d\u5b9a" : string.toLowerCase();
    }

    public String getAdnetworkKey() {
        return ADNETWORK_KEY;
    }

    public void destroy() {
        if (this.r != null) {
            this.r.dismiss();
            this.r = null;
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

    public MovieInterData getMovieData() {
        return p;
    }
}
