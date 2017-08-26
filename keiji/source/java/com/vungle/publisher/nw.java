package com.vungle.publisher;

import com.vungle.publisher.nq.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class nw implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!nw.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<gm.a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.eventBus = (ql) this.b.get();
        aVar.b = (gm.a) this.c.get();
    }

    private nw(Provider<ql> provider, Provider<gm.a> provider2) {
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

    public static MembersInjector<a> a(Provider<ql> provider, Provider<gm.a> provider2) {
        return new nw(provider, provider2);
    }
}
