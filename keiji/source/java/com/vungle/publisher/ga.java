package com.vungle.publisher;

import com.vungle.publisher.eo.a;
import com.vungle.publisher.jp.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ga implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!ga.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<ql> c;
    private final Provider<agt> d;
    private final Provider<String> e;
    private final Provider<ep.a> f;
    private final Provider<b> g;
    private final Provider<eo> h;
    private final Provider<en.a> i;
    private final Provider<bt> j;
    private final Provider<eu.a> k;
    private final Provider<ds.a> l;
    private final Provider<ft.a> m;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (cf) this.b.get();
        aVar.a = (ql) this.c.get();
        aVar.b = (agt) this.d.get();
        aVar.c = this.e;
        aVar.e = (ep.a) this.f.get();
        aVar.f = (b) this.g.get();
        aVar.g = this.h;
        aVar.h = (en.a) this.i.get();
        aVar.i = (bt) this.j.get();
        aVar.j = (eu.a) this.k.get();
        aVar.k = (ds.a) this.l.get();
        aVar.l = (ft.a) this.m.get();
    }

    private ga(Provider<cf> provider, Provider<ql> provider2, Provider<agt> provider3, Provider<String> provider4, Provider<ep.a> provider5, Provider<b> provider6, Provider<eo> provider7, Provider<en.a> provider8, Provider<bt> provider9, Provider<eu.a> provider10, Provider<ds.a> provider11, Provider<ft.a> provider12) {
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

    public static MembersInjector<a> a(Provider<cf> provider, Provider<ql> provider2, Provider<agt> provider3, Provider<String> provider4, Provider<ep.a> provider5, Provider<b> provider6, Provider<eo> provider7, Provider<en.a> provider8, Provider<bt> provider9, Provider<eu.a> provider10, Provider<ds.a> provider11, Provider<ft.a> provider12) {
        return new ga(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }
}
