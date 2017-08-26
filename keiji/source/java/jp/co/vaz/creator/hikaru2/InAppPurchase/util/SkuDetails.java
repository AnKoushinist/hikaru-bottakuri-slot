package jp.co.vaz.creator.hikaru2.InAppPurchase.util;

import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.tapjoy.TJAdUnitConstants.String;
import org.json.JSONObject;

public class SkuDetails {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final long e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;

    public SkuDetails(String str, String str2) {
        this.a = str;
        this.i = str2;
        JSONObject jSONObject = new JSONObject(this.i);
        this.b = jSONObject.optString("productId");
        this.c = jSONObject.optString(MoatAdEvent.EVENT_TYPE);
        this.d = jSONObject.optString("price");
        this.e = jSONObject.optLong("price_amount_micros");
        this.f = jSONObject.optString("price_currency_code");
        this.g = jSONObject.optString(String.TITLE);
        this.h = jSONObject.optString("description");
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.d;
    }

    public String toString() {
        return "SkuDetails:" + this.i;
    }
}
