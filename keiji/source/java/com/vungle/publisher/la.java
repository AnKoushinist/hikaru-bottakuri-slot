package com.vungle.publisher;

import com.vungle.publisher.kx.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class la implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!la.class.desiredAssertionStatus());
    private final Provider<kx> b;
    private final Provider<kd.a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
        aVar.b = (kd.a) this.c.get();
    }

    private la(Provider<kx> provider, Provider<kd.a> provider2) {
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

    public static MembersInjector<a> a(Provider<kx> provider, Provider<kd.a> provider2) {
        return new la(provider, provider2);
    }
}
