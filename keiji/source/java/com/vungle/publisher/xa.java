package com.vungle.publisher;

import com.vungle.publisher.wx.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class xa implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!xa.class.desiredAssertionStatus());
    private final Provider<pn> b;
    private final Provider<String> c;
    private final Provider<acf.a> d;
    private final Provider<wx> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.b = (pn) this.b.get();
        aVar.c = (String) this.c.get();
        aVar.a = (acf.a) this.d.get();
        aVar.d = this.e;
    }

    private xa(Provider<pn> provider, Provider<String> provider2, Provider<acf.a> provider3, Provider<wx> provider4) {
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

    public static MembersInjector<a> a(Provider<pn> provider, Provider<String> provider2, Provider<acf.a> provider3, Provider<wx> provider4) {
        return new xa(provider, provider2, provider3, provider4);
    }
}