package com.tapjoy.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public class hc {
    private static final String d = hc.class.getSimpleName();
    int a;
    int b;
    he c;
    private int[] e;
    private final int[] f;
    private ByteBuffer g;
    private byte[] h;
    private byte[] i;
    private int j;
    private int k;
    private hf l;
    private short[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;
    private int[] q;
    private a r;
    private Bitmap s;
    private boolean t;
    private int u;
    private int v;
    private int w;
    private int x;
    private boolean y;

    interface a {
        Bitmap a(int i, int i2, Config config);

        byte[] a(int i);

        int[] b(int i);
    }

    hc(a aVar, he heVar, ByteBuffer byteBuffer) {
        this(aVar, heVar, byteBuffer, (byte) 0);
    }

    private hc(a aVar, he heVar, ByteBuffer byteBuffer, byte b) {
        this(aVar);
        b(heVar, byteBuffer);
    }

    private hc(a aVar) {
        this.f = new int[256];
        this.j = 0;
        this.k = 0;
        this.r = aVar;
        this.c = new he();
    }

    hc() {
        this(new hh());
    }

    final synchronized Bitmap a() {
        Bitmap bitmap;
        if (this.c.c <= 0 || this.a < 0) {
            Object[] objArr = new Object[]{Integer.valueOf(this.c.c), Integer.valueOf(this.a)};
            this.u = 1;
        }
        if (this.u == 1 || this.u == 2) {
            new Object[1][0] = Integer.valueOf(this.u);
            bitmap = null;
        } else {
            hd hdVar;
            this.u = 0;
            hd hdVar2 = (hd) this.c.e.get(this.a);
            int i = this.a - 1;
            if (i >= 0) {
                hdVar = (hd) this.c.e.get(i);
            } else {
                hdVar = null;
            }
            this.e = hdVar2.k != null ? hdVar2.k : this.c.a;
            if (this.e == null) {
                new Object[1][0] = Integer.valueOf(this.a);
                this.u = 1;
                bitmap = null;
            } else {
                int i2;
                int i3;
                int i4;
                int i5;
                int i6;
                int i7;
                if (hdVar2.f) {
                    System.arraycopy(this.e, 0, this.f, 0, this.e.length);
                    this.e = this.f;
                    this.e[hdVar2.h] = 0;
                }
                int[] iArr = this.q;
                if (hdVar == null) {
                    Arrays.fill(iArr, 0);
                }
                if (hdVar != null && hdVar.g > 0) {
                    if (hdVar.g == 2) {
                        i2 = 0;
                        if (!hdVar2.f) {
                            i2 = this.c.l;
                            if (hdVar2.k != null && this.c.j == hdVar2.h) {
                                i2 = 0;
                            }
                        } else if (this.a == 0) {
                            this.y = true;
                        }
                        a(iArr, hdVar, i2);
                    } else if (hdVar.g == 3) {
                        if (this.s == null) {
                            a(iArr, hdVar, 0);
                        } else {
                            i3 = hdVar.b / this.v;
                            i4 = hdVar.a / this.v;
                            this.s.getPixels(iArr, (this.x * i3) + i4, this.x, i4, i3, hdVar.c / this.v, hdVar.d / this.v);
                        }
                    }
                }
                this.j = 0;
                this.k = 0;
                if (hdVar2 != null) {
                    this.g.position(hdVar2.j);
                }
                if (hdVar2 == null) {
                    i5 = this.c.f * this.c.g;
                } else {
                    i5 = hdVar2.c * hdVar2.d;
                }
                if (this.p == null || this.p.length < i5) {
                    this.p = this.r.a(i5);
                }
                if (this.m == null) {
                    this.m = new short[4096];
                }
                if (this.n == null) {
                    this.n = new byte[4096];
                }
                if (this.o == null) {
                    this.o = new byte[4097];
                }
                int c = c();
                int i8 = 1 << c;
                int i9 = i8 + 1;
                int i10 = i8 + 2;
                int i11 = -1;
                i2 = c + 1;
                int i12 = (1 << i2) - 1;
                for (i4 = 0; i4 < i8; i4++) {
                    this.m[i4] = (short) 0;
                    this.n[i4] = (byte) i4;
                }
                int i13 = 0;
                int i14 = 0;
                int i15 = 0;
                int i16 = 0;
                int i17 = 0;
                i3 = i12;
                int i18 = i10;
                i12 = 0;
                i10 = 0;
                i4 = i2;
                i2 = 0;
                while (i13 < i5) {
                    if (i10 == 0) {
                        i10 = d();
                        if (i10 <= 0) {
                            this.u = 3;
                            break;
                        }
                        i6 = 0;
                        i7 = i10;
                    } else {
                        i6 = i12;
                        i7 = i10;
                    }
                    i12 = ((this.h[i6] & 255) << i17) + i16;
                    i10 = i17 + 8;
                    i16 = i6 + 1;
                    i17 = i7 - 1;
                    i7 = i10;
                    i10 = i18;
                    i18 = i15;
                    int i19 = i2;
                    i2 = i4;
                    i4 = i19;
                    int i20 = i3;
                    i3 = i12;
                    i12 = i20;
                    while (i7 >= i2) {
                        i15 = i3 & i12;
                        i6 = i3 >> i2;
                        i7 -= i2;
                        if (i15 != i8) {
                            if (i15 <= i10) {
                                if (i15 == i9) {
                                    i15 = i18;
                                    i3 = i12;
                                    i12 = i16;
                                    i18 = i10;
                                    i10 = i17;
                                    i16 = i6;
                                    i17 = i7;
                                    i19 = i4;
                                    i4 = i2;
                                    i2 = i19;
                                    break;
                                } else if (i11 == -1) {
                                    i3 = i14 + 1;
                                    this.o[i14] = this.n[i15];
                                    i14 = i3;
                                    i18 = i15;
                                    i11 = i15;
                                    i3 = i6;
                                } else {
                                    if (i15 >= i10) {
                                        i3 = i14 + 1;
                                        this.o[i14] = (byte) i18;
                                        i14 = i3;
                                        i18 = i11;
                                    } else {
                                        i18 = i15;
                                    }
                                    while (i18 >= i8) {
                                        i3 = i14 + 1;
                                        this.o[i14] = this.n[i18];
                                        short s = this.m[i18];
                                        i14 = i3;
                                    }
                                    i18 = this.n[i18] & 255;
                                    i3 = i14 + 1;
                                    this.o[i14] = (byte) i18;
                                    if (i10 < 4096) {
                                        this.m[i10] = (short) i11;
                                        this.n[i10] = (byte) i18;
                                        i10++;
                                        if ((i10 & i12) == 0 && i10 < 4096) {
                                            i2++;
                                            i12 += i10;
                                        }
                                    }
                                    i14 = i13;
                                    i13 = i3;
                                    while (i13 > 0) {
                                        i3 = i4 + 1;
                                        i13--;
                                        this.p[i4] = this.o[i13];
                                        i14++;
                                        i4 = i3;
                                    }
                                    i3 = i6;
                                    i11 = i15;
                                    i19 = i14;
                                    i14 = i13;
                                    i13 = i19;
                                }
                            } else {
                                this.u = 3;
                                i15 = i18;
                                i3 = i12;
                                i12 = i16;
                                i18 = i10;
                                i10 = i17;
                                i16 = i6;
                                i17 = i7;
                                i19 = i4;
                                i4 = i2;
                                i2 = i19;
                                break;
                            }
                        }
                        i2 = c + 1;
                        i12 = (1 << i2) - 1;
                        i10 = i8 + 2;
                        i3 = i6;
                        i11 = -1;
                    }
                    i15 = i18;
                    i18 = i10;
                    i10 = i17;
                    i17 = i7;
                    i19 = i4;
                    i4 = i2;
                    i2 = i19;
                    i20 = i3;
                    i3 = i12;
                    i12 = i16;
                    i16 = i20;
                }
                while (i2 < i5) {
                    this.p[i2] = (byte) 0;
                    i2++;
                }
                i5 = hdVar2.d / this.v;
                c = hdVar2.b / this.v;
                i8 = hdVar2.c / this.v;
                i9 = hdVar2.a / this.v;
                i4 = 1;
                i10 = 8;
                i12 = 0;
                Object obj = this.a == 0 ? 1 : null;
                i18 = 0;
                while (i18 < i5) {
                    if (hdVar2.e) {
                        if (i12 >= i5) {
                            i4++;
                            switch (i4) {
                                case TwitterResponse.READ_WRITE /*2*/:
                                    i12 = 4;
                                    break;
                                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                                    i12 = 2;
                                    i10 = 4;
                                    break;
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                                    i12 = 1;
                                    i10 = 2;
                                    break;
                            }
                        }
                        i11 = i12 + i10;
                        i15 = i10;
                        i6 = i4;
                    } else {
                        i11 = i12;
                        i15 = i10;
                        i6 = i4;
                        i12 = i18;
                    }
                    i12 += c;
                    if (i12 < this.w) {
                        i4 = i12 * this.x;
                        i10 = i4 + i9;
                        i12 = i10 + i8;
                        if (this.x + i4 < i12) {
                            i14 = this.x + i4;
                        } else {
                            i14 = i12;
                        }
                        i12 = (this.v * i18) * hdVar2.c;
                        int i21 = i12 + ((i14 - i10) * this.v);
                        i7 = i12;
                        for (i16 = i10; i16 < i14; i16++) {
                            if (this.v == 1) {
                                i12 = this.e[this.p[i7] & 255];
                            } else {
                                int i22;
                                int i23 = hdVar2.c;
                                i13 = 0;
                                i3 = 0;
                                i4 = 0;
                                i10 = 0;
                                i12 = 0;
                                i17 = i7;
                                while (i17 < this.v + i7 && i17 < this.p.length && i17 < i21) {
                                    i22 = this.e[this.p[i17] & 255];
                                    if (i22 != 0) {
                                        i13 += (i22 >> 24) & 255;
                                        i3 += (i22 >> 16) & 255;
                                        i4 += (i22 >> 8) & 255;
                                        i10 += i22 & 255;
                                        i12++;
                                    }
                                    i17++;
                                }
                                i17 = i7 + i23;
                                while (i17 < (i7 + i23) + this.v && i17 < this.p.length && i17 < i21) {
                                    i22 = this.e[this.p[i17] & 255];
                                    if (i22 != 0) {
                                        i13 += (i22 >> 24) & 255;
                                        i3 += (i22 >> 16) & 255;
                                        i4 += (i22 >> 8) & 255;
                                        i10 += i22 & 255;
                                        i12++;
                                    }
                                    i17++;
                                }
                                if (i12 == 0) {
                                    i12 = 0;
                                } else {
                                    i12 = (i10 / i12) | (((i4 / i12) << 8) | (((i3 / i12) << 16) | ((i13 / i12) << 24)));
                                }
                            }
                            if (i12 != 0) {
                                iArr[i16] = i12;
                            } else if (!(this.y || obj == null)) {
                                this.y = true;
                            }
                            i7 = this.v + i7;
                        }
                    }
                    i18++;
                    i12 = i11;
                    i10 = i15;
                    i4 = i6;
                }
                if (this.t && (hdVar2.g == 0 || hdVar2.g == 1)) {
                    if (this.s == null) {
                        this.s = e();
                    }
                    this.s.setPixels(iArr, 0, this.x, 0, 0, this.x, this.w);
                }
                bitmap = e();
                bitmap.setPixels(iArr, 0, this.x, 0, 0, this.x, this.w);
            }
        }
        return bitmap;
    }

    private synchronized void a(he heVar, byte[] bArr) {
        a(heVar, ByteBuffer.wrap(bArr));
    }

    private synchronized void a(he heVar, ByteBuffer byteBuffer) {
        b(heVar, byteBuffer);
    }

    private synchronized void b(he heVar, ByteBuffer byteBuffer) {
        int highestOneBit = Integer.highestOneBit(1);
        this.u = 0;
        this.c = heVar;
        this.y = false;
        this.a = -1;
        this.b = 0;
        this.g = byteBuffer.asReadOnlyBuffer();
        this.g.position(0);
        this.g.order(ByteOrder.LITTLE_ENDIAN);
        this.t = false;
        for (hd hdVar : heVar.e) {
            if (hdVar.g == 3) {
                this.t = true;
                break;
            }
        }
        this.v = highestOneBit;
        this.x = heVar.f / highestOneBit;
        this.w = heVar.g / highestOneBit;
        this.p = this.r.a(heVar.f * heVar.g);
        this.q = this.r.b(this.x * this.w);
    }

    final synchronized int a(byte[] bArr) {
        if (this.l == null) {
            this.l = new hf();
        }
        this.c = this.l.a(bArr).a();
        if (bArr != null) {
            a(this.c, bArr);
        }
        return this.u;
    }

    private void a(int[] iArr, hd hdVar, int i) {
        int i2 = hdVar.c / this.v;
        int i3 = hdVar.a / this.v;
        int i4 = ((hdVar.b / this.v) * this.x) + i3;
        i3 = i4 + ((hdVar.d / this.v) * this.x);
        while (i4 < i3) {
            int i5 = i4 + i2;
            for (int i6 = i4; i6 < i5; i6++) {
                iArr[i6] = i;
            }
            i4 += this.x;
        }
    }

    private void b() {
        if (this.j <= this.k) {
            if (this.i == null) {
                this.i = this.r.a(16384);
            }
            this.k = 0;
            this.j = Math.min(this.g.remaining(), 16384);
            this.g.get(this.i, 0, this.j);
        }
    }

    private int c() {
        try {
            b();
            byte[] bArr = this.i;
            int i = this.k;
            this.k = i + 1;
            return bArr[i] & 255;
        } catch (Exception e) {
            this.u = 1;
            return 0;
        }
    }

    private int d() {
        int c = c();
        if (c > 0) {
            try {
                if (this.h == null) {
                    this.h = this.r.a(255);
                }
                int i = this.j - this.k;
                if (i >= c) {
                    System.arraycopy(this.i, this.k, this.h, 0, c);
                    this.k += c;
                } else if (this.g.remaining() + i >= c) {
                    System.arraycopy(this.i, this.k, this.h, 0, i);
                    this.k = this.j;
                    b();
                    int i2 = c - i;
                    System.arraycopy(this.i, 0, this.h, i, i2);
                    this.k += i2;
                } else {
                    this.u = 1;
                }
            } catch (Exception e) {
                new Object[1][0] = e;
                this.u = 1;
            }
        }
        return c;
    }

    private Bitmap e() {
        Bitmap a = this.r.a(this.x, this.w, this.y ? Config.ARGB_4444 : Config.RGB_565);
        if (VERSION.SDK_INT >= 12) {
            a.setHasAlpha(true);
        }
        return a;
    }
}
