package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;
import com.jirbo.adcolony.AdColony;
import com.jirbo.adcolony.AdColonyAd;
import com.jirbo.adcolony.AdColonyAdListener;
import com.jirbo.adcolony.AdColonyVideoAd;
import com.tapjoy.TapjoyConstants;

class MovieInter_6002 extends AdnetworkWorker {
    public static final String ADNETWORK_KEY = "6002";
    public static final String ADNETWORK_NAME = "AdColony";
    private static final MovieInterData m = new MovieInterData(ADNETWORK_KEY, ADNETWORK_NAME);
    AdColonyAdListener l;
    private String n;
    private String o;
    private String[] p;

    MovieInter_6002() {
    }

    AdColonyAdListener c() {
        if (this.l == null) {
            this.l = new AdColonyAdListener(this) {
                final /* synthetic */ MovieInter_6002 a;

                {
                    this.a = r1;
                }

                public void onAdColonyAdStarted(AdColonyAd adColonyAd) {
                    this.a.j.detail(Constants.TAG, "MovieInter_6002: adListener.onAdColonyAdStarted");
                    this.a.b();
                }

                public void onAdColonyAdAttemptFinished(AdColonyAd adColonyAd) {
                    this.a.j.detail(Constants.TAG, "MovieInter_6002: adListener.onAdColonyAdAttemptFinished");
                    if (adColonyAd.shown()) {
                        this.a.j.detail(Constants.TAG, "MovieInter_6002: adListener.ad.shown()");
                        this.a.a(this.a, MovieInter_6002.m);
                        this.a.a();
                        this.a.c(MovieInter_6002.m);
                    }
                    if (adColonyAd.notShown()) {
                        this.a.j.detail(Constants.TAG, "MovieInter_6002: adListener.ad.notShown()");
                    }
                    if (adColonyAd.skipped()) {
                        this.a.j.detail(Constants.TAG, "MovieInter_6002: adListener.ad.skipped()");
                        this.a.a(this.a, MovieInter_6002.m);
                    }
                    if (adColonyAd.canceled()) {
                        this.a.j.detail(Constants.TAG, "MovieInter_6002: adListener.ad.canceled()");
                    }
                    if (adColonyAd.noFill()) {
                        this.a.j.detail(Constants.TAG, "MovieInter_6002: adListener.ad.noFill()");
                        this.a.b(MovieInter_6002.m);
                    }
                    this.a.d(MovieInter_6002.m);
                }
            };
        }
        return this.l;
    }

    public boolean isPrepared() {
        boolean isReady = new AdColonyVideoAd(this.o).isReady();
        this.j.debug(Constants.TAG, "MovieInter_6002: try isPrepared: " + isReady);
        return isReady;
    }

    public void play(MovieMediater movieMediater) {
        this.j.debug(Constants.TAG, "MovieInter_6002: play");
        if (isPrepared()) {
            AdColonyVideoAd withListener = new AdColonyVideoAd(this.o).withListener(c());
            a(m);
            withListener.show();
        }
    }

    public boolean isProvideTestMode() {
        return false;
    }

    public void initWorker() {
        this.j.debug(Constants.TAG, "6002: AdColony init");
        this.n = this.c.getString(TapjoyConstants.TJC_APP_ID);
        this.o = this.c.getString("zone_id");
        this.p = this.c.getStringArray("all_zones");
        String str = "version:1.0,store:google";
        if (!AdColony.isConfigured()) {
            if (this.p == null || this.p.length <= 0) {
                AdColony.configure(this.a, "version:1.0,store:google", this.n, this.o);
                return;
            }
            AdColony.configure(this.a, "version:1.0,store:google", this.n, this.p);
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
            this.j.debug_w(Constants.TAG, "MovieInter_6002: sdk not found.");
            return z;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public MovieInterData getMovieData() {
        return m;
    }
}
