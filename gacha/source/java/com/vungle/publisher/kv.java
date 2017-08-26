package com.vungle.publisher;

import com.vungle.publisher.kn.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class kv implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!kv.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<cs.a> c;
    private final Provider<ck.a> d;
    private final Provider<js.a> e;
    private final Provider<ki.a> f;
    private final Provider<kn> g;
    private final Provider<a> h;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (cf) this.b.get();
        aVar.a = (cs.a) this.c.get();
        aVar.b = (ck.a) this.d.get();
        aVar.c = (js.a) this.e.get();
        aVar.e = (ki.a) this.f.get();
        aVar.f = this.g;
        aVar.g = (a) this.h.get();
    }

    private kv(Provider<cf> provider, Provider<cs.a> provider2, Provider<ck.a> provider3, Provider<js.a> provider4, Provider<ki.a> provider5, Provider<kn> provider6, Provider<a> provider7) {
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

    public static MembersInjector<a> a(Provider<cf> provider, Provider<cs.a> provider2, Provider<ck.a> provider3, Provider<js.a> provider4, Provider<ki.a> provider5, Provider<kn> provider6, Provider<a> provider7) {
        return new kv(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
