package com.vungle.publisher;

import com.vungle.publisher.wx.a;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class xi implements MembersInjector<xg> {
    static final /* synthetic */ boolean a = (!xi.class.desiredAssertionStatus());
    private final Provider<we> b;
    private final Provider<a> c;
    private final Provider<xb.a> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        xg xgVar = (xg) obj;
        if (xgVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        xgVar.c = this.b;
        xgVar.a = (a) this.c.get();
        xgVar.b = (xb.a) this.d.get();
    }

    private xi(Provider<we> provider, Provider<a> provider2, Provider<xb.a> provider3) {
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

    public static MembersInjector<xg> a(Provider<we> provider, Provider<a> provider2, Provider<xb.a> provider3) {
        return new xi(provider, provider2, provider3);
    }
}
