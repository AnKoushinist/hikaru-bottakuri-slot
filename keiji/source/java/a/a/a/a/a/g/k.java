package a.a.a.a.a.g;

import com.tapjoy.TJAdUnitConstants.String;
import org.json.JSONObject;

/* compiled from: DefaultSettingsJsonTransform */
class k implements v {
    k() {
    }

    public t a(a.a.a.a.a.b.k kVar, JSONObject jSONObject) {
        int optInt = jSONObject.optInt("settings_version", 0);
        int optInt2 = jSONObject.optInt("cache_duration", 3600);
        return new t(a(kVar, (long) optInt2, jSONObject), a(jSONObject.getJSONObject("app")), e(jSONObject.getJSONObject("session")), f(jSONObject.getJSONObject("prompt")), c(jSONObject.getJSONObject("features")), d(jSONObject.getJSONObject("analytics")), g(jSONObject.getJSONObject("beta")), optInt, optInt2);
    }

    private e a(JSONObject jSONObject) {
        String string = jSONObject.getString("identifier");
        String string2 = jSONObject.getString("status");
        String string3 = jSONObject.getString(String.URL);
        String string4 = jSONObject.getString("reports_url");
        boolean optBoolean = jSONObject.optBoolean("update_required", false);
        c cVar = null;
        if (jSONObject.has("icon") && jSONObject.getJSONObject("icon").has("hash")) {
            cVar = b(jSONObject.getJSONObject("icon"));
        }
        return new e(string, string2, string3, string4, optBoolean, cVar);
    }

    private c b(JSONObject jSONObject) {
        return new c(jSONObject.getString("hash"), jSONObject.getInt("width"), jSONObject.getInt("height"));
    }

    private m c(JSONObject jSONObject) {
        return new m(jSONObject.optBoolean("prompt_enabled", false), jSONObject.optBoolean("collect_logged_exceptions", true), jSONObject.optBoolean("collect_reports", true), jSONObject.optBoolean("collect_analytics", false));
    }

    private b d(JSONObject jSONObject) {
        return new b(jSONObject.optString(String.URL, "https://e.crashlytics.com/spi/v2/events"), jSONObject.optInt("flush_interval_secs", 600), jSONObject.optInt("max_byte_size_per_file", 8000), jSONObject.optInt("max_file_count_per_send", 1), jSONObject.optInt("max_pending_send_file_count", 100), jSONObject.optBoolean("track_custom_events", true), jSONObject.optBoolean("track_predefined_events", true), jSONObject.optInt("sampling_rate", 1), jSONObject.optBoolean("flush_on_background", true));
    }

    private p e(JSONObject jSONObject) {
        return new p(jSONObject.optInt("log_buffer_size", 64000), jSONObject.optInt("max_chained_exception_depth", 8), jSONObject.optInt("max_custom_exception_events", 64), jSONObject.optInt("max_custom_key_value_pairs", 64), jSONObject.optInt("identifier_mask", 255), jSONObject.optBoolean("send_session_without_crash", false));
    }

    private o f(JSONObject jSONObject) {
        return new o(jSONObject.optString(String.TITLE, "Send Crash Report?"), jSONObject.optString(String.MESSAGE, "Looks like we crashed! Please help us fix the problem by sending a crash report."), jSONObject.optString("send_button_title", "Send"), jSONObject.optBoolean("show_cancel_button", true), jSONObject.optString("cancel_button_title", "Don't Send"), jSONObject.optBoolean("show_always_send_button", true), jSONObject.optString("always_send_button_title", "Always Send"));
    }

    private f g(JSONObject jSONObject) {
        return new f(jSONObject.optString("update_endpoint", u.a), jSONObject.optInt("update_suspend_duration", 3600));
    }

    private long a(a.a.a.a.a.b.k kVar, long j, JSONObject jSONObject) {
        if (jSONObject.has("expires_at")) {
            return jSONObject.getLong("expires_at");
        }
        return kVar.a() + (1000 * j);
    }
}
