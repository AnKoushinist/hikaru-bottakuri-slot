package com.adcolony.sdk;

import jp.co.geniee.gnsrewardadapter.GNSAdapterCARewardRewardVideoAd;
import org.json.JSONException;
import org.json.JSONObject;

class o {
    private String a;
    private JSONObject b;

    o(JSONObject jSONObject) {
        try {
            this.b = jSONObject;
            this.a = jSONObject.getString("m_type");
        } catch (JSONException e) {
            bd.h.a("JSON Error in ADCMessage constructor: ").b(e.toString());
        }
    }

    o(String str, int i) {
        try {
            this.a = str;
            this.b = new JSONObject();
            this.b.put("m_target", i);
        } catch (JSONException e) {
            bd.h.a("JSON Error in ADCMessage constructor: ").b(e.toString());
        }
    }

    o(String str, int i, JSONObject jSONObject) {
        try {
            this.a = str;
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            this.b = jSONObject;
            this.b.put("m_target", i);
        } catch (JSONException e) {
            bd.h.a("JSON Error in ADCMessage constructor: ").b(e.toString());
        }
    }

    o a(JSONObject jSONObject) {
        try {
            o oVar = new o("reply", this.b.getInt("m_origin"), jSONObject);
            oVar.b.put(GNSAdapterCARewardRewardVideoAd.MEDIA_ID_COLUMN_NAME, this.b.getInt(GNSAdapterCARewardRewardVideoAd.MEDIA_ID_COLUMN_NAME));
            return oVar;
        } catch (JSONException e) {
            bd.h.a("JSON error in ADCMessage's create_reply(): ").b(e.toString());
            return new o("JSONException", 0);
        }
    }

    void a() {
        n.a(this.a, this.b);
    }

    JSONObject b() {
        return this.b;
    }

    String c() {
        return this.a;
    }
}
