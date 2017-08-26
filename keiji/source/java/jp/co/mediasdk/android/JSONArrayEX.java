package jp.co.mediasdk.android;

import org.json.JSONArray;

public class JSONArrayEX extends JSONArray {
    public JSONArrayEX(String str) {
        super(str);
    }

    public static boolean a(JSONArray jSONArray, String str) {
        if (jSONArray == null) {
            return false;
        }
        try {
            jSONArray.put(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean a(JSONArray jSONArray, Object obj) {
        if (jSONArray == null) {
            return false;
        }
        try {
            jSONArray.put(obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public JSONArrayEX(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                put(i, jSONArray.get(i));
            } catch (Exception e) {
                LoggerBase.a((Object) this, "JSONArrayEX", String.format("failed to set index '%d'.", new Object[]{Integer.valueOf(i)}), new Object[0]);
            }
        }
    }

    public boolean a(int i) {
        try {
            return JSON.a(super.get(i));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean b(int i) {
        try {
            return JSON.b(super.get(i));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean c(int i) {
        try {
            super.getDouble(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean d(int i) {
        try {
            super.getBoolean(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean e(int i) {
        try {
            super.getInt(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean f(int i) {
        try {
            return ((String) super.get(i)) instanceof String;
        } catch (Exception e) {
            return false;
        }
    }

    public double getDouble(int i) {
        try {
            return super.getDouble(i);
        } catch (Exception e) {
            LoggerBase.a((Object) this, "getDouble", String.format("failed to get val from index '%d'.", new Object[]{Integer.valueOf(i)}), new Object[0]);
            return 0.0d;
        }
    }

    public int getInt(int i) {
        int i2 = 0;
        try {
            i2 = super.getInt(i);
        } catch (Exception e) {
            LoggerBase.a((Object) this, "getInt", String.format("failed to get val from index '%d'.", new Object[]{Integer.valueOf(i)}), new Object[i2]);
        }
        return i2;
    }

    public boolean getBoolean(int i) {
        boolean z = false;
        try {
            z = super.getBoolean(i);
        } catch (Exception e) {
            LoggerBase.a((Object) this, "getBoolean", String.format("failed to get val from index '%d'.", new Object[]{Integer.valueOf(i)}), new Object[z]);
        }
        return z;
    }

    public String getString(int i) {
        try {
            return (String) super.get(i);
        } catch (Exception e) {
            LoggerBase.a((Object) this, "getString", String.format("failed to get val from index '%d'.", new Object[]{Integer.valueOf(i)}), new Object[0]);
            return null;
        }
    }

    public String toString(int i) {
        try {
            return super.toString(i);
        } catch (Exception e) {
            LoggerBase.a((Object) this, "toString", "failed toString.", new Object[0]);
            return null;
        }
    }
}
