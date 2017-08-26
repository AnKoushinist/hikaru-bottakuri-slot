package com.vungle.publisher;

import com.vungle.publisher.eo.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gb implements MembersInjector<eo> {
    static final /* synthetic */ boolean a = (!gb.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<ql> c;
    private final Provider<a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        eo eoVar = (eo) obj;
        if (eoVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        eoVar.u = (cf) this.b.get();
        eoVar.C = (ql) this.c.get();
        eoVar.w = (a) this.d.get();
    }

    private gb(Provider<cf> provider, Provider<ql> provider2, Provider<a> provider3) {
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

    public static MembersInjector<eo> a(Provider<cf> provider, Provider<ql> provider2, Provider<a> provider3) {
        return new gb(provider, provider2, provider3);
    }
}
