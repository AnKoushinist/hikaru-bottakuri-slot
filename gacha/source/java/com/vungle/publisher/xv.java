package com.vungle.publisher;

import com.vungle.publisher.adf.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class xv implements MembersInjector<xt> {
    static final /* synthetic */ boolean a = (!xv.class.desiredAssertionStatus());
    private final Provider<a> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        xt xtVar = (xt) obj;
        if (xtVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        xtVar.a = (a) this.b.get();
    }

    private xv(Provider<a> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<xt> a(Provider<a> provider) {
        return new xv(provider);
    }
}
