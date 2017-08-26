package com.tapjoy.internal;

import org.cocos2dx.lib.BuildConfig;

public final class ct {
    public static String a(String str) {
        return str == null ? BuildConfig.FLAVOR : str;
    }

    public static String b(String str) {
        return c(str) ? null : str;
    }

    public static boolean c(String str) {
        return str == null || str.length() == 0;
    }
}
