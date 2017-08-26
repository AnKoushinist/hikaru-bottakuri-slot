package com.vungle.publisher;

import com.vungle.publisher.aao.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class aaw implements MembersInjector<aau> {
    static final /* synthetic */ boolean a = (!aaw.class.desiredAssertionStatus());
    private final Provider<we> b;
    private final Provider<a> c;
    private final Provider<aar> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        aau com_vungle_publisher_aau = (aau) obj;
        if (com_vungle_publisher_aau == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        wg.a(com_vungle_publisher_aau, this.b);
        com_vungle_publisher_aau.a = (a) this.c.get();
        com_vungle_publisher_aau.b = (aar) this.d.get();
    }

    private aaw(Provider<we> provider, Provider<a> provider2, Provider<aar> provider3) {
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

    public static MembersInjector<aau> a(Provider<we> provider, Provider<a> provider2, Provider<aar> provider3) {
        return new aaw(provider, provider2, provider3);
    }
}
