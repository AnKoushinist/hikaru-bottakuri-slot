package com.vungle.publisher;

/* compiled from: vungle */
public final class alb<E> extends alg<E> {
    public alb(int i) {
        super(i);
    }

    public final boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        Object[] objArr = this.c;
        long j = this.producerIndex;
        long a = a(j);
        if (akk.b(objArr, a) != null) {
            return false;
        }
        akk.b(objArr, a, e);
        ali.a.putOrderedLong(this, f, j + 1);
        return true;
    }

    public final E poll() {
        long j = this.consumerIndex;
        long a = a(j);
        Object[] objArr = this.c;
        E b = akk.b(objArr, a);
        if (b == null) {
            return null;
        }
        akk.b(objArr, a, null);
        ali.a.putOrderedLong(this, e, j + 1);
        return b;
    }

    public final E peek() {
        return c(a(this.consumerIndex));
    }

    public final int size() {
        long b = b();
        while (true) {
            long a = a();
            long b2 = b();
            if (b == b2) {
                return (int) (a - b2);
            }
            b = b2;
        }
    }

    public final boolean isEmpty() {
        return a() == b();
    }

    private long a() {
        return ali.a.getLongVolatile(this, f);
    }

    private long b() {
        return ali.a.getLongVolatile(this, e);
    }
}
