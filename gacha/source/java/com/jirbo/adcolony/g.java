package com.jirbo.adcolony;

import android.app.ActivityManager;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.tapjoy.TapjoyConstants;
import java.util.Locale;
import org.cocos2dx.lib.BuildConfig;

class g {
    static String a;
    static boolean b;

    g() {
    }

    static String a() {
        if (a.P == null) {
            return BuildConfig.FLAVOR;
        }
        return Secure.getString(AdColony.activity().getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
    }

    static String b() {
        if (a.P == null) {
            return BuildConfig.FLAVOR;
        }
        String networkOperatorName = ((TelephonyManager) AdColony.activity().getSystemService("phone")).getNetworkOperatorName();
        if (networkOperatorName.length() == 0) {
            return "unknown";
        }
        return networkOperatorName;
    }

    static int c() {
        if (a.P == null) {
            return 0;
        }
        Context applicationContext = a.b().getApplicationContext();
        a.b();
        return ((ActivityManager) applicationContext.getSystemService("activity")).getMemoryClass();
    }

    static long d() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / ((long) 1048576);
    }

    static String e() {
        if (a.P == null) {
            return BuildConfig.FLAVOR;
        }
        return ai.a(a.b());
    }

    static int f() {
        if (a.P == null) {
            return 0;
        }
        return a.b().getWindowManager().getDefaultDisplay().getWidth();
    }

    static int g() {
        if (a.P == null) {
            return 0;
        }
        return a.b().getWindowManager().getDefaultDisplay().getHeight();
    }

    static String h() {
        return BuildConfig.FLAVOR;
    }

    static boolean i() {
        if (a.P == null) {
            return false;
        }
        if (a.ah == null) {
            DisplayMetrics displayMetrics = AdColony.activity().getResources().getDisplayMetrics();
            float f = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
            float f2 = ((float) displayMetrics.heightPixels) / displayMetrics.ydpi;
            if (Math.sqrt((double) ((f2 * f2) + (f * f))) < 6.0d) {
                return false;
            }
            return true;
        } else if (a.ah.equals("tablet")) {
            return true;
        } else {
            return false;
        }
    }

    static String j() {
        return Locale.getDefault().getLanguage();
    }

    static String k() {
        if (a.P == null) {
            return BuildConfig.FLAVOR;
        }
        try {
            return ((WifiManager) AdColony.activity().getSystemService(TapjoyConstants.TJC_CONNECTION_TYPE_WIFI)).getConnectionInfo().getMacAddress();
        } catch (RuntimeException e) {
            return null;
        }
    }

    static String l() {
        return Build.MANUFACTURER;
    }

    static String m() {
        return Build.MODEL;
    }

    static String n() {
        return BuildConfig.FLAVOR;
    }

    static String o() {
        return VERSION.RELEASE;
    }
}
