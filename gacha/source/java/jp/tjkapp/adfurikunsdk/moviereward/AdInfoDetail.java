package jp.tjkapp.adfurikunsdk.moviereward;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;

class AdInfoDetail {
    public String adnetworkKey;
    public String[] extraActionUrl;
    public boolean highPriority;
    public String html;
    public int noAdCheck;
    public String param;
    public String testMode;
    public String userAdId;
    public int wallType;
    public HashMap<String, Integer> weight;

    public AdInfoDetail() {
        a();
    }

    public AdInfoDetail(AdInfoDetail adInfoDetail) {
        a();
        if (adInfoDetail != null) {
            toCopy(adInfoDetail);
        }
    }

    private void a() {
        this.userAdId = BuildConfig.FLAVOR;
        this.weight = new HashMap();
        this.adnetworkKey = BuildConfig.FLAVOR;
        this.html = BuildConfig.FLAVOR;
        this.wallType = 0;
        this.param = BuildConfig.FLAVOR;
        this.extraActionUrl = null;
        this.noAdCheck = 0;
        this.testMode = "0";
        this.highPriority = false;
    }

    public void toCopy(AdInfoDetail adInfoDetail) {
        this.userAdId = adInfoDetail.userAdId != null ? adInfoDetail.userAdId : BuildConfig.FLAVOR;
        if (adInfoDetail.weight != null && adInfoDetail.weight.size() > 0) {
            this.weight.clear();
            this.weight.putAll(adInfoDetail.weight);
        }
        this.adnetworkKey = adInfoDetail.adnetworkKey != null ? adInfoDetail.adnetworkKey : BuildConfig.FLAVOR;
        this.html = adInfoDetail.html != null ? adInfoDetail.html : BuildConfig.FLAVOR;
        this.wallType = adInfoDetail.wallType;
        this.param = adInfoDetail.param != null ? adInfoDetail.param : BuildConfig.FLAVOR;
        this.extraActionUrl = adInfoDetail.extraActionUrl != null ? (String[]) adInfoDetail.extraActionUrl.clone() : null;
        this.noAdCheck = adInfoDetail.noAdCheck;
        this.testMode = adInfoDetail.testMode;
        this.highPriority = adInfoDetail.highPriority;
    }

