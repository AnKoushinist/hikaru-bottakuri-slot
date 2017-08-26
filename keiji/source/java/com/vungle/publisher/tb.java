package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class tb implements MembersInjector<sx> {
    static final /* synthetic */ boolean a = (!tb.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<ql> c;
    private final Provider<th> d;
    private final Provider<tl.a> e;
    private final Provider<bw> f;
    private final Provider<pn> g;

    public final /* synthetic */ void injectMembers(Object obj) {
        sx sxVar = (sx) obj;
        if (sxVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        sxVar.d = (a) this.b.get();
        sxVar.e = (ql) this.c.get();
        sxVar.f = (th) this.d.get();
        sxVar.g = (tl.a) this.e.get();
        sxVar.h = (bw) this.f.get();
        sxVar.i = (pn) this.g.get();
    }

    private tb(Provider<a> provider, Provider<ql> provider2, Provider<th> provider3, Provider<tl.a> provider4, Provider<bw> provider5, Provider<pn> provider6) {
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

    public static MembersInjector<sx> a(Provider<a> provider, Provider<ql> provider2, Provider<th> provider3, Provider<tl.a> provider4, Provider<bw> provider5, Provider<pn> provider6) {
        return new tb(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
