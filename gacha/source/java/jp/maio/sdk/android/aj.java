package jp.maio.sdk.android;

import org.cocos2dx.lib.BuildConfig;

class aj extends MaioAdsListener {
    final /* synthetic */ MaioAds a;

    aj(MaioAds maioAds) {
        this.a = maioAds;
    }

    public void onClosedAd(String str) {
        if (this.a.k && this.a.i != null) {
            am.a(this.a.i);
        }
        this.a.f = false;
        ax.a("playing unlocked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
        this.a.f();
    }

    public void onFailed(FailNotificationReason failNotificationReason, String str) {
        am.a(this.a.i, Integer.parseInt(str));
    }
}
