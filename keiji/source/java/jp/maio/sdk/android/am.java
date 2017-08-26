package jp.maio.sdk.android;

import org.cocos2dx.lib.BuildConfig;

class am extends MaioAdsListener {
    final /* synthetic */ MaioAds a;

    am(MaioAds maioAds) {
        this.a = maioAds;
    }

    public void onClosedAd(String str) {
        if (this.a.k && this.a.i != null) {
            bg.b.execute(new an(this));
        }
        this.a.f = false;
        bc.a("playing unlocked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
        this.a.f();
    }

    public void onFailed(FailNotificationReason failNotificationReason, String str) {
        aq.a(this.a.i, Integer.parseInt(str));
    }
}
