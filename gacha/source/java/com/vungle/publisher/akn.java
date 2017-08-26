package com.vungle.publisher;

/* compiled from: vungle */
public final class akn<E> extends ako<E> {
    public akn(int i) {
        super(Math.max(2, i));
    }

    public final boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        long j = this.b + 1;
        long[] jArr = this.d;
        long j2 = Long.MAX_VALUE;
        while (true) {
            long j3 = this.producerIndex;
            long d = d(j3);
            long a = akm.a(jArr, d) - j3;
            if (a == 0) {
                if (ali.a.compareAndSwapLong(this, akr.f, j3, j3 + 1)) {
                    a(a(j3), (Object) e);
                    akm.a(jArr, d, 1 + j3);
                    return true;
                }
            } else if (a < 0 && j3 - j <= r10) {
                a = j3 - j;
                j2 = this.consumerIndex;
                if (a <= j2) {
                    return false;
                }
            }
            j2 = j2;
        }
    }

    public final E poll() {
        long[] jArr = this.d;
        long j = -1;
        while (true) {
            long j2 = this.consumerIndex;
            long d = d(j2);
            long a = akm.a(jArr, d) - (1 + j2);
            if (a == 0) {
                if (ali.a.compareAndSwapLong(this, ako.e, j2, j2 + 1)) {
                    long a2 = a(j2);
                    E b = b(a2);
                    a(a2, null);
                    akm.a(jArr, d, (this.b + j2) + 1);
                    return b;
                }
            } else if (a < 0 && j2 >= r8) {
                j = this.producerIndex;
                if (j2 == j) {
                    return null;
                }
            }
            j = j;
        }
    }

    public final E peek() {
        E b;
        long j;
        do {
            j = this.consumerIndex;
            b = b(a(j));
            if (b != null) {
                break;
            }
        } while (j != this.producerIndex);
        return b;
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
