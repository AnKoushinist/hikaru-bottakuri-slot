package com.vungle.publisher;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class nd implements MembersInjector<nb> {
    static final /* synthetic */ boolean a = (!nd.class.desiredAssertionStatus());
    private final Provider<Context> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        nb nbVar = (nb) obj;
        if (nbVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        nbVar.a = (Context) this.b.get();
    }

    private nd(Provider<Context> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<nb> a(Provider<Context> provider) {
        return new nd(provider);
    }
}
