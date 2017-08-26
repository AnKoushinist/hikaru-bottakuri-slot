package jp.co.geniee.gnadsdk.rewardvideo;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import jp.co.geniee.gnsrewardadapter.GNSAdapterAdColonyRewardVideoAd;
import jp.co.geniee.gnsrewardadapter.GNSAdapterAppLovinRewardVideoAd;
import jp.co.geniee.gnsrewardadapter.GNSAdapterCARewardRewardVideoAd;
import jp.co.geniee.gnsrewardadapter.GNSAdapterMaioRewardVideoAd;
import jp.co.geniee.gnsrewardadapter.GNSAdapterTapjoyRewardVideoAd;
import jp.co.geniee.gnsrewardadapter.GNSAdapterUnityAdsRewardVideoAd;
import jp.co.geniee.gnsrewardadapter.GNSAdapterVungleRewardVideoAd;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;

class GNSZoneInfoSource {
    public static String a = "external_link_id";
    public static String b = "external_link_media_id";
    public static String c = GNSAdapterMaioRewardVideoAd.TAG;
    public static String d = GNSAdapterUnityAdsRewardVideoAd.TAG;
    public static String e = GNSAdapterAppLovinRewardVideoAd.TAG;
    public static String f = GNSAdapterAdColonyRewardVideoAd.TAG;
    public static String g = GNSAdapterCARewardRewardVideoAd.TAG;
    public static String h = GNSAdapterTapjoyRewardVideoAd.TAG;
    public static String i = GNSAdapterVungleRewardVideoAd.TAG;
    public String j;
    public String k;
    public String l;
    public ArrayList<String> m;

    public GNSZoneInfoSource() {
        b();
    }

    private void b() {
        this.j = BuildConfig.FLAVOR;
        this.m = new ArrayList();
        this.l = BuildConfig.FLAVOR;
        this.k = BuildConfig.FLAVOR;
    }

    public Bundle a() {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(this.l)) {
            bundle.putString("asid", this.j);
            try {
                JSONObject jSONObject = new JSONObject(this.l);
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    bundle.putString(str, jSONObject.getString(str));
                }
                if (this.k.equals(c)) {
                    bundle.putString(GNSAdapterMaioRewardVideoAd.MEDIA_ID_COLUMN_NAME, jSONObject.getString(a));
                } else if (this.k.equals(d)) {
                    bundle.putString(GNSAdapterUnityAdsRewardVideoAd.GAME_ID_COLUMN_NAME, jSONObject.getString(a));
                    bundle.putString(GNSAdapterUnityAdsRewardVideoAd.PLACEMENT_ID_COLUMN_NAME, jSONObject.getString(b));
                } else if (this.k.equals(e)) {
                    bundle.putString(GNSAdapterAppLovinRewardVideoAd.PACKAGE_NAME_COLUMN_NAME, jSONObject.getString(a));
                } else if (this.k.equals(f)) {
                    bundle.putString(GNSAdapterVungleRewardVideoAd.APP_ID_COLUMN_NAME, jSONObject.getString(a));
                    bundle.putString(GNSAdapterAdColonyRewardVideoAd.ZONE_ID_COLUMN_NAME, jSONObject.getString(b));
                } else if (this.k.equals(h)) {
                    bundle.putString(GNSAdapterUnityAdsRewardVideoAd.PLACEMENT_ID_COLUMN_NAME, jSONObject.getString(a));
                    bundle.putString(GNSAdapterTapjoyRewardVideoAd.SDK_KEY_COLUMN_NAME, jSONObject.getString(b));
                } else if (this.k.equals(g)) {
                    bundle.putString(GNSAdapterCARewardRewardVideoAd.MEDIA_ID_COLUMN_NAME, jSONObject.getString(a));
                    bundle.putString(GNSAdapterTapjoyRewardVideoAd.SDK_KEY_COLUMN_NAME, jSONObject.getString(b));
                } else if (this.k.equals(i)) {
                    bundle.putString(GNSAdapterVungleRewardVideoAd.APP_ID_COLUMN_NAME, jSONObject.getString(a));
                }
            } catch (Exception e) {
            }
        }
        return bundle;
    }

    public static void a(Context context, String str, GNSZoneInfo gNSZoneInfo, String str2, GNSLogger gNSLogger, boolean z) {
        try {
            if (!TextUtils.isEmpty(str2)) {
                JSONArray jSONArray = new JSONArray(str2);
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    Object string = jSONArray.getString(i);
                    if (!TextUtils.isEmpty(string)) {
                        JSONObject jSONObject = new JSONObject(string);
                        Iterator keys = jSONObject.keys();
                        GNSZoneInfoSource gNSZoneInfoSource = new GNSZoneInfoSource();
                        while (keys.hasNext()) {
                            String str3 = (String) keys.next();
                            if ("class".equals(str3)) {
                                gNSZoneInfoSource.k = jSONObject.getString(str3);
                                gNSLogger.d("ZoneInfoSource", "adnw=" + gNSZoneInfoSource.k);
                                if (gNSZoneInfoSource.k.indexOf(e) > -1) {
                                    gNSZoneInfoSource.k = e;
                                } else if (gNSZoneInfoSource.k.indexOf(c) > -1) {
                                    gNSZoneInfoSource.k = c;
                                } else if (gNSZoneInfoSource.k.indexOf(d) > -1) {
                                    gNSZoneInfoSource.k = d;
                                } else if (gNSZoneInfoSource.k.indexOf(f) > -1) {
                                    gNSZoneInfoSource.k = f;
                                } else if (gNSZoneInfoSource.k.indexOf(h) > -1) {
                                    gNSZoneInfoSource.k = h;
                                } else if (gNSZoneInfoSource.k.indexOf(g) > -1) {
                                    gNSZoneInfoSource.k = g;
                                } else if (gNSZoneInfoSource.k.indexOf(i) > -1) {
                                    gNSZoneInfoSource.k = i;
                                }
                            } else if ("imps".equals(str3)) {
                                JSONArray jSONArray2 = new JSONArray(jSONObject.getString(str3));
                                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                                    gNSLogger.d("ZoneInfoSource", "imps[" + i2 + "]=" + jSONArray2.getString(i2));
                                    gNSZoneInfoSource.m.add(jSONArray2.getString(i2));
                                }
                            } else if ("parameter".equals(str3)) {
                                str3 = jSONObject.getString(str3);
                                if (str3 == null) {
                                    str3 = BuildConfig.FLAVOR;
                                }
                                gNSZoneInfoSource.l = str3;
                            }
                        }
                        gNSZoneInfo.a.add(gNSZoneInfoSource);
                    }
                }
                if (!z) {
                    gNSLogger.d("ZoneInfoSource", "----------------------------------------------------");
                }
            }
        } catch (Exception e) {
            gNSLogger.f("ZoneInfoSource", "JSONException");
            gNSLogger.a("ZoneInfoSource", e);
        }
    }
}
