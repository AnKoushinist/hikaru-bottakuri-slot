package com.applovin.impl.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;

class cu implements Runnable {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final Context c;

    cu(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.c = appLovinSdkImpl.j();
        this.b = appLovinSdkImpl.h();
    }

    private void c() {
        String str = (String) this.a.a(cb.D);
        if (str.length() > 0) {
            for (String a : str.split(",")) {
                AppLovinAdSize a2 = AppLovinAdSize.a(a);
                if (a2 != null) {
                    this.a.p().d(new c(a2, AppLovinAdType.a));
                }
            }
        }
        if (((Boolean) this.a.a(cb.E)).booleanValue()) {
            this.a.p().d(new c(AppLovinAdSize.c, AppLovinAdType.b));
        }
        if (((Boolean) this.a.a(cb.aE)).booleanValue()) {
            this.a.q().d(NativeAdImpl.c);
        }
    }

    boolean a() {
        if (r.a("android.permission.INTERNET", this.c)) {
            return true;
        }
        this.b.e("TaskInitializeSdk", "Unable to enable AppLovin SDK: no android.permission.INTERNET");
        return false;
    }

    void b() {
        this.a.m().a(new ci(this.a), cw.MAIN, 500);
    }

    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        this.b.a("TaskInitializeSdk", "Initializing AppLovin SDK 6.3.2...");
        try {
            if (a()) {
                cg n = this.a.n();
                n.c();
                n.c("ad_imp_session");
                a.b(this.a);
                this.a.o().e(this.c);
                this.a.o().d(this.c);
                c();
                b();
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.c);
                if (!AppLovinSdkUtils.d(defaultSharedPreferences.getString("com.applovin.sdk.impl.isFirstRun", null))) {
                    defaultSharedPreferences.edit().putString("com.applovin.sdk.impl.isFirstRun", Boolean.toString(true)).commit();
                }
                this.a.u().a();
                this.a.t().a("landing");
                this.a.b(true);
            } else {
                this.a.b(false);
            }
            this.b.a("TaskInitializeSdk", "AppLovin SDK 6.3.2 initialization " + (this.a.c() ? "succeeded" : "failed") + " in " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        } catch (Throwable th) {
            Throwable th2 = th;
            this.b.a("TaskInitializeSdk", "AppLovin SDK 6.3.2 initialization " + (this.a.c() ? "succeeded" : "failed") + " in " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        }
    }
}
