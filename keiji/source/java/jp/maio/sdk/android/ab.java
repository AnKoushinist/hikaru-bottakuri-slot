package jp.maio.sdk.android;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.c;
import com.google.android.gms.common.d;

final class ab implements Runnable {
    ab() {
    }

    public void run() {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(v.a());
            if (advertisingIdInfo == null || advertisingIdInfo.isLimitAdTrackingEnabled()) {
                bc.a("SDK API Message", "AdvertisingId get Error. (LimitAdTrackingEnabled = True)", null);
                return;
            }
            aa.d = advertisingIdInfo.getId();
            bc.a("SDK API Message", "AdvertisingId get success. (advertisementId: " + aa.d + ")", null);
        } catch (Throwable e) {
            bc.a("SDK API Message", "AdvertisingId get error. (IOException) ", e);
        } catch (c e2) {
            bc.a("SDK API Message", "AdvertisingId get error. (GooglePlayServicesNotAvailableException) ", e2);
        } catch (Throwable e3) {
            bc.a("SDK API Message", "AdvertisingId get error. (IllegalStateException) ", e3);
            throw e3;
        } catch (d e4) {
            bc.a("SDK API Message", "AdvertisingId get error. (GooglePlayServicesRepairableException) ", e4);
        } catch (Throwable e32) {
            bc.a("SDK API Message", "AdvertisingId get error. (VerifyError) ", e32);
        } catch (Throwable e322) {
            bc.a("SDK API Message", "AdvertisingId get error (NullPointerError) ", e322);
        }
    }
}
