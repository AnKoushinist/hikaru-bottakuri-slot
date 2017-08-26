package com.vungle.publisher;

import com.vungle.publisher.ee.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ei implements MembersInjector<ee> {
    static final /* synthetic */ boolean a = (!ei.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        ee eeVar = (ee) obj;
        if (eeVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        eeVar.u = (cf) this.b.get();
        eeVar.h = (a) this.c.get();
    }

    private ei(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<ee> a(Provider<cf> provider, Provider<a> provider2) {
        return new ei(provider, provider2);
    }
}
