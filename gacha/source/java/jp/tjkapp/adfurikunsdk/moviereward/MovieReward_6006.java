package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;
import com.vungle.publisher.AdConfig;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.Orientation;
import com.vungle.publisher.VunglePub;

class MovieReward_6006 extends AdnetworkWorker {
    public static final String ADNETWORK_KEY = "6006";
    public static final String ADNETWORK_NAME = "Vungle";
    private static final MovieRewardData l = new MovieRewardData(ADNETWORK_KEY, ADNETWORK_NAME);
    private VunglePub m;
    private AdConfig n;
    private EventListener o;

    MovieReward_6006() {
    }

    private EventListener d() {
        if (this.o == null) {
            this.o = new EventListener(this) {
                final /* synthetic */ MovieReward_6006 a;

                {
                    this.a = r1;
                }

                public void onAdEnd(boolean z, boolean z2) {
                    this.a.j.debug(Constants.TAG, "6006: EventListener.onAdEnd, wasSuccessfulView:" + z + ", wasCallToActionClicked:" + z2);
                    if (z) {
                        this.a.a();
                        this.a.c(MovieReward_6006.l);
                    } else {
                        this.a.b(MovieReward_6006.l);
                    }
                    this.a.d(MovieReward_6006.l);
                    this.a.a(this.a, MovieReward_6006.l);
                }

                public void onAdStart() {
                    this.a.j.debug(Constants.TAG, "6006: EventListener.onAdStart");
                    this.a.b();
                    this.a.a(MovieReward_6006.l);
                }

                public void onAdUnavailable(String str) {
                    this.a.j.debug(Constants.TAG, "6006: EventListener.onAdUnavailable, reason:" + str);
                }

                public void onAdPlayableChanged(boolean z) {
                    this.a.j.debug(Constants.TAG, "6006: EventListener.onAdPlayableChanged, isAdPlayable:" + z);
                }

                public void onVideoView(boolean z, int i, int i2) {
                    this.a.j.debug(Constants.TAG, "6006: EventListener.onVideoView, isCompletedView:" + z + ", watchedMillis:" + i + ", videoMillis:" + i2);
                }
            };
        }
        return this.o;
    }

    public boolean isPrepared() {
        boolean z = this.m != null && this.m.isAdPlayable();
        this.j.debug(Constants.TAG, "6006: try isPrepared: " + z);
        return z;
    }

    public void play(MovieMediater movieMediater) {
        this.j.debug(Constants.TAG, "6006: play");
        if (isPrepared()) {
            this.m.playAd(this.n);
        }
    }

    public boolean isProvideTestMode() {
        return false;
    }

    public void initWorker() {
        this.j.debug(Constants.TAG, "6006: Vungle init");
        String string = this.c.getString("application_id");
        this.m = VunglePub.getInstance();
        this.m.init(this.a.getApplicationContext(), string);
        this.m.setEventListeners(d());
        this.n = this.m.getGlobalAdConfig();
        this.n.setOrientation(Orientation.autoRotate);
        this.n.setBackButtonImmediatelyEnabled(false);
        this.n.setImmersiveMode(true);
    }

    public String getAdnetworkKey() {
        return ADNETWORK_KEY;
    }

    public void resume(Activity activity) {
        this.m.onResume();
    }

    public void pause() {
        this.m.onPause();
    }

    public void destroy() {
        if (this.m != null) {
            this.m.clearEventListeners();
        }
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("com.vungle.publisher.VunglePub") != null;
            if (z) {
                return z;
            }
            this.j.debug_w(Constants.TAG, "6006: sdk not found.");
            return z;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public MovieRewardData getMovieData() {
        return l;
    }
}
