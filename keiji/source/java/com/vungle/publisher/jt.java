package com.vungle.publisher;

import com.vungle.publisher.el.b;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class jt extends em<js, jt> {
    @Inject
    com.vungle.publisher.js.a h;
    @Inject
    a i;

    @Singleton
    /* compiled from: vungle */
    public static class a extends a<js, jt, aef> {
        @Inject
        Provider<jt> b;

        public final /* bridge */ /* synthetic */ List a(String str, String[] strArr) {
            return super.a(str, strArr);
        }

        public final /* bridge */ /* synthetic */ List c(int i) {
            return super.c(i);
        }

        protected final /* synthetic */ dl c_() {
            jt jtVar = (jt) this.b.get();
            jtVar.g = this.a.a(jtVar);
            return jtVar;
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        @Inject
        a() {
        }

        final jt a(js jsVar, aef com_vungle_publisher_aef, b bVar) {
            if (com_vungle_publisher_aef == null) {
                return null;
            }
            String str = com_vungle_publisher_aef.m;
            if (str == null) {
                return null;
            }
            jt jtVar = (jt) super.a((cj) jsVar, (ade) com_vungle_publisher_aef, bVar);
            jtVar.a(str);
            return jtVar;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.i;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a r() {
        return this.h;
    }

    @Inject
    jt() {
    }

    public final String C() {
        return qx.a(u(), "index.html");
    }
}
