package com.vungle.publisher;

import android.content.SharedPreferences;
import android.os.SystemClock;
import com.vungle.log.Logger;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.cocos2dx.lib.GameControllerDelegate;

@Singleton
/* compiled from: vungle */
public final class pp extends qe {
    @Inject
    bt a;
    @Inject
    a b;
    @Inject
    public SharedPreferences c;
    public final AtomicBoolean d = new AtomicBoolean();
    public long e;

    @Singleton
    /* compiled from: vungle */
    static class a implements Runnable {
        @Inject
        ql a;

        @Inject
        a() {
        }

        public final void run() {
            this.a.a(new au());
        }
    }

    @Inject
    pp() {
    }

    public final boolean a() {
        boolean z = true;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long b = b();
        int elapsedRealtime2 = (int) ((SystemClock.elapsedRealtime() - b()) / 1000);
        if (elapsedRealtime2 < 0) {
            Logger.d(Logger.AD_TAG, "negative adDelayElapsedSeconds " + elapsedRealtime2 + ", currentTimestampMillis " + elapsedRealtime + ", lastAdEndMillis " + b);
        } else {
            int c = c();
            if (elapsedRealtime2 < c) {
                z = false;
            }
            if (z) {
                Logger.v(Logger.AD_TAG, elapsedRealtime2 + " / " + c + " ad delay seconds elapsed");
            } else {
                Logger.d(Logger.AD_TAG, elapsedRealtime2 + " / " + c + " ad delay seconds elapsed");
            }
        }
        return z;
    }

    public final long b() {
        long j = this.c.getLong("VgLastViewedTime", 0);
        Logger.v(Logger.AD_TAG, "returning last ad end millis: " + j);
        return j;
    }

    public final int c() {
        return this.c.getInt("VgAdDelayDuration", 0);
    }

    public final void a(boolean z) {
        if (this.d.compareAndSet(true, false)) {
            Logger.d(Logger.AD_TAG, "ending playing ad. isNormalAdEnd? " + z);
            unregister();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Logger.v(Logger.AD_TAG, "setting last ad end millis: " + elapsedRealtime);
            this.c.edit().putLong("VgLastViewedTime", elapsedRealtime).apply();
            this.e = 0;
            int c = c();
            if (c > 0) {
                this.eventBus.a(new at());
                this.a.a(this.b, (long) (c * GameControllerDelegate.THUMBSTICK_LEFT_X));
            }
            if (!z) {
                this.eventBus.a(new bn(this.e));
            }
        }
    }

    public final void onEvent(be beVar) {
        Logger.d(Logger.AD_TAG, "InterstitialAdState received end ad event");
        a(true);
    }
}
