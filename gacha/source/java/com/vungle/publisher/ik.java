package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ik implements MembersInjector<ig> {
    static final /* synthetic */ boolean a = (!ik.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        ig igVar = (ig) obj;
        if (igVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        igVar.u = (cf) this.b.get();
        igVar.e = (a) this.c.get();
    }

    private ik(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<ig> a(Provider<cf> provider, Provider<a> provider2) {
        return new ik(provider, provider2);
    }
}
