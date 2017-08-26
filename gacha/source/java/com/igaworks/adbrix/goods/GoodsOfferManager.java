package com.igaworks.adbrix.goods;

import android.app.Activity;

public class GoodsOfferManager {
    private static GoodsOfferManager manager;
    private Activity activity;
    private boolean runRealEngagement = false;

    private GoodsOfferManager(Activity activity) {
        if (activity != null) {
            this.activity = activity;
        }
    }

    public void setRunRealEngagement(boolean z) {
        this.runRealEngagement = z;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public static GoodsOfferManager getManager(Activity activity) {
        if (manager == null) {
            manager = new GoodsOfferManager(activity);
        }
        manager.setActivity(activity);
        return manager;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkRestoreRealReward(android.content.Context r12) {
        /*
        r11 = this;
        r8 = 1;
        r7 = -1;
        r0 = 0;
        r1 = "IGAW_QA";
        r2 = "checkRestoreRealReward : runRealEngagement = %s, activity is null = %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x00b6 }
        r4 = 0;
        r5 = r11.runRealEngagement;	 Catch:{ Exception -> 0x00b6 }
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ Exception -> 0x00b6 }
        r3[r4] = r5;	 Catch:{ Exception -> 0x00b6 }
        r4 = 1;
        r5 = r11.activity;	 Catch:{ Exception -> 0x00b6 }
        if (r5 != 0) goto L_0x0019;
    L_0x0018:
        r0 = r8;
    L_0x0019:
        r0 = java.lang.Boolean.valueOf(r0);	 Catch:{ Exception -> 0x00b6 }
        r3[r4] = r0;	 Catch:{ Exception -> 0x00b6 }
        r0 = java.lang.String.format(r2, r3);	 Catch:{ Exception -> 0x00b6 }
        r2 = 3;
        com.igaworks.core.IgawLogger.Logging(r12, r1, r0, r2);	 Catch:{ Exception -> 0x00b6 }
        r0 = r11.runRealEngagement;	 Catch:{ Exception -> 0x00b6 }
        if (r0 == 0) goto L_0x003b;
    L_0x002b:
        r0 = r11.activity;	 Catch:{ Exception -> 0x00b6 }
        if (r0 == 0) goto L_0x003b;
    L_0x002f:
        r0 = com.igaworks.adbrix.impl.ADBrixFrameworkFactory.getFramework();	 Catch:{ Exception -> 0x00b6 }
        r1 = r11.activity;	 Catch:{ Exception -> 0x00b6 }
        r0.showRealRewardNotice(r1);	 Catch:{ Exception -> 0x00b6 }
        r0 = 0;
        r11.runRealEngagement = r0;	 Catch:{ Exception -> 0x00b6 }
    L_0x003b:
        r0 = r11.activity;	 Catch:{ Exception -> 0x00b6 }
        if (r0 != 0) goto L_0x0040;
    L_0x003f:
        return;
    L_0x0040:
        r0 = com.igaworks.adbrix.db.RealRewardDAO.getInstance();	 Catch:{ Exception -> 0x00b6 }
        r6 = r0.getRetryCompleteCache(r12);	 Catch:{ Exception -> 0x00b6 }
        if (r6 == 0) goto L_0x005e;
    L_0x004a:
        r0 = r6.size();	 Catch:{ Exception -> 0x00b6 }
        if (r0 <= 0) goto L_0x005e;
    L_0x0050:
        r0 = r6.keySet();	 Catch:{ Exception -> 0x00b6 }
        r9 = r0.iterator();	 Catch:{ Exception -> 0x00b6 }
    L_0x0058:
        r0 = r9.hasNext();	 Catch:{ Exception -> 0x00b6 }
        if (r0 != 0) goto L_0x00d1;
    L_0x005e:
        r0 = com.igaworks.adbrix.db.RealRewardDAO.getInstance();	 Catch:{ Exception -> 0x00b6 }
        r0 = r0.getRetryRedeemCache(r12);	 Catch:{ Exception -> 0x00b6 }
        if (r0 == 0) goto L_0x003f;
    L_0x0068:
        r1 = r0.size();	 Catch:{ Exception -> 0x00b6 }
        if (r1 <= 0) goto L_0x003f;
    L_0x006e:
        r0 = r0.keySet();	 Catch:{ Exception -> 0x00b6 }
        r2 = r0.iterator();	 Catch:{ Exception -> 0x00b6 }
    L_0x0076:
        r0 = r2.hasNext();	 Catch:{ Exception -> 0x00b6 }
        if (r0 == 0) goto L_0x003f;
    L_0x007c:
        r0 = r2.next();	 Catch:{ Exception -> 0x00b6 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x00b6 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0136 }
        r1 = r0;
    L_0x0087:
        r0 = com.igaworks.adbrix.core.ADBrixHttpManager.schedule;	 Catch:{ Exception -> 0x00b6 }
        r0 = r0.getSchedule();	 Catch:{ Exception -> 0x00b6 }
        r0 = r0.getRealRewards();	 Catch:{ Exception -> 0x00b6 }
        r3 = 0;
        r0 = r0.get(r3);	 Catch:{ Exception -> 0x00b6 }
        r0 = (com.igaworks.adbrix.model.RealReward) r0;	 Catch:{ Exception -> 0x00b6 }
        r3 = r0.getRealRewardKey();	 Catch:{ Exception -> 0x00b6 }
        if (r3 != r1) goto L_0x0076;
    L_0x009e:
        r3 = r0.isIsDailyEvent();	 Catch:{ Exception -> 0x00b6 }
        if (r3 == 0) goto L_0x014f;
    L_0x00a4:
        r3 = com.igaworks.adbrix.db.RealRewardDAO.getInstance();	 Catch:{ Exception -> 0x00b6 }
        r3 = r3.hasCompleteToday(r12, r1);	 Catch:{ Exception -> 0x00b6 }
        if (r3 == 0) goto L_0x014f;
    L_0x00ae:
        r0 = com.igaworks.adbrix.db.RealRewardDAO.getInstance();	 Catch:{ Exception -> 0x00b6 }
        r0.clearRetryRedeemCache(r12, r1);	 Catch:{ Exception -> 0x00b6 }
        goto L_0x0076;
    L_0x00b6:
        r0 = move-exception;
        r1 = "IGAW_QA";
        r2 = new java.lang.StringBuilder;
        r3 = "checkRestoreRealReward - error : ";
        r2.<init>(r3);
        r0 = r0.getMessage();
        r0 = r2.append(r0);
        r0 = r0.toString();
        com.igaworks.core.IgawLogger.Logging(r12, r1, r0, r8);
        goto L_0x003f;
    L_0x00d1:
        r4 = -1;
        r0 = r9.next();	 Catch:{ Exception -> 0x00b6 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x00b6 }
        r2 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x00fe }
        r1 = r6.get(r0);	 Catch:{ Exception -> 0x01be }
        r1 = (java.lang.Long) r1;	 Catch:{ Exception -> 0x01be }
        r4 = r1.longValue();	 Catch:{ Exception -> 0x01be }
        r3 = r2;
    L_0x00e8:
        r0 = com.igaworks.adbrix.db.RealRewardDAO.getInstance();	 Catch:{ Exception -> 0x00b6 }
        r1 = r11.activity;	 Catch:{ Exception -> 0x00b6 }
        r0 = r0.isCompetedRealReward(r1, r3);	 Catch:{ Exception -> 0x00b6 }
        if (r0 == 0) goto L_0x0117;
    L_0x00f4:
        r0 = "IGAW_QA";
        r1 = "checkRestoreRealReward - completeRealReward > already completed event";
        r2 = 3;
        com.igaworks.core.IgawLogger.Logging(r12, r0, r1, r2);	 Catch:{ Exception -> 0x00b6 }
        goto L_0x0058;
    L_0x00fe:
        r1 = move-exception;
        r1 = r7;
    L_0x0100:
        r2 = "IGAW_QA";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b6 }
        r10 = "checkRestoreRealReward - completeRealReward > rrk converting err : rrk = ";
        r3.<init>(r10);	 Catch:{ Exception -> 0x00b6 }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x00b6 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00b6 }
        r3 = 3;
        com.igaworks.core.IgawLogger.Logging(r12, r2, r0, r3);	 Catch:{ Exception -> 0x00b6 }
        r3 = r1;
        goto L_0x00e8;
    L_0x0117:
        r0 = r11.activity;	 Catch:{ Exception -> 0x00b6 }
        r1 = com.igaworks.core.RequestParameter.getATRequestParameter(r0);	 Catch:{ Exception -> 0x00b6 }
        r0 = r11.activity;	 Catch:{ Exception -> 0x00b6 }
        r0 = com.igaworks.adbrix.core.ADBrixHttpManager.getManager(r0);	 Catch:{ Exception -> 0x00b6 }
        r2 = r1.getAppkey();	 Catch:{ Exception -> 0x00b6 }
        r6 = r11.activity;	 Catch:{ Exception -> 0x00b6 }
        r1 = r12;
        r0.completeRealReward(r1, r2, r3, r4, r6);	 Catch:{ Exception -> 0x00b6 }
        r0 = com.igaworks.adbrix.db.RealRewardDAO.getInstance();	 Catch:{ Exception -> 0x00b6 }
        r0.clearRetryCompleteCache(r12, r3);	 Catch:{ Exception -> 0x00b6 }
        goto L_0x005e;
    L_0x0136:
        r1 = move-exception;
        r1 = "IGAW_QA";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b6 }
        r4 = "checkRestoreRealReward - redeemRealReward > rrk converting err : rrk = ";
        r3.<init>(r4);	 Catch:{ Exception -> 0x00b6 }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x00b6 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00b6 }
        r3 = 3;
        com.igaworks.core.IgawLogger.Logging(r12, r1, r0, r3);	 Catch:{ Exception -> 0x00b6 }
        r1 = r7;
        goto L_0x0087;
    L_0x014f:
        r3 = new com.igaworks.adbrix.goods.GoodsOfferModel;	 Catch:{ Exception -> 0x00b6 }
        r3.<init>();	 Catch:{ Exception -> 0x00b6 }
        r4 = r0.getRealRewardImageUrl();	 Catch:{ Exception -> 0x00b6 }
        r3.setMainImg(r4);	 Catch:{ Exception -> 0x00b6 }
        r4 = r0.getMissionText();	 Catch:{ Exception -> 0x00b6 }
        r3.setMidText(r4);	 Catch:{ Exception -> 0x00b6 }
        r4 = "http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/title_02_large.png";
        r3.setTitleImg(r4);	 Catch:{ Exception -> 0x00b6 }
        r4 = 2;
        r3.setType(r4);	 Catch:{ Exception -> 0x00b6 }
        r4 = r0.getRealRewardKey();	 Catch:{ Exception -> 0x00b6 }
        r3.setRealRewardKey(r4);	 Catch:{ Exception -> 0x00b6 }
        r4 = r0.getConversionKey();	 Catch:{ Exception -> 0x00b6 }
        r3.setConversionKey(r4);	 Catch:{ Exception -> 0x00b6 }
        r4 = r0.isIsDailyEvent();	 Catch:{ Exception -> 0x00b6 }
        r3.setDailyEvent(r4);	 Catch:{ Exception -> 0x00b6 }
        r4 = r0.isNoCondition();	 Catch:{ Exception -> 0x00b6 }
        r3.setNoCondition(r4);	 Catch:{ Exception -> 0x00b6 }
        r0 = r0.isIsTest();	 Catch:{ Exception -> 0x00b6 }
        r3.setTest(r0);	 Catch:{ Exception -> 0x00b6 }
        r0 = new com.igaworks.adbrix.goods.GoodsOfferDialog;	 Catch:{ Exception -> 0x00b6 }
        r4 = r11.activity;	 Catch:{ Exception -> 0x00b6 }
        r0.<init>(r4, r3);	 Catch:{ Exception -> 0x00b6 }
        r3 = new android.view.WindowManager$LayoutParams;	 Catch:{ Exception -> 0x00b6 }
        r3.<init>();	 Catch:{ Exception -> 0x00b6 }
        r4 = r0.getWindow();	 Catch:{ Exception -> 0x00b6 }
        r4 = r4.getAttributes();	 Catch:{ Exception -> 0x00b6 }
        r3.copyFrom(r4);	 Catch:{ Exception -> 0x00b6 }
        r4 = -1;
        r3.width = r4;	 Catch:{ Exception -> 0x00b6 }
        r4 = -2;
        r3.height = r4;	 Catch:{ Exception -> 0x00b6 }
        r4 = r0.getWindow();	 Catch:{ Exception -> 0x00b6 }
        r4.setAttributes(r3);	 Catch:{ Exception -> 0x00b6 }
        r0.show();	 Catch:{ Exception -> 0x00b6 }
        r0 = com.igaworks.adbrix.db.RealRewardDAO.getInstance();	 Catch:{ Exception -> 0x00b6 }
        r0.clearRetryRedeemCache(r12, r1);	 Catch:{ Exception -> 0x00b6 }
        goto L_0x0076;
    L_0x01be:
        r1 = move-exception;
        r1 = r2;
        goto L_0x0100;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igaworks.adbrix.goods.GoodsOfferManager.checkRestoreRealReward(android.content.Context):void");
    }
}
