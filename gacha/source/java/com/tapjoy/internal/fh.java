package com.tapjoy.internal;

public final class fh {
    public static String a(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String trim = str.trim();
        return (trim == null || trim.length() == 0) ? null : trim;
    }

    public static String b(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() != 0) {
            return trim;
        }
        return null;
    }

    public static String a(String str, String str2, String str3) {
        if (str == null) {
            fj.a(str2, str3, "must not be null");
            return null;
        } else if (str.length() == 0) {
            fj.a(str2, str3, "must not be empty");
            return null;
        } else {
            String trim = str.trim();
            if (trim.length() != 0) {
                return trim;
            }
            fj.a(str2, str3, "must not be blank");
            return null;
        }
    }
}
