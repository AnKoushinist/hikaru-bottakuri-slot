package com.igaworks.core;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManager;
import com.d.a.a.c;
import com.igaworks.core.AdvertisingIdClient.AdInfo;
import com.igaworks.dao.CohortDAO;
import com.igaworks.dao.CoreIDDAO;
import com.igaworks.dao.IgawSignatureManager;
import com.igaworks.dao.LocalDemograhpicDAO;
import com.igaworks.dao.ReferralInfoDAO;
import com.igaworks.model.DeeplinkConversionItem;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.IgawBase64;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import jp.co.geniee.gnsrewardadapter.GNSAdapterAppLovinRewardVideoAd;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import twitter4j.MediaEntity.Size;

public class RequestParameter {
    private static Context context;
    public static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    private static RequestParameter singletonATRequestParameter;
    private String activity = null;
    private long adbrix_user_no = -1;
    private AdInfo adidInfo = null;
    private String ag = null;
    private String android_id = null;
    private long app_launch_count = 0;
    private String appkey = BuildConfig.FLAVOR;
    private String carrier = BuildConfig.FLAVOR;
    private int channel_type = -1;
    private int conversion_key = -1;
    private DeviceIDManger didManager;
    private String google_ad_id = BuildConfig.FLAVOR;
    private boolean google_ad_id_opt_out = false;
    private String hashkey = null;
    private String install_datetime = null;
    private boolean isWifiDevice = false;
    private long life_hour = 0;
    private String market = BuildConfig.FLAVOR;
    private String mc = null;
    private String model = BuildConfig.FLAVOR;
    private String mudid = null;
    private int nonCustomNetwork = 0;
    private String openudid = null;
    private String os = BuildConfig.FLAVOR;
    private String pudid = null;
    private String puid = null;
    private long referral_key = -1;
    private String refusn = null;
    private int reqseq = 0;
    private boolean security_enable = false;
    private long session_no = -1;
    private int shard_no = -1;
    private String subreferral_key = null;
    private String thirdPartyID;

