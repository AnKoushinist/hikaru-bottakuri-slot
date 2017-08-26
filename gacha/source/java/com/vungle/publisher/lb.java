package com.vungle.publisher;

import com.vungle.publisher.kx.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class lb implements MembersInjector<kx> {
    static final /* synthetic */ boolean a = (!lb.class.desiredAssertionStatus());
    private final Provider<a> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        kx kxVar = (kx) obj;
        if (kxVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        kxVar.a = (a) this.b.get();
    }

    private lb(Provider<a> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<kx> a(Provider<a> provider) {
        return new lb(provider);
    }
}
