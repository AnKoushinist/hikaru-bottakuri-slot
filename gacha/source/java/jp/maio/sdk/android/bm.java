package jp.maio.sdk.android;

final class bm implements Runnable {
    final /* synthetic */ String a;

    bm(String str) {
        this.a = str;
    }

    public void run() {
        bk.a.onOpenAd(this.a);
    }
}
