package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.sw.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class td implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!td.class.desiredAssertionStatus());
    private final Provider<Context> b;
    private final Provider<pn> c;
    private final Provider<ql> d;
    private final Provider<sx.a> e;
    private final Provider<su> f;
    private final Provider<th> g;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        my.a(aVar, this.b);
        my.b(aVar, this.c);
        my.c(aVar, this.d);
        aVar.d = (sx.a) this.e.get();
        aVar.e = this.f;
        aVar.f = (th) this.g.get();
    }

    private td(Provider<Context> provider, Provider<pn> provider2, Provider<ql> provider3, Provider<sx.a> provider4, Provider<su> provider5, Provider<th> provider6) {
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

    public static MembersInjector<a> a(Provider<Context> provider, Provider<pn> provider2, Provider<ql> provider3, Provider<sx.a> provider4, Provider<su> provider5, Provider<th> provider6) {
        return new td(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
