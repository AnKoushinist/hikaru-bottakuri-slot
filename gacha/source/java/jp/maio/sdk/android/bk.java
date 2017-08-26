package jp.maio.sdk.android;

import org.cocos2dx.lib.BuildConfig;

class bk {
    public static MaioAdsListenerInterface a = null;
    public static MaioAdsListenerInterface b = null;

    public static void a() {
        ax.a("maioAdsListenerManager#onInitialized", BuildConfig.FLAVOR, "DATA", null);
        if (a != null) {
            bb.a.post(new bl());
        }
    }

    public static void a(int i, boolean z, int i2, String str) {
        ax.a("maioAdsListenerManager#onFinishedAd", "duration=" + i2 + ", playtime=" + i + ", skipped=" + z, "DATA", null);
        if (a != null) {
            bb.a.post(new bp(i, z, i2, str));
        }
    }

    public static void a(String str) {
        ax.a("maioAdsListenerManager#onOpenAd", "zoneEid=" + str, "DATA", null);
        if (a != null) {
            bb.a.post(new bm(str));
        }
    }

    public static void a(String str, boolean z) {
        ax.a("maioAdsListenerManager#onChangedCanShow", "zoneEid=" + str + "canShow " + z, "DATA", null);
        if (a != null) {
            bb.a.post(new bt(str, z));
        }
    }

    public static void a(FailNotificationReason failNotificationReason, String str) {
        ax.a("maioAdsListenerManager#onFailed", "reason=" + failNotificationReason + ", zoneEid=" + str, "DATA", null);
        if (a != null) {
            bb.a.post(new br(failNotificationReason, str));
        }
    }

    public static void a(MaioAdsListenerInterface maioAdsListenerInterface, MaioAdsListenerInterface maioAdsListenerInterface2) {
        a = maioAdsListenerInterface;
        b = maioAdsListenerInterface2;
    }

    public static void b(String str) {
        ax.a("maioAdsListenerManager#onClosedAd", "zoneEid=" + str, "DATA", null);
        if (a != null) {
            bb.a.post(new bn(str));
        }
    }

    public static void b(FailNotificationReason failNotificationReason, String str) {
        ax.a("maioAdsListenerManager#onFailed", "reason=" + failNotificationReason + ", zoneEid=" + str, "DATA", null);
        if (b != null) {
            bb.a.post(new bs(failNotificationReason, str));
        }
    }

    public static void c(String str) {
        ax.a("maioAdsListenerManager#onStartedAd", "zoneEid=" + str, "DATA", null);
        if (a != null) {
            bb.a.post(new bo(str));
        }
    }

    public static void d(String str) {
        ax.a("maioAdsListenerManager#onClickedAd", "zoneEid=" + str, "DATA", null);
        if (a != null) {
            bb.a.post(new bq(str));
        }
    }
}