    private RequestParameter(Context context) {
        context = context;
        this.didManager = DeviceIDManger.getInstance(context);
        try {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if (RequestParameter.this.adidInfo == null) {
                            RequestParameter.this.adidInfo = DeviceIDManger.getInstance(RequestParameter.context).getAndroidADID(RequestParameter.context, null);
                            if (RequestParameter.this.adidInfo != null) {
                                RequestParameter.this.google_ad_id = RequestParameter.this.adidInfo.getId();
                                RequestParameter.this.google_ad_id_opt_out = RequestParameter.this.adidInfo.isLimitAdTrackingEnabled();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
        }
    }

    public void setAppKey(String str) {
        if (str == null || str.equals(BuildConfig.FLAVOR)) {
            this.appkey = "-1";
        }
        this.appkey = str;
    }

    public void setThirdPartyID(String str) {
        if (str != null && str.length() > 0) {
            this.thirdPartyID = str;
        }
    }

    public void setMc(String str) {
        if (str == null || str.equals(BuildConfig.FLAVOR)) {
            this.mc = "unknown";
        }
        this.mc = str;
    }

    public void setMarketPlace(String str) {
        if (str == null || str.equals(BuildConfig.FLAVOR)) {
            str = "unknown";
        }
        this.market = str;
    }

    public String getMarketPlace() {
        return this.market;
    }

    public String getModel() {
        return this.model;
    }

    public long getappLaunchCount() {
        return this.app_launch_count;
    }

    public void setActivityName(String str) {
        if (str == null || str.equals(BuildConfig.FLAVOR)) {
            this.activity = "unknown";
        }
        this.activity = str;
    }

    public String getAppkey() {
        return this.appkey;
    }

    public String getHashkey() {
        return this.hashkey;
    }

    public void setHashkey(String str) {
        this.hashkey = str;
    }

    public static RequestParameter getATRequestParameter(Context context) {
        if (singletonATRequestParameter == null) {
            synchronized (RequestParameter.class) {
                if (singletonATRequestParameter == null) {
                    IgawLogger.Logging(context, "IGAW_QA", "new ATRequest Parameter created", 3);
                    singletonATRequestParameter = new RequestParameter(context);
                }
            }
        }
        context = context;
        return singletonATRequestParameter;
    }

    private String checkIsNullOrEmptyAndReturnRegString(String str) {
        return (str == null || str.length() <= 0) ? BuildConfig.FLAVOR : str;
    }

    public String getTrackingParameterForADBrix(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2, String str, boolean z) {
        return getTrackingParameterForADBrix(context, arrayList, arrayList2, null, str, z);
    }

    public String getTrackingParameterForADBrix(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<Integer> arrayList3, String str, boolean z) {
        String jSONObject = getAdbrixJSONParameter(arrayList, arrayList2, arrayList3, str, z).toString();
        IgawLogger.Logging(context, "IGAW_QA", "ATRequestParameter > tracking Parameter : " + jSONObject, 3);
        return jSONObject;
    }

    public String getReferrerTrackingParameter(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2, String str, boolean z) {
        String jSONObject = getAdbrixJSONParameter(arrayList, arrayList2, null, null, str, z, true).toString();
        IgawLogger.Logging(context, "IGAW_QA", "ATRequestParameter > referral Parameter : " + jSONObject, 3);
        return jSONObject;
    }

    public String getDemographicParameter() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("puid", checkIsNullOrEmptyAndReturnRegString(this.puid));
        if (this.google_ad_id == null) {
            this.google_ad_id = CoreIDDAO.getInstance().getGoogleAdId(context);
        }
        jSONObject.put("google_ad_id", this.google_ad_id);
        JSONArray jSONArray = new JSONArray();
        List<Pair> demoInfo = getDemoInfo();
        if (demoInfo != null) {
            for (Pair pair : demoInfo) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("demo_key", pair.first);
                jSONObject2.put("demo_value", pair.second);
                jSONArray.put(jSONObject2);
            }
        }
        jSONObject.put("user_demo_info", jSONArray);
        String jSONObject3 = jSONObject.toString();
        IgawLogger.Logging(context, "IGAW_QA", "ATRequestParameter > tracking Parameter : user_demo_info" + jSONObject3, 3, true);
        return jSONObject3;
    }

    private JSONObject getAdbrixJSONParameter(ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<Integer> arrayList3, String str, boolean z) {
        return getAdbrixJSONParameter(arrayList, arrayList2, arrayList3, null, str, z, false);
    }

