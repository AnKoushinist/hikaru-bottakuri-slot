package com.vungle.publisher;

import com.vungle.publisher.we.a;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class xo extends a {
    @Inject
    xl.a a;
    @Inject
    Lazy<vf> b;
    @Inject
    Lazy<vo> d;

    @Inject
    xo() {
    }

    public final we a(cj cjVar, ji jiVar, String str) {
        vz vzVar;
        if (jiVar.a()) {
            vzVar = (vz) this.b.get();
        } else {
            vzVar = (vz) this.d.get();
        }
        xl xlVar = (xl) this.a.c();
        xlVar.e = cjVar;
        xlVar.f = jiVar;
        xlVar.b = str;
        return super.a(xlVar, vzVar);
    }
}
