package jp.co.geniee.gnadsdk.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: GNSTermUtil */
public class e {
    public static String a(Context context) {
        return context.getPackageName().toLowerCase();
    }

    public static String b(Context context) {
        ApplicationInfo applicationInfo;
        String a = a(context);
        String str = BuildConfig.FLAVOR;
        PackageManager packageManager = context.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(a, 0);
        } catch (NameNotFoundException e) {
            applicationInfo = null;
        }
        if (applicationInfo != null) {
            str = (String) packageManager.getApplicationLabel(applicationInfo);
        } else if (a != null && a.length() > 0) {
            str = a.substring(a.lastIndexOf(46) + 1);
        }
        String str2 = BuildConfig.FLAVOR;
        try {
            str = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            str = BuildConfig.FLAVOR;
        }
        return (str == null || str.length() <= 0) ? BuildConfig.FLAVOR : str;
    }

    public static String c(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
    }

    public static Point d(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (VERSION.SDK_INT >= 17) {
            try {
                Display.class.getMethod("getRealSize", new Class[]{Point.class}).invoke(defaultDisplay, new Object[]{point});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (VERSION.SDK_INT >= 13) {
            try {
                point.set(((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue(), ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return point;
    }
}
