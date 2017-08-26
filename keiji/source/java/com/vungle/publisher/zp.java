package com.vungle.publisher;

import com.vungle.publisher.zg.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class zp implements MembersInjector<zn> {
    static final /* synthetic */ boolean a = (!zp.class.desiredAssertionStatus());
    private final Provider<we> b;
    private final Provider<a> c;
    private final Provider<zk> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        zn znVar = (zn) obj;
        if (znVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        wg.a(znVar, this.b);
        znVar.a = (a) this.c.get();
        znVar.b = (zk) this.d.get();
    }

    private zp(Provider<we> provider, Provider<a> provider2, Provider<zk> provider3) {
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

    public static MembersInjector<zn> a(Provider<we> provider, Provider<a> provider2, Provider<zk> provider3) {
        return new zp(provider, provider2, provider3);
    }
}
