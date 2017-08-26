package com.vungle.publisher;

import com.vungle.publisher.agg.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class agj implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!agj.class.desiredAssertionStatus());
    private final Provider<agg> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (agg) this.b.get();
    }

    private agj(Provider<agg> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<agg> provider) {
        return new agj(provider);
    }
}
