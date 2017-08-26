package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class wt implements MembersInjector<wr> {
    static final /* synthetic */ boolean a = (!wt.class.desiredAssertionStatus());
    private final Provider<xg> b;
    private final Provider<vc> c;
    private final Provider<a> d;
    private final Provider<bt> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        wr wrVar = (wr) obj;
        if (wrVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        wrVar.a = (xg) this.b.get();
        wrVar.b = (vc) this.c.get();
        wrVar.c = (a) this.d.get();
        wrVar.d = (bt) this.e.get();
    }

    private wt(Provider<xg> provider, Provider<vc> provider2, Provider<a> provider3, Provider<bt> provider4) {
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

    public static MembersInjector<wr> a(Provider<xg> provider, Provider<vc> provider2, Provider<a> provider3, Provider<bt> provider4) {
        return new wt(provider, provider2, provider3, provider4);
    }
}
