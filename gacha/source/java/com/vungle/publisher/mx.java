package com.vungle.publisher;

import com.vungle.publisher.mt.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class mx implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!mx.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<pn> c;
    private final Provider<bw> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (ql) this.b.get();
        aVar.b = (pn) this.c.get();
        aVar.c = (bw) this.d.get();
    }

    private mx(Provider<ql> provider, Provider<pn> provider2, Provider<bw> provider3) {
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

    public static MembersInjector<a> a(Provider<ql> provider, Provider<pn> provider2, Provider<bw> provider3) {
        return new mx(provider, provider2, provider3);
    }
}
