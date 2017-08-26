package com.glossomads;

import com.glossomads.Logger.SugarDebugLogger;
import com.glossomads.Model.b;
import com.glossomads.Model.c;
import com.glossomads.Model.f;
import com.glossomads.Model.g;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;

public class t {
    private g a;
    private a b;
    private boolean c;
    private int d;
    private long e = 0;
    private long f = 60;
    private b g;

    public enum a {
        IDLE,
        RUNNING,
        STOP
    }

    public t(String str, int i) {
        this.a = new g(str);
        this.b = a.IDLE;
        this.d = i;
        this.c = false;
        this.g = new b(this.a.a());
    }

    public void a() {
        if (!f()) {
            b();
        }
        c();
    }

    public void b() {
        e();
        r();
    }

    public void c() {
        this.b = a.IDLE;
    }

    public void d() {
        this.b = a.STOP;
    }

    public void e() {
        this.b = a.RUNNING;
    }

    public boolean f() {
        if (this.b == a.STOP) {
            return true;
        }
        return false;
    }

    public boolean g() {
        if (this.b == a.IDLE) {
            return true;
        }
        return false;
    }

    public boolean h() {
        return this.c;
    }

    public void i() {
        d();
    }

    public void j() {
        c();
    }

    public void k() {
        this.f = 60;
    }

    public a l() {
        return this.b;
    }

    public int m() {
        return this.d;
    }

    public String n() {
        return this.a.a();
    }

    public boolean o() {
        return this.a.b();
    }

    public boolean p() {
        return this.a.c();
    }

    public boolean q() {
        return this.a.d();
    }

    public boolean a(String str) {
        boolean d = d(str);
        if (!d) {
            Collection v = v();
            String obj = SugarUtil.isEmptyCollection(v) ? BuildConfig.FLAVOR : v.toString();
            com.glossomads.Logger.a.c(com.glossomads.Logger.a.a.zoneNotReady, n(), obj);
        }
        return d;
    }

    public void r() {
        if (!this.c) {
            if (this.e == 0 || !s.a().c() || System.currentTimeMillis() >= this.e + (this.f * 1000)) {
                this.c = true;
                this.e = System.currentTimeMillis();
                f fVar = new f(this.a.a());
                if (fVar.c() != null) {
                    d dVar = new d(fVar);
                    dVar.a(new u(this));
                    com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.loadAdStart, fVar.a(), String.valueOf(this.f), l.d());
                    dVar.a();
                }
            }
        }
    }

    public void a(c cVar) {
        this.c = false;
        this.a.a(cVar.e());
        if (!cVar.a()) {
            this.f = (long) cVar.d();
        }
        c();
        Collection v = v();
        if (SugarUtil.isEmptyCollection(v)) {
            SugarDebugLogger.d("load ad (zoneId = " + this.a.a() + ", oldImpIds is empty)");
        } else {
            SugarDebugLogger.d("load ad (zoneId = " + this.a.a() + ", oldImpIds = " + v.toString() + ")");
        }
        if (cVar.c().isEmpty()) {
            SugarDebugLogger.d("load ad (zoneId = " + this.a.a() + ", newImpIds is empty)");
        } else {
            SugarDebugLogger.d("load ad (zoneId = " + this.a.a() + ", newImpIds = " + cVar.c().toString() + ")");
        }
        this.g.a(cVar);
        v.a(this);
        com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.loadAdFinish, this.a.a(), l.d());
        a(this.g.d());
    }

    public void a(boolean z) {
        if (!z) {
            this.g.e();
        }
        this.c = false;
        c();
    }

    public com.glossomads.Model.a b(String str) {
        if (SugarUtil.isEmptyValue(str) || !this.a.a(str)) {
            return null;
        }
        if (v.a(this.a.a())) {
            Object c = c(str);
            if (SugarUtil.isEmptyCollection(c)) {
                return null;
            }
            com.glossomads.Model.a aVar = (com.glossomads.Model.a) c.get(0);
            this.g.b(aVar);
            new ArrayList(new ArrayList(v())).remove(aVar.l());
            com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.queueIsNow, this.a.a(), r2.toString(), r1.toString());
            return aVar;
        }
        com.glossomads.Logger.a.a(this.a.a(), str);
        return null;
    }

    public List<com.glossomads.Model.a> s() {
        List<com.glossomads.Model.a> arrayList = new ArrayList();
        for (com.glossomads.Model.a aVar : this.g.a()) {
            if (a(aVar, null)) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    public List<com.glossomads.Model.a> c(String str) {
        List<com.glossomads.Model.a> arrayList = new ArrayList();
        for (com.glossomads.Model.a aVar : this.g.a()) {
            if (a(aVar, str)) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    public boolean t() {
        return s().size() > 0;
    }

    public ArrayList<String> u() {
        ArrayList<String> arrayList = new ArrayList();
        for (com.glossomads.Model.a aVar : this.g.a()) {
            if (a(aVar)) {
                arrayList.add(aVar.l());
            }
        }
        return arrayList;
    }

    public boolean d(String str) {
        return e(str).size() > 0;
    }

    public List<String> v() {
        return this.g.f();
    }

    public List<URL> w() {
        return this.g.g();
    }

    public boolean a(URL url) {
        return this.g.a(url);
    }

    public boolean a(com.glossomads.Model.a aVar) {
        return j.a().a(aVar.b());
    }

    public List<String> x() {
        return e(null);
    }

    public List<String> e(String str) {
        List<String> arrayList = new ArrayList();
        List s;
        if (SugarUtil.isEmptyValue(str)) {
            s = s();
        } else {
            s = c(str);
        }
        for (com.glossomads.Model.a l : r0) {
            arrayList.add(l.l());
        }
        return arrayList;
    }

    public boolean a(com.glossomads.Model.a aVar, String str) {
        if (!aVar.p() || !a(aVar)) {
            return false;
        }
        if (SugarUtil.isNotEmptyValue(str)) {
            return str.equals(aVar.m());
        }
        return true;
    }

    public void a(List<com.glossomads.Model.a> list) {
        for (com.glossomads.Model.a aVar : list) {
            j.a().a(n(), aVar.b(), aVar.j(), aVar.c(), aVar.d());
        }
    }
}
