package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import dagger.a.b;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ye implements MembersInjector<yc> {
    static final /* synthetic */ boolean a = (!ye.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<py> c;
    private final Provider<ym> d;
    private final Provider<zn> e;
    private final Provider<zz> f;
    private final Provider<aaf> g;
    private final Provider<aal> h;
    private final Provider<aau> i;
    private final Provider<aba> j;
    private final Provider<a> k;
    private final Provider<bt> l;

    public final /* synthetic */ void injectMembers(Object obj) {
        yc ycVar = (yc) obj;
        if (ycVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ycVar.a = (ql) this.b.get();
        ycVar.b = b.b(this.c);
        ycVar.c = (ym) this.d.get();
        ycVar.d = (zn) this.e.get();
        ycVar.e = (zz) this.f.get();
        ycVar.f = (aaf) this.g.get();
        ycVar.g = (aal) this.h.get();
        ycVar.h = b.b(this.i);
        ycVar.i = (aba) this.j.get();
        ycVar.j = (a) this.k.get();
        ycVar.k = (bt) this.l.get();
    }

    private ye(Provider<ql> provider, Provider<py> provider2, Provider<ym> provider3, Provider<zn> provider4, Provider<zz> provider5, Provider<aaf> provider6, Provider<aal> provider7, Provider<aau> provider8, Provider<aba> provider9, Provider<a> provider10, Provider<bt> provider11) {
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

    public static MembersInjector<yc> a(Provider<ql> provider, Provider<py> provider2, Provider<ym> provider3, Provider<zn> provider4, Provider<zz> provider5, Provider<aaf> provider6, Provider<aal> provider7, Provider<aau> provider8, Provider<aba> provider9, Provider<a> provider10, Provider<bt> provider11) {
        return new ye(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }
}
