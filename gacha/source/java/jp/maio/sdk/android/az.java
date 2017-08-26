package jp.maio.sdk.android;

import java.io.Serializable;
import org.json.JSONObject;

final class az implements Serializable {
    public final String a = "https://res-creatives.maio.jp/templates/2.html?t=2";
    public final String b = "https://res-creatives.maio.jp/templates/1.html?t=1";
    public final String c;
    public final String d;
    public final int e;
    public final int f;
    public final int g;
    public final double h;
    public final int i;
    public final int j;
    public final int k;

    public az(JSONObject jSONObject) {
        this.e = jSONObject.optInt("send_view_log_timer_interval_seconds", 20);
        this.c = jSONObject.getString("log_view_url");
        this.d = jSONObject.getString("log_click_url");
        this.f = jSONObject.optInt("sent_view_log_lifetime", 1440);
        this.g = jSONObject.optInt("min_load_ad_interval_seconds", 600);
        this.h = jSONObject.optDouble("video_update_time_interval_seconds", 0.2d);
        this.i = jSONObject.optInt("network_timeout_seconds", 10);
        this.j = jSONObject.optInt("download_retry_count", 10);
        this.k = jSONObject.optInt("download_retry_interval_seconds", 20);
    }
}
