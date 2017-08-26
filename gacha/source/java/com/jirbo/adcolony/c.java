package com.jirbo.adcolony;

import android.os.Build.VERSION;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.HttpResponseCode;

class c {
    static String c = "https://androidads23.adcolony.com/configure";
    String A;
    String B;
    String C;
    String D;
    String E;
    String F;
    String G;
    String H;
    String I;
    String J;
    String K;
    String L;
    String M;
    String N;
    boolean O;
    boolean P;
    d a;
    String b = "2.3.0";
    int d = HttpResponseCode.MULTIPLE_CHOICES;
    int e = 0;
    boolean f = false;
    boolean g = false;
    String h;
    g i = new g();
    g j;
    String k;
    String[] l;
    ad m;
    a n;
    double o = 0.0d;
    String p = RFLConstants.kRFLAPI_H1_Parameter_Platform_AND;
    String q = "android_native";
    String r = BuildConfig.VERSION_NAME;
    String s = "google";
    boolean t = false;
    String u;
    String v;
    c w;
    String x;
    String y = BuildConfig.FLAVOR;
    String z;

    c(d dVar) {
        this.a = dVar;
    }

    void a(String str) {
        if (str == null) {
            str = BuildConfig.FLAVOR;
        }
        for (String split : r8.split(",")) {
            String split2;
            String[] split3 = split2.split(":");
            if (split3.length == 2) {
                String str2 = split3[0];
                split2 = split3[1];
                if (str2.equals(MediationMetaData.KEY_VERSION)) {
                    this.r = split2;
                } else if (str2.equals(TapjoyConstants.TJC_STORE)) {
                    if (split2.toLowerCase().equals("google") || split2.toLowerCase().equals("amazon")) {
                        this.s = split2;
                    } else {
                        throw new AdColonyException("Origin store in client options must be set to either 'google' or 'amazon'");
                    }
                } else if (str2.equals("skippable")) {
                    this.t = false;
                }
            } else if (split3[0].equals("skippable")) {
                this.t = false;
            }
        }
    }

