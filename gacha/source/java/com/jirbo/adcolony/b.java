package com.jirbo.adcolony;

import android.app.Activity;
import android.os.Looper;
import com.jirbo.adcolony.ADCDownload.Listener;
import com.tapjoy.TapjoyConstants;
import java.util.Iterator;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.HttpResponseCode;

class b implements Listener {
    d a;
    boolean b;
    boolean c;
    boolean d;
    boolean e = true;
    boolean f = false;
    boolean g = true;
    double h;
    e i = new e();

    b(d dVar) {
        this.a = dVar;
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
    }

    void a() {
    }

    void b() {
        l.b.b((Object) "Attempting to load backup manifest from file.");
        f fVar = new f("manifest.txt");
        g b = k.b(fVar);
        if (b != null) {
            boolean z;
            c c = b.b("app").c("zones");
            if (c != null) {
                for (int i = 0; i < c.i(); i++) {
                    g b2 = c.b(i);
                    boolean z2 = false;
                    for (Object equals : a.l.a.l) {
                        if (b2.e("uuid").equals(equals)) {
                            z2 = true;
                        }
                    }
                    if (!z2) {
                        z = false;
                        break;
                    }
                }
            }
            z = true;
            a.C = true;
            if (z && a(b)) {
                this.b = true;
                this.i.a();
                a.C = false;
                return;
            }
            l.b.b((Object) "Invalid manifest loaded.");
            fVar.c();
            a.C = false;
            this.b = false;
        }
    }

    String c() {
        if (!this.b) {
            return null;
        }
        String str = null;
        for (int i = 0; i < this.i.n.b(); i++) {
            ad a = this.i.n.a(i);
            if (a.g()) {
                str = a.a;
                if (a.c()) {
                    return a.a;
                }
            }
        }
        return str;
    }

    String d() {
        if (!this.b) {
            return null;
        }
        String str = null;
        for (int i = 0; i < this.i.n.b(); i++) {
            ad a = this.i.n.a(i);
            if (a.i()) {
                str = a.a;
                if (a.c()) {
                    return a.a;
                }
            }
        }
        return str;
    }

    boolean a(String str) {
        return a(str, true);
    }

    boolean a(String str, boolean z) {
        for (int i = 0; i < this.i.n.b(); i++) {
            ad a = this.i.n.a(i);
            if (a.c(z) && a.a.equals(str)) {
                return true;
            }
        }
        return false;
    }

    boolean b(String str) {
        return b(str, false);
    }

    boolean b(String str, boolean z) {
        if (z) {
            return c(str, z);
        }
        if (!this.b) {
            return l.c.b("Ads are not ready to be played, as they are still downloading.");
        }
        if (z) {
            return this.i.a(str, true, false);
        }
        return this.i.a(str, false, true);
    }

    boolean c(String str, boolean z) {
        if (!this.b) {
            return false;
        }
        if (z) {
            return this.i.a(str, true, false);
        }
        return this.i.a(str, false, true);
    }

    void e() {
    }

    void f() {
        this.c = true;
        if (this.c) {
            this.c = false;
            if (g.c() >= 32) {
                this.h = aa.c() + 600.0d;
                h();
            }
        }
        if (q.c()) {
            if (!a.L) {
                a.h();
            }
            a.L = true;
            return;
        }
        if (a.L) {
            a.h();
        }
        a.L = false;
    }

