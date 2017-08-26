package jp.maio.sdk.android;

final class by implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;

    by(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public void run() {
        bp.a.onChangedCanShow(this.a, this.b);
    }
}
