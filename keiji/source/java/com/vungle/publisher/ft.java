package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ft extends jh<ft, eo, adr, jl, a, aep> {
    @Inject
    a a;

    @Singleton
    /* compiled from: vungle */
    public static class a extends a<ft, eo, adr, jl, a, aep> {
        @Inject
        Provider<ft> a;
        @Inject
        a b;

        public final /* bridge */ /* synthetic */ a a() {
            return this.b;
        }

        protected final /* synthetic */ jh b() {
            return (ft) this.a.get();
        }

        @Inject
        a() {
        }
    }

    protected final /* bridge */ /* synthetic */ a a() {
        return this.a;
    }

    @Inject
    ft() {
    }
}
