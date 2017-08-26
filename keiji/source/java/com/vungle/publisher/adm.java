package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class adm implements MembersInjector<adi> {
    static final /* synthetic */ boolean a = (!adm.class.desiredAssertionStatus());
    private final Provider<pn> b;
    private final Provider<pu> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        adi com_vungle_publisher_adi = (adi) obj;
        if (com_vungle_publisher_adi == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com_vungle_publisher_adi.a = (pn) this.b.get();
        com_vungle_publisher_adi.b = (pu) this.c.get();
    }

    private adm(Provider<pn> provider, Provider<pu> provider2) {
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

    public static MembersInjector<adi> a(Provider<pn> provider, Provider<pu> provider2) {
        return new adm(provider, provider2);
    }
}
