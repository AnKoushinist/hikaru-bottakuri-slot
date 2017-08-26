package jp.maio.sdk.android;

final class bt implements Runnable {
    final /* synthetic */ String a;

    bt(String str) {
        this.a = str;
    }

    public void run() {
        bp.a.onStartedAd(this.a);
    }
}
