package com.glossomads.Logger;

import android.util.Log;
import com.glossomads.l;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class SugarDebugLogger {
    private static final String TAG = "GlossomAds";

    public static void d(String str) {
        if (l.b() || l.a()) {
            Log.d(TAG, str);
        }
    }

    public static void d(String str, String str2) {
        if (l.b() || l.a()) {
            Log.d(str, str2);
        }
    }

    public static void e(String str) {
        if (l.b() || l.a()) {
            Log.e(TAG, str);
        }
    }

    public static void e(String str, String str2) {
        if (l.b() || l.a()) {
            Log.e(str, str2);
        }
    }

    public static void i(String str) {
        if (l.b() || l.a()) {
            Log.i(TAG, str);
        }
    }

    public static void i(String str, String str2) {
        if (l.b() || l.a()) {
            Log.i(str, str2);
        }
    }

    public static void v(String str) {
        if (l.b() || l.a()) {
            Log.v(TAG, str);
        }
    }

    public static void v(String str, String str2) {
        if (l.b() || l.a()) {
            Log.v(str, str2);
        }
    }

    public static void w(String str) {
        if (l.b() || l.a()) {
            Log.w(TAG, str);
        }
    }

    public static void w(String str, String str2) {
        if (l.b() || l.a()) {
            Log.w(str, str2);
        }
    }

    public static String printStackTrace(Exception exception) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        printWriter.flush();
        String stringWriter2 = stringWriter.toString();
        printWriter.close();
        return stringWriter2;
    }
}
