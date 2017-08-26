package com.tapjoy.internal;

public final class fi {
    public static final fi a = new fi(0, 0, 0, 0.0d);
    public final long b;
    public final long c;
    public final double d;
    public long e;
    private final long f;

    public fi(long j, long j2, long j3, double d) {
        this.f = j;
        this.b = j2;
        this.c = j3;
        this.d = d;
        this.e = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        fi fiVar = (fi) obj;
        if (this.f == fiVar.f && this.b == fiVar.b && this.c == fiVar.c && this.d == fiVar.d && this.e == fiVar.e) {
            return true;
        }
        return false;
    }
}
