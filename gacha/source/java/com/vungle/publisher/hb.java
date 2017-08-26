package com.vungle.publisher;

import com.vungle.publisher.gy.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hb implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!hb.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<gy> c;
    private final Provider<he.a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (cf) this.b.get();
        aVar.a = this.c;
        aVar.b = (he.a) this.d.get();
    }

    private hb(Provider<cf> provider, Provider<gy> provider2, Provider<he.a> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<cf> provider, Provider<gy> provider2, Provider<he.a> provider3) {
        return new hb(provider, provider2, provider3);
    }
}
