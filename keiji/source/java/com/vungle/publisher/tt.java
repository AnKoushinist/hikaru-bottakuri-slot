package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class tt implements MembersInjector<tp> {
    static final /* synthetic */ boolean a = (!tt.class.desiredAssertionStatus());
    private final Provider<nb> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        tp tpVar = (tp) obj;
        if (tpVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        tpVar.o = (nb) this.b.get();
    }

    private tt(Provider<nb> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<tp> a(Provider<nb> provider) {
        return new tt(provider);
    }
}
