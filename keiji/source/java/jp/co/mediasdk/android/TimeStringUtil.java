package jp.co.mediasdk.android;

public class TimeStringUtil {
    public static int a(String str) {
        if (str == null || StringUtilEmptySupport.c(str)) {
            return 0;
        }
        String[] split = str.split(":");
        if (split.length == 3) {
            return (((Integer.valueOf(split[0]).intValue() * 60) * 60) + (Integer.valueOf(split[1]).intValue() * 60)) + Integer.valueOf(b(split[2])).intValue();
        } else if (split.length == 2) {
            return (Integer.valueOf(split[0]).intValue() * 60) + Integer.valueOf(b(split[1])).intValue();
        } else if (split.length == 1) {
            return Integer.valueOf(b(split[0])).intValue();
        } else {
            return 0;
        }
    }

    public static String b(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf != -1) {
            return str.substring(0, lastIndexOf);
        }
        return str;
    }
}
