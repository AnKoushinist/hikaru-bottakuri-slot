package com.onesignal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.onesignal.OneSignal.LOG_LEVEL;

class AdvertisingIdProviderGPS implements AdvertisingIdentifierProvider {
    AdvertisingIdProviderGPS() {
    }

    public String getIdentifier(Context context) {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            String id = advertisingIdInfo.getId();
            if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                return "OptedOut";
            }
            return id;
        } catch (Throwable th) {
            OneSignal.Log(LOG_LEVEL.INFO, "Error getting Google Ad id: ", th);
            return null;
        }
    }
}
