package jp.maio.sdk.android;

final class bs implements Runnable {
    final /* synthetic */ String a;

    bs(String str) {
        this.a = str;
    }

    public void run() {
        bp.b.onClosedAd(this.a);
        bp.a.onClosedAd(this.a);
    }
}
