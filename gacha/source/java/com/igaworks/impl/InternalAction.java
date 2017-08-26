package com.igaworks.impl;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import com.igaworks.IgawCommon;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.dao.ActivityInfoDAO;
import com.igaworks.dao.AppImpressionDAO;
import com.igaworks.dao.ReferralInfoDAO;
import com.igaworks.dao.tracking.TrackingActivitySQLiteDB;
import com.igaworks.net.CommonHttpManager;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.bolts_task.Continuation;
import com.igaworks.util.bolts_task.Task;
import com.igaworks.util.bolts_task.TaskUtils;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.cocos2dx.lib.BuildConfig;

public class InternalAction {
    private static final int CORE_POOL_SIZE = ((CPU_COUNT * 2) + 1);
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int MAX_POOL_SIZE = (((CPU_COUNT * 2) * 2) + 1);
    public static final ExecutorService NETWORK_EXECUTOR = newThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(128), sThreadFactory);
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "Igaworks.NETWORK_EXECUTOR-thread-" + this.mCount.getAndIncrement());
        }
    };
    private static InternalAction singleton;

    private InternalAction() {
    }

    public static InternalAction getInstance() {
        if (singleton == null) {
            synchronized (InternalAction.class) {
                if (singleton == null) {
                    singleton = new InternalAction();
                }
            }
        }
        return singleton;
    }

    private static ThreadPoolExecutor newThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i2, j, timeUnit, blockingQueue, threadFactory);
        if (VERSION.SDK_INT >= 9) {
            threadPoolExecutor.allowCoreThreadTimeOut(true);
        }
        return threadPoolExecutor;
    }

    public void sendOphanActivities(final Context context, final boolean z, final CommonHttpManager commonHttpManager) {
        Task.forResult(null).continueWith(new Continuation<Void, Void>() {
            public Void then(Task<Void> task) {
                int i = 0;
                if (z) {
                    Log.i("IGAW_QA", "sendOphanActivities");
                }
                if (CommonHelper.checkInternetConnection(context) || z) {
                    try {
                        Task orphanTracking = TrackingActivitySQLiteDB.getInstance(context).getOrphanTracking(context, "tbl_AppTracking");
                        TaskUtils.wait(orphanTracking);
                        Task orphanTracking2 = TrackingActivitySQLiteDB.getInstance(context).getOrphanTracking(context, "tbl_ImpressionTracking");
                        TaskUtils.wait(orphanTracking2);
                        ArrayList arrayList = (ArrayList) orphanTracking.getResult();
                        ArrayList arrayList2 = (ArrayList) orphanTracking2.getResult();
                        if ((arrayList != null && arrayList.size() > 0) || (arrayList2 != null && arrayList2.size() > 0)) {
                            Context context = context;
                            String str = "IGAW_QA";
                            String str2 = "Send orphan tracking data to Adbrix data - activity : %d, imp : %d";
                            Object[] objArr = new Object[2];
                            objArr[0] = Integer.valueOf(arrayList == null ? 0 : arrayList.size());
                            if (arrayList2 != null) {
                                i = arrayList2.size();
                            }
                            objArr[1] = Integer.valueOf(i);
                            IgawLogger.Logging(context, str, String.format(str2, objArr), 2, true);
                            commonHttpManager.trackingForADBrix(RequestParameter.getATRequestParameter(context), context, arrayList, arrayList2);
                        }
                    } catch (Exception e) {
                        Log.e("IGAW_QA", "OnStartApplication: Send orphan tracking data to Adbrix >> Error >>" + e.getMessage());
                    }
                }
                return null;
            }
        }, NETWORK_EXECUTOR);
    }

    public void referrerCallForAdbrix(Context context, boolean z, RequestParameter requestParameter, CommonHttpManager commonHttpManager) {
        final Context context2 = context;
        final boolean z2 = z;
        final RequestParameter requestParameter2 = requestParameter;
        final CommonHttpManager commonHttpManager2 = commonHttpManager;
        Task.delay(1000).continueWith(new Continuation<Void, Void>() {
            public Void then(Task<Void> task) {
                if (CommonHelper.checkInternetConnection(context2) || z2) {
                    if (z2) {
                        Log.i("IGAW_QA", "referrerCallForAdbrix");
                    }
                    if (ReferralInfoDAO.getOnReceiveReferralFlag(context2)) {
                        if (!AppImpressionDAO.getSharedPreferencesForFirstStart(context2).contains("fts") || requestParameter2.getReferralKey() == -1) {
                            AppImpressionDAO.addFirstStartToSP(context2);
                        }
                        if (ReferralInfoDAO.isSentRefferrerSuccess2Adbrix(context2)) {
                            Log.i("IGAW_QA", "Can not send CPI referrerCallForAdbrix multiple times");
                            ReferralInfoDAO.clearOnReceiveReferralFlag(context2);
                        } else {
                            IgawLogger.Logging(context2, "IGAW_QA", "ReferralInfoDAO >> onReceiveReferral: true! SDK will call onReceiveReferral() api", 3, false);
                            IgawCommon.onReceiveReferral(context2, ReferralInfoDAO.getReferralInfo_referrer_params(context2));
                        }
                    } else if ((!AppImpressionDAO.getSharedPreferencesForFirstStart(context2).contains("fts") || requestParameter2.getReferralKey() == -1) && (requestParameter2.getReferralKey() == -1 || requestParameter2.getADBrixUserNo() < 1)) {
                        AppImpressionDAO.addFirstStartToSP(context2);
                        commonHttpManager2.normal_referrerCallForADBrix(requestParameter2, context2, ActivityInfoDAO.getActivityInfoForReferral(context2));
                    }
                }
                return null;
            }
        }, NETWORK_EXECUTOR);
    }

    public void trackingForAdbrixCall(Context context, boolean z, CommonHttpManager commonHttpManager, String str, String str2, long j) {
        final Context context2 = context;
        final boolean z2 = z;
        final String str3 = str2;
        final String str4 = str;
        final long j2 = j;
        final CommonHttpManager commonHttpManager2 = commonHttpManager;
        Task.forResult(null).continueWithTask(new Continuation<Void, Task<Void>>() {
            public Task<Void> then(Task<Void> task) {
                if (!CommonHelper.checkInternetConnection(context2) && !z2) {
                    return null;
                }
                if (z2) {
                    Log.i("IGAW_QA", "trackingForAdbrixCall");
                }
                String str = BuildConfig.FLAVOR;
                if (str3.equals(String.VIDEO_START)) {
                    Object obj = "OnStartSession";
                } else if (str3.equals("end")) {
                    r10 = "OnEndSession";
                } else {
                    r10 = "Flush tracking data";
                }
                try {
                    Task activityListParam = TrackingActivitySQLiteDB.getInstance(context2).getActivityListParam(false, context2, str4, str3, j2);
                    TaskUtils.wait(activityListParam);
                    Task impressionData = TrackingActivitySQLiteDB.getInstance(context2).getImpressionData(false, context2);
                    TaskUtils.wait(impressionData);
                    ArrayList arrayList = (ArrayList) activityListParam.getResult();
                    ArrayList arrayList2 = (ArrayList) impressionData.getResult();
                    if ((arrayList != null && arrayList.size() > 0) || (arrayList2 != null && arrayList2.size() > 0)) {
                        Context context = context2;
                        String str2 = "IGAW_QA";
                        String stringBuilder = new StringBuilder(String.valueOf(obj)).append(": trackingForAdbrix data - activity : %d, imp : %d").toString();
                        Object[] objArr = new Object[2];
                        objArr[0] = Integer.valueOf(arrayList == null ? 0 : arrayList.size());
                        objArr[1] = Integer.valueOf(arrayList2 == null ? 0 : arrayList2.size());
                        IgawLogger.Logging(context, str2, String.format(stringBuilder, objArr), 2, true);
                        commonHttpManager2.trackingForADBrix(RequestParameter.getATRequestParameter(context2), context2, arrayList, arrayList2);
                    }
                } catch (Exception e) {
                    Log.e("IGAW_QA", new StringBuilder(String.valueOf(obj)).append(": trackingForAdbrix Error >>").append(e.getMessage()).toString());
                }
                return Task.forResult(null);
            }
        }, NETWORK_EXECUTOR);
    }
}
