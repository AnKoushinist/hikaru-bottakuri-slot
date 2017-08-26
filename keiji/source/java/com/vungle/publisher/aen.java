package com.vungle.publisher;

import com.vungle.publisher.ael.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class aen implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!aen.class.desiredAssertionStatus());
    private final Provider<pn> b;
    private final Provider<pu> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (pn) this.b.get();
        aVar.b = (pu) this.c.get();
    }

    private aen(Provider<pn> provider, Provider<pu> provider2) {
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

    public static MembersInjector<a> a(Provider<pn> provider, Provider<pu> provider2) {
        return new aen(provider, provider2);
    }
}
