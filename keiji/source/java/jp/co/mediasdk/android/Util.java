package jp.co.mediasdk.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.PowerManager.WakeLock;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.util.UUID;
import jp.co.mediasdk.android.pref.PreferenceUtilParamSupport;

public class Util {
    protected static WakeLock a = null;
    protected static long b = -1;
    protected static PointF c = null;
    protected static HashMapEX d = new HashMapEX();

    public static void a(int i) {
        a((long) i);
    }

    public static void a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String a() {
        String c = PreferenceUtilParamSupport.c();
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        c = UUID.randomUUID().toString();
        PreferenceUtilParamSupport.k(c);
        return c;
    }

    public static boolean b() {
        for (String file : new String[]{"/system/bin/su", "/system/xbin/su", "/sbin/su"}) {
            if (new File(file).exists()) {
                return true;
            }
        }
        return false;
    }

    public static int a(Context context, int i) {
        return (int) (context.getResources().getDisplayMetrics().density * ((float) i));
    }

    public static int b(int i) {
        return (int) ((((float) i) * 9.0f) / 16.0f);
    }

    @SuppressLint({"NewApi"})
    public static Point a(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point(0, 0);
        defaultDisplay.getSize(point);
        return point;
    }
}
