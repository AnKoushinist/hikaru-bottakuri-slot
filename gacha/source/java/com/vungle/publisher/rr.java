package com.vungle.publisher;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.vungle.log.Logger;
import dagger.a.c;
import dagger.a.e;
import javax.inject.Provider;

/* compiled from: vungle */
public final class rr implements c<TelephonyManager> {
    static final /* synthetic */ boolean a = (!rr.class.desiredAssertionStatus());
    private final re b;
    private final Provider<Context> c;

    public final /* synthetic */ Object get() {
        TelephonyManager telephonyManager = (TelephonyManager) ((Context) this.c.get()).getSystemService("phone");
        if (telephonyManager == null) {
            Logger.d(Logger.DEVICE_TAG, "TelephonyManager not avaialble");
        }
        return (TelephonyManager) e.a(telephonyManager, "Cannot return null from a non-@Nullable @Provides method");
    }

    private rr(re reVar, Provider<Context> provider) {
        if (a || reVar != null) {
            this.b = reVar;
            if (a || provider != null) {
                this.c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static c<TelephonyManager> a(re reVar, Provider<Context> provider) {
        return new rr(reVar, provider);
    }
}
