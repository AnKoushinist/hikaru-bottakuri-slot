package com.b.a.a;

/* compiled from: SessionEventMetadata */
final class t {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final Boolean f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    private String m;

    public t(String str, String str2, String str3, String str4, String str5, Boolean bool, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = bool;
        this.g = str6;
        this.h = str7;
        this.i = str8;
        this.j = str9;
        this.k = str10;
        this.l = str11;
    }

    public String toString() {
        if (this.m == null) {
            this.m = "appBundleId=" + this.a + ", executionId=" + this.b + ", installationId=" + this.c + ", androidId=" + this.d + ", advertisingId=" + this.e + ", limitAdTrackingEnabled=" + this.f + ", betaDeviceToken=" + this.g + ", buildId=" + this.h + ", osVersion=" + this.i + ", deviceModel=" + this.j + ", appVersionCode=" + this.k + ", appVersionName=" + this.l;
        }
        return this.m;
    }
}
