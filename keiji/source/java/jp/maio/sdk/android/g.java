package jp.maio.sdk.android;

class g implements Runnable {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public void run() {
        this.a.a.h.start();
        this.a.a.a();
        if (this.a.a.m == 0) {
            this.a.a.r.onStartedAd(this.a.a.d.b);
        }
        this.a.a.m = this.a.a.m + 1;
    }
}
