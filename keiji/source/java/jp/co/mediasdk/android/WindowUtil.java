package jp.co.mediasdk.android;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.View;

public class WindowUtil {
    public static void a(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        if (VERSION.SDK_INT > 18) {
            decorView.setSystemUiVisibility(4102);
        } else if (VERSION.SDK_INT > 15) {
            decorView.setSystemUiVisibility(6);
        } else if (VERSION.SDK_INT > 13) {
            decorView.setSystemUiVisibility(2);
        } else {
            activity.getWindow().addFlags(1024);
        }
        activity.getWindow().addFlags(1024);
    }

    public static void b(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        if (VERSION.SDK_INT > 18) {
            decorView.setSystemUiVisibility(-4103 & decorView.getSystemUiVisibility());
        } else if (VERSION.SDK_INT > 15) {
            decorView.setSystemUiVisibility(-7 & decorView.getSystemUiVisibility());
        } else if (VERSION.SDK_INT > 13) {
            decorView.setSystemUiVisibility(-3 & decorView.getSystemUiVisibility());
        } else {
            activity.getWindow().addFlags(1024);
        }
    }
}
