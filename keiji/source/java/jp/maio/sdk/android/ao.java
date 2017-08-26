package jp.maio.sdk.android;

import java.util.TimerTask;

class ao extends TimerTask {
    final /* synthetic */ MaioAds a;

    ao(MaioAds maioAds) {
        this.a = maioAds;
    }

    public void run() {
        this.a.f();
    }
}
