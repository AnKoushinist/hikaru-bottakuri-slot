package jp.co.mediasdk.android;

import android.webkit.CookieSyncManager;

public class CookieSyncManagerUtil {
    public static boolean a() {
        CookieSyncManager.createInstance(ResourceContextSupport.i());
        return true;
    }

    public static boolean b() {
        CookieSyncManager.getInstance().sync();
        return true;
    }
}
