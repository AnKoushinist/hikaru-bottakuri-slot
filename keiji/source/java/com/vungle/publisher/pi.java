package com.vungle.publisher;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.vungle.log.Logger;
import com.vungle.publisher.bt.b;
import com.vungle.publisher.env.AndroidDevice;
import com.vungle.publisher.env.AndroidDevice.DeviceIdStrategy;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class pi extends DeviceIdStrategy {
    @Inject
    protected Context c;
    @Inject
    protected ql d;
    @Inject
    protected bt e;

    @Inject
    pi() {
    }

    protected final void c(final AndroidDevice androidDevice) {
        this.e.a(new Runnable(this) {
            final /* synthetic */ pi b;

            public final void run() {
                this.b.d(androidDevice);
            }
        }, b.deviceId);
    }

    protected final void d(AndroidDevice androidDevice) {
        Object obj = !a(androidDevice) ? 1 : null;
        if (b(androidDevice) && obj != null) {
            this.d.a(new qb());
        }
    }

    protected boolean a(AndroidDevice androidDevice) {
        return androidDevice.b();
    }

    protected boolean b(AndroidDevice androidDevice) {
        boolean z = false;
        try {
            if (androidDevice.b(Logger.DEVICE_TAG)) {
                Logger.d(Logger.DEVICE_TAG, "fetching advertising ID and ad tracking preference");
                Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.c);
                String id = advertisingIdInfo.getId();
                boolean z2 = !advertisingIdInfo.isLimitAdTrackingEnabled() ? true : z;
                Logger.d(Logger.DEVICE_TAG, "advertising ID " + id + "; ad tracking enabled " + z2);
                androidDevice.d = id;
                if (androidDevice.b() && (AndroidDevice.a(androidDevice.e) || androidDevice.l())) {
                    androidDevice.d();
                    androidDevice.k();
                }
                androidDevice.e();
                androidDevice.c = z2;
            }
        } catch (Throwable e) {
            Logger.w(Logger.DEVICE_TAG, "error fetching advertising ID and ad tracking preference", e);
        }
        try {
            z = androidDevice.b();
        } catch (Throwable e2) {
            Logger.w(Logger.DEVICE_TAG, "error verifying advertising ID", e2);
        }
        return z;
    }
}
