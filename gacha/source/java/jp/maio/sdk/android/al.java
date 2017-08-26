package jp.maio.sdk.android;

import java.util.TimerTask;

class al extends TimerTask {
    final /* synthetic */ MaioAds a;

    al(MaioAds maioAds) {
        this.a = maioAds;
    }

    public void run() {
        this.a.g();
    }
}
