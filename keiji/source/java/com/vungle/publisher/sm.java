package com.vungle.publisher;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class sm implements MembersInjector<sk> {
    static final /* synthetic */ boolean a = (!sm.class.desiredAssertionStatus());
    private final Provider<Context> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        sk skVar = (sk) obj;
        if (skVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        skVar.c = (Context) this.b.get();
    }

    private sm(Provider<Context> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<sk> a(Provider<Context> provider) {
        return new sm(provider);
    }
}
