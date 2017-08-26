package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ch implements MembersInjector<cf> {
    static final /* synthetic */ boolean a = (!ch.class.desiredAssertionStatus());
    private final Provider<qu> b;
    private final Provider<ql> c;
    private final Provider<pu> d;
    private final Provider<bt> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        cf cfVar = (cf) obj;
        if (cfVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        cfVar.a = (qu) this.b.get();
        cfVar.b = (ql) this.c.get();
        cfVar.c = (pu) this.d.get();
        cfVar.d = (bt) this.e.get();
    }

    private ch(Provider<qu> provider, Provider<ql> provider2, Provider<pu> provider3, Provider<bt> provider4) {
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

    public static MembersInjector<cf> a(Provider<qu> provider, Provider<ql> provider2, Provider<pu> provider3, Provider<bt> provider4) {
        return new ch(provider, provider2, provider3, provider4);
    }
}
