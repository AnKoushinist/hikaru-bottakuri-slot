package com.vungle.publisher;

import com.vungle.publisher.cq.b;
import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class agf implements MembersInjector<agb> {
    static final /* synthetic */ boolean a = (!agf.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<b> c;
    private final Provider<a> d;
    private final Provider<afy> e;
    private final Provider<vl> f;
    private final Provider<ck.a> g;

    public final /* synthetic */ void injectMembers(Object obj) {
        agb com_vungle_publisher_agb = (agb) obj;
        if (com_vungle_publisher_agb == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com_vungle_publisher_agb.eventBus = (ql) this.b.get();
        com_vungle_publisher_agb.d = (b) this.c.get();
        com_vungle_publisher_agb.e = (a) this.d.get();
        com_vungle_publisher_agb.f = (afy) this.e.get();
        com_vungle_publisher_agb.g = (vl) this.f.get();
        com_vungle_publisher_agb.h = (ck.a) this.g.get();
    }

    private agf(Provider<ql> provider, Provider<b> provider2, Provider<a> provider3, Provider<afy> provider4, Provider<vl> provider5, Provider<ck.a> provider6) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        if (a || provider5 != null) {
                            this.f = provider5;
                            if (a || provider6 != null) {
                                this.g = provider6;
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
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<agb> a(Provider<ql> provider, Provider<b> provider2, Provider<a> provider3, Provider<afy> provider4, Provider<vl> provider5, Provider<ck.a> provider6) {
        return new agf(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
