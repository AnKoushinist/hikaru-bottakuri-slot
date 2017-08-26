package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fd implements MembersInjector<ez> {
    static final /* synthetic */ boolean a = (!fd.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        ez ezVar = (ez) obj;
        if (ezVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ezVar.u = (cf) this.b.get();
        ezVar.d = (a) this.c.get();
    }

    private fd(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<ez> a(Provider<cf> provider, Provider<a> provider2) {
        return new fd(provider, provider2);
    }
}
