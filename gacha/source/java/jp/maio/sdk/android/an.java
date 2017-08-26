package jp.maio.sdk.android;

import org.cocos2dx.lib.BuildConfig;

final class an implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;

    an(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public void run() {
        try {
            ax.a("MaioAdsDownloading media locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
            am.a = bf.a(this.a, this.b);
            ax.a("MaioAdsDownloaded media locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
            if (am.a.d == "null") {
                am.a.d = String.valueOf(this.b);
            }
            ax.a("MaioAdsChecking Ad_deliver_Test locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
            if (am.e(am.a)) {
                throw new ay(FailNotificationReason.AD_STOCK_OUT);
            }
            ar.a(am.a.b.c, am.a.b.e);
            ax.a("MaioAdsInstanciating viewlogmanager locked", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
        } catch (Throwable e) {
            ax.a("MaioAds#init error", BuildConfig.FLAVOR, BuildConfig.FLAVOR, e);
            bk.a(e.a, null);
            am.a = null;
        }
    }
}
