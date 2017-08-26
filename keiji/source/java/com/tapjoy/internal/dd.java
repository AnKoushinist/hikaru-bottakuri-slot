package com.tapjoy.internal;

import com.tapjoy.internal.di.a;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

public abstract class dd implements di {
    private static final Logger a = Logger.getLogger(dd.class.getName());
    private final di b = new df(this) {
        final /* synthetic */ dd a;

        {
            this.a = r1;
        }

        protected final void a() {
            new Executor(this.a) {
                final /* synthetic */ dd a;

                {
                    this.a = r1;
                }

                public final void execute(Runnable runnable) {
                    new Thread(runnable, this.a.getClass().getSimpleName()).start();
                }
            }.execute(new Runnable(this) {
                final /* synthetic */ AnonymousClass1 a;

                {
                    this.a = r1;
                }

                public final void run() {
                    try {
                        Object obj;
                        this.a.a.a();
                        this.a.c();
                        if (this.a.f() == a.RUNNING) {
                            obj = 1;
                        } else {
                            obj = null;
                        }
                        if (obj != null) {
                            this.a.a.b();
                        }
                        this.a.a.c();
                        this.a.d();
                    } catch (Throwable th) {
                        this.a.a(th);
                        RuntimeException a = cu.a(th);
                    }
                }
            });
        }

        protected final void b() {
            this.a.d();
        }
    };

    public abstract void b();

    public void a() {
    }

    public void c() {
    }

    public void d() {
    }

    public String toString() {
        return getClass().getSimpleName() + " [" + f() + "]";
    }

    public final dh e() {
        return this.b.e();
    }

    public final a f() {
        return this.b.f();
    }
}
