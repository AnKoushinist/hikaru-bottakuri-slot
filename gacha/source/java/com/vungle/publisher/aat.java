package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class aat implements MembersInjector<aar> {
    static final /* synthetic */ boolean a = (!aat.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<wn> c;
    private final Provider<bt> d;
    private final Provider<py> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        aar com_vungle_publisher_aar = (aar) obj;
        if (com_vungle_publisher_aar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        wa.a(com_vungle_publisher_aar, this.b);
        wa.b(com_vungle_publisher_aar, this.c);
        ww.a(com_vungle_publisher_aar, this.d);
        com_vungle_publisher_aar.a = (py) this.e.get();
    }

    private aat(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3, Provider<py> provider4) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
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

    public static MembersInjector<aar> a(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3, Provider<py> provider4) {
        return new aat(provider, provider2, provider3, provider4);
    }
}
