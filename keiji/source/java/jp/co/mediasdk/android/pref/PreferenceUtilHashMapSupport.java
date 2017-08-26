package jp.co.mediasdk.android.pref;

import jp.co.mediasdk.android.HashMapEX;
import jp.co.mediasdk.android.StringUtilEmptySupport;

public class PreferenceUtilHashMapSupport extends PreferenceUtilBooleanSupport {
    public static boolean a(String str, HashMapEX hashMapEX) {
        if (hashMapEX == null) {
            hashMapEX = new HashMapEX();
        }
        return PreferenceUtilStringSupport.b(str, hashMapEX.b());
    }

    public static HashMapEX b(String str, HashMapEX hashMapEX) {
        String a = PreferenceUtilStringSupport.a(str, null);
        return StringUtilEmptySupport.c(a) ? hashMapEX : new HashMapEX(a);
    }
}
