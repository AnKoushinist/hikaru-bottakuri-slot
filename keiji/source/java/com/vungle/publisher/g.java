package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class g implements MembersInjector<b> {
    static final /* synthetic */ boolean a = (!g.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<a> c;
    private final Provider<a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        b bVar = (b) obj;
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.eventBus = (ql) this.b.get();
        bVar.b = (a) this.c.get();
        bVar.c = (a) this.d.get();
    }

    private g(Provider<ql> provider, Provider<a> provider2, Provider<a> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<b> a(Provider<ql> provider, Provider<a> provider2, Provider<a> provider3) {
        return new g(provider, provider2, provider3);
    }
}
