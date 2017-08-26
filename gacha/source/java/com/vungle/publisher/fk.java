package com.vungle.publisher;

import com.vungle.publisher.jo.b;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class fk extends jo<fe> {
    @Inject
    a e;

    @Singleton
    /* compiled from: vungle */
    static class a extends b<fk, fe> {
        @Inject
        Provider<fk> a;

        protected final /* bridge */ /* synthetic */ dl[] a(int i) {
            return new fk[i];
        }

        protected final /* synthetic */ dl c_() {
            return (fk) this.a.get();
        }

        @Inject
        protected a() {
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.e;
    }

    @Inject
    protected fk() {
    }
}
