package com.vungle.publisher;

import com.vungle.publisher.xb.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class xe implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!xe.class.desiredAssertionStatus());
    private final Provider<xb> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
    }

    private xe(Provider<xb> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<xb> provider) {
        return new xe(provider);
    }
}
