package com.vungle.publisher;

import com.vungle.publisher.jo.b;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ig extends jo<ia> {
    @Inject
    a e;

    @Singleton
    /* compiled from: vungle */
    static class a extends b<ig, ia> {
        @Inject
        Provider<ig> a;

        protected final /* bridge */ /* synthetic */ dl[] a(int i) {
            return new ig[i];
        }

        protected final /* synthetic */ dl c_() {
            return (ig) this.a.get();
        }

        @Inject
        protected a() {
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.dl.a a_() {
        return this.e;
    }

    @Inject
    protected ig() {
    }
}
