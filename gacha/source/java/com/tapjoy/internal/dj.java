package com.tapjoy.internal;

import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class dj extends dt {
    final int a;
    private final int e;

    class a implements com.tapjoy.internal.dk.a {
        final /* synthetic */ dj a;
        private int b;
        private final int c;

        private a(dj djVar) {
            this.a = djVar;
            this.b = djVar.a;
            this.c = this.b + djVar.a();
        }

        public final boolean hasNext() {
            return this.b < this.c;
        }

        public final byte a() {
            if (this.b >= this.c) {
                throw new NoSuchElementException();
            }
            byte[] bArr = this.a.d;
            int i = this.b;
            this.b = i + 1;
            return bArr[i];
        }

        public final void remove() {
            throw new UnsupportedOperationException();
        }

        public final /* synthetic */ Object next() {
            return Byte.valueOf(a());
        }
    }

    public final /* synthetic */ Iterator iterator() {
        return c();
    }

    dj(byte[] bArr, int i, int i2) {
        super(bArr);
        if (i < 0) {
            throw new IllegalArgumentException("Offset too small: " + i);
        } else if (i2 < 0) {
            throw new IllegalArgumentException("Length too small: " + i);
        } else if (((long) i) + ((long) i2) > ((long) bArr.length)) {
            throw new IllegalArgumentException("Offset+Length too large: " + i + Operation.PLUS + i2);
        } else {
            this.a = i;
            this.e = i2;
        }
    }

    public final int a() {
        return this.e;
    }

    protected final int b() {
        return this.a;
    }

    protected final void a(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.d, this.a + i, bArr, i2, i3);
    }

    public final com.tapjoy.internal.dk.a c() {
        return new a();
    }
}
