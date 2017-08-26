package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class vq implements MembersInjector<vo> {
    static final /* synthetic */ boolean a = (!vq.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<wn> c;
    private final Provider<bt> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        vo voVar = (vo) obj;
        if (voVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        voVar.c = (a) this.b.get();
        voVar.d = (wn) this.c.get();
        voVar.h = (bt) this.d.get();
    }

    private vq(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3) {
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

    public static MembersInjector<vo> a(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3) {
        return new vq(provider, provider2, provider3);
    }
}
