package com.vungle.publisher;

import com.vungle.publisher.eu.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ex implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!ex.class.desiredAssertionStatus());
    private final Provider<eu> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (eu) this.b.get();
    }

    private ex(Provider<eu> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<eu> provider) {
        return new ex(provider);
    }
}
