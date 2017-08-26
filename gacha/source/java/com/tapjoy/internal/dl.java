package com.tapjoy.internal;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import jp.co.vaz.creator.hikaru.R;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

public final class dl {
    private final byte[] a;
    private final boolean b;
    private int c;
    private int d;
    private int e;
    private final InputStream f;
    private int g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private a n;

    interface a {
    }

    public static dl a(byte[] bArr, int i) {
        dl dlVar = new dl(bArr, i);
        try {
            dlVar.b(i);
            return dlVar;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public final int a() {
        int i = 1;
        if (this.e != this.c || d(1)) {
            i = 0;
        }
        if (i != 0) {
            this.g = 0;
            return 0;
        }
        this.g = e();
        if (ec.b(this.g) != 0) {
            return this.g;
        }
        throw dq.d();
    }

    public final void a(int i) {
        if (this.g != i) {
            throw dq.e();
        }
    }

    public final boolean a(int i, dm dmVar) {
        long f;
        int a;
        switch (ec.a(i)) {
            case TwitterResponse.NONE /*0*/:
                f = f();
                dmVar.e(i);
                dmVar.a(f);
                return true;
            case TwitterResponse.READ /*1*/:
                f = g();
                dmVar.e(i);
                dmVar.b(f);
                return true;
            case TwitterResponse.READ_WRITE /*2*/:
                dk d = d();
                dmVar.e(i);
                dmVar.a(d);
                return true;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                dmVar.e(i);
                do {
                    a = a();
                    if (a != 0) {
                    }
                    a = ec.a(ec.b(i), 4);
                    a(a);
                    dmVar.e(a);
                    return true;
                } while (a(a, dmVar));
                a = ec.a(ec.b(i), 4);
                a(a);
                dmVar.e(a);
                return true;
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                return false;
            case AdInfo.BANNER_KIND_BANNER5 /*5*/:
                a = this.e;
                if (this.c - a < 4) {
                    c(4);
                    a = this.e;
                }
                byte[] bArr = this.a;
                this.e = a + 4;
                a = ((bArr[a + 3] & 255) << 24) | (((bArr[a] & 255) | ((bArr[a + 1] & 255) << 8)) | ((bArr[a + 2] & 255) << 16));
                dmVar.e(i);
                dmVar.c(a & 255);
                dmVar.c((a >> 8) & 255);
                dmVar.c((a >> 16) & 255);
                dmVar.c((a >> 24) & 255);
                return true;
            default:
                throw dq.f();
        }
    }

    public final long b() {
        return f();
    }

    public final boolean c() {
        return f() != 0;
    }

    public final du a(dw dwVar, dn dnVar) {
        int e = e();
        if (this.k >= this.l) {
            throw dq.g();
        }
        int b = b(e);
        this.k++;
        du duVar = (du) dwVar.a(this, dnVar);
        a(0);
        this.k--;
        this.j = b;
        i();
        return duVar;
    }

    public final dk d() {
        int e = e();
        if (e <= this.c - this.e && e > 0) {
            dk djVar = (this.b && this.h) ? new dj(this.a, this.e, e) : dk.a(this.a, this.e, e);
            this.e = e + this.e;
            return djVar;
        } else if (e == 0) {
            return dk.b;
        } else {
            return new dt(e(e));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int e() {
        /*
        r8 = this;
        r6 = 0;
        r0 = r8.e;
        r1 = r8.c;
        if (r1 == r0) goto L_0x0081;
    L_0x0008:
        r3 = r8.a;
        r2 = r0 + 1;
        r0 = r3[r0];
        if (r0 < 0) goto L_0x0013;
    L_0x0010:
        r8.e = r2;
    L_0x0012:
        return r0;
    L_0x0013:
        r1 = r8.c;
        r1 = r1 - r2;
        r4 = 9;
        if (r1 < r4) goto L_0x0081;
    L_0x001a:
        r1 = r2 + 1;
        r2 = r3[r2];
        r2 = r2 << 7;
        r0 = r0 ^ r2;
        r4 = (long) r0;
        r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r2 >= 0) goto L_0x002e;
    L_0x0026:
        r2 = (long) r0;
        r4 = -128; // 0xffffffffffffff80 float:NaN double:NaN;
        r2 = r2 ^ r4;
        r0 = (int) r2;
    L_0x002b:
        r8.e = r1;
        goto L_0x0012;
    L_0x002e:
        r2 = r1 + 1;
        r1 = r3[r1];
        r1 = r1 << 14;
        r0 = r0 ^ r1;
        r4 = (long) r0;
        r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r1 < 0) goto L_0x0041;
    L_0x003a:
        r0 = (long) r0;
        r4 = 16256; // 0x3f80 float:2.278E-41 double:8.0315E-320;
        r0 = r0 ^ r4;
        r0 = (int) r0;
        r1 = r2;
        goto L_0x002b;
    L_0x0041:
        r1 = r2 + 1;
        r2 = r3[r2];
        r2 = r2 << 21;
        r0 = r0 ^ r2;
        r4 = (long) r0;
        r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r2 >= 0) goto L_0x0054;
    L_0x004d:
        r2 = (long) r0;
        r4 = -2080896; // 0xffffffffffe03f80 float:NaN double:NaN;
        r2 = r2 ^ r4;
        r0 = (int) r2;
        goto L_0x002b;
    L_0x0054:
        r2 = r1 + 1;
        r1 = r3[r1];
        r4 = r1 << 28;
        r0 = r0 ^ r4;
        r4 = (long) r0;
        r6 = 266354560; // 0xfe03f80 float:2.2112565E-29 double:1.315966377E-315;
        r4 = r4 ^ r6;
        r0 = (int) r4;
        if (r1 >= 0) goto L_0x0087;
    L_0x0063:
        r1 = r2 + 1;
        r2 = r3[r2];
        if (r2 >= 0) goto L_0x002b;
    L_0x0069:
        r2 = r1 + 1;
        r1 = r3[r1];
        if (r1 >= 0) goto L_0x0087;
    L_0x006f:
        r1 = r2 + 1;
        r2 = r3[r2];
        if (r2 >= 0) goto L_0x002b;
    L_0x0075:
        r2 = r1 + 1;
        r1 = r3[r1];
        if (r1 >= 0) goto L_0x0087;
    L_0x007b:
        r1 = r2 + 1;
        r2 = r3[r2];
        if (r2 >= 0) goto L_0x002b;
    L_0x0081:
        r0 = r8.h();
        r0 = (int) r0;
        goto L_0x0012;
    L_0x0087:
        r1 = r2;
        goto L_0x002b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.dl.e():int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long f() {
        /*
        r10 = this;
        r8 = 0;
        r0 = r10.e;
        r1 = r10.c;
        if (r1 == r0) goto L_0x00bb;
    L_0x0008:
        r4 = r10.a;
        r1 = r0 + 1;
        r0 = r4[r0];
        if (r0 < 0) goto L_0x0014;
    L_0x0010:
        r10.e = r1;
        r0 = (long) r0;
    L_0x0013:
        return r0;
    L_0x0014:
        r2 = r10.c;
        r2 = r2 - r1;
        r3 = 9;
        if (r2 < r3) goto L_0x00bb;
    L_0x001b:
        r2 = r1 + 1;
        r1 = r4[r1];
        r1 = r1 << 7;
        r0 = r0 ^ r1;
        r0 = (long) r0;
        r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r3 >= 0) goto L_0x002d;
    L_0x0027:
        r4 = -128; // 0xffffffffffffff80 float:NaN double:NaN;
        r0 = r0 ^ r4;
    L_0x002a:
        r10.e = r2;
        goto L_0x0013;
    L_0x002d:
        r3 = r2 + 1;
        r2 = r4[r2];
        r2 = r2 << 14;
        r6 = (long) r2;
        r0 = r0 ^ r6;
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 < 0) goto L_0x003e;
    L_0x0039:
        r4 = 16256; // 0x3f80 float:2.278E-41 double:8.0315E-320;
        r0 = r0 ^ r4;
        r2 = r3;
        goto L_0x002a;
    L_0x003e:
        r2 = r3 + 1;
        r3 = r4[r3];
        r3 = r3 << 21;
        r6 = (long) r3;
        r0 = r0 ^ r6;
        r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r3 >= 0) goto L_0x004f;
    L_0x004a:
        r4 = -2080896; // 0xffffffffffe03f80 float:NaN double:NaN;
        r0 = r0 ^ r4;
        goto L_0x002a;
    L_0x004f:
        r3 = r2 + 1;
        r2 = r4[r2];
        r6 = (long) r2;
        r2 = 28;
        r6 = r6 << r2;
        r0 = r0 ^ r6;
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 < 0) goto L_0x0062;
    L_0x005c:
        r4 = 266354560; // 0xfe03f80 float:2.2112565E-29 double:1.315966377E-315;
        r0 = r0 ^ r4;
        r2 = r3;
        goto L_0x002a;
    L_0x0062:
        r2 = r3 + 1;
        r3 = r4[r3];
        r6 = (long) r3;
        r3 = 35;
        r6 = r6 << r3;
        r0 = r0 ^ r6;
        r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r3 >= 0) goto L_0x0076;
    L_0x006f:
        r4 = -34093383808; // 0xfffffff80fe03f80 float:2.2112565E-29 double:NaN;
        r0 = r0 ^ r4;
        goto L_0x002a;
    L_0x0076:
        r3 = r2 + 1;
        r2 = r4[r2];
        r6 = (long) r2;
        r2 = 42;
        r6 = r6 << r2;
        r0 = r0 ^ r6;
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 < 0) goto L_0x008b;
    L_0x0083:
        r4 = 4363953127296; // 0x3f80fe03f80 float:2.2112565E-29 double:2.1560793202584E-311;
        r0 = r0 ^ r4;
        r2 = r3;
        goto L_0x002a;
    L_0x008b:
        r2 = r3 + 1;
        r3 = r4[r3];
        r6 = (long) r3;
        r3 = 49;
        r6 = r6 << r3;
        r0 = r0 ^ r6;
        r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r3 >= 0) goto L_0x009f;
    L_0x0098:
        r4 = -558586000294016; // 0xfffe03f80fe03f80 float:2.2112565E-29 double:NaN;
        r0 = r0 ^ r4;
        goto L_0x002a;
    L_0x009f:
        r3 = r2 + 1;
        r2 = r4[r2];
        r6 = (long) r2;
        r2 = 56;
        r6 = r6 << r2;
        r0 = r0 ^ r6;
        r6 = 71499008037633920; // 0xfe03f80fe03f80 float:2.2112565E-29 double:6.838959413692434E-304;
        r0 = r0 ^ r6;
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 >= 0) goto L_0x00c1;
    L_0x00b2:
        r2 = r3 + 1;
        r3 = r4[r3];
        r4 = (long) r3;
        r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r3 >= 0) goto L_0x002a;
    L_0x00bb:
        r0 = r10.h();
        goto L_0x0013;
    L_0x00c1:
        r2 = r3;
        goto L_0x002a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.dl.f():long");
    }

    private long h() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            if (this.e == this.c) {
                c(1);
            }
            byte[] bArr = this.a;
            int i2 = this.e;
            this.e = i2 + 1;
            byte b = bArr[i2];
            j |= ((long) (b & 127)) << i;
            if ((b & 128) == 0) {
                return j;
            }
        }
        throw dq.c();
    }

    public final long g() {
        int i = this.e;
        if (this.c - i < 8) {
            c(8);
            i = this.e;
        }
        byte[] bArr = this.a;
        this.e = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48));
    }

    private dl(byte[] bArr, int i) {
        this.h = false;
        this.j = Integer.MAX_VALUE;
        this.l = 64;
        this.m = 67108864;
        this.n = null;
        this.a = bArr;
        this.c = i + 0;
        this.e = 0;
        this.i = 0;
        this.f = null;
        this.b = false;
    }

    dl(InputStream inputStream) {
        this.h = false;
        this.j = Integer.MAX_VALUE;
        this.l = 64;
        this.m = 67108864;
        this.n = null;
        this.a = new byte[4096];
        this.c = 0;
        this.e = 0;
        this.i = 0;
        this.f = inputStream;
        this.b = false;
    }

    private int b(int i) {
        if (i < 0) {
            throw dq.b();
        }
        int i2 = (this.i + this.e) + i;
        int i3 = this.j;
        if (i2 > i3) {
            throw dq.a();
        }
        this.j = i2;
        i();
        return i3;
    }

    private void i() {
        this.c += this.d;
        int i = this.i + this.c;
        if (i > this.j) {
            this.d = i - this.j;
            this.c -= this.d;
            return;
        }
        this.d = 0;
    }

    private void c(int i) {
        if (!d(i)) {
            throw dq.a();
        }
    }

    private boolean d(int i) {
        while (this.e + i > this.c) {
            if ((this.i + this.e) + i > this.j || this.f == null) {
                return false;
            }
            int i2 = this.e;
            if (i2 > 0) {
                if (this.c > i2) {
                    System.arraycopy(this.a, i2, this.a, 0, this.c - i2);
                }
                this.i += i2;
                this.c -= i2;
                this.e = 0;
            }
            i2 = this.f.read(this.a, this.c, this.a.length - this.c);
            if (i2 == 0 || i2 < -1 || i2 > this.a.length) {
                throw new IllegalStateException(new StringBuilder(R.styleable.AppCompatTheme_buttonStyle).append("InputStream#read(byte[]) returned invalid result: ").append(i2).append("\nThe InputStream implementation is buggy.").toString());
            } else if (i2 <= 0) {
                return false;
            } else {
                this.c = i2 + this.c;
                if ((this.i + i) - this.m > 0) {
                    throw dq.h();
                }
                i();
                if (this.c >= i) {
                    return true;
                }
            }
        }
        throw new IllegalStateException("refillBuffer() called when " + i + " bytes were already available in buffer");
    }

    private byte[] e(int i) {
        if (i <= 0) {
            if (i == 0) {
                return dp.a;
            }
            throw dq.b();
        } else if ((this.i + this.e) + i > this.j) {
            f((this.j - this.i) - this.e);
            throw dq.a();
        } else if (i < 4096) {
            r0 = new byte[i];
            r2 = this.c - this.e;
            System.arraycopy(this.a, this.e, r0, 0, r2);
            this.e = this.c;
            int i2 = i - r2;
            if (this.c - this.e < i2) {
                c(i2);
            }
            System.arraycopy(this.a, 0, r0, r2, i - r2);
            this.e = i - r2;
            return r0;
        } else {
            int i3 = this.e;
            int i4 = this.c;
            this.i += this.c;
            this.e = 0;
            this.c = 0;
            int i5 = i - (i4 - i3);
            List<byte[]> arrayList = new ArrayList();
            int i6 = i5;
            while (i6 > 0) {
                Object obj = new byte[Math.min(i6, 4096)];
                i5 = 0;
                while (i5 < obj.length) {
                    r2 = this.f == null ? -1 : this.f.read(obj, i5, obj.length - i5);
                    if (r2 == -1) {
                        throw dq.a();
                    }
                    this.i += r2;
                    i5 += r2;
                }
                i5 = i6 - obj.length;
                arrayList.add(obj);
                i6 = i5;
            }
            Object obj2 = new byte[i];
            i5 = i4 - i3;
            System.arraycopy(this.a, i3, obj2, 0, i5);
            r2 = i5;
            for (byte[] bArr : arrayList) {
                System.arraycopy(bArr, 0, obj2, r2, bArr.length);
                r2 = bArr.length + r2;
            }
            return obj2;
        }
    }

    private void f(int i) {
        if (i <= this.c - this.e && i >= 0) {
            this.e += i;
        } else if (i < 0) {
            throw dq.b();
        } else if ((this.i + this.e) + i > this.j) {
            f((this.j - this.i) - this.e);
            throw dq.a();
        } else {
            int i2 = this.c - this.e;
            this.e = this.c;
            c(1);
            while (i - i2 > this.c) {
                i2 += this.c;
                this.e = this.c;
                c(1);
            }
            this.e = i - i2;
        }
    }
}
