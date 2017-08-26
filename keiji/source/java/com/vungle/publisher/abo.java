package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.abg.b.a.a;
import com.vungle.publisher.abg.b.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class abo implements MembersInjector<b> {
    static final /* synthetic */ boolean a = (!abo.class.desiredAssertionStatus());
    private final Provider<Context> b;
    private final Provider<AdConfig> c;
    private final Provider<pn> d;
    private final Provider<a> e;
    private final Provider<uq> f;
    private final Provider<pu> g;

    public final /* synthetic */ void injectMembers(Object obj) {
        b bVar = (b) obj;
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.a = (Context) this.b.get();
        bVar.b = (AdConfig) this.c.get();
        bVar.c = (pn) this.d.get();
        bVar.d = (a) this.e.get();
        bVar.e = (uq) this.f.get();
        bVar.f = (pu) this.g.get();
    }

    private abo(Provider<Context> provider, Provider<AdConfig> provider2, Provider<pn> provider3, Provider<a> provider4, Provider<uq> provider5, Provider<pu> provider6) {
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

    public static MembersInjector<b> a(Provider<Context> provider, Provider<AdConfig> provider2, Provider<pn> provider3, Provider<a> provider4, Provider<uq> provider5, Provider<pu> provider6) {
        return new abo(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
