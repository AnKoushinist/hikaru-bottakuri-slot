package com.vungle.publisher;

import com.vungle.publisher.ck.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class co implements MembersInjector<ck> {
    static final /* synthetic */ boolean a = (!co.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        ck ckVar = (ck) obj;
        if (ckVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ckVar.u = (cf) this.b.get();
        ckVar.c = (a) this.c.get();
    }

    private co(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<ck> a(Provider<cf> provider, Provider<a> provider2) {
        return new co(provider, provider2);
    }
}
