package com.igaworks.adbrix.cpe;

import android.content.Context;
import com.igaworks.adbrix.core.ADBrixHttpManager;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.dao.ActivityDAOForRestore;
import com.igaworks.util.bolts_task.Continuation;
import com.igaworks.util.bolts_task.Task;
import com.igaworks.util.image.ImageDownloader;
import java.util.Calendar;

public class CPECompletionHandler {
    private static ImageDownloader imageDownloader;
    private static boolean onRestore = false;

    class AnonymousClass1 implements Continuation<Void, Void> {
        private final /* synthetic */ String val$activityName;
        private final /* synthetic */ Context val$context;
        private final /* synthetic */ String val$group;
        private final /* synthetic */ ADBrixHttpManager val$httpManager;
        private final /* synthetic */ RequestParameter val$parameter;
        private final /* synthetic */ Calendar val$restoreTime;

        AnonymousClass1(Context context, RequestParameter requestParameter, String str, String str2, ADBrixHttpManager aDBrixHttpManager, Calendar calendar) {
            this.val$context = context;
            this.val$parameter = requestParameter;
            this.val$group = str;
            this.val$activityName = str2;
            this.val$httpManager = aDBrixHttpManager;
            this.val$restoreTime = calendar;
        }

        public Void then(Task<Void> task) {
            boolean z = true;
            try {
                IgawLogger.Logging(this.val$context, "IGAW_QA", "ADBrixManager > Schedule check start : is schedule exist = " + (ADBrixHttpManager.schedule != null), 3, false);
                if (ADBrixHttpManager.schedule == null || this.val$parameter.getReferralKey() < 0) {
                    Context context = this.val$context;
                    String str = "IGAW_QA";
                    StringBuilder stringBuilder = new StringBuilder("ADBrixManager > add restore activity >> schedule == null : ");
                    if (ADBrixHttpManager.schedule != null) {
                        z = false;
                    }
                    IgawLogger.Logging(context, str, stringBuilder.append(z).append(", parameter.getReferralKey : ").append(this.val$parameter.getReferralKey()).toString(), 3, true);
                    ActivityDAOForRestore.getDAO(this.val$context).addItem(this.val$group, this.val$activityName);
                    return null;
                }
                EngagementCompletionHandler.checkAndCompleteEngagement(this.val$context, this.val$group, this.val$activityName, this.val$parameter, this.val$httpManager, this.val$restoreTime);
                if (this.val$group.equals("adspace")) {
                    PromotionHandler.checkAvailablePromotion(this.val$context, this.val$activityName, this.val$parameter);
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class AnonymousClass2 implements Continuation<Void, Void> {
        private final /* synthetic */ Context val$context;
        private final /* synthetic */ RequestParameter val$parameter;
        private final /* synthetic */ ADBrixHttpManager val$tracer;

        AnonymousClass2(Context context, RequestParameter requestParameter, ADBrixHttpManager aDBrixHttpManager) {
            this.val$context = context;
            this.val$parameter = requestParameter;
            this.val$tracer = aDBrixHttpManager;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void then(com.igaworks.util.bolts_task.Task<java.lang.Void> r13) {
            /*
            r12 = this;
            r9 = 0;
            r7 = 30;
            r11 = 0;
            r1 = r12.val$context;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r2 = com.igaworks.dao.ActivityDAOForRestore.getDAO(r1);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r1 = r2.getRestoreActivities();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r2 = r2.clearRestoreActivity();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r3 = r12.val$context;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r4 = "IGAW_QA";
            r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r6 = "clearRestoreActivity result : ";
            r5.<init>(r6);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r2 = r5.append(r2);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r2 = r2.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r5 = 3;
            com.igaworks.core.IgawLogger.Logging(r3, r4, r2, r5);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r2 = r12.val$context;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r3 = "IGAW_QA";
            r4 = "restoreCPEAction called";
            r5 = 3;
            com.igaworks.core.IgawLogger.Logging(r2, r3, r4, r5);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            if (r1 == 0) goto L_0x0107;
        L_0x0035:
            r2 = r1.size();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            if (r2 <= 0) goto L_0x0107;
        L_0x003b:
            r2 = r1.size();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            if (r2 <= r7) goto L_0x0063;
        L_0x0041:
            r2 = r12.val$context;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r3 = "IGAW_QA";
            r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r5 = "the number of restore activity over 30 : ";
            r4.<init>(r5);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r5 = r1.size();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r4 = r4.append(r5);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r4 = r4.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r5 = 3;
            com.igaworks.core.IgawLogger.Logging(r2, r3, r4, r5);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r2 = 0;
            r3 = 30;
            r1 = r1.subList(r2, r3);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
        L_0x0063:
            r2 = r12.val$context;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r3 = "IGAW_QA";
            r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r5 = "the number of restore activity : ";
            r4.<init>(r5);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r5 = r1.size();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r4 = r4.append(r5);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r4 = r4.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r5 = 3;
            com.igaworks.core.IgawLogger.Logging(r2, r3, r4, r5);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r10 = r1.iterator();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r8 = r9;
        L_0x0083:
            r1 = r10.hasNext();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            if (r1 != 0) goto L_0x008d;
        L_0x0089:
            com.igaworks.adbrix.cpe.CPECompletionHandler.onRestore = r11;
        L_0x008c:
            return r9;
        L_0x008d:
            r1 = r10.next();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r0 = r1;
            r0 = (com.igaworks.model.RestoreActivity) r0;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r7 = r0;
            r1 = r12.val$context;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r2 = "IGAW_QA";
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r4 = "restore item(group/activity) : ";
            r3.<init>(r4);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r4 = r7.getGroup();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r4 = "/";
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r4 = r7.getActivity();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r3 = r3.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r4 = 3;
            com.igaworks.core.IgawLogger.Logging(r1, r2, r3, r4);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r1 = r12.val$context;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r2 = r7.getGroup();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r3 = r7.getActivity();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r4 = r12.val$parameter;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r5 = r12.val$tracer;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r6 = r7.getRegistDatetime();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            com.igaworks.adbrix.cpe.EngagementCompletionHandler.checkAndCompleteEngagement(r1, r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r1 = r7.getGroup();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r2 = "adspace";
            r1 = r1.equals(r2);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            if (r1 == 0) goto L_0x0083;
        L_0x00df:
            if (r8 != 0) goto L_0x011e;
        L_0x00e1:
            r1 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r1.<init>();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
        L_0x00e6:
            r2 = r7.getActivity();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r2 = r1.contains(r2);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            if (r2 == 0) goto L_0x00f2;
        L_0x00f0:
            r8 = r1;
            goto L_0x0083;
        L_0x00f2:
            r2 = r7.getActivity();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r1.add(r2);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r2 = r12.val$context;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r3 = r7.getActivity();	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r4 = r12.val$parameter;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            com.igaworks.adbrix.cpe.PromotionHandler.checkAvailablePromotion(r2, r3, r4);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r8 = r1;
            goto L_0x0083;
        L_0x0107:
            r1 = r12.val$context;	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            r2 = "IGAW_QA";
            r3 = "there are no restore activity";
            r4 = 3;
            com.igaworks.core.IgawLogger.Logging(r1, r2, r3, r4);	 Catch:{ Exception -> 0x0113, all -> 0x0119 }
            goto L_0x0089;
        L_0x0113:
            r1 = move-exception;
            com.igaworks.adbrix.cpe.CPECompletionHandler.onRestore = r11;
            goto L_0x008c;
        L_0x0119:
            r1 = move-exception;
            com.igaworks.adbrix.cpe.CPECompletionHandler.onRestore = r11;
            throw r1;
        L_0x011e:
            r1 = r8;
            goto L_0x00e6;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.igaworks.adbrix.cpe.CPECompletionHandler.2.then(com.igaworks.util.bolts_task.Task):java.lang.Void");
        }
    }

    public static ImageDownloader getImageDownloader(Context context) {
        if (imageDownloader == null) {
            imageDownloader = new ImageDownloader(context, "imagecache");
        }
        return imageDownloader;
    }

    public static void checkAndComplete(Context context, String str, String str2, RequestParameter requestParameter, ADBrixHttpManager aDBrixHttpManager, Calendar calendar) {
        Task.forResult(null).continueWith(new AnonymousClass1(context, requestParameter, str, str2, aDBrixHttpManager, calendar), Task.BACKGROUND_EXECUTOR);
    }

    public static void restoreCPEAction(Context context, RequestParameter requestParameter, ADBrixHttpManager aDBrixHttpManager) {
        if (!onRestore) {
            onRestore = true;
            Task.forResult(null).continueWith(new AnonymousClass2(context, requestParameter, aDBrixHttpManager), Task.BACKGROUND_EXECUTOR);
        }
    }
}
