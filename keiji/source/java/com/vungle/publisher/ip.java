package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ip extends jh<ip, hu, aea, jl, a, aep> {
    @Inject
    a a;

    @Singleton
    /* compiled from: vungle */
    public static class a extends a<ip, hu, aea, jl, a, aep> {
        @Inject
        Provider<ip> a;
        @Inject
        a b;

        public final /* bridge */ /* synthetic */ a a() {
            return this.b;
        }

        protected final /* synthetic */ jh b() {
            return (ip) this.a.get();
        }

        @Inject
        a() {
        }
    }

    protected final /* bridge */ /* synthetic */ a a() {
        return this.a;
    }

    @Inject
    ip() {
    }
}
