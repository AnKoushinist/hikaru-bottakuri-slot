package com.applovin.impl.sdk;

import android.net.Uri;
import com.applovin.impl.adview.ao;
import com.applovin.impl.adview.v;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdkUtils;
import org.cocos2dx.lib.BuildConfig;

public class AppLovinAdImpl implements bd, AppLovinAd {
    private final AppLovinAdSize a;
    private final AppLovinAdType b;
    private final long c;
    private final String d;
    private final AdTarget e;
    private final v f;
    private final String g;
    private final String h;
    private final float i;
    private final float j;
    private final int k;
    private final String l;
    private final String m;
    private final String n;
    private final String o;
    private final boolean p;
    private final boolean q;
    private final String r;
    private final String s;
    private final ao t;

    public enum AdTarget {
        DEFAULT,
        ACTIVITY_PORTRAIT,
        ACTIVITY_LANDSCAPE
    }

    public class Builder {
        private String a;
        private AppLovinAdSize b;
        private AppLovinAdType c;
        private String d;
        private AdTarget e;
        private v f;
        private float g;
        private float h;
        private int i;
        private long j;
        private String k;
        private String l;
        private String m;
        private String n;
        private String o;
        private boolean p;
        private boolean q;
        private String r;
        private String s;
        private ao t;

        public Builder a(float f) {
            this.g = f;
            return this;
        }

        public Builder a(int i) {
            this.i = i;
            return this;
        }

        public Builder a(long j) {
            this.j = j;
            return this;
        }

        public Builder a(ao aoVar) {
            this.t = aoVar;
            return this;
        }

        public Builder a(v vVar) {
            this.f = vVar;
            return this;
        }

        public Builder a(AdTarget adTarget) {
            this.e = adTarget;
            return this;
        }

        public Builder a(AppLovinAdSize appLovinAdSize) {
            this.b = appLovinAdSize;
            return this;
        }

        public Builder a(AppLovinAdType appLovinAdType) {
            this.c = appLovinAdType;
            return this;
        }

        public Builder a(String str) {
            this.a = str;
            return this;
        }

        public Builder a(boolean z) {
            this.p = z;
            return this;
        }

        public AppLovinAdImpl a() {
            return new AppLovinAdImpl(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t);
        }

        public Builder b(float f) {
            this.h = f;
            return this;
        }

        public Builder b(String str) {
            this.d = str;
            return this;
        }

        public Builder b(boolean z) {
            this.q = z;
            return this;
        }

        public Builder c(String str) {
            this.k = str;
            return this;
        }

        public Builder d(String str) {
            this.l = str;
            return this;
        }

        public Builder e(String str) {
            this.m = str;
            return this;
        }

        public Builder f(String str) {
            this.n = str;
            return this;
        }

        public Builder g(String str) {
            this.o = str;
            return this;
        }

        public Builder h(String str) {
            this.r = str;
            return this;
        }

        public Builder i(String str) {
            this.s = str;
            return this;
        }
    }

