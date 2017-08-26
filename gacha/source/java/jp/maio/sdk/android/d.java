package jp.maio.sdk.android;

import java.util.TimerTask;

class d extends TimerTask {
    final /* synthetic */ AdFullscreenActivity a;

    d(AdFullscreenActivity adFullscreenActivity) {
        this.a = adFullscreenActivity;
    }

    public void run() {
        this.a.runOnUiThread(new e(this));
    }
}
