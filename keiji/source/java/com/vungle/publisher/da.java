package com.vungle.publisher;

import com.vungle.publisher.cj.b;
import com.vungle.publisher.eo.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class da implements MembersInjector<b> {
    static final /* synthetic */ boolean a = (!da.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<hu.a> c;
    private final Provider<js.a> d;
    private final Provider<gs.a> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        b bVar = (b) obj;
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.a = (a) this.b.get();
        bVar.b = (hu.a) this.c.get();
        bVar.c = (js.a) this.d.get();
        bVar.d = (gs.a) this.e.get();
    }

    private da(Provider<a> provider, Provider<hu.a> provider2, Provider<js.a> provider3, Provider<gs.a> provider4) {
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

    public static MembersInjector<b> a(Provider<a> provider, Provider<hu.a> provider2, Provider<js.a> provider3, Provider<gs.a> provider4) {
        return new da(provider, provider2, provider3, provider4);
    }
}
