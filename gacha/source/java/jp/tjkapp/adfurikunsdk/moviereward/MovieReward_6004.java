package jp.tjkapp.adfurikunsdk.moviereward;

import android.text.TextUtils;
import jp.maio.sdk.android.FailNotificationReason;
import jp.maio.sdk.android.MaioAds;
import jp.maio.sdk.android.MaioAdsListener;

class MovieReward_6004 extends AdnetworkWorker {
    public static final String ADNETWORK_KEY = "6004";
    public static final String ADNETWORK_NAME = "Maio";
    private static final MovieRewardData l = new MovieRewardData(ADNETWORK_KEY, ADNETWORK_NAME);
    private String m;
    private MaioAdsListener n;

    MovieReward_6004() {
    }

    private MaioAdsListener d() {
        if (this.n == null) {
            this.n = new MaioAdsListener(this) {
                final /* synthetic */ MovieReward_6004 a;

                {
                    this.a = r1;
                }

                public void onInitialized() {
                    this.a.j.debug(Constants.TAG, "6004: MaioAdsListener.onInitialized");
                }

                public void onChangedCanShow(String str, boolean z) {
                    this.a.j.debug(Constants.TAG, "6004: MaioAdsListener.onChangedCanShow: zoneEid:" + str + ", value:" + z);
                }

                public void onOpenAd(String str) {
                    this.a.j.debug(Constants.TAG, "6004: MaioAdsListener.onOpenAd: zoneEid:" + str);
                }

                public void onStartedAd(String str) {
                    this.a.j.debug(Constants.TAG, "6004: MaioAdsListener.onStartedAd: zoneEid:" + str);
                    this.a.a(MovieReward_6004.l);
                    this.a.b();
                }

                public void onFinishedAd(int i, boolean z, int i2, String str) {
                    this.a.j.debug(Constants.TAG, "6004: MaioAdsListener.onFinishedAd");
                    if (z) {
                        this.a.b(MovieReward_6004.l);
                        return;
                    }
                    this.a.a();
                    this.a.c(MovieReward_6004.l);
                }

                public void onFailed(FailNotificationReason failNotificationReason, String str) {
                    this.a.j.debug(Constants.TAG, "6004: MaioAdsListener.onFailed: reason:" + failNotificationReason.name() + " ,zoneEid:" + str);
                    this.a.b(MovieReward_6004.l);
                    this.a.a(this.a, MovieReward_6004.l);
                }

                public void onClickedAd(String str) {
                    this.a.j.debug(Constants.TAG, "6004: MaioAdsListener.onClickedAd: zoneEid:" + str);
                }

                public void onClosedAd(String str) {
                    this.a.j.debug(Constants.TAG, "6004: MaioAdsListener.onClosedAd: zoneEid:" + str);
                    this.a.d(MovieReward_6004.l);
                    this.a.a(this.a, MovieReward_6004.l);
                }
            };
        }
        return this.n;
    }

    public boolean isPrepared() {
        boolean canShow = MaioAds.canShow(this.m);
        this.j.debug(Constants.TAG, "6004: try isPrepared: " + canShow);
        return canShow;
    }

    public void play(MovieMediater movieMediater) {
        this.j.debug(Constants.TAG, "6004: play");
        if (isPrepared()) {
            MaioAds.setMaioAdsListener(d());
            MaioAds.show(this.m);
        }
    }

    public boolean isProvideTestMode() {
        return true;
    }

    protected void initWorker() {
        this.j.debug(Constants.TAG, "6004: maio init");
        MaioAds.setAdTestMode(this.g);
        String string = this.c.getString("media_id");
        this.m = this.c.getString("spot_id");
        if (this.m != null && TextUtils.isEmpty(this.m.trim())) {
            this.m = null;
        }
        MaioAds.init(this.a, string, d());
        MaioAds.setMaioAdsListener(d());
    }

    public String getAdnetworkKey() {
        return ADNETWORK_KEY;
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("jp.maio.sdk.android.MaioAds") != null;
            if (z) {
                return z;
            }
            this.j.debug_w(Constants.TAG, "6004: sdk not found.");
            return z;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public MovieRewardData getMovieData() {
        return l;
    }
}
