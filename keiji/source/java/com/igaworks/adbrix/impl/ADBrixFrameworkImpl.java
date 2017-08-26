package com.igaworks.adbrix.impl;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.WindowManager.LayoutParams;
import com.igaworks.adbrix.core.ADBrixHttpManager;
import com.igaworks.adbrix.core.OnGetSchedule;
import com.igaworks.adbrix.cpe.CPECompletionHandler;
import com.igaworks.adbrix.cpe.PromotionHandler;
import com.igaworks.adbrix.db.ConversionDAOForRetryCompletion;
import com.igaworks.adbrix.db.DailyPlayDAO;
import com.igaworks.adbrix.db.RealRewardDAO;
import com.igaworks.adbrix.db.ScheduleDAO;
import com.igaworks.adbrix.db.ViralCPIDAO;
import com.igaworks.adbrix.goods.AnipangGoodsOfferDialog;
import com.igaworks.adbrix.goods.GoodsConstant;
import com.igaworks.adbrix.goods.GoodsOfferDialog;
import com.igaworks.adbrix.goods.GoodsOfferManager;
import com.igaworks.adbrix.goods.GoodsOfferModel;
import com.igaworks.adbrix.interfaces.ADBrixHttpCallbackListener;
import com.igaworks.adbrix.interfaces.ADBrixInterface;
import com.igaworks.adbrix.model.Engagement;
import com.igaworks.adbrix.model.RealReward;
import com.igaworks.adbrix.model.RetryCompleteConversion;
import com.igaworks.adbrix.model.ViralCPIModel;
import com.igaworks.adbrix.model.ViralInfoModel;
import com.igaworks.adbrix.viral.ViralInfoDialog;
import com.igaworks.core.DeviceIDManger;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.dao.CPESessionImpressionDAO;
import com.igaworks.dao.tracking.TrackingActivitySQLiteDB;
import com.igaworks.impl.CommonFrameworkImpl;
import com.igaworks.impl.InternalAction;
import com.igaworks.interfaces.CommonActivityListener;
import com.igaworks.interfaces.ExtendedCommonActivityListener;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.bolts_task.Continuation;
import com.igaworks.util.bolts_task.Task;
import com.igaworks.util.bolts_task.TaskUtils;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;
import org.json.JSONArray;
import org.json.JSONObject;

class ADBrixFrameworkImpl extends CommonFrameworkImpl implements ADBrixInterface, CommonActivityListener, ExtendedCommonActivityListener {
    private static int DL_PLAY_TIME_PASS = 0;
    private static long DL_SESSION_START_TIME = 0;
    private static Object lockObj = new Object();
    public static boolean onProcessDailyPlay = false;
    private static int retryTime = 0;
    protected Activity activity;
    private AdPopCornDailyPlayTimerTask dailyPlayCheckTask;
    private Dialog god;
    protected ADBrixHttpManager httpManager = null;
    private Timer timer;
    private Dialog vid;

