package jp.co.mediasdk.mscore.ui.pva;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import jp.co.geniee.gnsrewardadapter.GNSAdapterCARewardRewardVideoAd;
import jp.co.mediasdk.android.DateUtil;
import jp.co.mediasdk.android.Hash;
import jp.co.mediasdk.android.PackageManagerUtil;
import jp.co.mediasdk.android.StringUtilEmptySupport;
import jp.co.mediasdk.android.StringUtilEqualsSupport;
import jp.co.mediasdk.android.URIUtilBase;
import jp.co.mediasdk.android.ad.AdvertisingIdClientUtil;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;
import org.cocos2dx.lib.BuildConfig;

public class MSPVAParamater {
    public static String a(String str, String str2) {
        return Hash.b(str + ":" + str2, MSParameterSupport.a("sdk_token"));
    }

    public static String a() {
        return MSParameterSupport.a("media_user_id");
    }

    public static HashMap<String, Object> b() {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put(TapjoyConstants.TJC_ADVERTISING_ID, c());
        hashMap.put(TapjoyConstants.TJC_APP_VERSION_NAME, PackageManagerUtil.b());
        hashMap.put(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, URIUtilBase.a(Build.MODEL));
        hashMap.put(GNSAdapterCARewardRewardVideoAd.MEDIA_ID_COLUMN_NAME, Integer.valueOf(Integer.parseInt(MSParameterSupport.a(GNSAdapterCARewardRewardVideoAd.MEDIA_ID_COLUMN_NAME))));
        hashMap.put("media_user_id", MSParameterSupport.a("media_user_id"));
        hashMap.put("os", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        hashMap.put(TapjoyConstants.TJC_DEVICE_OS_VERSION_NAME, VERSION.RELEASE);
        hashMap.put("sdk_version", "1.5.1");
        long a = DateUtil.a();
        String valueOf = String.valueOf(a);
        hashMap.put(TapjoyConstants.TJC_TIMESTAMP, Long.valueOf(a));
        hashMap.put(TapjoyConstants.TJC_VERIFIER, a(a(), valueOf));
        valueOf = MSParameterSupport.a("registered_time");
        a = 0;
        if (!(valueOf == null || StringUtilEqualsSupport.a(BuildConfig.FLAVOR, valueOf))) {
            a = Long.valueOf(valueOf).longValue();
        }
        hashMap.put("registered_time", Long.valueOf(a));
        String a2 = MSParameterSupport.a("media_user_attributes");
        if (!(a2 == null || StringUtilEqualsSupport.a(BuildConfig.FLAVOR, a2))) {
            hashMap.put("media_user_attributes", a2);
        }
        return hashMap;
    }

    @TargetApi(3)
    public static String c() {
        if (MSParameterSupport.b(TapjoyConstants.TJC_ADVERTISING_ID) && !StringUtilEmptySupport.c(MSParameterSupport.a(TapjoyConstants.TJC_ADVERTISING_ID))) {
            return MSParameterSupport.a(TapjoyConstants.TJC_ADVERTISING_ID);
        }
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            return BuildConfig.FLAVOR;
        }
        MSParameterSupport.a(TapjoyConstants.TJC_ADVERTISING_ID, AdvertisingIdClientUtil.b());
        return MSParameterSupport.a(TapjoyConstants.TJC_ADVERTISING_ID);
    }

    public static void d() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            MSParameterSupport.a(TapjoyConstants.TJC_ADVERTISING_ID, AdvertisingIdClientUtil.b());
        }
    }
}
