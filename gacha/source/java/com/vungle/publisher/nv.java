package com.vungle.publisher;

import com.vungle.publisher.nq.a.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class nv implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!nv.class.desiredAssertionStatus());
    private final Provider<nq.a> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (nq.a) this.b.get();
    }

    private nv(Provider<nq.a> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<nq.a> provider) {
        return new nv(provider);
    }
}
