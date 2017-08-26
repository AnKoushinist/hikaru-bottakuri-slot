package com.vungle.publisher;

import com.vungle.publisher.ds.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dv implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!dv.class.desiredAssertionStatus());
    private final Provider<ds> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
    }

    private dv(Provider<ds> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<ds> provider) {
        return new dv(provider);
    }
}
