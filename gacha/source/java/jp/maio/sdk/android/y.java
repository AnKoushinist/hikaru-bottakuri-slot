package jp.maio.sdk.android;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.c;
import com.google.android.gms.common.d;

final class y implements Runnable {
    y() {
    }

    public void run() {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(t.a());
            if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                ax.a("SDK API Message", "AdvertisingId get Error. (LimitAdTrackingEnabled = True)", null);
                return;
            }
            x.d = advertisingIdInfo.getId();
            ax.a("SDK API Message", "AdvertisingId get success. (advertisementId: " + x.d + ")", null);
        } catch (Throwable e) {
            ax.a("SDK API Message", "AdvertisingId get error. (IOException) ", e);
        } catch (c e2) {
            ax.a("SDK API Message", "AdvertisingId get error. (GooglePlayServicesNotAvailableException) ", e2);
        } catch (Throwable e3) {
            ax.a("SDK API Message", "AdvertisingId get error. (IllegalStateException) ", e3);
            throw e3;
        } catch (d e4) {
            ax.a("SDK API Message", "AdvertisingId get error. (GooglePlayServicesRepairableException) ", e4);
        } catch (Throwable e32) {
            ax.a("SDK API Message", "Verify error. (GooglePlayServicesRepairableException) ", e32);
        }
    }
}
