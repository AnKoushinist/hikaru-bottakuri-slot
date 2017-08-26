package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ij implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!ij.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<ig> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (cf) this.b.get();
        aVar.a = this.c;
    }

    private ij(Provider<cf> provider, Provider<ig> provider2) {
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

    public static MembersInjector<a> a(Provider<cf> provider, Provider<ig> provider2) {
        return new ij(provider, provider2);
    }
}
