package a.a.a.a;

import a.a.a.a.a.b.g;
import a.a.a.a.a.b.i;
import a.a.a.a.a.b.l;
import a.a.a.a.a.e.b;
import a.a.a.a.a.e.e;
import a.a.a.a.a.g.d;
import a.a.a.a.a.g.h;
import a.a.a.a.a.g.n;
import a.a.a.a.a.g.q;
import a.a.a.a.a.g.t;
import a.a.a.a.a.g.y;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/* compiled from: Onboarding */
class m extends i<Boolean> {
    private final e a = new b();
    private PackageManager b;
    private String c;
    private PackageInfo d;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private final Future<Map<String, k>> o;
    private final Collection<i> p;

    protected /* synthetic */ Object e() {
        return d();
    }

    public m(Future<Map<String, k>> future, Collection<i> collection) {
        this.o = future;
        this.p = collection;
    }

    public String a() {
        return "1.3.10.97";
    }

    protected boolean l_() {
        try {
            this.l = D().j();
            this.b = E().getPackageManager();
            this.c = E().getPackageName();
            this.d = this.b.getPackageInfo(this.c, 0);
            this.j = Integer.toString(this.d.versionCode);
            this.k = this.d.versionName == null ? "0.0" : this.d.versionName;
            this.m = this.b.getApplicationLabel(E().getApplicationInfo()).toString();
            this.n = Integer.toString(E().getApplicationInfo().targetSdkVersion);
            return true;
        } catch (Throwable e) {
            c.h().e("Fabric", "Failed init", e);
            return false;
        }
    }

    protected Boolean d() {
        boolean a;
        String k = i.k(E());
        t g = g();
        if (g != null) {
            try {
                Map map;
                if (this.o != null) {
                    map = (Map) this.o.get();
                } else {
                    map = new HashMap();
                }
                a = a(k, g.a, a(map, this.p).values());
            } catch (Throwable e) {
                c.h().e("Fabric", "Error performing auto configuration.", e);
            }
            return Boolean.valueOf(a);
        }
        a = false;
        return Boolean.valueOf(a);
    }

    private t g() {
        try {
            q.a().a(this, this.i, this.a, this.j, this.k, f()).c();
            return q.a().b();
        } catch (Throwable e) {
            c.h().e("Fabric", "Error dealing with settings", e);
            return null;
        }
    }

    Map<String, k> a(Map<String, k> map, Collection<i> collection) {
        for (i iVar : collection) {
            if (!map.containsKey(iVar.b())) {
                map.put(iVar.b(), new k(iVar.b(), iVar.a(), "binary"));
            }
        }
        return map;
    }

    public String b() {
        return "io.fabric.sdk.android:fabric";
    }

    private boolean a(String str, a.a.a.a.a.g.e eVar, Collection<k> collection) {
        if ("new".equals(eVar.b)) {
            if (b(str, eVar, collection)) {
                return q.a().d();
            }
            c.h().e("Fabric", "Failed to create app with Crashlytics service.", null);
            return false;
        } else if ("configured".equals(eVar.b)) {
            return q.a().d();
        } else {
            if (!eVar.e) {
                return true;
            }
            c.h().a("Fabric", "Server says an update is required - forcing a full App update.");
            c(str, eVar, collection);
            return true;
        }
    }

    private boolean b(String str, a.a.a.a.a.g.e eVar, Collection<k> collection) {
        return new h(this, f(), eVar.c, this.a).a(a(n.a(E(), str), (Collection) collection));
    }

    private boolean c(String str, a.a.a.a.a.g.e eVar, Collection<k> collection) {
        return a(eVar, n.a(E(), str), (Collection) collection);
    }

    private boolean a(a.a.a.a.a.g.e eVar, n nVar, Collection<k> collection) {
        return new y(this, f(), eVar.c, this.a).a(a(nVar, (Collection) collection));
    }

    private d a(n nVar, Collection<k> collection) {
        return new d(new g().a(E()), D().c(), this.k, this.j, i.a(i.m(r0)), this.m, l.a(this.l).a(), this.n, "0", nVar, collection);
    }

    String f() {
        return i.b(E(), "com.crashlytics.ApiEndpoint");
    }
}
