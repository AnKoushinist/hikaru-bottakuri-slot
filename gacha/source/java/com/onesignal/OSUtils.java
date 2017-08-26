package com.onesignal;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.onesignal.OneSignal.LOG_LEVEL;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.util.Locale;
import java.util.UUID;
import org.cocos2dx.lib.BuildConfig;

class OSUtils {
    OSUtils() {
    }

    int initializationChecker(int i, String str) {
        int i2 = 1;
        try {
            UUID.fromString(str);
            if ("b2f7f966-d8cc-11e4-bed1-df8f05be55ba".equals(str) || "5eb5a37e-b458-11e3-ac11-000c2940e62c".equals(str)) {
                OneSignal.Log(LOG_LEVEL.WARN, "OneSignal Example AppID detected, please update to your app's id found on OneSignal.com");
            }
            if (i == 1) {
                try {
                    Class.forName("com.google.android.gms.b.a");
                } catch (Throwable e) {
                    OneSignal.Log(LOG_LEVEL.FATAL, "The GCM Google Play services client library was not found. Please make sure to include it in your project.", e);
                    i2 = -4;
                }
                try {
                    Class.forName("com.google.android.gms.common.GooglePlayServicesUtil");
                } catch (Throwable e2) {
                    OneSignal.Log(LOG_LEVEL.FATAL, "The GooglePlayServicesUtil class part of Google Play services client library was not found. Include this in your project.", e2);
                    i2 = -4;
                }
            }
            try {
                Class.forName("android.support.v4.view.p");
                try {
                    Class.forName("android.support.v4.b.g");
                    Class.forName("android.support.v4.app.ai");
                    return i2;
                } catch (Throwable e22) {
                    OneSignal.Log(LOG_LEVEL.FATAL, "The included Android Support Library v4 is to old or incomplete. Please update your project's android-support-v4.jar to the latest revision.", e22);
                    return -5;
                }
            } catch (Throwable e222) {
                OneSignal.Log(LOG_LEVEL.FATAL, "Could not find the Android Support Library v4. Please make sure android-support-v4.jar has been correctly added to your project.", e222);
                return -3;
            }
        } catch (Throwable e2222) {
            OneSignal.Log(LOG_LEVEL.FATAL, "OneSignal AppId format is invalid.\nExample: 'b2f7f966-d8cc-11e4-bed1-df8f05be55ba'\n", e2222);
            return -999;
        }
    }

    int getDeviceType() {
        try {
            Class.forName("com.amazon.device.messaging.ADM");
            return 2;
        } catch (ClassNotFoundException e) {
            return 1;
        }
    }

    Integer getNetType() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) OneSignal.appContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        int type = activeNetworkInfo.getType();
        if (type == 1 || type == 9) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(1);
    }

    String getCarrierName() {
        String networkOperatorName = ((TelephonyManager) OneSignal.appContext.getSystemService("phone")).getNetworkOperatorName();
        return BuildConfig.FLAVOR.equals(networkOperatorName) ? null : networkOperatorName;
    }

    static String getManifestMeta(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(str);
        } catch (Throwable th) {
            OneSignal.Log(LOG_LEVEL.ERROR, BuildConfig.FLAVOR, th);
            return null;
        }
    }

    static String getResourceString(Context context, String str, String str2) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(str, "string", context.getPackageName());
        if (identifier != 0) {
            return resources.getString(identifier);
        }
        return str2;
    }

    static String getCorrectedLanguage() {
        String language = Locale.getDefault().getLanguage();
        if (language.equals("iw")) {
            return "he";
        }
        if (language.equals("in")) {
            return "id";
        }
        if (language.equals("ji")) {
            return "yi";
        }
        if (language.equals("zh")) {
            return language + Operation.MINUS + Locale.getDefault().getCountry();
        }
        return language;
    }
}
