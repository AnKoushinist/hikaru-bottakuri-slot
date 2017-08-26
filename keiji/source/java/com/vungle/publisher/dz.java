package com.vungle.publisher;

import com.vungle.publisher.dx.b;
import com.vungle.publisher.dx.c;
import com.vungle.publisher.j.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dz implements MembersInjector<b> {
    static final /* synthetic */ boolean a = (!dz.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;
    private final Provider<cj.b> d;
    private final Provider<gm.a> e;
    private final Provider<jy.a> f;
    private final Provider<eu.a> g;
    private final Provider<c> h;

    public final /* synthetic */ void injectMembers(Object obj) {
        b bVar = (b) obj;
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.a = (cf) this.b.get();
        bVar.b = (a) this.c.get();
        bVar.c = (cj.b) this.d.get();
        bVar.d = (gm.a) this.e.get();
        bVar.e = (jy.a) this.f.get();
        bVar.f = (eu.a) this.g.get();
        bVar.g = (c) this.h.get();
    }

    private dz(Provider<cf> provider, Provider<a> provider2, Provider<cj.b> provider3, Provider<gm.a> provider4, Provider<jy.a> provider5, Provider<eu.a> provider6, Provider<c> provider7) {
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
                            if (a || provider6 != null) {
                                this.g = provider6;
                                if (a || provider7 != null) {
                                    this.h = provider7;
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
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<b> a(Provider<cf> provider, Provider<a> provider2, Provider<cj.b> provider3, Provider<gm.a> provider4, Provider<jy.a> provider5, Provider<eu.a> provider6, Provider<c> provider7) {
        return new dz(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
