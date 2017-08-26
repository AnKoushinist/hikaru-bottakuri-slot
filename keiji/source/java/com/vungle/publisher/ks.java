package com.vungle.publisher;

import com.vungle.publisher.ko.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ks implements MembersInjector<ko> {
    static final /* synthetic */ boolean a = (!ks.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        ko koVar = (ko) obj;
        if (koVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        koVar.u = (cf) this.b.get();
        koVar.e = (a) this.c.get();
    }

    private ks(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<ko> a(Provider<cf> provider, Provider<a> provider2) {
        return new ks(provider, provider2);
    }
}
