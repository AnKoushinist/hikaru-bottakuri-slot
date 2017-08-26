package jp.co.mediasdk.android;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSON {
    public static boolean a(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        return a(new String(bArr, 0, bArr.length));
    }

    public static boolean a(String str) {
        return b(str) || c(str);
    }

    public static boolean a(Object obj) {
        return obj instanceof JSONObject;
    }

    public static boolean b(Object obj) {
        return obj instanceof JSONArray;
    }

    public static boolean b(String str) {
        try {
            return new JSONArray(str) instanceof JSONArray;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean c(String str) {
        try {
            return new JSONObject(str) instanceof JSONObject;
        } catch (Exception e) {
            return false;
        }
    }
}
