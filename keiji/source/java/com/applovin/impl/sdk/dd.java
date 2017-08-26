package com.applovin.impl.sdk;

import android.util.Log;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;

class dd implements p {
    final /* synthetic */ AppLovinSdkImpl a;
    final /* synthetic */ String b;
    final /* synthetic */ dc c;

    dd(dc dcVar, AppLovinSdkImpl appLovinSdkImpl, String str) {
        this.c = dcVar;
        this.a = appLovinSdkImpl;
        this.b = str;
    }

    public void a(int i) {
        int i2 = 0;
        int i3 = (i < HttpResponseCode.OK || i >= HttpResponseCode.INTERNAL_SERVER_ERROR) ? 1 : 0;
        if (i != -103) {
            i2 = 1;
        }
        if (i3 == 0 || r0 == 0 || this.c.a <= 0) {
            this.c.a(i);
            return;
        }
        long longValue = this.c.b < 0 ? ((Long) this.a.a(cb.l)).longValue() : this.c.b;
        Log.w(this.b, "Unable to send request due to server failure (code " + i + "). " + this.c.a + " attempts left, retrying in " + (((double) longValue) / 1000.0d) + " seconds...");
        dc.b(this.c, 1);
        if (this.c.a == 0) {
            this.c.c();
        }
        this.a.m().a(this.c, cw.BACKGROUND, longValue);
    }

    public void a(JSONObject jSONObject, int i) {
        this.c.a = 0;
        this.c.a(jSONObject, i);
    }
}
