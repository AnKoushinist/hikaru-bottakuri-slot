package com.vungle.publisher;

import com.tapjoy.TJAdUnitConstants.String;
import org.json.JSONObject;

/* compiled from: vungle */
public final class aet {
    public final String a;
    public final String b;
    public final String c;

    public aet(String str, JSONObject jSONObject) {
        this.a = jSONObject.getString("extension");
        this.b = jSONObject.getString(String.URL);
        this.c = str;
    }
}
