package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class vb implements MembersInjector<uz> {
    static final /* synthetic */ boolean a = (!vb.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<wn> c;
    private final Provider<bt> d;
    private final Provider<pv> e;
    private final Provider<agt> f;

    public final /* synthetic */ void injectMembers(Object obj) {
        uz uzVar = (uz) obj;
        if (uzVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        uzVar.c = (a) this.b.get();
        uzVar.d = (wn) this.c.get();
        uzVar.h = (bt) this.d.get();
        uzVar.a = (pv) this.e.get();
        uzVar.b = (agt) this.f.get();
    }

    private vb(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3, Provider<pv> provider4, Provider<agt> provider5) {
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

    public static MembersInjector<uz> a(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3, Provider<pv> provider4, Provider<agt> provider5) {
        return new vb(provider, provider2, provider3, provider4, provider5);
    }
}
