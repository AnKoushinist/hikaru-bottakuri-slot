package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gq implements MembersInjector<gm> {
    static final /* synthetic */ boolean a = (!gq.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        gm gmVar = (gm) obj;
        if (gmVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gmVar.u = (cf) this.b.get();
        gmVar.j = (a) this.c.get();
    }

    private gq(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<gm> a(Provider<cf> provider, Provider<a> provider2) {
        return new gq(provider, provider2);
    }
}
