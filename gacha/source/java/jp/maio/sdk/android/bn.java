package jp.maio.sdk.android;

final class bn implements Runnable {
    final /* synthetic */ String a;

    bn(String str) {
        this.a = str;
    }

    public void run() {
        bk.b.onClosedAd(this.a);
        bk.a.onClosedAd(this.a);
    }
}
