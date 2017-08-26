package com.vungle.publisher;

import com.vungle.publisher.cs.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fs implements MembersInjector<fj> {
    static final /* synthetic */ boolean a = (!fs.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;
    private final Provider<agt> d;
    private final Provider<fj.a> e;
    private final Provider<a> f;

    public final /* synthetic */ void injectMembers(Object obj) {
        fj fjVar = (fj) obj;
        if (fjVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        fjVar.u = (cf) this.b.get();
        fjVar.q = (a) this.c.get();
        fjVar.r = (agt) this.d.get();
        fjVar.v = (fj.a) this.e.get();
        fjVar.w = (a) this.f.get();
    }

    private fs(Provider<cf> provider, Provider<a> provider2, Provider<agt> provider3, Provider<fj.a> provider4, Provider<a> provider5) {
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

    public static MembersInjector<fj> a(Provider<cf> provider, Provider<a> provider2, Provider<agt> provider3, Provider<fj.a> provider4, Provider<a> provider5) {
        return new fs(provider, provider2, provider3, provider4, provider5);
    }
}
