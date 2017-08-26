package com.vungle.publisher;

import com.vungle.publisher.aai.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class aan implements MembersInjector<aal> {
    static final /* synthetic */ boolean a = (!aan.class.desiredAssertionStatus());
    private final Provider<we> b;
    private final Provider<a> c;
    private final Provider<vo> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        aal com_vungle_publisher_aal = (aal) obj;
        if (com_vungle_publisher_aal == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        wg.a(com_vungle_publisher_aal, this.b);
        com_vungle_publisher_aal.a = (a) this.c.get();
        com_vungle_publisher_aal.b = (vo) this.d.get();
    }

    private aan(Provider<we> provider, Provider<a> provider2, Provider<vo> provider3) {
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

    public static MembersInjector<aal> a(Provider<we> provider, Provider<a> provider2, Provider<vo> provider3) {
        return new aan(provider, provider2, provider3);
    }
}
