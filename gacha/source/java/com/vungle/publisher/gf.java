package com.vungle.publisher;

import com.vungle.publisher.eo.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gf implements MembersInjector<en> {
    static final /* synthetic */ boolean a = (!gf.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;
    private final Provider<en.a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        en enVar = (en) obj;
        if (enVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        enVar.u = (cf) this.b.get();
        enVar.c = (a) this.c.get();
        enVar.d = (en.a) this.d.get();
    }

    private gf(Provider<cf> provider, Provider<a> provider2, Provider<en.a> provider3) {
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

    public static MembersInjector<en> a(Provider<cf> provider, Provider<a> provider2, Provider<en.a> provider3) {
        return new gf(provider, provider2, provider3);
    }
}
