package com.vungle.publisher;

import com.vungle.publisher.cq.b;
import com.vungle.publisher.fj.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cy implements MembersInjector<b> {
    static final /* synthetic */ boolean a = (!cy.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;
    private final Provider<if.a> d;
    private final Provider<kn.a> e;
    private final Provider<hd.a> f;

    public final /* synthetic */ void injectMembers(Object obj) {
        b bVar = (b) obj;
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.a = (cf) this.b.get();
        bVar.b = (a) this.c.get();
        bVar.c = (if.a) this.d.get();
        bVar.d = (kn.a) this.e.get();
        bVar.e = (hd.a) this.f.get();
    }

    private cy(Provider<cf> provider, Provider<a> provider2, Provider<if.a> provider3, Provider<kn.a> provider4, Provider<hd.a> provider5) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        if (a || provider5 != null) {
                            this.f = provider5;
                            return;
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<b> a(Provider<cf> provider, Provider<a> provider2, Provider<if.a> provider3, Provider<kn.a> provider4, Provider<hd.a> provider5) {
        return new cy(provider, provider2, provider3, provider4, provider5);
    }
}
