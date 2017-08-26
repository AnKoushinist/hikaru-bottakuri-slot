package com.vungle.publisher;

import com.vungle.log.Logger;

/* compiled from: vungle */
final class ahb implements Runnable {
    final ahh a = new ahh();
    volatile boolean b;
    private final ahc c;

    ahb(ahc com_vungle_publisher_ahc) {
        this.c = com_vungle_publisher_ahc;
    }

    public final void run() {
        while (true) {
            try {
                ahg b = this.a.b();
                if (b == null) {
                    synchronized (this) {
                        b = this.a.a();
                        if (b == null) {
                            this.b = false;
                            this.b = false;
                            return;
                        }
                    }
                }
                this.c.a(b);
            } catch (Throwable e) {
                Logger.w("Event", Thread.currentThread().getName() + " was interruppted", e);
                this.b = false;
                return;
            } catch (Throwable th) {
                this.b = false;
            }
        }
    }
}
