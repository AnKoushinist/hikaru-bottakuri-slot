package jp.tjkapp.adfurikunsdk.moviereward;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.d.a.a.c;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;

public class ApiAccessUtil {
    public static final int SERVER_TYPE_AMAZON = 4;
    public static final int SERVER_TYPE_DEVELOPMENT = 0;
    public static final int SERVER_TYPE_PRODUCTION = 1;
    public static final int SERVER_TYPE_STAGING = 3;
    public static final String WEBAPI_KEY_ADNETWORKKEY = "adnetwork_key";
    public static final String WEBAPI_KEY_BANNER_KIND = "banner_kind";
    public static final String WEBAPI_KEY_BG_COLOR = "bg_color";
    public static final String WEBAPI_KEY_CYCLE_TIME = "cycle_time";
    public static final String WEBAPI_KEY_EXT_ACT_URL = "ext_act_url";
    public static final String WEBAPI_KEY_HTML = "html";
    public static final String WEBAPI_KEY_NOADCHECK = "noadcheck";
    public static final String WEBAPI_KEY_PARAM = "param";
    public static final String WEBAPI_KEY_SETTINGS = "settings";
    public static final String WEBAPI_KEY_TEST_FLG = "test_flg";
    public static final String WEBAPI_KEY_TRANSITION_OFF = "ta_off";
    public static final String WEBAPI_KEY_USER_AD_ID = "user_ad_id";
    public static final String WEBAPI_KEY_WALL_TYPE = "wall_type";
    public static final String WEBAPI_KEY_WEIGHT = "weight";

    public static class WebAPIResult {
        public JSONArray array = null;
        public String message = BuildConfig.FLAVOR;
        public int returnCode = -2;
    }

    public static boolean isConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isDevelopmentServerType() {
        return AdrurikunBuildConfig.SERVER_TYPE == 0;
    }

    public static boolean isProductionServerType() {
        return AdrurikunBuildConfig.SERVER_TYPE == SERVER_TYPE_PRODUCTION;
    }

    public static boolean isStagingServerType() {
        return AdrurikunBuildConfig.SERVER_TYPE == SERVER_TYPE_STAGING;
    }

    public static boolean isAmazonServerType() {
        return AdrurikunBuildConfig.SERVER_TYPE == SERVER_TYPE_AMAZON;
    }

    public static String getFillerUrl() {
        return isDevelopmentServerType() ? "http://115.30.27.111/adfurikun/api/get-default-script/app_id/[app_id]" : "http://d830x8j3o1b2k.cloudfront.net/adfurikun/api/get-default-script/app_id/[app_id]";
    }

    public static String getGetInfoBaseUrl() {
        switch (AdrurikunBuildConfig.SERVER_TYPE) {
            case SERVER_TYPE_DEVELOPMENT /*0*/:
                return "http://115.30.27.111/";
            case SERVER_TYPE_PRODUCTION /*1*/:
                return "https://adfurikun.jp/";
            case SERVER_TYPE_STAGING /*3*/:
                return "http://115.30.27.96/";
            case SERVER_TYPE_AMAZON /*4*/:
                return "https://ginf.adfurikun.jp/";
            default:
                return "http://115.30.27.111/";
        }
    }

    private static String a() {
        return AdrurikunBuildConfig.SERVER_TYPE == 0 ? "http://115.30.27.111/" : "https://adfurikun.jp/";
    }

    public static String getConversionBaseUrl() {
        return AdrurikunBuildConfig.SERVER_TYPE == 0 ? "http://115.30.27.111/" : "http://api.adfurikun.jp/";
    }

