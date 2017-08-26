package com.adcolony.sdk;

import java.util.HashMap;

class ao {
    String a;
    private HashMap<Integer, Boolean> b = new HashMap();
    private w c;
    private bg d;
    private int e;

    ao(String str, int i) {
        this.a = str;
        this.e = i;
    }

    void a(o oVar) {
        if (this.c == null) {
            this.c = new w(this.a, this.e);
            this.d = new bg(this.a, this.e);
        }
        int b = bb.b(oVar.b(), "id");
        if (bb.c(oVar.b(), "use_sound_pool")) {
            this.b.put(Integer.valueOf(b), Boolean.valueOf(true));
            this.c.a(oVar);
            return;
        }
        this.b.put(Integer.valueOf(b), Boolean.valueOf(false));
        this.d.a(oVar);
    }

    void a() {
        this.c.a().autoPause();
        this.d.a();
    }

    void b() {
        this.c.a().autoResume();
        this.d.b();
    }

    void b(o oVar) {
        if (((Boolean) this.b.get(Integer.valueOf(bb.b(oVar.b(), "id")))).booleanValue()) {
            this.c.d(oVar);
        } else {
            this.d.b(oVar);
        }
    }

    void c(o oVar) {
        if (((Boolean) this.b.get(Integer.valueOf(bb.b(oVar.b(), "id")))).booleanValue()) {
            this.c.c(oVar);
        } else {
            this.d.c(oVar);
        }
    }

    void d(o oVar) {
        if (((Boolean) this.b.get(Integer.valueOf(bb.b(oVar.b(), "id")))).booleanValue()) {
            this.c.b(oVar);
        } else {
            this.d.d(oVar);
        }
    }

    void e(o oVar) {
        if (((Boolean) this.b.get(Integer.valueOf(bb.b(oVar.b(), "id")))).booleanValue()) {
            this.c.e(oVar);
        } else {
            this.d.e(oVar);
        }
    }

    bg c() {
        return this.d;
    }

    w d() {
        return this.c;
    }
}
