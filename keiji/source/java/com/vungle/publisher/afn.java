package com.vungle.publisher;

import com.vungle.publisher.vi.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class afn implements MembersInjector<afl> {
    static final /* synthetic */ boolean a = (!afn.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<xr> c;
    private final Provider<xz> d;
    private final Provider<afo> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        afl com_vungle_publisher_afl = (afl) obj;
        if (com_vungle_publisher_afl == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        com_vungle_publisher_afl.a = (a) this.b.get();
        com_vungle_publisher_afl.b = (xr) this.c.get();
        com_vungle_publisher_afl.c = (xz) this.d.get();
        com_vungle_publisher_afl.d = (afo) this.e.get();
    }

    private afn(Provider<a> provider, Provider<xr> provider2, Provider<xz> provider3, Provider<afo> provider4) {
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

    public static MembersInjector<afl> a(Provider<a> provider, Provider<xr> provider2, Provider<xz> provider3, Provider<afo> provider4) {
        return new afn(provider, provider2, provider3, provider4);
    }
}
