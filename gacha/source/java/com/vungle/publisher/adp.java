package com.vungle.publisher;

import com.vungle.publisher.abg.b.b;
import com.vungle.publisher.adn.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class adp implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!adp.class.desiredAssertionStatus());
    private final Provider<abg.a.a> b;
    private final Provider<pn> c;
    private final Provider<pv> d;
    private final Provider<b> e;
    private final Provider<pu> f;
    private final Provider<ee.a> g;
    private final Provider<a> h;
    private final Provider<py> i;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.a = (abg.a.a) this.b.get();
        aVar.b = (pn) this.c.get();
        aVar.c = (pv) this.d.get();
        aVar.d = (b) this.e.get();
        aVar.e = (pu) this.f.get();
        aVar.f = (ee.a) this.g.get();
        aVar.g = (a) this.h.get();
        aVar.h = dagger.a.b.b(this.i);
    }

    private adp(Provider<abg.a.a> provider, Provider<pn> provider2, Provider<pv> provider3, Provider<b> provider4, Provider<pu> provider5, Provider<ee.a> provider6, Provider<a> provider7, Provider<py> provider8) {
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
                                    if (a || provider8 != null) {
                                        this.i = provider8;
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
        throw new AssertionError();
    }

    public static MembersInjector<a> a(Provider<abg.a.a> provider, Provider<pn> provider2, Provider<pv> provider3, Provider<b> provider4, Provider<pu> provider5, Provider<ee.a> provider6, Provider<a> provider7, Provider<py> provider8) {
        return new adp(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }
}
