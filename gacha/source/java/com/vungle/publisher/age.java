package com.vungle.publisher;

import com.vungle.publisher.agb.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class age implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!age.class.desiredAssertionStatus());
    private final Provider<agb> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (agb) this.b.get();
    }

    private age(Provider<agb> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<agb> provider) {
        return new age(provider);
    }
}
