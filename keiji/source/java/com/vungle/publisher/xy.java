package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class xy implements MembersInjector<xw> {
    static final /* synthetic */ boolean a = (!xy.class.desiredAssertionStatus());
    private final Provider<wn> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        xw xwVar = (xw) obj;
        if (xwVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        xwVar.a = (wn) this.b.get();
    }

    private xy(Provider<wn> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<xw> a(Provider<wn> provider) {
        return new xy(provider);
    }
}
