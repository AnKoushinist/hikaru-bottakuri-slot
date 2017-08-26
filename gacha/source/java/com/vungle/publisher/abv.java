package com.vungle.publisher;

import com.vungle.log.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: vungle */
public abstract class abv<T> extends abw<T> {
    protected abstract T c(JSONObject jSONObject);

    abv() {
    }

    public final T a(String str) {
        return str == null ? null : c(new JSONObject(str));
    }

    protected final T[] a(JSONArray jSONArray) {
        T[] tArr = null;
        if (jSONArray != null) {
            int length = jSONArray.length();
            tArr = a(length);
            for (int i = 0; i < length; i++) {
                tArr[i] = c(jSONArray.optJSONObject(i));
            }
        }
        return tArr;
    }

    protected static void a(JSONObject jSONObject, String str, Object obj) {
        if (obj == null) {
            Object opt = jSONObject.opt(str);
            if (opt == null) {
                Logger.d(Logger.PROTOCOL_TAG, "null " + str + " is required input");
            } else {
                Logger.d(Logger.PROTOCOL_TAG, "invalid " + str + ": " + opt);
            }
        } else if ((obj instanceof String) && ((String) obj).length() == 0) {
            Logger.d(Logger.PROTOCOL_TAG, "empty " + str + " is required input");
        } else if ((obj instanceof JSONArray) && ((JSONArray) obj).length() == 0) {
            Logger.d(Logger.PROTOCOL_TAG, "empty array " + str + " is required input");
        }
    }
}
