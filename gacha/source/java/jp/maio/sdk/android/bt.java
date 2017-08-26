package jp.maio.sdk.android;

final class bt implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;

    bt(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public void run() {
        bk.a.onChangedCanShow(this.a, this.b);
    }
}
