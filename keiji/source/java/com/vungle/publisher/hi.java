package com.vungle.publisher;

import com.vungle.publisher.he.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hi implements MembersInjector<he> {
    static final /* synthetic */ boolean a = (!hi.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        he heVar = (he) obj;
        if (heVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        heVar.u = (cf) this.b.get();
        heVar.e = (a) this.c.get();
    }

    private hi(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<he> a(Provider<cf> provider, Provider<a> provider2) {
        return new hi(provider, provider2);
    }
}
