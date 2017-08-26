package com.vungle.publisher;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* compiled from: vungle */
final class ahe extends Handler {
    final ahh a = new ahh();
    boolean b;
    private final int c = 10;
    private final ahc d;

    ahe(ahc com_vungle_publisher_ahc, Looper looper) {
        super(looper);
        this.d = com_vungle_publisher_ahc;
    }

    public final void handleMessage(Message message) {
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            do {
                ahg a = this.a.a();
                if (a == null) {
                    synchronized (this) {
                        a = this.a.a();
                        if (a == null) {
                            this.b = false;
                            this.b = false;
                            return;
                        }
                    }
                }
                this.d.a(a);
            } while (SystemClock.uptimeMillis() - uptimeMillis < ((long) this.c));
            if (sendMessage(obtainMessage())) {
                this.b = true;
                return;
            }
            throw new ahd("Could not send handler message");
        } catch (Throwable th) {
            this.b = false;
        }
    }
}
