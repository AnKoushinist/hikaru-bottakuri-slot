package com.vungle.publisher;

import android.content.Context;
import android.net.wifi.WifiManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ph implements MembersInjector<pf> {
    static final /* synthetic */ boolean a = (!ph.class.desiredAssertionStatus());
    private final Provider<Context> b;
    private final Provider<ql> c;
    private final Provider<bt> d;
    private final Provider<WifiManager> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        pf pfVar = (pf) obj;
        if (pfVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        pfVar.c = (Context) this.b.get();
        pfVar.d = (ql) this.c.get();
        pfVar.e = (bt) this.d.get();
        pfVar.a = (Context) this.b.get();
        pfVar.b = (WifiManager) this.e.get();
    }

    private ph(Provider<Context> provider, Provider<ql> provider2, Provider<bt> provider3, Provider<WifiManager> provider4) {
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

    public static MembersInjector<pf> a(Provider<Context> provider, Provider<ql> provider2, Provider<bt> provider3, Provider<WifiManager> provider4) {
        return new ph(provider, provider2, provider3, provider4);
    }
}
