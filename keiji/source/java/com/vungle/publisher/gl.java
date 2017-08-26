package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gl implements MembersInjector<gh> {
    static final /* synthetic */ boolean a = (!gl.class.desiredAssertionStatus());
    private final Provider<pn> b;
    private final Provider<py> c;

    public final /* synthetic */ void injectMembers(Object obj) {
        gh ghVar = (gh) obj;
        if (ghVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ghVar.d = (pn) this.b.get();
        ghVar.e = (py) this.c.get();
    }

    private gl(Provider<pn> provider, Provider<py> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<gh> a(Provider<pn> provider, Provider<py> provider2) {
        return new gl(provider, provider2);
    }
}
