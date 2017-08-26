package com.vungle.publisher;

import android.content.Context;
import android.content.SharedPreferences;
import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class qa implements MembersInjector<py> {
    static final /* synthetic */ boolean a = (!qa.class.desiredAssertionStatus());
    private final Provider<Context> b;
    private final Provider<agt> c;
    private final Provider<cc> d;
    private final Provider<pn> e;
    private final Provider<ql> f;
    private final Provider<lv> g;
    private final Provider<ur> h;
    private final Provider<bt> i;
    private final Provider<pp> j;
    private final Provider<yc> k;
    private final Provider<a> l;
    private final Provider<SharedPreferences> m;

    public final /* synthetic */ void injectMembers(Object obj) {
        py pyVar = (py) obj;
        if (pyVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        pyVar.a = (Context) this.b.get();
        pyVar.b = (agt) this.c.get();
        pyVar.c = (cc) this.d.get();
        pyVar.d = (pn) this.e.get();
        pyVar.e = (ql) this.f.get();
        pyVar.f = (lv) this.g.get();
        pyVar.g = (ur) this.h.get();
        pyVar.h = (bt) this.i.get();
        pyVar.i = (pp) this.j.get();
        pyVar.j = (yc) this.k.get();
        pyVar.k = (a) this.l.get();
        pyVar.l = (SharedPreferences) this.m.get();
    }

    private qa(Provider<Context> provider, Provider<agt> provider2, Provider<cc> provider3, Provider<pn> provider4, Provider<ql> provider5, Provider<lv> provider6, Provider<ur> provider7, Provider<bt> provider8, Provider<pp> provider9, Provider<yc> provider10, Provider<a> provider11, Provider<SharedPreferences> provider12) {
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

    public static MembersInjector<py> a(Provider<Context> provider, Provider<agt> provider2, Provider<cc> provider3, Provider<pn> provider4, Provider<ql> provider5, Provider<lv> provider6, Provider<ur> provider7, Provider<bt> provider8, Provider<pp> provider9, Provider<yc> provider10, Provider<a> provider11, Provider<SharedPreferences> provider12) {
        return new qa(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }
}
