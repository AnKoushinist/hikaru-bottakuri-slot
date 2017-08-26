package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class hv extends jl {
    @Inject
    a d;

    @Singleton
    /* compiled from: vungle */
    static class a extends b<aea> {
        @Inject
        Provider<hv> a;

        protected final /* synthetic */ dl c_() {
            return (hv) this.a.get();
        }

        @Inject
        a() {
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.d;
    }

    @Inject
    hv() {
    }
}
