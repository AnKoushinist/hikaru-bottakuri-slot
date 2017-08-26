package com.vungle.publisher;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* compiled from: vungle */
public final class akj<T> implements Queue<T> {
    static final int a = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    private static final Object j = new Object();
    final AtomicLong b = new AtomicLong();
    int c;
    long d;
    int e;
    AtomicReferenceArray<Object> f;
    int g;
    AtomicReferenceArray<Object> h;
    final AtomicLong i = new AtomicLong();

    public akj(int i) {
        int a = aks.a(Math.max(8, i));
        int i2 = a - 1;
        AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(a + 1);
        this.f = atomicReferenceArray;
        this.e = i2;
        this.c = Math.min(a / 4, a);
        this.h = atomicReferenceArray;
        this.g = i2;
        this.d = (long) (i2 - 1);
        a(0);
    }

    public final boolean offer(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        AtomicReferenceArray atomicReferenceArray = this.f;
        long j = this.b.get();
        int i = this.e;
        int i2 = ((int) j) & i;
        if (j < this.d) {
            return a(atomicReferenceArray, t, j, i2);
        }
        int i3 = this.c;
        if (atomicReferenceArray.get(((int) (((long) i3) + j)) & i) == null) {
            this.d = (((long) i3) + j) - 1;
            return a(atomicReferenceArray, t, j, i2);
        } else if (atomicReferenceArray.get(((int) (j + 1)) & i) != null) {
            return a(atomicReferenceArray, t, j, i2);
        } else {
            long j2 = (long) i;
            AtomicReferenceArray atomicReferenceArray2 = new AtomicReferenceArray(atomicReferenceArray.length());
            this.f = atomicReferenceArray2;
            this.d = (j2 + j) - 1;
            a(j + 1);
            atomicReferenceArray2.lazySet(i2, t);
            atomicReferenceArray.lazySet(atomicReferenceArray.length() - 1, atomicReferenceArray2);
            atomicReferenceArray.lazySet(i2, j);
            return true;
        }
    }

    private boolean a(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j, int i) {
        a(1 + j);
        atomicReferenceArray.lazySet(i, t);
        return true;
    }

    private static AtomicReferenceArray<Object> a(AtomicReferenceArray<Object> atomicReferenceArray) {
        return (AtomicReferenceArray) atomicReferenceArray.get(atomicReferenceArray.length() - 1);
    }

    public final T poll() {
        AtomicReferenceArray atomicReferenceArray = this.h;
        long j = this.i.get();
        int i = ((int) j) & this.g;
        T t = atomicReferenceArray.get(i);
        Object obj = t == j ? 1 : null;
        if (t != null && obj == null) {
            b(j + 1);
            atomicReferenceArray.lazySet(i, null);
            return t;
        } else if (obj == null) {
            return null;
        } else {
            AtomicReferenceArray a = a(atomicReferenceArray);
            this.h = a;
            t = a.get(i);
            if (t == null) {
                return null;
            }
            b(j + 1);
            a.lazySet(i, null);
            return t;
        }
    }

    public final T peek() {
        AtomicReferenceArray atomicReferenceArray = this.h;
        long j = this.i.get();
        int i = this.g;
        T t = atomicReferenceArray.get(((int) j) & i);
        if (t != j) {
            return t;
        }
        AtomicReferenceArray a = a(atomicReferenceArray);
        this.h = a;
        return a.get(((int) j) & i);
    }

    public final void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    public final int size() {
        long j = this.i.get();
        while (true) {
            long j2 = this.b.get();
            long j3 = this.i.get();
            if (j == j3) {
                return (int) (j2 - j3);
            }
            j = j3;
        }
    }

    public final boolean isEmpty() {
        return this.b.get() == this.i.get();
    }

    private void a(long j) {
        this.b.lazySet(j);
    }

    private void b(long j) {
        this.i.lazySet(j);
    }

    public final Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public final boolean contains(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    public final <E> E[] toArray(E[] eArr) {
        throw new UnsupportedOperationException();
    }

    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    public final T remove() {
        throw new UnsupportedOperationException();
    }

    public final T element() {
        throw new UnsupportedOperationException();
    }
}
