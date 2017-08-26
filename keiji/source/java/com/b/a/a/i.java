package com.b.a.a;

import a.a.a.a.a.b.g;
import a.a.a.a.a.d.f;
import a.a.a.a.a.e.e;
import a.a.a.a.a.g.b;
import a.a.a.a.c;
import android.content.Context;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: EnabledSessionAnalyticsManagerStrategy */
class i implements r {
    final t a;
    f b;
    g c = new g();
    j d = new k();
    boolean e = true;
    boolean f = true;
    volatile int g = -1;
    private final a.a.a.a.i h;
    private final e i;
    private final Context j;
    private final o k;
    private final ScheduledExecutorService l;
    private final AtomicReference<ScheduledFuture<?>> m = new AtomicReference();

    public i(a.a.a.a.i iVar, Context context, ScheduledExecutorService scheduledExecutorService, o oVar, e eVar, t tVar) {
        this.h = iVar;
        this.j = context;
        this.l = scheduledExecutorService;
        this.k = oVar;
        this.i = eVar;
        this.a = tVar;
    }

    public void a(b bVar, String str) {
        this.b = f.a(new p(this.h, str, bVar.a, this.i, this.c.a(this.j)));
        this.k.a(bVar);
        this.e = bVar.f;
        c.h().a("Answers", "Custom event tracking " + (this.e ? String.ENABLED : "disabled"));
        this.f = bVar.g;
        c.h().a("Answers", "Predefined event tracking " + (this.f ? String.ENABLED : "disabled"));
        if (bVar.i > 1) {
            c.h().a("Answers", "Event sampling enabled");
            this.d = new n(bVar.i);
        }
        this.g = bVar.b;
        a(0, (long) this.g);
    }

    public void a(a aVar) {
        s a = aVar.a(this.a);
        if (!this.e && b.CUSTOM.equals(a.c)) {
            c.h().a("Answers", "Custom events tracking disabled - skipping event: " + a);
        } else if (!this.f && b.PREDEFINED.equals(a.c)) {
            c.h().a("Answers", "Predefined events tracking disabled - skipping event: " + a);
        } else if (this.d.a(a)) {
            c.h().a("Answers", "Skipping filtered event: " + a);
        } else {
            try {
                this.k.a((Object) a);
            } catch (Throwable e) {
                c.h().e("Answers", "Failed to write event: " + a, e);
            }
            e();
        }
    }

    public void e() {
        if ((this.g != -1 ? 1 : null) != null) {
            a((long) this.g, (long) this.g);
        }
    }

    public void a() {
        int size;
        Throwable e;
        if (this.b == null) {
            a.a.a.a.a.b.i.a(this.j, "skipping files send because we don't yet know the target endpoint");
            return;
        }
        a.a.a.a.a.b.i.a(this.j, "Sending all files");
        List e2 = this.k.e();
        int i = 0;
        while (e2.size() > 0) {
            try {
                a.a.a.a.a.b.i.a(this.j, String.format(Locale.US, "attempt to send batch of %d files", new Object[]{Integer.valueOf(e2.size())}));
                boolean a = this.b.a(e2);
                if (a) {
                    size = e2.size() + i;
                    try {
                        this.k.a(e2);
                        i = size;
                    } catch (Exception e3) {
                        e = e3;
                    }
                }
                if (!a) {
                    break;
                }
                e2 = this.k.e();
            } catch (Throwable e4) {
                Throwable th = e4;
                size = i;
                e = th;
            }
        }
        if (i == 0) {
            this.k.g();
        }
        a.a.a.a.a.b.i.a(this.j, "Failed to send batch of analytics files to server: " + e.getMessage(), e);
        i = size;
        if (i == 0) {
            this.k.g();
        }
    }

    public void d() {
        if (this.m.get() != null) {
            a.a.a.a.a.b.i.a(this.j, "Cancelling time-based rollover because no events are currently being generated.");
            ((ScheduledFuture) this.m.get()).cancel(false);
            this.m.set(null);
        }
    }

    public void b() {
        this.k.f();
    }

    public boolean c() {
        try {
            return this.k.d();
        } catch (Throwable e) {
            a.a.a.a.a.b.i.a(this.j, "Failed to roll file over.", e);
            return false;
        }
    }

    void a(long j, long j2) {
        if ((this.m.get() == null ? 1 : null) != null) {
            Runnable iVar = new a.a.a.a.a.d.i(this.j, this);
            a.a.a.a.a.b.i.a(this.j, "Scheduling time based file roll over every " + j2 + " seconds");
            try {
                this.m.set(this.l.scheduleAtFixedRate(iVar, j, j2, TimeUnit.SECONDS));
            } catch (Throwable e) {
                a.a.a.a.a.b.i.a(this.j, "Failed to schedule time based file roll over", e);
            }
        }
    }
}
