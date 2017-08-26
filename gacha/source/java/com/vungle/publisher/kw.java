package com.vungle.publisher;

import com.vungle.publisher.cs.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kw implements MembersInjector<kn> {
    static final /* synthetic */ boolean a = (!kw.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;
    private final Provider<agt> d;
    private final Provider<kn.a> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        kn knVar = (kn) obj;
        if (knVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        knVar.u = (cf) this.b.get();
        knVar.q = (a) this.c.get();
        knVar.r = (agt) this.d.get();
        knVar.x = (kn.a) this.e.get();
    }

    private kw(Provider<cf> provider, Provider<a> provider2, Provider<agt> provider3, Provider<kn.a> provider4) {
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

    public static MembersInjector<kn> a(Provider<cf> provider, Provider<a> provider2, Provider<agt> provider3, Provider<kn.a> provider4) {
        return new kw(provider, provider2, provider3, provider4);
    }
}
