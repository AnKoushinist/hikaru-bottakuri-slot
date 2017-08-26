package com.tapjoy.internal;

import android.os.Handler;
import android.os.Looper;

public class ga implements fo {
    private static final ga a = new ga() {
        public final void a(String str) {
        }

        public final void b(String str) {
        }

        public final void c(String str) {
        }

        public final void d(String str) {
        }

        public final void a(String str, fl flVar) {
        }

        public final void a(String str, String str2, fl flVar) {
        }
    };
    private final fo b;
    private final bf c;

    public static ga a(fo foVar) {
        Object obj;
        if (foVar instanceof ga) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj == null) {
            throw new IllegalArgumentException();
        } else if (foVar != null) {
            return new ga(foVar);
        } else {
            return a;
        }
    }

    private ga() {
        this.b = null;
        this.c = null;
    }

    private ga(fo foVar) {
        Handler a;
        this.b = foVar;
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            cs.a((Object) myLooper);
            a = myLooper == Looper.getMainLooper() ? x.a() : new Handler(myLooper);
        } else {
            a = null;
        }
        if (a != null) {
            this.c = x.a(a);
            new Object[1][0] = a.getLooper();
        } else if (Thread.currentThread() == fr.b()) {
            this.c = fr.a;
        } else {
            this.c = x.a(x.a());
        }
    }

    public void a(final String str) {
        this.c.a(new Runnable(this) {
            final /* synthetic */ ga b;

            public final void run() {
                this.b.b.a(str);
            }
        });
    }

    public void b(final String str) {
        this.c.a(new Runnable(this) {
            final /* synthetic */ ga b;

            public final void run() {
                this.b.b.b(str);
            }
        });
    }

    public void c(final String str) {
        this.c.a(new Runnable(this) {
            final /* synthetic */ ga b;

            public final void run() {
                this.b.b.c(str);
            }
        });
    }

    public void d(final String str) {
        this.c.a(new Runnable(this) {
            final /* synthetic */ ga b;

            public final void run() {
                this.b.b.d(str);
            }
        });
    }

    public void a(final String str, final fl flVar) {
        this.c.a(new Runnable(this) {
            final /* synthetic */ ga c;

            public final void run() {
                this.c.b.a(str, flVar);
            }
        });
    }

    public void a(final String str, final String str2, final fl flVar) {
        this.c.a(new Runnable(this) {
            final /* synthetic */ ga d;

            public final void run() {
                this.d.b.a(str, str2, flVar);
            }
        });
    }
}
