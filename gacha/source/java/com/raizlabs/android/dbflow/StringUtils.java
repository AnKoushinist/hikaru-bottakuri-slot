package com.raizlabs.android.dbflow;

import org.cocos2dx.lib.BuildConfig;

public class StringUtils {
    public static boolean isNotNullOrEmpty(String str) {
        return (str == null || str.equals(BuildConfig.FLAVOR) || str.length() <= 0) ? false : true;
    }
}
