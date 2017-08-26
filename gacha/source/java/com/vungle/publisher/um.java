package com.vungle.publisher;

import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class um implements MembersInjector<uk> {
    static final /* synthetic */ boolean a = (!um.class.desiredAssertionStatus());
    private final Provider<ConnectivityManager> b;
    private final Provider<ur> c;
    private final Provider<TelephonyManager> d;

    public final /* synthetic */ void injectMembers(Object obj) {
        uk ukVar = (uk) obj;
        if (ukVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        ukVar.a = (ConnectivityManager) this.b.get();
        ukVar.b = this.c;
        ukVar.c = (TelephonyManager) this.d.get();
    }

    private um(Provider<ConnectivityManager> provider, Provider<ur> provider2, Provider<TelephonyManager> provider3) {
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

    public static MembersInjector<uk> a(Provider<ConnectivityManager> provider, Provider<ur> provider2, Provider<TelephonyManager> provider3) {
        return new um(provider, provider2, provider3);
    }
}
