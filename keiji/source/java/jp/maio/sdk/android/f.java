package jp.maio.sdk.android;

import android.net.Uri;
import org.cocos2dx.lib.BuildConfig;

class f implements aj {
    final /* synthetic */ AdFullscreenActivity a;

    f(AdFullscreenActivity adFullscreenActivity) {
        this.a = adFullscreenActivity;
    }

    public void a() {
        bc.a("IAdController#startVideo", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
        try {
            this.a.g.a();
            this.a.runOnUiThread(new g(this));
        } catch (Throwable e) {
            bc.a("VideoView#onPrepared interrupted", BuildConfig.FLAVOR, e);
            if (this.a.r != null) {
                this.a.r.onFailed(FailNotificationReason.VIDEO, this.a.d.b);
            }
            this.a.finish();
        }
    }

    public void a(String str) {
        bc.a("IAdController#openClickUrl", "clickUrl=" + str, BuildConfig.FLAVOR, null);
        w.a(this.a.getBaseContext(), Uri.parse(str), 268435456);
    }

    public void b() {
        bc.a("IAdController#startVideo", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
        a();
    }

    public void c() {
        bc.a("IAdController#pauseVideo", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
        this.a.runOnUiThread(new h(this));
    }

    public void d() {
        bc.a("IAdController#closeAd", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
        this.a.c();
        this.a.finish();
    }
}
