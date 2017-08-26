package jp.maio.sdk.android;

final class bu implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ boolean b;
    final /* synthetic */ int c;
    final /* synthetic */ String d;

    bu(int i, boolean z, int i2, String str) {
        this.a = i;
        this.b = z;
        this.c = i2;
        this.d = str;
    }

    public void run() {
        bp.a.onFinishedAd(this.a, this.b, this.c, this.d);
    }
}
