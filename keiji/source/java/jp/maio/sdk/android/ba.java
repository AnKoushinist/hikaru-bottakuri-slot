package jp.maio.sdk.android;

import java.io.Serializable;
import java.util.HashMap;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class ba implements Serializable {
    public final be a;
    public final String b;
    public String c;
    public final HashMap d = new HashMap();
    public bo e;

    public ba(bo boVar, be beVar, String str, String str2) {
        this.a = beVar;
        this.b = str;
        this.c = str2;
        this.d.put(boVar.b, boVar);
        this.e = boVar;
    }

    public String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject(this.e.h);
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject3 = new JSONObject(this.a.a);
            jSONArray.put(0, jSONObject2);
            jSONObject.put("defaultZoneEid", this.b);
            jSONObject.put("adDeliverTest", this.c);
            jSONObject.put("settings", jSONObject3);
            jSONObject.put("zones", jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            return BuildConfig.FLAVOR;
        }
    }
}
