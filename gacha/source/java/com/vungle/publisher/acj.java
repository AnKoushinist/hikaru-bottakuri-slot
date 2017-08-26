package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class acj implements MembersInjector<acf> {
    static final /* synthetic */ boolean a = (!acj.class.desiredAssertionStatus());
    private final Provider<pn> b;
    private final Provider<pu> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        acf com_vungle_publisher_acf = (acf) obj;
        if (com_vungle_publisher_acf == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com_vungle_publisher_acf.b = (pn) this.b.get();
        com_vungle_publisher_acf.c = (pu) this.c.get();
    }

    private acj(Provider<pn> provider, Provider<pu> provider2) {
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

    public static MembersInjector<acf> a(Provider<pn> provider, Provider<pu> provider2) {
        return new acj(provider, provider2);
    }
}
