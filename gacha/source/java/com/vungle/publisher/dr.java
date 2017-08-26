package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dr implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!dr.class.desiredAssertionStatus());
    private final Provider<do> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
    }

    private dr(Provider<do> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<do> provider) {
        return new dr(provider);
    }
}
