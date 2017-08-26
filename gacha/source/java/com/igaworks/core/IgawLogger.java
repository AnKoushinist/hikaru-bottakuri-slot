package com.igaworks.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

public class IgawLogger {
    public static ApplicationInfo appInfo;
    public static String logMode = null;

    public static void Logging(Context context, String str, String str2, int i) {
        Logging(context, str, str2, i, true);
    }

    public static void Logging(Context context, String str, String str2, int i, boolean z) {
        try {
            if (logMode == null) {
                appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                logMode = (String) appInfo.metaData.get("adbrix_logger_mode");
            }
        } catch (NameNotFoundException e) {
        } catch (Exception e2) {
        }
        if (logMode != null && logMode.equals("none")) {
            return;
        }
        if (!z || (logMode != null && logMode.equals("test"))) {
            switch (i) {
                case TwitterResponse.NONE /*0*/:
                    try {
                        Log.e(str, str2);
                        return;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                case TwitterResponse.READ /*1*/:
                    Log.w(str, str2);
                    return;
                case TwitterResponse.READ_WRITE /*2*/:
                    Log.i(str, str2);
                    return;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    Log.d(str, str2);
                    return;
                case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                    Log.v(str, str2);
                    return;
                default:
                    return;
            }
            e3.printStackTrace();
        }
    }
}
