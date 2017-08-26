package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.gm.a;
import com.vungle.publisher.lk.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class of implements MembersInjector<ob> {
    static final /* synthetic */ boolean a = (!of.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<mz> c;
    private final Provider<pn> d;
    private final Provider<ql> e;
    private final Provider<b> f;
    private final Provider<Context> g;
    private final Provider<a> h;

    public final /* synthetic */ void injectMembers(Object obj) {
        ob obVar = (ob) obj;
        if (obVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        obVar.c = (a) this.b.get();
        obVar.d = (mz) this.c.get();
        obVar.i = (pn) this.d.get();
        obVar.j = (ql) this.e.get();
        obVar.k = (b) this.f.get();
        obVar.l = (Context) this.g.get();
        obVar.m = (a) this.h.get();
    }

    private of(Provider<a> provider, Provider<mz> provider2, Provider<pn> provider3, Provider<ql> provider4, Provider<b> provider5, Provider<Context> provider6, Provider<a> provider7) {
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

    public static MembersInjector<ob> a(Provider<a> provider, Provider<mz> provider2, Provider<pn> provider3, Provider<ql> provider4, Provider<b> provider5, Provider<Context> provider6, Provider<a> provider7) {
        return new of(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
