package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class agy implements MembersInjector<agw> {
    static final /* synthetic */ boolean a = (!agy.class.desiredAssertionStatus());
    private final Provider<rb> b;
    private final Provider<a> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        agw com_vungle_publisher_agw = (agw) obj;
        if (com_vungle_publisher_agw == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com_vungle_publisher_agw.a = (rb) this.b.get();
        com_vungle_publisher_agw.b = (a) this.c.get();
    }

    private agy(Provider<rb> provider, Provider<a> provider2) {
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

    public static MembersInjector<agw> a(Provider<rb> provider, Provider<a> provider2) {
        return new agy(provider, provider2);
    }
}
