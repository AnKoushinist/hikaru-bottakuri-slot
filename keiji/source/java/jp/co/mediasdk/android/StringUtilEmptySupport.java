package jp.co.mediasdk.android;

import org.cocos2dx.lib.BuildConfig;

public class StringUtilEmptySupport {
    public static boolean c(String str) {
        if (str == null || str.equals(BuildConfig.FLAVOR)) {
            return true;
        }
        return false;
    }

    public static boolean a(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return true;
        }
        return false;
    }
}
