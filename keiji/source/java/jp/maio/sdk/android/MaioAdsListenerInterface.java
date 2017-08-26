package jp.maio.sdk.android;

public interface MaioAdsListenerInterface {
    void onChangedCanShow(String str, boolean z);

    void onClickedAd(String str);

    void onClosedAd(String str);

    void onFailed(FailNotificationReason failNotificationReason, String str);

    void onFinishedAd(int i, boolean z, int i2, String str);

    void onInitialized();

    void onOpenAd(String str);

    void onStartedAd(String str);
}
