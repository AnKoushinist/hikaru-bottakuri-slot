package com.vungle.publisher;

import com.vungle.publisher.gt.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gw implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!gw.class.desiredAssertionStatus());
    private final Provider<gt> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (gt) this.b.get();
    }

    private gw(Provider<gt> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<gt> provider) {
        return new gw(provider);
    }
}
