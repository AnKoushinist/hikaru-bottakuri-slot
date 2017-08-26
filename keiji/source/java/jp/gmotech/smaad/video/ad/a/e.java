package jp.gmotech.smaad.video.ad.a;

import jp.gmotech.smaad.video.ad.b.a;

class e implements Runnable {
    final /* synthetic */ i a;
    final /* synthetic */ d b;

    e(d dVar, i iVar) {
        this.b = dVar;
        this.a = iVar;
    }

    public void run() {
        if (this.a.a()) {
            a.c("RequestHandler", "requestRunnable canceled");
            this.b.a(2, this.a.b(), null);
            a.b("RequestHandler", "requestRunnable done");
            return;
        }
        this.a.c().e();
        this.b.a(2, this.a.b(), null);
        if (this.a.a()) {
            a.c("RequestHandler", "requestRunnable canceled");
            this.a.e();
            return;
        }
        this.a.d();
    }
}
