package com.tapjoy.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class cu {
    static final cx a = new cx() {
        public final boolean hasNext() {
            return false;
        }

        public final Object next() {
            throw new NoSuchElementException();
        }
    };
    private static final Iterator b = new Iterator() {
        public final boolean hasNext() {
            return false;
        }

        public final Object next() {
            throw new NoSuchElementException();
        }

        public final void remove() {
            throw new IllegalStateException();
        }
    };

    public static Object a(Iterator it) {
        return it.hasNext() ? it.next() : null;
    }
}
