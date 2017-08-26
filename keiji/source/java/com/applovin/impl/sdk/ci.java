package com.applovin.impl.sdk;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.applovin.sdk.AppLovinSdkUtils;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.tapjoy.TapjoyConstants;
import java.util.Locale;
import java.util.Map;
import jp.co.geniee.gnsrewardadapter.GNSAdapterAppLovinRewardVideoAd;
import org.json.JSONObject;

class ci extends ca {
    ci(AppLovinSdkImpl appLovinSdkImpl) {
        super("SubmitData", appLovinSdkImpl);
    }

    void a(JSONObject jSONObject) {
        try {
            JSONObject a = q.a(jSONObject);
            ce i = this.f.i();
            i.a(cb.c, a.getString("device_id"));
            i.a(cb.e, a.getString("device_token"));
            i.a(cb.d, a.getString("publisher_id"));
            i.b();
            q.a(a, this.f);
            if (a.has("adserver_parameters")) {
                i.a(cb.s, a.getJSONObject("adserver_parameters").toString());
            }
        } catch (Throwable e) {
            this.g.b(this.e, "Unable to parse API response", e);
        }
    }

    void b(JSONObject jSONObject) {
        r v = this.f.v();
        t b = v.b();
        u a = v.a();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("model", a.a);
        jSONObject2.put("os", a.b);
        jSONObject2.put("brand", a.c);
        jSONObject2.put("sdk_version", a.e);
        jSONObject2.put("revision", a.d);
        jSONObject2.put(TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, a.f);
        jSONObject2.put("carrier", a.g);
        jSONObject2.put("orientation_lock", a.i);
        jSONObject2.put("tz_offset", a.j);
        jSONObject2.put("wvvc", a.k);
        jSONObject2.put(MoatAdEvent.EVENT_TYPE, TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        s c = v.c();
        String str = c.b;
        boolean z = c.a;
        if ((!z || ((Boolean) this.f.i().a(cb.aW)).booleanValue()) && AppLovinSdkUtils.d(str)) {
            jSONObject2.put("idfa", str);
        }
        jSONObject2.put("dnt", z);
        Locale locale = a.h;
        if (locale != null) {
            jSONObject2.put("locale", locale.toString());
        }
        jSONObject.put("device_info", jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(GNSAdapterAppLovinRewardVideoAd.PACKAGE_NAME_COLUMN_NAME, b.c);
        jSONObject3.put("app_name", b.a);
        jSONObject3.put(TapjoyConstants.TJC_APP_VERSION_NAME, b.b);
        jSONObject3.put("installed_at", b.d);
        jSONObject3.put("applovin_sdk_version", "6.3.2");
        jSONObject3.put("ic", this.f.g());
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.h);
        String string = defaultSharedPreferences.getString("com.applovin.sdk.impl.isFirstRun", null);
        if (AppLovinSdkUtils.d(string)) {
            jSONObject3.put("first_install", string);
            if (string.equalsIgnoreCase(Boolean.toString(true))) {
                defaultSharedPreferences.edit().putString("com.applovin.sdk.impl.isFirstRun", Boolean.toString(false)).apply();
            }
        }
        String str2 = (String) this.f.a(cb.z);
        if (str2 != null && str2.length() > 0) {
            jSONObject3.put("plugin_version", str2);
        }
        jSONObject.put("app_info", jSONObject3);
        if (((Boolean) this.f.a(cb.F)).booleanValue()) {
            Map a2 = ((m) this.f.f()).a();
            if (!(a2 == null || a2.isEmpty())) {
                jSONObject.put("targeting", bc.a(a2));
            }
            jSONObject.put("stats", this.f.n().b());
        }
    }

    void c(JSONObject jSONObject) {
        dc cjVar = new cj(this, "Repeat" + this.e, cb.f, this.f, jSONObject);
        cjVar.a(cb.j);
        cjVar.run();
    }

    public void run() {
        try {
            this.g.b(this.e, "Submitting user data...");
            JSONObject jSONObject = new JSONObject();
            b(jSONObject);
            c(jSONObject);
        } catch (Throwable e) {
            this.g.b(this.e, "Unable to build JSON message with collected data", e);
        }
    }
}
