package com.vungle.publisher;

/* compiled from: vungle */
abstract class alc<E> extends akk<E> {
    private static final Integer e = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    protected final int d;

    public alc(int i) {
        super(i);
        this.d = Math.min(i / 4, e.intValue());
    }
}
