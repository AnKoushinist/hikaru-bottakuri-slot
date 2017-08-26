package com.applovin.sdk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import com.applovin.impl.sdk.NativeAdImpl;
import com.applovin.impl.sdk.dm;
import java.io.File;
import org.cocos2dx.lib.BuildConfig;

public class AppLovinSdkUtils {
    public static int a(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    public static String a(Context context) {
        Bundle g = g(context);
        if (g == null) {
            return null;
        }
        String string = g.getString("applovin.sdk.key");
        return string != null ? string : BuildConfig.FLAVOR;
    }

    public static void a(Context context, Uri uri, AppLovinSdk appLovinSdk) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", uri));
        } catch (Throwable th) {
            appLovinSdk.h().b("AppLovinSdkUtils", "Unable to open \"" + uri + "\".", th);
        }
    }

    public static void a(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable != null && (drawable instanceof BitmapDrawable)) {
                ((BitmapDrawable) drawable).getBitmap().recycle();
            }
        }
    }

    public static void a(ImageView imageView, Uri uri, int i) {
        a(imageView);
        Bitmap a = dm.a(new File(uri.getPath()), i);
        if (a != null) {
            imageView.setImageBitmap(a);
        }
    }

    public static AppLovinSdkSettings b(Context context) {
        AppLovinSdkSettings appLovinSdkSettings = new AppLovinSdkSettings();
        appLovinSdkSettings.a(c(context));
        appLovinSdkSettings.a(d(context));
        appLovinSdkSettings.a(e(context));
        appLovinSdkSettings.b(f(context));
        return appLovinSdkSettings;
    }

    private static boolean c(Context context) {
        Bundle g = g(context);
        return g != null && g.getBoolean("applovin.sdk.verbose_logging", false);
    }

    private static long d(Context context) {
        Bundle g = g(context);
        return g != null ? (long) g.getInt("applovin.sdk.ad_refresh_seconds", -100) : -100;
    }

    public static boolean d(String str) {
        return str != null && str.length() > 1;
    }

    private static String e(Context context) {
        Bundle g = g(context);
        if (g != null) {
            String string = g.getString("applovin.sdk.auto_preload_ad_sizes");
            if (string != null) {
                return string;
            }
        }
        return AppLovinAdSize.c.c();
    }

    private static String f(Context context) {
        Bundle g = g(context);
        if (g != null) {
            String string = g.getString("applovin.sdk.auto_preload_ad_types");
            if (string != null) {
                return string;
            }
        }
        return AppLovinAdType.a.a() + "," + AppLovinAdType.b.a() + "," + NativeAdImpl.b.a();
    }

    private static Bundle g(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (Throwable e) {
            Log.e("AppLovinSdk", "Unable to retrieve application metadata", e);
            return null;
        }
    }
}