    private static String b() {
        switch (AdrurikunBuildConfig.SERVER_TYPE) {
            case SERVER_TYPE_DEVELOPMENT /*0*/:
                return "http://115.30.27.111/";
            case SERVER_TYPE_PRODUCTION /*1*/:
                return "http://api.adfurikun.jp/";
            case SERVER_TYPE_STAGING /*3*/:
                return "http://115.30.27.96/";
            case SERVER_TYPE_AMAZON /*4*/:
                String str = "http://d2cjo8xlt6fbwy.cloudfront.net/";
                return "http://i.adfurikun.jp/";
            default:
                return "http://115.30.27.111/";
        }
    }

    private static String c() {
        switch (AdrurikunBuildConfig.SERVER_TYPE) {
            case SERVER_TYPE_DEVELOPMENT /*0*/:
                return "http://115.30.27.111/";
            case SERVER_TYPE_PRODUCTION /*1*/:
                return "http://api.adfurikun.jp/";
            case SERVER_TYPE_STAGING /*3*/:
                return "http://115.30.27.96/";
            case SERVER_TYPE_AMAZON /*4*/:
                return "http://api.adfurikun.jp/";
            default:
                return "http://115.30.27.111/";
        }
    }

    public static String replaceIDFA(Context context, String str, LogUtil logUtil) {
        CharSequence gaid = FileUtil.getGaid(context);
        CharSequence packageName = context.getPackageName();
        try {
            str = str.replace(Constants.REPLACE_IDFA, gaid).replace(Constants.REPLACE_IDFA2, gaid).replace(Constants.REPLACE_PACKAGE, packageName);
            return str.replace(Constants.REPLACE_PACKAGE2, packageName);
        } catch (Exception e) {
            Exception exception = e;
            String str2 = str;
            logUtil.debug_e(Constants.TAG, "Exception");
            logUtil.debug_e(Constants.TAG, exception);
            return str2;
        }
    }

    public static String replaceIDFA(String str, String str2, LogUtil logUtil) {
        try {
            str = str.replace(Constants.REPLACE_IDFA, str2);
            return str.replace(Constants.REPLACE_IDFA2, str2);
        } catch (Exception e) {
            Exception exception = e;
            String str3 = str;
            logUtil.debug_e(Constants.TAG, "Exception");
            logUtil.debug_e(Constants.TAG, exception);
            return str3;
        }
    }

