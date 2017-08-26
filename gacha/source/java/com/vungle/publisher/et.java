package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class et implements MembersInjector<ep> {
    static final /* synthetic */ boolean a = (!et.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;
    private final Provider<db.a> d;
    private final Provider<gh> e;
    private final Provider<eo.a> f;
    private final Provider<ep.a> g;
    private final Provider<lg.a> h;

    public final /* synthetic */ void injectMembers(Object obj) {
        ep epVar = (ep) obj;
        if (epVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        epVar.u = (cf) this.b.get();
        epVar.e = (a) this.c.get();
        epVar.f = (db.a) this.d.get();
        epVar.g = (gh) this.e.get();
        epVar.h = (eo.a) this.f.get();
        epVar.i = (ep.a) this.g.get();
        epVar.j = (lg.a) this.h.get();
    }

    private et(Provider<cf> provider, Provider<a> provider2, Provider<db.a> provider3, Provider<gh> provider4, Provider<eo.a> provider5, Provider<ep.a> provider6, Provider<lg.a> provider7) {
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

    public static MembersInjector<ep> a(Provider<cf> provider, Provider<a> provider2, Provider<db.a> provider3, Provider<gh> provider4, Provider<eo.a> provider5, Provider<ep.a> provider6, Provider<lg.a> provider7) {
        return new et(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