    void a() {
        String a;
        while (!AdColony.c && this.e < 60) {
            try {
                this.e++;
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }
        this.e = 0;
        this.a.g.a();
        this.L = a(g.a, BuildConfig.FLAVOR);
        this.O = g.b;
        this.u = a(g.a(), BuildConfig.FLAVOR);
        if (this.L.equals(BuildConfig.FLAVOR)) {
            a = a(aa.b(this.u), BuildConfig.FLAVOR);
        } else {
            a = BuildConfig.FLAVOR;
        }
        this.v = a;
        this.x = a(g.b(), BuildConfig.FLAVOR);
        if (this.z == null) {
            this.z = a(g.e(), BuildConfig.FLAVOR);
        }
        this.A = a(g.l(), BuildConfig.FLAVOR);
        this.B = a(g.m(), BuildConfig.FLAVOR);
        this.F = a(g.j(), Constants.LOCALE_EN);
        this.G = a(g.n(), BuildConfig.FLAVOR);
        this.H = a(g.o(), BuildConfig.FLAVOR);
        this.K = a(BuildConfig.FLAVOR + VERSION.SDK_INT, BuildConfig.FLAVOR);
        this.D = a(g.h(), BuildConfig.FLAVOR);
        this.E = BuildConfig.FLAVOR;
        this.I = a(BuildConfig.FLAVOR + g.c(), BuildConfig.FLAVOR);
        this.J = a(BuildConfig.FLAVOR + g.d(), BuildConfig.FLAVOR);
        boolean z = aa.d() && aa.e();
        this.P = z;
        a.af = this.H;
        a.ag = this.b;
        if (a.m) {
            this.C = "tablet";
        } else {
            this.C = "phone";
        }
        this.w = new c();
        if (aa.a("com.android.vending") || aa.a("com.android.market")) {
            this.w.a("google");
        }
        if (aa.a("com.amazon.venezia")) {
            this.w.a("amazon");
        }
        if (l.b.f) {
            l.b.a("sdk_version:").b(this.b);
            l.b.a("ad_manifest_url:").b(c);
            l.b.a("app_id:").b(this.k);
            l.b.a("zone_ids:").b(this.l);
            l.b.a("os_name:").b(this.p);
            l.b.a("sdk_type:").b(this.q);
            l.b.a("app_version:").b(this.r);
            l.b.a("origin_store:").b(this.s);
            l.b.a("skippable:").b(this.t);
            l.b.a("android_id:").b(this.u);
            l.b.a("android_id_sha1:").b(this.v);
            l.b.a("available_stores:").b(this.w);
            l.b.a("carrier_name:").b(this.x);
            l.b.a("custom_id:").b(this.y);
            l.b.a("device_id:").b(this.z);
            l.b.a("device_manufacturer:").b(this.A);
            l.b.a("device_model:").b(this.B);
            l.b.a("device_type:").b(this.C);
            l.b.a("imei:").b(this.D);
            l.b.a("imei_sha1:").b(this.E);
            l.b.a("language:").b(this.F);
            l.b.a("open_udid:").b(this.G);
            l.b.a("os_version:").b(this.H);
        }
        g gVar = new g();
        gVar.b("os_name", this.p);
        gVar.b(TapjoyConstants.TJC_DEVICE_OS_VERSION_NAME, this.H);
        gVar.b("device_api", this.K);
        gVar.b(TapjoyConstants.TJC_APP_VERSION_NAME, this.r);
        gVar.b("android_id_sha1", this.v);
        gVar.b("device_id", this.z);
        gVar.b("open_udid", this.G);
        gVar.b(TapjoyConstants.TJC_DEVICE_TYPE_NAME, this.C);
        gVar.b("ln", this.F);
        gVar.b("device_brand", this.A);
        gVar.b("device_model", this.B);
        gVar.b("screen_width", g.f());
        gVar.b("screen_height", g.g());
        gVar.b(TapjoyConstants.TJC_SDK_TYPE, this.q);
        gVar.b("sdk_version", this.b);
        gVar.b("origin_store", this.s);
        gVar.a("available_stores", this.w);
        gVar.b("imei_sha1", this.E);
        gVar.b("memory_class", this.I);
        gVar.b("memory_used_mb", this.J);
        gVar.b("advertiser_id", this.L);
        gVar.b("limit_tracking", this.O);
        gVar.b("immersion", this.P);
        this.j = gVar;
        this.a.f.a();
        this.a.c.a();
        this.a.d.a();
        this.a.b.a();
        this.a.e.a();
        this.g = true;
        a.l.b.i.n = new ag();
        this.a.b.h();
        if (this.a.b.i.i == null || this.a.b.i.i.equals(BuildConfig.FLAVOR)) {
            this.a.b.i.i = "all";
        }
        if (this.a.b.i.j == null || this.a.b.i.j.equals(BuildConfig.FLAVOR)) {
            this.a.b.i.j = "all";
        }
    }

    String a(String str, String str2) {
        return str != null ? str : str2;
    }

    void b(String str) {
        a(str, null);
    }

    void a(String str, a aVar) {
        this.m = this.a.b.i.n.a(str);
        if (this.m != null) {
            if (aVar == null) {
                this.n = this.m.k();
            } else {
                this.n = aVar;
            }
            if (this.n != null) {
                o oVar = this.a.c;
                ac acVar = this.n.z;
                this.i.b("video_enabled", acVar.a);
                this.i.b("video_filepath", acVar.b());
                this.i.b("video_width", acVar.b);
                this.i.b("video_height", acVar.c);
                this.i.b("video_duration", acVar.k);
                this.i.b("engagement_delay", acVar.m.e);
                this.i.b("skip_delay", acVar.l.e);
                this.i.b("browser_close_image_normal", oVar.b(this.n.v.k.f));
                this.i.b("browser_close_image_down", oVar.b(this.n.v.k.h));
                this.i.b("browser_reload_image_normal", oVar.b(this.n.v.m.f));
                this.i.b("browser_reload_image_down", oVar.b(this.n.v.m.h));
                this.i.b("browser_back_image_normal", oVar.b(this.n.v.j.f));
                this.i.b("browser_back_image_down", oVar.b(this.n.v.j.h));
                this.i.b("browser_forward_image_normal", oVar.b(this.n.v.l.f));
                this.i.b("browser_forward_image_down", oVar.b(this.n.v.l.h));
                this.i.b("browser_stop_image_normal", oVar.b(this.n.v.i.f));
                this.i.b("browser_stop_image_down", oVar.b(this.n.v.i.h));
                this.i.b("browser_glow_button", oVar.b(this.n.v.a));
                this.i.b("browser_icon", oVar.b(this.n.v.h.d));
                this.i.b("mute", oVar.b(this.n.A.j.d));
                this.i.b("unmute", oVar.b(this.n.A.k.d));
                this.i.b("poster_image", oVar.b(this.n.A.g.a));
                this.i.b("thumb_image", oVar.b(this.n.A.f.a));
                this.i.b("advertiser_name", this.n.A.c);
                this.i.b("description", this.n.A.d);
                this.i.b(String.TITLE, this.n.A.e);
                this.i.b("click_to_install", this.n.A.g.c.equals("install"));
                this.i.b("store_url", this.n.A.g.d);
                this.i.b("native_engagement_enabled", this.n.A.h.a);
                this.i.b("native_engagement_type", this.n.A.h.c);
                this.i.b("native_engagement_command", this.n.A.h.e);
                this.i.b("native_engagement_label", this.n.A.h.d);
                this.i.b("skip_video_image_normal", oVar.b(acVar.l.f));
                this.i.b("skip_video_image_down", oVar.b(acVar.l.h));
                this.i.b("engagement_image_normal", oVar.b(acVar.m.f));
                this.i.b("engagement_image_down", oVar.b(acVar.m.h));
                this.i.b("engagement_height", acVar.m.c);
                this.i.b("image_overlay_enabled", acVar.n.a);
                this.i.b("image_overlay_filepath", oVar.b(acVar.n.f));
                this.i.b("haptics_enabled", acVar.o.a);
                this.i.b("haptics_filepath", oVar.b(acVar.o.c));
                this.i.b("v4iap_enabled", this.n.B.c);
                this.i.b("product_id", this.n.B.a);
                this.i.b("in_progress", this.n.B.b);
                b();
            }
        }
    }

    void c(String str) {
        this.m = this.a.b.i.n.a(str);
        this.n = this.m.k();
        o oVar = this.a.c;
        ac acVar = this.n.z;
        this.i.b("video_enabled", acVar.a);
        this.i.b("video_filepath", acVar.b());
        this.i.b("video_width", acVar.b);
        this.i.b("video_height", acVar.c);
        this.i.b("video_duration", acVar.k);
        a.q = acVar.k;
        this.i.b("engagement_delay", acVar.m.e);
        this.i.b("skip_delay", acVar.l.e);
        b();
        c cVar = this.n.w;
        this.i.b("pre_popup_bg", oVar.b(cVar.b.d.e));
        this.i.b("v4vc_logo", oVar.b(cVar.b.d.l.d));
        this.i.b("no_button_normal", oVar.b(cVar.b.d.n.f));
        this.i.b("no_button_down", oVar.b(cVar.b.d.n.h));
        this.i.b("yes_button_normal", oVar.b(cVar.b.d.m.f));
        this.i.b("yes_button_down", oVar.b(cVar.b.d.m.h));
        this.i.b("done_button_normal", oVar.b(cVar.c.d.m.f));
        this.i.b("done_button_down", oVar.b(cVar.c.d.m.h));
        this.i.b("browser_close_image_normal", oVar.b(this.n.v.k.f));
        this.i.b("browser_close_image_down", oVar.b(this.n.v.k.h));
        this.i.b("browser_reload_image_normal", oVar.b(this.n.v.m.f));
        this.i.b("browser_reload_image_down", oVar.b(this.n.v.m.h));
        this.i.b("browser_back_image_normal", oVar.b(this.n.v.j.f));
        this.i.b("browser_back_image_down", oVar.b(this.n.v.j.h));
        this.i.b("browser_forward_image_normal", oVar.b(this.n.v.l.f));
        this.i.b("browser_forward_image_down", oVar.b(this.n.v.l.h));
        this.i.b("browser_stop_image_normal", oVar.b(this.n.v.i.f));
        this.i.b("browser_stop_image_down", oVar.b(this.n.v.i.h));
        this.i.b("browser_glow_button", oVar.b(this.n.v.a));
        this.i.b("browser_icon", oVar.b(this.n.v.h.d));
        this.i.b("skip_video_image_normal", oVar.b(acVar.l.f));
        this.i.b("skip_video_image_down", oVar.b(acVar.l.h));
        this.i.b("engagement_image_normal", oVar.b(acVar.m.f));
        this.i.b("engagement_image_down", oVar.b(acVar.m.h));
        this.i.b("engagement_height", acVar.m.c);
        this.i.b("image_overlay_enabled", acVar.n.a);
        this.i.b("image_overlay_filepath", oVar.b(acVar.n.f));
        this.i.b("haptics_enabled", acVar.o.a);
        this.i.b("haptics_filepath", oVar.b(acVar.o.c));
        this.i.b("v4iap_enabled", this.n.B.c);
        this.i.b("product_id", this.n.B.a);
        this.i.b("in_progress", this.n.B.b);
    }

    void b() {
        x xVar = this.n.y.h;
        j jVar = this.n.y.i;
        o oVar = this.a.c;
        if (this.n.y.d) {
            if (jVar.a()) {
                a.Y = true;
                a.ad = jVar.g;
                a.ae = oVar.b(jVar.f.b);
                this.i.b("close_image_normal", oVar.b(jVar.j.f));
                this.i.b("close_image_down", oVar.b(jVar.j.h));
                this.i.b("reload_image_normal", oVar.b(jVar.i.f));
                this.i.b("reload_image_down", oVar.b(jVar.i.h));
            } else {
                a.Y = false;
                this.i.b("end_card_filepath", oVar.b(xVar.d));
                this.i.b("info_image_normal", oVar.b(xVar.f.f));
                this.i.b("info_image_down", oVar.b(xVar.f.h));
                this.i.b("info_url", xVar.f.j);
                this.i.b("replay_image_normal", oVar.b(xVar.h.f));
                this.i.b("replay_image_down", oVar.b(xVar.h.h));
                this.i.b("continue_image_normal", oVar.b(xVar.i.f));
                this.i.b("continue_image_down", oVar.b(xVar.i.h));
                this.i.b("download_image_normal", oVar.b(xVar.g.f));
                this.i.b("download_image_down", oVar.b(xVar.g.h));
                this.i.b("download_url", xVar.g.j);
            }
            ac acVar = this.n.z;
            this.i.b("end_card_enabled", this.n.y.d);
            this.i.b("load_timeout_enabled", this.n.y.i.c);
            this.i.b("load_timeout", this.n.y.i.b);
            this.i.b("hardware_acceleration_disabled", this.a.b.i.e);
            return;
        }
        this.i.b("end_card_enabled", this.n.y.d);
    }
}
