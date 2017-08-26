package jp.tjkapp.adfurikunsdk.moviereward;

import com.tapjoy.TJActionRequest;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJError;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJVideoListener;
import com.tapjoy.Tapjoy;

class MovieReward_6005 extends AdnetworkWorker {
    public static final String ADNETWORK_KEY = "6005";
    public static final String ADNETWORK_NAME = "Tapjoy";
    private static final MovieRewardData l = new MovieRewardData(ADNETWORK_KEY, ADNETWORK_NAME);
    private String m;
    private TJPlacement n;
    private TJVideoListener o;
    private TJConnectListener p;
    private TJPlacementListener q;

    MovieReward_6005() {
    }

    private TJVideoListener d() {
        if (this.o == null) {
            this.o = new TJVideoListener(this) {
                final /* synthetic */ MovieReward_6005 a;

                {
                    this.a = r1;
                }

                public void onVideoStart() {
                    this.a.j.debug(Constants.TAG, "6005: Tapjoy video start!");
                    this.a.a(MovieReward_6005.l);
                }

                public void onVideoError(int i) {
                    this.a.j.debug(Constants.TAG, "6005: Tapjoy video error!, Code:" + i);
                    this.a.b(MovieReward_6005.l);
                }

                public void onVideoComplete() {
                    this.a.j.debug(Constants.TAG, "6005: Tapjoy video complete!");
                    this.a.a();
                    this.a.c(MovieReward_6005.l);
                }
            };
        }
        return this.o;
    }

    private TJConnectListener e() {
        if (this.p == null) {
            this.p = new TJConnectListener(this) {
                final /* synthetic */ MovieReward_6005 a;

                {
                    this.a = r1;
                }

                public void onConnectSuccess() {
                    this.a.j.detail(Constants.TAG, "6005: Tapjoy connect Succeeded");
                    this.a.n = new TJPlacement(this.a.a, this.a.m, this.a.f());
                    this.a.n.setMediationName("adfully");
                    this.a.n.setAdapterVersion("1.0.0");
                    if (Tapjoy.isConnected()) {
                        this.a.n.requestContent();
                        this.a.j.detail(Constants.TAG, "6005: Tapjoy Request Content");
                    }
                }

                public void onConnectFailure() {
                    this.a.j.detail(Constants.TAG, "6005: Tapjoy connect Failed");
                }
            };
        }
        return this.p;
    }

    private TJPlacementListener f() {
        if (this.q == null) {
            this.q = new TJPlacementListener(this) {
                final /* synthetic */ MovieReward_6005 a;

                {
                    this.a = r1;
                }

                public void onRequestSuccess(TJPlacement tJPlacement) {
                    this.a.j.detail(Constants.TAG, "6005: Tapjoy Request Succeeded");
                }

                public void onRequestFailure(TJPlacement tJPlacement, TJError tJError) {
                    this.a.j.detail(Constants.TAG, "6005: Tapjoy Request Failure: " + tJError.message);
                }

                public void onContentReady(TJPlacement tJPlacement) {
                    this.a.j.detail(Constants.TAG, "6005: Tapjoy Content Ready");
                }

                public void onContentShow(TJPlacement tJPlacement) {
                    this.a.j.detail(Constants.TAG, "6005: Tapjoy Content Show");
                    this.a.b();
                }

                public void onContentDismiss(TJPlacement tJPlacement) {
                    this.a.j.detail(Constants.TAG, "6005: Tapjoy Content Dismiss");
                    this.a.d(MovieReward_6005.l);
                    this.a.a(this.a, MovieReward_6005.l);
                }

                public void onPurchaseRequest(TJPlacement tJPlacement, TJActionRequest tJActionRequest, String str) {
                    this.a.j.detail(Constants.TAG, "6005: Tapjoy Purchase Request");
                }

                public void onRewardRequest(TJPlacement tJPlacement, TJActionRequest tJActionRequest, String str, int i) {
                    this.a.j.detail(Constants.TAG, "6005: Tapjoy Reward Request");
                }
            };
        }
        return this.q;
    }

    public void preload() {
        if (this.n != null && !isPrepared() && Tapjoy.isConnected()) {
            this.n.requestContent();
        }
    }

    public boolean isPrepared() {
        boolean z = this.n != null && this.n.isContentAvailable() && this.n.isContentReady();
        this.j.debug(Constants.TAG, "6005: try isPrepared: " + z);
        return z;
    }

    public void play(MovieMediater movieMediater) {
        this.j.debug(Constants.TAG, "6005: play");
        if (isPrepared()) {
            this.n.showContent();
        }
    }

    public boolean isProvideTestMode() {
        return false;
    }

    public void initWorker() {
        this.j.debug(Constants.TAG, "6005: Tapjoy init");
        this.m = this.c.getString("placement_id");
        Tapjoy.connect(this.a.getApplicationContext(), this.c.getString("sdk_key"), null, e());
        Tapjoy.setVideoListener(d());
    }

    public String getAdnetworkKey() {
        return ADNETWORK_KEY;
    }

    public void start() {
        Tapjoy.onActivityStart(this.a);
    }

    public void stop() {
        Tapjoy.onActivityStop(this.a);
    }

    public void destroy() {
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("com.tapjoy.Tapjoy") != null;
            if (z) {
                return z;
            }
            this.j.debug_w(Constants.TAG, "6005: sdk not found.");
            return z;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public MovieRewardData getMovieData() {
        return l;
    }
}
