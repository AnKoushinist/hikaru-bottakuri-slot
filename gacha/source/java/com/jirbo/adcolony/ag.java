package com.jirbo.adcolony;

import java.util.ArrayList;

class ag {
    d a;
    boolean b = false;
    ArrayList<af> c = new ArrayList();

    ag(d dVar) {
        this.a = dVar;
    }

    void a() {
        int i = 0;
        c c = k.c(new f("zone_state.txt"));
        if (c != null) {
            this.c.clear();
            for (int i2 = 0; i2 < c.i(); i2++) {
                g b = c.b(i2);
                af afVar = new af();
                if (afVar.a(b)) {
                    this.c.add(afVar);
                }
            }
        }
        String[] strArr = this.a.a.l;
        int length = strArr.length;
        while (i < length) {
            a(strArr[i]);
            i++;
        }
    }

    void b() {
        int i = 0;
        l.a.b((Object) "Saving zone state...");
        this.b = false;
        c cVar = new c();
        String[] strArr = this.a.a.l;
        int length = strArr.length;
        while (i < length) {
            cVar.a(a(strArr[i]).a());
            i++;
        }
        k.a(new f("zone_state.txt"), cVar);
        l.a.b((Object) "Saved zone state");
    }

    int c() {
        return this.c.size();
    }

    af a(int i) {
        return (af) this.c.get(i);
    }

    af a(String str) {
        af afVar;
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            afVar = (af) this.c.get(i);
            if (afVar.a.equals(str)) {
                return afVar;
            }
        }
        this.b = true;
        afVar = new af(str);
        this.c.add(afVar);
        return afVar;
    }

    void d() {
        if (this.b) {
            b();
        }
    }
}
