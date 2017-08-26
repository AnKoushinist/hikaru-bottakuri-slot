package com.glossomads.View;

import com.glossomads.View.w.a;
import com.glossomads.b.b;
import com.glossomads.e;

class h implements a {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public void a() {
        this.a.f = false;
        this.a.e = true;
        if (this.a.d != null) {
            this.a.d.a(this.a.e);
        }
    }

    public void a(b bVar) {
        this.a.f = false;
        this.a.e = false;
        this.a.c.a(bVar);
        if (bVar.b() == b.f) {
            com.glossomads.Logger.a.b(com.glossomads.Logger.a.a.loadStoreFailed, this.a.c.a(), this.a.c.b().l(), bVar.c(), bVar.a());
        } else {
            e.c(this.a.c);
        }
        if (this.a.d != null) {
            this.a.d.a(this.a.e);
        }
    }
}
