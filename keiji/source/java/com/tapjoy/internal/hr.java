package com.tapjoy.internal;

import java.io.EOFException;
import java.nio.charset.Charset;
import org.cocos2dx.lib.BuildConfig;

public final class hr implements hs, ht, Cloneable {
    private static final byte[] c = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102};
    hy a;
    long b;

    public final /* synthetic */ hs b(hu huVar) {
        return a(huVar);
    }

    public final /* synthetic */ hs b(String str) {
        return a(str);
    }

    public final /* synthetic */ Object clone() {
        return h();
    }

    public final /* synthetic */ hs d(int i) {
        return b(i);
    }

    public final /* synthetic */ hs e(int i) {
        return a(i);
    }

    public final /* synthetic */ hs f(long j) {
        return e(j);
    }

    public final hs a() {
        return this;
    }

    public final boolean b() {
        return this.b == 0;
    }

    public final void a(long j) {
        if (this.b < j) {
            throw new EOFException();
        }
    }

    public final byte c() {
        if (this.b == 0) {
            throw new IllegalStateException("size == 0");
        }
        hy hyVar = this.a;
        int i = hyVar.b;
        int i2 = hyVar.c;
        int i3 = i + 1;
        byte b = hyVar.a[i];
        this.b--;
        if (i3 == i2) {
            this.a = hyVar.a();
            hz.a(hyVar);
        } else {
            hyVar.b = i3;
        }
        return b;
    }

    public final int d() {
        if (this.b < 4) {
            throw new IllegalStateException("size < 4: " + this.b);
        }
        hy hyVar = this.a;
        int i = hyVar.b;
        int i2 = hyVar.c;
        if (i2 - i < 4) {
            return ((((c() & 255) << 24) | ((c() & 255) << 16)) | ((c() & 255) << 8)) | (c() & 255);
        }
        byte[] bArr = hyVar.a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        i = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        i3 = i4 + 1;
        i |= (bArr[i4] & 255) << 8;
        i4 = i3 + 1;
        i |= bArr[i3] & 255;
        this.b -= 4;
        if (i4 == i2) {
            this.a = hyVar.a();
            hz.a(hyVar);
            return i;
        }
        hyVar.b = i4;
        return i;
    }

    public final int e() {
        return ie.a(d());
    }

    public final hu b(long j) {
        return new hu(g(j));
    }

    public final String c(long j) {
        Charset charset = ie.a;
        ie.a(this.b, 0, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        } else if (j == 0) {
            return BuildConfig.FLAVOR;
        } else {
            hy hyVar = this.a;
            if (((long) hyVar.b) + j > ((long) hyVar.c)) {
                return new String(g(j), charset);
            }
            String str = new String(hyVar.a, hyVar.b, (int) j, charset);
            hyVar.b = (int) (((long) hyVar.b) + j);
            this.b -= j;
            if (hyVar.b != hyVar.c) {
                return str;
            }
            this.a = hyVar.a();
            hz.a(hyVar);
            return str;
        }
    }

    public final byte[] g() {
        try {
            return g(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    private byte[] g(long j) {
        ie.a(this.b, 0, j);
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        byte[] bArr = new byte[((int) j)];
        a(bArr);
        return bArr;
    }

    private void a(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            int i2;
            int length = bArr.length - i;
            ie.a((long) bArr.length, (long) i, (long) length);
            hy hyVar = this.a;
            if (hyVar == null) {
                i2 = -1;
            } else {
                i2 = Math.min(length, hyVar.c - hyVar.b);
                System.arraycopy(hyVar.a, hyVar.b, bArr, i, i2);
                hyVar.b += i2;
                this.b -= (long) i2;
                if (hyVar.b == hyVar.c) {
                    this.a = hyVar.a();
                    hz.a(hyVar);
                }
            }
            if (i2 == -1) {
                throw new EOFException();
            }
            i = i2 + i;
        }
    }

    public final void d(long j) {
        while (j > 0) {
            if (this.a == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j, (long) (this.a.c - this.a.b));
            this.b -= (long) min;
            j -= (long) min;
            hy hyVar = this.a;
            hyVar.b = min + hyVar.b;
            if (this.a.b == this.a.c) {
                hy hyVar2 = this.a;
                this.a = hyVar2.a();
                hz.a(hyVar2);
            }
        }
    }

    public final hr a(hu huVar) {
        if (huVar == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        huVar.a(this);
        return this;
    }

    public final hr a(String str) {
        int length = str.length();
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (length < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + length + " < 0");
        } else if (length > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + length + " > " + str.length());
        } else {
            int i = 0;
            while (i < length) {
                char charAt = str.charAt(i);
                int i2;
                if (charAt < '\u0080') {
                    hy c = c(1);
                    byte[] bArr = c.a;
                    int i3 = c.c - i;
                    int min = Math.min(length, 8192 - i3);
                    i2 = i + 1;
                    bArr[i + i3] = (byte) charAt;
                    while (i2 < min) {
                        charAt = str.charAt(i2);
                        if (charAt >= '\u0080') {
                            break;
                        }
                        i = i2 + 1;
                        bArr[i2 + i3] = (byte) charAt;
                        i2 = i;
                    }
                    i = (i2 + i3) - c.c;
                    c.c += i;
                    this.b += (long) i;
                    i = i2;
                } else if (charAt < '\u0800') {
                    a((charAt >> 6) | 192);
                    a((charAt & 63) | 128);
                    i++;
                } else if (charAt < '\ud800' || charAt > '\udfff') {
                    a((charAt >> 12) | 224);
                    a(((charAt >> 6) & 63) | 128);
                    a((charAt & 63) | 128);
                    i++;
                } else {
                    if (i + 1 < length) {
                        i2 = str.charAt(i + 1);
                    } else {
                        i2 = 0;
                    }
                    if (charAt > '\udbff' || i2 < 56320 || i2 > 57343) {
                        a(63);
                        i++;
                    } else {
                        i2 = ((i2 & -56321) | ((charAt & -55297) << 10)) + 65536;
                        a((i2 >> 18) | 240);
                        a(((i2 >> 12) & 63) | 128);
                        a(((i2 >> 6) & 63) | 128);
                        a((i2 & 63) | 128);
                        i += 2;
                    }
                }
            }
            return this;
        }
    }

    public final hr a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        ie.a((long) bArr.length, 0, (long) i2);
        int i3 = i2 + 0;
        while (i < i3) {
            hy c = c(1);
            int min = Math.min(i3 - i, 8192 - c.c);
            System.arraycopy(bArr, i, c.a, c.c, min);
            i += min;
            c.c = min + c.c;
        }
        this.b += (long) i2;
        return this;
    }

    public final hr a(int i) {
        hy c = c(1);
        byte[] bArr = c.a;
        int i2 = c.c;
        c.c = i2 + 1;
        bArr[i2] = (byte) i;
        this.b++;
        return this;
    }

    public final hr b(int i) {
        int a = ie.a(i);
        hy c = c(4);
        byte[] bArr = c.a;
        int i2 = c.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((a >>> 24) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) ((a >>> 16) & 255);
        i3 = i2 + 1;
        bArr[i2] = (byte) ((a >>> 8) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) (a & 255);
        c.c = i2;
        this.b += 4;
        return this;
    }

    public final hr e(long j) {
        long a = ie.a(j);
        hy c = c(8);
        byte[] bArr = c.a;
        int i = c.c;
        int i2 = i + 1;
        bArr[i] = (byte) ((int) ((a >>> 56) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((a >>> 48) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((a >>> 40) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((a >>> 32) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((a >>> 24) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((a >>> 16) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((a >>> 8) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) (a & 255));
        c.c = i;
        this.b += 8;
        return this;
    }

    final hy c(int i) {
        if (i <= 0 || i > 8192) {
            throw new IllegalArgumentException();
        } else if (this.a == null) {
            this.a = hz.a();
            hy hyVar = this.a;
            hy hyVar2 = this.a;
            r0 = this.a;
            hyVar2.g = r0;
            hyVar.f = r0;
            return r0;
        } else {
            r0 = this.a.g;
            if (r0.c + i > 8192 || !r0.e) {
                return r0.a(hz.a());
            }
            return r0;
        }
    }

    public final void a(hr hrVar, long j) {
        if (hrVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (hrVar == this) {
            throw new IllegalArgumentException("source == this");
        } else {
            ie.a(hrVar.b, 0, j);
            while (j > 0) {
                hy hyVar;
                hy hyVar2;
                if (j < ((long) (hrVar.a.c - hrVar.a.b))) {
                    hyVar = this.a != null ? this.a.g : null;
                    if (hyVar != null && hyVar.e) {
                        if ((((long) hyVar.c) + j) - ((long) (hyVar.d ? 0 : hyVar.b)) <= 8192) {
                            hrVar.a.a(hyVar, (int) j);
                            hrVar.b -= j;
                            this.b += j;
                            return;
                        }
                    }
                    hyVar = hrVar.a;
                    int i = (int) j;
                    if (i <= 0 || i > hyVar.c - hyVar.b) {
                        throw new IllegalArgumentException();
                    }
                    if (i >= 1024) {
                        hyVar2 = new hy(hyVar);
                    } else {
                        hyVar2 = hz.a();
                        System.arraycopy(hyVar.a, hyVar.b, hyVar2.a, 0, i);
                    }
                    hyVar2.c = hyVar2.b + i;
                    hyVar.b = i + hyVar.b;
                    hyVar.g.a(hyVar2);
                    hrVar.a = hyVar2;
                }
                hyVar2 = hrVar.a;
                long j2 = (long) (hyVar2.c - hyVar2.b);
                hrVar.a = hyVar2.a();
                if (this.a == null) {
                    this.a = hyVar2;
                    hyVar2 = this.a;
                    hyVar = this.a;
                    hy hyVar3 = this.a;
                    hyVar.g = hyVar3;
                    hyVar2.f = hyVar3;
                } else {
                    hyVar = this.a.g.a(hyVar2);
                    if (hyVar.g == hyVar) {
                        throw new IllegalStateException();
                    } else if (hyVar.g.e) {
                        int i2 = hyVar.c - hyVar.b;
                        if (i2 <= (hyVar.g.d ? 0 : hyVar.g.b) + (8192 - hyVar.g.c)) {
                            hyVar.a(hyVar.g, i2);
                            hyVar.a();
                            hz.a(hyVar);
                        }
                    }
                }
                hrVar.b -= j2;
                this.b += j2;
                j -= j2;
            }
        }
    }

    public final long b(hr hrVar, long j) {
        if (hrVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.b == 0) {
            return -1;
        } else {
            if (j > this.b) {
                j = this.b;
            }
            hrVar.a(this, j);
            return j;
        }
    }

    public final void flush() {
    }

    public final void close() {
    }

    public final boolean equals(Object obj) {
        long j = 0;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof hr)) {
            return false;
        }
        hr hrVar = (hr) obj;
        if (this.b != hrVar.b) {
            return false;
        }
        if (this.b == 0) {
            return true;
        }
        hy hyVar = this.a;
        hy hyVar2 = hrVar.a;
        int i = hyVar.b;
        int i2 = hyVar2.b;
        while (j < this.b) {
            long min = (long) Math.min(hyVar.c - i, hyVar2.c - i2);
            int i3 = 0;
            while (((long) i3) < min) {
                int i4 = i + 1;
                byte b = hyVar.a[i];
                i = i2 + 1;
                if (b != hyVar2.a[i2]) {
                    return false;
                }
                i3++;
                i2 = i;
                i = i4;
            }
            if (i == hyVar.c) {
                hyVar = hyVar.f;
                i = hyVar.b;
            }
            if (i2 == hyVar2.c) {
                hyVar2 = hyVar2.f;
                i2 = hyVar2.b;
            }
            j += min;
        }
        return true;
    }

    public final int hashCode() {
        hy hyVar = this.a;
        if (hyVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = hyVar.b;
            while (i2 < hyVar.c) {
                int i3 = hyVar.a[i2] + (i * 31);
                i2++;
                i = i3;
            }
            hyVar = hyVar.f;
        } while (hyVar != this.a);
        return i;
    }

    public final hr h() {
        hr hrVar = new hr();
        if (this.b == 0) {
            return hrVar;
        }
        hrVar.a = new hy(this.a);
        hy hyVar = hrVar.a;
        hy hyVar2 = hrVar.a;
        hy hyVar3 = hrVar.a;
        hyVar2.g = hyVar3;
        hyVar.f = hyVar3;
        for (hyVar = this.a.f; hyVar != this.a; hyVar = hyVar.f) {
            hrVar.a.g.a(new hy(hyVar));
        }
        hrVar.b = this.b;
        return hrVar;
    }

    public final long f() {
        if (this.b < 8) {
            throw new IllegalStateException("size < 8: " + this.b);
        }
        long d;
        hy hyVar = this.a;
        int i = hyVar.b;
        int i2 = hyVar.c;
        if (i2 - i < 8) {
            d = ((((long) d()) & 4294967295L) << 32) | (((long) d()) & 4294967295L);
        } else {
            byte[] bArr = hyVar.a;
            int i3 = i + 1;
            long j = (((long) bArr[i]) & 255) << 56;
            i = i3 + 1;
            long j2 = ((((long) bArr[i3]) & 255) << 48) | j;
            int i4 = i + 1;
            i = i4 + 1;
            j2 = (j2 | ((((long) bArr[i]) & 255) << 40)) | ((((long) bArr[i4]) & 255) << 32);
            i4 = i + 1;
            i = i4 + 1;
            j2 = (j2 | ((((long) bArr[i]) & 255) << 24)) | ((((long) bArr[i4]) & 255) << 16);
            i4 = i + 1;
            int i5 = i4 + 1;
            d = (((long) bArr[i4]) & 255) | (j2 | ((((long) bArr[i]) & 255) << 8));
            this.b -= 8;
            if (i5 == i2) {
                this.a = hyVar.a();
                hz.a(hyVar);
            } else {
                hyVar.b = i5;
            }
        }
        return ie.a(d);
    }

    public final String toString() {
        if (this.b > 2147483647L) {
            throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.b);
        }
        hu huVar;
        int i = (int) this.b;
        if (i == 0) {
            huVar = hu.b;
        } else {
            huVar = new ia(this, i);
        }
        return huVar.toString();
    }
}
