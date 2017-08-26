package com.b.a.b;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import b.a.a.a.a.a.b;
import b.a.a.a.a.b.m;
import b.a.a.a.a.b.o;
import b.a.a.a.a.b.o.a;
import b.a.a.a.a.b.s;
import b.a.a.a.a.f.d;
import b.a.a.a.a.g.f;
import b.a.a.a.a.g.q;
import b.a.a.a.a.g.t;
import b.a.a.a.i;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: Beta */
public class c extends i<Boolean> implements m {
    private final b<String> a = new b();
    private final h b = new h();
    private j c;

    protected /* synthetic */ Object e() {
        return d();
    }

    @TargetApi(14)
    protected boolean l_() {
        this.c = a(VERSION.SDK_INT, (Application) E().getApplicationContext());
        return true;
    }

    protected Boolean d() {
        b.a.a.a.c.h().a("Beta", "Beta kit initializing...");
        Context E = E();
        o D = D();
        if (TextUtils.isEmpty(a(E, D.j()))) {
            b.a.a.a.c.h().a("Beta", "A Beta device token was not found for this app");
            return Boolean.valueOf(false);
        }
        b.a.a.a.c.h().a("Beta", "Beta device token is present, checking for app updates.");
        f h = h();
        d a = a(E);
        if (a(h, a)) {
            this.c.a(E, this, D, h, a, new d(this), new s(), new b.a.a.a.a.e.b(b.a.a.a.c.h()));
        }
        return Boolean.valueOf(true);
    }

    @TargetApi(14)
    j a(int i, Application application) {
        if (i >= 14) {
            return new b(F().e(), F().f());
        }
        return new i();
    }

    public Map<a, String> f() {
        CharSequence a = a(E(), D().j());
        Map<a, String> hashMap = new HashMap();
        if (!TextUtils.isEmpty(a)) {
            hashMap.put(a.FONT_TOKEN, a);
        }
        return hashMap;
    }

    public String b() {
        return "com.crashlytics.sdk.android:beta";
    }

    public String a() {
        return "1.1.4.92";
    }

    @TargetApi(11)
    boolean a(String str, int i) {
        if (i < 11) {
            return str == null;
        } else {
            return "io.crash.air".equals(str);
        }
    }

    boolean a(f fVar, d dVar) {
        return (fVar == null || TextUtils.isEmpty(fVar.a) || dVar == null) ? false : true;
    }

    private String a(Context context, String str) {
        if (a(str, VERSION.SDK_INT)) {
            b.a.a.a.c.h().a("Beta", "App was possibly installed by Beta. Getting device token");
            try {
                String str2 = (String) this.a.a(context, this.b);
                if (BuildConfig.FLAVOR.equals(str2)) {
                    str2 = null;
                }
                return str2;
            } catch (Throwable e) {
                b.a.a.a.c.h().e("Beta", "Failed to load the Beta device token", e);
                return null;
            }
        }
        b.a.a.a.c.h().a("Beta", "App was not installed by Beta. Skipping device token");
        return null;
    }

    private f h() {
        t b = q.a().b();
        if (b != null) {
            return b.f;
        }
        return null;
    }

    private d a(Context context) {
        InputStream open;
        d a;
        Throwable th;
        Throwable e;
        Throwable th2;
        d dVar;
        InputStream inputStream = null;
        try {
            open = context.getAssets().open("crashlytics-build.properties");
            Object obj;
            if (open != null) {
                try {
                    a = d.a(open);
                } catch (Throwable e2) {
                    th = e2;
                    obj = inputStream;
                    th2 = th;
                    try {
                        b.a.a.a.c.h().e("Beta", "Error reading Beta build properties", th2);
                        if (open != null) {
                            try {
                                open.close();
                            } catch (Throwable th22) {
                                b.a.a.a.c.h().e("Beta", "Error closing Beta build properties asset", th22);
                            }
                        }
                        return dVar;
                    } catch (Throwable th3) {
                        e2 = th3;
                        if (open != null) {
                            try {
                                open.close();
                            } catch (Throwable th222) {
                                b.a.a.a.c.h().e("Beta", "Error closing Beta build properties asset", th222);
                            }
                        }
                        throw e2;
                    }
                }
                try {
                    b.a.a.a.c.h().a("Beta", a.d + " build properties: " + a.b + " (" + a.a + ")" + " - " + a.c);
                    dVar = a;
                } catch (Throwable e22) {
                    th = e22;
                    dVar = a;
                    th222 = th;
                    b.a.a.a.c.h().e("Beta", "Error reading Beta build properties", th222);
                    if (open != null) {
                        open.close();
                    }
                    return dVar;
                }
            }
            obj = inputStream;
            if (open != null) {
                try {
                    open.close();
                } catch (Throwable th2222) {
                    b.a.a.a.c.h().e("Beta", "Error closing Beta build properties asset", th2222);
                }
            }
        } catch (Throwable e222) {
            open = inputStream;
            InputStream inputStream2 = inputStream;
            th2222 = e222;
            dVar = inputStream2;
            b.a.a.a.c.h().e("Beta", "Error reading Beta build properties", th2222);
            if (open != null) {
                open.close();
            }
            return dVar;
        } catch (Throwable th4) {
            e222 = th4;
            open = inputStream;
            if (open != null) {
                open.close();
            }
            throw e222;
        }
        return dVar;
    }

    String g() {
        return b.a.a.a.a.b.i.b(E(), "com.crashlytics.ApiEndpoint");
    }
}
