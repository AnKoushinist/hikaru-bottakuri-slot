package jp.co.mediasdk.android.ad;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import jp.co.mediasdk.android.ResourceContextSupport;
import org.cocos2dx.lib.BuildConfig;

public class AdvertisingIdClientUtil {
    protected static Info a() {
        int i = 0;
        while (i < 3) {
            try {
                return AdvertisingIdClient.getAdvertisingIdInfo(ResourceContextSupport.i());
            } catch (Exception e) {
                try {
                    Thread.sleep(1);
                } catch (Exception e2) {
                }
                i++;
            }
        }
        return null;
    }

    public static String b() {
        Info a = a();
        if (a == null) {
            return BuildConfig.FLAVOR;
        }
        return a.getId();
    }
}
