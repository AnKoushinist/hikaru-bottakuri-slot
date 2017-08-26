package com.glossomads;

import android.os.Handler;
import com.glossomads.Logger.SugarDebugLogger;
import com.glossomads.d.d;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;

public class v {
    public boolean a;
    private boolean b;
    private ConcurrentHashMap<String, t> c;
    private String d;
    private boolean e;

    private static class a {
        private static final v a = new v();
    }

    private v() {
    }

    public static v a() {
        return a.a;
    }

    public static void b() {
        v a = a();
        a.b = false;
        a.a = false;
        a.e = false;
        a.d = null;
        if (a.c == null) {
            a.c = new ConcurrentHashMap();
        } else {
            a.c.clear();
        }
    }

    public static void a(List list) {
        b(list);
        if (m.m()) {
            a().b = true;
            a().e();
        }
        c();
    }

    private static void b(List list) {
        int i = 1;
        for (int i2 = 0; i2 < list.size(); i2++) {
            a((String) list.get(i2), new t((String) list.get(i2), i));
            i++;
        }
    }

    public static void c() {
        a().a = true;
        m.d();
    }

    public void d() {
        this.a = false;
        if (!this.b) {
            this.b = true;
            e();
        }
    }

    public void e() {
        if (this.b) {
            g();
            if (s.a().c()) {
                new Handler().postDelayed(new w(this), RFLConstants.GPS_TIME);
            }
        }
    }

    public static void a(String str, t tVar) {
        a().c.put(str, tVar);
    }

    public static boolean a(String str) {
        return a().c.containsKey(str);
    }

    public static t b(String str) {
        if (f()) {
            return null;
        }
        return (t) a().c.get(str);
    }

    public static boolean f() {
        return a().c.isEmpty();
    }

    public void g() {
        for (int h = a().h(); h < 2; h++) {
            t i = a().i();
            if (i != null) {
                a().d = i.n();
                i.a();
            }
        }
    }

    public int h() {
        int i = 0;
        for (Entry value : this.c.entrySet()) {
            int i2;
            if (com.glossomads.t.a.RUNNING == ((t) value.getValue()).l()) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    public t i() {
        int i = 0;
        if (this.d != null) {
            i = ((t) this.c.get(this.d)).m();
        }
        for (int i2 = 1; i2 <= this.c.size(); i2++) {
            int i3 = i + i2;
            if (i3 > this.c.size()) {
                i3 = 1;
            }
            t a = a(i3);
            if (a != null && a.g()) {
                return a;
            }
        }
        return null;
    }

    public t a(int i) {
        for (Entry value : this.c.entrySet()) {
            t tVar = (t) value.getValue();
            if (tVar.m() == i) {
                return tVar;
            }
        }
        return null;
    }

    public static void a(boolean z) {
        if (s.a().r() && !a().a) {
            if (z) {
                a().d();
            } else {
                a().b = false;
            }
            b(z);
        }
    }

    public static void j() {
        a().b = false;
        b(false);
    }

    public static void k() {
        if (!a().a) {
            a().d();
            b(true);
            c();
        }
    }

    public static void b(boolean z) {
        for (Entry entry : a().c.entrySet()) {
            t tVar = (t) entry.getValue();
            if (z) {
                com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.loadAdResumed, tVar.n());
                s.a().a(tVar.n(), s.a().b((String) entry.getKey()));
                tVar.j();
            } else {
                com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.loadAdPaused, tVar.n());
                s.a().a(tVar.n(), false);
                tVar.i();
            }
        }
    }

    public static void l() {
        v a = a();
        boolean f = m.f();
        if (!f && a.e) {
            for (Entry value : a.c.entrySet()) {
                ((t) value.getValue()).k();
            }
        }
        a.e = f;
    }

    public static void c(boolean z) {
        a().e = z;
    }

    public static boolean c(String str) {
        t b = b(str);
        if (b != null) {
            return b.o();
        }
        return false;
    }

    public static boolean d(String str) {
        t b = b(str);
        if (b != null) {
            return b.p();
        }
        return false;
    }

    public static boolean e(String str) {
        t b = b(str);
        if (b != null) {
            return b.q();
        }
        return false;
    }

    public static boolean f(String str) {
        if (m.E() || !d.m() || SugarUtil.isEmptyValue(str)) {
            return false;
        }
        t b = b(str);
        if (a(str) && b != null) {
            return b.a(null);
        }
        com.glossomads.Logger.a.b(com.glossomads.Logger.a.a.configureInvalidZoneId, str);
        return false;
    }

    public static boolean a(String str, String str2) {
        if (m.E() || !d.m() || SugarUtil.isEmptyValue(str)) {
            return false;
        }
        t b = b(str);
        if (a(str) && b != null) {
            return b.a(str2);
        }
        com.glossomads.Logger.a.a(str, str2);
        return false;
    }

    public static void a(t tVar) {
        s.a().a(tVar.n(), false);
    }

    public static com.glossomads.Model.a b(String str, String str2) {
        t b = b(str);
        if (b == null) {
            return null;
        }
        com.glossomads.Model.a b2 = b.b(str2);
        if (b2 == null) {
            s.a().a(str, false);
            return b2;
        } else if (b.t()) {
            return b2;
        } else {
            s.a().a(str, false);
            return b2;
        }
    }

    public static ArrayList<String> g(String str) {
        t b = b(str);
        if (b != null) {
            return b.u();
        }
        return null;
    }

    public static List<URL> m() {
        List<URL> arrayList = new ArrayList();
        for (Entry value : a().c.entrySet()) {
            for (URL url : ((t) value.getValue()).w()) {
                if (!arrayList.contains(url)) {
                    arrayList.add(url);
                }
            }
        }
        return arrayList;
    }

    public static List<String> a(URL url) {
        List<String> arrayList = new ArrayList();
        for (Entry value : a().c.entrySet()) {
            t tVar = (t) value.getValue();
            if (tVar.a(url)) {
                arrayList.add(tVar.n());
            }
        }
        return arrayList;
    }

    public static List<String> h(String str) {
        List<String> arrayList = new ArrayList();
        t b = b(str);
        if (b != null) {
            return b.x();
        }
        return arrayList;
    }

    public static void a(String str, String str2, boolean z) {
        try {
            for (String str3 : a(new URL(str2))) {
                t b = b(str3);
                if (!(b == null || b.h())) {
                    s.a().a(str3, z);
                }
            }
            if (z) {
                com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.assetDownloaded, str2, str);
                return;
            }
            com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.assetDownloadFailed, str2, str);
        } catch (Exception e) {
            SugarDebugLogger.printStackTrace(e);
        }
    }
}
