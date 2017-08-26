package com.b.a.b;

import android.annotation.SuppressLint;
import android.content.Context;
import b.a.a.a.a.b.g;
import b.a.a.a.a.b.k;
import b.a.a.a.a.b.o;
import b.a.a.a.a.e.e;
import b.a.a.a.a.f.c;
import b.a.a.a.a.g.f;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: AbstractCheckForUpdatesController */
abstract class a implements j {
    private final AtomicBoolean a;
    private final AtomicBoolean b;
    private Context c;
    private c d;
    private o e;
    private f f;
    private d g;
    private c h;
    private k i;
    private e j;
    private long k;

    public a() {
        this(false);
    }

    public a(boolean z) {
        this.a = new AtomicBoolean();
        this.k = 0;
        this.b = new AtomicBoolean(z);
    }

    public void a(Context context, c cVar, o oVar, f fVar, d dVar, c cVar2, k kVar, e eVar) {
        this.c = context;
        this.d = cVar;
        this.e = oVar;
        this.f = fVar;
        this.g = dVar;
        this.h = cVar2;
        this.i = kVar;
        this.j = eVar;
        if (b()) {
            c();
        }
    }

    protected boolean a() {
        this.b.set(true);
        return this.a.get();
    }

    boolean b() {
        this.a.set(true);
        return this.b.get();
    }

    @SuppressLint({"CommitPrefEdits"})
    protected void c() {
        synchronized (this.h) {
            if (this.h.a().contains("last_update_check")) {
                this.h.a(this.h.b().remove("last_update_check"));
            }
        }
        long a = this.i.a();
        long j = ((long) this.f.b) * 1000;
        b.a.a.a.c.h().a("Beta", "Check for updates delay: " + j);
        b.a.a.a.c.h().a("Beta", "Check for updates last check time: " + d());
        j += d();
        b.a.a.a.c.h().a("Beta", "Check for updates current time: " + a + ", next check time: " + j);
        if (a >= j) {
            try {
                e();
            } finally {
                a(a);
            }
        } else {
            b.a.a.a.c.h().a("Beta", "Check for updates next check time was not passed");
        }
    }

    private void e() {
        b.a.a.a.c.h().a("Beta", "Performing update check");
        new e(this.d, this.d.g(), this.f.a, this.j, new g()).a(new g().a(this.c), (String) this.e.i().get(b.a.a.a.a.b.o.a.FONT_TOKEN), this.g);
    }

    void a(long j) {
        this.k = j;
    }

    long d() {
        return this.k;
    }
}
