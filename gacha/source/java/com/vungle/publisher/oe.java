package com.vungle.publisher;

import com.vungle.publisher.ob.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class oe implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!oe.class.desiredAssertionStatus());
    private final Provider<ob> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
    }

    private oe(Provider<ob> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<ob> provider) {
        return new oe(provider);
    }
}
