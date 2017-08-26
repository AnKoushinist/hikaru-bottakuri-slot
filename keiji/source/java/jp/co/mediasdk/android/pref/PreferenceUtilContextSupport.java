package jp.co.mediasdk.android.pref;

import android.content.Context;
import android.content.SharedPreferences;
import jp.co.mediasdk.android.LoggerBase;

public class PreferenceUtilContextSupport extends PreferenceUtilParamSupport {
    protected static SharedPreferences a = null;

    public static boolean a(Context context) {
        if (a()) {
            return true;
        }
        if (context == null) {
            LoggerBase.a(PreferenceUtilContextSupport.class, "initialize", "context is null.", new Object[0]);
            return false;
        }
        PreferenceUtil.a = context.getSharedPreferences("jp_co_mediasdk_userparams", 0);
        return true;
    }

    public static boolean a() {
        return PreferenceUtil.a != null;
    }
}
