package com.igaworks.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.igaworks.core.AESGetTrackParam;
import com.igaworks.core.AdvertisingIdClient.ADIDCallbackListener;
import com.igaworks.core.AdvertisingIdClient.AdInfo;
import com.igaworks.core.DeviceIDManger;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.dao.ActivityInfoDAO;
import com.igaworks.dao.AppImpressionDAO;
import com.igaworks.dao.DeeplinkConversionRetryDAO;
import com.igaworks.dao.LocalDemograhpicDAO;
import com.igaworks.dao.ReferralInfoDAO;
import com.igaworks.dao.tracking.TrackingActivityModel;
import com.igaworks.dao.tracking.TrackingActivitySQLiteDB;
import com.igaworks.impl.CommonFrameworkImpl;
import com.igaworks.impl.InternalAction;
import com.igaworks.interfaces.CommonActivityListener;
import com.igaworks.interfaces.HttpCallbackListener;
import com.igaworks.model.DeeplinkConversionItem;
import com.igaworks.util.bolts_task.Continuation;
import com.igaworks.util.bolts_task.Task;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonHttpManager extends HttpManager {
    private static boolean onCPIReferrerCall = false;
    private static boolean onFetchDefererLink = false;
    private static boolean onReferrerCall = false;

    public void normal_referrerCallForADBrix(final RequestParameter requestParameter, final Context context, final ArrayList<String> arrayList) {
        if (getOnReferrerCall()) {
            IgawLogger.Logging(context, "IGAW_QA", "referrerCallForADBrix > referral call already sent.", 3);
            return;
        }
        setOnReferrerCall(true);
        try {
            DeviceIDManger.getInstance(context).getAndroidADID(context, new ADIDCallbackListener() {
                public void onResult(AdInfo adInfo) {
                    String str = CommonHttpManager.this.REFERRER_REQUEST_URL_FOR_ADBrix;
                    String str2 = BuildConfig.FLAVOR;
                    try {
                        boolean z;
                        IgawLogger.Logging(context, "IGAW_QA", "normal referrerCallForADBrix", 3, false);
                        RequestParameter requestParameter = requestParameter;
                        Context context = context;
                        ArrayList arrayList = arrayList;
                        String id = adInfo == null ? BuildConfig.FLAVOR : adInfo.getId();
                        if (adInfo == null) {
                            z = false;
                        } else {
                            z = adInfo.isLimitAdTrackingEnabled();
                        }
                        str2 = AESGetTrackParam.encrypt_hashkey(requestParameter.getReferrerTrackingParameter(context, arrayList, null, id, z), requestParameter.getHashkey());
                        if (requestParameter.getReferralKey() < 1) {
                            IgawLogger.Logging(context, "IGAW_QA", "normal referrerCallForADBrix > referral call send.", 3, false);
                            HashMap hashMap = new HashMap();
                            hashMap.put("k", new StringBuilder(String.valueOf(requestParameter.getAppkey())).toString());
                            hashMap.put("j", str2);
                            context = context;
                            final Context context2 = context;
                            final ArrayList arrayList2 = arrayList;
                            WeakReference weakReference = new WeakReference(new HttpUrlConnectionThread(context, 1, str, hashMap, new HttpCallbackListener() {
                                public void callback(String str) {
                                    if (str == null) {
                                        ActivityInfoDAO.restoreReferralTrackingInfo(context2, arrayList2);
                                        IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, responseResult null Error", 3, false);
                                        CommonHttpManager.this.setOnReferrerCall(false);
                                        return;
                                    }
                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, getReferral response String : " + str, 3, false);
                                    RequestParameter aTRequestParameter = RequestParameter.getATRequestParameter(context2);
                                    JSONObject jSONObject = new JSONObject(str);
                                    try {
                                        if (jSONObject.has("BaseTime")) {
                                            AppImpressionDAO.setServerBaseTimeOffset(context2, jSONObject.getLong("BaseTime") - System.currentTimeMillis());
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        if (jSONObject.getBoolean("Result")) {
                                            if (!jSONObject.isNull("Data")) {
                                                int i;
                                                String string;
                                                jSONObject = new JSONObject(jSONObject.getString("Data"));
                                                JSONArray jSONArray = new JSONArray(jSONObject.getString("conversion_key_list"));
                                                for (i = 0; i < jSONArray.length(); i++) {
                                                    int i2 = jSONArray.getInt(i);
                                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > key : " + i2, 3, false);
                                                    if (i2 != -1 && (aTRequestParameter.getConversionCache() == null || !aTRequestParameter.getConversionCache().contains(Integer.valueOf(i2)))) {
                                                        aTRequestParameter.setConversionCache(i2);
                                                    }
                                                }
                                                long j = jSONObject.getLong("referralKey");
                                                if (!jSONObject.has("channel_type") || jSONObject.isNull("channel_type")) {
                                                    i = -1;
                                                } else {
                                                    i = jSONObject.getInt("channel_type");
                                                }
                                                IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > referralKey : " + j, 3, false);
                                                if (j != -1) {
                                                    aTRequestParameter.setADBrixUserInfo_ReferralKey(j);
                                                }
                                                if (jSONObject.has("subreferralKey")) {
                                                    string = jSONObject.getString("subreferralKey");
                                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > subreferralKey : " + string, 3, false);
                                                    aTRequestParameter.setADBrixUserInfo_SubReferralKey(string);
                                                }
                                                if (jSONObject.has("refusn") && !jSONObject.isNull("refusn")) {
                                                    string = jSONObject.getString("refusn");
                                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > refusn : " + string, 3, false);
                                                    aTRequestParameter.setADBrixUserInfo_Refusn(string);
                                                }
                                                if (jSONObject.has("shard_no") && !jSONObject.isNull("shard_no")) {
                                                    int i3 = jSONObject.getInt("shard_no");
                                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > shard_no : " + i3, 3, false);
                                                    aTRequestParameter.setADBrixUserInfo_ShardNo(i3);
                                                }
                                                if (jSONObject.has("install_datetime") && !jSONObject.isNull("install_datetime")) {
                                                    string = jSONObject.getString("install_datetime");
                                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > install_datetime : " + string, 3, false);
                                                    aTRequestParameter.setADBrixUserInfo_install_datetime(string);
                                                }
                                                if (i != -1) {
                                                    aTRequestParameter.setChannelType(i);
                                                }
                                                long j2 = jSONObject.getLong("user_no");
                                                IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > adbrix_user_no : " + j2, 3, false);
                                                aTRequestParameter.setADBrixUserInfo(j2, System.currentTimeMillis());
                                            }
                                            if (CommonFrameworkImpl.getActivityListener() != null) {
                                                for (CommonActivityListener onGetReferralResponse : CommonFrameworkImpl.getActivityListener()) {
                                                    onGetReferralResponse.onGetReferralResponse(context2, str);
                                                }
                                            }
                                            if (CommonFrameworkImpl.httpManager == null) {
                                                CommonFrameworkImpl.httpManager = new CommonHttpManager();
                                            }
                                            InternalAction.getInstance().trackingForAdbrixCall(context2, CommonFrameworkImpl.isTest, CommonFrameworkImpl.httpManager, "n/a", "n/a", 0);
                                        } else {
                                            ActivityInfoDAO.restoreReferralTrackingInfo(context2, arrayList2);
                                            IgawLogger.Logging(context2, "IGAW_QA", "callbackReferrerADBrix result false", 0, false);
                                        }
                                        CommonHttpManager.this.setOnReferrerCall(false);
                                    } catch (Exception e2) {
                                        ActivityInfoDAO.restoreReferralTrackingInfo(context2, arrayList2);
                                        if (e2 != null) {
                                            e2.printStackTrace();
                                            IgawLogger.Logging(context2, "IGAW_QA", e2.toString(), 0);
                                        }
                                        CommonHttpManager.this.setOnReferrerCall(false);
                                    } catch (Throwable th) {
                                        CommonHttpManager.this.setOnReferrerCall(false);
                                    }
                                }
                            }));
                            ((Thread) weakReference.get()).setDaemon(true);
                            ((Thread) weakReference.get()).start();
                            return;
                        }
                        IgawLogger.Logging(context, "IGAW_QA", "ADBrixTracer > referrerCallForADBrix() : referral call info already saved.", 3, false);
                        CommonHttpManager.this.setOnReferrerCall(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                        IgawLogger.Logging(context, "IGAW_QA", e.getMessage(), 0);
                        CommonHttpManager.this.setOnReferrerCall(false);
                        ActivityInfoDAO.restoreReferralTrackingInfo(context, arrayList);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            IgawLogger.Logging(context, "IGAW_QA", e.getMessage(), 0);
            setOnReferrerCall(false);
            ActivityInfoDAO.restoreReferralTrackingInfo(context, arrayList);
        }
    }

    public void CPI_referrerCallForADBrix(final RequestParameter requestParameter, final Context context, final ArrayList<String> arrayList) {
        setOnReferrerCall(true);
        if (getOnCPIReferrerCall()) {
            IgawLogger.Logging(context, "IGAW_QA", "CPI referrerCallForADBrix > referral call already sent.", 3);
            return;
        }
        setOnCPIReferrerCall(true);
        try {
            DeviceIDManger.getInstance(context).getAndroidADID(context, new ADIDCallbackListener() {
                public void onResult(AdInfo adInfo) {
                    String str = CommonHttpManager.this.REFERRER_REQUEST_URL_FOR_ADBrix;
                    String str2 = BuildConfig.FLAVOR;
                    try {
                        boolean z;
                        IgawLogger.Logging(context, "IGAW_QA", "CPI referrerCallForADBrix", 3, false);
                        RequestParameter requestParameter = requestParameter;
                        Context context = context;
                        ArrayList arrayList = arrayList;
                        String id = adInfo == null ? BuildConfig.FLAVOR : adInfo.getId();
                        if (adInfo == null) {
                            z = false;
                        } else {
                            z = adInfo.isLimitAdTrackingEnabled();
                        }
                        str2 = AESGetTrackParam.encrypt_hashkey(requestParameter.getReferrerTrackingParameter(context, arrayList, null, id, z), requestParameter.getHashkey());
                        if (requestParameter.getReferralKey() < 1) {
                            IgawLogger.Logging(context, "IGAW_QA", "CPI referrerCallForADBrix > referral call send.", 3, false);
                            HashMap hashMap = new HashMap();
                            hashMap.put("k", new StringBuilder(String.valueOf(requestParameter.getAppkey())).toString());
                            hashMap.put("j", str2);
                            context = context;
                            final Context context2 = context;
                            final ArrayList arrayList2 = arrayList;
                            WeakReference weakReference = new WeakReference(new HttpUrlConnectionThread(context, 1, str, hashMap, new HttpCallbackListener() {
                                public void callback(String str) {
                                    if (str == null) {
                                        ActivityInfoDAO.restoreReferralTrackingInfo(context2, arrayList2);
                                        IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, responseResult null Error", 3, false);
                                        CommonHttpManager.this.setOnReferrerCall(false);
                                        CommonHttpManager.this.setOnCPIReferrerCall(false);
                                        return;
                                    }
                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, getReferral response String : " + str, 3, false);
                                    RequestParameter aTRequestParameter = RequestParameter.getATRequestParameter(context2);
                                    JSONObject jSONObject = new JSONObject(str);
                                    try {
                                        if (jSONObject.has("BaseTime")) {
                                            AppImpressionDAO.setServerBaseTimeOffset(context2, jSONObject.getLong("BaseTime") - System.currentTimeMillis());
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        if (jSONObject.getBoolean("Result")) {
                                            if (!jSONObject.isNull("Data")) {
                                                int i;
                                                String string;
                                                jSONObject = new JSONObject(jSONObject.getString("Data"));
                                                JSONArray jSONArray = new JSONArray(jSONObject.getString("conversion_key_list"));
                                                for (i = 0; i < jSONArray.length(); i++) {
                                                    int i2 = jSONArray.getInt(i);
                                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > key : " + i2, 3, false);
                                                    if (i2 != -1 && (aTRequestParameter.getConversionCache() == null || !aTRequestParameter.getConversionCache().contains(Integer.valueOf(i2)))) {
                                                        aTRequestParameter.setConversionCache(i2);
                                                    }
                                                }
                                                long j = jSONObject.getLong("referralKey");
                                                if (!jSONObject.has("channel_type") || jSONObject.isNull("channel_type")) {
                                                    i = -1;
                                                } else {
                                                    i = jSONObject.getInt("channel_type");
                                                }
                                                IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > referralKey : " + j, 3, false);
                                                if (j != -1) {
                                                    aTRequestParameter.setADBrixUserInfo_ReferralKey(j);
                                                }
                                                if (jSONObject.has("subreferralKey")) {
                                                    string = jSONObject.getString("subreferralKey");
                                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > subreferralKey : " + string, 3, false);
                                                    aTRequestParameter.setADBrixUserInfo_SubReferralKey(string);
                                                }
                                                if (jSONObject.has("refusn") && !jSONObject.isNull("refusn")) {
                                                    string = jSONObject.getString("refusn");
                                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > refusn : " + string, 3, false);
                                                    aTRequestParameter.setADBrixUserInfo_Refusn(string);
                                                }
                                                if (jSONObject.has("shard_no") && !jSONObject.isNull("shard_no")) {
                                                    int i3 = jSONObject.getInt("shard_no");
                                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > shard_no : " + i3, 3, false);
                                                    aTRequestParameter.setADBrixUserInfo_ShardNo(i3);
                                                }
                                                if (jSONObject.has("install_datetime") && !jSONObject.isNull("install_datetime")) {
                                                    string = jSONObject.getString("install_datetime");
                                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > install_datetime : " + string, 3, false);
                                                    aTRequestParameter.setADBrixUserInfo_install_datetime(string);
                                                }
                                                if (i != -1) {
                                                    aTRequestParameter.setChannelType(i);
                                                }
                                                long j2 = jSONObject.getLong("user_no");
                                                IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > adbrix_user_no : " + j2, 3, false);
                                                aTRequestParameter.setADBrixUserInfo(j2, System.currentTimeMillis());
                                            }
                                            ReferralInfoDAO.clearOnReceiveReferralFlag(context2);
                                            if (CommonFrameworkImpl.getActivityListener() != null) {
                                                for (CommonActivityListener onGetReferralResponse : CommonFrameworkImpl.getActivityListener()) {
                                                    onGetReferralResponse.onGetReferralResponse(context2, str);
                                                }
                                            }
                                            if (CommonFrameworkImpl.httpManager == null) {
                                                CommonFrameworkImpl.httpManager = new CommonHttpManager();
                                            }
                                            InternalAction.getInstance().trackingForAdbrixCall(context2, CommonFrameworkImpl.isTest, CommonFrameworkImpl.httpManager, "n/a", "n/a", 0);
                                        } else {
                                            ActivityInfoDAO.restoreReferralTrackingInfo(context2, arrayList2);
                                            IgawLogger.Logging(context2, "IGAW_QA", "callbackReferrerADBrix result false", 0, false);
                                        }
                                        CommonHttpManager.this.setOnReferrerCall(false);
                                        CommonHttpManager.this.setOnCPIReferrerCall(false);
                                    } catch (Exception e2) {
                                        ActivityInfoDAO.restoreReferralTrackingInfo(context2, arrayList2);
                                        if (e2 != null) {
                                            e2.printStackTrace();
                                            IgawLogger.Logging(context2, "IGAW_QA", e2.toString(), 0);
                                        }
                                        CommonHttpManager.this.setOnReferrerCall(false);
                                        CommonHttpManager.this.setOnCPIReferrerCall(false);
                                    } catch (Throwable th) {
                                        CommonHttpManager.this.setOnReferrerCall(false);
                                        CommonHttpManager.this.setOnCPIReferrerCall(false);
                                    }
                                }
                            }));
                            ((Thread) weakReference.get()).setDaemon(true);
                            ((Thread) weakReference.get()).start();
                            return;
                        }
                        IgawLogger.Logging(context, "IGAW_QA", "ADBrixTracer > referrerCallForADBrix() : referral call info already saved.", 3, false);
                        CommonHttpManager.this.setOnReferrerCall(false);
                        CommonHttpManager.this.setOnCPIReferrerCall(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                        IgawLogger.Logging(context, "IGAW_QA", e.getMessage(), 0);
                        CommonHttpManager.this.setOnReferrerCall(false);
                        CommonHttpManager.this.setOnCPIReferrerCall(false);
                        ActivityInfoDAO.restoreReferralTrackingInfo(context, arrayList);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            IgawLogger.Logging(context, "IGAW_QA", e.getMessage(), 0);
            setOnReferrerCall(false);
            setOnCPIReferrerCall(false);
            ActivityInfoDAO.restoreReferralTrackingInfo(context, arrayList);
        }
    }

    public void trackingForADBrix(RequestParameter requestParameter, Context context, ArrayList<TrackingActivityModel> arrayList, ArrayList<TrackingActivityModel> arrayList2) {
        try {
            final Context context2 = context;
            final ArrayList<TrackingActivityModel> arrayList3 = arrayList;
            final ArrayList<TrackingActivityModel> arrayList4 = arrayList2;
            final RequestParameter requestParameter2 = requestParameter;
            DeviceIDManger.getInstance(context).getAndroidADID(context, new ADIDCallbackListener() {
                public void onResult(AdInfo adInfo) {
                    String str = CommonHttpManager.this.TRACKING_REQUEST_URL_FOR_ADBrix;
                    String str2 = BuildConfig.FLAVOR;
                    try {
                        int i;
                        boolean z;
                        IgawLogger.Logging(context2, "IGAW_QA", "trackingForADBrix", 3);
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        for (i = 0; i < arrayList3.size(); i++) {
                            arrayList.add(((TrackingActivityModel) arrayList3.get(i)).getValue());
                        }
                        for (i = 0; i < arrayList4.size(); i++) {
                            arrayList2.add(((TrackingActivityModel) arrayList4.get(i)).getValue());
                        }
                        RequestParameter requestParameter = requestParameter2;
                        Context context = context2;
                        String id = adInfo == null ? BuildConfig.FLAVOR : adInfo.getId();
                        if (adInfo == null) {
                            z = false;
                        } else {
                            z = adInfo.isLimitAdTrackingEnabled();
                        }
                        str2 = AESGetTrackParam.encrypt(requestParameter.getTrackingParameterForADBrix(context, arrayList, arrayList2, id, z), requestParameter2.getHashkey());
                        HashMap hashMap = new HashMap();
                        hashMap.put("k", new StringBuilder(String.valueOf(requestParameter2.getAppkey())).toString());
                        hashMap.put("j", str2);
                        context = context2;
                        final Context context2 = context2;
                        final ArrayList arrayList3 = arrayList3;
                        final ArrayList arrayList4 = arrayList4;
                        WeakReference weakReference = new WeakReference(new HttpUrlConnectionThread(context, 1, str, hashMap, new HttpCallbackListener() {
                            public void callback(String str) {
                                if (str != null) {
                                    try {
                                        if (!str.equals(BuildConfig.FLAVOR)) {
                                            IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, tracking response result : " + str, 3, false);
                                            JSONObject jSONObject = new JSONObject(str);
                                            try {
                                                if (jSONObject.has("BaseTime")) {
                                                    AppImpressionDAO.setServerBaseTimeOffset(context2, jSONObject.getLong("BaseTime") - System.currentTimeMillis());
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            if (jSONObject.getBoolean("Result")) {
                                                TrackingActivitySQLiteDB.getInstance(context2).removeTrackingActivities(arrayList3, context2, "tbl_AppTracking");
                                                TrackingActivitySQLiteDB.getInstance(context2).removeTrackingActivities(arrayList4, context2, "tbl_ImpressionTracking");
                                                return;
                                            }
                                            CommonHttpManager.this.restoreTrackingInfo_Common(context2, arrayList3, arrayList4);
                                            IgawLogger.Logging(context2, "IGAW_QA", "responseResult Result = false", 2, false);
                                            return;
                                        }
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                        new Throwable().getStackTrace();
                                        IgawLogger.Logging(context2, "IGAW_QA", e2.getMessage(), 0);
                                        CommonHttpManager.this.restoreTrackingInfo_Common(context2, arrayList3, arrayList4);
                                        return;
                                    }
                                }
                                CommonHttpManager.this.restoreTrackingInfo_Common(context2, arrayList3, arrayList4);
                                IgawLogger.Logging(context2, "IGAW_QA", "responseResult null Error", 0, false);
                            }
                        }));
                        ((Thread) weakReference.get()).setDaemon(true);
                        ((Thread) weakReference.get()).start();
                    } catch (SocketTimeoutException e) {
                        e.printStackTrace();
                        IgawLogger.Logging(context2, "IGAW_QA", e.toString(), 0);
                        CommonHttpManager.this.restoreTrackingInfo_Common(context2, arrayList3, arrayList4);
                    } catch (Exception e2) {
                        if (!(arrayList3 == null || arrayList3.size() == 0)) {
                            CommonHttpManager.this.restoreTrackingInfo_Common(context2, arrayList3, arrayList4);
                        }
                        e2.printStackTrace();
                        IgawLogger.Logging(context2, "IGAW_QA", e2.toString(), 0);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            IgawLogger.Logging(context, "IGAW_QA", e.toString(), 0);
            restoreTrackingInfo_Common(context, arrayList, arrayList2);
        }
    }

    protected void restoreTrackingInfo_Common(Context context, ArrayList<TrackingActivityModel> arrayList, ArrayList<TrackingActivityModel> arrayList2) {
        TrackingActivitySQLiteDB.getInstance(context).reclaimDirtyDataForRetry(arrayList, context, "tbl_AppTracking");
        TrackingActivitySQLiteDB.getInstance(context).reclaimDirtyDataForRetry(arrayList2, context, "tbl_ImpressionTracking");
    }

    public void demographicCallForADBrix(RequestParameter requestParameter, final Context context) {
        String str = this.DEMOGRAPHIC_REQUEST_URL_FOR_ADBrix;
        String str2 = BuildConfig.FLAVOR;
        try {
            IgawLogger.Logging(context, "IGAW_QA", "demoCallForADBrix", 3, false);
            str2 = AESGetTrackParam.encrypt(requestParameter.getDemographicParameter(), requestParameter.getHashkey());
            HashMap hashMap = new HashMap();
            hashMap.put("k", new StringBuilder(String.valueOf(requestParameter.getAppkey())).toString());
            hashMap.put("j", str2);
            WeakReference weakReference = new WeakReference(new HttpUrlConnectionThread(context, 1, str, hashMap, new HttpCallbackListener() {
                public void callback(String str) {
                    if (str == null) {
                        try {
                            throw new Exception("responseResult null Error");
                        } catch (Exception e) {
                            e.printStackTrace();
                            new Throwable().getStackTrace();
                            IgawLogger.Logging(context, "IGAW_QA", e.getMessage(), 0);
                        }
                    } else if (new JSONObject(str).getBoolean("Result")) {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("demoForTracking", 0);
                        Editor edit = sharedPreferences.edit();
                        for (Entry entry : sharedPreferences.getAll().entrySet()) {
                            String str2 = (String) entry.getValue();
                            String str3 = (String) entry.getKey();
                            LocalDemograhpicDAO.getInstance(context).save_demographic_local(str3, str2);
                            edit.remove(str3);
                        }
                        edit.apply();
                    } else {
                        IgawLogger.Logging(context, "IGAW_QA", "callbackDemographicADBrix false", 3, false);
                    }
                }
            }));
            ((Thread) weakReference.get()).setDaemon(true);
            ((Thread) weakReference.get()).start();
        } catch (Exception e) {
            e.printStackTrace();
            new Throwable().getStackTrace();
            IgawLogger.Logging(context, "IGAW_QA", e.getMessage(), 0);
        }
    }

    public void conversionForDeeplink(RequestParameter requestParameter, final Context context, final List<DeeplinkConversionItem> list) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    IgawLogger.Logging(context, "IGAW_QA", "conversionForDeeplink", 2, false);
                    DeviceIDManger instance = DeviceIDManger.getInstance(context);
                    Context context = context;
                    final List list = list;
                    final Context context2 = context;
                    instance.getAndroidADID(context, new ADIDCallbackListener() {
                        public void onResult(AdInfo adInfo) {
                            String str = CommonHttpManager.this.DEEP_LINK_CONVERSION_FOR_ADBrix;
                            JSONArray jSONArray = new JSONArray();
                            for (DeeplinkConversionItem deeplinkConversionItem : list) {
                                try {
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put("clickId", deeplinkConversionItem.getCommerceClickID());
                                    jSONObject.put("adid", adInfo.getId());
                                    jSONObject.put("mtime", new Date().getTime());
                                    jSONArray.put(jSONObject);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            try {
                                IgawLogger.Logging(context2, "IGAW_QA", "conversionForDeeplink param : " + jSONArray.toString(), 2);
                                Context context = context2;
                                String jSONArray2 = jSONArray.toString();
                                final Context context2 = context2;
                                final List list = list;
                                WeakReference weakReference = new WeakReference(new DeeplinkHttpUrlConnectionThread(context, 1, str, jSONArray2, new HttpCallbackListener() {
                                    public void callback(String str) {
                                        if (str != null) {
                                            try {
                                                if (!str.equals(BuildConfig.FLAVOR)) {
                                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, deeplink conversion response result : " + str, 2, false);
                                                    JSONObject jSONObject = new JSONObject(str);
                                                    if (jSONObject.has("errMsg") && jSONObject.isNull("errMsg")) {
                                                        Task forResult = Task.forResult(null);
                                                        final Context context = context2;
                                                        forResult.continueWith(new Continuation<Object, Void>() {
                                                            public Void then(Task<Object> task) {
                                                                DeeplinkConversionRetryDAO.getDAO(context).clearRetryItems();
                                                                return null;
                                                            }
                                                        }, Task.BACKGROUND_EXECUTOR);
                                                        return;
                                                    }
                                                    CommonHttpManager.this.restoreConversionInfo(context2, list);
                                                    return;
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                CommonHttpManager.this.restoreConversionInfo(context2, list);
                                                new Throwable().getStackTrace();
                                                IgawLogger.Logging(context2, "IGAW_QA", e.getMessage(), 0);
                                                return;
                                            }
                                        }
                                        CommonHttpManager.this.restoreConversionInfo(context2, list);
                                        Log.e("IGAW_QA", "responseResult null Error");
                                    }
                                }));
                                ((Thread) weakReference.get()).setDaemon(true);
                                ((Thread) weakReference.get()).start();
                            } catch (Exception e2) {
                                CommonHttpManager.this.restoreConversionInfo(context2, list);
                                e2.printStackTrace();
                                IgawLogger.Logging(context2, "IGAW_QA", e2.toString(), 0);
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    CommonHttpManager.this.restoreConversionInfo(context, list);
                    IgawLogger.Logging(context, "IGAW_QA", e.toString(), 0);
                }
            }
        }).start();
    }

    public void restoreConversionInfo(final Context context, final List<DeeplinkConversionItem> list) {
        Task.forResult(null).continueWith(new Continuation<Object, Void>() {
            public Void then(Task<Object> task) {
                DeeplinkConversionRetryDAO dao = DeeplinkConversionRetryDAO.getDAO(context);
                for (DeeplinkConversionItem deeplinkConversionItem : list) {
                    if (deeplinkConversionItem.getRetryCnt() > 2) {
                        dao.removeRetryCount(deeplinkConversionItem.getKey());
                    } else {
                        dao.updateOrInsertConversionForRetry(deeplinkConversionItem.getKey(), deeplinkConversionItem.getConversionKey(), deeplinkConversionItem.getCommerceClickID());
                    }
                }
                return null;
            }
        }, Task.BACKGROUND_EXECUTOR);
    }

    protected synchronized void setOnReferrerCall(boolean z) {
        onReferrerCall = z;
    }

    protected synchronized boolean getOnReferrerCall() {
        return onReferrerCall;
    }

    protected synchronized void setOnCPIReferrerCall(boolean z) {
        onCPIReferrerCall = z;
    }

    protected synchronized boolean getOnCPIReferrerCall() {
        return onCPIReferrerCall;
    }
}
