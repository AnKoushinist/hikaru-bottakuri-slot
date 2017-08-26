package com.vungle.publisher;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rd implements MembersInjector<rb> {
    static final /* synthetic */ boolean a = (!rd.class.desiredAssertionStatus());
    private final Provider<Context> b;

    public final /* synthetic */ void injectMembers(Object obj) {
        rb rbVar = (rb) obj;
        if (rbVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        rbVar.a = (Context) this.b.get();
    }

    private rd(Provider<Context> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<rb> a(Provider<Context> provider) {
        return new rd(provider);
    }
}
