package com.vungle.publisher;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.tapjoy.TapjoyConstants;
import dagger.a.c;
import dagger.a.e;
import javax.inject.Provider;

/* compiled from: vungle */
public final class sb implements c<WifiManager> {
    static final /* synthetic */ boolean a = (!sb.class.desiredAssertionStatus());
    private final rz b;
    private final Provider<Context> c;

    public final /* synthetic */ Object get() {
        return (WifiManager) e.a((WifiManager) ((Context) this.c.get()).getSystemService(TapjoyConstants.TJC_CONNECTION_TYPE_WIFI), "Cannot return null from a non-@Nullable @Provides method");
    }

    private sb(rz rzVar, Provider<Context> provider) {
        if (a || rzVar != null) {
            this.b = rzVar;
            if (a || provider != null) {
                this.c = provider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static c<WifiManager> a(rz rzVar, Provider<Context> provider) {
        return new sb(rzVar, provider);
    }
}
