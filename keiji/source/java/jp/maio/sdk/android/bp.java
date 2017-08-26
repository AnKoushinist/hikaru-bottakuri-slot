package jp.maio.sdk.android;

import org.cocos2dx.lib.BuildConfig;

class bp {
    public static MaioAdsListenerInterface a = null;
    public static MaioAdsListenerInterface b = null;

    public static void a() {
        bc.a("maioAdsListenerManager#onInitialized", BuildConfig.FLAVOR, "DATA", null);
        if (a != null) {
            bg.a.post(new bq());
        }
    }

    public static void a(int i, boolean z, int i2, String str) {
        bc.a("maioAdsListenerManager#onFinishedAd", "duration=" + i2 + ", playtime=" + i + ", skipped=" + z, "DATA", null);
        if (a != null) {
            bg.a.post(new bu(i, z, i2, str));
        }
    }

    public static void a(String str) {
        bc.a("maioAdsListenerManager#onOpenAd", "zoneEid=" + str, "DATA", null);
        if (a != null) {
            bg.a.post(new br(str));
        }
    }

    public static void a(String str, boolean z) {
        bc.a("maioAdsListenerManager#onChangedCanShow", "zoneEid=" + str + "canShow " + z, "DATA", null);
        if (a != null) {
            bg.a.post(new by(str, z));
        }
    }

    public static void a(FailNotificationReason failNotificationReason, String str) {
        bc.a("maioAdsListenerManager#onFailed", "reason=" + failNotificationReason + ", zoneEid=" + str, "DATA", null);
        if (a != null) {
            bg.a.post(new bw(failNotificationReason, str));
        }
    }

    public static void a(MaioAdsListenerInterface maioAdsListenerInterface, MaioAdsListenerInterface maioAdsListenerInterface2) {
        a = maioAdsListenerInterface;
        b = maioAdsListenerInterface2;
    }

    public static void b(String str) {
        bc.a("maioAdsListenerManager#onClosedAd", "zoneEid=" + str, "DATA", null);
        if (a != null) {
            bg.a.post(new bs(str));
        }
    }

    public static void b(FailNotificationReason failNotificationReason, String str) {
        bc.a("maioAdsListenerManager#onFailed", "reason=" + failNotificationReason + ", zoneEid=" + str, "DATA", null);
        if (b != null) {
            bg.a.post(new bx(failNotificationReason, str));
        }
    }

    public static void c(String str) {
        bc.a("maioAdsListenerManager#onStartedAd", "zoneEid=" + str, "DATA", null);
        if (a != null) {
            bg.a.post(new bt(str));
        }
    }

    public static void d(String str) {
        bc.a("maioAdsListenerManager#onClickedAd", "zoneEid=" + str, "DATA", null);
        if (a != null) {
            bg.a.post(new bv(str));
        }
    }
}
