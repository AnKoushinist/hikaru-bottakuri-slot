package com.tapjoy.internal;

public final class cg implements Runnable {
    private final ce a;
    private final cj b;

    protected cg(ce ceVar, cj cjVar) {
        this.a = ceVar;
        this.b = cjVar;
    }

    public final void run() {
        try {
            Object f = this.a.f();
            if (this.b != null && !(this.b instanceof ck)) {
                this.b.a(this.a, f);
            }
        } catch (Throwable th) {
            if (this.b != null && !(this.b instanceof ck)) {
                this.b.a(this.a);
            }
        }
    }
}
