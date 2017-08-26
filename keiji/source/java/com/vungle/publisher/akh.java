package com.vungle.publisher;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* compiled from: vungle */
public final class akh<E> extends akg<E> {
    private static final Integer g = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    final AtomicLong c = new AtomicLong();
    long d;
    final AtomicLong e = new AtomicLong();
    final int f;

    public final /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public final /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    public akh(int i) {
        super(i);
        this.f = Math.min(i / 4, g.intValue());
    }

    public final boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        AtomicReferenceArray atomicReferenceArray = this.a;
        int i = this.b;
        long j = this.c.get();
        int i2 = ((int) j) & i;
        if (j >= this.d) {
            int i3 = this.f;
            if (atomicReferenceArray.get(i & ((int) (((long) i3) + j))) == null) {
                this.d = ((long) i3) + j;
            } else if (atomicReferenceArray.get(i2) != null) {
                return false;
            }
        }
        atomicReferenceArray.lazySet(i2, e);
        this.c.lazySet(1 + j);
        return true;
    }

    public final E poll() {
        long j = this.e.get();
        int a = a(j);
        AtomicReferenceArray atomicReferenceArray = this.a;
        E e = atomicReferenceArray.get(a);
        if (e == null) {
            return null;
        }
        atomicReferenceArray.lazySet(a, null);
        this.e.lazySet(j + 1);
        return e;
    }

    public final E peek() {
        return a(a(this.e.get()));
    }

    public final int size() {
        long j = this.e.get();
        while (true) {
            long j2 = this.c.get();
            long j3 = this.e.get();
            if (j == j3) {
                return (int) (j2 - j3);
            }
            j = j3;
        }
    }

    public final boolean isEmpty() {
        return this.c.get() == this.e.get();
    }
}
