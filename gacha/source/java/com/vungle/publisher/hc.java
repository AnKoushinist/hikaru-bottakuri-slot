package com.vungle.publisher;

import com.vungle.publisher.gy.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hc implements MembersInjector<gy> {
    static final /* synthetic */ boolean a = (!hc.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        gy gyVar = (gy) obj;
        if (gyVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gyVar.u = (cf) this.b.get();
        gyVar.e = (a) this.c.get();
    }

    private hc(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<gy> a(Provider<cf> provider, Provider<a> provider2) {
        return new hc(provider, provider2);
    }
}
