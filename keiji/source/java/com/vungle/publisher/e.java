package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.a.a;
import com.vungle.publisher.jp.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class e implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!e.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<py> c;
    private final Provider<Context> d;
    private final Provider<pn> e;
    private final Provider<Class> f;
    private final Provider<Class> g;
    private final Provider<bt> h;
    private final Provider<uq> i;
    private final Provider<b> j;
    private final Provider<a> k;
    private final Provider<c> l;
    private final Provider<yc> m;
    private final Provider<pv> n;
    private final Provider<hu.a> o;
    private final Provider<b> p;
    private final Provider<q> q;
    private final Provider<pp> r;
    private final Provider<gm.a> s;
    private final Provider<cj.b> t;
    private final Provider<dx.b> u;
    private final Provider<aff> v;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.eventBus = (ql) this.b.get();
        aVar.a = (py) this.c.get();
        aVar.b = (Context) this.d.get();
        aVar.c = (pn) this.e.get();
        aVar.d = (ql) this.b.get();
        aVar.e = (Class) this.f.get();
        aVar.f = (Class) this.g.get();
        aVar.g = (bt) this.h.get();
        aVar.h = (uq) this.i.get();
        aVar.i = dagger.a.b.b(this.j);
        aVar.j = dagger.a.b.b(this.k);
        aVar.k = this.l;
        aVar.l = (yc) this.m.get();
        aVar.m = (pv) this.n.get();
        aVar.n = (hu.a) this.o.get();
        aVar.o = (b) this.p.get();
        aVar.p = (q) this.q.get();
        aVar.q = (pp) this.r.get();
        aVar.r = (gm.a) this.s.get();
        aVar.s = (cj.b) this.t.get();
        aVar.t = (dx.b) this.u.get();
        aVar.u = (aff) this.v.get();
    }

    private e(Provider<ql> provider, Provider<py> provider2, Provider<Context> provider3, Provider<pn> provider4, Provider<Class> provider5, Provider<Class> provider6, Provider<bt> provider7, Provider<uq> provider8, Provider<b> provider9, Provider<a> provider10, Provider<c> provider11, Provider<yc> provider12, Provider<pv> provider13, Provider<hu.a> provider14, Provider<b> provider15, Provider<q> provider16, Provider<pp> provider17, Provider<gm.a> provider18, Provider<cj.b> provider19, Provider<dx.b> provider20, Provider<aff> provider21) {
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
                                                        if (a || provider13 != null) {
                                                            this.n = provider13;
                                                            if (a || provider14 != null) {
                                                                this.o = provider14;
                                                                if (a || provider15 != null) {
                                                                    this.p = provider15;
                                                                    if (a || provider16 != null) {
                                                                        this.q = provider16;
                                                                        if (a || provider17 != null) {
                                                                            this.r = provider17;
                                                                            if (a || provider18 != null) {
                                                                                this.s = provider18;
                                                                                if (a || provider19 != null) {
                                                                                    this.t = provider19;
                                                                                    if (a || provider20 != null) {
                                                                                        this.u = provider20;
                                                                                        if (a || provider21 != null) {
                                                                                            this.v = provider21;
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

    public static MembersInjector<a> a(Provider<ql> provider, Provider<py> provider2, Provider<Context> provider3, Provider<pn> provider4, Provider<Class> provider5, Provider<Class> provider6, Provider<bt> provider7, Provider<uq> provider8, Provider<b> provider9, Provider<a> provider10, Provider<c> provider11, Provider<yc> provider12, Provider<pv> provider13, Provider<hu.a> provider14, Provider<b> provider15, Provider<q> provider16, Provider<pp> provider17, Provider<gm.a> provider18, Provider<cj.b> provider19, Provider<dx.b> provider20, Provider<aff> provider21) {
        return new e(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21);
    }
}
