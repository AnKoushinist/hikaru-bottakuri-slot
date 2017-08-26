package jp.co.geniee.gnadsdk.rewardvideo;

import android.content.Context;
import android.text.TextUtils;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

class GNSZoneInfo {
    public ArrayList<GNSZoneInfoSource> a;
    public int b;
    public String c;
    public double d;
    private int e;
    private long f;

    public GNSZoneInfo() {
        this((GNSZoneInfo) null);
    }

    public GNSZoneInfo(GNSZoneInfo gNSZoneInfo) {
        this.b = 0;
        b();
        if (gNSZoneInfo != null) {
            a(gNSZoneInfo);
        }
    }

    private void b() {
        this.f = 0;
        this.a = new ArrayList();
        this.e = 0;
    }

    public void a(GNSZoneInfo gNSZoneInfo) {
        this.f = gNSZoneInfo.f;
        this.a = new ArrayList();
        if (gNSZoneInfo.a != null) {
            this.a.clear();
            this.a.addAll(gNSZoneInfo.a);
        }
        this.e = gNSZoneInfo.e;
    }

    public static GNSZoneInfo a(Context context, String str, String str2, boolean z) {
        GNSLogger a = GNSLogger.a();
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        GNSZoneInfo gNSZoneInfo = new GNSZoneInfo();
        try {
            JSONArray jSONArray = new JSONObject(str2).getJSONArray("zones");
            if (jSONArray.length() > 0) {
                JSONObject jSONObject = jSONArray.getJSONObject(0);
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str3 = (String) keys.next();
                    if ("zoneid".equals(str3)) {
                        gNSZoneInfo.b = jSONObject.getInt(str3);
                    } else if ("rate".equals(str3)) {
                        GNSPrefUtil.c(context, jSONObject.getInt(str3));
                    } else if ("limit".equals(str3)) {
                        GNSVideoTerm.a(jSONObject.getInt(str3));
                    } else if (MoatAdEvent.EVENT_TYPE.equals(str3)) {
                        gNSZoneInfo.c = jSONObject.getString(str3);
                    } else if ("reset".equals(str3)) {
                        GNSVideoTerm.b(jSONObject.getInt(str3));
                    } else if (TapjoyConstants.TJC_AMOUNT.equals(str3)) {
                        gNSZoneInfo.d = jSONObject.getDouble(str3);
                    } else if ("preload".equals(str3)) {
                        GNSPrefUtil.a(context, jSONObject.getBoolean(str3));
                    } else if ("timeout".equals(str3)) {
                        GNSPrefUtil.a(context, jSONObject.getInt(str3));
                    } else if (TapjoyConstants.TJC_RETRY.equals(str3)) {
                        GNSPrefUtil.b(context, jSONObject.getInt(str3));
                    } else if ("reward_adnws".equals(str3)) {
                        GNSZoneInfoSource.a(context, str, gNSZoneInfo, jSONObject.getString(str3), a, z);
                    }
                }
            }
        } catch (Exception e) {
            a.f("ZoneInfo", "JSONException");
            a.a("ZoneInfo", e);
            gNSZoneInfo = null;
        }
        return gNSZoneInfo;
    }

    public void a(long j) {
        this.f = j;
    }

    public boolean a() {
        return new Date().getTime() >= this.f;
    }
}
