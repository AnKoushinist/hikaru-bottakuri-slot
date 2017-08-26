package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class yb implements MembersInjector<xz> {
    static final /* synthetic */ boolean a = (!yb.class.desiredAssertionStatus());
    private final Provider<wi> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        xz xzVar = (xz) obj;
        if (xzVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        xzVar.a = (wi) this.b.get();
    }

    private yb(Provider<wi> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<xz> a(Provider<wi> provider) {
        return new yb(provider);
    }
}
