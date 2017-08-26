package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;
import com.jirbo.adcolony.AdColony;
import com.jirbo.adcolony.AdColonyAd;
import com.jirbo.adcolony.AdColonyAdListener;
import com.jirbo.adcolony.AdColonyV4VCAd;
import com.jirbo.adcolony.AdColonyV4VCListener;
import com.jirbo.adcolony.AdColonyV4VCReward;
import com.tapjoy.TapjoyConstants;

class MovieReward_6002 extends AdnetworkWorker {
    public static final String ADNETWORK_KEY = "6002";
    public static final String ADNETWORK_NAME = "AdColony";
    private static final MovieRewardData n = new MovieRewardData(ADNETWORK_KEY, ADNETWORK_NAME);
    AdColonyV4VCListener l;
    AdColonyAdListener m;
    private String o;
    private String p;
    private String[] q;

    MovieReward_6002() {
    }

    private AdColonyV4VCListener e() {
        if (this.l == null) {
            this.l = new AdColonyV4VCListener(this) {
                final /* synthetic */ MovieReward_6002 a;

                {
                    this.a = r1;
                }

                public void onAdColonyV4VCReward(AdColonyV4VCReward adColonyV4VCReward) {
                    this.a.j.detail(Constants.TAG, "6002: v4vcListener.onAdColonyV4VCReward");
                    if (adColonyV4VCReward.success()) {
                        this.a.a();
                        this.a.c(MovieReward_6002.n);
                    } else {
                        this.a.b(MovieReward_6002.n);
                    }
                    AdColony.removeV4VCListener(this.a.e());
                }
            };
        }
        return this.l;
    }

    AdColonyAdListener c() {
        if (this.m == null) {
            this.m = new AdColonyAdListener(this) {
                final /* synthetic */ MovieReward_6002 a;

                {
                    this.a = r1;
                }

                public void onAdColonyAdStarted(AdColonyAd adColonyAd) {
                    this.a.j.detail(Constants.TAG, "6002: adListener.onAdColonyAdStarted");
                    this.a.b();
                }

                public void onAdColonyAdAttemptFinished(AdColonyAd adColonyAd) {
                    this.a.j.detail(Constants.TAG, "6002: adListener.onAdColonyAdAttemptFinished");
                    if (adColonyAd.shown()) {
                        this.a.j.detail(Constants.TAG, "6002: adListener.ad.shown()");
                        this.a.a(this.a, MovieReward_6002.n);
                    }
                    if (adColonyAd.notShown()) {
                        this.a.j.detail(Constants.TAG, "6002: adListener.ad.notShown()");
                    }
                    if (adColonyAd.skipped()) {
                        this.a.j.detail(Constants.TAG, "6002: adListener.ad.skipped()");
                        this.a.a(this.a, MovieReward_6002.n);
                    }
                    if (adColonyAd.canceled()) {
                        this.a.j.detail(Constants.TAG, "6002: adListener.ad.canceled()");
                    }
                    if (adColonyAd.noFill()) {
                        this.a.j.detail(Constants.TAG, "6002: adListener.ad.noFill()");
                    }
                    this.a.d(MovieReward_6002.n);
                }
            };
        }
        return this.m;
    }

    public boolean isPrepared() {
        boolean isReady = new AdColonyV4VCAd(this.p).isReady();
        this.j.debug(Constants.TAG, "6002: try isPrepared: " + isReady);
        return isReady;
    }

    public void play(MovieMediater movieMediater) {
        this.j.debug(Constants.TAG, "6002: play");
        if (isPrepared()) {
            AdColonyV4VCAd withListener = new AdColonyV4VCAd(this.p).withListener(c());
            a(n);
            AdColony.addV4VCListener(e());
            withListener.show();
        }
    }

    public boolean isProvideTestMode() {
        return false;
    }

    public void initWorker() {
        this.j.debug(Constants.TAG, "6002: AdColony init");
        this.o = this.c.getString(TapjoyConstants.TJC_APP_ID);
        this.p = this.c.getString("zone_id");
        this.q = this.c.getStringArray("all_zones");
        String str = "version:1.0,store:google";
        if (!AdColony.isConfigured()) {
            if (this.q == null || this.q.length <= 0) {
                AdColony.configure(this.a, "version:1.0,store:google", this.o, this.p);
                return;
            }
            AdColony.configure(this.a, "version:1.0,store:google", this.o, this.q);
        }
    }

    public String getAdnetworkKey() {
        return ADNETWORK_KEY;
    }

    public void resume(Activity activity) {
        AdColony.resume(activity);
    }

    public void pause() {
        AdColony.pause();
    }

    public void destroy() {
        AdColony.cancelVideo();
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("com.jirbo.adcolony.AdColony") != null;
            if (z) {
                return z;
            }
            this.j.debug_w(Constants.TAG, "6002: sdk not found.");
            return z;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public MovieRewardData getMovieData() {
        return n;
    }
}
