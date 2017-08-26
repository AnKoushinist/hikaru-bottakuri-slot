package jp.co.geniee.gnadsdk.a;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.c;
import com.google.android.gms.common.d;
import java.io.IOException;
import java.util.HashMap;

/* compiled from: GNAdSDK */
public class b {
    private static Context a = null;
    private static b b = null;
    private static HashMap<String, String> c = new HashMap();

    /* compiled from: GNAdSDK */
    class AnonymousClass1 implements Runnable {
        private final /* synthetic */ Context a;
        private final /* synthetic */ HashMap b;

        AnonymousClass1(Context context, HashMap hashMap) {
            this.a = context;
            this.b = hashMap;
        }

        public void run() {
            Info advertisingIdInfo;
            try {
                advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.a);
            } catch (IOException e) {
                Log.w("GNAdSDK", e.toString());
                advertisingIdInfo = null;
            } catch (c e2) {
                Log.w("GNAdSDK", e2.toString());
                advertisingIdInfo = null;
            } catch (IllegalStateException e3) {
                Log.w("GNAdSDK", e3.toString());
                advertisingIdInfo = null;
            } catch (d e4) {
                Log.w("GNAdSDK", e4.toString());
                advertisingIdInfo = null;
            } catch (Exception e5) {
                Log.w("GNAdSDK", e5.toString());
                advertisingIdInfo = null;
            }
            if (advertisingIdInfo != null) {
                String id = advertisingIdInfo.getId();
                boolean isLimitAdTrackingEnabled = advertisingIdInfo.isLimitAdTrackingEnabled();
                if (id != null && id.length() > 0) {
                    this.b.put("i_adid", id);
                    if (isLimitAdTrackingEnabled) {
                        this.b.put("ad_track", "0");
                    } else {
                        this.b.put("ad_track", "1");
                    }
                }
            }
        }
    }

    private b(Context context) {
        a(context, c);
    }

    public static b a(Context context) {
        a = context;
        if (b == null) {
            b = new b(a);
        }
        return b;
    }

    public void a() {
    }

    protected static HashMap<String, String> b() {
        return c;
    }

    private static void a(Context context, HashMap<String, String> hashMap) {
        new Thread(new AnonymousClass1(context, hashMap)).start();
    }
}
