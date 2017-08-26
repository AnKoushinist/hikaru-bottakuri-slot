package jp.maio.sdk.android;

import android.os.Handler;
import android.os.Looper;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class bg {
    public static final Handler a = new Handler(Looper.getMainLooper());
    public static final ExecutorService b = Executors.newCachedThreadPool();
    public static final ExecutorService c = Executors.newFixedThreadPool(10);
    private static final String d = bg.class.getSimpleName();
    private static final Timer e = new Timer();
    private static final TimerTask f = new bh();

    bg() {
    }
}
