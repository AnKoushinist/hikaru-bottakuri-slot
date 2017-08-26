package jp.co.mediasdk.mscore.ui.common;

import jp.co.mediasdk.android.HashMapEX;
import jp.co.mediasdk.android.pref.PreferenceUtilParamSupport;
import org.cocos2dx.lib.BuildConfig;

public class MSParameterSupport {
    public static String n = "loading_text_key";

    public static void a(String str, String str2) {
        if (str2 == null) {
            str2 = BuildConfig.FLAVOR;
        }
        HashMapEX b = PreferenceUtilParamSupport.b();
        if (b == null) {
            b = new HashMapEX();
        }
        b.c(str, str2);
        PreferenceUtilParamSupport.a(b);
    }

    public static String a(String str) {
        return PreferenceUtilParamSupport.b().j(str);
    }

    public static boolean b(String str) {
        return PreferenceUtilParamSupport.j(str);
    }

    public static void c(String str) {
        HashMapEX b = PreferenceUtilParamSupport.b();
        b.remove(str);
        PreferenceUtilParamSupport.a(b);
    }
}
