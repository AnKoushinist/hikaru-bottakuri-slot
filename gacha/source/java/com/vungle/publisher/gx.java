package com.vungle.publisher;

import com.vungle.publisher.dx.c;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gx implements MembersInjector<gt> {
    static final /* synthetic */ boolean a = (!gx.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<c> c;
    private final Provider<agt> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        gt gtVar = (gt) obj;
        if (gtVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gtVar.a = (cf) this.b.get();
        gtVar.b = (c) this.c.get();
        gtVar.c = (agt) this.d.get();
    }

    private gx(Provider<cf> provider, Provider<c> provider2, Provider<agt> provider3) {
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

    public static MembersInjector<gt> a(Provider<cf> provider, Provider<c> provider2, Provider<agt> provider3) {
        return new gx(provider, provider2, provider3);
    }
}
