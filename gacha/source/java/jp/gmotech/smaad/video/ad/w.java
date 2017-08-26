package jp.gmotech.smaad.video.ad;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class w implements Runnable {
    final /* synthetic */ v a;

    w(v vVar) {
        this.a = vVar;
    }

    public void run() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        while (true) {
            newSingleThreadExecutor.execute(new x(this.a));
            try {
                Thread.sleep(240000);
            } catch (Exception e) {
            }
        }
    }
}