    private JSONObject getAdbrixJSONParameter(ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<Integer> arrayList3, List<DeeplinkConversionItem> list, String str, boolean z, boolean z2) {
        int i;
        DeviceIDManger deviceIDManger;
        Iterator it;
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        JSONArray jSONArray2 = new JSONArray();
        JSONArray jSONArray3 = new JSONArray();
        JSONArray jSONArray4 = new JSONArray();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        JSONObject jSONObject5 = new JSONObject();
        JSONArray jSONArray5 = new JSONArray();
        jSONObject.put("appkey", this.appkey);
        jSONObject.put(GNSAdapterAppLovinRewardVideoAd.PACKAGE_NAME_COLUMN_NAME, context.getPackageName());
        jSONObject.put(MediationMetaData.KEY_VERSION, IgawUpdateLog.getCommonVersion());
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            jSONObject.put("app_version_name", packageInfo.versionName);
            jSONObject.put("app_version_code", packageInfo.versionCode);
        } catch (Exception e) {
        }
        try {
            jSONObject.put("third_party_id", this.thirdPartyID);
        } catch (Exception e2) {
        }
        this.conversion_key = ReferralInfoDAO.getReferralInfo_conversionKey(context);
        jSONObject4.put("conversion_key", this.conversion_key);
        this.session_no = ReferralInfoDAO.getReferralInfo_session_no(context);
        jSONObject4.put("session_no", this.session_no);
        try {
            jSONObject4.put("referrer_param", URLEncoder.encode(ReferralInfoDAO.getReferralInfo_referrer_params(context), c.DEFAULT_CHARSET));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        jSONObject.put("referral_info", jSONObject4);
        ArrayList conversionCache = getConversionCache();
        if (conversionCache != null && conversionCache.size() > 0) {
            for (i = 0; i < conversionCache.size(); i++) {
                try {
                    jSONArray3.put(conversionCache.get(i));
                } catch (Exception e4) {
                    IgawLogger.Logging(context, "IGAW_QA", "error occurred during convert conversion_cache to integer", 0);
                }
            }
        }
        jSONObject.put("conversion_cache", jSONArray3);
        this.adbrix_user_no = getADBrixUserNo();
        try {
            jSONObject2.put("adbrix_user_no", this.adbrix_user_no);
        } catch (Exception e5) {
            jSONObject2.put("adbrix_user_no", -1);
            IgawLogger.Logging(context, "IGAW_QA", "error occurred during convert adbrix_user_no to long", 0);
        }
        try {
            this.shard_no = getADBrixUserInfo_ShardNo();
            jSONObject2.put("shard_no", this.shard_no);
        } catch (Exception e6) {
            jSONObject2.put("shard_no", -1);
            IgawLogger.Logging(context, "IGAW_QA", "error occurred during convert shard_no to int", 0);
        }
        try {
            if (this.install_datetime == null || this.install_datetime.length() < 1) {
                this.install_datetime = getADBrixUserInfo_install_datetime();
            }
            if (this.install_datetime != null) {
                jSONObject2.put("install_datetime", this.install_datetime);
                try {
                    jSONObject2.put("install_mdatetime", df.parse(this.install_datetime).getTime());
                } catch (Exception e7) {
                }
            } else {
                jSONObject2.put("install_datetime", BuildConfig.FLAVOR);
                jSONObject2.put("install_mdatetime", BuildConfig.FLAVOR);
            }
        } catch (Exception e8) {
            jSONObject2.put("install_datetime", BuildConfig.FLAVOR);
        }
        jSONObject2.put("life_hour", calculateLifeHour());
        jSONObject2.put("app_launch_count", this.app_launch_count);
        this.referral_key = getReferralKey();
        jSONObject2.put("referral_key", this.referral_key);
        try {
            int checkSignAndPackage = IgawSignatureManager.checkSignAndPackage(context, str);
            boolean z3 = (checkSignAndPackage | 1) != checkSignAndPackage;
            jSONObject2.put("set_referral_key", z3);
            jSONObject2.put("sig_type", checkSignAndPackage);
            IgawLogger.Logging(context, "IGAW_QA", "sig_type : " + checkSignAndPackage, 3, false);
            if (z3 && z2) {
                IgawSignatureManager.createSignature(context, str);
            }
        } catch (Exception e32) {
            e32.printStackTrace();
        }
        jSONObject.put("adbrix_user_info", jSONObject2);
        this.puid = this.didManager.getAESPuid(context);
        jSONObject5.put("puid", checkIsNullOrEmptyAndReturnRegString(this.puid));
        this.mudid = getMhowUdid(context);
        jSONObject5.put("mudid", checkIsNullOrEmptyAndReturnRegString(this.mudid));
        if (OpenUDID_manager.isInitialized()) {
            this.openudid = this.didManager.getOpenUDID();
            jSONObject5.put("openudid", checkIsNullOrEmptyAndReturnRegString(this.openudid));
        } else {
            jSONObject5.put("openudid", BuildConfig.FLAVOR);
        }
        if (this.openudid == null || this.openudid.length() <= 0) {
            jSONObject5.put("openudid_md5", BuildConfig.FLAVOR);
        } else {
            deviceIDManger = this.didManager;
            String str2 = this.openudid;
            this.didManager.getClass();
            jSONObject5.put("openudid_md5", deviceIDManger.getOPENUDID(str2, 100));
        }
        if (this.openudid == null || this.openudid.length() <= 0) {
            jSONObject5.put("openudid_sha1", BuildConfig.FLAVOR);
        } else {
            deviceIDManger = this.didManager;
            str2 = this.openudid;
            this.didManager.getClass();
            jSONObject5.put("openudid_sha1", deviceIDManger.getOPENUDID(str2, Size.CROP));
        }
        deviceIDManger = this.didManager;
        Context context = context;
        this.didManager.getClass();
        jSONObject5.put("android_id_md5", checkIsNullOrEmptyAndReturnRegString(deviceIDManger.getAndroidId(context, 100)));
        deviceIDManger = this.didManager;
        context = context;
        this.didManager.getClass();
        jSONObject5.put("android_id_sha1", checkIsNullOrEmptyAndReturnRegString(deviceIDManger.getAndroidId(context, Size.CROP)));
        deviceIDManger = this.didManager;
        context = context;
        this.didManager.getClass();
        jSONObject5.put("device_id_md5", deviceIDManger.getDeviceID(context, 100));
        deviceIDManger = this.didManager;
        context = context;
        this.didManager.getClass();
        jSONObject5.put("device_id_sha1", checkIsNullOrEmptyAndReturnRegString(deviceIDManger.getDeviceID(context, Size.CROP)));
        this.google_ad_id = str;
        this.google_ad_id_opt_out = z;
        jSONObject5.put("google_ad_id", this.google_ad_id);
        jSONObject5.put("google_ad_id_opt_out", this.google_ad_id_opt_out);
        try {
            if (this.ag == null || this.ag.length() < 1) {
                context.getPackageManager();
                if (CommonHelper.checkSelfPermission(context, "android.permission.GET_ACCOUNTS")) {
                    for (Account account : AccountManager.get(context).getAccounts()) {
                        if (validateEmailFormat(account.name)) {
                            this.ag = DeviceIDManger.getMd5Value(account.name);
                            break;
                        }
                    }
                }
            }
            jSONObject5.put("ag", this.ag);
        } catch (Exception e322) {
            IgawLogger.Logging(context, "IGAW_QA", "error occurred during get google account : " + e322.getMessage(), 0);
        }
        jSONObject5.put("odin", checkIsNullOrEmptyAndReturnRegString(this.didManager.getODIN1(context)));
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            this.carrier = telephonyManager.getNetworkOperatorName();
        }
        if (this.carrier == null || this.carrier.equalsIgnoreCase(BuildConfig.FLAVOR)) {
            this.carrier = "unknown";
        }
        this.carrier = URLEncoder.encode(this.carrier);
        jSONObject5.put("carrier", this.carrier);
        Locale locale = Locale.getDefault();
        jSONObject5.put("country", locale.getCountry());
        jSONObject5.put("language", locale.getLanguage());
        jSONObject5.put(TapjoyConstants.TJC_ANDROID_ID, IgawBase64.encodeString(DeviceIDManger.getAndroidId(context)));
        jSONObject.put("user_info", jSONObject5);
        try {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("custom_cohort_1", CohortDAO.getCohort(context, "custom_cohort_1"));
            jSONObject6.put("custom_cohort_2", CohortDAO.getCohort(context, "custom_cohort_2"));
            jSONObject6.put("custom_cohort_3", CohortDAO.getCohort(context, "custom_cohort_3"));
            jSONObject.put("cohort_info", jSONObject6);
        } catch (Exception e9) {
        }
        String string = context.getSharedPreferences("adpopcorn_parameter", 0).getString("adpopcorn_sdk_market", BuildConfig.FLAVOR);
        if (!(string.equals(BuildConfig.FLAVOR) || string.equals("__UNDEFINED__MARKET__"))) {
            this.market = string;
        }
        jSONObject3.put("vendor", this.market);
        if (Build.MODEL == null || Build.MODEL.equalsIgnoreCase(BuildConfig.FLAVOR)) {
            this.model = BuildConfig.FLAVOR;
        } else {
            this.model = Build.MODEL;
        }
        jSONObject3.put("model", this.model);
        try {
            jSONObject3.put("kn", System.getProperty("os.version"));
        } catch (Exception e10) {
        }
        if (getWifiDevice(context)) {
            jSONObject3.put("is_wifi_only", true);
        } else {
            jSONObject3.put("is_wifi_only", false);
        }
        jSONObject3.put("network", getCustomNetworkInfo(context));
        jSONObject3.put("noncustomnetwork", getNonCustomNetworkInfo(context));
        if (!(VERSION.RELEASE == null || VERSION.RELEASE.equalsIgnoreCase(BuildConfig.FLAVOR))) {
            this.os = VERSION.RELEASE;
        }
        jSONObject3.put("os", "a_" + this.os);
        jSONObject3.put("ptype", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        Display GetDisplay = GetDisplay(context);
        if (context.getResources().getConfiguration().orientation == 2) {
            jSONObject3.put("width", GetDisplay.getHeight());
            jSONObject3.put("height", GetDisplay.getWidth());
            jSONObject3.put("is_portrait", false);
        } else {
            jSONObject3.put("width", GetDisplay.getWidth());
            jSONObject3.put("height", GetDisplay.getHeight());
            jSONObject3.put("is_portrait", true);
        }
        try {
            double round = round(((float) Calendar.getInstance().getTimeZone().getRawOffset()) / 3600000.0f);
            IgawLogger.Logging(context, "IGAW_QA", "UTC_OFFSET: " + round, 3, true);
            jSONObject3.put("utc_offset", round);
        } catch (Exception e3222) {
            e3222.printStackTrace();
            IgawLogger.Logging(context, "IGAW_QA", "UTC_OFFSET Error: " + e3222.getMessage(), 0, false);
        }
        jSONObject.put("device_info", jSONObject3);
        List<Pair> persistantDemoInfo_v2 = getPersistantDemoInfo_v2();
        if (persistantDemoInfo_v2 != null) {
            for (Pair pair : persistantDemoInfo_v2) {
                JSONObject jSONObject7 = new JSONObject();
                jSONObject7.put("demo_key", pair.first);
                jSONObject7.put("demo_value", pair.second);
                jSONArray4.put(jSONObject7);
            }
        }
        jSONObject.put("demographics", jSONArray4);
        if (arrayList3 != null && arrayList3.size() > 0) {
            it = arrayList3.iterator();
            while (it.hasNext()) {
                jSONArray2.put(((Integer) it.next()).intValue());
            }
        }
        jSONObject.put("complete_conversions", jSONArray2);
        if (arrayList != null) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                string = (String) it2.next();
                try {
                    jSONArray.put(new JSONObject(string));
                } catch (Exception e11) {
                    IgawLogger.Logging(context, "IGAW_QA", "error occurred during fill activity info : " + e11.toString() + "\n contents : " + string, 0);
                }
            }
        }
        jSONObject.put("activity_info", jSONArray);
        if (arrayList2 != null) {
            it = arrayList2.iterator();
            while (it.hasNext()) {
                try {
                    jSONArray5.put(new JSONObject((String) it.next()));
                } catch (Exception e32222) {
                    IgawLogger.Logging(context, "IGAW_QA", "error occurred during fill imp info : " + e32222.toString(), 0);
                }
            }
        }
        jSONObject.put("impression_info", jSONArray5);
        if (list != null) {
            try {
                JSONArray jSONArray6 = new JSONArray();
                for (DeeplinkConversionItem deeplinkConversionItem : list) {
                    JSONObject jSONObject8 = new JSONObject();
                    jSONObject8.put("click_id", deeplinkConversionItem.getCommerceClickID());
                    jSONObject8.put("conversion_key", deeplinkConversionItem.getConversionKey());
                    jSONObject8.put("link_params", deeplinkConversionItem.getLinkParams());
                    jSONArray6.put(jSONObject8);
                }
                jSONObject.put("commerce_conversion", jSONArray6);
            } catch (Exception e12) {
            }
        }
        return jSONObject;
    }

    private boolean getWifiDevice(Context context) {
        try {
            if (((TelephonyManager) context.getSystemService("phone")) == null) {
                this.isWifiDevice = true;
            } else {
                this.isWifiDevice = false;
            }
        } catch (Exception e) {
            this.isWifiDevice = false;
            e.printStackTrace();
        }
        return this.isWifiDevice;
    }

    private Display GetDisplay(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    public void setSecurity_enable(boolean z) {
        this.security_enable = z;
    }

    public String getMhowUdid(Context context) {
        String imei = CoreIDDAO.getInstance().getIMEI(context);
        if (imei == null) {
            return imei;
        }
        try {
            if (imei.equals(BuildConfig.FLAVOR)) {
                return BuildConfig.FLAVOR;
            }
            return Mhows_AES_Util.encrypt(imei);
        } catch (Exception e) {
            e.printStackTrace();
            return imei;
        }
    }

    public String getCustomNetworkInfo(Context context) {
        if (context == null) {
            return "unKnown";
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return "unKnown";
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
            if (networkInfo != null && (networkInfo.getState() == State.CONNECTED || networkInfo.getState() == State.CONNECTING)) {
                return TapjoyConstants.TJC_CONNECTION_TYPE_MOBILE;
            }
            if (networkInfo2 != null) {
                if (networkInfo2.getState() == State.CONNECTED || networkInfo2.getState() == State.CONNECTING) {
                    return TapjoyConstants.TJC_CONNECTION_TYPE_WIFI;
                }
            }
            return "unknown";
        } catch (Exception e) {
            e.printStackTrace();
            return "unKnown";
        }
    }

    public int getNonCustomNetworkInfo(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Pair<String, String>> getDemoInfo() {
        List<Pair<String, String>> arrayList = new ArrayList();
        Map all = context.getSharedPreferences("demoForTracking", 0).getAll();
        if (all.size() == 0) {
            return null;
        }
        JSONObject convertDemographicInfoFromSP2JSONObject = LocalDemograhpicDAO.getInstance(context).convertDemographicInfoFromSP2JSONObject(context);
        for (String str : all.keySet()) {
            if (convertDemographicInfoFromSP2JSONObject.has(str)) {
                try {
                    String string = convertDemographicInfoFromSP2JSONObject.getString(str);
                    if (string == null) {
                        arrayList.add(new Pair(str, (String) all.get(str)));
                        IgawLogger.Logging(context, "IGAW_QA", "Demographic info for tracking >> key: " + str + "; value :" + ((String) all.get(str)), 3, true);
                    } else {
                        String str2 = (String) all.get(str);
                        if (!(str2 == null || str2.equals(string))) {
                            arrayList.add(new Pair(str, (String) all.get(str)));
                            IgawLogger.Logging(context, "IGAW_QA", "Demographic info for tracking >> key: " + str + "; value :" + ((String) all.get(str)), 3, true);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                arrayList.add(new Pair(str, (String) all.get(str)));
                IgawLogger.Logging(context, "IGAW_QA", "Demographic info for tracking >> key: " + str + "; value :" + ((String) all.get(str)), 3, true);
            }
        }
        return arrayList;
    }

    public List<Pair<String, String>> getPersistantDemoInfo_v2() {
        List<Pair<String, String>> arrayList = new ArrayList();
        Map all = context.getSharedPreferences("persistantDemoForTracking", 0).getAll();
        if (all.size() == 0) {
            return null;
        }
        for (String str : all.keySet()) {
            arrayList.add(new Pair(str, (String) all.get(str)));
        }
        return arrayList;
    }

    public static String convertActivityStringToJson(String str, String str2, String str3, String str4, String str5, String str6) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("prev_group", str);
        jSONObject.put("prev_activity", str2);
        jSONObject.put("group", str3);
        jSONObject.put("activity", str4);
        if (str5 == null) {
            str5 = BuildConfig.FLAVOR;
        }
        jSONObject.put("param", str5);
        jSONObject.put("created_at", str6);
        return jSONObject.toString();
    }

    public void setConversionCache(int i) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("conversionCache", 0);
        if (sharedPreferences.contains(new StringBuilder(String.valueOf(i)).toString())) {
            IgawLogger.Logging(context, "IGAW_QA", "conversionKey was already saved in storage", 3);
            return;
        }
        Editor edit = sharedPreferences.edit();
        edit.putInt(new StringBuilder(String.valueOf(i)).toString(), i);
        edit.commit();
    }

    public ArrayList<Integer> getConversionCache() {
        ArrayList<Integer> arrayList = new ArrayList();
        try {
            Collection values = context.getSharedPreferences("conversionCache", 0).getAll().values();
            if (values == null || values.size() == 0) {
                return null;
            }
            for (Object next : values) {
                int intValue;
                try {
                    intValue = ((Integer) next).intValue();
                } catch (Exception e) {
                    try {
                        intValue = Integer.parseInt((String) next);
                    } catch (Exception e2) {
                    }
                }
                arrayList.add(Integer.valueOf(intValue));
            }
            return arrayList;
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void setADBrixUserInfo(long j, long j2) {
        final long j3 = j;
        final long j4 = j2;
        new Thread(new Runnable() {
            public void run() {
                Editor edit = RequestParameter.context.getSharedPreferences("adbrix_user_info", 0).edit();
                if (j3 > -1) {
                    edit.putLong("adbrix_user_no", j3);
                    RequestParameter.this.adbrix_user_no = j3;
                    edit.putLong("life_hour_start_time", j4);
                }
                edit.commit();
            }
        }).start();
    }

    public void setADBrixUserInfo_ReferralKey(final long j) {
        new Thread(new Runnable() {
            public void run() {
                Editor edit = RequestParameter.context.getSharedPreferences("adbrix_user_info", 0).edit();
                if (j != -1) {
                    edit.putLong("referral_key", j);
                    RequestParameter.this.referral_key = j;
                }
                edit.commit();
            }
        }).start();
    }

    public void setADBrixUserInfo_SubReferralKey(final String str) {
        new Thread(new Runnable() {
            public void run() {
                Editor edit = RequestParameter.context.getSharedPreferences("adbrix_user_info", 0).edit();
                if (str != null && str.length() > 0) {
                    edit.putString("subreferral_key", str);
                    RequestParameter.this.subreferral_key = str;
                }
                edit.commit();
            }
        }).start();
    }

    public String getADBrixUserInfo_Refusn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("adbrix_user_info", 0);
        if (sharedPreferences.contains("refusn")) {
            return sharedPreferences.getString("refusn", null);
        }
        return null;
    }

    public void setADBrixUserInfo_Refusn(final String str) {
        new Thread(new Runnable() {
            public void run() {
                Editor edit = RequestParameter.context.getSharedPreferences("adbrix_user_info", 0).edit();
                if (str != null && str.length() > 0) {
                    edit.putString("refusn", str);
                    RequestParameter.this.refusn = str;
                }
                edit.commit();
            }
        }).start();
    }

    public int getADBrixUserInfo_ShardNo() {
        if (this.shard_no > -1) {
            return this.shard_no;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("adbrix_user_info", 0);
        if (sharedPreferences.contains("shard_no")) {
            return sharedPreferences.getInt("shard_no", -1);
        }
        return -1;
    }

    public void setADBrixUserInfo_ShardNo(final int i) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Editor edit = RequestParameter.context.getSharedPreferences("adbrix_user_info", 0).edit();
                    if (i > -1) {
                        edit.putInt("shard_no", i);
                        RequestParameter.this.shard_no = i;
                    }
                    edit.commit();
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public String getADBrixUserInfo_install_datetime() {
        if (this.install_datetime != null && this.install_datetime.length() > 0) {
            return this.install_datetime;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("adbrix_user_info", 0);
        if (sharedPreferences.contains("install_datetime")) {
            return sharedPreferences.getString("install_datetime", null);
        }
        return null;
    }

    public void setADBrixUserInfo_install_datetime(final String str) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Editor edit = RequestParameter.context.getSharedPreferences("adbrix_user_info", 0).edit();
                    if (str != null && str.length() > 0) {
                        edit.putString("install_datetime", str);
                        RequestParameter.this.install_datetime = str;
                    }
                    edit.commit();
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public String getADBrixUserInfo_SubReferralKey() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("adbrix_user_info", 0);
        if (sharedPreferences.contains("subreferral_key")) {
            return sharedPreferences.getString("subreferral_key", null);
        }
        return null;
    }

    public void setChannelType(final int i) {
        new Thread(new Runnable() {
            public void run() {
                Editor edit = RequestParameter.context.getSharedPreferences("adbrix_user_info", 0).edit();
                if (i != -1) {
                    edit.putInt("channel_type", i);
                    RequestParameter.this.channel_type = i;
                }
                edit.commit();
            }
        }).start();
    }

    public long getADBrixUserNo() {
        long j = -1;
        SharedPreferences sharedPreferences = context.getSharedPreferences("adbrix_user_info", 0);
        try {
            j = sharedPreferences.getLong("adbrix_user_no", -1);
        } catch (Exception e) {
            try {
                j = Long.parseLong(sharedPreferences.getString("adbrix_user_no", "-1"));
            } catch (Exception e2) {
            }
        }
        return j;
    }

    public long calculateLifeHour() {
        long j = context.getSharedPreferences("adbrix_user_info", 0).getLong("life_hour_start_time", 0);
        if (j == 0) {
            return -1;
        }
        j = (System.currentTimeMillis() - j) / 3600000;
        IgawLogger.Logging(context, "IGAW_QA", "calculate lifehour : " + j, 3);
        return j;
    }

    public long getReferralKey() {
        if (this.referral_key > -1) {
            return this.referral_key;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("adbrix_user_info", 0);
        try {
            this.referral_key = sharedPreferences.getLong("referral_key", -1);
        } catch (Exception e) {
            try {
                this.referral_key = Long.parseLong(sharedPreferences.getString("referral_key", "-1"));
            } catch (Exception e2) {
                this.referral_key = -1;
            }
        }
        return this.referral_key;
    }

    public int getChannelType() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("adbrix_user_info", 0);
        try {
            this.channel_type = sharedPreferences.getInt("channel_type", -1);
        } catch (Exception e) {
            try {
                this.channel_type = Integer.parseInt(sharedPreferences.getString("channel_type", "-1"));
            } catch (Exception e2) {
                this.channel_type = -1;
            }
        }
        return this.channel_type;
    }

    public void increaseAppLaunchCount() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    SharedPreferences sharedPreferences = RequestParameter.context.getSharedPreferences("adbrix_user_info", 0);
                    Editor edit = sharedPreferences.edit();
                    RequestParameter.this.app_launch_count = sharedPreferences.getLong("app_launch_count", 0);
                    RequestParameter requestParameter = RequestParameter.this;
                    requestParameter.app_launch_count = requestParameter.app_launch_count + 1;
                    IgawLogger.Logging(RequestParameter.context, "IGAW_QA", "app_launch_count : " + RequestParameter.this.app_launch_count, 3);
                    edit.putLong("app_launch_count", RequestParameter.this.app_launch_count);
                    edit.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static boolean validateEmailFormat(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", str.trim());
    }

    public static double round(float f) {
        return 0.5d * ((double) Math.round(2.0f * f));
    }
}
