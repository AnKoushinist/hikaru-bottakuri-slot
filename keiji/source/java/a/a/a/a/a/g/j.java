package a.a.a.a.a.g;

import a.a.a.a.a.b.k;
import a.a.a.a.a.f.c;
import a.a.a.a.a.f.d;
import a.a.a.a.i;
import android.annotation.SuppressLint;
import android.content.SharedPreferences.Editor;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

/* compiled from: DefaultSettingsController */
class j implements s {
    private final w a;
    private final v b;
    private final k c;
    private final g d;
    private final x e;
    private final i f;
    private final c g = new d(this.f);

    public j(i iVar, w wVar, k kVar, v vVar, g gVar, x xVar) {
        this.f = iVar;
        this.a = wVar;
        this.c = kVar;
        this.b = vVar;
        this.d = gVar;
        this.e = xVar;
    }

    public t a() {
        return a(r.USE_CACHE);
    }

    public t a(r rVar) {
        Throwable th;
        t tVar;
        Throwable th2;
        t tVar2 = null;
        try {
            if (!(a.a.a.a.c.i() || d())) {
                tVar2 = b(rVar);
            }
            if (tVar2 == null) {
                try {
                    JSONObject a = this.e.a(this.a);
                    if (a != null) {
                        tVar2 = this.b.a(this.c, a);
                        this.d.a(tVar2.g, a);
                        a(a, "Loaded settings: ");
                        a(b());
                    }
                } catch (Throwable e) {
                    th = e;
                    tVar = tVar2;
                    th2 = th;
                    a.a.a.a.c.h().e("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
                    return tVar;
                }
            }
            tVar = tVar2;
            if (tVar == null) {
                try {
                    tVar = b(r.IGNORE_CACHE_EXPIRATION);
                } catch (Exception e2) {
                    th2 = e2;
                    a.a.a.a.c.h().e("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
                    return tVar;
                }
            }
        } catch (Throwable e3) {
            th = e3;
            tVar = null;
            th2 = th;
            a.a.a.a.c.h().e("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
            return tVar;
        }
        return tVar;
    }

    private t b(r rVar) {
        Throwable th;
        t tVar = null;
        try {
            if (r.SKIP_CACHE_LOOKUP.equals(rVar)) {
                return null;
            }
            JSONObject a = this.d.a();
            if (a != null) {
                t a2 = this.b.a(this.c, a);
                if (a2 != null) {
                    a(a, "Loaded cached settings: ");
                    long a3 = this.c.a();
                    if (r.IGNORE_CACHE_EXPIRATION.equals(rVar) || !a2.a(a3)) {
                        try {
                            a.a.a.a.c.h().a("Fabric", "Returning cached settings.");
                            return a2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            tVar = a2;
                            th = th2;
                            a.a.a.a.c.h().e("Fabric", "Failed to get cached settings", th);
                            return tVar;
                        }
                    }
                    a.a.a.a.c.h().a("Fabric", "Cached settings have expired.");
                    return null;
                }
                a.a.a.a.c.h().e("Fabric", "Failed to transform cached settings data.", null);
                return null;
            }
            a.a.a.a.c.h().a("Fabric", "No cached settings data found.");
            return null;
        } catch (Exception e2) {
            th = e2;
            a.a.a.a.c.h().e("Fabric", "Failed to get cached settings", th);
            return tVar;
        }
    }

    private void a(JSONObject jSONObject, String str) {
        a.a.a.a.c.h().a("Fabric", str + jSONObject.toString());
    }

    String b() {
        return a.a.a.a.a.b.i.a(a.a.a.a.a.b.i.m(this.f.E()));
    }

    String c() {
        return this.g.a().getString("existing_instance_identifier", BuildConfig.FLAVOR);
    }

    @SuppressLint({"CommitPrefEdits"})
    boolean a(String str) {
        Editor b = this.g.b();
        b.putString("existing_instance_identifier", str);
        return this.g.a(b);
    }

    boolean d() {
        return !c().equals(b());
    }
}
