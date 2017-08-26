package com.igaworks.adbrix.core;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;
import com.igaworks.adbrix.cpe.CPECompletionHandler;
import com.igaworks.adbrix.db.ConversionDAOForRetryCompletion;
import com.igaworks.adbrix.db.DailyPlayDAO;
import com.igaworks.adbrix.db.RealRewardDAO;
import com.igaworks.adbrix.db.ViralCPIDAO;
import com.igaworks.adbrix.goods.AnipangGoodsOfferRewardDialog;
import com.igaworks.adbrix.goods.GoodsConstant;
import com.igaworks.adbrix.goods.GoodsOfferDialog;
import com.igaworks.adbrix.goods.GoodsOfferManager;
import com.igaworks.adbrix.goods.GoodsOfferModel;
import com.igaworks.adbrix.impl.ADBrixFrameworkFactory;
import com.igaworks.adbrix.interfaces.ADBrixHttpCallbackListener;
import com.igaworks.adbrix.interfaces.ParticipationProgressCallbackListener;
import com.igaworks.adbrix.json.JSON2ScheduleConverter;
import com.igaworks.adbrix.json.JSON2ViralConverter;
import com.igaworks.adbrix.model.DailyPlay;
import com.igaworks.adbrix.model.Engagement;
import com.igaworks.adbrix.model.Promotion;
import com.igaworks.adbrix.model.RealReward;
import com.igaworks.adbrix.model.RealRewardResultModel;
import com.igaworks.adbrix.model.ScheduleContainer;
import com.igaworks.adbrix.model.Theme;
import com.igaworks.adbrix.model.ViralInfoModel;
import com.igaworks.adbrix.model.ViralUrlModel;
import com.igaworks.core.AESGetTrackParam;
import com.igaworks.core.AdvertisingIdClient.ADIDCallbackListener;
import com.igaworks.core.AdvertisingIdClient.AdInfo;
import com.igaworks.core.DeviceIDManger;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.dao.AppImpressionDAO;
import com.igaworks.dao.CoreIDDAO;
import com.igaworks.dao.tracking.TrackingActivityModel;
import com.igaworks.interfaces.HttpCallbackListener;
import com.igaworks.net.CommonHttpManager;
import com.igaworks.net.HttpManager;
import com.igaworks.net.HttpUrlConnectionThread;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.bolts_task.Continuation;
import com.igaworks.util.bolts_task.Task;
import com.igaworks.util.image.ImageCacheFactory;
import com.igaworks.util.image.ImageDownloadAsyncCallback;
import com.unity3d.ads.metadata.MediationMetaData;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ADBrixHttpManager extends CommonHttpManager {
    private static ADBrixHttpManager manager;
    private static boolean onCompleteGoodsOffer = false;
    private static boolean onGetSchedule = false;
    public static OnGetSchedule onGetScheduleEvent;
    public static ScheduleContainer schedule;
    private String COMPLETE_CPE_REQUEST_URL_FOR_ADBrix = (cpn_domain + "Campaign/Complete");
    private String COMPLETE_REAL_REWARD_REQUEST_URL_FOR_ADBrix = (cpn_domain + "RealReward/CompleteEvent");
    private String PARTICIPATION_PROGRESS_REQUEST_URL_FOR_ADBrix = (cpn_domain + "CampaignVer2/GetCampaignInfo");
    private String REDEEM_REAL_REWARD_REQUEST_URL_FOR_ADBrix = (cpn_domain + "RealReward/GetRedeem");
    private String SCHEDULE_REQUEST_URL_FOR_ADBrix = (cpn_domain + "CampaignVer2/GetSchedule");
    private String START_AND_COMPLETE_REAL_REWARD_REQUEST_URL_FOR_ADBrix = (cpn_domain + "RealReward/CompleteEventInstantly");
    private String START_REAL_REWARD_REQUEST_URL_FOR_ADBrix = (cpn_domain + "RealReward/StartEvent");
    private final String VIRAL_CPI_DOMAIN_ROOT_LIVE = "http://live.adbrix.igaworks.com/adpopcorn/2/api/mobile/mobileservice.svc/";
    private final String VIRAL_CPI_DOMAIN_ROOT_STAGE = "http://staging.igaworks.com/adpopcorn/2/api/mobile/mobileservice.svc/";
    private final String VIRAL_CPI_GET_PROMOTION_INFO_PATH = "GetPromotingCPIInfoByEncodedQuery";
    private final String VIRAL_CPI_GET_PROMOTION_URL_PATH = "GetPromotingCPITrackingURLByEncodedQuery";
    private Dialog god;

    private ADBrixHttpManager() {
    }

    public static ADBrixHttpManager getManager(Context context) {
        if (manager == null) {
            manager = new ADBrixHttpManager();
        }
        return manager;
    }

    public void completeCPECallForADBrix(RequestParameter requestParameter, Context context, ArrayList<TrackingActivityModel> arrayList, ArrayList<TrackingActivityModel> arrayList2, ArrayList<Integer> arrayList3) {
        try {
            final Context context2 = context;
            final ArrayList<TrackingActivityModel> arrayList4 = arrayList;
            final ArrayList<TrackingActivityModel> arrayList5 = arrayList2;
            final RequestParameter requestParameter2 = requestParameter;
            final ArrayList<Integer> arrayList6 = arrayList3;
            DeviceIDManger.getInstance(context).getAndroidADID(context, new ADIDCallbackListener() {
                public void onResult(AdInfo adInfo) {
                    try {
                        int i;
                        boolean z;
                        String access$0 = ADBrixHttpManager.this.COMPLETE_CPE_REQUEST_URL_FOR_ADBrix;
                        String str = BuildConfig.FLAVOR;
                        IgawLogger.Logging(context2, "IGAW_QA", "completeCPECallForADBrix", 3, false);
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        for (i = 0; i < arrayList4.size(); i++) {
                            arrayList.add(((TrackingActivityModel) arrayList4.get(i)).getValue());
                        }
                        for (i = 0; i < arrayList5.size(); i++) {
                            arrayList2.add(((TrackingActivityModel) arrayList5.get(i)).getValue());
                        }
                        RequestParameter requestParameter = requestParameter2;
                        Context context = context2;
                        ArrayList arrayList3 = arrayList6;
                        String id = adInfo == null ? BuildConfig.FLAVOR : adInfo.getId();
                        if (adInfo == null) {
                            z = false;
                        } else {
                            z = adInfo.isLimitAdTrackingEnabled();
                        }
                        str = AESGetTrackParam.encrypt_hashkey(requestParameter.getTrackingParameterForADBrix(context, arrayList, arrayList2, arrayList3, id, z), requestParameter2.getHashkey());
                        HashMap hashMap = new HashMap();
                        hashMap.put("k", new StringBuilder(String.valueOf(requestParameter2.getAppkey())).toString());
                        hashMap.put("j", str);
                        Context context2 = context2;
                        final Context context3 = context2;
                        arrayList2 = arrayList4;
                        arrayList3 = arrayList5;
                        final ArrayList arrayList4 = arrayList6;
                        WeakReference weakReference = new WeakReference(new HttpUrlConnectionThread(context2, 1, access$0, hashMap, new HttpCallbackListener() {
                            public void callback(String str) {
                                if (str == null) {
                                    try {
                                        throw new Exception("complete CPE null Error");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e("IGAW_QA", "completeCPECallForADBrix Callback error: " + e.getMessage());
                                        ADBrixHttpManager.this.restoreTrackingInfo_Common(context3, arrayList2, arrayList3);
                                        ADBrixHttpManager.this.restoreCPEConversionList(context3, arrayList4);
                                        return;
                                    }
                                }
                                IgawLogger.Logging(context3, "IGAW_QA", "ADBrixTracer, callbackReferrerADBrix > responseString : " + str, 3, false);
                                RequestParameter aTRequestParameter = RequestParameter.getATRequestParameter(context3);
                                JSONObject jSONObject = new JSONObject(str);
                                try {
                                    if (jSONObject.has("BaseTime")) {
                                        AppImpressionDAO.setServerBaseTimeOffset(context3, jSONObject.getLong("BaseTime") - System.currentTimeMillis());
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                                if (!jSONObject.getBoolean("Result") || jSONObject.isNull("Data")) {
                                    ADBrixHttpManager.this.restoreTrackingInfo_Common(context3, arrayList2, arrayList3);
                                    ADBrixHttpManager.this.restoreCPEConversionList(context3, arrayList4);
                                    IgawLogger.Logging(context3, "IGAW_QA", "complete cpe error : result false", 3, false);
                                    return;
                                }
                                jSONObject = new JSONObject(jSONObject.getString("Data"));
                                if (jSONObject.has("conversion_result") && !jSONObject.isNull("conversion_result")) {
                                    JSONArray jSONArray = jSONObject.getJSONArray("conversion_result");
                                    Editor edit = context3.getSharedPreferences("completeConversions", 0).edit();
                                    for (int i = 0; i < jSONArray.length(); i++) {
                                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                                        if (jSONObject2.has("conversion_key") && !jSONObject2.isNull("conversion_key") && jSONObject2.has("result_code") && !jSONObject2.isNull("result_code")) {
                                            Engagement engagement;
                                            int i2 = jSONObject2.getInt("conversion_key");
                                            int i3 = jSONObject2.getInt("result_code");
                                            for (Engagement engagement2 : ADBrixHttpManager.schedule.getSchedule().getEngagements()) {
                                                if (engagement2.getConversionKey() == i2) {
                                                    engagement = engagement2;
                                                    break;
                                                }
                                            }
                                            engagement = null;
                                            for (DailyPlay dailyPlay : ADBrixHttpManager.schedule.getSchedule().getReEngagement().getDailyPlay()) {
                                                if (dailyPlay.getConversionKey() == i2) {
                                                    break;
                                                }
                                            }
                                            DailyPlay dailyPlay2 = null;
                                            if (i3 == 1) {
                                                aTRequestParameter.setConversionCache(i2);
                                                if (engagement != null) {
                                                    final String completeMessage = engagement.getDisplayData().getCompleteMessage();
                                                    if (engagement.getDisplayData().getCompleteToastMSec() > 0 && completeMessage != null && completeMessage.length() > 0 && !completeMessage.equals("null")) {
                                                        Handler handler = new Handler(Looper.getMainLooper());
                                                        final Context context = context3;
                                                        handler.post(new Runnable() {
                                                            public void run() {
                                                                IgawLogger.Logging(context, "IGAW_QA", "callback complete cpe > msg : " + completeMessage + ", duration : " + engagement.getDisplayData().getCompleteToastMSec(), 3, false);
                                                                ADBrixHttpManager.this.makeCompleteToast(context, (long) engagement.getDisplayData().getCompleteToastMSec(), completeMessage);
                                                            }
                                                        });
                                                    }
                                                    IgawLogger.Logging(context3, "IGAW_QA", "callback complete cpe > key : " + i2, 3, false);
                                                }
                                                if (dailyPlay2 != null) {
                                                    DailyPlayDAO.getInstance().setPendingConversionKey(context3, -1);
                                                    DailyPlayDAO.getInstance().setLatestConversionKey(context3, dailyPlay2.getConversionKey());
                                                }
                                            } else if (i3 != 7000) {
                                                ADBrixHttpManager.this.restoreCPESingleConversion(context3, i2, i3);
                                            } else if (dailyPlay2 != null) {
                                                int i4;
                                                if (DailyPlayDAO.getInstance().getPendingConversionKey(context3) == dailyPlay2.getConversionKey()) {
                                                    DailyPlayDAO.getInstance().setPendingConversionKey(context3, -1);
                                                }
                                                if (!jSONObject2.has("waiting_time") || jSONObject2.isNull("waiting_time")) {
                                                    i4 = -1;
                                                } else {
                                                    i4 = jSONObject2.getInt("waiting_time");
                                                }
                                                DailyPlayDAO.getInstance().setWaitingTime(context3, i4);
                                            }
                                            edit.remove(i2);
                                            edit.commit();
                                        }
                                    }
                                }
                            }
                        }));
                        ((Thread) weakReference.get()).setDaemon(true);
                        ((Thread) weakReference.get()).start();
                    } catch (Exception e) {
                        e.printStackTrace();
                        IgawLogger.Logging(context2, "IGAW_QA", e.getMessage(), 0);
                        ADBrixHttpManager.this.restoreTrackingInfo_Common(context2, arrayList4, arrayList5);
                        ADBrixHttpManager.this.restoreCPEConversionList(context2, arrayList6);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            IgawLogger.Logging(context, "IGAW_QA", e.getMessage(), 0);
            restoreTrackingInfo_Common(context, arrayList, arrayList2);
            restoreCPEConversionList(context, arrayList3);
        }
    }

    public void getScheduleForADBrix(RequestParameter requestParameter, Context context, String str, ScheduleContainer scheduleContainer) {
        final String str2 = this.SCHEDULE_REQUEST_URL_FOR_ADBrix;
        final Context context2 = context;
        final RequestParameter requestParameter2 = requestParameter;
        final String str3 = str;
        new Thread(new Runnable() {
            public void run() {
                try {
                    DeviceIDManger instance = DeviceIDManger.getInstance(context2);
                    Context context = context2;
                    final Context context2 = context2;
                    final RequestParameter requestParameter = requestParameter2;
                    final String str = str3;
                    final String str2 = str2;
                    instance.getAndroidADID(context, new ADIDCallbackListener() {
                        public void onResult(AdInfo adInfo) {
                            if (adInfo == null) {
                                Log.e("IGAW_QA", "@getScheduleForADBrix: google_ad_id is null");
                                ADBrixHttpManager.onGetSchedule = false;
                            } else if (ADBrixHttpManager.onGetSchedule) {
                                IgawLogger.Logging(context2, "IGAW_QA", "onGetSchedule already called.", 3);
                            } else {
                                try {
                                    IgawLogger.Logging(context2, "IGAW_QA", "getScheduleForADBrix > getSchedule call send.", 3, false);
                                    Locale locale = Locale.getDefault();
                                    String str = BuildConfig.FLAVOR;
                                    if (!(VERSION.RELEASE == null || VERSION.RELEASE.equalsIgnoreCase(BuildConfig.FLAVOR))) {
                                        str = VERSION.RELEASE;
                                    }
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("k", new StringBuilder(String.valueOf(requestParameter.getAppkey())).toString());
                                    hashMap.put("la", locale.getLanguage());
                                    hashMap.put("co", locale.getCountry());
                                    hashMap.put("os", "a_" + str);
                                    hashMap.put(MediationMetaData.KEY_VERSION, "a_" + str);
                                    hashMap.put("puid", str);
                                    hashMap.put("google_ad_id", adInfo.getId());
                                    Object obj = "0";
                                    if (!(ADBrixHttpManager.schedule == null || ADBrixHttpManager.schedule.getCheckSum() == null)) {
                                        obj = ADBrixHttpManager.schedule.getCheckSum();
                                    }
                                    hashMap.put("checksum", obj);
                                    Context context = context2;
                                    String str2 = str2;
                                    final Context context2 = context2;
                                    WeakReference weakReference = new WeakReference(new HttpUrlConnectionThread(context, 1, str2, hashMap, new HttpCallbackListener() {
                                        public void callback(String str) {
                                            ADBrixHttpManager.onGetSchedule = false;
                                            if (str == null) {
                                                throw new Exception("responseResult null Error");
                                            }
                                            IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, get schedule response result : " + str, 3);
                                            try {
                                                JSONObject jSONObject = new JSONObject(str);
                                                if (jSONObject.has("BaseTime")) {
                                                    AppImpressionDAO.setServerBaseTimeOffset(context2, jSONObject.getLong("BaseTime") - System.currentTimeMillis());
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                ADBrixHttpManager.schedule = JSON2ScheduleConverter.json2ScheduleV2(context2, str);
                                                IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, schedule received, count =  " + (ADBrixHttpManager.schedule != null ? "exist" : "null"), 3, false);
                                                if (ADBrixHttpManager.onGetScheduleEvent != null) {
                                                    ADBrixHttpManager.onGetScheduleEvent.onGetSchedule(context2, true);
                                                }
                                                try {
                                                    RequestParameter aTRequestParameter = RequestParameter.getATRequestParameter(context2);
                                                    if (aTRequestParameter.getReferralKey() != -1) {
                                                        CPECompletionHandler.restoreCPEAction(context2, aTRequestParameter, ADBrixHttpManager.this);
                                                    }
                                                    if (ADBrixHttpManager.schedule != null) {
                                                        GoodsOfferManager.getManager(null).checkRestoreRealReward(context2);
                                                        if (RequestParameter.getATRequestParameter(context2).getReferralKey() > -1 && ViralCPIDAO.isRestoreViralCPI() && ViralCPIDAO.getActivity() != null) {
                                                            IgawLogger.Logging(context2, "IGAW_QA", "getSchedule - RestoreViralCPIDialog", 3);
                                                            try {
                                                                ADBrixFrameworkFactory.getFramework().showViralCPINotice(ViralCPIDAO.getActivity());
                                                            } catch (Exception e2) {
                                                                e2.printStackTrace();
                                                            }
                                                        }
                                                        if (!(ADBrixHttpManager.schedule.getSchedule() == null || ADBrixHttpManager.schedule.getSchedule().getMedia() == null || ADBrixHttpManager.schedule.getSchedule().getMedia().getTheme() == null)) {
                                                            Theme theme = ADBrixHttpManager.schedule.getSchedule().getMedia().getTheme();
                                                            ADBrixHttpManager.this.preDownloadImage(context2, theme.getCirclePlayBtn());
                                                            ADBrixHttpManager.this.preDownloadImage(context2, theme.getCloseBtn());
                                                            ADBrixHttpManager.this.preDownloadImage(context2, theme.getMissionCheckOff());
                                                            ADBrixHttpManager.this.preDownloadImage(context2, theme.getMissionCheckOn());
                                                            ADBrixHttpManager.this.preDownloadImage(context2, theme.getPlayBtnAreaBG());
                                                            ADBrixHttpManager.this.preDownloadImage(context2, theme.getSelectedAppArrow());
                                                            ADBrixHttpManager.this.preDownloadImage(context2, theme.getSlideLeftBtn());
                                                            ADBrixHttpManager.this.preDownloadImage(context2, theme.getSlideRightBtn());
                                                            ADBrixHttpManager.this.preDownloadImage(context2, theme.getSquarePlayBtn());
                                                            ADBrixHttpManager.this.preDownloadImage(context2, theme.getStepArrow());
                                                        }
                                                        for (Promotion promotion : ADBrixHttpManager.schedule.getSchedule().getPromotions()) {
                                                            if (promotion.getDisplay() != null) {
                                                                try {
                                                                    if (promotion.getDisplay().getSlide().getResource() != null) {
                                                                        for (String access$6 : promotion.getDisplay().getSlide().getResource()) {
                                                                            ADBrixHttpManager.this.preDownloadImage(context2, access$6);
                                                                        }
                                                                    }
                                                                    ADBrixHttpManager.this.preDownloadImage(context2, promotion.getDisplay().getIcon().getResource());
                                                                } catch (Exception e22) {
                                                                    e22.printStackTrace();
                                                                }
                                                            }
                                                        }
                                                    }
                                                } catch (Exception e222) {
                                                    e222.printStackTrace();
                                                    ADBrixHttpManager.onGetSchedule = false;
                                                    IgawLogger.Logging(context2, "IGAW_QA", e222.getMessage(), 0);
                                                }
                                            } catch (Exception e2222) {
                                                ADBrixHttpManager.schedule = null;
                                                IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, schedule received, but parsing error occurred -> " + e2222.toString(), 3, false);
                                            }
                                        }
                                    }));
                                    ADBrixHttpManager.onGetSchedule = true;
                                    ((Thread) weakReference.get()).setDaemon(true);
                                    ((Thread) weakReference.get()).start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    ADBrixHttpManager.onGetSchedule = false;
                                    IgawLogger.Logging(context2, "IGAW_QA", e.getMessage(), 0);
                                }
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    ADBrixHttpManager.onGetSchedule = false;
                }
            }
        }).start();
    }

    public void getParticipationProgressForADBrix(RequestParameter requestParameter, Context context, String str, int i, String str2, String str3, ParticipationProgressCallbackListener participationProgressCallbackListener) {
        final String str4 = this.PARTICIPATION_PROGRESS_REQUEST_URL_FOR_ADBrix;
        final Context context2 = context;
        final int i2 = i;
        final String str5 = str3;
        final String str6 = str;
        final String str7 = str2;
        final ParticipationProgressCallbackListener participationProgressCallbackListener2 = participationProgressCallbackListener;
        new Thread(new Runnable() {
            public void run() {
                try {
                    DeviceIDManger instance = DeviceIDManger.getInstance(context2);
                    Context context = context2;
                    final Context context2 = context2;
                    final int i = i2;
                    final String str = str5;
                    final String str2 = str6;
                    final String str3 = str7;
                    final String str4 = str4;
                    final ParticipationProgressCallbackListener participationProgressCallbackListener = participationProgressCallbackListener2;
                    instance.getAndroidADID(context, new ADIDCallbackListener() {
                        public void onResult(AdInfo adInfo) {
                            if (adInfo != null) {
                                try {
                                    IgawLogger.Logging(context2, "IGAW_QA", "getParticipationProgressForADBrix > getParticipationProgress call send. ck = " + i + ", google adid = " + adInfo.getId() + ", usn = " + str + ", appKey = " + str2, 3, true);
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("ak", str2);
                                    hashMap.put("ck", new StringBuilder(String.valueOf(i)).toString());
                                    hashMap.put("puid", str3);
                                    hashMap.put("google_ad_id", adInfo.getId());
                                    hashMap.put("usn", str);
                                    Context context = context2;
                                    String str = str4;
                                    final Context context2 = context2;
                                    final ParticipationProgressCallbackListener participationProgressCallbackListener = participationProgressCallbackListener;
                                    WeakReference weakReference = new WeakReference(new HttpUrlConnectionThread(context, 1, str, hashMap, new HttpCallbackListener() {
                                        public void callback(String str) {
                                            if (str == null) {
                                                try {
                                                    throw new Exception("responseResult null Error");
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                    IgawLogger.Logging(context2, "IGAW_QA", e.getMessage(), 0);
                                                    return;
                                                }
                                            }
                                            try {
                                                IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, get participation progress result : " + str, 3);
                                                participationProgressCallbackListener.callback(JSON2ScheduleConverter.json2ParticipationProgressModel(str));
                                            } catch (Exception e2) {
                                                participationProgressCallbackListener.callback(null);
                                                e2.printStackTrace();
                                                IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, get participation progress error : " + e2.toString(), 3);
                                            }
                                        }
                                    }));
                                    ((Thread) weakReference.get()).setDaemon(true);
                                    ((Thread) weakReference.get()).start();
                                    return;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, get participation progress error : " + e.toString(), 3);
                                    return;
                                }
                            }
                            Log.e("IGAW_QA", "@getParticipationProgressForADBrix: google_ad_is is null");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void startRealReward(final Context context, String str, final int i, String str2) {
        String str3 = this.START_REAL_REWARD_REQUEST_URL_FOR_ADBrix;
        String str4 = BuildConfig.FLAVOR;
        str4 = CoreIDDAO.getInstance().getGoogleAdId(context);
        try {
            IgawLogger.Logging(context, "IGAW_QA", "startRealReward > startRealReward call send. rrk = " + i + ", adid = " + str4 + ", appKey = " + str, 3, true);
            HashMap hashMap = new HashMap();
            hashMap.put("appKey", str);
            hashMap.put("rrk", new StringBuilder(String.valueOf(i)).toString());
            hashMap.put("adid", str4);
            hashMap.put("usn", str2);
            str4 = AESGetTrackParam.encrypt_hashkey(getJSONParam(hashMap), RequestParameter.getATRequestParameter(context).getHashkey());
            hashMap.clear();
            hashMap.put("k", str);
            hashMap.put("j", str4);
            WeakReference weakReference = new WeakReference(new HttpUrlConnectionThread(context, 1, str3, hashMap, new HttpCallbackListener() {
                public void callback(String str) {
                    if (str == null) {
                        try {
                            throw new Exception("responseResult null Error");
                        } catch (Exception e) {
                            e.printStackTrace();
                            IgawLogger.Logging(context, "IGAW_QA", e.getMessage(), 0);
                            return;
                        }
                    }
                    try {
                        IgawLogger.Logging(context, "IGAW_QA", "ADBrixTracer, startRealReward result : " + str, 3);
                        RealRewardResultModel json2RealReward = JSON2ScheduleConverter.json2RealReward(str);
                        if (json2RealReward == null) {
                            Toast.makeText(context, "\uc774\ubca4\ud2b8\uc5d0 \ucc38\uc5ec\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4. \uc7a0\uc2dc \ud6c4 \ub2e4\uc2dc \uc2dc\ub3c4\ud574\uc8fc\uc138\uc694.", 0).show();
                        } else if (json2RealReward.isResult()) {
                            RealRewardDAO.getInstance().saveSessionNo(context, i, json2RealReward.getSessionNo());
                        } else {
                            Toast.makeText(context, json2RealReward.getResultMessage(), 0).show();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        IgawLogger.Logging(context, "IGAW_QA", "ADBrixTracer, startRealReward error : " + e2.toString(), 3);
                    }
                }
            }));
            ((Thread) weakReference.get()).setDaemon(true);
            ((Thread) weakReference.get()).start();
        } catch (Exception e) {
            e.printStackTrace();
            IgawLogger.Logging(context, "IGAW_QA", "ADBrixTracer, startRealReward error : " + e.toString(), 3);
        }
    }

    public void completeRealReward(Context context, String str, int i, long j, Activity activity) {
        if (onCompleteGoodsOffer) {
            IgawLogger.Logging(context, "IGAW_QA", "completeRealReward already called.", 3);
            return;
        }
        String str2 = this.COMPLETE_REAL_REWARD_REQUEST_URL_FOR_ADBrix;
        String str3 = BuildConfig.FLAVOR;
        try {
            IgawLogger.Logging(context, "IGAW_QA", "completeRealReward > completeRealReward call send. rrk = " + i + ", sn = " + j + ", appKey = " + str, 3, true);
            HashMap hashMap = new HashMap();
            hashMap.put("appKey", str);
            hashMap.put("rrk", new StringBuilder(String.valueOf(i)).toString());
            hashMap.put("sn", new StringBuilder(String.valueOf(j)).toString());
            str3 = AESGetTrackParam.encrypt_hashkey(getJSONParam(hashMap), RequestParameter.getATRequestParameter(context).getHashkey());
            hashMap.clear();
            hashMap.put("k", str);
            hashMap.put("j", str3);
            final Context context2 = context;
            final Activity activity2 = activity;
            final String str4 = str;
            final int i2 = i;
            WeakReference weakReference = new WeakReference(new HttpUrlConnectionThread(context, 1, str2, hashMap, new HttpCallbackListener() {
                public void callback(String str) {
                    ADBrixHttpManager.onCompleteGoodsOffer = false;
                    if (str == null) {
                        try {
                            throw new Exception("responseResult null Error");
                        } catch (Exception e) {
                            e.printStackTrace();
                            IgawLogger.Logging(context2, "IGAW_QA", e.getMessage(), 0);
                            return;
                        }
                    }
                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, completeRealReward result : " + str, 3);
                    ADBrixHttpManager.this.handleCompleteResult(context2, activity2, str, str4, i2);
                }
            }));
            ((Thread) weakReference.get()).setDaemon(true);
            ((Thread) weakReference.get()).start();
            onCompleteGoodsOffer = true;
        } catch (Exception e) {
            e.printStackTrace();
            IgawLogger.Logging(context, "IGAW_QA", "ADBrixTracer, completeRealReward error : " + e.toString(), 3);
        }
    }

    public String getJSONParam(HashMap<String, String> hashMap) {
        JSONObject jSONObject = new JSONObject();
        if (hashMap != null) {
            try {
                if (hashMap.size() > 0) {
                    for (Entry entry : hashMap.entrySet()) {
                        jSONObject.put((String) entry.getKey(), entry.getValue());
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    public void startAndCompeteRealReward(Context context, String str, int i, String str2, Activity activity) {
        String str3 = this.START_AND_COMPLETE_REAL_REWARD_REQUEST_URL_FOR_ADBrix;
        String str4 = BuildConfig.FLAVOR;
        str4 = CoreIDDAO.getInstance().getGoogleAdId(context);
        try {
            IgawLogger.Logging(context, "IGAW_QA", "startAndCompeteRealReward > startAndCompeteRealReward call send. rrk = " + i + ", adid = " + str4 + ", appKey = " + str, 3, true);
            HashMap hashMap = new HashMap();
            hashMap.put("appKey", str);
            hashMap.put("rrk", new StringBuilder(String.valueOf(i)).toString());
            hashMap.put("adid", str4);
            hashMap.put("usn", str2);
            str4 = AESGetTrackParam.encrypt_hashkey(getJSONParam(hashMap), RequestParameter.getATRequestParameter(context).getHashkey());
            hashMap.clear();
            hashMap.put("k", str);
            hashMap.put("j", str4);
            final Context context2 = context;
            final Activity activity2 = activity;
            final String str5 = str;
            final int i2 = i;
            WeakReference weakReference = new WeakReference(new HttpUrlConnectionThread(context, 1, str3, hashMap, new HttpCallbackListener() {
                public void callback(String str) {
                    if (str == null) {
                        try {
                            throw new Exception("responseResult null Error");
                        } catch (Exception e) {
                            e.printStackTrace();
                            IgawLogger.Logging(context2, "IGAW_QA", e.getMessage(), 0);
                            return;
                        }
                    }
                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, startAndCompeteRealReward result : " + str, 3);
                    ADBrixHttpManager.this.handleCompleteResult(context2, activity2, str, str5, i2);
                }
            }));
            ((Thread) weakReference.get()).setDaemon(true);
            ((Thread) weakReference.get()).start();
        } catch (Exception e) {
            e.printStackTrace();
            IgawLogger.Logging(context, "IGAW_QA", "ADBrixTracer, startAndCompeteRealReward error : " + e.toString(), 3);
        }
    }

    private void handleCompleteResult(Context context, Activity activity, String str, String str2, int i) {
        try {
            RealRewardResultModel json2RealReward = JSON2ScheduleConverter.json2RealReward(str);
            if (json2RealReward == null) {
                Toast.makeText(context, "\uc774\ubca4\ud2b8\uc5d0 \ucc38\uc5ec\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4. \uc7a0\uc2dc \ud6c4 \ub2e4\uc2dc \uc2dc\ub3c4\ud574\uc8fc\uc138\uc694.", 0).show();
                return;
            }
            RealReward realReward = (RealReward) schedule.getSchedule().getRealRewards().get(0);
            GoodsOfferModel goodsOfferModel = new GoodsOfferModel();
            goodsOfferModel.setMainImg(realReward.getRealRewardImageUrl());
            goodsOfferModel.setRealRewardKey(realReward.getRealRewardKey());
            goodsOfferModel.setConversionKey(realReward.getConversionKey());
            goodsOfferModel.setDailyEvent(realReward.isIsDailyEvent());
            goodsOfferModel.setNoCondition(realReward.isNoCondition());
            goodsOfferModel.setTest(realReward.isIsTest());
            LayoutParams layoutParams;
            LayoutParams layoutParams2;
            if (json2RealReward.isResult()) {
                IgawLogger.Logging(context, "IGAW_QA", "ADBrixTracer, handleCompleteResult success : redeem type = " + json2RealReward.getType(), 3);
                if (!json2RealReward.getType().equals("Email")) {
                    if (this.god == null || !this.god.isShowing()) {
                        if (GoodsConstant.anipangApps.contains(str2) || realReward.isNoCondition()) {
                            this.god = new AnipangGoodsOfferRewardDialog(activity, json2RealReward);
                            layoutParams = new LayoutParams();
                            layoutParams.copyFrom(this.god.getWindow().getAttributes());
                            layoutParams.width = -1;
                            layoutParams.height = -2;
                            this.god.getWindow().setAttributes(layoutParams);
                            this.god.show();
                        } else {
                            goodsOfferModel.setMidText(json2RealReward.getSuccessMsg());
                            goodsOfferModel.setTitleImg("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/title_02_large.png");
                            goodsOfferModel.setType(4);
                            this.god = new GoodsOfferDialog(activity, goodsOfferModel);
                            layoutParams = new LayoutParams();
                            layoutParams.copyFrom(this.god.getWindow().getAttributes());
                            layoutParams.width = -1;
                            layoutParams.height = -2;
                            this.god.getWindow().setAttributes(layoutParams);
                            this.god.show();
                        }
                    }
                    RealRewardDAO.getInstance().clearRetryRedeemCache(context, i);
                    RealRewardDAO.getInstance().clearSessions(context, i);
                    if (realReward.isIsDailyEvent()) {
                        RealRewardDAO.getInstance().saveDailyCompletion(context, i);
                    } else {
                        RealRewardDAO.getInstance().saveCompletedRealRewardKey(context, i);
                    }
                } else if (this.god == null || !this.god.isShowing()) {
                    goodsOfferModel.setTitleImg("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/title_02_large.png");
                    goodsOfferModel.setType(2);
                    this.god = new GoodsOfferDialog(activity, goodsOfferModel);
                    layoutParams2 = new LayoutParams();
                    layoutParams2.copyFrom(this.god.getWindow().getAttributes());
                    layoutParams2.width = -1;
                    layoutParams2.height = -2;
                    this.god.getWindow().setAttributes(layoutParams2);
                    this.god.show();
                }
                RealRewardDAO.getInstance().clearRetryCompleteCache(context, i);
            } else if (json2RealReward.getResultCode() == 5701) {
                goodsOfferModel.setMidText(realReward.getMissionText());
                goodsOfferModel.setTitleImg("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/title_03_large.png");
                goodsOfferModel.setType(3);
                GoodsOfferDialog goodsOfferDialog = new GoodsOfferDialog(activity, goodsOfferModel);
                LayoutParams layoutParams3 = new LayoutParams();
                layoutParams3.copyFrom(goodsOfferDialog.getWindow().getAttributes());
                layoutParams3.width = -1;
                layoutParams3.height = -2;
                goodsOfferDialog.getWindow().setAttributes(layoutParams3);
                goodsOfferDialog.show();
                RealRewardDAO.getInstance().clearRetryRedeemCache(context, i);
                RealRewardDAO.getInstance().clearRetryCompleteCache(context, i);
                RealRewardDAO.getInstance().clearSessions(context, i);
                if (realReward.isIsDailyEvent()) {
                    RealRewardDAO.getInstance().saveDailyCompletion(context, i);
                } else {
                    RealRewardDAO.getInstance().saveCompletedRealRewardKey(context, i);
                }
            } else if (GoodsConstant.anipangApps.contains(str2) || realReward.isNoCondition()) {
                if (json2RealReward.getStatusCodes() == 572 && realReward.isIsDailyEvent()) {
                    RealRewardDAO.getInstance().saveDailyCompletion(context, i);
                }
                this.god = new AnipangGoodsOfferRewardDialog(activity, json2RealReward);
                layoutParams2 = new LayoutParams();
                layoutParams2.copyFrom(this.god.getWindow().getAttributes());
                layoutParams2.width = -1;
                layoutParams2.height = -2;
                this.god.getWindow().setAttributes(layoutParams2);
                this.god.show();
            } else {
                goodsOfferModel.setMidText("\uc624\ub958\uac00 \ubc1c\uc0dd\ud558\uc600\uc2b5\ub2c8\ub2e4. \uc7a0\uc2dc \ud6c4 \ub2e4\uc2dc \uc2dc\ub3c4\ud574\uc8fc\uc138\uc694.");
                goodsOfferModel.setTitleImg("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/title_03_large.png");
                goodsOfferModel.setType(3);
                GoodsOfferDialog goodsOfferDialog2 = new GoodsOfferDialog(activity, goodsOfferModel);
                layoutParams = new LayoutParams();
                layoutParams.copyFrom(goodsOfferDialog2.getWindow().getAttributes());
                layoutParams.width = -1;
                layoutParams.height = -2;
                goodsOfferDialog2.getWindow().setAttributes(layoutParams);
                goodsOfferDialog2.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            IgawLogger.Logging(context, "IGAW_QA", "ADBrixTracer, handleCompleteResult error : " + e.toString(), 3);
        }
    }

    public void redeemRealReward(Context context, String str, int i, long j, String str2) {
        String str3 = this.REDEEM_REAL_REWARD_REQUEST_URL_FOR_ADBrix;
        String str4 = BuildConfig.FLAVOR;
        try {
            IgawLogger.Logging(context, "IGAW_QA", "redeemRealReward > redeemRealReward call send. rrk = " + i + ", sn = " + j + ", appKey = " + str, 3, true);
            HashMap hashMap = new HashMap();
            hashMap.put("appKey", str);
            hashMap.put("rrk", new StringBuilder(String.valueOf(i)).toString());
            hashMap.put("sn", new StringBuilder(String.valueOf(j)).toString());
            hashMap.put("email", str2);
            str4 = AESGetTrackParam.encrypt_hashkey(getJSONParam(hashMap), RequestParameter.getATRequestParameter(context).getHashkey());
            hashMap.clear();
            hashMap.put("k", str);
            hashMap.put("j", str4);
            final Context context2 = context;
            final int i2 = i;
            final long j2 = j;
            WeakReference weakReference = new WeakReference(new HttpUrlConnectionThread(context, 1, str3, hashMap, new HttpCallbackListener() {
                public void callback(String str) {
                    if (str == null) {
                        try {
                            throw new Exception("responseResult null Error");
                        } catch (Exception e) {
                            e.printStackTrace();
                            IgawLogger.Logging(context2, "IGAW_QA", e.getMessage(), 0);
                            return;
                        }
                    }
                    try {
                        IgawLogger.Logging(context2, "IGAW_QA", "redeemRealReward result : " + str, 3);
                        RealRewardResultModel json2RealReward = JSON2ScheduleConverter.json2RealReward(str);
                        if (json2RealReward == null) {
                            Toast.makeText(context2, "\uc774\ubca4\ud2b8\uc5d0 \ucc38\uc5ec\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4. \uc7a0\uc2dc \ud6c4 \ub2e4\uc2dc \uc2dc\ub3c4\ud574\uc8fc\uc138\uc694.", 0).show();
                            RealRewardDAO.getInstance().saveRetryRedeemCache(context2, i2, j2);
                        } else if (json2RealReward.isResult()) {
                            RealRewardDAO.getInstance().clearRetryRedeemCache(context2, i2);
                            RealRewardDAO.getInstance().clearRetryCompleteCache(context2, i2);
                            RealRewardDAO.getInstance().clearSessions(context2, i2);
                            RealRewardDAO.getInstance().saveCompletedRealRewardKey(context2, i2);
                            Builder builder = new Builder(context2);
                            builder.setMessage("\uc694\uccad\ud558\uc2e0 \uc774\uba54\uc77c\ub85c \uad50\ud658\ucfe0\ud3f0\uc774 \uc9c0\uae09\ub418\uc5c8\uc2b5\ub2c8\ub2e4. \uc774\uba54\uc77c\uc744 \ud655\uc778\ud574\ubcf4\uc138\uc694!");
                            builder.setTitle("\uc54c\ub9bc");
                            builder.setCancelable(false);
                            builder.setPositiveButton("\ud655\uc778", new OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.show();
                        } else {
                            Toast.makeText(context2, json2RealReward.getResultMessage(), 0).show();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        IgawLogger.Logging(context2, "IGAW_QA", "redeemRealReward error : " + e2.toString(), 3);
                    }
                }
            }));
            ((Thread) weakReference.get()).setDaemon(true);
            ((Thread) weakReference.get()).start();
        } catch (Exception e) {
            e.printStackTrace();
            IgawLogger.Logging(context, "IGAW_QA", "redeemRealReward error : " + e.toString(), 3);
        }
    }

    public void getViralInfo(final Context context, String str, int i, long j, String str2, final ADBrixHttpCallbackListener<ViralInfoModel> aDBrixHttpCallbackListener) {
        String str3 = "http://live.adbrix.igaworks.com/adpopcorn/2/api/mobile/mobileservice.svc/GetPromotingCPIInfoByEncodedQuery";
        String googleAdId = CoreIDDAO.getInstance().getGoogleAdId(context);
        if (!HttpManager.isLive(context)) {
            str3 = "http://staging.igaworks.com/adpopcorn/2/api/mobile/mobileservice.svc/GetPromotingCPIInfoByEncodedQuery";
        }
        try {
            IgawLogger.Logging(context, "IGAW_QA", "getPromotionInfo > getPromotionInfo call send. mediakey = " + str + ", campaignKey = " + i, 3, true);
            HashMap hashMap = new HashMap();
            hashMap.put("mediakey", str);
            hashMap.put("campaignkey", new StringBuilder(String.valueOf(i)).toString());
            hashMap.put("conversionKey", new StringBuilder(String.valueOf(j)).toString());
            hashMap.put("usn", str2);
            hashMap.put("adid", googleAdId);
            WeakReference weakReference = new WeakReference(new HttpUrlConnectionThread(context, 0, str3, hashMap, new HttpCallbackListener() {
                public void callback(String str) {
                    if (str == null) {
                        try {
                            aDBrixHttpCallbackListener.onResponse(null);
                            IgawLogger.Logging(context, "IGAW_QA", "responseResult null Error", 3);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            IgawLogger.Logging(context, "IGAW_QA", e.getMessage(), 0);
                            return;
                        }
                    }
                    try {
                        IgawLogger.Logging(context, "IGAW_QA", "getPromotionInfo result : " + str, 3);
                        ViralInfoModel convert2ViralInfo = JSON2ViralConverter.convert2ViralInfo(str);
                        if (convert2ViralInfo == null) {
                            IgawLogger.Logging(context, "IGAW_QA", "viModel is null", 3);
                        }
                        if (!convert2ViralInfo.isResult()) {
                            IgawLogger.Logging(context, "IGAW_QA", convert2ViralInfo.getResultMsg(), 3);
                        }
                        aDBrixHttpCallbackListener.onResponse(convert2ViralInfo);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        IgawLogger.Logging(context, "IGAW_QA", "getPromotionInfo error : " + e2.toString(), 3);
                    }
                }
            }, true));
            ((Thread) weakReference.get()).setDaemon(true);
            ((Thread) weakReference.get()).start();
        } catch (Exception e) {
            e.printStackTrace();
            IgawLogger.Logging(context, "IGAW_QA", "getPromotionInfo error : " + e.toString(), 3);
        }
    }

    public void getViralUrl(final Context context, String str, int i, int i2, String str2, final ADBrixHttpCallbackListener<ViralUrlModel> aDBrixHttpCallbackListener) {
        String str3 = "http://live.adbrix.igaworks.com/adpopcorn/2/api/mobile/mobileservice.svc/GetPromotingCPITrackingURLByEncodedQuery";
        String googleAdId = CoreIDDAO.getInstance().getGoogleAdId(context);
        if (!HttpManager.isLive(context)) {
            str3 = "http://staging.igaworks.com/adpopcorn/2/api/mobile/mobileservice.svc/GetPromotingCPITrackingURLByEncodedQuery";
        }
        try {
            IgawLogger.Logging(context, "IGAW_QA", "getPromotionInfo > getViralUrl call send. mediakey = " + str + ", campaignKey = " + i + ", conversionKey = " + i2 + ", usn = " + str2 + ", adid = " + googleAdId, 3, true);
            HashMap hashMap = new HashMap();
            hashMap.put("mediakey", new StringBuilder(String.valueOf(str)).toString());
            hashMap.put("campaignkey", new StringBuilder(String.valueOf(i)).toString());
            hashMap.put("conversionkey", new StringBuilder(String.valueOf(i2)).toString());
            hashMap.put("usn", str2);
            hashMap.put("adid", googleAdId);
            WeakReference weakReference = new WeakReference(new HttpUrlConnectionThread(context, 0, str3, hashMap, new HttpCallbackListener() {
                public void callback(String str) {
                    if (str == null) {
                        try {
                            aDBrixHttpCallbackListener.onResponse(null);
                            IgawLogger.Logging(context, "IGAW_QA", "responseResult null Error", 3);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            IgawLogger.Logging(context, "IGAW_QA", e.getMessage(), 0);
                            return;
                        }
                    }
                    try {
                        IgawLogger.Logging(context, "IGAW_QA", "getViralUrl result : " + str, 3);
                        ViralUrlModel convert2ViralUrl = JSON2ViralConverter.convert2ViralUrl(str);
                        if (convert2ViralUrl == null) {
                            IgawLogger.Logging(context, "IGAW_QA", "vuModel is null", 3);
                        }
                        if (!convert2ViralUrl.isResult()) {
                            IgawLogger.Logging(context, "IGAW_QA", convert2ViralUrl.getResultMsg(), 3);
                        }
                        aDBrixHttpCallbackListener.onResponse(convert2ViralUrl);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        IgawLogger.Logging(context, "IGAW_QA", "getViralUrl error : " + e2.toString(), 3);
                    }
                }
            }, true));
            ((Thread) weakReference.get()).setDaemon(true);
            ((Thread) weakReference.get()).start();
        } catch (Exception e) {
            e.printStackTrace();
            IgawLogger.Logging(context, "IGAW_QA", "getViralUrl error : " + e.toString(), 3);
        }
    }

    private void restoreCPESingleConversion(final Context context, final int i, final int i2) {
        Task.forResult(null).continueWith(new Continuation<Object, Void>() {
            public Void then(Task<Object> task) {
                ConversionDAOForRetryCompletion.getDAO(context).updateOrInsertConversionForRetry(i);
                IgawLogger.Logging(context, "IGAW_QA", "callback complete cpe error occurred : resultCode = " + i2, 3, false);
                return null;
            }
        }, Task.BACKGROUND_EXECUTOR);
    }

    private void restoreCPEConversionList(final Context context, final ArrayList<Integer> arrayList) {
        Task.forResult(null).continueWith(new Continuation<Object, Void>() {
            public Void then(Task<Object> task) {
                ConversionDAOForRetryCompletion dao = ConversionDAOForRetryCompletion.getDAO(context);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    dao.updateOrInsertConversionForRetry(((Integer) it.next()).intValue());
                }
                return null;
            }
        }, Task.BACKGROUND_EXECUTOR);
    }

    public void makeCompleteToast(Context context, long j, String str) {
        if (j > 0 && str != null && str.length() > 0 && !str.equals("null")) {
            final Toast makeText = Toast.makeText(context, str, 0);
            makeText.show();
            new CountDownTimer(j, 100) {
                public void onTick(long j) {
                    makeText.show();
                }

                public void onFinish() {
                    makeText.show();
                }
            }.start();
        }
    }

    private void preDownloadImage(Context context, String str) {
        if (str != null) {
            try {
                if (str.length() >= 1 && CommonHelper.CheckPermissionForCommonSDK(context)) {
                    String trim = str.trim();
                    try {
                        CPECompletionHandler.getImageDownloader(context).download(trim, null, null, null, new ImageDownloadAsyncCallback(trim, null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                            public void onResultCustom(Bitmap bitmap) {
                                bitmap.recycle();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
