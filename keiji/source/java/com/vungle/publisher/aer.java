package com.vungle.publisher;

import com.vungle.publisher.aep.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class aer implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!aer.class.desiredAssertionStatus());
    private final Provider<acc.a> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (acc.a) this.b.get();
    }

    private aer(Provider<acc.a> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<acc.a> provider) {
        return new aer(provider);
    }
}
