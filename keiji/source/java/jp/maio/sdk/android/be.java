package jp.maio.sdk.android;

import java.io.Serializable;
import org.json.JSONObject;

final class be implements Serializable {
    public final String a;
    public final String b = "https://res-creatives.maio.jp/templates/2.html?t=2";
    public final String c = "https://res-creatives.maio.jp/templates/1.html?t=1";
    public final String d;
    public final String e;
    public final int f;
    public final int g;
    public final int h;
    public final double i;
    public final int j;
    public final int k;
    public final int l;

    public be(JSONObject jSONObject) {
        this.a = jSONObject.toString();
        this.f = jSONObject.optInt("send_view_log_timer_interval_seconds", 20);
        this.d = jSONObject.getString("log_view_url");
        this.e = jSONObject.getString("log_click_url");
        this.g = jSONObject.optInt("sent_view_log_lifetime", 1440);
        this.h = jSONObject.optInt("min_load_ad_interval_seconds", 600);
        this.i = jSONObject.optDouble("video_update_time_interval_seconds", 0.2d);
        this.j = jSONObject.optInt("network_timeout_seconds", 10);
        this.k = jSONObject.optInt("download_retry_count", 10);
        this.l = jSONObject.optInt("download_retry_interval_seconds", 20);
    }
}
