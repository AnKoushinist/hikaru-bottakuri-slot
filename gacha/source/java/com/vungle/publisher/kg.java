package com.vungle.publisher;

import com.vungle.publisher.kd.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kg implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!kg.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<kd> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (cf) this.b.get();
        aVar.a = this.c;
    }

    private kg(Provider<cf> provider, Provider<kd> provider2) {
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

    public static MembersInjector<a> a(Provider<cf> provider, Provider<kd> provider2) {
        return new kg(provider, provider2);
    }
}
