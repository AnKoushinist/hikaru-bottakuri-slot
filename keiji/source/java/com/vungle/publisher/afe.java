package com.vungle.publisher;

import com.vungle.publisher.cj.b;
import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class afe implements MembersInjector<afc> {
    static final /* synthetic */ boolean a = (!afe.class.desiredAssertionStatus());
    private final Provider<b> b;
    private final Provider<dx.b> c;
    private final Provider<a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        afc com_vungle_publisher_afc = (afc) obj;
        if (com_vungle_publisher_afc == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com_vungle_publisher_afc.a = (b) this.b.get();
        com_vungle_publisher_afc.b = (dx.b) this.c.get();
        com_vungle_publisher_afc.c = (a) this.d.get();
    }

    private afe(Provider<b> provider, Provider<dx.b> provider2, Provider<a> provider3) {
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

    public static MembersInjector<afc> a(Provider<b> provider, Provider<dx.b> provider2, Provider<a> provider3) {
        return new afe(provider, provider2, provider3);
    }
}
