package com.vungle.publisher;

import com.vungle.publisher.js.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class lf implements MembersInjector<js> {
    static final /* synthetic */ boolean a = (!lf.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<a> c;
    private final Provider<lg.a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        js jsVar = (js) obj;
        if (jsVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        jsVar.u = (cf) this.b.get();
        jsVar.C = (a) this.c.get();
        jsVar.D = (lg.a) this.d.get();
    }

    private lf(Provider<cf> provider, Provider<a> provider2, Provider<lg.a> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<js> a(Provider<cf> provider, Provider<a> provider2, Provider<lg.a> provider3) {
        return new lf(provider, provider2, provider3);
    }
}
