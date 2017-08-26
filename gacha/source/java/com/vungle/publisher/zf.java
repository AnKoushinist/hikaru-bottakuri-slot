package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class zf implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!zf.class.desiredAssertionStatus());
    private final Provider<yc> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (yc) this.b.get();
    }

    private zf(Provider<yc> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<yc> provider) {
        return new zf(provider);
    }
}
