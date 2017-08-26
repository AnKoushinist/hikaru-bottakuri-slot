package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ot implements MembersInjector<op> {
    static final /* synthetic */ boolean a = (!ot.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<mz> c;
    private final Provider<nb> d;
    private final Provider<ql> e;
    private final Provider<op.a> f;
    private final Provider<og.a> g;
    private final Provider<oj.a> h;
    private final Provider<nx.a> i;
    private final Provider<pn> j;
    private final Provider<agw> k;
    private final Provider<by> l;
    private final Provider<c.a> m;
    private final Provider<lr> n;

    public final /* synthetic */ void injectMembers(Object obj) {
        op opVar = (op) obj;
        if (opVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        opVar.c = (a) this.b.get();
        opVar.d = (mz) this.c.get();
        opVar.m = (nb) this.d.get();
        opVar.n = (ql) this.e.get();
        opVar.o = (op.a) this.f.get();
        opVar.p = (og.a) this.g.get();
        opVar.q = (oj.a) this.h.get();
        opVar.r = (nx.a) this.i.get();
        opVar.s = (pn) this.j.get();
        opVar.t = (agw) this.k.get();
        opVar.u = (by) this.l.get();
        opVar.v = (c.a) this.m.get();
        opVar.w = (lr) this.n.get();
    }

    private ot(Provider<a> provider, Provider<mz> provider2, Provider<nb> provider3, Provider<ql> provider4, Provider<op.a> provider5, Provider<og.a> provider6, Provider<oj.a> provider7, Provider<nx.a> provider8, Provider<pn> provider9, Provider<agw> provider10, Provider<by> provider11, Provider<c.a> provider12, Provider<lr> provider13) {
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

    public static MembersInjector<op> a(Provider<a> provider, Provider<mz> provider2, Provider<nb> provider3, Provider<ql> provider4, Provider<op.a> provider5, Provider<og.a> provider6, Provider<oj.a> provider7, Provider<nx.a> provider8, Provider<pn> provider9, Provider<agw> provider10, Provider<by> provider11, Provider<c.a> provider12, Provider<lr> provider13) {
        return new ot(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13);
    }
}
