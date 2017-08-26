package com.vungle.publisher;

import com.vungle.publisher.cq.b;
import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class agk implements MembersInjector<agg> {
    static final /* synthetic */ boolean a = (!agk.class.desiredAssertionStatus());
    private final Provider<ql> b;
    private final Provider<b> c;
    private final Provider<a> d;
    private final Provider<afy> e;
    private final Provider<vl> f;

    public final /* synthetic */ void injectMembers(Object obj) {
        agg com_vungle_publisher_agg = (agg) obj;
        if (com_vungle_publisher_agg == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com_vungle_publisher_agg.eventBus = (ql) this.b.get();
        com_vungle_publisher_agg.d = (b) this.c.get();
        com_vungle_publisher_agg.e = (a) this.d.get();
        com_vungle_publisher_agg.f = (afy) this.e.get();
        com_vungle_publisher_agg.g = (vl) this.f.get();
    }

    private agk(Provider<ql> provider, Provider<b> provider2, Provider<a> provider3, Provider<afy> provider4, Provider<vl> provider5) {
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

    public static MembersInjector<agg> a(Provider<ql> provider, Provider<b> provider2, Provider<a> provider3, Provider<afy> provider4, Provider<vl> provider5) {
        return new agk(provider, provider2, provider3, provider4, provider5);
    }
}
