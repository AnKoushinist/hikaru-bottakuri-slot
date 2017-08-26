package jp.co.mediasdk.mscore.event.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventParameterCoordinator {
    public static int a(boolean z) {
        return z ? 1 : 0;
    }

    public static JSONObject a(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                jSONObject2.put(str, b(jSONObject2.get(str)));
            }
            return jSONObject2;
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONObject a(Map<?, ?> map) {
        try {
            JSONObject jSONObject = new JSONObject();
            for (Entry entry : map.entrySet()) {
                jSONObject.put(entry.getKey().toString(), b(entry.getValue()));
            }
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONArray a(JSONArray jSONArray) {
        try {
            JSONArray jSONArray2 = new JSONArray(jSONArray.toString());
            int length = jSONArray2.length();
            for (int i = 0; i < length; i++) {
                jSONArray2.put(i, b(jSONArray2.get(i)));
            }
            return jSONArray2;
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONArray a(Collection<?> collection) {
        JSONArray jSONArray = new JSONArray();
        for (Object b : collection) {
            jSONArray.put(b(b));
        }
        return jSONArray;
    }

    private static JSONArray a(Object obj) {
        if (!obj.getClass().isArray()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            jSONArray.put(b(Array.get(obj, i)));
        }
        return jSONArray;
    }

    private static Object b(Object obj) {
        if (obj == null || obj.equals(JSONObject.NULL)) {
            return JSONObject.NULL;
        }
        if (obj instanceof JSONObject) {
            return a((JSONObject) obj);
        }
        if (obj instanceof Map) {
            return a((Map) obj);
        }
        if (obj instanceof JSONArray) {
            return a((JSONArray) obj);
        }
        if (obj instanceof Collection) {
            return a((Collection) obj);
        }
        if (obj.getClass().isArray()) {
            return a(obj);
        }
        if (obj instanceof Boolean) {
            return Integer.valueOf(a(((Boolean) obj).booleanValue()));
        }
        return !c(obj) ? obj.toString() : obj;
    }

    private static boolean c(Object obj) {
        return (obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Double);
    }
}
