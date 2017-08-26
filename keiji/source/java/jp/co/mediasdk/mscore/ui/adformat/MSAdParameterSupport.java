package jp.co.mediasdk.mscore.ui.adformat;

import android.annotation.TargetApi;
import android.os.Looper;
import com.tapjoy.TapjoyConstants;
import jp.co.geniee.gnsrewardadapter.GNSAdapterCARewardRewardVideoAd;
import jp.co.mediasdk.android.DateUtil;
import jp.co.mediasdk.android.Hash;
import jp.co.mediasdk.android.HashMapEX;
import jp.co.mediasdk.android.StringUtilEmptySupport;
import jp.co.mediasdk.android.Util;
import jp.co.mediasdk.android.ad.AdvertisingIdClientUtil;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;

public class MSAdParameterSupport extends MSParameterSupport {
    public static String a = "m_owner_id";
    public static String b = GNSAdapterCARewardRewardVideoAd.MEDIA_ID_COLUMN_NAME;
    public static String c = TapjoyConstants.TJC_API_KEY;
    public static String d = "user_id";
    public static String e = "autoplay";
    public static String f = "crypt";
    public static String g = "advid";
    public static String h = "game_object_name";
    public static String i = "time";
    public static String j = "rts";
    public static String k = "cb";
    public static String l = "sdk";
    public static String m = "sdkver";

    public static void a() {
        MSParameterSupport.a(j, Util.b() ? "1" : "0");
        b();
    }

    @TargetApi(3)
    public static void b() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            MSParameterSupport.a(g, AdvertisingIdClientUtil.b());
        }
    }

    public static String c() {
        String a = MSParameterSupport.a(c);
        return Hash.a(Hash.c(a + ":" + d() + ":" + MSParameterSupport.a(b)));
    }

    public static String d() {
        String a = MSParameterSupport.a(d);
        if (!MSParameterSupport.b(d) || StringUtilEmptySupport.c(a)) {
            return Util.a();
        }
        return a;
    }

    public static String e() {
        b();
        HashMapEX hashMapEX = new HashMapEX();
        hashMapEX.a(m, "1.5.1");
        hashMapEX.a(d, d());
        hashMapEX.a(f, c());
        hashMapEX.a(g, MSParameterSupport.a(g));
        hashMapEX.a(e, MSParameterSupport.a(e));
        if (MSParameterSupport.b(k)) {
            hashMapEX.a(k, MSParameterSupport.a(k));
        }
        hashMapEX.a(j, MSParameterSupport.a(j));
        hashMapEX.a(l, "1");
        hashMapEX.a(i, String.valueOf(DateUtil.a()));
        return hashMapEX.e();
    }
}
