package com.glossomads.Logger;

import android.util.Log;

public class SugarLogger {
    private static final String TAG = "GlossomAds";

    public static void d(String str) {
        Log.d(TAG, str);
    }

    public static void d(String str, String str2) {
        Log.d(str, str2);
    }

    public static void e(String str) {
        Log.e(TAG, str);
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
    }

    public static void i(String str) {
        Log.i(TAG, str);
    }

    public static void i(String str, String str2) {
        Log.i(str, str2);
    }

    public static void v(String str) {
        Log.v(TAG, str);
    }

    public static void v(String str, String str2) {
        Log.v(str, str2);
    }

    public static void w(String str) {
        Log.w(TAG, str);
    }

    public static void w(String str, String str2) {
        Log.w(str, str2);
    }
}
