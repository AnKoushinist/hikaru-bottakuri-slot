package com.vungle.publisher;

import com.vungle.publisher.dx.c;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ey implements MembersInjector<eu> {
    static final /* synthetic */ boolean a = (!ey.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<c> c;
    private final Provider<agt> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        eu euVar = (eu) obj;
        if (euVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        euVar.a = (cf) this.b.get();
        euVar.b = (c) this.c.get();
        euVar.c = (agt) this.d.get();
    }

    private ey(Provider<cf> provider, Provider<c> provider2, Provider<agt> provider3) {
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

    public static MembersInjector<eu> a(Provider<cf> provider, Provider<c> provider2, Provider<agt> provider3) {
        return new ey(provider, provider2, provider3);
    }
}
