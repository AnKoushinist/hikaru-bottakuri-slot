package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import java.io.IOException;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import org.cocos2dx.lib.BuildConfig;

public class d {
    static Map<String, d> a = new HashMap();
    static String e;
    private static h f;
    private static f g;
    Context b;
    KeyPair c;
    String d = BuildConfig.FLAVOR;

    protected d(Context context, String str, Bundle bundle) {
        this.b = context.getApplicationContext();
        this.d = str;
    }

    public static synchronized d a(Context context, Bundle bundle) {
        d dVar;
        synchronized (d.class) {
            String string = bundle == null ? BuildConfig.FLAVOR : bundle.getString("subtype");
            String str = string == null ? BuildConfig.FLAVOR : string;
            Context applicationContext = context.getApplicationContext();
            if (f == null) {
                f = new h(applicationContext);
                g = new f(applicationContext);
            }
            e = Integer.toString(FirebaseInstanceId.b(applicationContext));
            dVar = (d) a.get(str);
            if (dVar == null) {
                dVar = new d(applicationContext, str, bundle);
                a.put(str, dVar);
            }
        }
        return dVar;
    }

    KeyPair a() {
        if (this.c == null) {
            this.c = f.d(this.d);
        }
        if (this.c == null) {
            this.c = f.a(this.d);
        }
        return this.c;
    }

    public void a(String str, String str2, Bundle bundle) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        f.b(this.d, str, str2);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("sender", str);
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("subscription", str);
        bundle.putString("delete", "1");
        bundle.putString("X-delete", "1");
        bundle.putString("subtype", BuildConfig.FLAVOR.equals(this.d) ? str : this.d);
        String str3 = "X-subtype";
        if (!BuildConfig.FLAVOR.equals(this.d)) {
            str = this.d;
        }
        bundle.putString(str3, str);
        g.b(g.a(bundle, a()));
    }

    public String b(String str, String str2, Bundle bundle) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        Object obj = 1;
        if (bundle.getString("ttl") != null || "jwt".equals(bundle.getString("type"))) {
            obj = null;
        } else {
            a a = f.a(this.d, str, str2);
            if (!(a == null || a.b(e))) {
                return a.a;
            }
        }
        String c = c(str, str2, bundle);
        if (c == null || r0 == null) {
            return c;
        }
        f.a(this.d, str, str2, c, e);
        return c;
    }

    public void b() {
        f.b(this.d);
        this.c = null;
    }

    public h c() {
        return f;
    }

    public String c(String str, String str2, Bundle bundle) {
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("sender", str);
        String str3 = BuildConfig.FLAVOR.equals(this.d) ? str : this.d;
        if (!bundle.containsKey("legacy.register")) {
            bundle.putString("subscription", str);
            bundle.putString("subtype", str3);
            bundle.putString("X-subscription", str);
            bundle.putString("X-subtype", str3);
        }
        return g.b(g.a(bundle, a()));
    }

    public f d() {
        return g;
    }
}
