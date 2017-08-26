package com.vungle.publisher;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* compiled from: vungle */
public final class aki<T> extends AtomicReferenceArray<T> implements Queue<T> {
    final int a;
    final int b;
    final AtomicLong c = new AtomicLong();
    final AtomicLong d = new AtomicLong();

    public aki(int i) {
        super(aks.a(i));
        int length = length();
        this.a = length - 1;
        this.b = length - i;
    }

    public final boolean offer(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        long j = this.c.get();
        int i = this.a;
        if (get(((int) (((long) this.b) + j)) & i) != null) {
            return false;
        }
        i &= (int) j;
        this.c.lazySet(j + 1);
        lazySet(i, t);
        return true;
    }

    public final T poll() {
        long j = this.d.get();
        int i = this.a & ((int) j);
        T t = get(i);
        if (t == null) {
            return null;
        }
        this.d.lazySet(j + 1);
        lazySet(i, null);
        return t;
    }

    public final T peek() {
        return get(((int) this.d.get()) & this.a);
    }

    public final void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    public final boolean isEmpty() {
        return this.c == this.d;
    }

    public final int size() {
        long j = this.d.get();
        while (true) {
            long j2 = this.c.get();
            long j3 = this.d.get();
            if (j == j3) {
                return (int) (j2 - j3);
            }
            j = j3;
        }
    }

    public final boolean contains(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final Iterator<T> iterator() {
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
