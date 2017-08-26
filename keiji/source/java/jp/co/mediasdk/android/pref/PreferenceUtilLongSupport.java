package jp.co.mediasdk.android.pref;

import jp.co.mediasdk.android.LoggerBase;

public class PreferenceUtilLongSupport extends PreferenceUtilStringSupport {
    public static long h(String str) {
        return a(str, 0);
    }

    public static long a(String str, long j) {
        if (str == null || !PreferenceUtilContextSupport.a()) {
            LoggerBase.a(PreferenceUtil.class, "getLong", "context or key is null.", new Object[0]);
            return j;
        }
        try {
            return PreferenceUtil.a.getLong(str, j);
        } catch (Exception e) {
            if (PreferenceUtilStringSupport.m(str)) {
                return (long) Integer.parseInt(PreferenceUtilStringSupport.l(str));
            }
            if (PreferenceUtilFloatSupport.d(str)) {
                return (long) ((int) PreferenceUtilFloatSupport.e(str));
            }
            if (!PreferenceUtilBooleanSupport.c(str)) {
                return j;
            }
            return PreferenceUtilBooleanSupport.b(str) ? 1 : 0;
        }
    }

    public static boolean i(String str) {
        if (str == null || !PreferenceUtilContextSupport.a()) {
            LoggerBase.a(PreferenceUtil.class, "isInt", "context or key is null.", new Object[0]);
            return false;
        }
        try {
            PreferenceUtil.a.getLong(str, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