    void g() {
        new Thread(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                for (int i = 0; i < HttpResponseCode.INTERNAL_SERVER_ERROR && a.a(); i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
        h();
    }

    void h() {
        a(null);
    }

    void a(Activity activity) {
        a.z = true;
        if (!this.d) {
            this.d = true;
            if (!this.b) {
                b();
            }
        }
        if (activity != null) {
            a.U = null;
        }
        g gVar = this.a.a.j;
        gVar.b(TapjoyConstants.TJC_APP_ID, this.a.a.k);
        i cVar = new c();
        if (this.a.a.l != null) {
            for (String str : this.a.a.l) {
                ad g = a.l.g(str);
                if (g == null || (g != null && (g.a() || System.currentTimeMillis() - g.q > g.o))) {
                    cVar.a(str);
                }
            }
        }
        if (cVar.i() != 0) {
            gVar.a("zones", cVar);
            i gVar2 = new g();
            i gVar3 = new g();
            i gVar4 = new g();
            if (this.a.a.l != null) {
                for (String str2 : this.a.a.l) {
                    ad g2 = a.l.g(str2);
                    i cVar2 = new c();
                    i cVar3 = new c();
                    if (!(g2 == null || g2.m == null || g2.m.a == null)) {
                        Iterator it = g2.m.a.iterator();
                        while (it.hasNext()) {
                            a aVar = (a) it.next();
                            if (!aVar.b()) {
                                cVar2.a(aVar.a);
                            }
                            if (aVar.c()) {
                                cVar3.a(aVar.a);
                            }
                        }
                    }
                    if (cVar2.i() > 0) {
                        gVar2.a(str2, cVar2);
                    }
                    if (cVar3.i() > 0) {
                        gVar3.a(str2, cVar3);
                    }
                    if (g2 != null && g2.j.i() > 0) {
                        gVar4.a(str2, g2.j);
                    }
                }
            }
            gVar.a("ad_queue", gVar2);
            gVar.a("ad_playing", gVar3);
            gVar.a("ad_history", gVar4);
            String str3 = a.l.e.k == null ? BuildConfig.FLAVOR : a.l.e.k;
            int i = a.l.e.j;
            gVar.b(Constants.PREFKEY_CARR, this.a.a.x);
            if (q.a()) {
                gVar.b("network_type", TapjoyConstants.TJC_CONNECTION_TYPE_WIFI);
            } else if (q.b()) {
                gVar.b("network_type", "cell");
            } else {
                gVar.b("network_type", "none");
            }
            gVar.b("custom_id", this.a.a.y);
            gVar.b("sid", str3);
            gVar.b("s_imp_count", i);
            if (!a.p) {
                l a = l.b.a("Downloading ad manifest from ");
                c cVar4 = this.a.a;
                a.a(c.c).b((Object) " with the following post content: ");
                l.b.b(gVar.toString());
                a.p = true;
                d dVar = this.a;
                c cVar5 = this.a.a;
                new ADCDownload(dVar, c.c, this).a("application/json", gVar.q()).b();
            }
        }
    }

    public void on_download_finished(ADCDownload aDCDownload) {
        a.z = true;
        if (aDCDownload.i) {
            l.c.b((Object) "Received ad server response from:");
            l.c.b(aDCDownload.c);
            g b = k.b(aDCDownload.n);
            if (b == null) {
                l.a.b((Object) "Invalid JSON in manifest.  Raw data:");
                l.a.b(aDCDownload.n);
                return;
            } else if (a(b)) {
                int i;
                l.b.b((Object) "Ad manifest updated.");
                c c = k.c(b.b("app").c("zones").toString());
                for (i = 0; i < this.i.n.b(); i++) {
                    boolean z;
                    for (int i2 = 0; i2 < c.i(); i2++) {
                        if (((ad) this.i.n.a.get(i)).a.equals(c.b(i2).e("uuid"))) {
                            z = true;
                            break;
                        }
                    }
                    z = false;
                    if (!z) {
                        c.a(this.i.n.a(i).b);
                    }
                }
                b.b("app").c("zones").j();
                for (int i3 = 0; i3 < c.i(); i3++) {
                    boolean z2 = false;
                    for (String equals : a.l.a.l) {
                        if (equals.equals(c.b(i3).e("uuid"))) {
                            z2 = true;
                        }
                    }
                    if (z2) {
                        c.b(i3).b("last_config_ms", new Long(System.currentTimeMillis()).toString());
                        b.b("app").c("zones").a(c.b(i3));
                    }
                }
                new f("manifest.txt").a(b.toString());
                this.b = true;
                this.i.a();
                if (this.i.i == null || this.i.i.equals(BuildConfig.FLAVOR)) {
                    this.i.i = "all";
                }
                if (this.i.j == null || this.i.j.equals(BuildConfig.FLAVOR)) {
                    this.i.j = "all";
                }
                a.h();
                return;
            } else {
                l.b.b((Object) "Invalid manifest.");
                return;
            }
        }
        l.c.b((Object) "Error retrieving ad server response from:");
        l.c.b(aDCDownload.c);
    }

    boolean a(g gVar) {
        if (gVar == null || !gVar.e("status").equals("success") || !this.i.a(gVar.b("app"))) {
            return false;
        }
        l.a.b((Object) "Finished parsing manifest");
        a.h();
        if (this.i.h.equalsIgnoreCase("none")) {
            a.a(2);
        } else {
            l.c.b((Object) "Enabling debug logging.");
            a.a(1);
        }
        return true;
    }
}
