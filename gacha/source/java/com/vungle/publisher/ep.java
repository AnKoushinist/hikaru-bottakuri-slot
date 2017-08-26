package com.vungle.publisher;

import com.vungle.publisher.el.b;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ep extends em<eo, ep> {
    @Inject
    com.vungle.publisher.eo.a h;
    @Inject
    a i;
    @Inject
    public com.vungle.publisher.lg.a j;

    @Singleton
    /* compiled from: vungle */
    public static class a extends a<eo, ep, adr> {
        @Inject
        Provider<ep> b;

        public final /* bridge */ /* synthetic */ List a(String str, String[] strArr) {
            return super.a(str, strArr);
        }

        public final /* bridge */ /* synthetic */ List c(int i) {
            return super.c(i);
        }

        protected final /* synthetic */ dl c_() {
            ep epVar = (ep) this.b.get();
            epVar.g = this.a.a(epVar);
            return epVar;
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        @Inject
        a() {
        }

        final ep a(eo eoVar, adr com_vungle_publisher_adr, b bVar) {
            if (com_vungle_publisher_adr == null) {
                return null;
            }
            if (bVar == b.postRoll) {
                String str = com_vungle_publisher_adr.k;
                if (str == null) {
                    return null;
                }
                ep epVar = (ep) super.a((cj) eoVar, (ade) com_vungle_publisher_adr, bVar);
                epVar.a(str);
                return epVar;
            }
            throw new IllegalArgumentException("cannot create archive of type: " + bVar);
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.i;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a r() {
        return this.h;
    }

    @Inject
    protected ep() {
    }
}
