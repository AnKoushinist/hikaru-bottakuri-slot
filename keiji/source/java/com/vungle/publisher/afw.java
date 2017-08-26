package com.vungle.publisher;

import com.vungle.publisher.adr.a;
import com.vungle.publisher.aft.b;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class afw implements MembersInjector<b> {
    static final /* synthetic */ boolean a = (!afw.class.desiredAssertionStatus());
    private final Provider<a> b;
    private final Provider<adu.a> c;
    private final Provider<aef.a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        b bVar = (b) obj;
        if (bVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bVar.a = (a) this.b.get();
        bVar.b = (adu.a) this.c.get();
        bVar.c = (aef.a) this.d.get();
    }

    private afw(Provider<a> provider, Provider<adu.a> provider2, Provider<aef.a> provider3) {
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

    public static MembersInjector<b> a(Provider<a> provider, Provider<adu.a> provider2, Provider<aef.a> provider3) {
        return new afw(provider, provider2, provider3);
    }
}
