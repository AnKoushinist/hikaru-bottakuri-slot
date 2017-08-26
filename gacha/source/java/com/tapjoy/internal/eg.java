package com.tapjoy.internal;

import android.os.SystemClock;

public final class eg {
    public final long a;
    public long b;

    public eg(long j) {
        this.a = j;
        this.b = SystemClock.elapsedRealtime();
    }

    public eg() {
        this.a = 3600000;
        this.b = SystemClock.elapsedRealtime() - 3600000;
    }

    public final boolean a() {
        return SystemClock.elapsedRealtime() - this.b > this.a;
    }
}
