package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class zy implements MembersInjector<zw> {
    static final /* synthetic */ boolean a = (!zy.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<wn> c;
    private final Provider<bt> d;
    private final Provider<ql> e;
    private final Provider<aea.a> f;
    private final Provider<adu.a> g;
    private final Provider<adf.a> h;

    public final /* synthetic */ void injectMembers(Object obj) {
        zw zwVar = (zw) obj;
        if (zwVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        wa.a(zwVar, this.b);
        wa.b(zwVar, this.c);
        ww.a(zwVar, this.d);
        zwVar.a = (ql) this.e.get();
        zwVar.b = (aea.a) this.f.get();
        zwVar.i = (adu.a) this.g.get();
        zwVar.j = (adf.a) this.h.get();
    }

    private zy(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3, Provider<ql> provider4, Provider<aea.a> provider5, Provider<adu.a> provider6, Provider<adf.a> provider7) {
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

    public static MembersInjector<zw> a(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3, Provider<ql> provider4, Provider<aea.a> provider5, Provider<adu.a> provider6, Provider<adf.a> provider7) {
        return new zy(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
