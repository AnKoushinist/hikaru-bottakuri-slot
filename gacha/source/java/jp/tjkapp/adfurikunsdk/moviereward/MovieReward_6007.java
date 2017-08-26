package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;
import jp.gmotech.smaad.video.ad.a;
import jp.gmotech.smaad.video.ad.b;

class MovieReward_6007 extends AdnetworkWorker {
    public static final String ADNETWORK_KEY = "6007";
    public static final String ADNETWORK_NAME = "SmaADVideo";
    private static final MovieRewardData l = new MovieRewardData(ADNETWORK_KEY, ADNETWORK_NAME);
    private String m;
    private String n;
    private b o;

    MovieReward_6007() {
    }

    private b d() {
        if (this.o == null) {
            this.o = new b(this) {
                final /* synthetic */ MovieReward_6007 a;
                private boolean b;

                {
                    this.a = r1;
                }

                public void onSmaAdVideoInitEnd() {
                    this.a.j.detail(Constants.TAG, "6007: SmaAdVideoListener.onSmaAdVideoInitEnd");
                }

                public void onSmaAdVideoInitError(int i) {
                    this.a.j.detail(Constants.TAG, "6007: SmaAdVideoListener.onSmaAdVideoInitError errorCode" + i);
                }

                public void onSmaAdVideoReady() {
                    this.a.j.debug(Constants.TAG, "6007: SmaAdVideoListener.onSmaAdVideoReady");
                }

                public void onSmaAdVideoError(int i) {
                    this.a.j.debug(Constants.TAG, "6007: SmaAdVideoListener.onSmaAdVideoError errorCode:" + i);
                    this.b = false;
                }

                public void onSmaAdVideoStart() {
                    this.a.j.debug(Constants.TAG, "6007: SmaAdVideoListener.onSmaAdVideoStart");
                    if (!this.b) {
                        this.a.a(MovieReward_6007.l);
                        this.a.b();
                        this.b = true;
                    }
                }

                public void onSmaAdVideoComplete(String str) {
                    this.a.j.debug(Constants.TAG, "6007: SmaAdVideoListener.onSmaAdVideoComplete");
                    this.a.a();
                    this.a.c(MovieReward_6007.l);
                    this.b = false;
                }

                public void onSmaAdVideoStop() {
                    this.a.j.debug(Constants.TAG, "6007: SmaAdVideoListener.onSmaAdVideoStop");
                    this.a.b(MovieReward_6007.l);
                    this.a.a(this.a, MovieReward_6007.l);
                    this.b = false;
                }

                public void onSmaAdEndcardClosed() {
                    this.a.j.debug(Constants.TAG, "6007: SmaAdVideoListener.onSmaAdEndcardClosed");
                    this.a.d(MovieReward_6007.l);
                    this.a.a(this.a, MovieReward_6007.l);
                    this.b = false;
                }
            };
        }
        return this.o;
    }

    public boolean isPrepared() {
        boolean b = a.b(this.m);
        this.j.debug(Constants.TAG, "6007: \u8aad\u307f\u8fbc\u307f\u30c1\u30a7\u30c3\u30af " + b);
        return b;
    }

    public void play(MovieMediater movieMediater) {
        this.j.debug(Constants.TAG, "6007: play");
        if (isPrepared()) {
            a.a(this.a, this.m);
        }
    }

    public boolean isProvideTestMode() {
        return false;
    }

    public void initWorker() {
        this.j.debug(Constants.TAG, "6007: SmaAD Video init");
        a.a(false);
        this.n = this.c.getString("media_id");
        this.m = this.c.getString("zone_id");
        a.a(this.a, this.m, true, d());
    }

    public String getAdnetworkKey() {
        return ADNETWORK_KEY;
    }

    public void resume(Activity activity) {
        a.c(this.m);
    }

    public void pause() {
        a.d(this.m);
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("jp.gmotech.smaad.video.ad.a") != null;
            if (z) {
                return z;
            }
            this.j.debug_w(Constants.TAG, "6007: sdk not found.");
            return z;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public MovieRewardData getMovieData() {
        return l;
    }
}
