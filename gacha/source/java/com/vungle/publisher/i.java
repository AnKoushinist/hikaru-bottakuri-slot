package com.vungle.publisher;

import com.vungle.publisher.hu.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class i implements MembersInjector<c> {
    static final /* synthetic */ boolean a = (!i.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        c cVar = (c) obj;
        if (cVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        cVar.eventBus = (ql) this.b.get();
        cVar.e = (a) this.c.get();
    }

    private i(Provider<ql> provider, Provider<a> provider2) {
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

    public static MembersInjector<c> a(Provider<ql> provider, Provider<a> provider2) {
        return new i(provider, provider2);
    }
}
