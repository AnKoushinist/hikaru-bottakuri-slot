package com.vungle.publisher;

import com.vungle.publisher.js.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class le implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!le.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<ql> c;
    private final Provider<agt> d;
    private final Provider<js> e;
    private final Provider<a> f;
    private final Provider<String> g;
    private final Provider<jy.a> h;
    private final Provider<jt.a> i;
    private final Provider<kd.a> j;
    private final Provider<kx.a> k;
    private final Provider<jc.a> l;
    private final Provider<ds.a> m;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (cf) this.b.get();
        aVar.a = (ql) this.c.get();
        aVar.b = (agt) this.d.get();
        aVar.c = this.e;
        aVar.e = (agt) this.d.get();
        aVar.f = (a) this.f.get();
        aVar.g = this.g;
        aVar.h = (jy.a) this.h.get();
        aVar.i = (jt.a) this.i.get();
        aVar.j = (kd.a) this.j.get();
        aVar.k = (kx.a) this.k.get();
        aVar.l = (jc.a) this.l.get();
        aVar.m = (ds.a) this.m.get();
    }

    private le(Provider<cf> provider, Provider<ql> provider2, Provider<agt> provider3, Provider<js> provider4, Provider<a> provider5, Provider<String> provider6, Provider<jy.a> provider7, Provider<jt.a> provider8, Provider<kd.a> provider9, Provider<kx.a> provider10, Provider<jc.a> provider11, Provider<ds.a> provider12) {
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
                                        if (a || provider9 != null) {
                                            this.j = provider9;
                                            if (a || provider10 != null) {
                                                this.k = provider10;
                                                if (a || provider11 != null) {
                                                    this.l = provider11;
                                                    if (a || provider12 != null) {
                                                        this.m = provider12;
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
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<cf> provider, Provider<ql> provider2, Provider<agt> provider3, Provider<js> provider4, Provider<a> provider5, Provider<String> provider6, Provider<jy.a> provider7, Provider<jt.a> provider8, Provider<kd.a> provider9, Provider<kx.a> provider10, Provider<jc.a> provider11, Provider<ds.a> provider12) {
        return new le(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }
}
