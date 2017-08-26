package com.b.a.a;

import android.content.Context;
import b.a.a.a.a.d.d;
import b.a.a.a.a.e.e;
import b.a.a.a.c;
import b.a.a.a.i;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: AnswersEventsHandler */
class b implements d {
    final ScheduledExecutorService a;
    r b = new h();
    private final i c;
    private final Context d;
    private final c e;
    private final v f;
    private final e g;

    public b(i iVar, Context context, c cVar, v vVar, e eVar, ScheduledExecutorService scheduledExecutorService) {
        this.c = iVar;
        this.d = context;
        this.e = cVar;
        this.f = vVar;
        this.g = eVar;
        this.a = scheduledExecutorService;
    }

    public void a(a aVar) {
        a(aVar, false, false);
    }

    public void b(a aVar) {
        a(aVar, false, true);
    }

    public void c(a aVar) {
        a(aVar, true, false);
    }

    public void a(final b.a.a.a.a.g.b bVar, final String str) {
        b(new Runnable(this) {
            final /* synthetic */ b c;

            public void run() {
                try {
                    this.c.b.a(bVar, str);
                } catch (Throwable e) {
                    c.h().e("Answers", "Failed to set analytics settings data", e);
                }
            }
        });
    }

    public void a() {
        b(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    r rVar = this.a.b;
                    this.a.b = new h();
                    rVar.b();
                } catch (Throwable e) {
                    c.h().e("Answers", "Failed to disable events", e);
                }
            }
        });
    }

    public void a(String str) {
        b(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    this.a.b.a();
                } catch (Throwable e) {
                    c.h().e("Answers", "Failed to send events files", e);
                }
            }
        });
    }

    public void b() {
        b(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    t a = this.a.f.a();
                    o a2 = this.a.e.a();
                    a2.a(this.a);
                    this.a.b = new i(this.a.c, this.a.d, this.a.a, a2, this.a.g, a);
                } catch (Throwable e) {
                    c.h().e("Answers", "Failed to enable events", e);
                }
            }
        });
    }

    public void c() {
        b(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    this.a.b.c();
                } catch (Throwable e) {
                    c.h().e("Answers", "Failed to flush events", e);
                }
            }
        });
    }

    void a(final a aVar, boolean z, final boolean z2) {
        Runnable anonymousClass6 = new Runnable(this) {
            final /* synthetic */ b c;

            public void run() {
                try {
                    this.c.b.a(aVar);
                    if (z2) {
                        this.c.b.c();
                    }
                } catch (Throwable e) {
                    c.h().e("Answers", "Failed to process event", e);
                }
            }
        };
        if (z) {
            a(anonymousClass6);
        } else {
            b(anonymousClass6);
        }
    }

    private void a(Runnable runnable) {
        try {
            this.a.submit(runnable).get();
        } catch (Throwable e) {
            c.h().e("Answers", "Failed to run events task", e);
        }
    }

    private void b(Runnable runnable) {
        try {
            this.a.submit(runnable);
        } catch (Throwable e) {
            c.h().e("Answers", "Failed to submit events task", e);
        }
    }
}
