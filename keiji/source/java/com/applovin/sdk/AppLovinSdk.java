package com.applovin.sdk;

import android.content.Context;
import android.util.Log;
import com.applovin.impl.sdk.AppLovinSdkImpl;

public abstract class AppLovinSdk {
    private static AppLovinSdk[] a = new AppLovinSdk[0];
    private static final Object b = new Object();

    public static AppLovinSdk b(String str, AppLovinSdkSettings appLovinSdkSettings, Context context) {
        AppLovinSdk appLovinSdk;
        synchronized (b) {
            if (a.length == 1 && a[0].a().equals(str)) {
                appLovinSdk = a[0];
            } else {
                for (AppLovinSdk appLovinSdk2 : a) {
                    if (appLovinSdk2.a().equals(str)) {
                        break;
                    }
                }
                try {
                    appLovinSdk2 = new AppLovinSdkImpl();
                    appLovinSdk2.a(str, appLovinSdkSettings, context.getApplicationContext());
                    appLovinSdk2.a(appLovinSdk2.a(context));
                    Object obj = new AppLovinSdk[(a.length + 1)];
                    System.arraycopy(a, 0, obj, 0, a.length);
                    obj[a.length] = appLovinSdk2;
                    a = obj;
                } catch (Throwable th) {
                    Log.e("AppLovinSdk", "Failed to build AppLovin SDK. Try cleaning application data and starting the application again.", th);
                    RuntimeException runtimeException = new RuntimeException("Unable to build AppLovin SDK");
                }
            }
        }
        return appLovinSdk2;
    }

    public static void b(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        AppLovinSdk c = c(context);
        if (c != null) {
            c.l();
        } else {
            Log.e("AppLovinSdk", "Unable to initialize AppLovin SDK: SDK object not created");
        }
    }

    public static AppLovinSdk c(Context context) {
        if (context != null) {
            return b(AppLovinSdkUtils.a(context), AppLovinSdkUtils.b(context), context);
        }
        throw new IllegalArgumentException("No context specified");
    }

    public abstract String a();

    public abstract boolean d();

    public abstract AppLovinAdService e();

    public abstract AppLovinLogger h();

    public abstract void l();
}
