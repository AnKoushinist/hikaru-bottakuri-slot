package jp.co.mediasdk.android;

import org.cocos2dx.lib.BuildConfig;

public class MathUtilParseSupport {
    public static boolean a(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static float b(String str) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            try {
                return Float.parseFloat(e(str));
            } catch (Exception e2) {
                LoggerBase.a(MathUtil.class, "parseFloat", "failed to parse '%s'.", str);
                return 0.0f;
            }
        }
    }

    public static int c(String str) {
        return a(str, 10);
    }

    public static int a(String str, int i) {
        int i2 = 0;
        try {
            i2 = Integer.parseInt(str, i);
        } catch (Exception e) {
            try {
                str = e(str);
                if (StringUtilEmptySupport.c(str)) {
                    str = "0";
                }
                i2 = Integer.parseInt(str, i);
            } catch (Exception e2) {
                LoggerBase.a(MathUtil.class, "parseInt", "failed to parse '%s'.", str);
            }
        }
        return i2;
    }

    public static long d(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            try {
                return Long.parseLong(e(str));
            } catch (Exception e2) {
                LoggerBase.a(MathUtil.class, "parseLong", "failed to parse '%s'.", str);
                return 0;
            }
        }
    }

    protected static String e(String str) {
        return StringUtilRegexpSupport.a("[^0-9\\-\\+\\.]", BuildConfig.FLAVOR, str);
    }
}
