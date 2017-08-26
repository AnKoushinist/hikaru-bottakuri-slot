package com.igaworks.adbrix.cpe;

import android.content.Context;
import android.util.Log;
import com.igaworks.adbrix.core.ADBrixHttpManager;
import com.igaworks.adbrix.db.DailyPlayDAO;
import com.igaworks.adbrix.model.DailyPlay;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.dao.tracking.TrackingActivitySQLiteDB;
import com.igaworks.impl.InternalAction;
import com.igaworks.util.bolts_task.Task;
import com.igaworks.util.bolts_task.TaskUtils;
import java.util.ArrayList;
import java.util.List;

public class DailyPlayHandler {

    class AnonymousClass1 implements Runnable {
        private final /* synthetic */ Context val$context;
        private final /* synthetic */ int val$conversionkey;

        AnonymousClass1(Context context, int i) {
            this.val$context = context;
            this.val$conversionkey = i;
        }

        public void run() {
            DailyPlayDAO.getInstance().setPendingConversionKey(this.val$context, this.val$conversionkey);
            IgawLogger.Logging(this.val$context, "IGAW_QA", "ADBrixManager > DailyPlayHandler sendCompleRequest", 3);
            ArrayList arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(this.val$conversionkey));
            try {
                Task activityListParam = TrackingActivitySQLiteDB.getInstance(this.val$context).getActivityListParam(false, this.val$context, "n/a", "n/a", 0);
                TaskUtils.wait(activityListParam);
                Task impressionData = TrackingActivitySQLiteDB.getInstance(this.val$context).getImpressionData(false, this.val$context);
                TaskUtils.wait(impressionData);
                ADBrixHttpManager.getManager(this.val$context).completeCPECallForADBrix(RequestParameter.getATRequestParameter(this.val$context), this.val$context, (ArrayList) activityListParam.getResult(), (ArrayList) impressionData.getResult(), arrayList);
            } catch (Exception e) {
                Log.e("IGAW_QA", "OnStartSession: DailyPlay sendCompleRequest Error >>" + e.getMessage());
            }
        }
    }

    public static void checkandComplete(Context context, List<DailyPlay> list, int i) {
        int latestConversionKey = DailyPlayDAO.getInstance().getLatestConversionKey(context);
        int i2;
        DailyPlay dailyPlay;
        if (latestConversionKey == -1) {
            for (i2 = 0; i2 < list.size(); i2++) {
                dailyPlay = (DailyPlay) list.get(i2);
                if (dailyPlay.getParentConversionKey() == i) {
                    latestConversionKey = dailyPlay.getConversionKey();
                    DailyPlayDAO.getInstance().setPlayTime(context, dailyPlay.getPlayTime());
                    sendCompletRequest(context, latestConversionKey);
                }
            }
            return;
        }
        for (i2 = 0; i2 < list.size(); i2++) {
            dailyPlay = (DailyPlay) list.get(i2);
            if (dailyPlay.getParentConversionKey() == latestConversionKey) {
                int conversionKey = dailyPlay.getConversionKey();
                DailyPlayDAO.getInstance().setPlayTime(context, dailyPlay.getPlayTime());
                ArrayList conversionCache = RequestParameter.getATRequestParameter(context).getConversionCache();
                if (conversionCache == null || !conversionCache.contains(Integer.valueOf(conversionKey))) {
                    sendCompletRequest(context, conversionKey);
                }
            }
        }
    }

    private static void sendCompletRequest(Context context, int i) {
        InternalAction.NETWORK_EXECUTOR.execute(new AnonymousClass1(context, i));
    }
}
