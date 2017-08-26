package jp.co.mediasdk.android.pref;

import jp.co.mediasdk.android.HashMapEX;

public class PreferenceUtilParamSupport {
    public static HashMapEX b() {
        return PreferenceUtilHashMapSupport.b("mediasdk", new HashMapEX());
    }

    public static boolean a(HashMapEX hashMapEX) {
        return PreferenceUtilHashMapSupport.a("mediasdk", hashMapEX);
    }

    public static boolean j(String str) {
        b();
        return b().a(str);
    }

    public static String c() {
        return PreferenceUtilStringSupport.l("uuid");
    }

    public static boolean k(String str) {
        return PreferenceUtilStringSupport.b("uuid", str);
    }
}
