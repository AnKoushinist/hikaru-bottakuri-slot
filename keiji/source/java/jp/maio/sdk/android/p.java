package jp.maio.sdk.android;

import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import org.json.JSONObject;

class p extends HashMap {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ o b;

    p(o oVar, JSONObject jSONObject) {
        this.b = oVar;
        this.a = jSONObject;
        put("zoneEid", this.b.a.b.b);
        put(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, this.a);
        put("campaignId", Integer.valueOf(this.b.a.c.f));
        put("creativeId", Integer.valueOf(this.b.a.c.b));
        put("media", new JSONObject(this.b.a.e.a()));
    }
}