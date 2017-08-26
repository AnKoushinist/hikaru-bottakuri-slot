package com.vungle.publisher;

import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: vungle */
public final class se {
    public static Boolean a(JSONObject jSONObject, String str) {
        boolean optBoolean = jSONObject.optBoolean(str, false);
        if (optBoolean) {
            return Boolean.valueOf(optBoolean);
        }
        optBoolean = jSONObject.optBoolean(str, true);
        if (!optBoolean) {
            return Boolean.valueOf(optBoolean);
        }
        return null;
    }

    public static Float b(JSONObject jSONObject, String str) {
        Double valueOf;
        double optDouble = jSONObject.optDouble(str);
        if (optDouble == Double.NaN) {
            optDouble = jSONObject.optDouble(str, -1.0d);
            valueOf = optDouble != -1.0d ? Double.valueOf(optDouble) : null;
        } else {
            valueOf = Double.valueOf(optDouble);
        }
        if (valueOf == null) {
            return null;
        }
        return Float.valueOf(valueOf.floatValue());
    }

    public static Integer c(JSONObject jSONObject, String str) {
        int optInt = jSONObject.optInt(str, -1);
        if (optInt != -1) {
            return Integer.valueOf(optInt);
        }
        optInt = jSONObject.optInt(str, -2);
        if (optInt != -2) {
            return Integer.valueOf(optInt);
        }
        return null;
    }

    public static Long d(JSONObject jSONObject, String str) {
        long optLong = jSONObject.optLong(str, -1);
        if (optLong != -1) {
            return Long.valueOf(optLong);
        }
        optLong = jSONObject.optLong(str, -2);
        if (optLong != -2) {
            return Long.valueOf(optLong);
        }
        return null;
    }

    public static String e(JSONObject jSONObject, String str) {
        return jSONObject.isNull(str) ? null : jSONObject.optString(str, null);
    }

    public static List<String> f(JSONObject jSONObject, String str) {
        Object[] objArr;
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            objArr = new String[length];
            for (int i = 0; i < length; i++) {
                objArr[i] = optJSONArray.optString(i, null);
            }
        } else {
            objArr = null;
        }
        if (objArr != null) {
            return Arrays.asList(objArr);
        }
        return null;
    }

    public static <T extends abe> JSONArray a(T... tArr) {
        JSONArray jSONArray = null;
        if (tArr != null) {
            jSONArray = new JSONArray();
            for (abe a : tArr) {
                jSONArray.put(a(a));
            }
        }
        return jSONArray;
    }

    public static <T> JSONArray a(List<T> list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (T obj : list) {
            jSONArray.put(obj.toString());
        }
        return jSONArray;
    }

    public static JSONObject a(abe com_vungle_publisher_abe) {
        if (com_vungle_publisher_abe != null) {
            return com_vungle_publisher_abe.a();
        }
        return null;
    }
}
