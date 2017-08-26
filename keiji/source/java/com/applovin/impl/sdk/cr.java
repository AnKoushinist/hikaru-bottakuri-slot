package com.applovin.impl.sdk;

import android.graphics.Point;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdkUtils;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import java.util.Map;
import jp.co.geniee.gnsrewardadapter.GNSAdapterTapjoyRewardVideoAd;
import org.json.JSONObject;

class cr extends ca implements dl {
    private final AppLovinAdSize a;
    private final AppLovinAdType b;
    private final AppLovinAdLoadListener c;
    private boolean d = false;

    cr(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("FetchNextAd", appLovinSdkImpl);
        this.a = appLovinAdSize;
        this.b = appLovinAdType;
        this.c = appLovinAdLoadListener;
    }

    private void a(cg cgVar) {
        if (System.currentTimeMillis() - cgVar.b("ad_session_start") > ((long) ((Integer) this.f.a(cb.r)).intValue()) * 60000) {
            cgVar.b("ad_session_start", System.currentTimeMillis());
            cgVar.c("ad_imp_session");
        }
    }

    private void b(int i) {
        this.g.d(this.e, "Unable to fetch " + this.a + " ad: server returned " + i);
        try {
            a(i);
        } catch (Throwable th) {
            this.g.c(this.e, "Unable process a failure to recieve an ad", th);
        }
        q.b(i, this.f);
    }

    private void b(JSONObject jSONObject) {
        q.a(jSONObject, this.f);
        this.f.m().a(a(jSONObject), cw.MAIN);
    }

    private void d(Map map) {
        map.put("api_did", this.f.a(cb.c));
        map.put(GNSAdapterTapjoyRewardVideoAd.SDK_KEY_COLUMN_NAME, this.f.a());
        map.put("sdk_version", "6.3.2");
        map.put(TapjoyConstants.TJC_APP_VERSION_NAME, this.f.v().b().b);
        if (!"{BUILD_NUMBER}".equals("{BUILD_NUMBER}")) {
            map.put("build_tag", "{BUILD_NUMBER}");
        }
        String str = (String) this.f.a(cb.z);
        if (str != null && str.length() > 0) {
            map.put("plugin_version", str);
        }
        map.put("accept", g());
        map.put("v1", Boolean.toString(n.a("android.permission.WRITE_EXTERNAL_STORAGE", this.h)));
        map.put("v2", Boolean.toString(n.a(AppLovinInterstitialActivity.class, this.h)));
        map.put("preloading", String.valueOf(this.d));
        map.put("size", this.a.c());
        map.put("format", "json");
        map.put("ia", Long.toString(this.f.v().b().d));
    }

    private void e(Map map) {
        if (((Boolean) this.f.a(cb.F)).booleanValue()) {
            cg n = this.f.n();
            map.put("li", String.valueOf(n.b("ad_imp")));
            map.put("si", String.valueOf(n.b("ad_imp_session")));
        }
    }

    private void f(Map map) {
        if (((Boolean) this.f.a(cb.F)).booleanValue()) {
            Map a = ((m) this.f.f()).a();
            if (a != null && !a.isEmpty()) {
                map.putAll(a);
            }
        }
    }

    private String g() {
        String str = "custom_size,launch_app";
        return (n.b() && n.a(AppLovinInterstitialActivity.class, this.h)) ? str + ",video" : str;
    }

    private void g(Map map) {
        Map a = a.a(this.f);
        if (a.isEmpty()) {
            try {
                h(a);
                a.a(a, this.f);
            } catch (Throwable e) {
                this.g.b(this.e, "Unable to populate device information", e);
            }
        }
        map.putAll(a);
        map.put("network", q.a(this.f));
        j(map);
        map.put("vz", dm.a(this.f.j().getPackageName(), this.f));
    }

    private void h(Map map) {
        u a = this.f.v().a();
        map.put("brand", dm.c(a.c));
        map.put("carrier", dm.c(a.g));
        map.put(TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, dm.c(a.f));
        map.put("locale", a.h.toString());
        map.put("model", dm.c(a.a));
        map.put("os", dm.c(a.b));
        map.put(TapjoyConstants.TJC_PLATFORM, TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        map.put("revision", dm.c(a.d));
        map.put("orientation_lock", a.i);
        map.put("tz_offset", String.valueOf(a.j));
        map.put("wvvc", String.valueOf(a.k));
        i(map);
    }

    private void i(Map map) {
        Point a = n.a(this.f.j());
        map.put("dx", Integer.toString(a.x));
        map.put("dy", Integer.toString(a.y));
    }

    private void j(Map map) {
        s c = this.f.v().c();
        String str = c.b;
        boolean z = c.a;
        if ((!z || ((Boolean) this.f.i().a(cb.aW)).booleanValue()) && AppLovinSdkUtils.d(str)) {
            map.put("idfa", str);
        }
        map.put("dnt", Boolean.toString(z));
    }

    protected ca a(JSONObject jSONObject) {
        return new da(jSONObject, this.c, this.f);
    }

    protected void a(int i) {
        if (this.c == null) {
            return;
        }
        if (this.c instanceof w) {
            ((w) this.c).a(new c(this.a, this.b), i);
        } else {
            this.c.failedToReceiveAd(i);
        }
    }

    protected void a(Map map) {
        f(map);
        g(map);
        e(map);
        d(map);
        b(map);
        c(map);
    }

    public void a(boolean z) {
        this.d = z;
    }

    void b() {
        super.b();
        b(-410);
    }

    protected void b(Map map) {
        if (this.b != null) {
            map.put("require", this.b.a());
        }
    }

    String c() {
        Map hashMap = new HashMap();
        a(hashMap);
        String d = d();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(d);
        stringBuffer.append("?");
        stringBuffer.append(dm.a(hashMap));
        return stringBuffer.toString();
    }

    protected void c(Map map) {
        di a = dg.a().a("tFNA");
        if (a != null) {
            map.put("etf", Long.toString(a.b()));
            map.put("ntf", a.a());
        }
        a = dg.a().a("tRA");
        if (a != null) {
            map.put("etr", Long.toString(a.b()));
            map.put("ntr", a.a());
            map.put("fvr", a.c() ? "1" : "0");
        }
    }

    protected String d() {
        return q.b("2.0/ad", this.f);
    }

    public String e() {
        return "tFNA";
    }

    public boolean f() {
        return false;
    }

    public void run() {
        if (this.d) {
            this.g.a(this.e, "Preloading next ad...");
        } else {
            this.g.a(this.e, "Fetching next ad...");
        }
        cg n = this.f.n();
        n.a("ad_req");
        a(n);
        try {
            dc csVar = new cs(this, "RepeatFetchNextAd", cb.h, this.f);
            csVar.a(cb.k);
            csVar.run();
        } catch (Throwable th) {
            this.g.b(this.e, "Unable to fetch " + this.a + " ad", th);
            b(0);
        }
    }
}
