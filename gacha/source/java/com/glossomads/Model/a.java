package com.glossomads.Model;

import com.glossomads.SugarUtil;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

public class a implements Serializable, Cloneable {
    private String a;
    private URL b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private long l;
    private boolean m = false;

    public /* synthetic */ Object clone() {
        return a();
    }

    public a(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject.getJSONObject("ext");
        if (jSONObject2 == null) {
            throw new com.glossomads.b.a();
        }
        this.c = jSONObject.optString("impid", null);
        String optString = jSONObject.optString("iurl", null);
        this.b = null;
        if (!SugarUtil.isEmptyValue(optString)) {
            try {
                this.b = new URL(optString);
            } catch (MalformedURLException e) {
            }
        }
        this.a = jSONObject2.optString("ad_type", null);
        this.d = jSONObject.optString("adm", null);
        this.l = jSONObject2.optLong("video_size", 0);
        this.h = jSONObject2.optString("start_video", null);
        this.g = jSONObject2.optString("finish_video", null);
        this.e = jSONObject.optString("nurl", null);
        this.f = jSONObject2.optString("dlfinish_video", null);
        this.i = jSONObject2.optString("stop_video", null);
        this.j = jSONObject2.optString("fail_video", null);
        this.k = jSONObject2.optString("resume_video", null);
        if (SugarUtil.isEmptyValue(this.c) || SugarUtil.isEmptyValue(this.d) || SugarUtil.isEmptyValue(this.c) || SugarUtil.isEmptyValue(optString) || SugarUtil.isEmptyValue(this.a) || this.l == 0 || SugarUtil.isEmptyValue(this.h) || SugarUtil.isEmptyValue(this.g)) {
            throw new com.glossomads.b.a();
        }
    }

    public a a() {
        try {
            return (a) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public URL b() {
        return this.b;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.f;
    }

    public String e() {
        return this.h;
    }

    public String f() {
        return this.g;
    }

    public String g() {
        return this.k;
    }

    public String h() {
        return this.i;
    }

    public String i() {
        return this.j;
    }

    public long j() {
        return this.l;
    }

    public String k() {
        return this.d;
    }

    public String l() {
        return this.c;
    }

    public String m() {
        return this.a;
    }

    public boolean n() {
        if ("v4vc".equals(this.a)) {
            return true;
        }
        return false;
    }

    public boolean o() {
        if ("interstitial".equals(this.a)) {
            return true;
        }
        return false;
    }

    public boolean p() {
        return this.m;
    }

    public void a(boolean z) {
        this.m = z;
    }
}
