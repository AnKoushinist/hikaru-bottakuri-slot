package com.vungle.publisher;

import com.vungle.publisher.ki.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class km implements MembersInjector<ki> {
    static final /* synthetic */ boolean a = (!km.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        ki kiVar = (ki) obj;
        if (kiVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        kiVar.u = (cf) this.b.get();
        kiVar.e = (a) this.c.get();
    }

    private km(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<ki> a(Provider<cf> provider, Provider<a> provider2) {
        return new km(provider, provider2);
    }
}
