package com.moat.analytics.mobile.tjy;

import android.os.Build.VERSION;
import org.cocos2dx.lib.GameControllerDelegate;
import org.json.JSONArray;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;

public class u {
    private boolean a = false;
    private boolean b = false;
    private int c = HttpResponseCode.OK;

    public u(String str) {
        a(str);
    }

    private boolean a(JSONObject jSONObject) {
        try {
            if (jSONObject.has("ob")) {
                JSONArray jSONArray = jSONObject.getJSONArray("ob");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    if (jSONArray.getInt(i) == VERSION.SDK_INT) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("sa");
            boolean equals = string.equals("8ace5ca5da6b9adb3c0f055aad4a98c2aedf4bd7");
            if ((string.equals("on") || equals) && !a(jSONObject)) {
                this.a = true;
                this.b = equals;
            }
            if (jSONObject.has("in")) {
                int i = jSONObject.getInt("in");
                if (i >= 100 && i <= GameControllerDelegate.THUMBSTICK_LEFT_X) {
                    this.c = i;
                }
            }
        } catch (Exception e) {
            this.a = false;
            this.b = false;
            this.c = HttpResponseCode.OK;
        }
    }

    public boolean a() {
        return this.b;
    }

    public boolean b() {
        return this.a;
    }

    public int c() {
        return this.c;
    }
}
