package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class s implements MembersInjector<q> {
    static final /* synthetic */ boolean a = (!s.class.desiredAssertionStatus());
    private final Provider<AdConfig> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        q qVar = (q) obj;
        if (qVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        qVar.a = (AdConfig) this.b.get();
    }

    private s(Provider<AdConfig> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<q> a(Provider<AdConfig> provider) {
        return new s(provider);
    }
}
