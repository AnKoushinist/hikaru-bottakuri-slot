package com.igaworks.impl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.IgawUpdateLog;
import com.igaworks.core.OpenUDID_manager;
import com.igaworks.core.RequestParameter;
import com.igaworks.dao.ActivityInfoDAO;
import com.igaworks.dao.AppImpressionDAO;
import com.igaworks.dao.CoreIDDAO;
import com.igaworks.dao.DeeplinkConversionRetryDAO;
import com.igaworks.dao.ReferralInfoDAO;
import com.igaworks.dao.tracking.TrackingActivitySQLiteDB;
import com.igaworks.interfaces.CommonActivityListener;
import com.igaworks.interfaces.CommonInterface;
import com.igaworks.interfaces.ExtendedCommonActivityListener;
import com.igaworks.interfaces.ICommonLiveOpsCallbackListener;
import com.igaworks.model.DeeplinkConversionItem;
import com.igaworks.net.CommonHttpManager;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.bolts_task.Continuation;
import com.igaworks.util.bolts_task.Task;
import com.igaworks.util.bolts_task.TaskUtils;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TJAdUnitConstants.String;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class CommonFrameworkImpl implements CommonInterface {
    protected static long ContinueSessionMillis = 60000;
    public static final List<String> GROUPS_FOR_TRACKING_INSTANTLY = new ArrayList();
    protected static Context appContext;
    protected static String appkey = null;
    protected static Map<String, ExtendedCommonActivityListener> eListeners;
    protected static long endSession = 0;
    protected static long endSessionParam = 0;
    protected static long endTimer = 0;
    protected static String hashkey = null;
    public static CommonHttpManager httpManager = null;
    public static boolean isOnGetApplink = false;
    public static boolean isTest = false;
    protected static Map<String, CommonActivityListener> listeners;
    protected static List<Pair<String, String>> localDemographicInfo;
    private static final Object lock = new Object();
    protected static String marketInfo = null;
    protected static RequestParameter parameter;
    protected static String prev_activity = BuildConfig.FLAVOR;
    protected static String prev_group = BuildConfig.FLAVOR;
    public static List<String> processedClickID = new ArrayList();
    protected static List<String> receiverComponents = new ArrayList();
    protected static List<JSONObject> restoreForNullContext = new ArrayList();
    protected static boolean security_enable = false;
    protected static int session_stack_count = 0;
    protected static boolean shouldSendCompleteCall = false;
    protected static long startSessionTime = 0;
    protected static boolean test_server_enable = false;
    protected static String thirdPartyID = null;
    String activity_info = BuildConfig.FLAVOR;
    private ICommonLiveOpsCallbackListener commonLiveOpsCallbackListener;
    private ReferrerThread mReferrerThread = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);

    static {
        GROUPS_FOR_TRACKING_INSTANTLY.add("fte");
        GROUPS_FOR_TRACKING_INSTANTLY.add("buy");
    }

    public static Context getContext() {
        return appContext;
    }

    public static void addActivityListener(String str, CommonActivityListener commonActivityListener) {
        if (listeners == null) {
            listeners = new HashMap();
        }
        if (!listeners.containsKey(str)) {
            listeners.put(str, commonActivityListener);
        }
    }

    public static Collection<CommonActivityListener> getActivityListener() {
        if (listeners == null) {
            return null;
        }
        return listeners.values();
    }

    public static void addExtendedActivityListener(String str, ExtendedCommonActivityListener extendedCommonActivityListener) {
        if (eListeners == null) {
            eListeners = new HashMap();
        }
        if (!eListeners.containsKey(str)) {
            eListeners.put(str, extendedCommonActivityListener);
        }
    }

    public static Collection<ExtendedCommonActivityListener> getExtendedActivityListener() {
        if (eListeners == null) {
            return null;
        }
        return eListeners.values();
    }

    protected CommonHttpManager getHttpManager(Context context) {
        initAppInfo(context);
        if (httpManager == null) {
            synchronized (lock) {
                if (httpManager == null) {
                    httpManager = new CommonHttpManager();
                }
            }
        }
        return httpManager;
    }

    public void initAppInfo(Context context) {
        try {
            if (appkey == null || hashkey == null) {
                if (AppImpressionDAO.getRequestPermisisonAlready(context)) {
                    CommonHelper.CheckandRequestPermissionForCommonSDK(context);
                } else {
                    CommonHelper.RequestPermissionForCommonSDK(context);
                    AppImpressionDAO.addRequestPermissionAlready(context);
                }
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo.metaData == null) {
                    throw new Exception("ADBrix SDK can not find meta-data tags required. Please check that meta-data tag is in the application tag.");
                }
                if (applicationInfo.metaData.containsKey("adbrix_app_key")) {
                    appkey = String.valueOf(applicationInfo.metaData.get("adbrix_app_key"));
                    if (applicationInfo.metaData.containsKey("adbrix_hash_key")) {
                        hashkey = String.valueOf(applicationInfo.metaData.get("adbrix_hash_key"));
                        if (applicationInfo.metaData.containsKey("adbrix_market_info")) {
                            marketInfo = String.valueOf(applicationInfo.metaData.get("adbrix_market_info"));
                        } else {
                            marketInfo = "google";
                        }
                    } else {
                        throw new Exception("ADBrix SDK can not find meta-data tag named 'adbrix_hash_key'. please check a menifest file and add 'adbrix_hash_key'. ");
                    }
                } else if (applicationInfo.metaData.containsKey("adPOPcorn_media_key")) {
                    appkey = (String) applicationInfo.metaData.get("adPOPcorn_media_key");
                    if (applicationInfo.metaData.containsKey("adPOPcorn_hash_key")) {
                        hashkey = (String) applicationInfo.metaData.get("adPOPcorn_hash_key");
                        if (applicationInfo.metaData.containsKey("adPOPcorn_market_info")) {
                            marketInfo = (String) applicationInfo.metaData.get("adPOPcorn_market_info");
                        } else {
                            marketInfo = "google";
                        }
                    } else {
                        throw new Exception("ADBrix SDK can not find meta-data tag named 'adPOPcorn_hash_key'. please check a menifest file and add 'adPOPcorn_hash_key'. ");
                    }
                } else if (applicationInfo.metaData.containsKey("igaworks_app_key")) {
                    appkey = String.valueOf(applicationInfo.metaData.get("igaworks_app_key"));
                    if (applicationInfo.metaData.containsKey("igaworks_hash_key")) {
                        hashkey = String.valueOf(applicationInfo.metaData.get("igaworks_hash_key"));
                        if (applicationInfo.metaData.containsKey("igaworks_market_info")) {
                            marketInfo = String.valueOf(applicationInfo.metaData.get("igaworks_market_info"));
                        } else {
                            marketInfo = "google";
                        }
                    } else {
                        throw new Exception("ADBrix SDK can not find meta-data tag named 'igaworks_hash_key'. please check a menifest file and add 'igaworks_hash_key'. ");
                    }
                } else {
                    throw new Exception("ADBrix SDK can not find meta-data tag named 'igaworks_app_key'. please check a menifest file and add 'igaworks_app_key'. ");
                }
                try {
                    if (applicationInfo.metaData.containsKey("igaworks_third_party_id")) {
                        thirdPartyID = String.valueOf(applicationInfo.metaData.get("igaworks_third_party_id"));
                    }
                } catch (Exception e) {
                }
                OpenUDID_manager.sync(context);
                synchronized (lock) {
                    session_stack_count = 0;
                }
                try {
                    boolean checkSelfPermission = CommonHelper.checkSelfPermission(context, "android.permission.INTERNET");
                    boolean checkSelfPermission2 = CommonHelper.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE");
                    boolean checkSelfPermission3 = CommonHelper.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE");
                    boolean checkSelfPermission4 = CommonHelper.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE");
                    checkSelfPermission = checkSelfPermission && checkSelfPermission2 && checkSelfPermission3 && checkSelfPermission4;
                    if (CommonFrameworkFactory.isHasAdbrixSDK) {
                        IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > adbrix version : " + IgawUpdateLog.getVersion(), 3, false);
                    } else {
                        IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > common only", 3, false);
                    }
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > common version : " + IgawUpdateLog.getCommonVersion(), 3, false);
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > appkey : " + appkey, 3, false);
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > marketInfo : " + marketInfo, 3, false);
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > thirdPartyInfo : " + thirdPartyID, 3, false);
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > set READ_EXTERNAL_STORAGE permission : " + checkSelfPermission3, 3, false);
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > set WRITE_EXTERNAL_STORAGE permission : " + checkSelfPermission4, 3, false);
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > set Adbrix Receiver : " + CommonHelper.checkReceiver(context), 3, false);
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > have all required permisison: " + checkSelfPermission, 3, false);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    Log.e("IGAW_QA", "Error: " + e2.getMessage().toString());
                }
            }
        } catch (Exception e22) {
            e22.printStackTrace();
            throw new RuntimeException(new Exception("ADBrix >> AndroidManifest.xml setting Error : Check AndroidManifest.xml file -> Are meta-data tags in application tag"));
        }
    }

    protected void activity(String str, String str2, String str3, String str4, Context context) {
        try {
            activityImpl(str, str2, str3, str4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void activityImpl(String str, String str2, String str3, String str4) {
        String str5 = BuildConfig.FLAVOR;
        Date time = Calendar.getInstance().getTime();
        String GetKSTServerTimeAsString = (str4 == null || str4.length() < 1) ? getContext() != null ? CommonHelper.GetKSTServerTimeAsString(getContext()) : CommonHelper.GetKSTCreateAtAsString() : str4;
        if (appContext == null) {
            if (!str.equals(String.VIDEO_ERROR)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("group", str);
                jSONObject.put("activity", str2);
                jSONObject.put(ApiAccessUtil.WEBAPI_KEY_PARAM, str3);
                jSONObject.put("createdAt", GetKSTServerTimeAsString);
                restoreForNullContext.add(jSONObject);
            }
            Log.e("IGAW_QA", "ADBrixManager > application context error, please check context value. startSession() should be called at least once.");
            return;
        }
        initAppInfo(appContext);
        IgawLogger.Logging(appContext, "IGAW_QA", "ADBrixManager > activity : " + prev_group + " / " + prev_activity + " / " + str + " / " + str2 + " / " + str3 + " / " + GetKSTServerTimeAsString, 3, false);
        try {
            this.activity_info = RequestParameter.convertActivityStringToJson(prev_group, prev_activity, str, str2, str3, GetKSTServerTimeAsString);
        } catch (Exception e) {
            if (str3 != null) {
                this.activity_info += "{\"prev_group\":" + "\"" + prev_group + "\"" + "," + "\"prev_activity\":" + "\"" + prev_activity + "\"," + "\"group\":" + "\"" + str + "\"" + "," + "\"activity\":" + "\"" + str2 + "\"" + "," + "\"param\":" + "\"" + str3 + "\"" + "," + "\"created_at\":" + "\"" + GetKSTServerTimeAsString + "\"}";
            } else {
                this.activity_info += "{\"prev_group\":" + "\"" + prev_group + "\"" + "," + "\"prev_activity\":" + "\"" + prev_activity + "\"," + "\"group\":" + "\"" + str + "\"" + "," + "\"activity\":" + "\"" + str2 + "\"" + "," + "\"param\":" + "\"\"" + "," + "\"created_at\":" + "\"" + GetKSTServerTimeAsString + "\"}";
            }
            IgawLogger.Logging(appContext, "IGAW_QA", "error occurred during create activity_info text : " + e.toString(), 0);
        }
        try {
            TrackingActivitySQLiteDB.getInstance(appContext).addTrackingActivityAsyn(time.getTime() + "_" + str + "_" + str2, this.activity_info);
            try {
                if (parameter.getappLaunchCount() < 1 && parameter.getReferralKey() < 1) {
                    ActivityInfoDAO.addActivityInfoForReferral(appContext, time.getTime() + "_" + str + "_" + str2, this.activity_info);
                }
            } catch (Exception e2) {
                IgawLogger.Logging(appContext, "IGAW_QA", "error occurred during add referralActivityForTracking in activity() : " + e2.toString() + " / " + e2.getMessage(), 0, false);
            }
            if (!(str.equals("session") && str2.equals("end"))) {
                prev_activity = str2;
                prev_group = str;
            }
            if (getActivityListener() != null) {
                for (CommonActivityListener onActivityCalled : getActivityListener()) {
                    onActivityCalled.onActivityCalled(appContext, str, str2, parameter);
                }
            }
            if (!GROUPS_FOR_TRACKING_INSTANTLY.contains(str)) {
                return;
            }
            if (!str.equals("session") || (!str2.equals(String.VIDEO_START) && !str2.equals("end"))) {
                if (CommonHelper.checkInternetConnection(getContext())) {
                    flush();
                } else {
                    Log.i("IGAW_QA", "Can not connect to Adbrix. No internet connection now");
                }
            }
        } catch (Exception e22) {
            e22.printStackTrace();
        }
    }

    public void startApplication(Context context) {
        try {
            appContext = context;
            IgawLogger.Logging(appContext, "IGAW_QA", "IgawCommon >> startApplication called.", 3, false);
            CoreIDDAO.getInstance().initialize(appContext);
            try {
                Class.forName("com.igaworks.adbrix.impl.ADBrixFrameworkFactory");
            } catch (Exception e) {
                IgawLogger.Logging(appContext, "IGAW_QA", "IgawCommon >> Common only sdk mode.", 3, false);
            }
            parameter = RequestParameter.getATRequestParameter(appContext);
            httpManager = getHttpManager(appContext);
            initAppInfo(appContext);
            parameter = RequestParameter.getATRequestParameter(appContext);
            parameter.setAppKey(appkey);
            parameter.setMc(appkey);
            parameter.setThirdPartyID(thirdPartyID);
            parameter.setActivityName(String.VIDEO_START);
            parameter.setMarketPlace(marketInfo);
            parameter.setSecurity_enable(security_enable);
            parameter.setHashkey(hashkey);
            aprt("start_application");
            InternalAction.getInstance().sendOphanActivities(context, isTest, httpManager);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void startSession(Context context) {
        try {
            Class.forName("com.igaworks.adbrix.impl.ADBrixFrameworkFactory");
        } catch (Exception e) {
            IgawLogger.Logging(context, "IGAW_QA", "IgawCommon >> Common only sdk mode.", 3, false);
        }
        try {
            appContext = context;
            RequestParameter.getATRequestParameter(getContext());
            initAppInfo(context);
            Task.forResult(null).continueWith(new Continuation<Object, Void>() {
                public Void then(Task<Object> task) {
                    Void voidR = null;
                    SharedPreferences sharedPreferences = CommonFrameworkImpl.appContext.getSharedPreferences("activityForTracking", 0);
                    Editor edit = sharedPreferences.edit();
                    if (voidR == null || voidR.size() < 1) {
                        Collection keySet = sharedPreferences.getAll().keySet();
                    } else {
                        Object obj = voidR;
                    }
                    if (!(r0 == null || r0.size() == 0)) {
                        IgawLogger.Logging(CommonFrameworkImpl.getContext(), "IGAW_QA", "Compat: Copy app tracking from SP to SQLite. Size:  " + r0.size(), 2, true);
                        for (String str : r0) {
                            String string = sharedPreferences.getString(str, voidR);
                            edit.remove(str);
                            if (!(string == null || string.equals(BuildConfig.FLAVOR))) {
                                TrackingActivitySQLiteDB.getInstance(CommonFrameworkImpl.getContext()).addTrackingActivityAsyn(str, string);
                            }
                        }
                        edit.apply();
                    }
                    SharedPreferences sharedPreferences2 = CommonFrameworkImpl.appContext.getSharedPreferences("promotion_impression_sp", 0);
                    Editor edit2 = sharedPreferences2.edit();
                    if (voidR == null || voidR.size() < 1) {
                        keySet = sharedPreferences2.getAll().keySet();
                    } else {
                        obj = voidR;
                    }
                    if (!(r0 == null || r0.size() == 0)) {
                        IgawLogger.Logging(CommonFrameworkImpl.getContext(), "IGAW_QA", "Compat: Copy impression from SP to SQLite. Size: " + r0.size(), 2, true);
                        for (String str2 : r0) {
                            String string2 = sharedPreferences2.getString(str2, voidR);
                            edit2.remove(str2);
                            if (!(string2 == null || string2.equals(BuildConfig.FLAVOR))) {
                                try {
                                    Boolean valueOf;
                                    String string3;
                                    JSONObject jSONObject = new JSONObject(string2);
                                    if (jSONObject.has("isFirstTime")) {
                                        valueOf = Boolean.valueOf(jSONObject.getBoolean("isFirstTime"));
                                    } else {
                                        Object obj2 = voidR;
                                    }
                                    if (jSONObject.has("conversion_key")) {
                                        string3 = jSONObject.getString("conversion_key");
                                    } else {
                                        Object obj3 = voidR;
                                    }
                                    IgawLogger.Logging(CommonFrameworkImpl.getContext(), "IGAW_QA", "IP_CAMPAIGN_KEY:" + jSONObject.getInt("campaign_key") + " IP_RESOURCE_KEY:" + jSONObject.getInt("resource_key") + " IP_SPACE_KEY:" + jSONObject.getString("space_key") + " IP_CREATED_AT:" + jSONObject.getString("created_at") + " isFirstTime: " + valueOf + " conversionKey:" + string3, 3, true);
                                    TrackingActivitySQLiteDB.getInstance(CommonFrameworkImpl.getContext()).setImpressionData(CommonFrameworkImpl.getContext(), jSONObject.getInt("campaign_key"), jSONObject.getInt("resource_key"), jSONObject.getString("space_key"), jSONObject.getString("created_at"), string3, valueOf);
                                } catch (JSONException e) {
                                    IgawLogger.Logging(CommonFrameworkImpl.getContext(), "IGAW_QA", "onStartSession: Impression Compat error: " + e.toString(), 0);
                                }
                            }
                        }
                        edit2.apply();
                    }
                    return voidR;
                }
            }, InternalAction.NETWORK_EXECUTOR);
            startSessionImpl();
            restoreActivityForNullContext(appContext);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void startSessionImpl() {
        long j = 0;
        try {
            boolean z;
            dailyRetentionEvent(appContext);
            httpManager = getHttpManager(appContext);
            IgawLogger.Logging(appContext, "IGAW_QA", "ADBrixManager > startSession() : stack_count :" + session_stack_count, 3, false);
            parameter = RequestParameter.getATRequestParameter(appContext);
            parameter.setAppKey(appkey);
            parameter.setMc(appkey);
            parameter.setThirdPartyID(thirdPartyID);
            parameter.setActivityName(String.VIDEO_START);
            parameter.setMarketPlace(marketInfo);
            parameter.setSecurity_enable(security_enable);
            parameter.setHashkey(hashkey);
            synchronized (lock) {
                if (session_stack_count >= 4) {
                    IgawLogger.Logging(appContext, "IGAW_QA", "ADBrixManager > startSession() : The startSession API is called continuously without endSession API. Please make sure that the startSession and endSession API is called as a pair of an activity unit", 0, false);
                    session_stack_count = 0;
                    endTimer = 0;
                }
            }
            if ((parameter.getReferralKey() == -1 || parameter.getADBrixUserNo() < 1 || ReferralInfoDAO.getOnReceiveReferralFlag(appContext)) && this.mReferrerThread == null) {
                this.mReferrerThread = new ReferrerThread();
                this.mReferrerThread.start();
            }
            if (session_stack_count != 0) {
                shouldSendCompleteCall = false;
                z = true;
            } else if (endTimer > 0) {
                j = SystemClock.elapsedRealtime() - endTimer;
                if (j <= ContinueSessionMillis) {
                    shouldSendCompleteCall = false;
                    z = true;
                } else {
                    shouldSendCompleteCall = true;
                    startSessionTime = SystemClock.elapsedRealtime();
                    z = false;
                }
            } else {
                shouldSendCompleteCall = true;
                startSessionTime = SystemClock.elapsedRealtime();
                z = false;
            }
            if (shouldSendCompleteCall) {
                parameter.increaseAppLaunchCount();
            }
            if (getActivityListener() != null) {
                for (CommonActivityListener onStartSession : getActivityListener()) {
                    onStartSession.onStartSession(appContext, parameter, z);
                }
            }
            if (!(parameter == null || parameter.getADBrixUserNo() == -1)) {
                InternalAction.getInstance().trackingForAdbrixCall(getContext(), isTest, httpManager, "session", String.VIDEO_START, j);
            }
            endTimer = 0;
            synchronized (lock) {
                session_stack_count++;
            }
            if (!z) {
                prev_activity = BuildConfig.FLAVOR;
                prev_group = BuildConfig.FLAVOR;
                activity("session", String.VIDEO_START, null, null, appContext);
            }
            deeplinkConversion(appContext, parameter, z);
            if (parameter != null) {
                IgawLogger.Logging(appContext, "IGAW_QA", String.format("channel_type : %d", new Object[]{Integer.valueOf(parameter.getChannelType())}), 3, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endSession() {
        try {
            endSessionImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void endSessionImpl() {
        try {
            if (appContext == null) {
                Log.e("IGAW_QA", "ADBrixManager > application context error, please check context value. startSession() should be called at least once.");
                return;
            }
            long j;
            IgawLogger.Logging(appContext, "IGAW_QA", "endSession : statck_count : " + session_stack_count, 3, false);
            httpManager = getHttpManager(appContext);
            endSession = SystemClock.elapsedRealtime();
            synchronized (lock) {
                if (session_stack_count > 0) {
                    session_stack_count--;
                }
                if (session_stack_count == 0) {
                    endTimer = endSession;
                    long j2 = endSession - startSessionTime;
                    endSessionParam = j2;
                    j = j2;
                } else {
                    endTimer = 0;
                    j = 0;
                }
            }
            if (!(session_stack_count != 0 || parameter == null || parameter.getADBrixUserNo() == -1)) {
                InternalAction.getInstance().trackingForAdbrixCall(getContext(), isTest, httpManager, "session", "end", 0);
            }
            Task.forResult(null).continueWith(new Continuation<Void, Void>() {
                public Void then(Task<Void> task) {
                    if (CommonHelper.checkInternetConnection(CommonFrameworkImpl.getContext()) || CommonFrameworkImpl.isTest) {
                        SharedPreferences sharedPreferences = CommonFrameworkImpl.appContext.getSharedPreferences("demoForTracking", 0);
                        if (CommonFrameworkImpl.localDemographicInfo == null || CommonFrameworkImpl.localDemographicInfo.size() <= 0) {
                            List demoInfo = RequestParameter.getATRequestParameter(CommonFrameworkImpl.getContext()).getDemoInfo();
                            if (demoInfo != null && demoInfo.size() > 0) {
                                CommonFrameworkImpl.httpManager.demographicCallForADBrix(CommonFrameworkImpl.parameter, CommonFrameworkImpl.appContext);
                            }
                        } else {
                            Editor edit = sharedPreferences.edit();
                            for (int i = 0; i < CommonFrameworkImpl.localDemographicInfo.size(); i++) {
                                Pair pair = (Pair) CommonFrameworkImpl.localDemographicInfo.get(i);
                                edit.putString((String) pair.first, (String) pair.second);
                            }
                            edit.apply();
                            try {
                                TaskUtils.wait(Task.delay(500));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            CommonFrameworkImpl.localDemographicInfo.clear();
                        }
                    }
                    return null;
                }
            }, InternalAction.NETWORK_EXECUTOR);
            if (session_stack_count == 0) {
                activity("session", "end", Long.toString(j), null, appContext);
            }
            if (this.commonLiveOpsCallbackListener != null) {
                this.commonLiveOpsCallbackListener.onEndSession(appContext);
            }
            if (getExtendedActivityListener() != null) {
                for (ExtendedCommonActivityListener onEndSession : getExtendedActivityListener()) {
                    onEndSession.onEndSession(appContext, session_stack_count);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void flush() {
        try {
            if (appContext == null) {
                Log.e("IGAW_QA", "ADBrixManager > application context error, please check context value. startSession() should be called at least once.");
                return;
            }
            IgawLogger.Logging(appContext, "IGAW_QA", "ADBrixManager > flush started", 3, false);
            httpManager = getHttpManager(getContext());
            InternalAction.getInstance().trackingForAdbrixCall(getContext(), isTest, httpManager, "n/a", "n/a", 0);
        } catch (Exception e) {
            Log.e("IGAW_QA", "FLUSH ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void aprt(String str) {
        activity("ret", str, null, null, appContext);
    }

    public void onReceiveReferral(final Context context, String str) {
        try {
            if (appContext == null) {
                IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > application context error, please check context value. startSession() should be called at least once.", 0, false);
                return;
            }
            initAppInfo(context);
            if (parameter == null) {
                parameter = RequestParameter.getATRequestParameter(appContext);
            }
            if (parameter.getReferralKey() < 1 || parameter.getADBrixUserNo() < 1) {
                new Thread(new Runnable() {
                    public void run() {
                        CommonFrameworkImpl.this.getHttpManager(CommonFrameworkImpl.appContext).CPI_referrerCallForADBrix(CommonFrameworkImpl.parameter, CommonFrameworkImpl.appContext, ActivityInfoDAO.getActivityInfoForReferral(context));
                    }
                }).start();
            }
            navigateDeeplinkActivity(context, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void navigateDeeplinkActivity(Context context, String str) {
        if (str != null) {
            String[] split;
            IgawLogger.Logging(context, "IGAW_QA", "deeplink referral param : " + str, 2, true);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
            for (String str2 : str.split("&")) {
                IgawLogger.Logging(context, "IGAW_QA", "deeplink splitted param : " + str2, 2, true);
                split = str2.split(Operation.EQUALS);
                if (split.length == 2) {
                    if (split[0].equals("igaw_intent")) {
                        try {
                            intent.setData(Uri.parse(Uri.decode(split[1])));
                            IgawLogger.Logging(context, "IGAW_QA", "deeplink data : " + intent.getDataString(), 2, true);
                        } catch (Exception e) {
                            try {
                                IgawLogger.Logging(context, "IGAW_QA", "deeplink data error : " + e.toString(), 0, true);
                            } catch (Exception e2) {
                                try {
                                    e2.printStackTrace();
                                    return;
                                } catch (Exception e22) {
                                    e22.printStackTrace();
                                    return;
                                }
                            }
                        }
                    }
                    intent.putExtra(split[0], split[1]);
                }
            }
            if (receiverComponents != null && receiverComponents.size() > 0) {
                IgawLogger.Logging(context, "IGAW_QA", "deeplink receiverComponents size : " + receiverComponents.size(), 2, true);
                for (String str22 : receiverComponents) {
                    IgawLogger.Logging(context, "IGAW_QA", String.format("deeplink intent broadcasting : %s << %s", new Object[]{str22, str}), 2, false);
                    split = str22.split(";");
                    if (split != null && split.length == 2) {
                        intent.setComponent(new ComponentName(split[0], split[1]));
                        appContext.startActivity(intent);
                    }
                }
            } else if (intent.getData() != null) {
                IgawLogger.Logging(context, "IGAW_QA", "deeplink intent broadcasting", 2, true);
                if (appContext instanceof Activity) {
                    ((Activity) appContext).startActivity(intent);
                } else {
                    appContext.startActivity(intent);
                }
            }
        }
    }

    private void restoreActivityForNullContext(Context context) {
        try {
            if (restoreForNullContext != null && restoreForNullContext.size() > 0) {
                for (JSONObject jSONObject : restoreForNullContext) {
                    String string;
                    String string2;
                    String string3;
                    String string4;
                    if (jSONObject.has("group")) {
                        string = jSONObject.getString("group");
                    } else {
                        string = null;
                    }
                    if (jSONObject.has("activity")) {
                        string2 = jSONObject.getString("activity");
                    } else {
                        string2 = null;
                    }
                    if (jSONObject.has(ApiAccessUtil.WEBAPI_KEY_PARAM)) {
                        string3 = jSONObject.getString(ApiAccessUtil.WEBAPI_KEY_PARAM);
                    } else {
                        string3 = null;
                    }
                    if (jSONObject.has("createdAt")) {
                        string4 = jSONObject.getString("createdAt");
                    } else {
                        string4 = null;
                    }
                    activity(string, string2, string3, string4, context);
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager >restore activity for null context : " + string + " / " + string2 + " / " + string3 + Operation.DIVISION + string4, 3, false);
                }
                restoreForNullContext.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deeplinkConversion(final Context context, final RequestParameter requestParameter, final boolean z) {
        Task.forResult(null).continueWith(new Continuation<Object, Void>() {
            public Void then(Task<Object> task) {
                List retryConversions;
                int parseInt;
                try {
                    Intent intent;
                    Uri data;
                    Bundle extras;
                    if (!z) {
                        try {
                            retryConversions = DeeplinkConversionRetryDAO.getDAO(context).getRetryConversions();
                        } catch (Exception e) {
                            IgawLogger.Logging(context, "IGAW_QA", "Retry conversion error : " + e.toString(), 0, false);
                        }
                        if (context instanceof Activity) {
                            IgawLogger.Logging(CommonFrameworkImpl.getContext(), "IGAW_QA", "Context is not Activity Context", 2, false);
                            return null;
                        }
                        intent = ((Activity) context).getIntent();
                        if (intent != null) {
                            data = intent.getData();
                            extras = intent.getExtras();
                            if (!(data == null && data.toString().contains(Operation.EMPTY_PARAM)) && (extras == null || extras.size() < 1)) {
                                IgawLogger.Logging(context, "IGAW_QA", "deeplink onstartsession bundle and data are null", 2, false);
                            } else {
                                Uri parse;
                                boolean z;
                                String str = BuildConfig.FLAVOR;
                                if (data == null || !data.toString().contains(Operation.EMPTY_PARAM)) {
                                    try {
                                        String str2 = str;
                                        for (String str3 : extras.keySet()) {
                                            if (extras.get(str3) != null) {
                                                str2 = new StringBuilder(String.valueOf(str2)).append(str3).append(Operation.EQUALS).append(extras.get(str3).toString()).append("&").toString();
                                            }
                                        }
                                        if (str2.length() > 0) {
                                            str3 = "http://igaworks.com?" + str2.substring(0, str2.length() - 1);
                                            IgawLogger.Logging(context, "IGAW_QA", "uriStr from bundle: " + str3, 3, true);
                                            try {
                                                parse = Uri.parse(str3);
                                            } catch (Exception e2) {
                                                parse = null;
                                            }
                                        } else {
                                            parse = data;
                                        }
                                    } catch (Exception e3) {
                                        Log.d("IGAW_QA", "@deeplinkConversion >> Bundle Parse Error: " + e3.getMessage().toString());
                                        str3 = BuildConfig.FLAVOR;
                                        parse = null;
                                    }
                                } else {
                                    str3 = data.toString();
                                    IgawLogger.Logging(context, "IGAW_QA", "uriStr from intent data: " + str3, 3, true);
                                    try {
                                        parse = Uri.parse("http://igaworks.com" + str3.substring(str3.indexOf(63), str3.length()));
                                    } catch (Exception e4) {
                                        parse = null;
                                    }
                                }
                                Context context = context;
                                String str4 = "IGAW_QA";
                                StringBuilder stringBuilder = new StringBuilder("deeplink onstartsession >> iUri is null >>");
                                if (parse == null) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                IgawLogger.Logging(context, str4, stringBuilder.append(z).toString(), 2, false);
                                if (parse != null) {
                                    String queryParameter;
                                    int i;
                                    List list;
                                    try {
                                        parseInt = Integer.parseInt(parse.getQueryParameter("ck"));
                                        try {
                                            queryParameter = parse.getQueryParameter("cid");
                                            i = parseInt;
                                        } catch (Exception e5) {
                                            i = parseInt;
                                            queryParameter = null;
                                            IgawLogger.Logging(context, "IGAW_QA", "deeplink >> onStartSession bundle extra : " + parse.getQuery(), 2, true);
                                            parse.getQueryParameter("iga_web_id");
                                            if (CommonFrameworkImpl.processedClickID.contains(new StringBuilder(String.valueOf(i)).append(";").append(queryParameter).toString())) {
                                                if (queryParameter != null) {
                                                }
                                                list = retryConversions;
                                                CommonFrameworkImpl.processedClickID.add(new StringBuilder(String.valueOf(i)).append(";").append(queryParameter).toString());
                                            } else {
                                                list = retryConversions;
                                            }
                                            IgawLogger.Logging(context, "IGAW_QA", "deeplink Conversion - count : " + list.size(), 3, true);
                                            CommonFrameworkImpl.httpManager.conversionForDeeplink(requestParameter, context, list);
                                            return null;
                                        }
                                    } catch (Exception e6) {
                                        parseInt = -1;
                                        i = parseInt;
                                        queryParameter = null;
                                        IgawLogger.Logging(context, "IGAW_QA", "deeplink >> onStartSession bundle extra : " + parse.getQuery(), 2, true);
                                        parse.getQueryParameter("iga_web_id");
                                        if (CommonFrameworkImpl.processedClickID.contains(new StringBuilder(String.valueOf(i)).append(";").append(queryParameter).toString())) {
                                            if (queryParameter != null) {
                                            }
                                            list = retryConversions;
                                            CommonFrameworkImpl.processedClickID.add(new StringBuilder(String.valueOf(i)).append(";").append(queryParameter).toString());
                                        } else {
                                            list = retryConversions;
                                        }
                                        IgawLogger.Logging(context, "IGAW_QA", "deeplink Conversion - count : " + list.size(), 3, true);
                                        CommonFrameworkImpl.httpManager.conversionForDeeplink(requestParameter, context, list);
                                        return null;
                                    }
                                    IgawLogger.Logging(context, "IGAW_QA", "deeplink >> onStartSession bundle extra : " + parse.getQuery(), 2, true);
                                    parse.getQueryParameter("iga_web_id");
                                    if (CommonFrameworkImpl.processedClickID.contains(new StringBuilder(String.valueOf(i)).append(";").append(queryParameter).toString())) {
                                        if (queryParameter != null || queryParameter.length() <= 0 || i <= -1) {
                                            list = retryConversions;
                                        } else {
                                            DeeplinkConversionItem deeplinkConversionItem = new DeeplinkConversionItem(-1, i, queryParameter, parse.getQuery(), 0);
                                            if (retryConversions == null) {
                                                retryConversions = new ArrayList();
                                            }
                                            retryConversions.add(deeplinkConversionItem);
                                            list = retryConversions;
                                        }
                                        CommonFrameworkImpl.processedClickID.add(new StringBuilder(String.valueOf(i)).append(";").append(queryParameter).toString());
                                    } else {
                                        list = retryConversions;
                                    }
                                    if (list != null && list.size() > 0) {
                                        IgawLogger.Logging(context, "IGAW_QA", "deeplink Conversion - count : " + list.size(), 3, true);
                                        CommonFrameworkImpl.httpManager.conversionForDeeplink(requestParameter, context, list);
                                    }
                                }
                            }
                        }
                        return null;
                    }
                    retryConversions = null;
                    try {
                        if (context instanceof Activity) {
                            IgawLogger.Logging(CommonFrameworkImpl.getContext(), "IGAW_QA", "Context is not Activity Context", 2, false);
                            return null;
                        }
                        intent = ((Activity) context).getIntent();
                        if (intent != null) {
                            data = intent.getData();
                            extras = intent.getExtras();
                            if (data == null) {
                            }
                            IgawLogger.Logging(context, "IGAW_QA", "deeplink onstartsession bundle and data are null", 2, false);
                        }
                        return null;
                    } catch (Exception e32) {
                        IgawLogger.Logging(context, "IGAW_QA", "@deeplinkConversion >> Igaworks Deeplink Parse Error: " + e32.toString(), 0, false);
                    }
                } catch (Exception e322) {
                    IgawLogger.Logging(context, "IGAW_QA", "@deeplinkConversion Error: " + e322.toString(), 0, false);
                }
            }
        }, Task.BACKGROUND_EXECUTOR);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void dailyRetentionEvent(android.content.Context r7) {
        /*
        r6 = this;
        r0 = com.igaworks.dao.AppImpressionDAO.getLastDailyRentionDate(r7);
        r1 = "";
        r1 = r0.equals(r1);	 Catch:{ Exception -> 0x0050 }
        if (r1 != 0) goto L_0x0043;
    L_0x000c:
        r1 = java.util.Calendar.getInstance();	 Catch:{ Exception -> 0x0050 }
        r2 = 6;
        r3 = -1;
        r1.add(r2, r3);	 Catch:{ Exception -> 0x0050 }
        r2 = com.igaworks.dao.AdbrixDB_v2.DB_DATE_FORMAT;	 Catch:{ Exception -> 0x0050 }
        r1 = r1.getTime();	 Catch:{ Exception -> 0x0050 }
        r1 = r2.format(r1);	 Catch:{ Exception -> 0x0050 }
        r2 = 0;
        r3 = 10;
        r1 = r1.substring(r2, r3);	 Catch:{ Exception -> 0x0050 }
        r2 = 0;
        r3 = 10;
        r0 = r0.substring(r2, r3);	 Catch:{ Exception -> 0x0050 }
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x0050 }
        if (r0 == 0) goto L_0x003f;
    L_0x0033:
        r1 = "session";
        r2 = "retention";
        r3 = 0;
        r4 = 0;
        r5 = appContext;	 Catch:{ Exception -> 0x0050 }
        r0 = r6;
        r0.activity(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0050 }
    L_0x003f:
        com.igaworks.dao.AppImpressionDAO.setLastDailyRentionDate(r7);
    L_0x0042:
        return;
    L_0x0043:
        r1 = "session";
        r2 = "retention";
        r3 = 0;
        r4 = 0;
        r5 = appContext;	 Catch:{ Exception -> 0x0050 }
        r0 = r6;
        r0.activity(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0050 }
        goto L_0x003f;
    L_0x0050:
        r0 = move-exception;
        r1 = "IGAW_QA";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x006d }
        r3 = "dailyRetentionEvent Error: ";
        r2.<init>(r3);	 Catch:{ all -> 0x006d }
        r0 = r0.getMessage();	 Catch:{ all -> 0x006d }
        r0 = r2.append(r0);	 Catch:{ all -> 0x006d }
        r0 = r0.toString();	 Catch:{ all -> 0x006d }
        android.util.Log.e(r1, r0);	 Catch:{ all -> 0x006d }
        com.igaworks.dao.AppImpressionDAO.setLastDailyRentionDate(r7);
        goto L_0x0042;
    L_0x006d:
        r0 = move-exception;
        com.igaworks.dao.AppImpressionDAO.setLastDailyRentionDate(r7);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igaworks.impl.CommonFrameworkImpl.dailyRetentionEvent(android.content.Context):void");
    }
}
