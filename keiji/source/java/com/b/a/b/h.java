package com.b.a.b;

import a.a.a.a.a.a.d;
import a.a.a.a.c;
import android.content.Context;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: DeviceTokenLoader */
public class h implements d<String> {
    public /* synthetic */ Object c(Context context) {
        return a(context);
    }

    public String a(Context context) {
        long nanoTime = System.nanoTime();
        String str = BuildConfig.FLAVOR;
        ZipInputStream zipInputStream = null;
        try {
            zipInputStream = b(context);
            str = a(zipInputStream);
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (Throwable e) {
                    c.h().e("Beta", "Failed to close the APK file", e);
                }
            }
        } catch (Throwable e2) {
            c.h().e("Beta", "Failed to find this app in the PackageManager", e2);
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (Throwable e22) {
                    c.h().e("Beta", "Failed to close the APK file", e22);
                }
            }
        } catch (Throwable e222) {
            c.h().e("Beta", "Failed to find the APK file", e222);
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (Throwable e2222) {
                    c.h().e("Beta", "Failed to close the APK file", e2222);
                }
            }
        } catch (Throwable e22222) {
            c.h().e("Beta", "Failed to read the APK file", e22222);
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (Throwable e222222) {
                    c.h().e("Beta", "Failed to close the APK file", e222222);
                }
            }
        } catch (Throwable th) {
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (Throwable e2222222) {
                    c.h().e("Beta", "Failed to close the APK file", e2222222);
                }
            }
        }
        c.h().a("Beta", "Beta device token load took " + (((double) (System.nanoTime() - nanoTime)) / 1000000.0d) + "ms");
        return str;
    }

    ZipInputStream b(Context context) {
        return new ZipInputStream(new FileInputStream(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir));
    }

    String a(ZipInputStream zipInputStream) {
        String name;
        do {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                return BuildConfig.FLAVOR;
            }
            name = nextEntry.getName();
        } while (!name.startsWith("assets/com.crashlytics.android.beta/dirfactor-device-token="));
        return name.substring("assets/com.crashlytics.android.beta/dirfactor-device-token=".length(), name.length() - 1);
    }
}
