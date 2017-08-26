package com.vungle.publisher;

import com.vungle.publisher.hd.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hl implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!hl.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<cs.a> c;
    private final Provider<ck.a> d;
    private final Provider<gs.a> e;
    private final Provider<gy.a> f;
    private final Provider<hd> g;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (cf) this.b.get();
        aVar.a = (cs.a) this.c.get();
        aVar.b = (ck.a) this.d.get();
        aVar.c = (gs.a) this.e.get();
        aVar.e = (gy.a) this.f.get();
        aVar.f = this.g;
    }

    private hl(Provider<cf> provider, Provider<cs.a> provider2, Provider<ck.a> provider3, Provider<gs.a> provider4, Provider<gy.a> provider5, Provider<hd> provider6) {
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

    public static MembersInjector<a> a(Provider<cf> provider, Provider<cs.a> provider2, Provider<ck.a> provider3, Provider<gs.a> provider4, Provider<gy.a> provider5, Provider<hd> provider6) {
        return new hl(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
