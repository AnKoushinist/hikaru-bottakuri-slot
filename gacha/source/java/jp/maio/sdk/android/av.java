package jp.maio.sdk.android;

import java.io.Serializable;
import java.util.HashMap;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import org.json.JSONArray;
import org.json.JSONObject;

final class av implements Serializable {
    public String a;
    public final az b;
    public final String c;
    public String d;
    public final HashMap e = new HashMap();

    public av(JSONObject jSONObject) {
        this.a = jSONObject.toString();
        this.b = new az(jSONObject.getJSONObject(ApiAccessUtil.WEBAPI_KEY_SETTINGS));
        this.c = jSONObject.getString("default_zone_eid");
        this.d = jSONObject.getString("ad_deliver_test");
        JSONArray jSONArray = jSONObject.getJSONArray("zones");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            bj bjVar = new bj(jSONArray.getJSONObject(i));
            this.e.put(bjVar.b, bjVar);
        }
    }
}
