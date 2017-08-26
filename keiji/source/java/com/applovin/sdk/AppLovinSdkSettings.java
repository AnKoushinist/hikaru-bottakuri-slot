package com.applovin.sdk;

import com.applovin.impl.sdk.NativeAdImpl;

public class AppLovinSdkSettings {
    private boolean a = false;
    private long b = -1;
    private String c = AppLovinAdSize.c.c();
    private String d = (AppLovinAdType.b.a() + "," + AppLovinAdType.a.a() + "," + NativeAdImpl.b.a());
    private boolean e = false;

    public void a(long j) {
        this.b = j;
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(boolean z) {
        this.a = z;
    }

    public void b(String str) {
        this.d = str;
    }

    public boolean c() {
        return this.a;
    }

    public long d() {
        return this.b;
    }

    public String e() {
        return this.c;
    }

    public String f() {
        return this.d;
    }

    public boolean g() {
        return this.e;
    }
}
