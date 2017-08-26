package com.vungle.publisher;

import com.vungle.publisher.dx.c;
import com.vungle.publisher.j.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class eb implements MembersInjector<c> {
    static final /* synthetic */ boolean a = (!eb.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;
    private final Provider<gm.a> d;
    private final Provider<agt> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        c cVar = (c) obj;
        if (cVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        cVar.a = (cf) this.b.get();
        cVar.b = (a) this.c.get();
        cVar.c = (gm.a) this.d.get();
        cVar.d = (agt) this.e.get();
    }

    private eb(Provider<cf> provider, Provider<a> provider2, Provider<gm.a> provider3, Provider<agt> provider4) {
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

    public static MembersInjector<c> a(Provider<cf> provider, Provider<a> provider2, Provider<gm.a> provider3, Provider<agt> provider4) {
        return new eb(provider, provider2, provider3, provider4);
    }
}
