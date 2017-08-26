package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fo implements MembersInjector<fk> {
    static final /* synthetic */ boolean a = (!fo.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        fk fkVar = (fk) obj;
        if (fkVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        fkVar.u = (cf) this.b.get();
        fkVar.e = (a) this.c.get();
    }

    private fo(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<fk> a(Provider<cf> provider, Provider<a> provider2) {
        return new fo(provider, provider2);
    }
}
