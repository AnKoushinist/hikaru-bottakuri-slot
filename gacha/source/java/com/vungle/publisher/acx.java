package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class acx implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!acx.class.desiredAssertionStatus());
    private final Provider<a> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (a) this.b.get();
    }

    private acx(Provider<a> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<a> provider) {
        return new acx(provider);
    }
}