    public static String createUniqueID(String str) {
        String str2 = BuildConfig.FLAVOR;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(simpleDateFormat.format(Calendar.getInstance().getTime()));
            stringBuilder.append(new Random().nextInt());
            return a(MessageDigest.getInstance("SHA-1").digest(stringBuilder.toString().getBytes()));
        } catch (NoSuchAlgorithmException e) {
            return BuildConfig.FLAVOR;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = bArr.length;
        for (int i = SERVER_TYPE_DEVELOPMENT; i < length; i += SERVER_TYPE_PRODUCTION) {
            String toHexString = Integer.toHexString(bArr[i] & 255);
            for (int length2 = toHexString.length(); length2 < 2; length2 += SERVER_TYPE_PRODUCTION) {
                stringBuilder.append("0");
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    public static WebAPIResult getInfo(String str, LogUtil logUtil, String str2, boolean z) {
        String locale = Locale.getDefault().toString();
        StringBuilder stringBuilder = new StringBuilder();
        if (z) {
            stringBuilder.append(getGetInfoBaseUrl());
        } else {
            stringBuilder.append(a());
        }
        stringBuilder.append("adfurikun/api/getinfo/");
        stringBuilder.append("app_id/");
        stringBuilder.append(str);
        stringBuilder.append(Operation.DIVISION);
        stringBuilder.append("locale/");
        stringBuilder.append(locale);
        stringBuilder.append(Operation.DIVISION);
        stringBuilder.append("ver/");
        stringBuilder.append(Constants.ADFURIKUN_VERSION);
        return callGetWebAPI(stringBuilder.toString(), logUtil, str2, true);
    }

    public static WebAPIResult recImpression(String str, String str2, LogUtil logUtil, String str3, String str4, String str5, String str6) {
        String currentCountryCode = Util.getCurrentCountryCode();
        boolean z = false;
        String b = b();
        if (AdrurikunBuildConfig.SERVER_TYPE == SERVER_TYPE_AMAZON) {
            if (str5 == null || str5.length() <= 0) {
                z = true;
            } else {
                b = b.replace("http", "https");
                z = true;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b);
        stringBuilder.append("adfurikun/api/rec-impression");
        if (z) {
            stringBuilder.append(Operation.EMPTY_PARAM);
            stringBuilder.append("app_id=");
            stringBuilder.append(str);
            stringBuilder.append("&");
            stringBuilder.append("locale=");
            stringBuilder.append(currentCountryCode);
            stringBuilder.append("&");
            stringBuilder.append("user_ad_id=");
            stringBuilder.append(str2);
            stringBuilder.append("&");
            stringBuilder.append("uid=");
            stringBuilder.append(str4);
            if (str5 != null && str5.length() > 0) {
                stringBuilder.append("&");
                stringBuilder.append("rev=");
                stringBuilder.append(str5);
            }
            a(stringBuilder, str6, "&", Operation.EQUALS);
        } else {
            stringBuilder.append(Operation.DIVISION);
            stringBuilder.append("app_id/");
            stringBuilder.append(str);
            stringBuilder.append(Operation.DIVISION);
            stringBuilder.append("locale/");
            stringBuilder.append(currentCountryCode);
            stringBuilder.append(Operation.DIVISION);
            stringBuilder.append("user_ad_id/");
            stringBuilder.append(str2);
            stringBuilder.append(Operation.DIVISION);
            stringBuilder.append("uid/");
            stringBuilder.append(str4);
            if (str5 != null && str5.length() > 0) {
                stringBuilder.append(Operation.DIVISION);
                stringBuilder.append("rev/");
                stringBuilder.append(str5);
            }
            a(stringBuilder, str6, Operation.DIVISION, Operation.DIVISION);
        }
        return callGetWebAPI(stringBuilder.toString(), logUtil, str3, true);
    }

    public static WebAPIResult recFinished(String str, String str2, LogUtil logUtil, String str3, String str4, String str5) {
        String currentCountryCode = Util.getCurrentCountryCode();
        boolean z = AdrurikunBuildConfig.SERVER_TYPE == SERVER_TYPE_AMAZON;
        String c = c();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(c);
        stringBuilder.append("adfurikun/api/rec-finished");
        if (z) {
            stringBuilder.append(Operation.EMPTY_PARAM);
            stringBuilder.append("app_id=");
            stringBuilder.append(str);
            stringBuilder.append("&");
            stringBuilder.append("locale=");
            stringBuilder.append(currentCountryCode);
            stringBuilder.append("&");
            stringBuilder.append("user_ad_id=");
            stringBuilder.append(str2);
            stringBuilder.append("&");
            stringBuilder.append("uid=");
            stringBuilder.append(str4);
            a(stringBuilder, str5, "&", Operation.EQUALS);
        } else {
            stringBuilder.append(Operation.DIVISION);
            stringBuilder.append("app_id/");
            stringBuilder.append(str);
            stringBuilder.append(Operation.DIVISION);
            stringBuilder.append("locale/");
            stringBuilder.append(currentCountryCode);
            stringBuilder.append(Operation.DIVISION);
            stringBuilder.append("user_ad_id/");
            stringBuilder.append(str2);
            stringBuilder.append(Operation.DIVISION);
            stringBuilder.append("uid/");
            stringBuilder.append(str4);
            a(stringBuilder, str5, Operation.DIVISION, Operation.DIVISION);
        }
        return callGetWebAPI(stringBuilder.toString(), logUtil, str3, true);
    }

    private static void a(StringBuilder stringBuilder, String str, String str2, String str3) {
        if (stringBuilder != null && str != null && str.length() > 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str4 = (String) keys.next();
                    String string = jSONObject.getString(str4);
                    stringBuilder.append(str2);
                    stringBuilder.append(str4);
                    stringBuilder.append(str3);
                    stringBuilder.append(string);
                }
            } catch (Exception e) {
            }
        }
    }

    public static WebAPIResult getAdnetworkPriceCode(String str, String str2, LogUtil logUtil, String str3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://54.157.49.192/get-rtb-encrypted-price.php");
        stringBuilder.append("?ankey=");
        stringBuilder.append(str);
        stringBuilder.append("&price=");
        stringBuilder.append(str2);
        return callGetWebAPI(stringBuilder.toString(), logUtil, str3, true);
    }

    public static WebAPIResult getPriceCode(String str, LogUtil logUtil, String str2) {
        return callGetWebAPI((AdrurikunBuildConfig.SERVER_TYPE == SERVER_TYPE_AMAZON ? "https://d830x8j3o1b2k.cloudfront.net/adfurikun/api/get-game-logic-encrypted-price/price/" : "http://115.30.5.174/adfurikun/api/get-game-logic-encrypted-price?price=") + str, logUtil, str2, true);
    }

    public static WebAPIResult getIPUA(LogUtil logUtil, String str) {
        return callGetWebAPI("http://ipua.adfurikun.jp/ua.php", logUtil, str, true);
    }

    public static WebAPIResult callGetWebAPI(String str, LogUtil logUtil, String str2, boolean z) {
        WebAPIResult webAPIResult = new WebAPIResult();
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        try {
            HttpUriRequest httpGet = new HttpGet(str);
            httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
            HttpParams params = defaultHttpClient.getParams();
            if (z) {
                HttpConnectionParams.setConnectionTimeout(params, 5000);
                HttpConnectionParams.setSoTimeout(params, 5000);
            } else {
                HttpConnectionParams.setConnectionTimeout(params, 2000);
                HttpConnectionParams.setSoTimeout(params, 2000);
            }
            if (str2 != null && str2.length() > 0) {
                params.setParameter("http.useragent", str2);
            }
            HttpResponse execute = defaultHttpClient.execute(httpGet);
            webAPIResult.returnCode = execute.getStatusLine().getStatusCode();
            if (webAPIResult.returnCode == HttpResponseCode.OK) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                execute.getEntity().writeTo(byteArrayOutputStream);
                webAPIResult.message = byteArrayOutputStream.toString();
            } else if (webAPIResult.returnCode == HttpResponseCode.NOT_FOUND) {
                logUtil.debug_e(Constants.TAG, "url not found");
            } else if (webAPIResult.returnCode == 408) {
                logUtil.debug_e(Constants.TAG, "SC_REQUEST_TIMEOUT");
            } else if (webAPIResult.returnCode == HttpResponseCode.BAD_REQUEST) {
                logUtil.debug_e(Constants.TAG, "SC_BAD_REQUEST");
            } else {
                logUtil.debug_e(Constants.TAG, "\u53d6\u5f97\u6642\u30a8\u30e9\u30fc\u767a\u751f");
            }
        } catch (Exception e) {
            webAPIResult.returnCode = -2;
            logUtil.debug_e(Constants.TAG, "ClientProtocolException");
            logUtil.debug_e(Constants.TAG, e);
        } catch (Exception e2) {
            webAPIResult.returnCode = -2;
            logUtil.debug_e(Constants.TAG, "IllegalArgumentException");
            logUtil.debug_e(Constants.TAG, e2);
        } catch (Exception e22) {
            webAPIResult.returnCode = -2;
            logUtil.debug_e(Constants.TAG, "IOException");
            logUtil.debug_e(Constants.TAG, e22);
        }
        return webAPIResult;
    }

    public static WebAPIResult callPostWebAPI(String str, LogUtil logUtil, String str2, String str3, HashMap<String, String> hashMap, String str4, boolean z) {
        WebAPIResult webAPIResult = new WebAPIResult();
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        try {
            String str5 = !TextUtils.isEmpty(str3) ? "application/json" : "application/x-www-form-urlencoded";
            HttpUriRequest httpPost = new HttpPost(str.toString());
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", str5);
            if (!TextUtils.isEmpty(str4)) {
                httpPost.addHeader("x-forwarded-for", str4);
            }
            HttpParams params = defaultHttpClient.getParams();
            if (z) {
                HttpConnectionParams.setConnectionTimeout(params, 5000);
                HttpConnectionParams.setSoTimeout(params, 5000);
            } else {
                HttpConnectionParams.setConnectionTimeout(params, 2000);
                HttpConnectionParams.setSoTimeout(params, 2000);
            }
            if (!TextUtils.isEmpty(str2)) {
                params.setParameter("http.useragent", str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                httpPost.setEntity(new StringEntity(str3));
            }
            if (!(hashMap == null || hashMap.isEmpty())) {
                List arrayList = new ArrayList();
                for (String str52 : hashMap.keySet()) {
                    arrayList.add(new BasicNameValuePair(str52, (String) hashMap.get(str52)));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(arrayList, c.DEFAULT_CHARSET));
            }
            HttpResponse execute = defaultHttpClient.execute(httpPost);
            webAPIResult.returnCode = execute.getStatusLine().getStatusCode();
            if (webAPIResult.returnCode == HttpResponseCode.OK) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                execute.getEntity().writeTo(byteArrayOutputStream);
                webAPIResult.message = byteArrayOutputStream.toString();
            } else if (webAPIResult.returnCode == HttpResponseCode.NOT_FOUND) {
                logUtil.debug_e(Constants.TAG, "url not found");
            } else if (webAPIResult.returnCode == 408) {
                logUtil.debug_e(Constants.TAG, "SC_REQUEST_TIMEOUT");
            } else if (webAPIResult.returnCode == HttpResponseCode.BAD_REQUEST) {
                logUtil.debug_e(Constants.TAG, "SC_BAD_REQUEST");
            } else {
                logUtil.debug_e(Constants.TAG, "\u53d6\u5f97\u6642\u30a8\u30e9\u30fc\u767a\u751f");
            }
        } catch (Exception e) {
            webAPIResult.returnCode = -2;
            logUtil.debug_e(Constants.TAG, "ClientProtocolException");
            logUtil.debug_e(Constants.TAG, e);
        } catch (Exception e2) {
            webAPIResult.returnCode = -2;
            logUtil.debug_e(Constants.TAG, "IllegalArgumentException");
            logUtil.debug_e(Constants.TAG, e2);
        } catch (Exception e22) {
            webAPIResult.returnCode = -2;
            logUtil.debug_e(Constants.TAG, "IOException");
            logUtil.debug_e(Constants.TAG, e22);
        }
        return webAPIResult;
    }

    public static void reportClashLytics(Context context, String str) {
        try {
            String str2 = (String) context.getApplicationInfo().metaData.get(Constants.CLASHLYTICS_META_KEY);
            if (str2 != null && str2.equals(Constants.CLASHLYTICS_API_KEY)) {
                try {
                    Class cls = Class.forName("com.b.a.a");
                    Class[] clsArr = new Class[SERVER_TYPE_PRODUCTION];
                    clsArr[SERVER_TYPE_DEVELOPMENT] = Throwable.class;
                    Method method = cls.getMethod("logException", clsArr);
                    if (method != null) {
                        Object[] objArr = new Object[SERVER_TYPE_PRODUCTION];
                        objArr[SERVER_TYPE_DEVELOPMENT] = new Throwable(str);
                        method.invoke(cls, objArr);
                    }
                } catch (ClassNotFoundException e) {
                } catch (NullPointerException e2) {
                } catch (Exception e3) {
                } catch (Error e4) {
                }
            }
        } catch (NullPointerException e5) {
        }
    }
}
