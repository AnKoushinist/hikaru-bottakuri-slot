package jp.gmotech.smaad.video.ad.b;

import android.util.Log;

public class a {
    private static boolean a = false;

    public static void a(String str, String str2) {
        if (a) {
            Log.d(str, str2);
        }
    }

    public static void b(String str, String str2) {
        if (a) {
            Log.i(str, str2);
        }
    }

    public static void c(String str, String str2) {
        if (a) {
            Log.e(str, str2);
        }
    }
}
