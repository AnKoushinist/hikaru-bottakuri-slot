package com.vungle.publisher;

import com.vungle.publisher.adu.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class adw implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!adw.class.desiredAssertionStatus());
    private final Provider<j.a> b;
    private final Provider<agt> c;
    private final Provider<gm.a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (j.a) this.b.get();
        aVar.b = (agt) this.c.get();
        aVar.c = (gm.a) this.d.get();
    }

    private adw(Provider<j.a> provider, Provider<agt> provider2, Provider<gm.a> provider3) {
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

    public static MembersInjector<a> a(Provider<j.a> provider, Provider<agt> provider2, Provider<gm.a> provider3) {
        return new adw(provider, provider2, provider3);
    }
}
