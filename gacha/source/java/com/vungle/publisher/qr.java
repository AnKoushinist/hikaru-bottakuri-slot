package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class qr implements MembersInjector<qp> {
    static final /* synthetic */ boolean a = (!qr.class.desiredAssertionStatus());
    private final Provider<wr> b;
    private final Provider<pv> c;
    private final Provider<a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        qp qpVar = (qp) obj;
        if (qpVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        qpVar.a = (wr) this.b.get();
        qpVar.b = (pv) this.c.get();
        qpVar.c = (a) this.d.get();
    }

    private qr(Provider<wr> provider, Provider<pv> provider2, Provider<a> provider3) {
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

    public static MembersInjector<qp> a(Provider<wr> provider, Provider<pv> provider2, Provider<a> provider3) {
        return new qr(provider, provider2, provider3);
    }
}
