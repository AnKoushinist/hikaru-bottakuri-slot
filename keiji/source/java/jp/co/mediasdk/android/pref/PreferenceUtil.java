package jp.co.mediasdk.android.pref;

import java.util.Map;
import jp.co.mediasdk.android.LoggerBase;

public class PreferenceUtil extends PreferenceUtilHashMapSupport {
    public static boolean a(String str) {
        if (str == null || !PreferenceUtilContextSupport.a()) {
            LoggerBase.a(PreferenceUtil.class, "has", "context or key is null.", new Object[0]);
            return false;
        }
        Map all = a.getAll();
        if (all != null) {
            return all.containsKey(str);
        }
        LoggerBase.a(PreferenceUtil.class, "has", "fetch all preference keys failed.", new Object[0]);
        return false;
    }
}
