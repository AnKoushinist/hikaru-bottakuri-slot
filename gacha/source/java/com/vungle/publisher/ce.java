package com.vungle.publisher;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ce implements MembersInjector<cc> {
    static final /* synthetic */ boolean a = (!ce.class.desiredAssertionStatus());
    private final Provider<Context> b;
    private final Provider<cf> c;
    private final Provider<pu> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        cc ccVar = (cc) obj;
        if (ccVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ccVar.a = (Context) this.b.get();
        ccVar.b = (cf) this.c.get();
        ccVar.c = (pu) this.d.get();
    }

    private ce(Provider<Context> provider, Provider<cf> provider2, Provider<pu> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<cc> a(Provider<Context> provider, Provider<cf> provider2, Provider<pu> provider3) {
        return new ce(provider, provider2, provider3);
    }
}
