package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hz implements MembersInjector<hv> {
    static final /* synthetic */ boolean a = (!hz.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        hv hvVar = (hv) obj;
        if (hvVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        hvVar.u = (cf) this.b.get();
        hvVar.d = (a) this.c.get();
    }

    private hz(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<hv> a(Provider<cf> provider, Provider<a> provider2) {
        return new hz(provider, provider2);
    }
}
