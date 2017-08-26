package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class hd extends cq<hd, gy, he, gs> implements ec {
    @Inject
    a v;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.cq.a<hd, gy, he, gs, adu> {
        @Inject
        com.vungle.publisher.gs.a c;
        @Inject
        com.vungle.publisher.gy.a e;
        @Inject
        Provider<hd> f;

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.cj.a a() {
            return this.c;
        }

        protected final /* bridge */ /* synthetic */ dl[] a(int i) {
            return new hd[i];
        }

        protected final /* synthetic */ dl c_() {
            return (hd) this.f.get();
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.cp.a e() {
            return this.e;
        }

        @Inject
        a() {
        }

        public final j f() {
            return j.third_party_mraid;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.v;
    }

    public final /* bridge */ /* synthetic */ com.vungle.publisher.cq.a b() {
        return this.v;
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.cp.a e() {
        return this.v.e;
    }

    @Inject
    hd() {
    }

    public final void a_(Long l) {
    }
}
