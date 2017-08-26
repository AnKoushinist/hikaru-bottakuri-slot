package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class zm implements MembersInjector<zk> {
    static final /* synthetic */ boolean a = (!zm.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<wn> c;
    private final Provider<bt> d;
    private final Provider<adj.a> e;
    private final Provider<pv> f;
    private final Provider<ql> g;
    private final Provider<zb> h;

    public final /* synthetic */ void injectMembers(Object obj) {
        zk zkVar = (zk) obj;
        if (zkVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        wa.a(zkVar, this.b);
        wa.b(zkVar, this.c);
        ww.a(zkVar, this.d);
        zkVar.a = (adj.a) this.e.get();
        zkVar.b = (pv) this.f.get();
        zkVar.i = (ql) this.g.get();
        zkVar.j = this.h;
    }

    private zm(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3, Provider<adj.a> provider4, Provider<pv> provider5, Provider<ql> provider6, Provider<zb> provider7) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        if (a || provider5 != null) {
                            this.f = provider5;
                            if (a || provider6 != null) {
                                this.g = provider6;
                                if (a || provider7 != null) {
                                    this.h = provider7;
                                    return;
                                }
                                throw new AssertionError();
                            }
                            throw new AssertionError();
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<zk> a(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3, Provider<adj.a> provider4, Provider<pv> provider5, Provider<ql> provider6, Provider<zb> provider7) {
        return new zm(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
