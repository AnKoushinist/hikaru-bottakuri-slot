package com.vungle.publisher;

import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class id implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!id.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<ia> c;
    private final Provider<a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (cf) this.b.get();
        aVar.a = this.c;
        aVar.b = (a) this.d.get();
    }

    private id(Provider<cf> provider, Provider<ia> provider2, Provider<a> provider3) {
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

    public static MembersInjector<a> a(Provider<cf> provider, Provider<ia> provider2, Provider<a> provider3) {
        return new id(provider, provider2, provider3);
    }
}
