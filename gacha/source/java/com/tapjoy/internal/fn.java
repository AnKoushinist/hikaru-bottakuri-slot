package com.tapjoy.internal;

import android.os.Handler;
import android.os.Looper;

public class fn implements fb {
    private static final fn a = new fn() {
        public final void a(String str) {
        }

        public final void b(String str) {
        }

        public final void c(String str) {
        }

        public final void d(String str) {
        }

        public final void a(String str, ey eyVar) {
        }

        public final void b(String str, ey eyVar) {
        }
    };
    private final fb b;
    private final be c;

    public static fn a(fb fbVar) {
        Object obj;
        if (fbVar instanceof fn) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj == null) {
            throw new IllegalArgumentException();
        } else if (fbVar != null) {
            return new fn(fbVar);
        } else {
            return a;
        }
    }

    private fn() {
        this.b = null;
        this.c = null;
    }

    private fn(fb fbVar) {
        Handler a;
        this.b = fbVar;
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            cq.a((Object) myLooper);
            a = myLooper == Looper.getMainLooper() ? x.a() : new Handler(myLooper);
        } else {
            a = null;
        }
        if (a != null) {
            this.c = x.a(a);
            new Object[1][0] = a.getLooper();
        } else if (Thread.currentThread() == fe.b()) {
            this.c = fe.a;
        } else {
            this.c = x.a(x.a());
        }
    }

    public void a(final String str) {
        this.c.a(new Runnable(this) {
            final /* synthetic */ fn b;

            public final void run() {
                this.b.b.a(str);
            }
        });
    }

    public void b(final String str) {
        this.c.a(new Runnable(this) {
            final /* synthetic */ fn b;

            public final void run() {
                this.b.b.b(str);
            }
        });
    }

    public void c(final String str) {
        this.c.a(new Runnable(this) {
            final /* synthetic */ fn b;

            public final void run() {
                this.b.b.c(str);
            }
        });
    }

    public void d(final String str) {
        this.c.a(new Runnable(this) {
            final /* synthetic */ fn b;

            public final void run() {
                this.b.b.d(str);
            }
        });
    }

    public void a(final String str, final ey eyVar) {
        this.c.a(new Runnable(this) {
            final /* synthetic */ fn c;

            public final void run() {
                this.c.b.a(str, eyVar);
            }
        });
    }

    public void b(final String str, final ey eyVar) {
        this.c.a(new Runnable(this) {
            final /* synthetic */ fn c;

            public final void run() {
                this.c.b.b(str, eyVar);
            }
        });
    }
}
