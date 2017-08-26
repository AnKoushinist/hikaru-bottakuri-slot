package com.vungle.publisher;

import com.vungle.publisher.aef.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class aeh implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!aeh.class.desiredAssertionStatus());
    private final Provider<j.a> b;
    private final Provider<agt> c;
    private final Provider<afa.a> d;
    private final Provider<aeu.a> e;
    private final Provider<abp.a> f;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (j.a) this.b.get();
        aVar.b = (agt) this.c.get();
        aVar.c = (afa.a) this.d.get();
        aVar.d = (aeu.a) this.e.get();
        aVar.e = (abp.a) this.f.get();
    }

    private aeh(Provider<j.a> provider, Provider<agt> provider2, Provider<afa.a> provider3, Provider<aeu.a> provider4, Provider<abp.a> provider5) {
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

    public static MembersInjector<a> a(Provider<j.a> provider, Provider<agt> provider2, Provider<afa.a> provider3, Provider<aeu.a> provider4, Provider<abp.a> provider5) {
        return new aeh(provider, provider2, provider3, provider4, provider5);
    }
}
