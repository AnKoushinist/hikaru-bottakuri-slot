package jp.co.geniee.gnadsdk.rewardvideo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class GNSLogger {
    private static GNSLogger b = null;
    private int a = 6;

    public static synchronized GNSLogger a() {
        GNSLogger gNSLogger;
        synchronized (GNSLogger.class) {
            if (b == null) {
                b = new GNSLogger();
            }
            gNSLogger = b;
        }
        return gNSLogger;
    }

    public GNSLogger a(Activity activity) {
        Context applicationContext = activity.getApplicationContext();
        if (applicationContext != null) {
            try {
                ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
                if (!(applicationInfo.metaData == null || applicationInfo.metaData.get("jp.co.geniee.gnadsdk.rewardvideo.gnslogger.priority") == null)) {
                    String string = applicationInfo.metaData.getString("jp.co.geniee.gnadsdk.rewardvideo.gnslogger.priority");
                    if (string.equals("VERBOSE")) {
                        a(2);
                    } else if (string.equals("DEBUG")) {
                        a(3);
                    } else if (string.equals("INFO")) {
                        a(4);
                    } else if (string.equals("WARN")) {
                        a(5);
                    } else if (string.equals("ERROR")) {
                        a(6);
                    } else if (string.equals("NONE")) {
                        a(Integer.MAX_VALUE);
                    }
                    d("Logger", "jp.co.geniee.gnadsdk.rewardvideo.gnslogger.priority=" + b());
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public int b() {
        return this.a;
    }

    public int a(int i) {
        this.a = i;
        return i;
    }

    public int a(String str, String str2) {
        return a(4, str, str2);
    }

    public int b(String str, String str2) {
        return a(5, str, str2);
    }

    public int c(String str, String str2) {
        return a(6, str, str2);
    }

    public int d(String str, String str2) {
        return b(3, str, str2);
    }

    public int e(String str, String str2) {
        return b(5, str, str2);
    }

    public int f(String str, String str2) {
        return b(6, str, str2);
    }

    public int a(String str, Exception exception) {
        return b(6, str, exception.getMessage());
    }

    private int a(int i, String str, String str2) {
        if (i >= this.a) {
            return Log.println(i, "GNS [RWD]", "[" + str + "] " + str2);
        }
        return -1;
    }

    private int b(int i, String str, String str2) {
        if (3 >= this.a) {
            return Log.println(i, "GNS [RWD]", "[" + str + "] " + str2);
        }
        return -1;
    }
}
