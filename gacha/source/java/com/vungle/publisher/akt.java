package com.vungle.publisher;

/* compiled from: vungle */
public final class akt<E> extends akx<E> {
    public akt(int i) {
        super(i);
    }

    public final boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        Object[] objArr = this.c;
        long j = this.b;
        long j2 = this.producerIndex;
        long a = a(j2);
        if (akk.b(objArr, a) == null) {
            akk.a(objArr, a, e);
            d(1 + j2);
        } else if (j2 - this.consumerIndex > j) {
            return false;
        } else {
            do {
            } while (akk.b(objArr, a) != null);
        }
        akk.a(objArr, a, e);
        d(1 + j2);
        return true;
    }

    public final E poll() {
        long j;
        long j2 = this.f;
        do {
            j = this.consumerIndex;
            if (j >= j2) {
                long j3 = this.producerIndex;
                if (j >= j3) {
                    return null;
                }
                this.f = j3;
            }
        } while (!a(j, 1 + j));
        j2 = a(j);
        Object[] objArr = this.c;
        E a = akk.a(objArr, j2);
        akk.b(objArr, j2, null);
        return a;
    }

    public final E peek() {
        E c;
        long j = this.f;
        do {
            long j2 = this.consumerIndex;
            if (j2 >= j) {
                long j3 = this.producerIndex;
                if (j2 >= j3) {
                    return null;
                }
                this.f = j3;
            }
            c = c(a(j2));
        } while (c == null);
        return c;
    }

    public final int size() {
        long j = this.consumerIndex;
        while (true) {
            long j2 = this.producerIndex;
            long j3 = this.consumerIndex;
            if (j == j3) {
                return (int) (j2 - j3);
            }
            j = j3;
        }
    }

    public final boolean isEmpty() {
        return this.consumerIndex == this.producerIndex;
    }
}
