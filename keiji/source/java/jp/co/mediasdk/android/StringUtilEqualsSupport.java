package jp.co.mediasdk.android;

public class StringUtilEqualsSupport extends StringUtilToStringSupport {
    public static boolean a(String str, String str2) {
        if (str == null || str2 == null || !str.equals(str2)) {
            return false;
        }
        return true;
    }
}
