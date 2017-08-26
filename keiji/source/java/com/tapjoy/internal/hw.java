package com.tapjoy.internal;

final class hw implements hs {
    public final hr a = new hr();
    public final ib b;
    boolean c;

    hw(ib ibVar) {
        if (ibVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.b = ibVar;
    }

    public final void a(hr hrVar, long j) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(hrVar, j);
        b();
    }

    public final hs b(hu huVar) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(huVar);
        return b();
    }

    public final hs b(String str) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(str);
        return b();
    }

    public final hs e(int i) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(i);
        return b();
    }

    public final hs d(int i) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(i);
        return b();
    }

    public final hs f(long j) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.e(j);
        return b();
    }

    private hs b() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        hr hrVar = this.a;
        long j = hrVar.b;
        if (j == 0) {
            j = 0;
        } else {
            hy hyVar = hrVar.a.g;
            if (hyVar.c < 8192 && hyVar.e) {
                j -= (long) (hyVar.c - hyVar.b);
            }
        }
        if (j > 0) {
            this.b.a(this.a, j);
        }
        return this;
    }

    public final hs a() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long j = this.a.b;
        if (j > 0) {
            this.b.a(this.a, j);
        }
        return this;
    }

    public final void flush() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (this.a.b > 0) {
            this.b.a(this.a, this.a.b);
        }
        this.b.flush();
    }

    public final void close() {
        if (!this.c) {
            Throwable th = null;
            try {
                if (this.a.b > 0) {
                    this.b.a(this.a, this.a.b);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.b.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.c = true;
            if (th != null) {
                ie.a(th);
            }
        }
    }

    public final String toString() {
        return "buffer(" + this.b + ")";
    }
}
