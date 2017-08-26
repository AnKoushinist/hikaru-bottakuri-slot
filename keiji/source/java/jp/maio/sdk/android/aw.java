package jp.maio.sdk.android;

import java.util.TimerTask;

class aw extends TimerTask {
    final /* synthetic */ String a;
    final /* synthetic */ av b;

    aw(av avVar, String str) {
        this.b = avVar;
        this.a = str;
    }

    public synchronized void run() {
        bg.b.execute(new ax(this));
    }
}
