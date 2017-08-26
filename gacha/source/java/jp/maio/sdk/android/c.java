package jp.maio.sdk.android;

import org.cocos2dx.lib.BuildConfig;

class c implements Runnable {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public void run() {
        if (this.a.a.h == null || this.a.a.g == null) {
            if (this.a.a.r != null) {
                this.a.a.r.onFailed(FailNotificationReason.VIDEO, this.a.a.d.b);
            }
            this.a.a.finish();
            return;
        }
        this.a.a.g.a(this.a.a.h.d());
        this.a.a.g.b();
        ax.a("Waiting here", BuildConfig.FLAVOR, BuildConfig.FLAVOR, null);
    }
}
