package com.tapjoy.internal;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import jp.co.vaz.creator.hikaru2.R;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.TwitterResponse;

public final class hf {
    private final byte[] a = new byte[256];
    private ByteBuffer b;
    private he c;
    private int d = 0;

    public final hf a(byte[] bArr) {
        if (bArr != null) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            this.b = null;
            Arrays.fill(this.a, (byte) 0);
            this.c = new he();
            this.d = 0;
            this.b = wrap.asReadOnlyBuffer();
            this.b.position(0);
            this.b.order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.b = null;
            this.c.b = 2;
        }
        return this;
    }

    public final he a() {
        if (this.b == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        } else if (h()) {
            return this.c;
        } else {
            d();
            if (!h()) {
                b();
                if (this.c.c < 0) {
                    this.c.b = 1;
                }
            }
            return this.c;
        }
    }

    private void b() {
        int i = 0;
        while (i == 0 && !h() && this.c.c <= Integer.MAX_VALUE) {
            int g;
            switch (g()) {
                case R.styleable.AppCompatTheme_actionModeCopyDrawable /*33*/:
                    switch (g()) {
                        case TwitterResponse.READ /*1*/:
                            e();
                            break;
                        case 249:
                            boolean z;
                            this.c.d = new hd();
                            g();
                            g = g();
                            this.c.d.g = (g & 28) >> 2;
                            if (this.c.d.g == 0) {
                                this.c.d.g = 1;
                            }
                            hd hdVar = this.c.d;
                            if ((g & 1) != 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            hdVar.f = z;
                            g = this.b.getShort();
                            if (g < 2) {
                                g = 10;
                            }
                            this.c.d.i = g * 10;
                            this.c.d.h = g();
                            g();
                            break;
                        case 254:
                            e();
                            break;
                        case 255:
                            f();
                            String str = BuildConfig.FLAVOR;
                            for (g = 0; g < 11; g++) {
                                str = str + ((char) this.a[g]);
                            }
                            if (!str.equals("NETSCAPE2.0")) {
                                e();
                                break;
                            } else {
                                c();
                                break;
                            }
                        default:
                            e();
                            break;
                    }
                case R.styleable.AppCompatTheme_dialogPreferredPadding /*44*/:
                    boolean z2;
                    if (this.c.d == null) {
                        this.c.d = new hd();
                    }
                    this.c.d.a = this.b.getShort();
                    this.c.d.b = this.b.getShort();
                    this.c.d.c = this.b.getShort();
                    this.c.d.d = this.b.getShort();
                    int g2 = g();
                    g = (g2 & 128) != 0 ? 1 : 0;
                    int pow = (int) Math.pow(2.0d, (double) ((g2 & 7) + 1));
                    hd hdVar2 = this.c.d;
                    if ((g2 & 64) != 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    hdVar2.e = z2;
                    if (g != 0) {
                        this.c.d.k = a(pow);
                    } else {
                        this.c.d.k = null;
                    }
                    this.c.d.j = this.b.position();
                    g();
                    e();
                    if (!h()) {
                        he heVar = this.c;
                        heVar.c++;
                        this.c.e.add(this.c.d);
                        break;
                    }
                    break;
                case R.styleable.AppCompatTheme_toolbarStyle /*59*/:
                    i = 1;
                    break;
                default:
                    this.c.b = 1;
                    break;
            }
        }
    }

    private void c() {
        do {
            f();
            if (this.a[0] == (byte) 1) {
                this.c.m = (this.a[1] & 255) | ((this.a[2] & 255) << 8);
                if (this.c.m == 0) {
                    this.c.m = -1;
                }
            }
            if (this.d <= 0) {
                return;
            }
        } while (!h());
    }

    private void d() {
        int i;
        boolean z = true;
        String str = BuildConfig.FLAVOR;
        for (i = 0; i < 6; i++) {
            str = str + ((char) g());
        }
        if (str.startsWith("GIF")) {
            this.c.f = this.b.getShort();
            this.c.g = this.b.getShort();
            i = g();
            he heVar = this.c;
            if ((i & 128) == 0) {
                z = false;
            }
            heVar.h = z;
            this.c.i = 2 << (i & 7);
            this.c.j = g();
            this.c.k = g();
            if (this.c.h && !h()) {
                this.c.a = a(this.c.i);
                this.c.l = this.c.a[this.c.j];
                return;
            }
            return;
        }
        this.c.b = 1;
    }

    private int[] a(int i) {
        int[] iArr;
        BufferUnderflowException e;
        byte[] bArr = new byte[(i * 3)];
        try {
            this.b.get(bArr);
            iArr = new int[256];
            int i2 = 0;
            int i3 = 0;
            while (i3 < i) {
                int i4 = i2 + 1;
                try {
                    int i5 = bArr[i2] & 255;
                    int i6 = i4 + 1;
                    int i7 = bArr[i4] & 255;
                    i2 = i6 + 1;
                    i4 = i3 + 1;
                    iArr[i3] = (((i5 << 16) | -16777216) | (i7 << 8)) | (bArr[i6] & 255);
                    i3 = i4;
                } catch (BufferUnderflowException e2) {
                    e = e2;
                }
            }
        } catch (BufferUnderflowException e3) {
            BufferUnderflowException bufferUnderflowException = e3;
            iArr = null;
            e = bufferUnderflowException;
            new Object[1][0] = e;
            this.c.b = 1;
            return iArr;
        }
        return iArr;
    }

    private void e() {
        int g;
        do {
            try {
                g = g();
                this.b.position(this.b.position() + g);
            } catch (IllegalArgumentException e) {
                return;
            }
        } while (g > 0);
    }

    private int f() {
        this.d = g();
        if (this.d <= 0) {
            return 0;
        }
        int i = 0;
        int i2 = 0;
        while (i2 < this.d) {
            try {
                i = this.d - i2;
                this.b.get(this.a, i2, i);
                i2 += i;
            } catch (Exception e) {
                Object[] objArr = new Object[]{Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(this.d), e};
                this.c.b = 1;
                return i2;
            }
        }
        return i2;
    }

    private int g() {
        int i = 0;
        try {
            return this.b.get() & 255;
        } catch (Exception e) {
            this.c.b = 1;
            return i;
        }
    }

    private boolean h() {
        return this.c.b != 0;
    }
}
