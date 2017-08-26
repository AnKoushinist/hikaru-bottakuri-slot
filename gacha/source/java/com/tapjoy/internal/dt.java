package com.tapjoy.internal;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

class dt extends dk {
    private int a = 0;
    protected final byte[] d;

    class a implements com.tapjoy.internal.dk.a {
        final /* synthetic */ dt a;
        private int b;
        private final int c;

        private a(dt dtVar) {
            this.a = dtVar;
            this.b = 0;
            this.c = dtVar.a();
        }

        public final boolean hasNext() {
            return this.b < this.c;
        }

        public final byte a() {
            try {
                byte[] bArr = this.a.d;
                int i = this.b;
                this.b = i + 1;
                return bArr[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchElementException(e.getMessage());
            }
        }

        public final void remove() {
            throw new UnsupportedOperationException();
        }

        public final /* synthetic */ Object next() {
            return Byte.valueOf(a());
        }
    }

    public /* synthetic */ Iterator iterator() {
        return c();
    }

    dt(byte[] bArr) {
        this.d = bArr;
    }

    public int a() {
        return this.d.length;
    }

    protected void a(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.d, i, bArr, i2, i3);
    }

    final void a(OutputStream outputStream, int i, int i2) {
        outputStream.write(this.d, b() + i, i2);
    }

    public final String b(String str) {
        return new String(this.d, b(), a(), str);
    }

    public final boolean f() {
        int b = b();
        return eb.a(this.d, b, a() + b);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final int a(int r11, int r12, int r13) {
        /*
        r10 = this;
        r9 = -32;
        r4 = -96;
        r2 = -1;
        r8 = -65;
        r0 = r10.b();
        r1 = r0 + r12;
        r5 = r10.d;
        r6 = r1 + r13;
        if (r11 == 0) goto L_0x008b;
    L_0x0013:
        if (r1 < r6) goto L_0x0016;
    L_0x0015:
        return r11;
    L_0x0016:
        r7 = (byte) r11;
        if (r7 >= r9) goto L_0x0025;
    L_0x0019:
        r0 = -62;
        if (r7 < r0) goto L_0x0023;
    L_0x001d:
        r0 = r1 + 1;
        r1 = r5[r1];
        if (r1 <= r8) goto L_0x008c;
    L_0x0023:
        r11 = r2;
        goto L_0x0015;
    L_0x0025:
        r0 = -16;
        if (r7 >= r0) goto L_0x0050;
    L_0x0029:
        r0 = r11 >> 8;
        r0 = r0 ^ -1;
        r0 = (byte) r0;
        if (r0 != 0) goto L_0x003c;
    L_0x0030:
        r3 = r1 + 1;
        r0 = r5[r1];
        if (r3 < r6) goto L_0x003b;
    L_0x0036:
        r11 = com.tapjoy.internal.eb.a(r7, r0);
        goto L_0x0015;
    L_0x003b:
        r1 = r3;
    L_0x003c:
        if (r0 > r8) goto L_0x004e;
    L_0x003e:
        if (r7 != r9) goto L_0x0042;
    L_0x0040:
        if (r0 < r4) goto L_0x004e;
    L_0x0042:
        r3 = -19;
        if (r7 != r3) goto L_0x0048;
    L_0x0046:
        if (r0 >= r4) goto L_0x004e;
    L_0x0048:
        r0 = r1 + 1;
        r1 = r5[r1];
        if (r1 <= r8) goto L_0x008c;
    L_0x004e:
        r11 = r2;
        goto L_0x0015;
    L_0x0050:
        r0 = r11 >> 8;
        r0 = r0 ^ -1;
        r3 = (byte) r0;
        r0 = 0;
        if (r3 != 0) goto L_0x0063;
    L_0x0058:
        r3 = r1 + 1;
        r1 = r5[r1];
        if (r3 < r6) goto L_0x0091;
    L_0x005e:
        r11 = com.tapjoy.internal.eb.a(r7, r1);
        goto L_0x0015;
    L_0x0063:
        r0 = r11 >> 16;
        r0 = (byte) r0;
        r4 = r3;
        r3 = r1;
    L_0x0068:
        if (r0 != 0) goto L_0x0075;
    L_0x006a:
        r1 = r3 + 1;
        r0 = r5[r3];
        if (r1 < r6) goto L_0x0076;
    L_0x0070:
        r11 = com.tapjoy.internal.eb.a(r7, r4, r0);
        goto L_0x0015;
    L_0x0075:
        r1 = r3;
    L_0x0076:
        if (r4 > r8) goto L_0x0089;
    L_0x0078:
        r3 = r7 << 28;
        r4 = r4 + 112;
        r3 = r3 + r4;
        r3 = r3 >> 30;
        if (r3 != 0) goto L_0x0089;
    L_0x0081:
        if (r0 > r8) goto L_0x0089;
    L_0x0083:
        r0 = r1 + 1;
        r1 = r5[r1];
        if (r1 <= r8) goto L_0x008c;
    L_0x0089:
        r11 = r2;
        goto L_0x0015;
    L_0x008b:
        r0 = r1;
    L_0x008c:
        r11 = com.tapjoy.internal.eb.b(r5, r0, r6);
        goto L_0x0015;
    L_0x0091:
        r4 = r1;
        goto L_0x0068;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.dt.a(int, int, int):int");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof dk)) {
            return false;
        }
        if (a() != ((dk) obj).a()) {
            return false;
        }
        if (a() == 0) {
            return true;
        }
        if (obj instanceof dt) {
            return a((dt) obj, 0, a());
        }
        if (obj instanceof dy) {
            return obj.equals(this);
        }
        String valueOf = String.valueOf(String.valueOf(obj.getClass()));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 49).append("Has a new type of ByteString been created? Found ").append(valueOf).toString());
    }

    final boolean a(dt dtVar, int i, int i2) {
        if (i2 > dtVar.a()) {
            throw new IllegalArgumentException("Length too large: " + i2 + a());
        } else if (i + i2 > dtVar.a()) {
            throw new IllegalArgumentException("Ran off end of other: " + i + ", " + i2 + ", " + dtVar.a());
        } else {
            byte[] bArr = this.d;
            byte[] bArr2 = dtVar.d;
            int b = b() + i2;
            int b2 = b();
            int b3 = dtVar.b() + i;
            while (b2 < b) {
                if (bArr[b2] != bArr2[b3]) {
                    return false;
                }
                b2++;
                b3++;
            }
            return true;
        }
    }

    public int hashCode() {
        int i = this.a;
        if (i == 0) {
            i = a();
            i = b(i, 0, i);
            if (i == 0) {
                i = 1;
            }
            this.a = i;
        }
        return i;
    }

    protected final int j() {
        return this.a;
    }

    protected final int b(int i, int i2, int i3) {
        byte[] bArr = this.d;
        int b = b() + i2;
        for (int i4 = b; i4 < b + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    public com.tapjoy.internal.dk.a c() {
        return new a();
    }

    protected final int h() {
        return 0;
    }

    protected final boolean i() {
        return true;
    }

    protected int b() {
        return 0;
    }
}
