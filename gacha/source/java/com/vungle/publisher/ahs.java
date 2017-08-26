package com.vungle.publisher;

import java.util.concurrent.TimeUnit;

/* compiled from: vungle */
public abstract class ahs {
    static final long a = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    /* compiled from: vungle */
    public static abstract class a implements ahv {
        public abstract ahv a(aie com_vungle_publisher_aie);

        public abstract ahv a(aie com_vungle_publisher_aie, long j, TimeUnit timeUnit);
    }

    public abstract a a();

    public static long b() {
        return System.currentTimeMillis();
    }
}
