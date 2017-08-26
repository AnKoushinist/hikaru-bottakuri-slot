package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

public class NativeAdImpl implements bd, x {
    public static final AppLovinAdSize a = new AppLovinAdSize("NATIVE");
    public static final AppLovinAdType b = new AppLovinAdType("NATIVE");
    public static final c c = new c(a, b);
    private final AppLovinSdkImpl d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private float o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private long v;

    private NativeAdImpl(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, float f, String str10, String str11, String str12, String str13, String str14, String str15, String str16, long j, AppLovinSdkImpl appLovinSdkImpl) {
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.h = str4;
        this.i = str5;
        this.j = str6;
        this.k = str7;
        this.m = str8;
        this.n = str9;
        this.o = f;
        this.p = str10;
        this.q = str11;
        this.r = str12;
        this.s = str13;
        this.t = str14;
        this.u = str15;
        this.l = str16;
        this.v = j;
        this.d = appLovinSdkImpl;
    }

    public String a() {
        return this.e;
    }

    public void a(String str) {
        this.m = str;
    }

    public String b() {
        return this.f;
    }

    public void b(String str) {
        this.n = str;
    }

    public String c() {
        return this.h;
    }

    public void c(String str) {
        this.p = str;
    }

    public String d() {
        return this.p;
    }

    public long e() {
        return this.v;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NativeAdImpl nativeAdImpl = (NativeAdImpl) obj;
        if (this.k == null ? nativeAdImpl.k != null : !this.k.equals(nativeAdImpl.k)) {
            return false;
        }
        if (this.u == null ? nativeAdImpl.u != null : !this.u.equals(nativeAdImpl.u)) {
            return false;
        }
        if (this.r == null ? nativeAdImpl.r != null : !this.r.equals(nativeAdImpl.r)) {
            return false;
        }
        if (this.l == null ? nativeAdImpl.l != null : !this.l.equals(nativeAdImpl.l)) {
            return false;
        }
        if (this.j == null ? nativeAdImpl.j != null : !this.j.equals(nativeAdImpl.j)) {
            return false;
        }
        if (this.q == null ? nativeAdImpl.q != null : !this.q.equals(nativeAdImpl.q)) {
            return false;
        }
        if (this.e == null ? nativeAdImpl.e != null : !this.e.equals(nativeAdImpl.e)) {
            return false;
        }
        if (this.f == null ? nativeAdImpl.f != null : !this.f.equals(nativeAdImpl.f)) {
            return false;
        }
        if (this.g == null ? nativeAdImpl.g != null : !this.g.equals(nativeAdImpl.g)) {
            return false;
        }
        if (this.h == null ? nativeAdImpl.h != null : !this.h.equals(nativeAdImpl.h)) {
            return false;
        }
        if (this.i == null ? nativeAdImpl.i != null : !this.i.equals(nativeAdImpl.i)) {
            return false;
        }
        if (this.t == null ? nativeAdImpl.t != null : !this.t.equals(nativeAdImpl.t)) {
            return false;
        }
        if (this.s != null) {
            if (this.s.equals(nativeAdImpl.s)) {
                return true;
            }
        } else if (nativeAdImpl.s == null) {
            return true;
        }
        return false;
    }

    public boolean f() {
        boolean z = (this.m == null || this.m.equals(this.e)) ? false : true;
        boolean z2 = (this.n == null || this.n.equals(this.f)) ? false : true;
        return z && z2;
    }

    public boolean g() {
        return (this.p == null || this.p.equals(this.h)) ? false : true;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.t != null ? this.t.hashCode() : 0) + (((this.s != null ? this.s.hashCode() : 0) + (((this.r != null ? this.r.hashCode() : 0) + (((this.q != null ? this.q.hashCode() : 0) + (((this.l != null ? this.l.hashCode() : 0) + (((this.k != null ? this.k.hashCode() : 0) + (((this.j != null ? this.j.hashCode() : 0) + (((this.i != null ? this.i.hashCode() : 0) + (((this.h != null ? this.h.hashCode() : 0) + (((this.g != null ? this.g.hashCode() : 0) + (((this.f != null ? this.f.hashCode() : 0) + ((this.e != null ? this.e.hashCode() : 0) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.u != null) {
            i = this.u.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "WidgetSlot{clCode='" + this.u + '\'' + ", sourceIconUrl='" + this.e + '\'' + ", sourceImageUrl='" + this.f + '\'' + ", sourceStarRatingImageUrl='" + this.g + '\'' + ", sourceVideoUrl='" + this.h + '\'' + ", title='" + this.i + '\'' + ", descriptionText='" + this.j + '\'' + ", captionText='" + this.k + '\'' + ", ctaText='" + this.l + '\'' + ", iconUrl='" + this.m + '\'' + ", imageUrl='" + this.n + '\'' + ", starRating='" + this.o + '\'' + ", videoUrl='" + this.p + '\'' + ", impressionTrackingUrl='" + this.q + '\'' + ", clickUrl='" + this.r + '\'' + ", videoStartTrackingUrl='" + this.s + '\'' + ", videoEndTrackingUrl='" + this.t + '\'' + '}';
    }
}
