package com.vungle.publisher;

import java.util.Iterator;

/* compiled from: vungle */
public abstract class akk<E> extends akl<E> {
    protected static final int a = Integer.getInteger("sparse.shift", 0).intValue();
    private static final long d = ((long) (ali.a.arrayBaseOffset(Object[].class) + (32 << (e - a))));
    private static final int e;
    protected final long b;
    protected final E[] c;

    static {
        int arrayIndexScale = ali.a.arrayIndexScale(Object[].class);
        if (4 == arrayIndexScale) {
            e = a + 2;
        } else if (8 == arrayIndexScale) {
            e = a + 3;
        } else {
            throw new IllegalStateException("Unknown pointer size");
        }
    }

    public akk(int i) {
        int a = aks.a(i);
        this.b = (long) (a - 1);
        this.c = new Object[((a << a) + 64)];
    }

    protected final long a(long j) {
        long j2 = this.b;
        return ((j2 & j) << e) + d;
    }

    protected final void a(long j, E e) {
        a(this.c, j, e);
    }

    protected static void a(E[] eArr, long j, E e) {
        ali.a.putObject(eArr, j, e);
    }

    protected static void b(E[] eArr, long j, E e) {
        ali.a.putOrderedObject(eArr, j, e);
    }

    protected final E b(long j) {
        return a(this.c, j);
    }

    protected static E a(E[] eArr, long j) {
        return ali.a.getObject(eArr, j);
    }

    protected final E c(long j) {
        return b(this.c, j);
    }

    protected static E b(E[] eArr, long j) {
        return ali.a.getObjectVolatile(eArr, j);
    }

    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }
}
