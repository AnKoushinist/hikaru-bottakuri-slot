package jp.maio.sdk.android;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.Calendar;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;

final class s implements Serializable {
    public final int a;
    public final double b;
    public final Calendar c;
    public final int d;
    public final int e;
    public final v[] f;
    public final String g;
    public final String h;
    public String i;
    public int j;

    public s(JSONObject jSONObject) {
        this.a = jSONObject.getInt("campaign_id");
        this.b = jSONObject.getDouble("daily_budget_remaining");
        String optString = jSONObject.optString("deliver_end_time");
        Calendar a = (optString == null || TextUtils.isEmpty(optString) || optString.equals("null")) ? null : ah.a(optString);
        this.c = a;
        if (jSONObject.getString("frequency").equals("null")) {
            this.d = 0;
        } else {
            this.d = jSONObject.getInt("frequency");
        }
        if (jSONObject.getString("recency").equals("null")) {
            this.e = 0;
        } else {
            this.e = jSONObject.getInt("recency");
        }
        JSONArray jSONArray = jSONObject.getJSONArray("creatives");
        this.f = new v[jSONArray.length()];
        for (int i = 0; i < this.f.length; i++) {
            this.f[i] = new v(jSONArray.getJSONObject(i), this.a);
        }
        this.g = jSONObject.optString("url_scheme");
        this.h = jSONObject.optString("application_id");
    }

    private boolean e() {
        return this.c == null || Calendar.getInstance().compareTo(this.c) < 0;
    }

    private boolean f() {
        ax.a("Check Frequency ", String.valueOf(this.a), null);
        if (this.d == 0) {
            return true;
        }
        Calendar instance = Calendar.getInstance();
        instance.add(12, -1440);
        return ar.a(this.i, this.j).b(instance.getTime(), String.valueOf(this.a)) < this.d;
    }

    private boolean g() {
        ax.a("Check Recency ", String.valueOf(this.a), null);
        if (this.e == 0) {
            return true;
        }
        Calendar instance = Calendar.getInstance();
        instance.add(12, -this.e);
        return ar.a(this.i, this.j).a(instance.getTime(), String.valueOf(this.a));
    }

    public boolean a() {
        return (e() && f() && g()) ? (TextUtils.isEmpty(this.g) || !x.a(this.g)) ? TextUtils.isEmpty(this.h) || !x.b(this.h) : false : false;
    }

    public boolean b() {
        ax.a("Campaign#isAdReady.", BuildConfig.FLAVOR, null);
        v c = c();
        return c != null && c.a();
    }

    public v c() {
        if (!a()) {
            return null;
        }
        v[] d = d();
        return d.length != 0 ? d[0] : null;
    }

    public v[] d() {
        return this.f;
    }
}
