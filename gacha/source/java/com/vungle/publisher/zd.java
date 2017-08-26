package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class zd implements MembersInjector<zb> {
    static final /* synthetic */ boolean a = (!zd.class.desiredAssertionStatus());
    private final Provider<bt> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        zb zbVar = (zb) obj;
        if (zbVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bs.a(zbVar, this.b);
        zbVar.b = (a) this.c.get();
    }

    private zd(Provider<bt> provider, Provider<a> provider2) {
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

    public static MembersInjector<zb> a(Provider<bt> provider, Provider<a> provider2) {
        return new zd(provider, provider2);
    }
}
