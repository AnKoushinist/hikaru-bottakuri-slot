package com.tapjoy.internal;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

final class dy extends dk {
    private static final int[] a;
    private final int d;
    private final dk e;
    private final dk f;
    private final int g;
    private final int h;
    private int i;

    static class a {
        final Stack a;

        private a() {
            this.a = new Stack();
        }

        final void a(dk dkVar) {
            dk dkVar2 = dkVar;
            while (!dkVar2.i()) {
                if (dkVar2 instanceof dy) {
                    dy dyVar = (dy) dkVar2;
                    a(dyVar.e);
                    dkVar2 = dyVar.f;
                } else {
                    String valueOf = String.valueOf(String.valueOf(dkVar2.getClass()));
                    throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 49).append("Has a new type of ByteString been created? Found ").append(valueOf).toString());
                }
            }
            int a = a(dkVar2.a());
            int i = dy.a[a + 1];
            if (this.a.isEmpty() || ((dk) this.a.peek()).a() >= i) {
                this.a.push(dkVar2);
                return;
            }
            int i2 = dy.a[a];
            dk dkVar3 = (dk) this.a.pop();
            while (!this.a.isEmpty() && ((dk) this.a.peek()).a() < i2) {
                dkVar3 = new dy((dk) this.a.pop(), dkVar3);
            }
            dkVar2 = new dy(dkVar3, dkVar2);
            while (!this.a.isEmpty()) {
                if (((dk) this.a.peek()).a() >= dy.a[a(dkVar2.a()) + 1]) {
                    break;
                }
                dkVar2 = new dy((dk) this.a.pop(), dkVar2);
            }
            this.a.push(dkVar2);
        }

        private static int a(int i) {
            int binarySearch = Arrays.binarySearch(dy.a, i);
            if (binarySearch < 0) {
                return (-(binarySearch + 1)) - 1;
            }
            return binarySearch;
        }
    }

    static class b implements Iterator {
        private final Stack a;
        private dt b;

        public final /* synthetic */ Object next() {
            return a();
        }

        private b(dk dkVar) {
            this.a = new Stack();
            this.b = a(dkVar);
        }

        private dt a(dk dkVar) {
            dk dkVar2 = dkVar;
            while (dkVar2 instanceof dy) {
                dy dyVar = (dy) dkVar2;
                this.a.push(dyVar);
                dkVar2 = dyVar.e;
            }
            return (dt) dkVar2;
        }

        private dt b() {
            while (!this.a.isEmpty()) {
                Object obj;
                dt a = a(((dy) this.a.pop()).f);
                if (a.a() == 0) {
                    obj = 1;
                    continue;
                } else {
                    obj = null;
                    continue;
                }
                if (obj == null) {
                    return a;
                }
            }
            return null;
        }

        public final boolean hasNext() {
            return this.b != null;
        }

        public final dt a() {
            if (this.b == null) {
                throw new NoSuchElementException();
            }
            dt dtVar = this.b;
            this.b = b();
            return dtVar;
        }

        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class c implements com.tapjoy.internal.dk.a {
        int a;
        final /* synthetic */ dy b;
        private final b c;
        private com.tapjoy.internal.dk.a d;

        private c(dy dyVar) {
            this.b = dyVar;
            this.c = new b(dyVar);
            this.d = this.c.a().c();
            this.a = dyVar.a();
        }

        public final boolean hasNext() {
            return this.a > 0;
        }

        public final byte a() {
            if (!this.d.hasNext()) {
                this.d = this.c.a().c();
            }
            this.a--;
            return this.d.a();
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

    static {
        int i = 1;
        List arrayList = new ArrayList();
        int i2 = 1;
        while (i > 0) {
            arrayList.add(Integer.valueOf(i));
            int i3 = i2 + i;
            i2 = i;
            i = i3;
        }
        arrayList.add(Integer.valueOf(Integer.MAX_VALUE));
        a = new int[arrayList.size()];
        for (i2 = 0; i2 < a.length; i2++) {
            a[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
    }

    private dy(dk dkVar, dk dkVar2) {
        this.i = 0;
        this.e = dkVar;
        this.f = dkVar2;
        this.g = dkVar.a();
        this.d = this.g + dkVar2.a();
        this.h = Math.max(dkVar.h(), dkVar2.h()) + 1;
    }

    static dk a(dk dkVar, dk dkVar2) {
        dy dyVar = dkVar instanceof dy ? (dy) dkVar : null;
        if (dkVar2.a() == 0) {
            return dkVar;
        }
        if (dkVar.a() == 0) {
            return dkVar2;
        }
        int a = dkVar.a() + dkVar2.a();
        if (a < 128) {
            return b(dkVar, dkVar2);
        }
        if (dyVar != null && dyVar.f.a() + dkVar2.a() < 128) {
            return new dy(dyVar.e, b(dyVar.f, dkVar2));
        } else if (dyVar == null || dyVar.e.h() <= dyVar.f.h() || dyVar.h <= dkVar2.h()) {
            if (a >= a[Math.max(dkVar.h(), dkVar2.h()) + 1]) {
                return new dy(dkVar, dkVar2);
            }
            a aVar = new a();
            aVar.a(dkVar);
            aVar.a(dkVar2);
            dk dkVar3 = (dk) aVar.a.pop();
            while (!aVar.a.isEmpty()) {
                dkVar3 = new dy((dk) aVar.a.pop(), dkVar3);
            }
            return dkVar3;
        } else {
            return new dy(dyVar.e, new dy(dyVar.f, dkVar2));
        }
    }

    private static dt b(dk dkVar, dk dkVar2) {
        int a = dkVar.a();
        int a2 = dkVar2.a();
        byte[] bArr = new byte[(a + a2)];
        dkVar.b(bArr, 0, 0, a);
        dkVar2.b(bArr, 0, a, a2);
        return new dt(bArr);
    }

    public final int a() {
        return this.d;
    }

    protected final int h() {
        return this.h;
    }

    protected final boolean i() {
        return this.d >= a[this.h];
    }

    protected final void a(byte[] bArr, int i, int i2, int i3) {
        if (i + i3 <= this.g) {
            this.e.a(bArr, i, i2, i3);
        } else if (i >= this.g) {
            this.f.a(bArr, i - this.g, i2, i3);
        } else {
            int i4 = this.g - i;
            this.e.a(bArr, i, i2, i4);
            this.f.a(bArr, 0, i2 + i4, i3 - i4);
        }
    }

    final void a(OutputStream outputStream, int i, int i2) {
        if (i + i2 <= this.g) {
            this.e.a(outputStream, i, i2);
        } else if (i >= this.g) {
            this.f.a(outputStream, i - this.g, i2);
        } else {
            int i3 = this.g - i;
            this.e.a(outputStream, i, i3);
            this.f.a(outputStream, 0, i2 - i3);
        }
    }

    public final String b(String str) {
        return new String(d(), str);
    }

    public final boolean f() {
        if (this.f.a(this.e.a(0, 0, this.g), 0, this.f.a()) == 0) {
            return true;
        }
        return false;
    }

    protected final int a(int i, int i2, int i3) {
        if (i2 + i3 <= this.g) {
            return this.e.a(i, i2, i3);
        }
        if (i2 >= this.g) {
            return this.f.a(i, i2 - this.g, i3);
        }
        int i4 = this.g - i2;
        return this.f.a(this.e.a(i, i2, i4), 0, i3 - i4);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof dk)) {
            return false;
        }
        dk dkVar = (dk) obj;
        if (this.d != dkVar.a()) {
            return false;
        }
        if (this.d == 0) {
            return true;
        }
        int j;
        if (this.i != 0) {
            j = dkVar.j();
            if (!(j == 0 || this.i == j)) {
                return false;
            }
        }
        Iterator bVar = new b(this);
        dt dtVar = (dt) bVar.next();
        Iterator bVar2 = new b(dkVar);
        dt dtVar2 = (dt) bVar2.next();
        int i = 0;
        dt dtVar3 = dtVar;
        int i2 = 0;
        j = 0;
        while (true) {
            int a = dtVar3.a() - i2;
            int a2 = dtVar2.a() - i;
            int min = Math.min(a, a2);
            if (!(i2 == 0 ? dtVar3.a(dtVar2, i, min) : dtVar2.a(dtVar3, i2, min))) {
                return false;
            }
            int i3 = j + min;
            if (i3 >= this.d) {
                break;
            }
            if (min == a) {
                dtVar3 = (dt) bVar.next();
                i2 = 0;
            } else {
                i2 += min;
            }
            if (min == a2) {
                dtVar2 = (dt) bVar2.next();
                i = 0;
                j = i3;
            } else {
                i += min;
                j = i3;
            }
        }
        if (i3 == this.d) {
            return true;
        }
        throw new IllegalStateException();
    }

    public final int hashCode() {
        int i = this.i;
        if (i == 0) {
            i = b(this.d, 0, this.d);
            if (i == 0) {
                i = 1;
            }
            this.i = i;
        }
        return i;
    }

    protected final int j() {
        return this.i;
    }

    protected final int b(int i, int i2, int i3) {
        if (i2 + i3 <= this.g) {
            return this.e.b(i, i2, i3);
        }
        if (i2 >= this.g) {
            return this.f.b(i, i2 - this.g, i3);
        }
        int i4 = this.g - i2;
        return this.f.b(this.e.b(i, i2, i4), 0, i3 - i4);
    }

    public final com.tapjoy.internal.dk.a c() {
        return new c();
    }
}
