package com.glossomads.Model;

import com.glossomads.Logger.a;
import com.glossomads.SugarUtil;
import com.glossomads.m;
import com.glossomads.s;
import com.glossomads.v;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import java.util.Iterator;
import java.util.UUID;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f {
    private String a;
    private JSONObject b;

    public f(String str) {
        this.a = str;
        b();
    }

    public String a() {
        return this.a;
    }

    public void b() {
        this.b = null;
        String uuid = UUID.randomUUID().toString();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject5 = new JSONObject();
        JSONObject jSONObject6 = new JSONObject();
        JSONObject jSONObject7 = new JSONObject();
        JSONArray jSONArray2 = new JSONArray();
        try {
            int i;
            if (m.a() == null) {
                jSONObject2.put("ifa", BuildConfig.FLAVOR);
            } else {
                jSONObject2.put("ifa", m.a());
            }
            jSONObject2.put("dnt", m.e());
            jSONObject2.put("os", m.g());
            jSONObject2.put("connectiontype", m.h());
            jSONObject2.put("language", m.s());
            jSONObject2.put("osv", m.r());
            jSONObject2.put("make", m.k());
            jSONObject2.put("model", m.j());
            jSONObject2.put("w", m.q().x);
            jSONObject2.put("h", m.q().y);
            jSONObject2.put("ua", m.p());
            jSONObject2.put("devicetype", String.valueOf(m.B()));
            jSONObject2.put(Constants.PREFKEY_IP, m.t());
            jSONObject2.put(Constants.PREFKEY_CARR, m.D());
            jSONObject7.put("country", m.u());
            jSONObject2.put("geo", jSONObject7);
            jSONObject3.put("ver", m.v());
            jSONObject3.put("id", this.a);
            jSONObject3.put(MediationMetaData.KEY_NAME, m.A());
            jSONObject3.put("cat", m.z());
            jSONObject3.put(String.BUNDLE, m.i());
            jSONObject6.put("ver_sugar_sdk", m.w());
            jSONObject6.put("ver_adfully_sdk", m.x());
            jSONObject6.put("app_key", s.a().n());
            jSONObject3.put("ext", jSONObject6);
            Object g = v.g(this.a);
            if (!SugarUtil.isEmptyCollection(g)) {
                Iterator it = g.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    JSONObject jSONObject8 = new JSONObject();
                    jSONObject8.put("impid", str);
                    jSONArray.put(jSONObject8);
                }
                jSONObject5.put("except", jSONArray);
            }
            jSONObject4.put("id", m.y());
            jSONObject6 = new JSONObject();
            jSONObject6.put("id", uuid + "_1");
            jSONArray2.put(jSONObject6);
            jSONObject.put("id", uuid);
            jSONObject.put(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, jSONObject2);
            jSONObject.put("app", jSONObject3);
            jSONObject.put("ext", jSONObject5);
            jSONObject.put("user", jSONObject4);
            jSONObject.put("imp", jSONArray2);
            uuid = "test";
            if (m.F()) {
                i = 1;
            } else {
                i = 0;
            }
            jSONObject.put(uuid, i);
            this.b = jSONObject;
        } catch (JSONException e) {
            a.g(a.a.loadAdFailed, this.a, e.getMessage());
        }
    }

    public JSONObject c() {
        return this.b;
    }
}
