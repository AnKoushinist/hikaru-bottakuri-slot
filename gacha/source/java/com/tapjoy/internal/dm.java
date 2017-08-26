package com.tapjoy.internal;

import java.io.IOException;
import java.io.OutputStream;

public final class dm {
    final int a;
    int b;
    final OutputStream c;
    private final byte[] d;
    private int e;

    public static class a extends IOException {
        a() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    dm(byte[] bArr, int i) {
        this.e = 0;
        this.c = null;
        this.d = bArr;
        this.b = 0;
        this.a = i + 0;
    }

    private dm(OutputStream outputStream, byte[] bArr) {
        this.e = 0;
        this.c = outputStream;
        this.d = bArr;
        this.b = 0;
        this.a = bArr.length;
    }

    public static dm a(OutputStream outputStream, int i) {
        return new dm(outputStream, new byte[i]);
    }

    public final void a(int i, double d) {
        c(i, 1);
        b(Double.doubleToRawLongBits(d));
    }

    public final void a(int i, long j) {
        c(i, 0);
        a(j);
    }

    public final void a(int i, int i2) {
        c(i, 0);
        f(i2);
    }

    public final void a(int i, boolean z) {
        int i2 = 0;
        c(i, 0);
        if (z) {
            i2 = 1;
        }
        c(i2);
    }

    public final void a(int i, du duVar) {
        c(i, 2);
        e(duVar.b());
        duVar.a(this);
    }

    public final void a(int i, dk dkVar) {
        c(i, 2);
        a(dkVar);
    }

    public final void a(int i) {
        c(1, 0);
        f(i);
    }

    private void f(int i) {
        if (i >= 0) {
            e(i);
        } else {
            a((long) i);
        }
    }

    public final void a(dk dkVar) {
        e(dkVar.a());
        c(dkVar);
    }

    public static int b(int i, long j) {
        int i2;
        int d = d(i);
        if ((-128 & j) == 0) {
            i2 = 1;
        } else if ((-16384 & j) == 0) {
            i2 = 2;
        } else if ((-2097152 & j) == 0) {
            i2 = 3;
        } else if ((-268435456 & j) == 0) {
            i2 = 4;
        } else if ((-34359738368L & j) == 0) {
            i2 = 5;
        } else if ((-4398046511104L & j) == 0) {
            i2 = 6;
        } else if ((-562949953421312L & j) == 0) {
            i2 = 7;
        } else if ((-72057594037927936L & j) == 0) {
            i2 = 8;
        } else if ((Long.MIN_VALUE & j) == 0) {
            i2 = 9;
        } else {
            i2 = 10;
        }
        return i2 + d;
    }

    public static int b(int i, int i2) {
        return d(i) + g(i2);
    }

    public static int b(int i, du duVar) {
        int d = d(i);
        int b = duVar.b();
        return d + (b + h(b));
    }

    public static int b(int i, dk dkVar) {
        return d(i) + b(dkVar);
    }

    public static int b(int i) {
        return d(1) + g(i);
    }

    private static int g(int i) {
        if (i >= 0) {
            return h(i);
        }
        return 10;
    }

    public static int b(dk dkVar) {
        return h(dkVar.a()) + dkVar.a();
    }

    private void b() {
        if (this.c == null) {
            throw new a();
        }
        this.c.write(this.d, 0, this.b);
        this.b = 0;
    }

    public final void a() {
        if (this.c != null) {
            b();
        }
    }

    public final void c(int i) {
        byte b = (byte) i;
        if (this.b == this.a) {
            b();
        }
        byte[] bArr = this.d;
        int i2 = this.b;
        this.b = i2 + 1;
        bArr[i2] = b;
        this.e++;
    }

    public final void c(dk dkVar) {
        int a = dkVar.a();
        if (this.a - this.b >= a) {
            dkVar.b(this.d, 0, this.b, a);
            this.b += a;
        } else {
            int i = this.a - this.b;
            dkVar.b(this.d, 0, this.b, i);
            int i2 = i + 0;
            a -= i;
            this.b = this.a;
            this.e = i + this.e;
            b();
            if (a <= this.a) {
                dkVar.b(this.d, i2, 0, a);
                this.b = a;
            } else {
                OutputStream outputStream = this.c;
                if (i2 < 0) {
                    throw new IndexOutOfBoundsException("Source offset < 0: " + i2);
                } else if (a < 0) {
                    throw new IndexOutOfBoundsException("Length < 0: " + a);
                } else if (i2 + a > dkVar.a()) {
                    throw new IndexOutOfBoundsException("Source end offset exceeded: " + (a + i2));
                } else if (a > 0) {
                    dkVar.a(outputStream, i2, a);
                }
            }
        }
        this.e = a + this.e;
    }

    private void c(int i, int i2) {
        e(ec.a(i, i2));
    }

    public static int d(int i) {
        return h(ec.a(i, 0));
    }

    public final void e(int i) {
        while ((i & -128) != 0) {
            c((i & 127) | 128);
            i >>>= 7;
        }
        c(i);
    }

    private static int h(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        if ((-268435456 & i) == 0) {
            return 4;
        }
        return 5;
    }

    public final void a(long j) {
        while ((-128 & j) != 0) {
            c((((int) j) & 127) | 128);
            j >>>= 7;
        }
        c((int) j);
    }

    public final void b(long j) {
        c(((int) j) & 255);
        c(((int) (j >> 8)) & 255);
        c(((int) (j >> 16)) & 255);
        c(((int) (j >> 24)) & 255);
        c(((int) (j >> 32)) & 255);
        c(((int) (j >> 40)) & 255);
        c(((int) (j >> 48)) & 255);
        c(((int) (j >> 56)) & 255);
    }
}
