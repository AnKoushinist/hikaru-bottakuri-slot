package com.vungle.publisher;

import com.vungle.publisher.oy.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class pe implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!pe.class.desiredAssertionStatus());
    private final Provider<ql> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.eventBus = (ql) this.b.get();
    }

    private pe(Provider<ql> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<ql> provider) {
        return new pe(provider);
    }
}
