package com.tapjoy.internal;

public final class dp {
    final hs a;

    static int a(int i, dk dkVar) {
        return (i << 3) | dkVar.e;
    }

    static int a(int i) {
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

    static int a(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        if ((Long.MIN_VALUE & j) == 0) {
            return 9;
        }
        return 10;
    }

    static int b(int i) {
        return (i << 1) ^ (i >> 31);
    }

    static long b(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public dp(hs hsVar) {
        this.a = hsVar;
    }

    public final void a(hu huVar) {
        this.a.b(huVar);
    }

    public final void c(int i) {
        while ((i & -128) != 0) {
            this.a.e((i & 127) | 128);
            i >>>= 7;
        }
        this.a.e(i);
    }

    public final void c(long j) {
        while ((-128 & j) != 0) {
            this.a.e((((int) j) & 127) | 128);
            j >>>= 7;
        }
        this.a.e((int) j);
    }

    public final void d(int i) {
        this.a.d(i);
    }

    public final void d(long j) {
        this.a.f(j);
    }
}
