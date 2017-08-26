package jp.maio.sdk.android;

import java.util.TimerTask;

class ak extends TimerTask {
    final /* synthetic */ MaioAds a;

    ak(MaioAds maioAds) {
        this.a = maioAds;
    }

    public void run() {
        this.a.f();
    }
}
