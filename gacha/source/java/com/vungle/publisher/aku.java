package com.vungle.publisher;

/* compiled from: vungle */
abstract class aku<E> extends akw<E> {
    protected static final long d = ali.a(aku.class, "consumerIndex");
    volatile long consumerIndex;

    public aku(int i) {
        super(i);
    }

    protected final boolean a(long j, long j2) {
        return ali.a.compareAndSwapLong(this, d, j, j2);
    }
}
