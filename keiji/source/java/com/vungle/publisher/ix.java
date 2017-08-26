package com.vungle.publisher;

import com.vungle.publisher.hu.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ix implements MembersInjector<hu> {
    static final /* synthetic */ boolean a = (!ix.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<ql> c;
    private final Provider<a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        hu huVar = (hu) obj;
        if (huVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        huVar.u = (cf) this.b.get();
        huVar.C = (ql) this.c.get();
        huVar.q = (a) this.d.get();
    }

    private ix(Provider<cf> provider, Provider<ql> provider2, Provider<a> provider3) {
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

    public static MembersInjector<hu> a(Provider<cf> provider, Provider<ql> provider2, Provider<a> provider3) {
        return new ix(provider, provider2, provider3);
    }
}
