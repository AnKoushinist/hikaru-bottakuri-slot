package com.vungle.publisher;

import com.vungle.publisher.acf.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class aci implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!aci.class.desiredAssertionStatus());
    private final Provider<acf> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = this.b;
    }

    private aci(Provider<acf> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<acf> provider) {
        return new aci(provider);
    }
}
