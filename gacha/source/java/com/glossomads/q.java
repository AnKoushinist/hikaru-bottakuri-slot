package com.glossomads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class q {
    public static SharedPreferences a() {
        return s.f().getSharedPreferences("SugarSDK", 0);
    }

    public static String a(String str) {
        return a(str, null);
    }

    public static String a(String str, String str2) {
        return a().getString(str, str2);
    }

    public static int a(String str, int i) {
        return a().getInt(str, i);
    }

    public static void b(String str, int i) {
        Editor edit = a().edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public static boolean a(String str, boolean z) {
        return a().getBoolean(str, z);
    }

    public static void b(String str, boolean z) {
        Editor edit = a().edit();
        if (SugarUtil.isNotEmptyValue(str)) {
            edit.putString("SugarAdvertisingId", str);
        }
        edit.putBoolean("SugarLimitAdTracking", z);
        edit.apply();
    }
}
