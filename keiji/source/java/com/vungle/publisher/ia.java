package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ia extends jm<if, ia, ig> {
    @Inject
    a e;

    @Singleton
    /* compiled from: vungle */
    static class a extends com.vungle.publisher.jm.a<if, ia, ig, hu, ht> {
        @Inject
        Provider<ia> a;
        @Inject
        a b;

        protected final /* bridge */ /* synthetic */ dl[] a(int i) {
            return new ia[i];
        }

        protected final /* synthetic */ dl c_() {
            return (ia) this.a.get();
        }

        @Inject
        protected a() {
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.e;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.cr.a b() {
        return this.e.b;
    }

    @Inject
    protected ia() {
    }
}
