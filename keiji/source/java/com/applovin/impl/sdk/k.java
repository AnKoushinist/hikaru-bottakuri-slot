package com.applovin.impl.sdk;

import android.util.Log;
import com.applovin.sdk.AppLovinLogger;
import org.cocos2dx.lib.BuildConfig;

class k implements AppLovinLogger {
    private ce a;
    private l b;

    k() {
    }

    void a(ce ceVar) {
        this.a = ceVar;
    }

    void a(l lVar) {
        this.b = lVar;
    }

    public void a(String str, String str2) {
        if (a()) {
            Log.d("AppLovinSdk", "[" + str + "] " + str2);
        }
        if (this.b != null) {
            this.b.a("DEBUG  [" + str + "] " + str2);
        }
    }

    public void a(String str, String str2, Throwable th) {
        if (a()) {
            Log.w("AppLovinSdk", "[" + str + "] " + str2, th);
        }
        if (this.b != null) {
            this.b.a("WARN  [" + str + "] " + str2);
        }
    }

    boolean a() {
        return this.a != null ? ((Boolean) this.a.a(cb.i)).booleanValue() : false;
    }

    public void b(String str, String str2) {
        if (a()) {
            Log.i("AppLovinSdk", "[" + str + "] " + str2);
        }
        if (this.b != null) {
            this.b.a("INFO  [" + str + "] " + str2);
        }
    }

    public void b(String str, String str2, Throwable th) {
        if (a()) {
            Log.e("AppLovinSdk", "[" + str + "] " + str2, th);
        }
        if (this.b != null) {
            this.b.a("ERROR  [" + str + "] " + str2 + (th != null ? ": " + th.getMessage() : BuildConfig.FLAVOR));
        }
    }

    public void c(String str, String str2) {
        a(str, str2, null);
    }

    public void c(String str, String str2, Throwable th) {
        Log.e("AppLovinSdk", "[" + str + "] " + str2, th);
        if (this.b != null) {
            this.b.a("USER  [" + str + "] " + str2 + (th != null ? ": " + th.getMessage() : BuildConfig.FLAVOR));
        }
    }

    public void d(String str, String str2) {
        b(str, str2, null);
    }

    public void e(String str, String str2) {
        c(str, str2, null);
    }
}
