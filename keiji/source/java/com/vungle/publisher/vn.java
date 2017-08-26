package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class vn implements MembersInjector<vl> {
    static final /* synthetic */ boolean a = (!vn.class.desiredAssertionStatus());
    private final Provider<xo> b;
    private final Provider<bt> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        vl vlVar = (vl) obj;
        if (vlVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        vlVar.a = (xo) this.b.get();
        vlVar.b = (bt) this.c.get();
    }

    private vn(Provider<xo> provider, Provider<bt> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<vl> a(Provider<xo> provider, Provider<bt> provider2) {
        return new vn(provider, provider2);
    }
}
