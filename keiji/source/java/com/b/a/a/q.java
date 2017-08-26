package com.b.a.a;

import a.a.a.a.a.b.n;
import a.a.a.a.a.b.o;
import a.a.a.a.a.e.e;
import a.a.a.a.a.f.b;
import a.a.a.a.c;
import a.a.a.a.i;
import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.b.a.a.g.a;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: SessionAnalyticsManager */
class q implements a {
    final b a;
    final a.a.a.a.a b;
    final g c;
    final e d;
    private final long e;

    public static q a(i iVar, Context context, o oVar, String str, String str2, long j) {
        v vVar = new v(context, oVar, str, str2);
        c cVar = new c(context, new b(iVar));
        e bVar = new a.a.a.a.a.e.b(c.h());
        a.a.a.a.a aVar = new a.a.a.a.a(context);
        ScheduledExecutorService b = n.b("Answers Events Handler");
        g gVar = new g(b);
        return new q(new b(iVar, context, cVar, vVar, bVar, b), aVar, gVar, e.a(context), j);
    }

    q(b bVar, a.a.a.a.a aVar, g gVar, e eVar, long j) {
        this.a = bVar;
        this.b = aVar;
        this.c = gVar;
        this.d = eVar;
        this.e = j;
    }

    public void b() {
        this.a.b();
        this.b.a(new d(this, this.c));
        this.c.a((a) this);
        if (a(this.e)) {
            d();
            this.d.a();
        }
    }

    public void c() {
        this.b.a();
        this.a.a();
    }

    public void a(String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("onCrash called from main thread!!!");
        }
        c.h().a("Answers", "Logged crash");
        this.a.c(s.a(str));
    }

    public void d() {
        c.h().a("Answers", "Logged install");
        this.a.b(s.a());
    }

    public void a(Activity activity, b bVar) {
        c.h().a("Answers", "Logged lifecycle event: " + bVar.name());
        this.a.a(s.a(bVar, activity));
    }

    public void a() {
        c.h().a("Answers", "Flush events when app is backgrounded");
        this.a.c();
    }

    public void a(a.a.a.a.a.g.b bVar, String str) {
        this.c.a(bVar.h);
        this.a.a(bVar, str);
    }

    boolean a(long j) {
        return !this.d.b() && b(j);
    }

    boolean b(long j) {
        return System.currentTimeMillis() - j < 3600000;
    }
}
