package com.vungle.publisher;

import com.vungle.publisher.nh.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class nk implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!nk.class.desiredAssertionStatus());
    private final Provider<nh> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
    }

    private nk(Provider<nh> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<nh> provider) {
        return new nk(provider);
    }
}
