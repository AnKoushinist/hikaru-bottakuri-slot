package com.applovin.impl.sdk;

import com.applovin.impl.adview.ao;
import com.applovin.impl.adview.v;
import com.applovin.impl.sdk.AppLovinAdImpl.AdTarget;
import com.applovin.impl.sdk.AppLovinAdImpl.Builder;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class da extends ca implements dl {
    private final Collection a;
    private final JSONObject b;
    private final AppLovinAdLoadListener c;
    private final y d;
    private boolean i;
    private c j = new c(AppLovinAdSize.c, AppLovinAdType.a);

    da(JSONObject jSONObject, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("RenderAd", appLovinSdkImpl);
        this.b = jSONObject;
        this.c = appLovinAdLoadListener;
        this.a = d();
        this.d = appLovinSdkImpl.o();
    }

    private float a(String str, AppLovinAdType appLovinAdType, float f) {
        return appLovinAdType.equals(AppLovinAdType.b) ? 0.5f : (appLovinAdType.equals(AppLovinAdType.a) && str != null && f == -1.0f) ? 0.5f : 0.0f;
    }

    private v a(int i) {
        return i == 1 ? v.WhiteXOnTransparentGrey : v.WhiteXOnOpaqueBlack;
    }

    private v a(String str) {
        return str != null ? v.WhiteXOnTransparentGrey : v.WhiteXOnOpaqueBlack;
    }

    private String a(String str, String str2) {
        File a = this.d.a(str2.replace("/", "_"), this.f.j(), true);
        if (a == null) {
            return null;
        }
        if (a.exists()) {
            this.g.a(this.e, "Loaded " + str2 + " from cache: file://" + a.getAbsolutePath());
            return "file://" + a.getAbsolutePath();
        }
        return this.d.a(a, new StringBuilder().append(str).append(str2).toString()) ? "file://" + a.getAbsolutePath() : null;
    }

    private void a(JSONObject jSONObject) {
        AppLovinAdSize a;
        String string = jSONObject.getString(String.HTML);
        if (jSONObject.has("size")) {
            a = AppLovinAdSize.a(jSONObject.getString("size"));
        } else {
            a = AppLovinAdSize.a;
        }
        String str = null;
        if (string == null || string.length() <= 0) {
            this.g.d(this.e, "No HTML received for requested ad");
            c();
            return;
        }
        AdTarget valueOf;
        AppLovinAdType a2;
        String string2;
        v a3;
        ao aoVar;
        String b = b(string);
        if (jSONObject.has("ad_target")) {
            valueOf = AdTarget.valueOf(jSONObject.getString("ad_target").toUpperCase(Locale.ENGLISH));
        } else {
            valueOf = AdTarget.DEFAULT;
        }
        if (jSONObject.has("ad_type")) {
            a2 = AppLovinAdType.a(jSONObject.getString("ad_type").toUpperCase(Locale.ENGLISH));
        } else {
            a2 = AppLovinAdType.a;
        }
        this.j = new c(a, a2);
        if (jSONObject.has("video")) {
            string2 = jSONObject.getString("video");
            if (string2 == null || string2.isEmpty()) {
                string2 = null;
            } else {
                try {
                    str = this.d.a(this.h, string2);
                    this.i = true;
                    string2 = str;
                } catch (Exception e) {
                    string2 = str;
                }
            }
            if (string2 == null) {
                c();
                return;
            }
            str = string2;
        }
        long j = -1;
        if (jSONObject.has("ad_id")) {
            j = jSONObject.getLong("ad_id");
        }
        JSONObject jSONObject2 = jSONObject;
        int a4 = bc.a(jSONObject2, "countdown_length", 0, this.f);
        jSONObject2 = jSONObject;
        float a5 = bc.a(jSONObject2, "close_delay", 0.0f, this.f);
        jSONObject2 = jSONObject;
        float a6 = bc.a(jSONObject2, "close_delay_graphic", a(str, a2, a5), this.f);
        if (jSONObject.has("close_style")) {
            try {
                a3 = a(jSONObject.getInt("close_style"));
            } catch (JSONException e2) {
                a3 = a(str);
            }
        } else {
            a3 = a(str);
        }
        String str2 = BuildConfig.FLAVOR;
        if (jSONObject.has("clcodes")) {
            try {
                string2 = ((JSONArray) jSONObject.get("clcodes")).getString(0);
            } catch (JSONException e3) {
                string2 = str2;
            }
        } else {
            string2 = str2;
        }
        jSONObject2 = jSONObject;
        String a7 = bc.a(jSONObject2, "video_end_url", BuildConfig.FLAVOR, this.f);
        jSONObject2 = jSONObject;
        String a8 = bc.a(jSONObject2, "click_tracking_url", BuildConfig.FLAVOR, this.f);
        jSONObject2 = jSONObject;
        boolean a9 = bc.a(jSONObject2, "dismiss_on_skip", false, this.f);
        jSONObject2 = jSONObject;
        boolean a10 = bc.a(jSONObject2, "video_clickable", false, this.f);
        String a11 = bc.a(jSONObject, TapjoyConstants.TJC_CLICK_URL, BuildConfig.FLAVOR, this.f);
        jSONObject2 = jSONObject;
        String a12 = bc.a(jSONObject2, "mute_image", BuildConfig.FLAVOR, this.f);
        str2 = BuildConfig.FLAVOR;
        try {
            str2 = this.f.o().a(this.h, a12, false);
        } catch (Exception e4) {
        }
        jSONObject2 = jSONObject;
        String a13 = bc.a(jSONObject2, "unmute_image", BuildConfig.FLAVOR, this.f);
        a12 = BuildConfig.FLAVOR;
        try {
            a12 = this.f.o().a(this.h, a13, false);
        } catch (Exception e5) {
        }
        a13 = BuildConfig.FLAVOR;
        if (jSONObject.has("video_button_properties")) {
            try {
                JSONObject jSONObject3 = jSONObject.getJSONObject("video_button_properties");
                aoVar = new ao(jSONObject3, this.f);
                try {
                    jSONObject2 = jSONObject3;
                    a13 = bc.a(jSONObject2, "video_button_html", BuildConfig.FLAVOR, this.f);
                } catch (Exception e6) {
                }
            } catch (Exception e7) {
                aoVar = null;
            }
        } else {
            aoVar = null;
        }
        a(new Builder().a(b).a(a).a(a2).b(str).a(valueOf).a(a3).a(a5).b(a6).a(a4).a(j).c(string2).d(a7).e(a8).f(str2).g(a12).a(a9).b(a10).h(a11).i(a13).a(aoVar).a());
    }

    private String b(String str) {
        return ((Boolean) this.f.a(cb.B)).booleanValue() ? c(str) : str;
    }

    private String c(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        for (String str2 : ((String) this.f.a(cb.C)).split(",")) {
            int i = 0;
            int i2 = 0;
            while (i2 < stringBuilder.length()) {
                i2 = stringBuilder.indexOf(str2, i);
                if (i2 == -1) {
                    break;
                }
                int length = stringBuilder.length();
                i = i2;
                while (!this.a.contains(Character.valueOf(stringBuilder.charAt(i))) && i < length) {
                    i++;
                }
                if (i <= i2 || i == length) {
                    this.g.a(this.e, "Unable to cache resource; ad HTML is invalid.");
                } else {
                    String a = a(str2, stringBuilder.substring(str2.length() + i2, i));
                    if (a != null) {
                        stringBuilder.replace(i2, i, a);
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    private Collection d() {
        Collection hashSet = new HashSet();
        for (char valueOf : ((String) this.f.a(cb.ah)).toCharArray()) {
            hashSet.add(Character.valueOf(valueOf));
        }
        hashSet.add(Character.valueOf('\"'));
        return hashSet;
    }

    void a(AppLovinAd appLovinAd) {
        this.f.h().a(a(), "Rendered new ad:" + appLovinAd);
        if (this.c != null) {
            this.c.adReceived(appLovinAd);
        }
    }

    void c() {
        try {
            if (this.c == null) {
                return;
            }
            if (this.c instanceof w) {
                ((w) this.c).a(this.j, -6);
            } else {
                this.c.failedToReceiveAd(-6);
            }
        } catch (Throwable th) {
            this.g.b(this.e, "Unable process a failure to receive an ad", th);
        }
    }

    public String e() {
        return "tRA";
    }

    public boolean f() {
        return this.i;
    }

    public void run() {
        this.g.a(this.e, "Rendering ad...");
        try {
            a(this.b);
        } catch (Throwable e) {
            this.g.b(this.e, "Unable to parse ad service response", e);
            c();
        } catch (Throwable e2) {
            this.g.b(this.e, "Ad response is not valid", e2);
            c();
        } catch (Throwable e22) {
            this.g.b(this.e, "Unable to render ad", e22);
            c();
        }
    }
}
