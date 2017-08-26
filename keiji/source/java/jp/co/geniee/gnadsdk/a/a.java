package jp.co.geniee.gnadsdk.a;

import android.util.Log;

/* compiled from: GNAdLogger */
public class a {
    private final String a;
    private int b;

    public a(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public int a() {
        return this.b;
    }

    public int a(int i) {
        this.b = i;
        return i;
    }

    public int a(String str, String str2) {
        return a(4, str, str2);
    }

    public int b(String str, String str2) {
        return a(5, str, str2);
    }

    public int a(String str, String str2, Throwable th) {
        return a(5, this.a, str2 + Log.getStackTraceString(th));
    }

    private int a(int i, String str, String str2) {
        if (i >= this.b) {
            return Log.println(i, "GNAdSDK", "[" + str + "] " + str2);
        }
        return -1;
    }
}