    private AppLovinAdImpl(String str, AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, String str2, AdTarget adTarget, v vVar, float f, float f2, int i, long j, String str3, String str4, String str5, String str6, String str7, boolean z, boolean z2, String str8, String str9, ao aoVar) {
        if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No size specified");
        } else if (appLovinAdType == null) {
            throw new IllegalArgumentException("No type specified");
        } else {
            this.a = appLovinAdSize;
            this.b = appLovinAdType;
            this.d = str2;
            this.c = j;
            this.h = str;
            this.e = adTarget;
            this.i = f;
            this.k = i;
            this.g = str3;
            this.f = vVar;
            this.j = f2;
            this.l = str4;
            this.m = str5;
            this.n = str6;
            this.o = str7;
            this.p = z;
            this.q = z2;
            this.r = str8;
            this.s = str9;
            this.t = aoVar;
        }
    }

    public long a() {
        return this.c;
    }

    public String a(int i, String str) {
        String l = l();
        return AppLovinSdkUtils.d(l) ? dm.a(str, Uri.parse(l.replace("{CLCODE}", h())).buildUpon().appendQueryParameter("pv", Integer.toString(i)).build().toString()) : BuildConfig.FLAVOR;
    }

    public String a(String str) {
        String str2 = this.m;
        return AppLovinSdkUtils.d(str2) ? dm.a(str, str2.replace("{CLCODE}", h())) : BuildConfig.FLAVOR;
    }

    public AppLovinAdSize b() {
        return this.a;
    }

    public AppLovinAdType c() {
        return this.b;
    }

    public AdTarget d() {
        return this.e;
    }

    public float e() {
        return this.i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r7) {
        /*
        r6 = this;
        r0 = 1;
        r1 = 0;
        if (r6 != r7) goto L_0x0006;
    L_0x0004:
        r1 = r0;
    L_0x0005:
        return r1;
    L_0x0006:
        if (r7 == 0) goto L_0x0005;
    L_0x0008:
        r2 = r6.getClass();
        r3 = r7.getClass();
        if (r2 != r3) goto L_0x0005;
    L_0x0012:
        r7 = (com.applovin.impl.sdk.AppLovinAdImpl) r7;
        r2 = r6.c;
        r4 = r7.c;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 != 0) goto L_0x0005;
    L_0x001c:
        r2 = r7.i;
        r3 = r6.i;
        r2 = java.lang.Float.compare(r2, r3);
        if (r2 != 0) goto L_0x0005;
    L_0x0026:
        r2 = r7.j;
        r3 = r6.j;
        r2 = java.lang.Float.compare(r2, r3);
        if (r2 != 0) goto L_0x0005;
    L_0x0030:
        r2 = r6.k;
        r3 = r7.k;
        if (r2 != r3) goto L_0x0005;
    L_0x0036:
        r2 = r6.p;
        r3 = r7.p;
        if (r2 != r3) goto L_0x0005;
    L_0x003c:
        r2 = r6.q;
        r3 = r7.q;
        if (r2 != r3) goto L_0x0005;
    L_0x0042:
        r2 = r6.a;
        if (r2 == 0) goto L_0x00fa;
    L_0x0046:
        r2 = r6.a;
        r3 = r7.a;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0050:
        r2 = r6.b;
        if (r2 == 0) goto L_0x0100;
    L_0x0054:
        r2 = r6.b;
        r3 = r7.b;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x005e:
        r2 = r6.d;
        if (r2 == 0) goto L_0x0106;
    L_0x0062:
        r2 = r6.d;
        r3 = r7.d;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x006c:
        r2 = r6.e;
        r3 = r7.e;
        if (r2 != r3) goto L_0x0005;
    L_0x0072:
        r2 = r6.f;
        r3 = r7.f;
        if (r2 != r3) goto L_0x0005;
    L_0x0078:
        r2 = r6.g;
        if (r2 == 0) goto L_0x010c;
    L_0x007c:
        r2 = r6.g;
        r3 = r7.g;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0086:
        r2 = r6.h;
        if (r2 == 0) goto L_0x0112;
    L_0x008a:
        r2 = r6.h;
        r3 = r7.h;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0094:
        r2 = r6.l;
        if (r2 == 0) goto L_0x0118;
    L_0x0098:
        r2 = r6.l;
        r3 = r7.l;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x00a2:
        r2 = r6.m;
        if (r2 == 0) goto L_0x011e;
    L_0x00a6:
        r2 = r6.m;
        r3 = r7.m;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x00b0:
        r2 = r6.n;
        if (r2 == 0) goto L_0x0124;
    L_0x00b4:
        r2 = r6.n;
        r3 = r7.n;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x00be:
        r2 = r6.o;
        if (r2 == 0) goto L_0x012a;
    L_0x00c2:
        r2 = r6.o;
        r3 = r7.o;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x00cc:
        r2 = r6.r;
        if (r2 == 0) goto L_0x0130;
    L_0x00d0:
        r2 = r6.r;
        r3 = r7.r;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x00da:
        r2 = r6.s;
        if (r2 == 0) goto L_0x0136;
    L_0x00de:
        r2 = r6.s;
        r3 = r7.s;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x00e8:
        r2 = r6.t;
        if (r2 == 0) goto L_0x013c;
    L_0x00ec:
        r2 = r6.t;
        r3 = r7.t;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x00f7;
    L_0x00f6:
        r0 = r1;
    L_0x00f7:
        r1 = r0;
        goto L_0x0005;
    L_0x00fa:
        r2 = r7.a;
        if (r2 == 0) goto L_0x0050;
    L_0x00fe:
        goto L_0x0005;
    L_0x0100:
        r2 = r7.b;
        if (r2 == 0) goto L_0x005e;
    L_0x0104:
        goto L_0x0005;
    L_0x0106:
        r2 = r7.d;
        if (r2 == 0) goto L_0x006c;
    L_0x010a:
        goto L_0x0005;
    L_0x010c:
        r2 = r7.g;
        if (r2 == 0) goto L_0x0086;
    L_0x0110:
        goto L_0x0005;
    L_0x0112:
        r2 = r7.h;
        if (r2 == 0) goto L_0x0094;
    L_0x0116:
        goto L_0x0005;
    L_0x0118:
        r2 = r7.l;
        if (r2 == 0) goto L_0x00a2;
    L_0x011c:
        goto L_0x0005;
    L_0x011e:
        r2 = r7.m;
        if (r2 == 0) goto L_0x00b0;
    L_0x0122:
        goto L_0x0005;
    L_0x0124:
        r2 = r7.n;
        if (r2 == 0) goto L_0x00be;
    L_0x0128:
        goto L_0x0005;
    L_0x012a:
        r2 = r7.o;
        if (r2 == 0) goto L_0x00cc;
    L_0x012e:
        goto L_0x0005;
    L_0x0130:
        r2 = r7.r;
        if (r2 == 0) goto L_0x00da;
    L_0x0134:
        goto L_0x0005;
    L_0x0136:
        r2 = r7.s;
        if (r2 == 0) goto L_0x00e8;
    L_0x013a:
        goto L_0x0005;
    L_0x013c:
        r2 = r7.t;
        if (r2 != 0) goto L_0x00f6;
    L_0x0140:
        goto L_0x00f7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.AppLovinAdImpl.equals(java.lang.Object):boolean");
    }

    public float f() {
        return this.j;
    }

    public int g() {
        return this.k;
    }

    public String h() {
        return this.g;
    }

    public int hashCode() {
        int i = 1;
        int i2 = 0;
        int hashCode = ((this.p ? 1 : 0) + (((this.o != null ? this.o.hashCode() : 0) + (((this.n != null ? this.n.hashCode() : 0) + (((this.m != null ? this.m.hashCode() : 0) + (((this.l != null ? this.l.hashCode() : 0) + (((((this.j != 0.0f ? Float.floatToIntBits(this.j) : 0) + (((this.i != 0.0f ? Float.floatToIntBits(this.i) : 0) + (((this.h != null ? this.h.hashCode() : 0) + (((this.g != null ? this.g.hashCode() : 0) + (((this.f != null ? this.f.hashCode() : 0) + (((this.e != null ? this.e.hashCode() : 0) + (((this.d != null ? this.d.hashCode() : 0) + (((((this.b != null ? this.b.hashCode() : 0) + ((this.a != null ? this.a.hashCode() : 0) * 31)) * 31) + ((int) (this.c ^ (this.c >>> 32)))) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + this.k) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!this.q) {
            i = 0;
        }
        hashCode = ((this.s != null ? this.s.hashCode() : 0) + (((this.r != null ? this.r.hashCode() : 0) + ((hashCode + i) * 31)) * 31)) * 31;
        if (this.t != null) {
            i2 = this.t.hashCode();
        }
        return hashCode + i2;
    }

    public v i() {
        return this.f;
    }

    public String j() {
        return this.h;
    }

    public String k() {
        return this.d;
    }

    public String l() {
        return this.l;
    }

    public String m() {
        return this.n;
    }

    public String n() {
        return this.o;
    }

    public boolean o() {
        return this.p;
    }

    public boolean p() {
        return this.q;
    }

    public String q() {
        return this.r;
    }

    public String r() {
        return this.s;
    }

    public ao s() {
        return this.t;
    }

    public String toString() {
        return "AppLovinAdImpl{size=" + this.a + ", type=" + this.b + ", adIdNumber=" + this.c + ", videoFilename='" + this.d + '\'' + ", target=" + this.e + ", closeStyle=" + this.f + ", clCode='" + this.g + '\'' + ", htmlSource='" + this.h + '\'' + ", videoCloseDelay=" + this.i + ", closeDelay=" + this.j + ", countdownLength=" + this.k + ", completionUrl='" + this.l + '\'' + ", supplementalClickTrackingUrl='" + this.m + '\'' + ", muteImageFilename='" + this.n + '\'' + ", unmuteImageFilename='" + this.o + '\'' + ", dismissOnSkip=" + this.p + ", videoClickableDuringPlayback=" + this.q + ", clickDestinationUrl='" + this.r + '\'' + ", videoButtonHtmlSource='" + this.s + '\'' + ", videoButtonProperties=" + this.t + '}';
    }
}
