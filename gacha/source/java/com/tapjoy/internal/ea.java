package com.tapjoy.internal;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class ea extends AbstractList implements ds, RandomAccess {
    private final ds a;

    public ea(ds dsVar) {
        this.a = dsVar;
    }

    public final int size() {
        return this.a.size();
    }

    public final dk a(int i) {
        return this.a.a(i);
    }

    public final void a(dk dkVar) {
        throw new UnsupportedOperationException();
    }

    public final ListIterator listIterator(final int i) {
        return new ListIterator(this) {
            ListIterator a = this.c.a.listIterator(i);
            final /* synthetic */ ea c;

            public final boolean hasNext() {
                return this.a.hasNext();
            }

            public final boolean hasPrevious() {
                return this.a.hasPrevious();
            }

            public final int nextIndex() {
                return this.a.nextIndex();
            }

            public final int previousIndex() {
                return this.a.previousIndex();
            }

            public final void remove() {
                throw new UnsupportedOperationException();
            }

            public final /* synthetic */ void add(Object obj) {
                throw new UnsupportedOperationException();
            }

            public final /* synthetic */ void set(Object obj) {
                throw new UnsupportedOperationException();
            }

            public final /* bridge */ /* synthetic */ Object previous() {
                return (String) this.a.previous();
            }

            public final /* bridge */ /* synthetic */ Object next() {
                return (String) this.a.next();
            }
        };
    }

    public final Iterator iterator() {
        return new Iterator(this) {
            Iterator a = this.b.a.iterator();
            final /* synthetic */ ea b;

            {
                this.b = r2;
            }

            public final boolean hasNext() {
                return this.a.hasNext();
            }

            public final void remove() {
                throw new UnsupportedOperationException();
            }

            public final /* bridge */ /* synthetic */ Object next() {
                return (String) this.a.next();
            }
        };
    }

    public final List a() {
        return this.a.a();
    }

    public final ds b() {
        return this;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return (String) this.a.get(i);
    }
}
