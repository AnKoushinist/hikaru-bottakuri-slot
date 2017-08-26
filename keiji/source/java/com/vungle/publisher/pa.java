package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class pa implements MembersInjector<oy> {
    static final /* synthetic */ boolean a = (!pa.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<a> c;
    private final Provider<agp> d;
    private final Provider<pn> e;
    private final Provider<oy.a.a> f;
    private final Provider<agg.a> g;
    private final Provider<op.a> h;
    private final Provider<ob.a> i;

    public final /* synthetic */ void injectMembers(Object obj) {
        oy oyVar = (oy) obj;
        if (oyVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        oyVar.g = (ql) this.b.get();
        oyVar.h = (a) this.c.get();
        oyVar.i = (agp) this.d.get();
        oyVar.j = (pn) this.e.get();
        oyVar.k = (oy.a.a) this.f.get();
        oyVar.l = (agg.a) this.g.get();
        oyVar.m = (op.a) this.h.get();
        oyVar.n = (ob.a) this.i.get();
    }

    private pa(Provider<ql> provider, Provider<a> provider2, Provider<agp> provider3, Provider<pn> provider4, Provider<oy.a.a> provider5, Provider<agg.a> provider6, Provider<op.a> provider7, Provider<ob.a> provider8) {
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
                                    if (a || provider8 != null) {
                                        this.i = provider8;
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
        throw new AssertionError();
    }

    public static MembersInjector<oy> a(Provider<ql> provider, Provider<a> provider2, Provider<agp> provider3, Provider<pn> provider4, Provider<oy.a.a> provider5, Provider<agg.a> provider6, Provider<op.a> provider7, Provider<ob.a> provider8) {
        return new pa(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }
}
