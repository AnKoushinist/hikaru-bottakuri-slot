package jp.maio.sdk.android;

import org.cocos2dx.lib.BuildConfig;

final class ar implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;

    ar(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public void run() {
        try {
            bc.a("MaioAdsDownloading media locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
            aq.a = bk.a(this.a, this.b);
            bc.a("MaioAdsDownloaded media locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
            if (aq.a.d == "null") {
                aq.a.d = String.valueOf(this.b);
            }
            bc.a("MaioAdsChecking Ad_deliver_Test locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
            if (aq.h(aq.a)) {
                throw new bd(FailNotificationReason.AD_STOCK_OUT);
            }
            av.a(aq.a.b.d, aq.a.b.f);
            bc.a("MaioAdsInstanciating viewlogmanager locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
        } catch (Throwable e) {
            bc.a("MaioAds#init error", BuildConfig.FLAVOR, BuildConfig.FLAVOR, e);
            bp.a(e.a, null);
            aq.a = null;
        }
    }
}
