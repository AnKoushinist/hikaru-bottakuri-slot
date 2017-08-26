package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class fe extends jm<fj, fe, fk> {
    @Inject
    a e;

    @Singleton
    /* compiled from: vungle */
    static class a extends com.vungle.publisher.jm.a<fj, fe, fk, eo, en> {
        @Inject
        Provider<fe> a;
        @Inject
        a b;

        protected final /* bridge */ /* synthetic */ dl[] a(int i) {
            return new fe[i];
        }

        protected final /* synthetic */ dl c_() {
            return (fe) this.a.get();
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
    protected fe() {
    }
}
