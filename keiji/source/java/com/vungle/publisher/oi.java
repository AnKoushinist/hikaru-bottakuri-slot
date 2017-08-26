package com.vungle.publisher;

import com.vungle.publisher.og.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class oi implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!oi.class.desiredAssertionStatus());
    private final Provider<agw> b;
    private final Provider<ql> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (agw) this.b.get();
        aVar.b = (ql) this.c.get();
    }

    private oi(Provider<agw> provider, Provider<ql> provider2) {
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

    public static MembersInjector<a> a(Provider<agw> provider, Provider<ql> provider2) {
        return new oi(provider, provider2);
    }
}
