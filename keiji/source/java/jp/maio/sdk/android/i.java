package jp.maio.sdk.android;

class i implements MaioAdsListenerInterface {
    final /* synthetic */ AdFullscreenActivity a;

    i(AdFullscreenActivity adFullscreenActivity) {
        this.a = adFullscreenActivity;
    }

    public void onChangedCanShow(String str, boolean z) {
    }

    public void onClickedAd(String str) {
        bp.d(str);
    }

    public void onClosedAd(String str) {
        bp.b(str);
    }

    public void onFailed(FailNotificationReason failNotificationReason, String str) {
        bp.a(FailNotificationReason.VIDEO, str);
    }

    public void onFinishedAd(int i, boolean z, int i2, String str) {
        this.a.g.onFinishedAd(i, z, i2, str);
        if (!z) {
            i = i2;
        }
        if (this.a.m == 1) {
            bp.a(i, z, i2, str);
        }
        this.a.c();
    }

    public void onInitialized() {
    }

    public void onOpenAd(String str) {
        bp.a(str);
    }

    public void onStartedAd(String str) {
        bp.c(str);
    }
}
