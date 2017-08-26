package com.vungle.publisher;

import com.vungle.publisher.ne.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ng implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!ng.class.desiredAssertionStatus());
    private final Provider<oy> b;
    private final Provider<nq> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
        aVar.b = this.c;
    }

    private ng(Provider<oy> provider, Provider<nq> provider2) {
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

    public static MembersInjector<a> a(Provider<oy> provider, Provider<nq> provider2) {
        return new ng(provider, provider2);
    }
}