    public Bundle convertParamToBundle() {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(this.param)) {
            bundle.putString(ApiAccessUtil.WEBAPI_KEY_USER_AD_ID, this.userAdId);
            try {
                JSONObject jSONObject = new JSONObject(this.param);
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    if (str.equals("all_zones")) {
                        JSONArray optJSONArray = jSONObject.optJSONArray(str);
                        String[] strArr = new String[optJSONArray.length()];
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            strArr[i] = optJSONArray.optString(i);
                        }
                        bundle.putStringArray(str, strArr);
                    } else {
                        bundle.putString(str, jSONObject.getString(str));
                    }
                }
            } catch (Exception e) {
            }
        }
        return bundle;
    }

    public static void getAdInfoDetail(Context context, String str, AdInfo adInfo, String str2, LogUtil logUtil, boolean z) {
        try {
            if (!TextUtils.isEmpty(str2)) {
                if (!z) {
                    logUtil.debug_i(Constants.TAG, "---------------------------------------------------------");
                    logUtil.debug_i(Constants.TAG, "adfurikun_appkey[" + str + "]");
                    logUtil.debug_i(Constants.TAG, "[adnetwork_key]weight");
                }
                JSONArray jSONArray = new JSONArray(str2);
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    Object string = jSONArray.getString(i);
                    if (!TextUtils.isEmpty(string)) {
                        JSONObject jSONObject = new JSONObject(string);
                        Iterator keys = jSONObject.keys();
                        AdInfoDetail adInfoDetail = new AdInfoDetail();
                        while (keys.hasNext()) {
                            String str3 = (String) keys.next();
                            if (ApiAccessUtil.WEBAPI_KEY_USER_AD_ID.equals(str3)) {
                                adInfoDetail.userAdId = jSONObject.getString(str3);
                            } else if (ApiAccessUtil.WEBAPI_KEY_WEIGHT.equals(str3)) {
                                JSONObject jSONObject2 = new JSONObject(jSONObject.getString(str3));
                                Iterator keys2 = jSONObject2.keys();
                                while (keys2.hasNext()) {
                                    str3 = (String) keys2.next();
                                    adInfoDetail.weight.put(str3, Integer.valueOf(jSONObject2.getInt(str3)));
                                }
                            } else if (ApiAccessUtil.WEBAPI_KEY_ADNETWORKKEY.equals(str3)) {
                                adInfoDetail.adnetworkKey = jSONObject.getString(str3);
                            } else if (ApiAccessUtil.WEBAPI_KEY_HTML.equals(str3)) {
                                adInfoDetail.html = new String(Base64.decode(jSONObject.getString(str3), 0));
                            } else if (ApiAccessUtil.WEBAPI_KEY_WALL_TYPE.equals(str3)) {
                                try {
                                    adInfoDetail.wallType = jSONObject.getInt(str3);
                                } catch (NumberFormatException e) {
                                    adInfoDetail.wallType = 0;
                                }
                            } else if (ApiAccessUtil.WEBAPI_KEY_PARAM.equals(str3)) {
                                str3 = jSONObject.getString(str3);
                                if (str3 == null) {
                                    str3 = BuildConfig.FLAVOR;
                                }
                                adInfoDetail.param = str3;
                            } else if (ApiAccessUtil.WEBAPI_KEY_EXT_ACT_URL.equals(str3)) {
                                string = jSONObject.getString(str3);
                                if (!TextUtils.isEmpty(string)) {
                                    String[] split = string.split(",");
                                    if (split.length > 0) {
                                        int length2 = split.length;
                                        adInfoDetail.extraActionUrl = new String[length2];
                                        for (int i2 = 0; i2 < length2; i2++) {
                                            adInfoDetail.extraActionUrl[i2] = new String(Base64.decode(split[i2], 0));
                                        }
                                    }
                                }
                            } else if (ApiAccessUtil.WEBAPI_KEY_NOADCHECK.equals(str3)) {
                                try {
                                    adInfoDetail.noAdCheck = jSONObject.getInt(str3) == 1 ? 1 : 0;
                                } catch (NumberFormatException e2) {
                                    adInfoDetail.noAdCheck = 0;
                                }
                            } else if (ApiAccessUtil.WEBAPI_KEY_TEST_FLG.equals(str3)) {
                                adInfoDetail.testMode = jSONObject.getString(str3);
                            }
                        }
                        Set keySet = adInfoDetail.weight.keySet();
                        String currentCountryCode = Util.getCurrentCountryCode();
                        if (keySet == null || keySet.contains(currentCountryCode)) {
                            adInfoDetail.html = ApiAccessUtil.replaceIDFA(context, adInfoDetail.html, logUtil);
                            if (!z) {
                                logUtil.debug_i(Constants.TAG, ("[" + adInfoDetail.adnetworkKey + "]") + adInfoDetail.weight);
                            }
                            currentCountryCode = FileUtil.getAdInfoDetailFilePath(context, str, adInfoDetail);
                            string = null;
                            if (!z) {
                                string = 1;
                            } else if (!new File(currentCountryCode).exists()) {
                                string = 1;
                            }
                            if (string != null) {
                                FileUtil.saveStringFile(currentCountryCode, adInfoDetail.html);
                            }
                            adInfo.adInfoDetailArray.add(adInfoDetail);
                        } else {
                            logUtil.debug_i(Constants.TAG, ("[" + adInfoDetail.adnetworkKey + "]") + "[ A language of a device isn't included in setting. device language : " + currentCountryCode + ", setting language : " + keySet.toString() + "]");
                        }
                    }
                }
                if (!z) {
                    logUtil.debug_i(Constants.TAG, "---------------------------------------------------------");
                }
            }
            adInfo.a();
        } catch (Exception e3) {
            logUtil.debug_e(Constants.TAG, "JSONException");
            logUtil.debug_e(Constants.TAG, e3);
        }
    }
}
