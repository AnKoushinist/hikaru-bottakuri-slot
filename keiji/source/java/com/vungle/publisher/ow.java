package com.vungle.publisher;

import com.vungle.publisher.op.c.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ow implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!ow.class.desiredAssertionStatus());
    private final Provider<c> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (c) this.b.get();
    }

    private ow(Provider<c> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<c> provider) {
        return new ow(provider);
    }
}
