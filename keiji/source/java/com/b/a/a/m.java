package com.b.a.a;

import a.a.a.a.a.c.a.e;

/* compiled from: RetryManager */
class m {
    long a;
    private e b;

    public m(e eVar) {
        if (eVar == null) {
            throw new NullPointerException("retryState must not be null");
        }
        this.b = eVar;
    }

    public boolean a(long j) {
        return j - this.a >= 1000000 * this.b.a();
    }

    public void b(long j) {
        this.a = j;
        this.b = this.b.b();
    }

    public void a() {
        this.a = 0;
        this.b = this.b.c();
    }
}
