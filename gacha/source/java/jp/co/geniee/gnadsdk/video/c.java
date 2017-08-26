package jp.co.geniee.gnadsdk.video;

import java.util.ArrayList;
import java.util.List;

/* compiled from: VASTXmlParser */
public class c {
    private volatile c a;
    private String b;
    private List<String> c;
    private List<String> d;
    private String e;
    private List<a> f;

    /* compiled from: VASTXmlParser */
    public class a {
        private int a;
        private String b;

        public int a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }
    }

    public List<String> a() {
        List<String> arrayList = new ArrayList();
        arrayList.addAll(this.d);
        if (this.a != null) {
            arrayList.addAll(this.a.a());
        }
        return arrayList;
    }

    public String b() {
        if (this.e != null || this.a == null) {
            return this.e;
        }
        return this.a.b();
    }

    public List<String> a(int i) {
        List<String> arrayList = new ArrayList();
        for (a aVar : this.f) {
            if (aVar.a() == i) {
                arrayList.add(aVar.b());
            }
        }
        if (this.a != null) {
            arrayList.addAll(this.a.a(i));
        }
        return arrayList;
    }

    public String c() {
        String str = this.b;
        if (str != null || this.a == null) {
            return str;
        }
        return this.a.c();
    }

    public List<String> d() {
        List<String> arrayList = new ArrayList();
        if (this.c != null) {
            arrayList.addAll(this.c);
        }
        if (this.a != null) {
            arrayList.addAll(this.a.d());
        }
        return arrayList;
    }
}
