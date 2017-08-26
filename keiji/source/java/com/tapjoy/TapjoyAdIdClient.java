package com.tapjoy;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.lang.reflect.Method;

public class TapjoyAdIdClient {
    private Context a;
    private String b;
    private boolean c;

    public TapjoyAdIdClient(Context context) {
        this.a = context;
    }

    public boolean setupAdIdInfo() {
        try {
            boolean z;
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.a);
            this.b = advertisingIdInfo.getId();
            if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                z = false;
            } else {
                z = true;
            }
            this.c = z;
            return true;
        } catch (Exception e) {
            return false;
        } catch (Error e2) {
            return false;
        }
    }

    public boolean setupAdIdInfoReflection() {
        try {
            Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            Method method = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class});
            TapjoyLog.d("TapjoyAdIdClient", "Found method: " + method);
            Object invoke = method.invoke(cls, new Object[]{this.a});
            Method method2 = invoke.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]);
            Method method3 = invoke.getClass().getMethod("getId", new Class[0]);
            this.c = !((Boolean) method2.invoke(invoke, new Object[0])).booleanValue();
            this.b = (String) method3.invoke(invoke, new Object[0]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAdvertisingId() {
        return this.b;
    }

    public boolean isAdTrackingEnabled() {
        return this.c;
    }
}
