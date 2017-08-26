package com.vungle.publisher;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class oo implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!oo.class.desiredAssertionStatus());
    private final Provider<Context> b;
    private final Provider<pn> c;
    private final Provider<ql> d;
    private final Provider<mm> e;
    private final Provider<mj> f;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (Context) this.b.get();
        aVar.b = (pn) this.c.get();
        aVar.c = (ql) this.d.get();
        aVar.d = (mm) this.e.get();
        aVar.e = (mj) this.f.get();
    }

    private oo(Provider<Context> provider, Provider<pn> provider2, Provider<ql> provider3, Provider<mm> provider4, Provider<mj> provider5) {
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

    public static MembersInjector<a> a(Provider<Context> provider, Provider<pn> provider2, Provider<ql> provider3, Provider<mm> provider4, Provider<mj> provider5) {
        return new oo(provider, provider2, provider3, provider4, provider5);
    }
}
