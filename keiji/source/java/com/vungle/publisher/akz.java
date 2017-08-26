package com.vungle.publisher;

/* compiled from: vungle */
abstract class akz<E> extends akv<E> {
    protected static final long e = ali.a(akz.class, "producerIndex");
    volatile long producerIndex;

    protected final void d(long j) {
        ali.a.putOrderedLong(this, e, j);
    }

    public akz(int i) {
        super(i);
    }
}
