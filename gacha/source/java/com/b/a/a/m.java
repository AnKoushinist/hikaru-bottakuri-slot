package com.b.a.a;

import b.a.a.a.a.c.a.e;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;

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
        return j - this.a >= RFLConstants.UPDATE_INTERVAL_IN_MILLISECONDS_ON_STAY * this.b.a();
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
