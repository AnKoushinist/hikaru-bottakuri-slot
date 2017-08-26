package com.vungle.publisher;

import com.vungle.publisher.ht.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ja implements MembersInjector<a> {
    static final /* synthetic */ boolean a = (!ja.class.desiredAssertionStatus());
    private final Provider<cf> b;
    private final Provider<j.a> c;
    private final Provider<ht> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        a aVar = (a) obj;
        if (aVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        aVar.d = (cf) this.b.get();
        aVar.e = (j.a) this.c.get();
        aVar.a = this.d;
    }

    private ja(Provider<cf> provider, Provider<j.a> provider2, Provider<ht> provider3) {
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

    public static MembersInjector<a> a(Provider<cf> provider, Provider<j.a> provider2, Provider<ht> provider3) {
        return new ja(provider, provider2, provider3);
    }
}
