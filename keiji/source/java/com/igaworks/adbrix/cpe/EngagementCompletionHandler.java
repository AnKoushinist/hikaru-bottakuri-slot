package com.igaworks.adbrix.cpe;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.igaworks.adbrix.core.ADBrixHttpManager;
import com.igaworks.adbrix.model.Engagement;
import com.igaworks.adbrix.model.Segment;
import com.igaworks.adbrix.model.Trigger;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.dao.CounterDAOForAllActivity;
import com.igaworks.dao.CounterDAOForCPEActivity;
import com.igaworks.dao.tracking.TrackingActivitySQLiteDB;
import com.igaworks.model.ActivityCounter;
import com.igaworks.util.bolts_task.Task;
import com.igaworks.util.bolts_task.TaskUtils;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class EngagementCompletionHandler {

    class AnonymousClass1 implements Runnable {
        private final /* synthetic */ Context val$context;
        private final /* synthetic */ Engagement val$engagement;

        AnonymousClass1(Engagement engagement, Context context) {
            this.val$engagement = engagement;
            this.val$context = context;
        }

        public void run() {
            String replace = this.val$engagement.getDisplayData().getProgressMessage().replace("%r", new StringBuilder(String.valueOf(this.val$engagement.getTrigger().getCount() - 1)).toString()).replace("%n", "1");
            CPEProgressBarHandler.makeToastPopup(this.val$context, this.val$engagement.getDisplayData().getProgressTitle(), replace, this.val$engagement.getTrigger().getCount(), 1, 5000);
            CPEProgressBarHandler.setNotification(this.val$context, this.val$engagement.getDisplayData().getProgressTitle(), replace);
        }
    }

    class AnonymousClass2 implements Runnable {
        private final /* synthetic */ int val$cCnt;
        private final /* synthetic */ Context val$context;
        private final /* synthetic */ Engagement val$engagement;

        AnonymousClass2(Engagement engagement, int i, Context context) {
            this.val$engagement = engagement;
            this.val$cCnt = i;
            this.val$context = context;
        }

        public void run() {
            String replace = this.val$engagement.getDisplayData().getProgressMessage().replace("%r", new StringBuilder(String.valueOf(this.val$engagement.getTrigger().getCount() - this.val$cCnt)).toString()).replace("%n", new StringBuilder(String.valueOf(this.val$cCnt)).toString());
            CPEProgressBarHandler.makeToastPopup(this.val$context, this.val$engagement.getDisplayData().getProgressTitle(), replace, this.val$engagement.getTrigger().getCount(), this.val$cCnt, 5000);
            CPEProgressBarHandler.setNotification(this.val$context, this.val$engagement.getDisplayData().getProgressTitle(), replace);
        }
    }

    public static void checkAndCompleteEngagement(Context context, String str, String str2, RequestParameter requestParameter, ADBrixHttpManager aDBrixHttpManager, Calendar calendar) {
        ArrayList arrayList;
        Handler handler = new Handler(Looper.getMainLooper());
        CounterDAOForCPEActivity dao = CounterDAOForCPEActivity.getDAO(context);
        CounterDAOForAllActivity.getDAO(context).updateItemToAllActivity(str, str2);
        ArrayList conversionCache = requestParameter.getConversionCache();
        if (conversionCache == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = conversionCache;
        }
        if (ADBrixHttpManager.schedule == null || ADBrixHttpManager.schedule.getSchedule() == null || ADBrixHttpManager.schedule.getSchedule().getEngagements() == null) {
            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement schedule is null", 3);
            return;
        }
        IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement schedule size : " + ADBrixHttpManager.schedule.getSchedule().getEngagements().size(), 3);
        for (Engagement engagement : ADBrixHttpManager.schedule.getSchedule().getEngagements()) {
            String group = engagement.getTrigger().getGroup();
            String activity = engagement.getTrigger().getActivity();
            if (group.equals(str) && activity.equals(str2)) {
                IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement Item : group = " + group + ", activity = " + activity, 3, false);
                if (arrayList.contains(Integer.valueOf(engagement.getConversionKey()))) {
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Already Complete Engagement", 3, false);
                } else {
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement Check For parent Conversion Key : " + engagement.getParentConversionKey(), 3);
                    if (!(engagement.getParentConversionKey() == -1 || arrayList == null)) {
                        if (!arrayList.contains(Integer.valueOf(engagement.getParentConversionKey()))) {
                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement Check : Parent Conversion not completed", 3, false);
                        }
                    }
                    if (engagement.getSegments() != null) {
                        Object userValue;
                        IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement Segment Check Start", 3);
                        for (Segment segment : engagement.getSegments()) {
                            Object arrayList2;
                            userValue = ConditionChecker.getUserValue(context, requestParameter, 0, new StringBuilder(String.valueOf(engagement.getConversionKey())).toString(), segment.getScheme(), segment.getKey());
                            Object val = segment.getVal();
                            if (segment.getVal() instanceof Collection) {
                                if (!(userValue instanceof String)) {
                                    userValue = null;
                                }
                                arrayList2 = new ArrayList();
                                arrayList2.add((String) userValue);
                            } else {
                                arrayList2 = userValue;
                            }
                            if (!(val == null || arrayList2 == null)) {
                                String op = segment.getOp();
                                boolean z = segment.getScheme().equals("app") && segment.getKey().equals("package");
                                if (ConditionChecker.isMatch(context, op, val, arrayList2, z)) {
                                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement Segment check passed : " + segment.getScheme() + " / " + segment.getKey() + " / " + segment.getOp() + " / " + val.toString() + ", UserValue = " + arrayList2.toString(), 3);
                                }
                            }
                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement Segment check failed : ", 3, false);
                            IgawLogger.Logging(context, "IGAW_QA", segment.getScheme() + " / " + segment.getKey() + " / " + segment.getOp() + " / " + (val == null ? "null" : val.toString()) + ", UserValue = " + (arrayList2 == null ? "null" : arrayList2.toString()), 3);
                            userValue = null;
                            if (userValue == null) {
                                continue;
                            }
                        }
                        userValue = 1;
                        if (userValue == null) {
                            continue;
                        }
                    } else {
                        IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement Check : Segment not exist", 3, false);
                    }
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement Trigger Count : " + engagement.getTrigger().getCount(), 3);
                    if (engagement.getTrigger().getCount() > 1) {
                        int i;
                        int i2;
                        int i3;
                        int i4;
                        List<ActivityCounter> activityCounters = dao.getActivityCounters(str, str2);
                        Calendar calendar2 = null;
                        if (calendar == null) {
                            calendar2 = Calendar.getInstance();
                            i = calendar2.get(1);
                            i2 = calendar2.get(2);
                            i3 = calendar2.get(5);
                            i4 = calendar2.get(11);
                        } else {
                            i = calendar.get(1);
                            i2 = calendar.get(2);
                            i3 = calendar.get(5);
                            i4 = calendar.get(11);
                        }
                        IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement Current Date : " + i + "-" + i2 + "-" + i3 + " " + i4, 3);
                        if (activityCounters == null || activityCounters.size() < 1) {
                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Activity Counter not exist, starting insert row", 3);
                            dao.insertCounter(i, i2, i3, i4, str, str2, calendar);
                            if (engagement.getDisplayData().isProgressShow()) {
                                handler.post(new AnonymousClass1(engagement, context));
                            }
                        } else {
                            Calendar instance;
                            boolean isContinuous = engagement.getTrigger().isContinuous();
                            long intervalMSec = engagement.getTrigger().getIntervalMSec();
                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement Continuous : " + isContinuous + ", interval : " + intervalMSec, 3);
                            Calendar instance2 = Calendar.getInstance();
                            if (calendar != null) {
                                instance2.setTime(new Date());
                            } else {
                                instance2.set(1, i);
                                instance2.set(2, i2);
                                instance2.set(5, i3);
                                instance2.set(11, i4);
                            }
                            instance2.setTime(new Date(instance2.getTimeInMillis() - engagement.getTrigger().getIntervalMSec()));
                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement Date For Interval : " + instance2.toString(), 3);
                            ActivityCounter activityCounter = (ActivityCounter) activityCounters.get(0);
                            try {
                                Date parse = CounterDAOForCPEActivity.DB_DATE_FORMAT.parse(activityCounter.getUpdateDatetime());
                                instance = Calendar.getInstance();
                                instance.setTime(parse);
                            } catch (ParseException e) {
                                e.printStackTrace();
                                instance = Calendar.getInstance();
                                instance.set(1, activityCounter.getYear());
                                instance.set(2, activityCounter.getMonth());
                                instance.set(5, activityCounter.getDay());
                                instance.set(11, activityCounter.getHour());
                                instance.set(12, 0);
                                instance.set(13, 0);
                                instance.set(14, 0);
                            }
                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement Last Count Date : " + instance.toString(), 3);
                            if (intervalMSec > 0 && instance.after(instance2)) {
                                if (calendar != null) {
                                    calendar2 = calendar;
                                }
                                if (!checkResetData(context, calendar2, instance, engagement.getTrigger())) {
                                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement failed by interval", 3, false);
                                    dao.updateNoCountingDateUpdated(activityCounter, i, i2, i3, i4, calendar);
                                    return;
                                }
                            }
                            instance2 = Calendar.getInstance();
                            if (calendar == null) {
                                instance2.setTime(new Date());
                            } else {
                                instance2.set(1, i);
                                instance2.set(2, i2);
                                instance2.set(5, i3);
                                instance2.set(11, i4);
                            }
                            calculateIntervalDate(engagement.getTrigger(), instance2);
                            instance2.setTime(new Date(instance2.getTimeInMillis() - intervalMSec));
                            calendar2 = Calendar.getInstance();
                            int i5 = 0;
                            Calendar calendar3 = instance2;
                            instance2 = calendar2;
                            for (ActivityCounter activityCounter2 : activityCounters) {
                                if (intervalMSec <= 0 || !isContinuous) {
                                    i5 = activityCounter2.getCounter() + i5;
                                } else {
                                    try {
                                        Date parse2 = CounterDAOForCPEActivity.DB_DATE_FORMAT.parse(activityCounter2.getNoCountingUpdateDatetime());
                                        instance2 = Calendar.getInstance();
                                        instance2.setTime(parse2);
                                    } catch (ParseException e2) {
                                        instance2.set(1, activityCounter2.getYearUpdated());
                                        instance2.set(2, activityCounter2.getMonthUpdated());
                                        instance2.set(5, activityCounter2.getDayUpdated());
                                        instance2.set(11, activityCounter2.getHourUpdated());
                                    }
                                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement continuous check start : target date = " + calendar3.toString() + "\ncurrent date = " + instance2.toString(), 3);
                                    if (calendar3.after(instance2)) {
                                        IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement failed by continuous", 3, false);
                                        dao.removeCounterLessThanDate(activityCounter2.getYearUpdated(), activityCounter2.getMonthUpdated(), activityCounter2.getDayUpdated(), activityCounter2.getHourUpdated(), str, str2);
                                        break;
                                    }
                                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement current count : " + activityCounter2.getCounter(), 3, false);
                                    int counter = activityCounter2.getCounter() + i5;
                                    try {
                                        Date parse3 = CounterDAOForCPEActivity.DB_DATE_FORMAT.parse(activityCounter2.getNoCountingUpdateDatetime());
                                        calendar3 = Calendar.getInstance();
                                        calendar3.setTime(parse3);
                                        calendar2 = calendar3;
                                    } catch (ParseException e3) {
                                        calendar3.set(1, activityCounter2.getYear());
                                        calendar3.set(2, activityCounter2.getMonth());
                                        calendar3.set(5, activityCounter2.getDay());
                                        calendar3.set(11, activityCounter2.getHour());
                                        calendar2 = calendar3;
                                    }
                                    calendar2.setTime(new Date(calendar2.getTimeInMillis() - intervalMSec));
                                    calendar3 = calendar2;
                                    i5 = counter;
                                }
                            }
                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement count check : target Count = " + engagement.getTrigger().getCount() + ", current count = " + i5, 3);
                            if (i == instance.get(1) && i2 == instance.get(2) && i3 == instance.get(5) && i4 == instance.get(11)) {
                                dao.increment(activityCounter);
                                IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement count increased", 3);
                            } else {
                                dao.insertCounter(i, i2, i3, i4, str, str2);
                                IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement counter row inserted", 3);
                            }
                            int i6 = i5 + 1;
                            if (i6 >= engagement.getTrigger().getCount()) {
                                handleRewardSchedule(context, engagement, arrayList, requestParameter, aDBrixHttpManager);
                                dao.removeCounter(str, str2);
                                IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement completed", 3, false);
                            } else if (engagement.getDisplayData().isProgressShow()) {
                                handler.post(new AnonymousClass2(engagement, i6, context));
                            }
                        }
                    } else {
                        handleRewardSchedule(context, engagement, arrayList, requestParameter, aDBrixHttpManager);
                        IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement completed without count check", 3, false);
                    }
                }
            } else {
                IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement check : current engagement group/activity = " + group + " / " + activity + " is Not Matched", 3, true);
            }
        }
    }

    private static boolean checkResetData(Context context, Calendar calendar, Calendar calendar2, Trigger trigger) {
        if (trigger.getResetType() == null) {
            return true;
        }
        int i = calendar.get(2);
        int i2 = calendar.get(4);
        calendar.get(5);
        int i3 = calendar.get(11);
        int i4 = calendar.get(12);
        int i5 = calendar.get(13);
        int i6 = calendar2.get(2);
        int i7 = calendar2.get(4);
        calendar2.get(5);
        int i8 = calendar2.get(11);
        int i9 = calendar2.get(12);
        int i10 = calendar2.get(13);
        if (trigger.getResetType().equals("daily")) {
            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement check reset data RESET_DAILY", 3);
            i2 = Integer.parseInt(new StringBuilder(String.valueOf(String.format("%02d", new Object[]{Integer.valueOf(i3)}))).append(String.format("%02d", new Object[]{Integer.valueOf(i4)})).append(String.format("%02d", new Object[]{Integer.valueOf(i5)})).toString());
            i3 = Integer.parseInt(new StringBuilder(String.valueOf(String.format("%02d", new Object[]{Integer.valueOf(i8)}))).append(String.format("%02d", new Object[]{Integer.valueOf(i9)})).append(String.format("%02d", new Object[]{Integer.valueOf(i10)})).toString());
            i = Integer.parseInt(new StringBuilder(String.valueOf(String.format("%02d", new Object[]{Integer.valueOf(trigger.getResetData())}))).append("0000").toString());
            if (i2 < i3) {
                IgawLogger.Logging(context, "IGAW_QA", "Add Current/ResetTime +240000", 3);
                i2 += 240000;
                i += 240000;
            }
            IgawLogger.Logging(context, "IGAW_QA", String.format("cTime : %d, lTime : %d, rTime : %d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i)}), 3);
            if (i2 >= i && i3 <= i) {
                return true;
            }
        } else if (trigger.getResetType().equals("weekly")) {
            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement check reset data RESET_WEEKLY", 3);
            r2 = Long.parseLong(new StringBuilder(String.valueOf(i2)).append(String.format("%02d", new Object[]{Integer.valueOf(i3)})).append(String.format("%02d", new Object[]{Integer.valueOf(i4)})).append(String.format("%02d", new Object[]{Integer.valueOf(i5)})).toString());
            r4 = Long.parseLong(new StringBuilder(String.valueOf(i7)).append(String.format("%02d", new Object[]{Integer.valueOf(i8)})).append(String.format("%02d", new Object[]{Integer.valueOf(i9)})).append(String.format("%02d", new Object[]{Integer.valueOf(i10)})).toString());
            r0 = Long.parseLong(new StringBuilder(String.valueOf(i2)).append("000000").toString());
            if (r2 < r4) {
                IgawLogger.Logging(context, "IGAW_QA", "Add Current/ResetTime +6000000", 3);
                r2 += 6000000;
                r0 += 6000000;
            }
            IgawLogger.Logging(context, "IGAW_QA", String.format("cTime : %d, lTime : %d, rTime : %d", new Object[]{Long.valueOf(r2), Long.valueOf(r4), Long.valueOf(r0)}), 3);
            if (r2 >= r0 && r4 <= r0) {
                return true;
            }
        } else if (trigger.getResetType().equals("monthly")) {
            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Engagement check reset data RESET_MONTHLY", 3);
            r2 = Long.parseLong(new StringBuilder(String.valueOf(String.format("%02d", new Object[]{Integer.valueOf(i)}))).append(String.format("%02d", new Object[]{Integer.valueOf(calendar.get(5))})).append(String.format("%02d", new Object[]{Integer.valueOf(i3)})).append(String.format("%02d", new Object[]{Integer.valueOf(i4)})).append(String.format("%02d", new Object[]{Integer.valueOf(i5)})).toString());
            r4 = Long.parseLong(new StringBuilder(String.valueOf(String.format("%02d", new Object[]{Integer.valueOf(i6)}))).append(String.format("%02d", new Object[]{Integer.valueOf(calendar2.get(5))})).append(String.format("%02d", new Object[]{Integer.valueOf(i8)})).append(String.format("%02d", new Object[]{Integer.valueOf(i9)})).append(String.format("%02d", new Object[]{Integer.valueOf(i10)})).toString());
            r0 = Long.parseLong(new StringBuilder(String.valueOf(i)).append("00000000").toString());
            if (r2 < r4) {
                IgawLogger.Logging(context, "IGAW_QA", "Add Current/ResetTime +1200000000", 3);
                r2 += 1200000000;
                r0 += 1200000000;
            }
            IgawLogger.Logging(context, "IGAW_QA", String.format("cTime : %d, lTime : %d, rTime : %d", new Object[]{Long.valueOf(r2), Long.valueOf(r4), Long.valueOf(r0)}), 3);
            if (r2 >= r0 && r4 <= r0) {
                return true;
            }
        }
        return false;
    }

    private static void calculateIntervalDate(Trigger trigger, Calendar calendar) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(calendar.getTime());
        if (trigger.getResetType() == null) {
            calendar.setTime(new Date(calendar.getTimeInMillis() - trigger.getIntervalMSec()));
        } else if (trigger.getResetType().equals("daily")) {
            instance.set(11, trigger.getResetData());
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            if (calendar.after(instance)) {
                calendar.setTime(instance.getTime());
                return;
            }
            instance.add(5, -1);
            calendar.setTime(instance.getTime());
        } else if (trigger.getResetType().equals("weekly")) {
            instance.set(7, trigger.getResetData());
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            if (calendar.after(instance)) {
                calendar.setTime(instance.getTime());
                return;
            }
            instance.add(4, -1);
            calendar.setTime(instance.getTime());
        } else if (trigger.getResetType().equals("monthly")) {
            instance.set(5, trigger.getResetData());
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            if (calendar.after(instance)) {
                calendar.setTime(instance.getTime());
                return;
            }
            instance.add(2, -1);
            calendar.setTime(instance.getTime());
        } else {
            calendar.setTime(new Date(calendar.getTimeInMillis() - trigger.getIntervalMSec()));
        }
    }

    private static void handleRewardSchedule(Context context, Engagement engagement, ArrayList<Integer> arrayList, RequestParameter requestParameter, ADBrixHttpManager aDBrixHttpManager) {
        IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > handleRewardSchedule start", 3);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Integer.valueOf(engagement.getConversionKey()));
        try {
            Task activityListParam = TrackingActivitySQLiteDB.getInstance(context).getActivityListParam(false, context, "n/a", "n/a", 0);
            TaskUtils.wait(activityListParam);
            Task impressionData = TrackingActivitySQLiteDB.getInstance(context).getImpressionData(false, context);
            TaskUtils.wait(impressionData);
            aDBrixHttpManager.completeCPECallForADBrix(requestParameter, context, (ArrayList) activityListParam.getResult(), (ArrayList) impressionData.getResult(), arrayList2);
        } catch (Exception e) {
            Log.e("IGAW_QA", "OnStartSession: trackingForAdbrix Error >>" + e.getMessage());
        }
    }
}
