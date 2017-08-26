package com.b.a.b;

import com.tapjoy.TJAdUnitConstants.String;
import org.json.JSONObject;

/* compiled from: CheckForUpdatesResponseTransform */
class g {
    g() {
    }

    public f a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new f(jSONObject.optString(String.URL, null), jSONObject.optString("version_string", null), jSONObject.optString("display_version", null), jSONObject.optString("build_version", null), jSONObject.optString("identifier", null), jSONObject.optString("instance_identifier", null));
    }
}
