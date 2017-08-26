package com.glossomads.d;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.b.f;
import com.glossomads.Logger.SugarDebugLogger;
import com.glossomads.View.t;
import com.glossomads.d.b.b;
import com.glossomads.e;
import com.glossomads.s;

public class d {
    public static final IntentFilter a = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    public static final IntentFilter b = new IntentFilter("android.media.RINGER_MODE_CHANGED");
    private c c;
    private b d;
    private a e;

    private static class a {
        private static final d a = new d();
    }

    private d() {
    }

    public static d a() {
        return a.a;
    }

    public static void b() {
        g();
        if (s.f() != null) {
            c();
        }
    }

    public static void c() {
        d();
        e();
        f();
    }

    public static void d() {
        if (a().c == null) {
            a().c = new c();
            s.f().registerReceiver(a().c, a);
            a().c.a(new e());
        }
    }

    public static void e() {
        if (a().d == null) {
            a().d = new b();
            s.f().registerReceiver(a().d, b);
            a().d.a(new f());
        }
    }

    public static void f() {
        if (a().e == null) {
            a().e = new a();
            f.a(s.f()).a(a().e, new IntentFilter("adViewReceiver"));
            a().e.a(new g());
        }
    }

    public static void g() {
        h();
        i();
        j();
    }

    public static void h() {
        d a = a();
        if (a.c != null) {
            Context f = s.f();
            if (f != null) {
                f.unregisterReceiver(a.c);
                a.c = null;
            }
        }
    }

    public static void i() {
        d a = a();
        if (a.d != null) {
            Context f = s.f();
            if (f != null) {
                f.unregisterReceiver(a.d);
                a.d = null;
            }
        }
    }

    public static void j() {
        d a = a();
        if (a.e != null && s.f() != null) {
            f.a(s.f()).a(a.e);
            a.e = null;
        }
    }

    public void k() {
        s.a().l();
    }

    public void l() {
        s.a().m();
    }

    public void a(b bVar) {
        s.a().a(bVar);
    }

    public static boolean m() {
        return a(s.f()) || b(s.f());
    }

    public static boolean a(Context context) {
        return c.a(context);
    }

    public static boolean b(Context context) {
        return c.b(context);
    }

    public static boolean n() {
        return b.a(s.a().d().getApplicationContext());
    }

    public static NetworkInfo c(Context context) {
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            SugarDebugLogger.d(e.getMessage());
            throw e;
        }
    }

    public void a(t tVar, a.b bVar) {
        e.a().a(tVar, bVar);
    }
}
