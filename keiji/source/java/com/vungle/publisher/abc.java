package com.vungle.publisher;

import com.vungle.publisher.aax.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class abc implements MembersInjector<aba> {
    static final /* synthetic */ boolean a = (!abc.class.desiredAssertionStatus());
    private final Provider<we> b;
    private final Provider<a> c;
    private final Provider<vo> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        aba com_vungle_publisher_aba = (aba) obj;
        if (com_vungle_publisher_aba == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        wg.a(com_vungle_publisher_aba, this.b);
        com_vungle_publisher_aba.a = (a) this.c.get();
        com_vungle_publisher_aba.b = (vo) this.d.get();
    }

    private abc(Provider<we> provider, Provider<a> provider2, Provider<vo> provider3) {
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

    public static MembersInjector<aba> a(Provider<we> provider, Provider<a> provider2, Provider<vo> provider3) {
        return new abc(provider, provider2, provider3);
    }
}
