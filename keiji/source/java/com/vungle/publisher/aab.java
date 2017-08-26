package com.vungle.publisher;

import com.vungle.publisher.zt.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class aab implements MembersInjector<zz> {
    static final /* synthetic */ boolean a = (!aab.class.desiredAssertionStatus());
    private final Provider<we> b;
    private final Provider<a> c;
    private final Provider<zw> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        zz zzVar = (zz) obj;
        if (zzVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        wg.a(zzVar, this.b);
        zzVar.a = (a) this.c.get();
        zzVar.b = (zw) this.d.get();
    }

    private aab(Provider<we> provider, Provider<a> provider2, Provider<zw> provider3) {
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

    public static MembersInjector<zz> a(Provider<we> provider, Provider<a> provider2, Provider<zw> provider3) {
        return new aab(provider, provider2, provider3);
    }
}
