package com.vungle.publisher;

import com.vungle.publisher.uv.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class uy implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!uy.class.desiredAssertionStatus());
    private final Provider<pn> b;
    private final Provider<String> c;
    private final Provider<uv> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.b = (pn) this.b.get();
        aVar.c = (String) this.c.get();
        aVar.a = this.d;
    }

    private uy(Provider<pn> provider, Provider<String> provider2, Provider<uv> provider3) {
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

    public static MembersInjector<a> a(Provider<pn> provider, Provider<String> provider2, Provider<uv> provider3) {
        return new uy(provider, provider2, provider3);
    }
}
