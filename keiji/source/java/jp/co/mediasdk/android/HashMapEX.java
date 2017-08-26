package jp.co.mediasdk.android;

import org.json.JSONArray;
import org.json.JSONObject;

public class HashMapEX extends HashMapEXEmptySupport {
    public HashMapEX(JSONArray jSONArray) {
        a(jSONArray);
    }

    public HashMapEX(JSONObject jSONObject) {
        a(jSONObject);
    }

    public HashMapEX(byte[] bArr) {
        if (bArr != null) {
            g(new String(bArr, 0, bArr.length));
        }
    }

    public HashMapEX(String str) {
        g(str);
    }

    public boolean a(HashMapEX hashMapEX) {
        if (hashMapEX == null) {
            return false;
        }
        for (String str : hashMapEX.keySet()) {
            a(str, (String) hashMapEX.get(str));
        }
        return true;
    }

    public static boolean b(HashMapEX hashMapEX) {
        if (hashMapEX == null) {
            return true;
        }
        return hashMapEX.isEmpty();
    }
}
