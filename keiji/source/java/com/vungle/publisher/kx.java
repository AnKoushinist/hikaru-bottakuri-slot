package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class kx extends jh<kx, js, aef, kd, com.vungle.publisher.kd.a, abp> {
    @Inject
    a a;

    @Singleton
    /* compiled from: vungle */
    public static class a extends a<kx, js, aef, kd, com.vungle.publisher.kd.a, abp> {
        @Inject
        Provider<kx> a;
        @Inject
        com.vungle.publisher.kd.a b;

        public final /* bridge */ /* synthetic */ a a() {
            return this.b;
        }

        protected final /* synthetic */ jh b() {
            return (kx) this.a.get();
        }

        @Inject
        a() {
        }
    }

    protected final /* bridge */ /* synthetic */ a a() {
        return this.a;
    }

    @Inject
    kx() {
    }
}
