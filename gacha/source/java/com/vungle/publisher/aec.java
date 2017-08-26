package com.vungle.publisher;

import com.vungle.publisher.aea.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class aec implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!aec.class.desiredAssertionStatus());
    private final Provider<j.a> b;
    private final Provider<aed.a.a> c;
    private final Provider<agt> d;
    private final Provider<aep.a> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (j.a) this.b.get();
        aVar.d = (aed.a.a) this.c.get();
        aVar.b = (agt) this.d.get();
        aVar.c = (aep.a) this.e.get();
    }

    private aec(Provider<j.a> provider, Provider<aed.a.a> provider2, Provider<agt> provider3, Provider<aep.a> provider4) {
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

    public static MembersInjector<a> a(Provider<j.a> provider, Provider<aed.a.a> provider2, Provider<agt> provider3, Provider<aep.a> provider4) {
        return new aec(provider, provider2, provider3, provider4);
    }
}
