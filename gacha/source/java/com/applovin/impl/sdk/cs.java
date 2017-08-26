package com.applovin.impl.sdk;

import org.json.JSONObject;
import twitter4j.HttpResponseCode;

class cs extends dc {
    final /* synthetic */ cr a;

    cs(cr crVar, String str, cd cdVar, AppLovinSdkImpl appLovinSdkImpl) {
        this.a = crVar;
        super(str, cdVar, appLovinSdkImpl);
    }

    public void a(int i) {
        this.a.b(i);
    }

    protected void a(o oVar, p pVar) {
        oVar.a(this.a.c(), ((Integer) this.f.a(cb.p)).intValue(), pVar);
    }

    public void a(JSONObject jSONObject, int i) {
        if (i == HttpResponseCode.OK) {
            this.a.b(jSONObject);
        } else {
            this.a.b(i);
        }
    }
}
