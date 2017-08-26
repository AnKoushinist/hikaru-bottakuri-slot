package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gp implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!gp.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<pn> c;
    private final Provider<pv> d;
    private final Provider<gm> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (cf) this.b.get();
        aVar.a = (pn) this.c.get();
        aVar.b = (pv) this.d.get();
        aVar.c = this.e;
    }

    private gp(Provider<cf> provider, Provider<pn> provider2, Provider<pv> provider3, Provider<gm> provider4) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
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

    public static MembersInjector<a> a(Provider<cf> provider, Provider<pn> provider2, Provider<pv> provider3, Provider<gm> provider4) {
        return new gp(provider, provider2, provider3, provider4);
    }
}
