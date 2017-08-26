package com.vungle.publisher;

import com.vungle.publisher.aao.a;
import com.vungle.publisher.env.WrapperFramework;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class aaq implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!aaq.class.desiredAssertionStatus());
    private final Provider<pn> b;
    private final Provider<sr> c;
    private final Provider<pu> d;
    private final Provider<String> e;
    private final Provider<WrapperFramework> f;
    private final Provider<String> g;
    private final Provider<adn.a> h;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        vx.a(aVar, this.b);
        aVar.a = (sr) this.c.get();
        aVar.c = (pu) this.d.get();
        aVar.d = (String) this.e.get();
        aVar.e = (WrapperFramework) this.f.get();
        aVar.f = (String) this.g.get();
        aVar.g = (pn) this.b.get();
        aVar.h = (adn.a) this.h.get();
    }

    private aaq(Provider<pn> provider, Provider<sr> provider2, Provider<pu> provider3, Provider<String> provider4, Provider<WrapperFramework> provider5, Provider<String> provider6, Provider<adn.a> provider7) {
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

    public static MembersInjector<a> a(Provider<pn> provider, Provider<sr> provider2, Provider<pu> provider3, Provider<String> provider4, Provider<WrapperFramework> provider5, Provider<String> provider6, Provider<adn.a> provider7) {
        return new aaq(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
