package com.vungle.publisher;

import com.vungle.publisher.gs.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hq implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!hq.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<ql> c;
    private final Provider<agt> d;
    private final Provider<gs> e;
    private final Provider<gt.a> f;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (cf) this.b.get();
        aVar.a = (ql) this.c.get();
        aVar.b = (agt) this.d.get();
        aVar.c = this.e;
        aVar.e = (gt.a) this.f.get();
    }

    private hq(Provider<cf> provider, Provider<ql> provider2, Provider<agt> provider3, Provider<gs> provider4, Provider<gt.a> provider5) {
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

    public static MembersInjector<a> a(Provider<cf> provider, Provider<ql> provider2, Provider<agt> provider3, Provider<gs> provider4, Provider<gt.a> provider5) {
        return new hq(provider, provider2, provider3, provider4, provider5);
    }
}
