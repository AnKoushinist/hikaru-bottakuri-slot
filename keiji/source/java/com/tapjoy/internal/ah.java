package com.tapjoy.internal;

import android.view.ViewGroup;
import java.util.Iterator;

public final class ah {

    static class a implements Iterator {
        private final ViewGroup a;
        private int b;
        private int c = 0;

        public a(ViewGroup viewGroup) {
            this.a = viewGroup;
            this.b = viewGroup.getChildCount();
        }

        public final boolean hasNext() {
            return this.c < this.b;
        }

        public final void remove() {
            this.a.removeViewAt(this.c - 1);
        }

        public final /* synthetic */ Object next() {
            ViewGroup viewGroup = this.a;
            int i = this.c;
            this.c = i + 1;
            return viewGroup.getChildAt(i);
        }
    }

    public static Iterable a(ViewGroup viewGroup) {
        final Iterator aVar = new a(viewGroup);
        return new Iterable() {
            public final Iterator iterator() {
                return aVar;
            }
        };
    }
}
