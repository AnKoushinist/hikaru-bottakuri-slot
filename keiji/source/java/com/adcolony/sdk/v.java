package com.adcolony.sdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executors;
import org.json.JSONObject;

class v implements Runnable {
    final long a = 30000;
    final long b = 5000;
    final int c = 17;
    private long d;
    private long e;
    private long f;
    private long g;
    private long h;
    private long i;
    private long j;
    private boolean k = true;
    private boolean l = true;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;

    v() {
    }

    public void a() {
        n.a("SessionInfo.stopped", new q(this) {
            final /* synthetic */ v a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.r = true;
            }
        });
    }

    public void run() {
        while (!this.p) {
            this.g = System.currentTimeMillis();
            n.f();
            if (this.e >= 30000) {
                bd.d.a("Ending session due to excessive suspend time: ").b((double) this.e);
                break;
            }
            if (this.k) {
                if (this.m && this.l) {
                    this.m = false;
                    this.q = false;
                    d();
                }
                this.e = 0;
            } else {
                if (this.m && !this.l) {
                    this.m = false;
                    c();
                }
                if (!this.q && n.d() && n.c().isFinishing()) {
                    this.q = true;
                    this.i = 0;
                }
                if (this.q) {
                    this.i += this.f;
                    if (this.i > 5000) {
                        bd.d.b((Object) "Ending session due to excessive time between an Activity finishing and an onResume() event.");
                        break;
                    }
                }
                this.e += this.f;
            }
            this.f = 17;
            a(this.f);
            this.h = System.currentTimeMillis() - this.g;
            if (this.h > 0 && this.h < 6000) {
                this.d += this.h;
            }
            aq a = n.a();
            if (n.d() && System.currentTimeMillis() - this.j > 1000) {
                this.j = System.currentTimeMillis();
                String c = a.b.c();
                if (!c.equals(a.p())) {
                    a.a(c);
                    JSONObject a2 = bb.a();
                    bb.a(a2, "network_type", a.p());
                    new o("Network.on_status_change", 1, a2).a();
                }
            }
        }
        bd.c.b((Object) "AdColony session ending, releasing Activity reference.");
        n.a().b(true);
        n.a(null);
        this.o = true;
        this.s = true;
        b();
        a aVar = new a(10.0d);
        while (!this.r && !aVar.a() && this.s) {
            n.f();
            a(100);
        }
        bd.d.b((Object) "SessionInfo.stopped message received, ending ADC.update_module() spam.");
    }

    void a(boolean z) {
        if (!this.n) {
            if (this.o) {
                n.a().b(false);
                this.o = false;
            }
            this.d = 0;
            this.e = 0;
            this.n = true;
            this.k = true;
            this.r = false;
            new Thread(this).start();
            if (z) {
                JSONObject a = bb.a();
                bb.a(a, "id", ab.c());
                new o("SessionInfo.on_start", 1, a).a();
            }
            if (b.a.isShutdown()) {
                b.a = Executors.newSingleThreadExecutor();
            }
        }
    }

    void b() {
        this.n = false;
        this.k = false;
        if (bf.d != null) {
            bf.d.a();
        }
        JSONObject a = bb.a();
        bb.a(a, "session_length", ((double) this.d) / 1000.0d);
        new o("SessionInfo.on_stop", 1, a).a();
        n.f();
        b.a.shutdown();
        bd.d.b((Object) "SESSION STOP");
    }

    void c() {
        ArrayList c = n.a().k().c();
        synchronized (c) {
            Iterator it = c.iterator();
            while (it.hasNext()) {
                new o("SessionInfo.on_pause", ((r) it.next()).a()).a();
            }
        }
        this.l = true;
        n.f();
    }

    void d() {
        ArrayList c = n.a().k().c();
        synchronized (c) {
            Iterator it = c.iterator();
            while (it.hasNext()) {
                new o("SessionInfo.on_resume", ((r) it.next()).a()).a();
            }
        }
        this.l = false;
    }

    void b(boolean z) {
        this.k = z;
    }

    void c(boolean z) {
        this.m = z;
    }

    void d(boolean z) {
        this.s = z;
    }

    boolean e() {
        return this.k;
    }

    boolean f() {
        return this.n;
    }

    void a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
        }
    }
}
