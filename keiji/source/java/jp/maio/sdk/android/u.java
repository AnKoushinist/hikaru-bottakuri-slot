package jp.maio.sdk.android;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.Calendar;
import jp.co.geniee.gnsrewardadapter.GNSAdapterVungleRewardVideoAd;
import org.json.JSONArray;
import org.json.JSONObject;

final class u implements Serializable {
    public final int a;
    public final double b;
    public final Calendar c;
    public final int d;
    public final int e;
    public final x[] f;
    public final String g;
    public final String h;
    public String i;
    public int j;
    public final String k;

    public u(JSONObject jSONObject) {
        this.a = jSONObject.getInt("campaign_id");
        this.b = jSONObject.getDouble("daily_budget_remaining");
        String optString = jSONObject.optString("deliver_end_time");
        Calendar a = (optString == null || TextUtils.isEmpty(optString) || optString.equals("null")) ? null : ak.a(optString);
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
        this.f = new x[jSONArray.length()];
        for (int i = 0; i < this.f.length; i++) {
            this.f[i] = new x(jSONArray.getJSONObject(i), this.a);
        }
        this.g = jSONObject.optString("url_scheme");
        this.h = jSONObject.optString("application_id");
        this.k = jSONObject.optString(GNSAdapterVungleRewardVideoAd.APP_ID_COLUMN_NAME);
    }

    private boolean e() {
        return this.c == null || Calendar.getInstance().compareTo(this.c) < 0;
    }

    private boolean f() {
        if (this.d == 0) {
            return true;
        }
        Calendar instance = Calendar.getInstance();
        instance.add(12, -1440);
        return av.a(this.i, this.j).b(instance.getTime(), String.valueOf(this.a)) < this.d;
    }

    private boolean g() {
        if (this.e == 0) {
            return true;
        }
        Calendar instance = Calendar.getInstance();
        instance.add(12, -this.e);
        return av.a(this.i, this.j).a(instance.getTime(), String.valueOf(this.a));
    }

    public boolean a() {
        return e() && f() && g();
    }

    public boolean b() {
        x c = c();
        return c != null && c.a();
    }

    public x c() {
        if (!a()) {
            return null;
        }
        x[] d = d();
        return d.length != 0 ? d[0] : null;
    }

    public x[] d() {
        return this.f;
    }
}
