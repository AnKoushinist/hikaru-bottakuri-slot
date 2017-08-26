package com.vungle.publisher;

/* compiled from: vungle */
public abstract class akm<E> extends akk<E> {
    private static final long e = ((long) (ali.a.arrayBaseOffset(long[].class) + (32 << (f - a))));
    private static final int f = (a + 3);
    protected final long[] d;

    static {
        if (8 == ali.a.arrayIndexScale(long[].class)) {
            return;
        }
        throw new IllegalStateException("Unexpected long[] element size");
    }

    public akm(int i) {
        super(i);
        int i2 = (int) (this.b + 1);
        this.d = new long[((i2 << a) + 64)];
        for (long j = 0; j < ((long) i2); j++) {
            a(this.d, d(j), j);
        }
    }

    protected final long d(long j) {
        return e + ((this.b & j) << f);
    }

    protected static void a(long[] jArr, long j, long j2) {
        ali.a.putOrderedLong(jArr, j, j2);
    }

    protected static long a(long[] jArr, long j) {
        return ali.a.getLongVolatile(jArr, j);
    }
}
