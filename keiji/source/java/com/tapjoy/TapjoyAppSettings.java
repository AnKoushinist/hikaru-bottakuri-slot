package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.tapjoy.internal.ct;

public class TapjoyAppSettings {
    public static final String TAG = TapjoyAppSettings.class.getSimpleName();
    private static TapjoyAppSettings b;
    String a = this.d.getString(TapjoyConstants.PREF_LOG_LEVEL, null);
    private Context c;
    private SharedPreferences d = this.c.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);

    private TapjoyAppSettings(Context context) {
        this.c = context;
        if (!ct.c(this.a)) {
            TapjoyLog.d(TAG, "restoreLoggingLevel from sharedPref -- loggingLevel=" + this.a);
            TapjoyLog.a(this.a, true);
        }
    }

    public static TapjoyAppSettings getInstance() {
        return b;
    }

    public static void init(Context context) {
        TapjoyLog.d(TAG, "initializing app settings");
        b = new TapjoyAppSettings(context);
    }

    public void saveLoggingLevel(String str) {
        if (ct.c(str)) {
            TapjoyLog.d(TAG, "saveLoggingLevel -- server logging level is NULL or Empty string");
            return;
        }
        TapjoyLog.d(TAG, "saveLoggingLevel -- currentLevel=" + this.a + ";newLevel=" + str);
        if (ct.c(this.a) || !this.a.equals(str)) {
            Editor edit = this.d.edit();
            edit.putString(TapjoyConstants.PREF_LOG_LEVEL, str);
            edit.commit();
            this.a = str;
            TapjoyLog.a(this.a, true);
        }
        TapjoyLog.i(TAG, "Tapjoy remote device debugging set to '" + str + "'. The SDK Debug-setting is: " + (TapjoyLog.isLoggingEnabled() ? "'Enabled'" : "'Disabled'"));
    }

    public void clearLoggingLevel() {
        Editor edit = this.d.edit();
        edit.remove(TapjoyConstants.PREF_LOG_LEVEL);
        edit.commit();
        this.a = null;
        boolean isLoggingEnabled = TapjoyLog.isLoggingEnabled();
        TapjoyLog.i(TAG, "Tapjoy remote device debugging 'Disabled'. The SDK Debug-setting is: " + (isLoggingEnabled ? "'Enabled'" : "'Disabled'"));
        TapjoyLog.setDebugEnabled(isLoggingEnabled);
    }

    public void saveConnectResultAndParams(String str, String str2, long j) {
        if (!ct.c(str) && !ct.c(str2)) {
            Editor edit = this.d.edit();
            edit.putString(TapjoyConstants.PREF_LAST_CONNECT_RESULT, str);
            edit.putString(TapjoyConstants.PREF_LAST_CONNECT_PARAMS_HASH, str2);
            if (j >= 0) {
                edit.putLong(TapjoyConstants.PREF_LAST_CONNECT_RESULT_EXPIRES, j);
            } else {
                edit.remove(TapjoyConstants.PREF_LAST_CONNECT_RESULT_EXPIRES);
            }
            TapjoyLog.i(TAG, "Stored connect result");
            edit.commit();
        }
    }

    public void removeConnectResult() {
        if (this.d.getString(TapjoyConstants.PREF_LAST_CONNECT_PARAMS_HASH, null) != null) {
            Editor edit = this.d.edit();
            edit.remove(TapjoyConstants.PREF_LAST_CONNECT_RESULT);
            edit.remove(TapjoyConstants.PREF_LAST_CONNECT_PARAMS_HASH);
            edit.remove(TapjoyConstants.PREF_LAST_CONNECT_RESULT_EXPIRES);
            TapjoyLog.i(TAG, "Removed connect result");
            edit.commit();
        }
    }

    public String getConnectResult(String str, long j) {
        String string = this.d.getString(TapjoyConstants.PREF_LAST_CONNECT_RESULT, null);
        if (ct.c(string) || ct.c(str) || !str.equals(this.d.getString(TapjoyConstants.PREF_LAST_CONNECT_PARAMS_HASH, null))) {
            return null;
        }
        long j2 = this.d.getLong(TapjoyConstants.PREF_LAST_CONNECT_RESULT_EXPIRES, -1);
        if (j2 < 0 || j2 >= j) {
            return string;
        }
        return null;
    }
}
