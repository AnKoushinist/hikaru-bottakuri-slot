package com.vungle.publisher;

import com.vungle.publisher.ahs.a;

/* compiled from: vungle */
final class ajr implements aie {
    private final aie a;
    private final a b;
    private final long c;

    public ajr(aie com_vungle_publisher_aie, a aVar, long j) {
        this.a = com_vungle_publisher_aie;
        this.b = aVar;
        this.c = j;
    }

    public final void d() {
        if (!this.b.c()) {
            long currentTimeMillis = this.c - System.currentTimeMillis();
            if (currentTimeMillis > 0) {
                try {
                    Thread.sleep(currentTimeMillis);
                } catch (Throwable e) {
                    Thread.currentThread().interrupt();
                    ahx.a(e);
                }
            }
            if (!this.b.c()) {
                this.a.d();
            }
        }
    }
}
