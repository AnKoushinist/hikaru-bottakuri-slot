package jp.maio.sdk.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.tapjoy.TapjoyConstants;
import java.util.HashSet;
import java.util.Locale;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.TwitterResponse;

class aa {
    private static String a = TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE;
    private static String b = BuildConfig.FLAVOR;
    private static String c = BuildConfig.FLAVOR;
    private static String d = BuildConfig.FLAVOR;
    private static float e = 0.0f;
    private static int f = 0;
    private static int g = 0;
    private static int h = 0;
    private static LocationManager i = null;
    private static double j = 0.0d;
    private static double k = 0.0d;
    private static HashSet l;
    private static final LocationListener m = new ac();

    private aa() {
    }

    private static int a(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        }
    }

    public static void a() {
        b = v.a().getPackageName();
        c = Locale.getDefault().getLanguage();
        h = a(v.a());
        new Thread(new ab()).start();
        e = r().density;
        Point q = q();
        f = q.x;
        g = q.y;
        bc.a("SDK API Message", BuildConfig.FLAVOR, "Sdk api init complete.", null);
    }

    public static boolean a(String str) {
        return v.a().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(new StringBuilder().append(str).append("://").toString())), 65536).size() > 0;
    }

    public static int b() {
        return h;
    }

    public static boolean b(String str) {
        if (l == null) {
            synchronized (aa.class) {
                if (l == null) {
                    l = new HashSet();
                    for (ApplicationInfo applicationInfo : v.a().getPackageManager().getInstalledApplications(0)) {
                        l.add(applicationInfo.packageName);
                    }
                }
            }
        }
        return l.contains(str);
    }

    public static String c() {
        return a;
    }

    public static String d() {
        return b;
    }

    public static String e() {
        return c;
    }

    public static String f() {
        return d;
    }

    public static String g() {
        return VERSION.RELEASE;
    }

    public static String h() {
        return Build.BRAND;
    }

    public static String i() {
        return Build.DEVICE;
    }

    public static float j() {
        return e;
    }

    public static int k() {
        return f;
    }

    public static int l() {
        return g;
    }

    public static String m() {
        Context a = v.a();
        if (a.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == -1) {
            return TapjoyConstants.TJC_CONNECTION_TYPE_MOBILE;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) a.getSystemService("connectivity");
        if (connectivityManager == null) {
            return TapjoyConstants.TJC_CONNECTION_TYPE_MOBILE;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) ? BuildConfig.FLAVOR : activeNetworkInfo.getTypeName().toLowerCase(Locale.getDefault());
    }

    private static Point q() {
        Point point = new Point(0, 0);
        DisplayMetrics r = r();
        if (s().equals("l")) {
            point.x = r.heightPixels;
            point.y = r.widthPixels;
        } else {
            point.x = r.widthPixels;
            point.y = r.heightPixels;
        }
        return point;
    }

    private static DisplayMetrics r() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) v.a().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    private static String s() {
        String str = BuildConfig.FLAVOR;
        switch (v.a().getResources().getConfiguration().orientation) {
            case TwitterResponse.READ /*1*/:
                return "p";
            case TwitterResponse.READ_WRITE /*2*/:
                return "l";
            default:
                return str;
        }
    }
}
