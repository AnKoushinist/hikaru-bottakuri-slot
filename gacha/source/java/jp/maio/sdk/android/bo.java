package jp.maio.sdk.android;

final class bo implements Runnable {
    final /* synthetic */ String a;

    bo(String str) {
        this.a = str;
    }

    public void run() {
        bk.a.onStartedAd(this.a);
    }
}
