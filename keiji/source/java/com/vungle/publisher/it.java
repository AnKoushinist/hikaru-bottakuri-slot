package com.vungle.publisher;

import com.vungle.publisher.ip.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class it implements MembersInjector<ip> {
    static final /* synthetic */ boolean a = (!it.class.desiredAssertionStatus());
    private final Provider<a> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        ip ipVar = (ip) obj;
        if (ipVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ipVar.a = (a) this.b.get();
    }

    private it(Provider<a> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<ip> a(Provider<a> provider) {
        return new it(provider);
    }
}
