package jp.co.mediasdk.mscore.ui.hybrid;

import android.annotation.TargetApi;
import android.os.Looper;
import com.tapjoy.TapjoyConstants;
import jp.co.geniee.gnsrewardadapter.GNSAdapterCARewardRewardVideoAd;
import jp.co.mediasdk.android.Hash;
import jp.co.mediasdk.android.HashMapEX;
import jp.co.mediasdk.android.PackageManagerUtil;
import jp.co.mediasdk.android.Util;
import jp.co.mediasdk.android.ad.AdvertisingIdClientUtil;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;

public class MSWebParameterSupport extends MSParameterSupport {
    public static String a = "m_owner_id";
    public static String b = GNSAdapterCARewardRewardVideoAd.MEDIA_ID_COLUMN_NAME;
    public static String c = TapjoyConstants.TJC_API_KEY;
    public static String d = "user_id";
    public static String e = "autoplay";
    public static String f = "pattern";
    public static String g = "crypt";
    public static String h = "dpid";
    public static String i = "os";
    public static String j = "sdkver";
    public static String k = "appver";
    public static String l = "sdk";
    public static String m = "rts";

    public static void a() {
        MSParameterSupport.a(i, TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        MSParameterSupport.a(j, "1.5.1");
        MSParameterSupport.a(l, "1");
        MSParameterSupport.a(k, PackageManagerUtil.b());
        MSParameterSupport.a(m, Util.b() ? "1" : "0");
    }

    @TargetApi(3)
    public static void b() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            MSParameterSupport.a(h, AdvertisingIdClientUtil.b());
        }
    }

    public static String c() {
        String a = MSParameterSupport.a(d);
        return Hash.a(Hash.b(a + MSParameterSupport.a(c)));
    }

    public static String d() {
        b();
        HashMapEX hashMapEX = new HashMapEX();
        hashMapEX.a(d, MSParameterSupport.a(d));
        hashMapEX.a(g, c());
        hashMapEX.a(l, MSParameterSupport.a(l));
        hashMapEX.a(k, MSParameterSupport.a(k));
        hashMapEX.a(j, MSParameterSupport.a(j));
        hashMapEX.a(h, MSParameterSupport.a(h));
        hashMapEX.a(i, MSParameterSupport.a(i));
        hashMapEX.a(e, MSParameterSupport.a(e));
        hashMapEX.a(f, MSParameterSupport.a(f));
        return hashMapEX.e();
    }
}
