package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ns implements MembersInjector<nq> {
    static final /* synthetic */ boolean a = (!ns.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<a> c;
    private final Provider<agp> d;
    private final Provider<pn> e;
    private final Provider<agb.a> f;
    private final Provider<nq.a.a> g;
    private final Provider<nh.a> h;

    public final /* synthetic */ void injectMembers(Object obj) {
        nq nqVar = (nq) obj;
        if (nqVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        nqVar.g = (ql) this.b.get();
        nqVar.h = (a) this.c.get();
        nqVar.i = (agp) this.d.get();
        nqVar.j = (pn) this.e.get();
        nqVar.k = (agb.a) this.f.get();
        nqVar.l = (nq.a.a) this.g.get();
        nqVar.m = (nh.a) this.h.get();
    }

    private ns(Provider<ql> provider, Provider<a> provider2, Provider<agp> provider3, Provider<pn> provider4, Provider<agb.a> provider5, Provider<nq.a.a> provider6, Provider<nh.a> provider7) {
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

    public static MembersInjector<nq> a(Provider<ql> provider, Provider<a> provider2, Provider<agp> provider3, Provider<pn> provider4, Provider<agb.a> provider5, Provider<nq.a.a> provider6, Provider<nh.a> provider7) {
        return new ns(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
