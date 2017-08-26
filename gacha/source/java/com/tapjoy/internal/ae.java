package com.tapjoy.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build.VERSION;

public final class ae {
    public static String a(PackageManager packageManager, String str) {
        try {
            return packageManager.getPackageInfo(str, 0).versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static int a(Context context) {
        return b(context.getPackageManager(), context.getPackageName());
    }

    public static int b(PackageManager packageManager, String str) {
        int i = 0;
        try {
            return packageManager.getPackageInfo(str, 0).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        }
    }

    @SuppressLint({"NewApi"})
    public static long c(PackageManager packageManager, String str) {
        if (VERSION.SDK_INT >= 9) {
            try {
                long j = packageManager.getPackageInfo(str, 0).firstInstallTime;
                if (j > 0) {
                    return j;
                }
            } catch (NameNotFoundException e) {
            }
        }
        return 0;
    }

    public static String d(PackageManager packageManager, String str) {
        try {
            return cr.b(packageManager.getApplicationInfo(str, 0).sourceDir);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static Signature[] e(PackageManager packageManager, String str) {
        try {
            return packageManager.getPackageInfo(str, 64).signatures;
        } catch (NameNotFoundException e) {
            return null;
        }
    }
}
