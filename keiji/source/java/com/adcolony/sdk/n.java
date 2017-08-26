package com.adcolony.sdk;

import android.app.Activity;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONObject;

class n {
    static boolean a;
    static boolean b;
    private static WeakReference<Activity> c;
    private static aq d;

    static void a(final Activity activity, d dVar, boolean z) {
        a(activity);
        b = true;
        if (d == null) {
            d = new aq();
            d.a(dVar, z);
        } else {
            d.a(dVar);
        }
        ab.a.execute(new Runnable() {
            public void run() {
                n.d.a(activity, null);
            }
        });
        bd.c.b((Object) "Configuring AdColony");
        d.b(false);
        d.g().b(true);
        d.g().c(true);
        d.g().d(false);
        d.c = true;
        d.g().a(false);
    }

    static aq a() {
        if (!b()) {
            if (!d()) {
                return new aq();
            }
            d = new aq();
            JSONObject c = bb.c(c().getFilesDir().getAbsolutePath() + "/adc3/AppInfo");
            JSONArray f = bb.f(c, "zoneIds");
            d.a(new d().b(bb.a(c, "appId")).a(bb.a(f)), false);
        }
        return d;
    }

    static boolean b() {
        return d != null;
    }

    static void a(Activity activity) {
        if (activity == null) {
            c.clear();
        } else {
            c = new WeakReference(activity);
        }
    }

    static Activity c() {
        return (Activity) c.get();
    }

    static boolean d() {
        return (c == null || c.get() == null) ? false : true;
    }

    static boolean e() {
        return a;
    }

    static void a(String str, q qVar) {
        a().k().a(str, qVar);
    }

    static q a(String str, q qVar, boolean z) {
        a().k().a(str, qVar);
        return qVar;
    }

    static void b(String str, q qVar) {
        a().k().b(str, qVar);
    }

    static void f() {
        a().k().b();
    }

    static void a(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = bb.a();
        }
        bb.a(jSONObject, "m_type", str);
        a().k().a(jSONObject);
    }
}
