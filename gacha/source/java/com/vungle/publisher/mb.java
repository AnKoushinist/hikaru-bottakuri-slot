package com.vungle.publisher;

import com.vungle.publisher.ly.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class mb implements MembersInjector<lz> {
    static final /* synthetic */ boolean a = (!mb.class.desiredAssertionStatus());
    private final Provider<pv> b;
    private final Provider<agt> c;
    private final Provider<a> d;
    private final Provider<wr> e;
    private final Provider<bt> f;
    private final Provider<gm.a> g;

    public final /* synthetic */ void injectMembers(Object obj) {
        lz lzVar = (lz) obj;
        if (lzVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        lzVar.a = (pv) this.b.get();
        lzVar.b = (agt) this.c.get();
        lzVar.c = (a) this.d.get();
        lzVar.d = (wr) this.e.get();
        lzVar.e = (bt) this.f.get();
        lzVar.f = (gm.a) this.g.get();
    }

    private mb(Provider<pv> provider, Provider<agt> provider2, Provider<a> provider3, Provider<wr> provider4, Provider<bt> provider5, Provider<gm.a> provider6) {
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

    public static MembersInjector<lz> a(Provider<pv> provider, Provider<agt> provider2, Provider<a> provider3, Provider<wr> provider4, Provider<bt> provider5, Provider<gm.a> provider6) {
        return new mb(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
