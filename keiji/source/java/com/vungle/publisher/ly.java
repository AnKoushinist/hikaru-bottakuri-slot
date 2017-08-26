package com.vungle.publisher;

import android.content.Context;
import com.tapjoy.TapjoyConstants;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class ly extends abe {
    JSONObject a;
    long b;
    String c;
    String d;
    boolean e;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        pn a;
        @Inject
        agt b;
        @Inject
        Context c;
        @Inject
        Provider<ly> d;

        @Inject
        a() {
        }
    }

    public final /* synthetic */ Object b() {
        return a();
    }

    @Inject
    ly() {
    }

    public final JSONObject a() {
        JSONObject a = super.a();
        a.put("last_polled", this.b);
        a.put("ifa", this.c);
        a.put("isu", this.d);
        a.put("app_store_ids", this.a);
        a.put("is_tracking_enabled", this.e);
        a.put(TapjoyConstants.TJC_PLATFORM, TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        return a;
    }
}
