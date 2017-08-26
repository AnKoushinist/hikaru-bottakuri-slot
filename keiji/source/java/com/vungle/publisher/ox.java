package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ox implements MembersInjector<c> {
    static final /* synthetic */ boolean a = (!ox.class.desiredAssertionStatus());
    private final Provider<ql> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        c cVar = (c) obj;
        if (cVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        cVar.eventBus = (ql) this.b.get();
    }

    private ox(Provider<ql> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<c> a(Provider<ql> provider) {
        return new ox(provider);
    }
}
