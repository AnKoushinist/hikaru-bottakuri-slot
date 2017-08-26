package jp.co.mediasdk.android;

import org.json.JSONArray;
import org.json.JSONObject;

public class HashMapEXJSONSupport extends HashMapEXJSONControl {
    public String a(int i, JSONArray jSONArray) {
        return a(String.format("%d", new Object[]{Integer.valueOf(i)}), jSONArray);
    }

    public String a(int i, JSONObject jSONObject) {
        return a(String.format("%d", new Object[]{Integer.valueOf(i)}), jSONObject);
    }

    public String a(String str, JSONArray jSONArray) {
        return b(str, new HashMapEX(jSONArray));
    }

    public String a(String str, JSONObject jSONObject) {
        return b(str, new HashMapEX(jSONObject));
    }

    public boolean g(String str) {
        if (StringUtilEmptySupport.c(str)) {
            return false;
        }
        if (JSON.c(str)) {
            try {
                a(new JSONObjectEX(str));
            } catch (Exception e) {
                LoggerBase.a((Object) this, "setJSON", String.format("failed to unserialize text.", new Object[0]), new Object[0]);
                return false;
            }
        } else if (JSON.b(str)) {
            try {
                a(new JSONArrayEX(str));
            } catch (Exception e2) {
                LoggerBase.a((Object) this, "setJSON", String.format("failed to unserialize text.", new Object[0]), new Object[0]);
                return false;
            }
        } else {
            LoggerBase.a((Object) this, "set", String.format("text is not JSON.", new Object[0]), new Object[0]);
        }
        return true;
    }

    protected void a(JSONArray jSONArray) {
        a(new JSONArrayEX(jSONArray));
    }

    protected void a(JSONArrayEX jSONArrayEX) {
        for (int i = 0; i < jSONArrayEX.length(); i++) {
            if (jSONArrayEX.f(i)) {
                b(i, jSONArrayEX.getString(i));
            } else if (jSONArrayEX.c(i)) {
                if (jSONArrayEX.getDouble(i) != ((double) jSONArrayEX.getInt(i))) {
                    a(i, jSONArrayEX.getDouble(i));
                } else {
                    a(i, jSONArrayEX.getInt(i));
                }
            } else if (jSONArrayEX.e(i)) {
                a(i, jSONArrayEX.getInt(i));
            } else if (jSONArrayEX.d(i)) {
                a(i, jSONArrayEX.getBoolean(i));
            } else if (jSONArrayEX.a(i)) {
                try {
                    a(i, jSONArrayEX.getJSONObject(i));
                } catch (Exception e) {
                    LoggerBase.a((Object) this, "set", String.format("failed to set index '%d'.", new Object[]{Integer.valueOf(i)}), new Object[0]);
                }
            } else if (jSONArrayEX.b(i)) {
                try {
                    a(i, jSONArrayEX.getJSONArray(i));
                } catch (Exception e2) {
                    LoggerBase.a((Object) this, "set", String.format("failed to set index '%d'.", new Object[]{Integer.valueOf(i)}), new Object[0]);
                }
            } else if (jSONArrayEX.isNull(i)) {
                LoggerBase.d(this, "set", String.format("key '%d' is null.", new Object[]{Integer.valueOf(i)}), new Object[0]);
                b(i, (String) null);
            } else {
                LoggerBase.a((Object) this, "set", String.format("failed to set index '%d'.", new Object[]{Integer.valueOf(i)}), new Object[0]);
            }
        }
    }

    protected void a(JSONObject jSONObject) {
        a(new JSONObjectEX(jSONObject));
    }

    protected void a(JSONObjectEX jSONObjectEX) {
        for (String str : jSONObjectEX.a()) {
            if (jSONObjectEX.f(str)) {
                c(str, jSONObjectEX.getString(str));
            } else if (jSONObjectEX.d(str)) {
                if (jSONObjectEX.getDouble(str) != ((double) jSONObjectEX.getInt(str))) {
                    a(str, jSONObjectEX.getDouble(str));
                } else {
                    a(str, jSONObjectEX.getInt(str));
                }
            } else if (jSONObjectEX.e(str)) {
                a(str, jSONObjectEX.getInt(str));
            } else if (jSONObjectEX.c(str)) {
                a(str, jSONObjectEX.getBoolean(str));
            } else if (jSONObjectEX.a(str)) {
                try {
                    a(str, jSONObjectEX.getJSONObject(str));
                } catch (Exception e) {
                    LoggerBase.a((Object) this, "set", "failed to set key '%s'.", str);
                }
            } else if (jSONObjectEX.b(str)) {
                try {
                    a(str, jSONObjectEX.getJSONArray(str));
                } catch (Exception e2) {
                    LoggerBase.a((Object) this, "set", "failed to set key '%s'.", str);
                }
            } else if (jSONObjectEX.isNull(str)) {
                LoggerBase.d(this, "set", "key '%s' is null.", str);
                c(str, (String) null);
            } else {
                LoggerBase.a((Object) this, "set", "failed to set key '%s'.", str);
            }
        }
    }

    public String b() {
        JSONObject c = c();
        if (c == null) {
            return null;
        }
        return c.toString();
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        for (String str : a()) {
            if (f(str)) {
                JSONObjectEX.a(jSONObject, str, e(str).c());
            } else {
                JSONObjectEX.a(jSONObject, str, j(str));
            }
        }
        return jSONObject;
    }

    public JSONArray d() {
        JSONArray jSONArray = new JSONArray();
        for (String str : a()) {
            if (f(str)) {
                JSONArrayEX.a(jSONArray, e(str).c());
            } else {
                JSONArrayEX.a(jSONArray, j(str));
            }
        }
        return jSONArray;
    }
}
