package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class mo implements MembersInjector<mm> {
    static final /* synthetic */ boolean a = (!mo.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<mp> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        mm mmVar = (mm) obj;
        if (mmVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        mmVar.a = (ql) this.b.get();
        mmVar.b = (mp) this.c.get();
    }

    private mo(Provider<ql> provider, Provider<mp> provider2) {
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

    public static MembersInjector<mm> a(Provider<ql> provider, Provider<mp> provider2) {
        return new mo(provider, provider2);
    }
}
