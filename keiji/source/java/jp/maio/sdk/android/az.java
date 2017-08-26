package jp.maio.sdk.android;

import java.io.Serializable;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

final class az implements Serializable {
    public String a;
    public final be b;
    public final String c;
    public String d;
    public final int e;
    public final HashMap f = new HashMap();

    public az(JSONObject jSONObject) {
        this.a = jSONObject.toString();
        this.b = new be(jSONObject.getJSONObject("settings"));
        this.c = jSONObject.getString("default_zone_eid");
        this.d = jSONObject.getString("ad_deliver_test");
        this.e = jSONObject.getInt("creative_cache_size_to_clean_threshold_mb");
        JSONArray jSONArray = jSONObject.getJSONArray("zones");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            bo boVar = new bo(jSONArray.getJSONObject(i));
            this.f.put(boVar.b, boVar);
        }
    }
}
