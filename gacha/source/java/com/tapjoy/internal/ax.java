package com.tapjoy.internal;

import java.util.AbstractQueue;
import java.util.Iterator;

public abstract class ax extends AbstractQueue implements bb {
    public Iterator iterator() {
        return new Iterator(this) {
            final /* synthetic */ ax a;
            private int b = 0;

            {
                this.a = r2;
            }

            public final boolean hasNext() {
                return this.b < this.a.size();
            }

            public final Object next() {
                ax axVar = this.a;
                int i = this.b;
                this.b = i + 1;
                return axVar.a(i);
            }

            public final void remove() {
                if (this.b == 1) {
                    this.a.b(1);
                    this.b = 0;
                    return;
                }
                throw new UnsupportedOperationException("For the first element only");
            }
        };
    }
}
