package com.vungle.publisher;

import android.content.Context;
import android.net.ConnectivityManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ut implements MembersInjector<ur> {
    static final /* synthetic */ boolean a = (!ut.class.desiredAssertionStatus());
    private final Provider<Context> b;
    private final Provider<uq> c;
    private final Provider<ql> d;
    private final Provider<ConnectivityManager> e;

    public final /* synthetic */ void injectMembers(Object obj) {
        ur urVar = (ur) obj;
        if (urVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        urVar.b = (Context) this.b.get();
        urVar.c = (uq) this.c.get();
        urVar.d = (ql) this.d.get();
        urVar.e = (ConnectivityManager) this.e.get();
    }

    private ut(Provider<Context> provider, Provider<uq> provider2, Provider<ql> provider3, Provider<ConnectivityManager> provider4) {
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

    public static MembersInjector<ur> a(Provider<Context> provider, Provider<uq> provider2, Provider<ql> provider3, Provider<ConnectivityManager> provider4) {
        return new ut(provider, provider2, provider3, provider4);
    }
}
