package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class wh implements MembersInjector<we> {
    static final /* synthetic */ boolean a = (!wh.class.desiredAssertionStatus());
    private final Provider<wi> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        we weVar = (we) obj;
        if (weVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        weVar.e = (wi) this.b.get();
    }

    private wh(Provider<wi> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<we> a(Provider<wi> provider) {
        return new wh(provider);
    }
}
