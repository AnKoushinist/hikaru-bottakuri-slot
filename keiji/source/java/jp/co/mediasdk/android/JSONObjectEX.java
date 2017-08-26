package jp.co.mediasdk.android;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class JSONObjectEX extends JSONObject {
    public JSONObjectEX(String str) {
        super(str);
    }

    public static boolean a(JSONObject jSONObject, String str, String str2) {
        if (jSONObject == null) {
            return false;
        }
        try {
            jSONObject.put(str, str2);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean a(JSONObject jSONObject, String str, Object obj) {
        if (jSONObject == null) {
            return false;
        }
        try {
            jSONObject.put(str, obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public JSONObjectEX(JSONObject jSONObject) {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            try {
                put(str, jSONObject.get(str));
            } catch (Exception e) {
                LoggerBase.a((Object) this, "JSONObjectEX", String.format("failed to set key '%s'.", new Object[]{str}), new Object[0]);
            }
        }
    }

    public String[] a() {
        List arrayList = new ArrayList();
        Iterator keys = keys();
        while (keys.hasNext()) {
            arrayList.add((String) keys.next());
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public boolean has(String str) {
        boolean has = super.has(str);
        if (has) {
            LoggerBase.c(String.format("[%s::%s] key '%s' is exist.", new Object[]{getClass().getSimpleName(), "has", str}));
        } else {
            LoggerBase.c(String.format("[%s::%s] key '%s' is not exist.", new Object[]{getClass().getSimpleName(), "has", str}));
        }
        return has;
    }

    public boolean a(String str) {
        try {
            return JSON.a(super.get(str));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean b(String str) {
        try {
            return JSON.b(super.get(str));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean c(String str) {
        try {
            super.getBoolean(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean d(String str) {
        try {
            super.getDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean e(String str) {
        try {
            super.getInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean f(String str) {
        try {
            return ((String) super.get(str)) instanceof String;
        } catch (Exception e) {
            return false;
        }
    }

    public double getDouble(String str) {
        try {
            return super.getDouble(str);
        } catch (Exception e) {
            LoggerBase.a((Object) this, "getDouble", String.format("failed to get val from key '%s'.", new Object[]{str}), new Object[0]);
            return 0.0d;
        }
    }

    public boolean getBoolean(String str) {
        boolean z = false;
        try {
            z = super.getBoolean(str);
        } catch (Exception e) {
            LoggerBase.a((Object) this, "getBoolean", String.format("failed to get val from key '%s'.", new Object[]{str}), new Object[z]);
        }
        return z;
    }

    public int getInt(String str) {
        int i = 0;
        try {
            i = super.getInt(str);
        } catch (Exception e) {
            LoggerBase.a((Object) this, "getInt", String.format("failed to get val from key '%s'.", new Object[]{str}), new Object[i]);
        }
        return i;
    }

    public String getString(String str) {
        try {
            return (String) super.get(str);
        } catch (Exception e) {
            LoggerBase.a((Object) this, "getString", String.format("failed to get val from key '%s'.", new Object[]{str}), new Object[0]);
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
