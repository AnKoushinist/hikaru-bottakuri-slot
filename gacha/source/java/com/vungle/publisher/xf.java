package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class xf implements MembersInjector<xb> {
    static final /* synthetic */ boolean a = (!xf.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<wn> c;
    private final Provider<bt> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        xb xbVar = (xb) obj;
        if (xbVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        xbVar.c = (a) this.b.get();
        xbVar.d = (wn) this.c.get();
        xbVar.h = (bt) this.d.get();
        xbVar.b = (a) this.b.get();
    }

    private xf(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<xb> a(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3) {
        return new xf(provider, provider2, provider3);
    }
}
