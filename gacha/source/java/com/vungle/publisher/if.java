package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class if extends jn<if, ia, ig, hu, ht> {
    @Inject
    a v;
    @Inject
    a w;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.jn.a<if, ia, ig, hu, ht, aea> {
        @Inject
        a c;
        @Inject
        com.vungle.publisher.hu.a e;
        @Inject
        Provider<if> f;

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a a() {
            return this.e;
        }

        protected final /* bridge */ /* synthetic */ dl[] a(int i) {
            return new if[i];
        }

        protected final /* synthetic */ dl c_() {
            return (if) this.f.get();
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.cp.a e() {
            return this.c;
        }

        @Inject
        protected a() {
        }

        public final j f() {
            return j.vungle_streaming;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.v;
    }

    public final /* bridge */ /* synthetic */ com.vungle.publisher.cq.a b() {
        return this.v;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.cp.a e() {
        return this.w;
    }

    @Inject
    protected if() {
    }
}
