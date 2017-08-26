package com.vungle.publisher;

import com.vungle.publisher.a.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class c implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!c.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.eventBus = (ql) this.b.get();
        aVar.a = (a) this.c.get();
    }

    private c(Provider<ql> provider, Provider<a> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<ql> provider, Provider<a> provider2) {
        return new c(provider, provider2);
    }
}
