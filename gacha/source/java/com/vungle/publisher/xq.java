package com.vungle.publisher;

import com.vungle.publisher.xl.a;
import dagger.MembersInjector;
import dagger.a.b;
import javax.inject.Provider;

/* compiled from: vungle */
public final class xq implements MembersInjector<xo> {
    static final /* synthetic */ boolean a = (!xq.class.desiredAssertionStatus());
    private final Provider<we> b;
    private final Provider<a> c;
    private final Provider<vf> d;
    private final Provider<vo> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        xo xoVar = (xo) obj;
        if (xoVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        xoVar.c = this.b;
        xoVar.a = (a) this.c.get();
        xoVar.b = b.b(this.d);
        xoVar.d = b.b(this.e);
    }

    private xq(Provider<we> provider, Provider<a> provider2, Provider<vf> provider3, Provider<vo> provider4) {
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

    public static MembersInjector<xo> a(Provider<we> provider, Provider<a> provider2, Provider<vf> provider3, Provider<vo> provider4) {
        return new xq(provider, provider2, provider3, provider4);
    }
}
