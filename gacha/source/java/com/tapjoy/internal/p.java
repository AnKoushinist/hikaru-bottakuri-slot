package com.tapjoy.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class p {
    public static boolean a(SharedPreferences sharedPreferences, String str, boolean z) {
        Editor edit = sharedPreferences.edit();
        edit.putBoolean(str, z);
        return edit.commit();
    }

    public static boolean a(SharedPreferences sharedPreferences, String str, int i) {
        Editor edit = sharedPreferences.edit();
        edit.putInt(str, i);
        return edit.commit();
    }

    public static boolean a(SharedPreferences sharedPreferences, String str, String str2) {
        Editor edit = sharedPreferences.edit();
        edit.putString(str, str2);
        return edit.commit();
    }
}
