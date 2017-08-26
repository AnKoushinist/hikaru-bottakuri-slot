package com.vungle.publisher;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ki extends cp<kn, ki, ko> {
    @Inject
    a e;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.cp.a<kn, ki, ko> {
        @Inject
        Provider<ki> a;
        @Inject
        com.vungle.publisher.ko.a b;

        public final /* bridge */ /* synthetic */ List a(String str, String[] strArr) {
            return super.a(str, strArr);
        }

        public final /* bridge */ /* synthetic */ List c(int i) {
            return super.c(i);
        }

        protected final /* synthetic */ dl c_() {
            return (ki) this.a.get();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        @Inject
        a() {
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.e;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.cr.a b() {
        return this.e.b;
    }

    @Inject
    ki() {
    }
}
