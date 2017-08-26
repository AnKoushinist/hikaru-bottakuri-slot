package jp.co.mediasdk.android;

import android.webkit.CookieManager;

public class CookieManagerUtil {
    public static String a(String str) {
        if (str == null) {
            return null;
        }
        CookieSyncManagerUtil.a();
        CookieManager instance = CookieManager.getInstance();
        if (instance != null) {
            return instance.getCookie(str);
        }
        return null;
    }

    public static boolean a(String str, String str2) {
        if (str == null) {
            return false;
        }
        CookieSyncManagerUtil.a();
        CookieManager instance = CookieManager.getInstance();
        instance.setCookie(str, str2);
        if (instance != null) {
            return CookieSyncManagerUtil.b();
        }
        return false;
    }
}
