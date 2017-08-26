package com.jirbo.adcolony;

import android.content.Intent;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.mraid.controller.Abstract;
import java.util.ArrayList;

class d {
    c a = new c(this);
    b b = new b(this);
    o c = new o(this);
    t d = new t(this);
    u e = new u(this);
    ADCStorage f = new ADCStorage(this);
    ag g = new ag(this);
    ArrayList<j> h = new ArrayList();
    ArrayList<j> i = new ArrayList();
    volatile boolean j;
    boolean k;
    boolean l;
    a m = new a();

    d() {
    }

    void a(final j jVar) {
        new Thread(new Runnable(this) {
            final /* synthetic */ d b;

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                r3 = this;
                r0 = r3.b;
                r1 = r0.h;
                monitor-enter(r1);
                r0 = com.jirbo.adcolony.a.d();	 Catch:{ all -> 0x0023 }
                if (r0 != 0) goto L_0x000d;
            L_0x000b:
                monitor-exit(r1);	 Catch:{ all -> 0x0023 }
            L_0x000c:
                return;
            L_0x000d:
                r0 = r3.b;	 Catch:{ all -> 0x0023 }
                r0 = r0.h;	 Catch:{ all -> 0x0023 }
                r2 = r3;	 Catch:{ all -> 0x0023 }
                r0.add(r2);	 Catch:{ all -> 0x0023 }
                r0 = r3.b;	 Catch:{ all -> 0x0023 }
                r0 = r0.j;	 Catch:{ all -> 0x0023 }
                if (r0 != 0) goto L_0x0021;
            L_0x001c:
                r0 = r3.b;	 Catch:{ all -> 0x0023 }
                r0.g();	 Catch:{ all -> 0x0023 }
            L_0x0021:
                monitor-exit(r1);	 Catch:{ all -> 0x0023 }
                goto L_0x000c;
            L_0x0023:
                r0 = move-exception;
                monitor-exit(r1);	 Catch:{ all -> 0x0023 }
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jirbo.adcolony.d.1.run():void");
            }
        }).start();
    }

    void a() {
        if (!this.k && a.d()) {
            while (true) {
                try {
                    if (!this.k || (!this.j && this.h.size() > 0)) {
                        this.k = true;
                        this.i.addAll(this.h);
                        this.h.clear();
                        for (int i = 0; i < this.i.size(); i++) {
                            if (this.i.get(i) != null) {
                                ((j) this.i.get(i)).a();
                            }
                        }
                        this.i.clear();
                    }
                } catch (RuntimeException e) {
                    this.k = false;
                    this.i.clear();
                    this.h.clear();
                    a.a(e);
                    return;
                }
            }
            this.k = false;
        }
    }

    void b() {
        this.j = true;
        AnonymousClass2 anonymousClass2 = new j(this, this) {
            final /* synthetic */ d a;

            void a() {
                this.o.e.c();
            }
        };
    }

    void c() {
        this.j = false;
        AnonymousClass3 anonymousClass3 = new j(this, this) {
            final /* synthetic */ d a;

            void a() {
                this.o.e.d();
            }
        };
    }

    void d() {
        AnonymousClass4 anonymousClass4 = new j(this, this) {
            final /* synthetic */ d a;

            void a() {
                this.o.e.e();
            }
        };
    }

    synchronized void a(final AdColonyAd adColonyAd) {
        this.a.o = 0.0d;
        l.a.b((Object) "Tracking ad event - start");
        af afVar = adColonyAd.i.r;
        afVar.d++;
        if (!adColonyAd.b()) {
            adColonyAd.i.m();
        }
        AnonymousClass5 anonymousClass5 = new j(this, this) {
            final /* synthetic */ d b;

            void a() {
                if (AdColony.isZoneV4VC(adColonyAd.h) || adColonyAd.j.A == null || !adColonyAd.j.A.a || (adColonyAd.j.A.a && adColonyAd.u && adColonyAd.l.equals(Abstract.FULL_SCREEN))) {
                    this.b.a(String.VIDEO_START, "{\"ad_slot\":" + (a.l.e.j + 1) + ", \"replay\":" + adColonyAd.u + "}", adColonyAd);
                    adColonyAd.j.q = true;
                    adColonyAd.j.r = true;
                    a.h();
                    adColonyAd.i.j.a(adColonyAd.j.a);
                }
            }
        };
    }

    void a(double d, AdColonyAd adColonyAd) {
        final AdColonyAd adColonyAd2 = adColonyAd;
        final double d2 = d;
        AnonymousClass6 anonymousClass6 = new j(this, this) {
            final /* synthetic */ d c;

            void a() {
                if (!a.o && adColonyAd2.j.w != null && adColonyAd2.j.w.a && d2 > 0.9d) {
                    l.c.b((Object) "V4VC validated.");
                    a.o = true;
                }
                this.o.d.a(d2, adColonyAd2);
            }
        };
    }

    synchronized void a(boolean z, String str, int i) {
        a.X.a(z, str, i);
    }

    synchronized void a(boolean z, AdColonyAd adColonyAd) {
        Object obj = 1;
        synchronized (this) {
            if (adColonyAd != null) {
                a(1.0d, adColonyAd);
                if (!z && adColonyAd.b()) {
                    adColonyAd.i.m();
                    ad adVar = adColonyAd.i;
                    adVar.s++;
                    AdColonyV4VCAd adColonyV4VCAd = (AdColonyV4VCAd) a.T;
                    final String rewardName = adColonyV4VCAd.getRewardName();
                    final int rewardAmount = adColonyV4VCAd.getRewardAmount();
                    if (!adColonyAd.i.b()) {
                        obj = null;
                    }
                    if (obj != null) {
                        if (adColonyV4VCAd.i.n.e && a.o) {
                            a(true, rewardName, rewardAmount);
                        }
                        final AdColonyAd adColonyAd2 = adColonyAd;
                        AnonymousClass7 anonymousClass7 = new j(this, this) {
                            final /* synthetic */ d d;

                            void a() {
                                g gVar = new g();
                                gVar.b("v4vc_name", rewardName);
                                gVar.b("v4vc_amount", rewardAmount);
                                this.o.d.a("reward_v4vc", gVar, adColonyAd2);
                            }
                        };
                    }
                }
            }
        }
    }

    void a(final String str, final String str2) {
        AnonymousClass8 anonymousClass8 = new j(this, this) {
            final /* synthetic */ d c;

            void a() {
                this.o.d.a(str, k.b(str2));
            }
        };
    }

    void a(String str, String str2, AdColonyAd adColonyAd) {
        final String str3 = str;
        final String str4 = str2;
        final AdColonyAd adColonyAd2 = adColonyAd;
        AnonymousClass9 anonymousClass9 = new j(this, this) {
            final /* synthetic */ d d;

            void a() {
                this.o.d.a(str3, k.b(str4), adColonyAd2);
            }
        };
    }

    synchronized double a(String str) {
        double f;
        try {
            f = this.a.i.f(str);
        } catch (RuntimeException e) {
            a.a(e);
            f = 0.0d;
        }
        return f;
    }

    synchronized int b(String str) {
        int g;
        try {
            g = this.a.i.g(str);
        } catch (RuntimeException e) {
            a.a(e);
            g = 0;
        }
        return g;
    }

    synchronized boolean c(String str) {
        boolean h;
        try {
            h = this.a.i.h(str);
        } catch (RuntimeException e) {
            a.a(e);
            h = false;
        }
        return h;
    }

    synchronized String d(String str) {
        String e;
        try {
            e = this.a.i.e(str);
        } catch (RuntimeException e2) {
            a.a(e2);
            e = null;
        }
        return e;
    }

    synchronized String e() {
        return this.b.c();
    }

    synchronized String f() {
        return this.b.d();
    }

    synchronized boolean e(String str) {
        return a(str, false, true);
    }

    synchronized boolean a(String str, boolean z, boolean z2) {
        boolean z3 = false;
        synchronized (this) {
            try {
                if (a.d()) {
                    if (this.b.b(str, z)) {
                        z3 = this.b.i.n.a(str).b(z2);
                    }
                }
            } catch (RuntimeException e) {
                a.a(e);
            }
        }
        return z3;
    }

    synchronized boolean f(String str) {
        return b(str, false, true);
    }

    synchronized boolean b(String str, boolean z, boolean z2) {
        boolean z3 = false;
        synchronized (this) {
            try {
                if (a.d()) {
                    if (this.b.b(str, z)) {
                        z3 = this.b.i.n.a(str).c(z2);
                    }
                }
            } catch (RuntimeException e) {
                a.a(e);
            }
        }
        return z3;
    }

    synchronized void a(AdColonyVideoAd adColonyVideoAd) {
        this.a.b(adColonyVideoAd.h);
    }

    synchronized void a(AdColonyInterstitialAd adColonyInterstitialAd) {
        this.a.b(adColonyInterstitialAd.h);
    }

    synchronized boolean b(AdColonyVideoAd adColonyVideoAd) {
        boolean z = false;
        synchronized (this) {
            try {
                a.T = adColonyVideoAd;
                Object obj = adColonyVideoAd.h;
                if (e(obj)) {
                    l.a.a("Showing ad for zone ").b(obj);
                    a(adColonyVideoAd);
                    z = b((AdColonyAd) adColonyVideoAd);
                }
            } catch (RuntimeException e) {
                a.a(e);
            }
        }
        return z;
    }

    synchronized boolean b(AdColonyInterstitialAd adColonyInterstitialAd) {
        boolean z = false;
        synchronized (this) {
            try {
                a.T = adColonyInterstitialAd;
                Object obj = adColonyInterstitialAd.h;
                if (e(obj)) {
                    l.a.a("Showing ad for zone ").b(obj);
                    a(adColonyInterstitialAd);
                    z = b((AdColonyAd) adColonyInterstitialAd);
                }
            } catch (RuntimeException e) {
                a.a(e);
            }
        }
        return z;
    }

    synchronized ad g(String str) {
        return this.b.i.n.a(str);
    }

    synchronized void a(AdColonyV4VCAd adColonyV4VCAd) {
        this.a.c(adColonyV4VCAd.h);
    }

    synchronized boolean b(AdColonyV4VCAd adColonyV4VCAd) {
        boolean z = false;
        synchronized (this) {
            try {
                a.T = adColonyV4VCAd;
                Object obj = adColonyV4VCAd.h;
                if (f(obj)) {
                    l.a.a("Showing v4vc for zone ").b(obj);
                    a(adColonyV4VCAd);
                    z = b((AdColonyAd) adColonyV4VCAd);
                }
            } catch (RuntimeException e) {
                a.a(e);
            }
        }
        return z;
    }

    synchronized boolean b(AdColonyAd adColonyAd) {
        boolean z;
        if (this.a.m.d()) {
            a.T.f = 3;
            z = false;
        } else {
            ADCVideo.a();
            if (a.m) {
                l.a.b((Object) "Launching AdColonyOverlay");
                a.b().startActivity(new Intent(a.b(), AdColonyOverlay.class));
            } else {
                l.a.b((Object) "Launching AdColonyFullscreen");
                a.b().startActivity(new Intent(a.b(), AdColonyFullscreen.class));
            }
            z = true;
        }
        return z;
    }

    synchronized void a(String str, String str2, String[] strArr) {
        try {
            a(a.n);
            l.c.a("==== Configuring AdColony ").a(this.a.b).b((Object) " with app/zone ids: ====");
            l.c.b((Object) str2);
            for (Object b : strArr) {
                l.c.b(b);
            }
            l.a.a("package name: ").b(aa.f());
            this.a.k = str2;
            this.a.l = strArr;
            this.a.a(str);
            this.m.a();
        } catch (RuntimeException e) {
            a.a(e);
        }
    }

    synchronized void g() {
        if (!a.c()) {
            try {
                a();
                if (!a.x) {
                    if (g.n() != null || this.m.b() > 5.0d) {
                        this.a.a();
                        a.x = true;
                    }
                    a.z = true;
                    this.b.f();
                }
                this.c.e();
                this.e.b();
                this.d.d();
                this.g.d();
            } catch (RuntimeException e) {
                a.a(e);
            }
        }
    }

    void a(int i) {
        a.a(i);
    }
}