    class AdPopCornDailyPlayTimerTask extends TimerTask {
        AdPopCornDailyPlayTimerTask() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r7 = this;
            r0 = com.igaworks.adbrix.core.ADBrixHttpManager.schedule;	 Catch:{ Exception -> 0x008d }
            if (r0 == 0) goto L_0x00e0;
        L_0x0004:
            r0 = com.igaworks.impl.CommonFrameworkImpl.getContext();	 Catch:{ Exception -> 0x008d }
            r0 = com.igaworks.core.RequestParameter.getATRequestParameter(r0);	 Catch:{ Exception -> 0x008d }
            r0 = r0.getReferralKey();	 Catch:{ Exception -> 0x008d }
            r2 = -1;
            r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r0 <= 0) goto L_0x00e0;
        L_0x0016:
            r0 = 0;
            com.igaworks.adbrix.impl.ADBrixFrameworkImpl.retryTime = r0;	 Catch:{ Exception -> 0x008d }
            r1 = com.igaworks.adbrix.impl.ADBrixFrameworkImpl.lockObj;	 Catch:{ Exception -> 0x008d }
            monitor-enter(r1);	 Catch:{ Exception -> 0x008d }
            r0 = com.igaworks.adbrix.impl.ADBrixFrameworkImpl.onProcessDailyPlay;	 Catch:{ all -> 0x00a8 }
            if (r0 == 0) goto L_0x0025;
        L_0x0023:
            monitor-exit(r1);	 Catch:{ all -> 0x00a8 }
        L_0x0024:
            return;
        L_0x0025:
            r0 = 1;
            com.igaworks.adbrix.impl.ADBrixFrameworkImpl.onProcessDailyPlay = r0;	 Catch:{ all -> 0x00a8 }
            monitor-exit(r1);	 Catch:{ all -> 0x00a8 }
            r0 = com.igaworks.adbrix.core.ADBrixHttpManager.schedule;	 Catch:{ Exception -> 0x008d }
            r0 = r0.getSchedule();	 Catch:{ Exception -> 0x008d }
            r0 = r0.getReEngagement();	 Catch:{ Exception -> 0x008d }
            r0 = r0.getDailyPlay();	 Catch:{ Exception -> 0x008d }
            if (r0 == 0) goto L_0x00bd;
        L_0x0039:
            r1 = com.igaworks.impl.CommonFrameworkImpl.getContext();	 Catch:{ Exception -> 0x008d }
            r1 = com.igaworks.core.RequestParameter.getATRequestParameter(r1);	 Catch:{ Exception -> 0x008d }
            r2 = r1.getReferralKey();	 Catch:{ Exception -> 0x008d }
            r1 = (int) r2;	 Catch:{ Exception -> 0x008d }
            if (r1 <= 0) goto L_0x00ab;
        L_0x0048:
            r2 = com.igaworks.impl.CommonFrameworkImpl.getContext();	 Catch:{ Exception -> 0x008d }
            if (r2 == 0) goto L_0x0077;
        L_0x004e:
            r2 = com.igaworks.adbrix.db.DailyPlayDAO.getInstance();	 Catch:{ Exception -> 0x008d }
            r3 = com.igaworks.impl.CommonFrameworkImpl.getContext();	 Catch:{ Exception -> 0x008d }
            r2.saveLastConversionDateTime(r3);	 Catch:{ Exception -> 0x008d }
            r2 = com.igaworks.impl.CommonFrameworkImpl.getContext();	 Catch:{ Exception -> 0x008d }
            r3 = "IGAW_QA";
            r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x008d }
            r5 = "ADBrixManager >> ReEngagement DailyPlayStepList size: ";
            r4.<init>(r5);	 Catch:{ Exception -> 0x008d }
            r5 = r0.size();	 Catch:{ Exception -> 0x008d }
            r4 = r4.append(r5);	 Catch:{ Exception -> 0x008d }
            r4 = r4.toString();	 Catch:{ Exception -> 0x008d }
            r5 = 3;
            r6 = 1;
            com.igaworks.core.IgawLogger.Logging(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x008d }
        L_0x0077:
            r2 = com.igaworks.impl.CommonFrameworkImpl.getContext();	 Catch:{ Exception -> 0x008d }
            com.igaworks.adbrix.cpe.DailyPlayHandler.checkandComplete(r2, r0, r1);	 Catch:{ Exception -> 0x008d }
            r0 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
            r0 = com.igaworks.util.bolts_task.Task.delay(r0);	 Catch:{ Exception -> 0x008d }
            r1 = new com.igaworks.adbrix.impl.ADBrixFrameworkImpl$AdPopCornDailyPlayTimerTask$1;	 Catch:{ Exception -> 0x008d }
            r1.<init>();	 Catch:{ Exception -> 0x008d }
            r0.continueWith(r1);	 Catch:{ Exception -> 0x008d }
            goto L_0x0024;
        L_0x008d:
            r0 = move-exception;
            r1 = "IGAW_QA";
            r2 = new java.lang.StringBuilder;
            r3 = "DailyPlayTimerTask Error: ";
            r2.<init>(r3);
            r0 = r0.getMessage();
            r0 = r2.append(r0);
            r0 = r0.toString();
            android.util.Log.e(r1, r0);
            goto L_0x0024;
        L_0x00a8:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x00a8 }
            throw r0;	 Catch:{ Exception -> 0x008d }
        L_0x00ab:
            r0 = com.igaworks.impl.CommonFrameworkImpl.getContext();	 Catch:{ Exception -> 0x008d }
            r1 = "IGAW_QA";
            r2 = "DailyPlay ReEngaement: Organic User";
            r3 = 3;
            r4 = 1;
            com.igaworks.core.IgawLogger.Logging(r0, r1, r2, r3, r4);	 Catch:{ Exception -> 0x008d }
            r0 = 0;
            com.igaworks.adbrix.impl.ADBrixFrameworkImpl.onProcessDailyPlay = r0;	 Catch:{ Exception -> 0x008d }
            goto L_0x0024;
        L_0x00bd:
            r0 = com.igaworks.impl.CommonFrameworkImpl.getContext();	 Catch:{ Exception -> 0x008d }
            if (r0 == 0) goto L_0x00db;
        L_0x00c3:
            r0 = com.igaworks.impl.CommonFrameworkImpl.getContext();	 Catch:{ Exception -> 0x008d }
            r1 = "IGAW_QA";
            r2 = "DailyPlay ReEngaement: Null ";
            r3 = 3;
            r4 = 1;
            com.igaworks.core.IgawLogger.Logging(r0, r1, r2, r3, r4);	 Catch:{ Exception -> 0x008d }
            r0 = com.igaworks.adbrix.db.DailyPlayDAO.getInstance();	 Catch:{ Exception -> 0x008d }
            r1 = com.igaworks.impl.CommonFrameworkImpl.getContext();	 Catch:{ Exception -> 0x008d }
            r0.saveLastConversionDateTime(r1);	 Catch:{ Exception -> 0x008d }
        L_0x00db:
            r0 = 0;
            com.igaworks.adbrix.impl.ADBrixFrameworkImpl.onProcessDailyPlay = r0;	 Catch:{ Exception -> 0x008d }
            goto L_0x0024;
        L_0x00e0:
            r0 = com.igaworks.adbrix.impl.ADBrixFrameworkImpl.retryTime;	 Catch:{ Exception -> 0x008d }
            r0 = r0 + 1;
            com.igaworks.adbrix.impl.ADBrixFrameworkImpl.retryTime = r0;	 Catch:{ Exception -> 0x008d }
            r0 = com.igaworks.adbrix.impl.ADBrixFrameworkImpl.retryTime;	 Catch:{ Exception -> 0x008d }
            r1 = 5;
            if (r0 >= r1) goto L_0x0024;
        L_0x00f0:
            r0 = com.igaworks.adbrix.impl.ADBrixFrameworkImpl.this;	 Catch:{ Exception -> 0x008d }
            r0.startDailyPlayCheckTask();	 Catch:{ Exception -> 0x008d }
            goto L_0x0024;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.igaworks.adbrix.impl.ADBrixFrameworkImpl.AdPopCornDailyPlayTimerTask.run():void");
        }
    }

    protected ADBrixFrameworkImpl() {
    }

    public ADBrixHttpManager getHttpManager(Context context) {
        initAppInfo(context);
        if (this.httpManager == null) {
            this.httpManager = ADBrixHttpManager.getManager(context);
        }
        return this.httpManager;
    }

    public void firstTimeExperience(String str, String str2) {
        activity("fte", str, str2, null, appContext);
    }

    public void retention(String str, String str2) {
        activity("ret", str, str2, null, appContext);
    }

    public void showRealRewardNotice(Activity activity) {
        Object obj = null;
        try {
            if (!(this.activity == null || this.activity.getTaskId() == activity.getTaskId())) {
                obj = 1;
            }
            if (activity != null) {
                this.activity = activity;
            }
            GoodsOfferManager.getManager(this.activity);
            if (ADBrixHttpManager.schedule == null) {
                IgawLogger.Logging(appContext, "IGAW_QA", "showRealRewardNotice > schedule is null", 3);
                GoodsOfferManager.getManager(this.activity).setRunRealEngagement(true);
            } else if (activity == null || ADBrixHttpManager.schedule.getSchedule() == null || ADBrixHttpManager.schedule.getSchedule().getRealRewards() == null || ADBrixHttpManager.schedule.getSchedule().getRealRewards().size() < 1) {
                IgawLogger.Logging(appContext, "IGAW_QA", "showRealRewardNotice > real reward schedule is null", 3);
            } else {
                if (obj == null) {
                    if (this.god == null || !this.god.isShowing()) {
                        RealReward realReward = (RealReward) ADBrixHttpManager.schedule.getSchedule().getRealRewards().get(0);
                        if (!realReward.isIsTest()) {
                            if (!realReward.isIsDailyEvent() && RealRewardDAO.getInstance().isCompetedRealReward(activity, realReward.getRealRewardKey())) {
                                IgawLogger.Logging(appContext, "IGAW_QA", "showRealRewardNotice > already completed event", 3);
                                return;
                            } else if (realReward.isIsDailyEvent() && RealRewardDAO.getInstance().hasCompleteToday(activity, realReward.getRealRewardKey())) {
                                IgawLogger.Logging(appContext, "IGAW_QA", "showRealRewardNotice > already completed event at today", 3);
                                return;
                            } else {
                                List<RealReward> realRewards = ADBrixHttpManager.schedule.getSchedule().getRealRewards();
                                Map activeSessionNo = RealRewardDAO.getInstance().getActiveSessionNo(this.activity);
                                Object obj2 = null;
                                for (RealReward realReward2 : realRewards) {
                                    for (String str : activeSessionNo.keySet()) {
                                        if (str.equals(new StringBuilder(String.valueOf(realReward2.getRealRewardKey())).toString())) {
                                            if (realReward2.getProgressValidTime() > (new Date().getTime() - RealRewardDAO.getInstance().getSessionTime(this.activity, ((Long) activeSessionNo.get(str)).longValue())) / 60000) {
                                                IgawLogger.Logging(appContext, "IGAW_QA", "showRealRewardNotice > hasActiveSession : rrk = " + str, 3);
                                                obj2 = 1;
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (obj2 != null) {
                                    return;
                                }
                            }
                        }
                        GoodsOfferModel goodsOfferModel = new GoodsOfferModel();
                        goodsOfferModel.setMainImg(realReward.getRealRewardImageUrl());
                        goodsOfferModel.setMidText(realReward.getMissionText());
                        goodsOfferModel.setType(1);
                        goodsOfferModel.setRealRewardKey(realReward.getRealRewardKey());
                        goodsOfferModel.setConversionKey(realReward.getConversionKey());
                        goodsOfferModel.setDailyEvent(realReward.isIsDailyEvent());
                        goodsOfferModel.setNoCondition(realReward.isNoCondition());
                        goodsOfferModel.setTest(realReward.isIsTest());
                        if (RequestParameter.getATRequestParameter(this.activity).getAppkey() == null) {
                            initAppInfo(this.activity);
                        }
                        try {
                            LayoutParams layoutParams;
                            if (GoodsConstant.anipangApps.contains(RequestParameter.getATRequestParameter(this.activity).getAppkey()) || realReward.isNoCondition()) {
                                goodsOfferModel.setTitleImg("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/title_00.png");
                                this.god = new AnipangGoodsOfferDialog(activity, goodsOfferModel);
                                layoutParams = new LayoutParams();
                                layoutParams.copyFrom(this.god.getWindow().getAttributes());
                                layoutParams.width = -1;
                                layoutParams.height = -2;
                                this.god.getWindow().setAttributes(layoutParams);
                                this.god.show();
                                return;
                            }
                            goodsOfferModel.setTitleImg("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/title_01_large.png");
                            this.god = new GoodsOfferDialog(this.activity, goodsOfferModel);
                            layoutParams = new LayoutParams();
                            layoutParams.copyFrom(this.god.getWindow().getAttributes());
                            layoutParams.width = -1;
                            layoutParams.height = -2;
                            this.god.getWindow().setAttributes(layoutParams);
                            this.god.show();
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            this.god = null;
                            return;
                        }
                    }
                }
                IgawLogger.Logging(appContext, "IGAW_QA", "showRealRewardNotice > already show dialog", 3);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void buy(String str, String str2) {
        activity("buy", str, str2, null, appContext);
    }

    public void onActivityCalled(Context context, String str, String str2, RequestParameter requestParameter) {
        IgawLogger.Logging(context, "IGAW_QA", String.format("onActivityCalled > group - %s, activity - %s", new Object[]{str, str2}), 3);
        CPECompletionHandler.checkAndComplete(context, str, str2, requestParameter, ADBrixHttpManager.getManager(context), null);
    }

    public void onStartSession(final Context context, final RequestParameter requestParameter, final boolean z) {
        synchronized (lockObj) {
            DL_SESSION_START_TIME = SystemClock.elapsedRealtime();
        }
        Task.forResult(null).continueWithTask(new Continuation<Object, Task<Void>>() {
            public Task<Void> then(Task<Object> task) {
                String lastOnStartSessionDateTime = DailyPlayDAO.getInstance().getLastOnStartSessionDateTime(context);
                try {
                    if (!lastOnStartSessionDateTime.equals(BuildConfig.FLAVOR)) {
                        DailyPlayDAO.getInstance().setLastOnStartSessionDateTime(context);
                        Calendar instance = Calendar.getInstance();
                        Calendar instance2 = Calendar.getInstance();
                        instance.setTime(DailyPlayDAO.sdf.parse(lastOnStartSessionDateTime));
                        if (instance2.get(5) != instance.get(5)) {
                            ADBrixFrameworkImpl.DL_PLAY_TIME_PASS = 0;
                        }
                    }
                } catch (Exception e) {
                    Log.e("IGAW_QA", "Check startSessionDateTime Error: " + e.getMessage());
                    ADBrixFrameworkImpl.DL_PLAY_TIME_PASS = 0;
                }
                ADBrixHttpManager httpManager = ADBrixFrameworkImpl.this.getHttpManager(context);
                IgawLogger.Logging(context, "IGAW_QA", "adbrix onStartSession called", 3);
                if (context instanceof Activity) {
                    GoodsOfferManager.getManager((Activity) context);
                    if (PromotionHandler.dialogOpenner != null) {
                        PromotionHandler.dialogOpenner = (Activity) context;
                    }
                } else {
                    IgawLogger.Logging(context, "IGAW_QA", "context is not instance of Activity", 1);
                }
                if (!z || ADBrixHttpManager.schedule == null) {
                    httpManager.getScheduleForADBrix(requestParameter, context, DeviceIDManger.getInstance(context).getAESPuid(context), ScheduleDAO.getInstance().getSchedule(context));
                }
                if (ADBrixHttpManager.schedule != null) {
                    ADBrixFrameworkImpl.this.retryCPEConversion(context);
                    if (RequestParameter.getATRequestParameter(context).getReferralKey() > 0) {
                        Log.d("IGAW_QA", "DailyPlay start and check");
                        ADBrixFrameworkImpl.this.DailyPlayStart(context);
                    }
                } else {
                    ADBrixFrameworkImpl.this.setGetScheduleEventListener();
                }
                if (!z) {
                    CPESessionImpressionDAO.getInstance().clearImpressionData(context);
                    PromotionHandler.nextCampaigns.clear();
                }
                return null;
            }
        }, InternalAction.NETWORK_EXECUTOR);
    }

    public void showViralCPINotice(final Activity activity) {
        RequestParameter aTRequestParameter = RequestParameter.getATRequestParameter(activity);
        if (ADBrixHttpManager.schedule == null || aTRequestParameter.getReferralKey() < 0) {
            IgawLogger.Logging(activity, "IGAW_QA", "showViralCPINotice - ADBrixHttpManager.schedule == null. Save restore info", 3);
            ViralCPIDAO.saveRestoreViralDialog(activity);
            return;
        }
        ViralCPIDAO.setRestoreViralCPI(false);
        if (ADBrixHttpManager.schedule.getSchedule() != null && ADBrixHttpManager.schedule.getSchedule().getViralCPIs() != null && ADBrixHttpManager.schedule.getSchedule().getViralCPIs().size() > 0) {
            IgawLogger.Logging(activity, "IGAW_QA", "showViralCPINotice - viral cpi campaign exists.", 3);
            for (final ViralCPIModel viralCPIModel : ADBrixHttpManager.schedule.getSchedule().getViralCPIs()) {
                if (ViralCPIDAO.getInstance().isDoNotShow(activity, viralCPIModel.getCampaignKey())) {
                    IgawLogger.Logging(activity, "IGAW_QA", "showViralCPINotice - isDoNotShow is true.", 3);
                } else {
                    IgawLogger.Logging(activity, "IGAW_QA", String.format("showViralCPINotice : ref_key = %d, parent_conversion_key = %d", new Object[]{Long.valueOf(aTRequestParameter.getReferralKey()), Long.valueOf(viralCPIModel.getParentConversionKey())}), 3);
                    if (RequestParameter.getATRequestParameter(activity).getReferralKey() == viralCPIModel.getParentConversionKey()) {
                        ADBrixHttpManager.getManager(activity).getViralInfo(activity, aTRequestParameter.getADBrixUserInfo_SubReferralKey(), viralCPIModel.getCampaignKey(), viralCPIModel.getParentConversionKey(), aTRequestParameter.getADBrixUserInfo_Refusn(), new ADBrixHttpCallbackListener<ViralInfoModel>() {
                            public void onResponse(ViralInfoModel viralInfoModel) {
                                if (viralInfoModel == null) {
                                    IgawLogger.Logging(activity, "IGAW_QA", "result is null", 3);
                                } else if (viralInfoModel.isResult()) {
                                    try {
                                        viralCPIModel.setItemURL(viralInfoModel.getImageURL());
                                        viralCPIModel.setItemName(viralInfoModel.getItemName());
                                        viralCPIModel.setItemQuantity(viralInfoModel.getItemQuantity());
                                        ADBrixFrameworkImpl.this.vid = new ViralInfoDialog(activity, viralCPIModel, 1);
                                        LayoutParams layoutParams = new LayoutParams();
                                        layoutParams.copyFrom(ADBrixFrameworkImpl.this.vid.getWindow().getAttributes());
                                        layoutParams.width = -1;
                                        layoutParams.height = -2;
                                        ADBrixFrameworkImpl.this.vid.getWindow().setAttributes(layoutParams);
                                        ADBrixFrameworkImpl.this.vid.show();
                                    } catch (Exception e) {
                                        if (e != null) {
                                            IgawLogger.Logging(activity, "IGAW_QA", "ViralInfoDialog error : " + e.toString(), 3);
                                        }
                                    }
                                } else {
                                    switch (viralInfoModel.getResultCode()) {
                                        case 999:
                                            ViralCPIDAO.getInstance().saveDoNotShow(activity, viralCPIModel.getCampaignKey());
                                            break;
                                        case 1500:
                                            ViralCPIDAO.getInstance().saveDoNotShow(activity, viralCPIModel.getCampaignKey());
                                            break;
                                    }
                                    IgawLogger.Logging(activity, "IGAW_QA", "result is false : " + viralInfoModel.getResultCode(), 3);
                                }
                            }
                        });
                        return;
                    }
                }
            }
        }
    }

    public void addConversionCache(final Context context, final RequestParameter requestParameter, final String str) {
        Task.delay(1000).continueWith(new Continuation<Void, Void>() {
            public Void then(Task<Void> task) {
                if (ADBrixHttpManager.schedule != null) {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.getBoolean("Result") && !jSONObject.isNull("Data")) {
                        JSONArray jSONArray = new JSONArray(new JSONObject(jSONObject.getString("Data")).getString("conversion_key_list"));
                        for (int i = 0; i < jSONArray.length(); i++) {
                            int i2 = jSONArray.getInt(i);
                            IgawLogger.Logging(context, "IGAW_QA", "onReferralResponse - addConversionCache > key : " + i2, 3, false);
                            if (i2 != -1 && (requestParameter.getConversionCache() == null || !requestParameter.getConversionCache().contains(Integer.valueOf(i2)))) {
                                List engagements = ADBrixHttpManager.schedule.getSchedule().getEngagements();
                                for (int i3 = 0; i3 < engagements.size(); i3++) {
                                    Engagement engagement = (Engagement) engagements.get(i3);
                                    if (engagement.getConversionKey() == i2) {
                                        String completeMessage = engagement.getDisplayData().getCompleteMessage();
                                        IgawLogger.Logging(context, "IGAW_QA", "callback complete cpe by referral > msg : " + completeMessage + ", duration : " + engagement.getDisplayData().getCompleteToastMSec(), 3);
                                        if (engagement.getDisplayData().getCompleteToastMSec() > 0 && completeMessage != null && completeMessage.length() > 0 && !completeMessage.equals("null")) {
                                            ADBrixHttpManager.getManager(context).makeCompleteToast(context, (long) engagement.getDisplayData().getCompleteToastMSec(), completeMessage);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return null;
            }
        }, InternalAction.NETWORK_EXECUTOR);
    }

    public void restoreCPEAction_onGetReferralResponse(Context context, RequestParameter requestParameter) {
        if (ADBrixHttpManager.schedule != null) {
            IgawLogger.Logging(context, "IGAW_QA", "onReferralResponse - restoreCPEAction", 3);
            CPECompletionHandler.restoreCPEAction(context, requestParameter, ADBrixHttpManager.getManager(context));
            return;
        }
        Log.i("IGAW_QA", "Adbrix SDK waiting for schedule...");
    }

    public void restoreCPEAction_OnGetSchedule(Context context, RequestParameter requestParameter) {
        if (requestParameter.getADBrixUserNo() > 0) {
            IgawLogger.Logging(context, "IGAW_QA", "OnGetSchedule - restoreCPEAction", 3);
            CPECompletionHandler.restoreCPEAction(context, requestParameter, ADBrixHttpManager.getManager(context));
            return;
        }
        Log.i("IGAW_QA", "Adbrix SDK waiting for referrer...");
    }

    public void onGetReferralResponse(Context context, String str) {
        try {
            IgawLogger.Logging(context, "IGAW_QA", "onReferralResponse called.", 3);
            RequestParameter aTRequestParameter = RequestParameter.getATRequestParameter(context);
            restoreCPEAction_onGetReferralResponse(context, aTRequestParameter);
            addConversionCache(context, aTRequestParameter, str);
            if (ADBrixHttpManager.schedule != null && aTRequestParameter.getReferralKey() > -1 && ViralCPIDAO.isRestoreViralCPI() && ViralCPIDAO.getActivity() != null) {
                IgawLogger.Logging(context, "IGAW_QA", "onReferralResponse - RestoreViralCPIDialog", 3);
                try {
                    ADBrixFrameworkFactory.getFramework().showViralCPINotice(ViralCPIDAO.getActivity());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Task.delay(2000).continueWith(new Continuation<Void, Void>() {
                public Void then(Task<Void> task) {
                    if (ADBrixHttpManager.schedule == null) {
                        Log.d("IGAW_QA", "DailyPlay is waiting schedule");
                    } else if (RequestParameter.getATRequestParameter(CommonFrameworkImpl.getContext()).getReferralKey() == 0) {
                        Log.d("IGAW_QA", "DailyPlay Skip: Organic");
                    } else {
                        Log.d("IGAW_QA", "DailyPlay start and check");
                        ADBrixFrameworkImpl.this.DailyPlayStart(CommonFrameworkImpl.getContext());
                    }
                    return null;
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onEndSession(Context context, int i) {
        IgawLogger.Logging(context, "IGAW_QA", "adbrix onEndSession called", 3, true);
        synchronized (lockObj) {
            if (DL_SESSION_START_TIME > 0) {
                DL_PLAY_TIME_PASS = (int) ((SystemClock.elapsedRealtime() - DL_SESSION_START_TIME) + ((long) DL_PLAY_TIME_PASS));
                DL_SESSION_START_TIME = 0;
            } else {
                Log.e("IGAW_QA", "StartSession and EndSession are pair. Must call startSession before endSession");
                DL_PLAY_TIME_PASS = 0;
            }
            IgawLogger.Logging(context, "IGAW_QA", "DailyPlay >> elapsed: " + DL_PLAY_TIME_PASS + " ms", 3, false);
            if (this.timer != null) {
                this.timer.cancel();
                this.timer = null;
            }
        }
    }

    private void DailyPlayStart(Context context) {
        if (DailyPlayDAO.getInstance().canJoinCampaignToday(CommonFrameworkImpl.getContext())) {
            startDailyPlayCheckTask();
        }
    }

    private void startDailyPlayCheckTask() {
        try {
            synchronized (lockObj) {
                if (this.timer != null) {
                    IgawLogger.Logging(CommonFrameworkImpl.getContext(), "IGAW_QA", "Start DailyPlay Timer again.", 2, true);
                    this.timer.cancel();
                }
                this.timer = new Timer();
                this.dailyPlayCheckTask = new AdPopCornDailyPlayTimerTask();
            }
            int playTime = DailyPlayDAO.getInstance().getPlayTime(CommonFrameworkImpl.getContext());
            IgawLogger.Logging(CommonFrameworkImpl.getContext(), "IGAW_QA", "startDailyPlayCheckTask ... REQUIRED_PLAY_TIME: " + playTime + ">> Elapsed: " + DL_PLAY_TIME_PASS, 2, true);
            playTime -= DL_PLAY_TIME_PASS;
            if (playTime > GameControllerDelegate.THUMBSTICK_LEFT_X) {
                this.timer.schedule(this.dailyPlayCheckTask, (long) playTime);
            } else {
                this.timer.schedule(this.dailyPlayCheckTask, 1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void retryCPEConversion(final Context context) {
        Task.BACKGROUND_EXECUTOR.execute(new Runnable() {
            public void run() {
                if (CommonHelper.checkInternetConnection(context)) {
                    ConversionDAOForRetryCompletion dao = ConversionDAOForRetryCompletion.getDAO(context);
                    List<RetryCompleteConversion> retryConversions = dao.getRetryConversions();
                    if (retryConversions != null && retryConversions.size() > 0) {
                        IgawLogger.Logging(context, "IGAW_QA", "cpe complete retry start, the num of conversion = " + retryConversions.size(), 3);
                        ArrayList arrayList = new ArrayList();
                        for (RetryCompleteConversion retryCompleteConversion : retryConversions) {
                            if (retryCompleteConversion.getRetryCount() >= 3) {
                                dao.removeRetryCount(retryCompleteConversion.getConversionKey());
                                IgawLogger.Logging(context, "IGAW_QA", "cpe complete retry failed 3 times, conversionKey = " + retryCompleteConversion.getConversionKey(), 3);
                            } else {
                                arrayList.add(Integer.valueOf(retryCompleteConversion.getConversionKey()));
                                IgawLogger.Logging(context, "IGAW_QA", "cpe complete retry, conversionKey = " + retryCompleteConversion.getConversionKey(), 3);
                            }
                        }
                        try {
                            Task activityListParam = TrackingActivitySQLiteDB.getInstance(CommonFrameworkImpl.getContext()).getActivityListParam(false, CommonFrameworkImpl.getContext(), "session", String.VIDEO_START, ADBrixFrameworkImpl.endSessionParam);
                            TaskUtils.wait(activityListParam);
                            Task impressionData = TrackingActivitySQLiteDB.getInstance(CommonFrameworkImpl.getContext()).getImpressionData(false, CommonFrameworkImpl.getContext());
                            TaskUtils.wait(impressionData);
                            ADBrixFrameworkImpl.this.httpManager.completeCPECallForADBrix(ADBrixFrameworkImpl.parameter, context, (ArrayList) activityListParam.getResult(), (ArrayList) impressionData.getResult(), arrayList);
                        } catch (Exception e) {
                            Log.e("IGAW_QA", "OnStartSession: trackingForAdbrix Error >>" + e.getMessage());
                        }
                    }
                }
            }
        });
    }

    private void setGetScheduleEventListener() {
        ADBrixHttpManager.onGetScheduleEvent = new OnGetSchedule() {
            public void onGetSchedule(Context context, boolean z) {
                if (z) {
                    RequestParameter aTRequestParameter = RequestParameter.getATRequestParameter(context);
                    ADBrixFrameworkImpl.this.restoreCPEAction_OnGetSchedule(context, aTRequestParameter);
                    ADBrixFrameworkImpl.this.retryCPEConversion(context);
                    long referralKey = aTRequestParameter.getReferralKey();
                    if (referralKey == -1) {
                        Log.d("IGAW_QA", "DailyPlay is waiting getReferrer");
                    }
                    if (referralKey == 0) {
                        Log.d("IGAW_QA", "DailyPlay Skip >> Organic");
                    }
                    if (referralKey > 0) {
                        Log.d("IGAW_QA", "DailyPlay start and check");
                        ADBrixFrameworkImpl.this.DailyPlayStart(context);
                    }
                }
            }
        };
    }
}
