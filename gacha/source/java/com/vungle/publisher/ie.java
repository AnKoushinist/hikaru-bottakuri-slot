package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ie implements MembersInjector<ia> {
    static final /* synthetic */ boolean a = (!ie.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        ia iaVar = (ia) obj;
        if (iaVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        iaVar.u = (cf) this.b.get();
        iaVar.e = (a) this.c.get();
    }

    private ie(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<ia> a(Provider<cf> provider, Provider<a> provider2) {
        return new ie(provider, provider2);
    }
}
