package com.vungle.publisher;

import com.vungle.publisher.cs.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hm implements MembersInjector<hd> {
    static final /* synthetic */ boolean a = (!hm.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;
    private final Provider<agt> d;
    private final Provider<hd.a> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        hd hdVar = (hd) obj;
        if (hdVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        hdVar.u = (cf) this.b.get();
        hdVar.q = (a) this.c.get();
        hdVar.r = (agt) this.d.get();
        hdVar.v = (hd.a) this.e.get();
    }

    private hm(Provider<cf> provider, Provider<a> provider2, Provider<agt> provider3, Provider<hd.a> provider4) {
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

    public static MembersInjector<hd> a(Provider<cf> provider, Provider<a> provider2, Provider<agt> provider3, Provider<hd.a> provider4) {
        return new hm(provider, provider2, provider3, provider4);
    }
}
