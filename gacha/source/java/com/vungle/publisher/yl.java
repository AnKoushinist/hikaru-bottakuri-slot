package com.vungle.publisher;

import com.vungle.publisher.gm.a;
import dagger.MembersInjector;
import dagger.a.b;
import javax.inject.Provider;

/* compiled from: vungle */
public final class yl implements MembersInjector<yj> {
    static final /* synthetic */ boolean a = (!yl.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<wn> c;
    private final Provider<bt> d;
    private final Provider<afy> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        yj yjVar = (yj) obj;
        if (yjVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        wa.a(yjVar, this.b);
        wa.b(yjVar, this.c);
        ww.a(yjVar, this.d);
        yjVar.a = b.b(this.e);
    }

    private yl(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3, Provider<afy> provider4) {
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

    public static MembersInjector<yj> a(Provider<a> provider, Provider<wn> provider2, Provider<bt> provider3, Provider<afy> provider4) {
        return new yl(provider, provider2, provider3, provider4);
    }
}
