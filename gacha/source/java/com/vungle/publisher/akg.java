package com.vungle.publisher;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* compiled from: vungle */
abstract class akg<E> extends AbstractQueue<E> {
    protected final AtomicReferenceArray<E> a;
    protected final int b;

    public akg(int i) {
        int a = aks.a(i);
        this.b = a - 1;
        this.a = new AtomicReferenceArray(a);
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

    protected final int a(long j) {
        return ((int) j) & this.b;
    }

    protected final E a(int i) {
        return this.a.get(i);
    }
}
