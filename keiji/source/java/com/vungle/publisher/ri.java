package com.vungle.publisher;

import android.content.Context;
import android.net.ConnectivityManager;
import com.vungle.log.Logger;
import dagger.a.c;
import dagger.a.e;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ri implements c<ConnectivityManager> {
    static final /* synthetic */ boolean a = (!ri.class.desiredAssertionStatus());
    private final re b;
    private final Provider<Context> c;

    public final /* synthetic */ Object get() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ((Context) this.c.get()).getSystemService("connectivity");
        if (connectivityManager == null) {
            Logger.d(Logger.DEVICE_TAG, "ConnectivityManager not available");
        }
        return (ConnectivityManager) e.a(connectivityManager, "Cannot return null from a non-@Nullable @Provides method");
    }

    private ri(re reVar, Provider<Context> provider) {
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

    public static c<ConnectivityManager> a(re reVar, Provider<Context> provider) {
        return new ri(reVar, provider);
    }
}
