package com.vungle.publisher;

import com.vungle.publisher.cs.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cw implements MembersInjector<cs> {
    static final /* synthetic */ boolean a = (!cw.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        cs csVar = (cs) obj;
        if (csVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        csVar.u = (cf) this.b.get();
        csVar.d = (a) this.c.get();
    }

    private cw(Provider<cf> provider, Provider<a> provider2) {
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

    public static MembersInjector<cs> a(Provider<cf> provider, Provider<a> provider2) {
        return new cw(provider, provider2);
    }
}
