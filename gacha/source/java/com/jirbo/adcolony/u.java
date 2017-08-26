package com.jirbo.adcolony;

class u {
    d a;
    boolean b;
    boolean c;
    boolean d = false;
    boolean e = false;
    boolean f = true;
    double g;
    double h;
    double i;
    int j;
    String k = "uuid";

    u(d dVar) {
        this.a = dVar;
        this.k = aa.b();
    }

    void a() {
    }

    void b() {
        if (this.a.b.b) {
            if (this.d) {
                this.d = false;
                this.a.d.a("install", null);
            }
            if (this.e) {
                this.e = false;
                this.a.d.a("session_start", null);
            }
        }
    }

    void c() {
        l.b.b((Object) "AdColony resuming");
        a.z = true;
        if (this.b) {
            l.d.b((Object) "AdColony.onResume() called multiple times in succession.");
        }
        this.b = true;
        g();
        double c = aa.c();
        if (this.c) {
            if (c - this.h > ((double) this.a.a.d)) {
                a(this.i);
                this.g = c;
                h();
            }
            this.c = false;
            f();
        } else {
            this.g = c;
            h();
        }
        a.h();
    }

    void d() {
        l.b.b((Object) "AdColony suspending");
        a.z = true;
        if (!this.b) {
            l.d.b((Object) "AdColony.onPause() called without initial call to onResume().");
        }
        this.b = false;
        this.c = true;
        this.h = aa.c();
        f();
    }

    void e() {
        l.b.b((Object) "AdColony terminating");
        a.z = true;
        a(this.i);
        this.c = false;
        f();
    }

    void f() {
        g gVar = new g();
        gVar.b("allow_resume", this.c);
        gVar.b("start_time", this.g);
        gVar.b("finish_time", this.h);
        gVar.b("session_time", this.i);
        k.a(new f("session_info.txt"), gVar);
    }

    void g() {
        g b = k.b(new f("session_info.txt"));
        if (b != null) {
            this.c = b.h("allow_resume");
            this.g = b.f("start_time");
            this.h = b.f("finish_time");
            this.i = b.f("session_time");
            return;
        }
        this.d = true;
    }

    void h() {
        this.e = true;
        if (!this.f) {
            this.k = aa.b();
        }
        this.f = false;
        this.i = 0.0d;
        this.j = 0;
        if (a.l != null && a.l.b != null && a.l.b.i != null && a.l.b.i.n != null) {
            for (int i = 0; i < a.l.b.i.n.b(); i++) {
                if (a.l.b.i.n.a(i).r != null) {
                    a.l.b.i.n.a(i).r.d = 0;
                }
                if (a.l.b.i.n.a(i) != null) {
                    a.l.b.i.n.a(i).j = new c();
                }
            }
        }
    }

    void a(double d) {
        l.a.a("Submitting session duration ").b(d);
        g gVar = new g();
        gVar.b("session_length", (int) d);
        this.a.d.a("session_end", gVar);
    }
}
