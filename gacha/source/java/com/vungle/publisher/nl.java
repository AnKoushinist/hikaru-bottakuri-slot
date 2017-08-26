package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.gm.a;
import com.vungle.publisher.lk.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class nl implements MembersInjector<nh> {
    static final /* synthetic */ boolean a = (!nl.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<mz> c;
    private final Provider<pn> d;
    private final Provider<ql> e;
    private final Provider<b> f;
    private final Provider<Context> g;
    private final Provider<sw.a> h;
    private final Provider<nh.b.a> i;
    private final Provider<th> j;
    private final Provider<nb> k;
    private final Provider<te.a> l;
    private final Provider<og.a> m;

    public final /* synthetic */ void injectMembers(Object obj) {
        nh nhVar = (nh) obj;
        if (nhVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        nhVar.c = (a) this.b.get();
        nhVar.d = (mz) this.c.get();
        nhVar.i = (pn) this.d.get();
        nhVar.j = (ql) this.e.get();
        nhVar.k = (b) this.f.get();
        nhVar.l = (Context) this.g.get();
        nhVar.r = (sw.a) this.h.get();
        nhVar.s = (nh.b.a) this.i.get();
        nhVar.t = (th) this.j.get();
        nhVar.u = (nb) this.k.get();
        nhVar.v = (te.a) this.l.get();
        nhVar.w = (og.a) this.m.get();
    }

    private nl(Provider<a> provider, Provider<mz> provider2, Provider<pn> provider3, Provider<ql> provider4, Provider<b> provider5, Provider<Context> provider6, Provider<sw.a> provider7, Provider<nh.b.a> provider8, Provider<th> provider9, Provider<nb> provider10, Provider<te.a> provider11, Provider<og.a> provider12) {
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

    public static MembersInjector<nh> a(Provider<a> provider, Provider<mz> provider2, Provider<pn> provider3, Provider<ql> provider4, Provider<b> provider5, Provider<Context> provider6, Provider<sw.a> provider7, Provider<nh.b.a> provider8, Provider<th> provider9, Provider<nb> provider10, Provider<te.a> provider11, Provider<og.a> provider12) {
        return new nl(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }
}
