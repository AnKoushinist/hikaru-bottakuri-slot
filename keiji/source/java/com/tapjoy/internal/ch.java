package com.tapjoy.internal;

public final class ch implements Runnable {
    private final cf a;
    private final ck b;

    protected ch(cf cfVar, ck ckVar) {
        this.a = cfVar;
        this.b = ckVar;
    }

    public final void run() {
        try {
            Object f = this.a.f();
            if (this.b != null && !(this.b instanceof cl)) {
                this.b.a(this.a, f);
            }
        } catch (Throwable th) {
            if (this.b != null && !(this.b instanceof cl)) {
                this.b.a(this.a);
            }
        }
    }
}
