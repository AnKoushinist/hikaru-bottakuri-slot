package com.applovin.impl.adview;

import com.applovin.impl.sdk.bc;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.tapjoy.TapjoyConnectCore;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;

public class ao {
    private final AppLovinLogger a;
    private int b;
    private int c;
    private int d;
    private int e;
    private boolean f;
    private int g;
    private int h;
    private int i;
    private float j;
    private float k;

    public ao(JSONObject jSONObject, AppLovinSdk appLovinSdk) {
        this.a = appLovinSdk.h();
        this.a.b("VideoButtonProperties", "Updating video button properties with JSON = " + jSONObject);
        this.b = bc.a(jSONObject, "width", 64, appLovinSdk);
        this.c = bc.a(jSONObject, "height", 7, appLovinSdk);
        this.d = bc.a(jSONObject, "margin", 20, appLovinSdk);
        this.e = bc.a(jSONObject, "gravity", 85, appLovinSdk);
        this.f = bc.a(jSONObject, "tap_to_fade", false, appLovinSdk);
        this.g = bc.a(jSONObject, "tap_to_fade_duration_milliseconds", (int) HttpResponseCode.INTERNAL_SERVER_ERROR, appLovinSdk);
        this.h = bc.a(jSONObject, "fade_in_duration_milliseconds", (int) HttpResponseCode.INTERNAL_SERVER_ERROR, appLovinSdk);
        this.i = bc.a(jSONObject, "fade_out_duration_milliseconds", (int) HttpResponseCode.INTERNAL_SERVER_ERROR, appLovinSdk);
        this.j = bc.a(jSONObject, "fade_in_delay_seconds", (float) TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, appLovinSdk);
        this.k = bc.a(jSONObject, "fade_out_delay_seconds", 6.0f, appLovinSdk);
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public boolean e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ao aoVar = (ao) obj;
        if (this.b != aoVar.b || this.c != aoVar.c || this.d != aoVar.d || this.e != aoVar.e || this.f != aoVar.f || this.g != aoVar.g || this.h != aoVar.h || this.i != aoVar.i || Float.compare(aoVar.j, this.j) != 0) {
            return false;
        }
        if (Float.compare(aoVar.k, this.k) != 0) {
            z = false;
        }
        return z;
    }

    public int f() {
        return this.g;
    }

    public int g() {
        return this.h;
    }

    public int h() {
        return this.i;
    }

    public int hashCode() {
        int i = 0;
        int floatToIntBits = ((this.j != 0.0f ? Float.floatToIntBits(this.j) : 0) + (((((((((this.f ? 1 : 0) + (((((((this.b * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31)) * 31) + this.g) * 31) + this.h) * 31) + this.i) * 31)) * 31;
        if (this.k != 0.0f) {
            i = Float.floatToIntBits(this.k);
        }
        return floatToIntBits + i;
    }

    public float i() {
        return this.j;
    }

    public float j() {
        return this.k;
    }

    public String toString() {
        return "VideoButtonProperties{widthPercentOfScreen=" + this.b + ", heightPercentOfScreen=" + this.c + ", margin=" + this.d + ", gravity=" + this.e + ", tapToFade=" + this.f + ", tapToFadeDurationMillis=" + this.g + ", fadeInDurationMillis=" + this.h + ", fadeOutDurationMillis=" + this.i + ", fadeInDelay=" + this.j + ", fadeOutDelay=" + this.k + '}';
    }
}
