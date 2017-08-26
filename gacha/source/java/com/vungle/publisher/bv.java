package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import dagger.a.b;
import javax.inject.Provider;

/* compiled from: vungle */
public final class bv implements MembersInjector<bt> {
    static final /* synthetic */ boolean a = (!bv.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<agt> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        bt btVar = (bt) obj;
        if (btVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        btVar.c = b.b(this.b);
        btVar.d = (agt) this.c.get();
    }

    private bv(Provider<a> provider, Provider<agt> provider2) {
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

    public static MembersInjector<bt> a(Provider<a> provider, Provider<agt> provider2) {
        return new bv(provider, provider2);
    }
}
