package com.adcolony.sdk;

import java.io.IOException;
import java.util.Iterator;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class bb {
    static JSONObject a() {
        return new JSONObject();
    }

    static JSONObject a(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            bd.h.b(e.toString());
            return new JSONObject();
        }
    }

    static JSONArray b() {
        return new JSONArray();
    }

    static JSONArray b(String str) {
        try {
            return new JSONArray(str);
        } catch (JSONException e) {
            bd.h.b(e.toString());
            return new JSONArray();
        }
    }

    static JSONObject a(JSONArray jSONArray, int i) {
        try {
            return jSONArray.getJSONObject(i);
        } catch (JSONException e) {
            bd.h.b(e.toString());
            return new JSONObject();
        }
    }

    static String b(JSONArray jSONArray, int i) {
        try {
            return jSONArray.getString(i);
        } catch (JSONException e) {
            return BuildConfig.FLAVOR;
        }
    }

    static String a(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getString(str);
        } catch (JSONException e) {
            return BuildConfig.FLAVOR;
        }
    }

    static int b(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getInt(str);
        } catch (JSONException e) {
            return 0;
        }
    }

    static int a(JSONObject jSONObject, String str, int i) {
        try {
            i = jSONObject.getInt(str);
        } catch (JSONException e) {
        }
        return i;
    }

    static boolean c(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getBoolean(str);
        } catch (JSONException e) {
            return false;
        }
    }

    static double d(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getDouble(str);
        } catch (JSONException e) {
            return 0.0d;
        }
    }

    static JSONObject e(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str);
        } catch (JSONException e) {
            return new JSONObject();
        }
    }

    static JSONObject c(JSONArray jSONArray, int i) {
        try {
            return jSONArray.getJSONObject(i);
        } catch (JSONException e) {
            return new JSONObject();
        }
    }

    static JSONArray f(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONArray(str);
        } catch (JSONException e) {
            return new JSONArray();
        }
    }

    static boolean a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
            return true;
        } catch (JSONException e) {
            bd.h.a("JSON error in ADCJSON put_string(): ").b(e.toString());
            return false;
        }
    }

    static boolean b(JSONObject jSONObject, String str, int i) {
        try {
            jSONObject.put(str, i);
            return true;
        } catch (JSONException e) {
            bd.h.a("JSON error in ADCJSON put_integer(): ").b(e.toString());
            return false;
        }
    }

    static boolean a(JSONObject jSONObject, String str, long j) {
        try {
            jSONObject.put(str, j);
            return true;
        } catch (JSONException e) {
            bd.h.a("JSON error in ADCJSON put_integer(): ").b(e.toString());
            return false;
        }
    }

    static boolean a(JSONObject jSONObject, String str, boolean z) {
        try {
            jSONObject.put(str, z);
            return true;
        } catch (JSONException e) {
            bd.h.a("JSON error in ADCJSON put_boolean(): ").b(e.toString());
            return false;
        }
    }

    static boolean a(JSONObject jSONObject, String str, JSONArray jSONArray) {
        try {
            jSONObject.put(str, jSONArray);
            return true;
        } catch (JSONException e) {
            bd.h.a("JSON error in ADCJSON put_array(): ").b(e.toString());
            return false;
        }
    }

    static boolean a(JSONObject jSONObject, String str, JSONObject jSONObject2) {
        try {
            jSONObject.put(str, jSONObject2);
            return true;
        } catch (JSONException e) {
            bd.h.a("JSON error in ADCJSON put_object(): ").b(e.toString());
            return false;
        }
    }

    static boolean a(JSONObject jSONObject, String str, double d) {
        try {
            jSONObject.put(str, d);
            return true;
        } catch (JSONException e) {
            bd.h.a("JSON error in ADCJSON put_double(): ").b(e.toString());
            return false;
        }
    }

    static void a(JSONArray jSONArray, String str) {
        jSONArray.put(str);
    }

    static void a(JSONArray jSONArray, Object obj) {
        jSONArray.put(obj);
    }

    static boolean g(JSONObject jSONObject, String str) {
        try {
            n.a().f().a(str, jSONObject.toString(), false);
            return true;
        } catch (IOException e) {
            bd.h.a("IOException in ADCJSON's save_object: ").b(e.toString());
            return false;
        }
    }

    static JSONObject c(String str) {
        try {
            return a(n.a().f().a(str, false).toString());
        } catch (IOException e) {
            bd.h.a("IOException in ADCJSON's load_object: ").b(e.toString());
            return a();
        }
    }

    static String[] a(JSONArray jSONArray) {
        String[] strArr = new String[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            strArr[i] = b(jSONArray, i);
        }
        return strArr;
    }

    static JSONArray a(String[] strArr) {
        JSONArray b = b();
        for (String a : strArr) {
            a(b, a);
        }
        return b;
    }

    static boolean b(JSONArray jSONArray, String str) {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (b(jSONArray, i).equals(str)) {
                return true;
            }
        }
        return false;
    }

    static boolean h(JSONObject jSONObject, String str) {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            if (str.equals(keys.next())) {
                return true;
            }
        }
        return false;
    }

    static JSONArray a(JSONArray jSONArray, String[] strArr, boolean z) {
        int i = 0;
        while (i < strArr.length) {
            if (!z || !b(jSONArray, strArr[i])) {
                a(jSONArray, strArr[i]);
            }
            i++;
        }
        return jSONArray;
    }
}
