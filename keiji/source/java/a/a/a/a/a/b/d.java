package a.a.a.a.a.b;

import a.a.a.a.c;
import android.content.Context;

/* compiled from: AdvertisingInfoReflectionStrategy */
class d implements f {
    private final Context a;

    public d(Context context) {
        this.a = context.getApplicationContext();
    }

    boolean a(Context context) {
        try {
            if (((Integer) Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(null, new Object[]{context})).intValue() == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public b a() {
        if (a(this.a)) {
            return new b(b(), c());
        }
        return null;
    }

    private String b() {
        try {
            return (String) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("getId", new Class[0]).invoke(d(), new Object[0]);
        } catch (Exception e) {
            c.h().d("Fabric", "Could not call getId on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return null;
        }
    }

    private boolean c() {
        try {
            return ((Boolean) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(d(), new Object[0])).booleanValue();
        } catch (Exception e) {
            c.h().d("Fabric", "Could not call isLimitAdTrackingEnabled on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return false;
        }
    }

    private Object d() {
        Object obj = null;
        try {
            obj = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.a});
        } catch (Exception e) {
            c.h().d("Fabric", "Could not call getAdvertisingIdInfo on com.google.android.gms.ads.identifier.AdvertisingIdClient");
        }
        return obj;
    }
}
