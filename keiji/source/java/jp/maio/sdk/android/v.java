package jp.maio.sdk.android;

import android.content.Context;

public class v {
    private static Context a;

    public static Context a() {
        return a;
    }

    public static void a(Context context) {
        if (a == null) {
            a = context;
        }
    }

    public static String b() {
        return a.getFilesDir().getAbsolutePath();
    }
}
