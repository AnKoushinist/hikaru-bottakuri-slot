package com.vungle.publisher;

import com.vungle.publisher.afq.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class afs implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!afs.class.desiredAssertionStatus());
    private final Provider<py> b;
    private final Provider<pp> c;
    private final Provider<ql> d;
    private final Provider<bt> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (py) this.b.get();
        aVar.b = (pp) this.c.get();
        aVar.c = (ql) this.d.get();
        aVar.d = (bt) this.e.get();
    }

    private afs(Provider<py> provider, Provider<pp> provider2, Provider<ql> provider3, Provider<bt> provider4) {
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

    public static MembersInjector<a> a(Provider<py> provider, Provider<pp> provider2, Provider<ql> provider3, Provider<bt> provider4) {
        return new afs(provider, provider2, provider3, provider4);
    }
}
