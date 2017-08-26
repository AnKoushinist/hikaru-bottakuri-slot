package com.vungle.publisher;

import com.vungle.publisher.cq.b;
import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class afk implements MembersInjector<afi> {
    static final /* synthetic */ boolean a = (!afk.class.desiredAssertionStatus());
    private final Provider<b> b;
    private final Provider<a> c;
    private final Provider<afl> d;
    private final Provider<eo.a> e;
    private final Provider<dx.b> f;

    public final /* synthetic */ void injectMembers(Object obj) {
        afi com_vungle_publisher_afi = (afi) obj;
        if (com_vungle_publisher_afi == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com_vungle_publisher_afi.a = (b) this.b.get();
        com_vungle_publisher_afi.b = (a) this.c.get();
        com_vungle_publisher_afi.c = (afl) this.d.get();
        com_vungle_publisher_afi.d = (eo.a) this.e.get();
        com_vungle_publisher_afi.e = (dx.b) this.f.get();
    }

    private afk(Provider<b> provider, Provider<a> provider2, Provider<afl> provider3, Provider<eo.a> provider4, Provider<dx.b> provider5) {
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

    public static MembersInjector<afi> a(Provider<b> provider, Provider<a> provider2, Provider<afl> provider3, Provider<eo.a> provider4, Provider<dx.b> provider5) {
        return new afk(provider, provider2, provider3, provider4, provider5);
    }
}
