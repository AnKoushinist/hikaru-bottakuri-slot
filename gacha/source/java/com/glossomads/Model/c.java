package com.glossomads.Model;

import com.glossomads.Logger.SugarDebugLogger;
import com.glossomads.SugarUtil;
import com.glossomads.b.a;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.ArrayList;
import java.util.HashMap;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;

public class c {
    HashMap<String, a> a = new HashMap();
    private ArrayList<String> b = new ArrayList();
    private int c = 60;
    private String d = BuildConfig.FLAVOR;
    private Boolean e = Boolean.valueOf(false);
    private Integer f;

    public c(String str) {
        int i = 0;
        if (!SugarUtil.isEmptyValue(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray optJSONArray = jSONObject.optJSONArray("seatbid");
                if (optJSONArray != null && optJSONArray.length() != 0) {
                    if (SugarUtil.isEmptyValue(jSONObject.optString("id"))) {
                        throw new a();
                    } else if (SugarUtil.isEmptyValue(jSONObject.optString("bidid"))) {
                        throw new a();
                    } else if (SugarUtil.isEmptyValue(jSONObject.optString("cur"))) {
                        throw new a();
                    } else {
                        jSONObject = optJSONArray.getJSONObject(0);
                        optJSONArray = jSONObject.optJSONArray("bid");
                        JSONObject optJSONObject = jSONObject.optJSONObject("ext");
                        if (optJSONObject == null) {
                            throw new a();
                        }
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("queue");
                        if (optJSONArray2 == null) {
                            throw new a();
                        }
                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                            if (SugarUtil.isEmptyValue(optJSONArray2.getJSONObject(i2).optString("impid", null))) {
                                throw new a();
                            }
                            this.b.add(optJSONArray2.getJSONObject(i2).optString("impid"));
                        }
                        this.d = optJSONObject.optString(String.MESSAGE);
                        if (optJSONArray != null) {
                            while (i < optJSONArray.length()) {
                                String optString = optJSONArray.optJSONObject(i).optString("impid");
                                if (!SugarUtil.isEmptyValue(optString)) {
                                    try {
                                        this.a.put(optString, new a(optJSONArray.optJSONObject(i)));
                                    } catch (a e) {
                                    }
                                }
                                i++;
                            }
                        }
                        try {
                            this.c = Integer.valueOf(optJSONObject.optString("next_load")).intValue();
                            if (this.c < 1) {
                                this.e = Boolean.valueOf(true);
                            }
                        } catch (NumberFormatException e2) {
                            this.e = Boolean.valueOf(true);
                        }
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("app");
                        this.f = null;
                        if (optJSONObject2 != null) {
                            try {
                                this.f = new Integer(optJSONObject2.optString("posid"));
                            } catch (NumberFormatException e3) {
                            }
                        }
                    }
                }
            } catch (Exception e4) {
                SugarDebugLogger.printStackTrace(e4);
            }
        }
    }

    public boolean a() {
        return this.e.booleanValue();
    }

    public HashMap<String, a> b() {
        return this.a;
    }

    public ArrayList<String> c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public Integer e() {
        return this.f;
    }

    public String f() {
        return this.d;
    }
}
