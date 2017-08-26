package com.tapjoy.internal;

import com.tapjoy.internal.dg.a;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

public abstract class db implements dg {
    private static final Logger a = Logger.getLogger(db.class.getName());
    private final dg b = new dd(this) {
        final /* synthetic */ db a;

        {
            this.a = r1;
        }

        protected final void a() {
            new Executor(this.a) {
                final /* synthetic */ db a;

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
                        RuntimeException a = cs.a(th);
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

    public final df e() {
        return this.b.e();
    }

    public final a f() {
        return this.b.f();
    }
}
