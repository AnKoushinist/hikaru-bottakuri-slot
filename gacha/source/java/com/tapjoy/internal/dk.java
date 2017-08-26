package com.tapjoy.internal;

import com.d.a.a.c;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class dk implements Iterable {
    public static final dk b = new dt(new byte[0]);
    static final /* synthetic */ boolean c;

    public interface a extends Iterator {
        byte a();
    }

    public static final class b extends OutputStream {
        private static final byte[] a = new byte[0];
        private final int b = 128;
        private final ArrayList c = new ArrayList();
        private int d;
        private byte[] e = new byte[128];
        private int f;

        b() {
        }

        public final synchronized void write(int i) {
            if (this.f == this.e.length) {
                a(1);
            }
            byte[] bArr = this.e;
            int i2 = this.f;
            this.f = i2 + 1;
            bArr[i2] = (byte) i;
        }

        public final synchronized void write(byte[] bArr, int i, int i2) {
            if (i2 <= this.e.length - this.f) {
                System.arraycopy(bArr, i, this.e, this.f, i2);
                this.f += i2;
            } else {
                int length = this.e.length - this.f;
                System.arraycopy(bArr, i, this.e, this.f, length);
                int i3 = i + length;
                length = i2 - length;
                a(length);
                System.arraycopy(bArr, i3, this.e, 0, length);
                this.f = length;
            }
        }

        public final synchronized dk a() {
            if (this.f >= this.e.length) {
                this.c.add(new dt(this.e));
                this.e = a;
            } else if (this.f > 0) {
                Object obj = this.e;
                int i = this.f;
                Object obj2 = new byte[i];
                System.arraycopy(obj, 0, obj2, 0, Math.min(obj.length, i));
                this.c.add(new dt(obj2));
            }
            this.d += this.f;
            this.f = 0;
            return dk.a(this.c);
        }

        private synchronized int b() {
            return this.d + this.f;
        }

        public final String toString() {
            return String.format("<ByteString.Output@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(b())});
        }

        private void a(int i) {
            this.c.add(new dt(this.e));
            this.d += this.e.length;
            this.e = new byte[Math.max(this.b, Math.max(i, this.d >>> 1))];
            this.f = 0;
        }
    }

    public abstract int a();

    protected abstract int a(int i, int i2, int i3);

    abstract void a(OutputStream outputStream, int i, int i2);

    protected abstract void a(byte[] bArr, int i, int i2, int i3);

    protected abstract int b(int i, int i2, int i3);

    public abstract String b(String str);

    public abstract a c();

    public abstract boolean f();

    protected abstract int h();

    public abstract int hashCode();

    protected abstract boolean i();

    protected abstract int j();

    static {
        boolean z;
        if (dk.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        c = z;
    }

    public /* synthetic */ Iterator iterator() {
        return c();
    }

    dk() {
    }

    public static dk a(byte[] bArr, int i, int i2) {
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        return new dt(obj);
    }

    public static dk a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static dk a(String str) {
        try {
            return new dt(str.getBytes(c.DEFAULT_CHARSET));
        } catch (Throwable e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }

    public final dk a(dk dkVar) {
        int a = a();
        int a2 = dkVar.a();
        if (((long) a) + ((long) a2) < 2147483647L) {
            return dy.a(this, dkVar);
        }
        throw new IllegalArgumentException("ByteString would be too long: " + a + Operation.PLUS + a2);
    }

    public static dk a(Iterable iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
        } else {
            Collection arrayList = new ArrayList();
            for (dk add : iterable) {
                arrayList.add(add);
            }
            iterable = arrayList;
        }
        if (iterable.isEmpty()) {
            return b;
        }
        return a(iterable.iterator(), iterable.size());
    }

    private static dk a(Iterator it, int i) {
        if (!c && i <= 0) {
            throw new AssertionError();
        } else if (i == 1) {
            return (dk) it.next();
        } else {
            int i2 = i >>> 1;
            return a(it, i2).a(a(it, i - i2));
        }
    }

    public final void b(byte[] bArr, int i, int i2, int i3) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Source offset < 0: " + i);
        } else if (i2 < 0) {
            throw new IndexOutOfBoundsException("Target offset < 0: " + i2);
        } else if (i3 < 0) {
            throw new IndexOutOfBoundsException("Length < 0: " + i3);
        } else if (i + i3 > a()) {
            throw new IndexOutOfBoundsException("Source end offset < 0: " + (i + i3));
        } else if (i2 + i3 > bArr.length) {
            throw new IndexOutOfBoundsException("Target end offset < 0: " + (i2 + i3));
        } else if (i3 > 0) {
            a(bArr, i, i2, i3);
        }
    }

    public final byte[] d() {
        int a = a();
        if (a == 0) {
            return dp.a;
        }
        byte[] bArr = new byte[a];
        a(bArr, 0, 0, a);
        return bArr;
    }

    public final String e() {
        try {
            return b(c.DEFAULT_CHARSET);
        } catch (Throwable e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }

    public static b g() {
        return new b();
    }

    public String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(a())});
    }
}
