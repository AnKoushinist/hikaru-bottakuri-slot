package com.glossomads.Model;

import com.glossomads.SugarUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class b {
    public String a;
    private List<a> b = new ArrayList();

    public b(String str) {
        this.a = str;
    }

    public List<a> a() {
        return this.b;
    }

    public void a(c cVar) {
        HashMap b = cVar.b();
        if (b != null) {
            b();
            ArrayList c = cVar.c();
            for (int i = 0; i < c.size(); i++) {
                String str = (String) c.get(i);
                a aVar = (a) b.get(str);
                if (aVar == null) {
                    aVar = a(str);
                }
                a(aVar);
            }
            c();
        }
    }

    public void b() {
        if (!SugarUtil.isEmptyCollection(this.b)) {
            for (a a : this.b) {
                a.a(false);
            }
        }
    }

    public void c() {
        if (!SugarUtil.isEmptyCollection(this.b)) {
            List<a> arrayList = new ArrayList();
            for (a aVar : this.b) {
                if (!aVar.p()) {
                    arrayList.add(aVar);
                }
            }
            for (a aVar2 : arrayList) {
                this.b.remove(aVar2);
            }
        }
    }

    public void a(a aVar) {
        if (aVar != null) {
            a a = a(aVar.l());
            if (a != null) {
                this.b.remove(a);
            }
            aVar.a(true);
            this.b.add(aVar);
        }
    }

    public List<a> d() {
        List<a> arrayList = new ArrayList();
        for (a aVar : this.b) {
            if (aVar.p()) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    public a a(String str) {
        if (SugarUtil.isEmptyValue(str) || SugarUtil.isEmptyCollection(this.b)) {
            return null;
        }
        for (a aVar : this.b) {
            if (str.equals(aVar.l())) {
                return aVar;
            }
        }
        return null;
    }

    public void e() {
        this.b.clear();
    }

    public void b(a aVar) {
        if (aVar != null && this.b.contains(aVar)) {
            this.b.remove(aVar);
        }
    }

    public List<String> f() {
        List<String> arrayList = new ArrayList();
        for (a aVar : d()) {
            if (SugarUtil.isNotEmptyValue(aVar.l())) {
                arrayList.add(aVar.l());
            }
        }
        return arrayList;
    }

    public List<URL> g() {
        List<URL> arrayList = new ArrayList();
        for (a b : this.b) {
            URL b2 = b.b();
            if (!arrayList.contains(b2)) {
                arrayList.add(b2);
            }
        }
        return arrayList;
    }

    public boolean a(URL url) {
        for (a b : this.b) {
            if (url.toString().equals(b.b().toString())) {
                return true;
            }
        }
        return false;
    }
}
