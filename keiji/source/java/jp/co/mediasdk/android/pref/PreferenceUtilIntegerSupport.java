package jp.co.mediasdk.android.pref;

import jp.co.mediasdk.android.LoggerBase;

public class PreferenceUtilIntegerSupport extends PreferenceUtilLongSupport {
    public static boolean f(String str) {
        if (str == null || !PreferenceUtilContextSupport.a()) {
            LoggerBase.a(PreferenceUtil.class, "isInt", "context or key is null.", new Object[0]);
            return false;
        }
        try {
            PreferenceUtil.a.getInt(str, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static int g(String str) {
        return a(str, 0);
    }

    public static int a(String str, int i) {
        int i2 = 0;
        if (str == null || !PreferenceUtilContextSupport.a()) {
            LoggerBase.a(PreferenceUtil.class, "getInt", "context or key is null.", new Object[0]);
            return i;
        }
        try {
            return PreferenceUtil.a.getInt(str, i);
        } catch (Exception e) {
            if (PreferenceUtilStringSupport.m(str)) {
                return Integer.valueOf(PreferenceUtilStringSupport.l(str)).intValue();
            }
            if (PreferenceUtilFloatSupport.d(str)) {
                return (int) PreferenceUtilFloatSupport.e(str);
            }
            if (!PreferenceUtilBooleanSupport.c(str)) {
                return i;
            }
            if (PreferenceUtilBooleanSupport.b(str)) {
                i2 = 1;
            }
            return i2;
        }
    }
}
