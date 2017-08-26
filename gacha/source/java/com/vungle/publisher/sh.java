package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class sh implements MembersInjector<sf> {
    static final /* synthetic */ boolean a = (!sh.class.desiredAssertionStatus());
    private final Provider<sj> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        sf sfVar = (sf) obj;
        if (sfVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        sfVar.a = (sj) this.b.get();
    }

    private sh(Provider<sj> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<sf> a(Provider<sj> provider) {
        return new sh(provider);
    }
}
