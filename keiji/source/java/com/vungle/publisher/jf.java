package com.vungle.publisher;

import com.vungle.publisher.jc.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class jf implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!jf.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<gm.a> c;
    private final Provider<jc> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (cf) this.b.get();
        aVar.a = (gm.a) this.c.get();
        aVar.b = this.d;
    }

    private jf(Provider<cf> provider, Provider<gm.a> provider2, Provider<jc> provider3) {
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

    public static MembersInjector<a> a(Provider<cf> provider, Provider<gm.a> provider2, Provider<jc> provider3) {
        return new jf(provider, provider2, provider3);
    }
}
