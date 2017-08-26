package jp.co.mediasdk.android.pref;

import com.tapjoy.TapjoyConnectCore;
import jp.co.mediasdk.android.LoggerBase;

public class PreferenceUtilFloatSupport extends PreferenceUtilIntegerSupport {
    public static boolean d(String str) {
        if (str == null || !PreferenceUtilContextSupport.a()) {
            LoggerBase.a(PreferenceUtil.class, "isFloat", "context or key is null.", new Object[0]);
            return false;
        }
        try {
            PreferenceUtil.a.getFloat(str, 0.0f);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static float e(String str) {
        return a(str, 0.0f);
    }

    public static float a(String str, float f) {
        if (str == null || !PreferenceUtilContextSupport.a()) {
            LoggerBase.a(PreferenceUtil.class, "getFloat", "context or key is null.", new Object[0]);
            return f;
        } else if (!PreferenceUtil.a(str)) {
            return f;
        } else {
            try {
                return PreferenceUtil.a.getFloat(str, f);
            } catch (Exception e) {
                if (PreferenceUtilStringSupport.m(str)) {
                    return Float.parseFloat(PreferenceUtilStringSupport.l(str));
                }
                if (PreferenceUtilIntegerSupport.f(str)) {
                    return (float) PreferenceUtilIntegerSupport.g(str);
                }
                if (PreferenceUtilLongSupport.i(str)) {
                    return (float) PreferenceUtilLongSupport.h(str);
                }
                if (!PreferenceUtilBooleanSupport.c(str)) {
                    return f;
                }
                return PreferenceUtilBooleanSupport.b(str) ? TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER : 0.0f;
            }
        }
    }
}
