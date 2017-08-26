package com.vungle.publisher;

import com.vungle.publisher.gs.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hr implements MembersInjector<gs> {
    static final /* synthetic */ boolean a = (!hr.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;
    private final Provider<ln.a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        gs gsVar = (gs) obj;
        if (gsVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gsVar.u = (cf) this.b.get();
        gsVar.r = (a) this.c.get();
        gsVar.v = (ln.a) this.d.get();
    }

    private hr(Provider<cf> provider, Provider<a> provider2, Provider<ln.a> provider3) {
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

    public static MembersInjector<gs> a(Provider<cf> provider, Provider<a> provider2, Provider<ln.a> provider3) {
        return new hr(provider, provider2, provider3);
    }
}
