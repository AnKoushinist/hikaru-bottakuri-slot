package com.tapjoy.internal;

final class hy {
    final byte[] a;
    int b;
    int c;
    boolean d;
    boolean e;
    hy f;
    hy g;

    hy() {
        this.a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    hy(hy hyVar) {
        this(hyVar.a, hyVar.b, hyVar.c);
        hyVar.d = true;
    }

    hy(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.e = false;
        this.d = true;
    }

    public final hy a() {
        hy hyVar = this.f != this ? this.f : null;
        this.g.f = this.f;
        this.f.g = this.g;
        this.f = null;
        this.g = null;
        return hyVar;
    }

    public final hy a(hy hyVar) {
        hyVar.g = this;
        hyVar.f = this.f;
        this.f.g = hyVar;
        this.f = hyVar;
        return hyVar;
    }

    public final void a(hy hyVar, int i) {
        if (hyVar.e) {
            if (hyVar.c + i > 8192) {
                if (hyVar.d) {
                    throw new IllegalArgumentException();
                } else if ((hyVar.c + i) - hyVar.b > 8192) {
                    throw new IllegalArgumentException();
                } else {
                    System.arraycopy(hyVar.a, hyVar.b, hyVar.a, 0, hyVar.c - hyVar.b);
                    hyVar.c -= hyVar.b;
                    hyVar.b = 0;
                }
            }
            System.arraycopy(this.a, this.b, hyVar.a, hyVar.c, i);
            hyVar.c += i;
            this.b += i;
            return;
        }
        throw new IllegalArgumentException();
    }
}
