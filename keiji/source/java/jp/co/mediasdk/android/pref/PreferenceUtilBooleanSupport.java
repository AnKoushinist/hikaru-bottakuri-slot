package jp.co.mediasdk.android.pref;

import jp.co.mediasdk.android.LoggerBase;

public class PreferenceUtilBooleanSupport extends PreferenceUtilFloatSupport {
    public static boolean b(String str) {
        return a(str, false);
    }

    public static boolean a(String str, boolean z) {
        boolean z2 = true;
        if (str == null || !PreferenceUtilContextSupport.a()) {
            LoggerBase.a(PreferenceUtil.class, "getBoolean", "context or key is null.", new Object[0]);
            return z;
        } else if (!PreferenceUtil.a(str)) {
            return z;
        } else {
            try {
                return PreferenceUtil.a.getBoolean(str, z);
            } catch (Exception e) {
                if (PreferenceUtilStringSupport.m(str)) {
                    return Boolean.parseBoolean(PreferenceUtilStringSupport.l(str));
                }
                if (PreferenceUtilIntegerSupport.f(str)) {
                    if (PreferenceUtilIntegerSupport.g(str) == 0) {
                        z2 = false;
                    }
                    return z2;
                } else if (PreferenceUtilLongSupport.i(str)) {
                    if (PreferenceUtilLongSupport.h(str) == 0) {
                        z2 = false;
                    }
                    return z2;
                } else if (!PreferenceUtilFloatSupport.d(str)) {
                    return z;
                } else {
                    if (PreferenceUtilFloatSupport.e(str) == 0.0f) {
                        z2 = false;
                    }
                    return z2;
                }
            }
        }
    }

    public static boolean c(String str) {
        if (str == null || !PreferenceUtilContextSupport.a()) {
            LoggerBase.a(PreferenceUtil.class, "isBoolean", "context or key is null.", new Object[0]);
            return false;
        }
        try {
            PreferenceUtil.a.getBoolean(str, false);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
