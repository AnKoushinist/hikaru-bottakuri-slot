package jp.co.mediasdk.android.pref;

import android.content.SharedPreferences.Editor;
import jp.co.mediasdk.android.LoggerBase;

public class PreferenceUtilStringSupport extends PreferenceUtilListenerSupport {
    public static String l(String str) {
        return a(str, null);
    }

    public static String a(String str, String str2) {
        if (str == null || !PreferenceUtilContextSupport.a()) {
            LoggerBase.a(PreferenceUtil.class, "getString", "context or key is null.", new Object[0]);
            return str2;
        }
        try {
            return PreferenceUtil.a.getString(str, str2);
        } catch (Exception e) {
            if (PreferenceUtilIntegerSupport.f(str)) {
                return String.valueOf(PreferenceUtilIntegerSupport.g(str));
            }
            if (PreferenceUtilLongSupport.i(str)) {
                return String.valueOf(PreferenceUtilLongSupport.h(str));
            }
            if (PreferenceUtilFloatSupport.d(str)) {
                return String.valueOf(PreferenceUtilFloatSupport.e(str));
            }
            if (m(str)) {
                return String.valueOf(l(str));
            }
            if (PreferenceUtilBooleanSupport.c(str)) {
                return String.valueOf(l(str));
            }
            return str2;
        }
    }

    public static boolean b(String str, String str2) {
        if (str == null || str2 == null || !PreferenceUtilContextSupport.a()) {
            LoggerBase.a(PreferenceUtil.class, "set", "context or key is null.", new Object[0]);
            return false;
        }
        Editor edit = PreferenceUtil.a.edit();
        edit.putString(str, str2);
        return edit.commit();
    }

    public static boolean m(String str) {
        if (str == null || !PreferenceUtilContextSupport.a()) {
            LoggerBase.a(PreferenceUtil.class, "isString", "context or key is null.", new Object[0]);
            return false;
        }
        try {
            PreferenceUtil.a.getString(str, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
