package com.igaworks.adbrix.cpe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager.LayoutParams;
import com.igaworks.adbrix.core.ADBrixHttpManager;
import com.igaworks.adbrix.cpe.activitydialog.PromotionActivityDialog;
import com.igaworks.adbrix.cpe.dialog.PromotionDialog;
import com.igaworks.adbrix.interfaces.ADBrixCallbackListener;
import com.igaworks.adbrix.interfaces.PromotionActionListener;
import com.igaworks.adbrix.model.Promotion;
import com.igaworks.adbrix.model.Segment;
import com.igaworks.adbrix.model.Space;
import com.igaworks.adbrix.model.SpaceSegment;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.dao.NotAvailableCampaignDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionHandler {
    public static Activity dialogOpenner;
    public static Map<String, Integer> nextCampaigns = new HashMap();
    public static ADBrixCallbackListener onPlayBtnClickListener;
    public static PromotionActionListener promotionActionListener;
    private static Dialog promotionDialog;

    class AnonymousClass5 implements Runnable {
        private final /* synthetic */ Context val$context;
        private final /* synthetic */ int val$primaryCampaignKey;
        private final /* synthetic */ Space val$space;
        private final /* synthetic */ ArrayList val$visibleCampaigns;

        AnonymousClass5(Context context, int i, ArrayList arrayList, Space space) {
            this.val$context = context;
            this.val$primaryCampaignKey = i;
            this.val$visibleCampaigns = arrayList;
            this.val$space = space;
        }

        public void run() {
            try {
                if (this.val$context instanceof Activity) {
                    Context context;
                    Context context2 = (Activity) this.val$context;
                    if (PromotionHandler.dialogOpenner != null) {
                        context = PromotionHandler.dialogOpenner;
                    } else {
                        context = context2;
                    }
                    IgawLogger.Logging(this.val$context, "IGAW_QA", "ADBrixManager > show promotion dialog", 3);
                    LayoutParams layoutParams;
                    try {
                        if (!(PromotionHandler.promotionDialog == null || PromotionHandler.promotionDialog.isShowing())) {
                            PromotionHandler.promotionDialog.dismiss();
                        }
                        PromotionHandler.promotionDialog = new PromotionDialog(context, this.val$primaryCampaignKey, this.val$visibleCampaigns, this.val$space.getSpaceKey(), PromotionHandler.onPlayBtnClickListener, PromotionHandler.promotionActionListener);
                        layoutParams = new LayoutParams();
                        layoutParams.copyFrom(PromotionHandler.promotionDialog.getWindow().getAttributes());
                        layoutParams.width = -1;
                        layoutParams.height = -1;
                        PromotionHandler.promotionDialog.getWindow().setAttributes(layoutParams);
                        PromotionHandler.promotionDialog.show();
                        return;
                    } catch (Exception e) {
                        PromotionHandler.promotionDialog.dismiss();
                        PromotionHandler.promotionDialog = new PromotionDialog(context, this.val$primaryCampaignKey, this.val$visibleCampaigns, this.val$space.getSpaceKey(), PromotionHandler.onPlayBtnClickListener, PromotionHandler.promotionActionListener);
                        layoutParams = new LayoutParams();
                        layoutParams.copyFrom(PromotionHandler.promotionDialog.getWindow().getAttributes());
                        layoutParams.width = -1;
                        layoutParams.height = -1;
                        PromotionHandler.promotionDialog.getWindow().setAttributes(layoutParams);
                        PromotionHandler.promotionDialog.show();
                        return;
                    }
                }
                IgawLogger.Logging(this.val$context, "IGAW_QA", "ADBrixManager > show promotion activity dialog", 3);
                if (PromotionActivityDialog.isActive) {
                    IgawLogger.Logging(this.val$context, "IGAW_QA", "ADBrixManager > promotion dialog is already opened", 3);
                    return;
                }
                PromotionActivityDialog.onPlayBtnClickListener = PromotionHandler.onPlayBtnClickListener;
                PromotionActivityDialog.promotionActionListener = PromotionHandler.promotionActionListener;
                Intent intent = new Intent(this.val$context, PromotionActivityDialog.class);
                intent.putIntegerArrayListExtra("campaignKeys", this.val$visibleCampaigns);
                intent.putExtra("primaryCampaignKey", this.val$primaryCampaignKey);
                intent.putExtra("spaceKey", this.val$space.getSpaceKey());
                intent.setFlags(268435456);
                this.val$context.startActivity(intent);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void checkAvailablePromotion(Context context, String str, RequestParameter requestParameter) {
        Handler handler = new Handler(Looper.getMainLooper());
        try {
            if (ADBrixHttpManager.schedule != null && ADBrixHttpManager.schedule.getSchedule() != null) {
                Object obj = null;
                for (Space space : ADBrixHttpManager.schedule.getSchedule().getSpaces()) {
                    if (space.getSpaceKey().equals(str)) {
                        List<Integer> campaignList = space.getCampaignList();
                        List<Promotion> promotions = ADBrixHttpManager.schedule.getSchedule().getPromotions();
                        int i = 0;
                        int i2 = 0;
                        Collection notAvailableCampaign = NotAvailableCampaignDAO.getInstance().getNotAvailableCampaign(context);
                        ArrayList arrayList = new ArrayList();
                        Object obj2 = null;
                        Object obj3 = null;
                        for (Integer intValue : campaignList) {
                            int intValue2 = intValue.intValue();
                            for (Promotion promotion : promotions) {
                                if (promotion.getCampaignKey() == intValue2) {
                                    Object obj4;
                                    Object val;
                                    String op;
                                    boolean z;
                                    String str2 = "IGAW_QA";
                                    StringBuilder stringBuilder = new StringBuilder("ADBrixManager > All Space Segment size =  ");
                                    if (space.getSpaceSegments() == null) {
                                        obj4 = "null";
                                    } else {
                                        obj4 = Integer.valueOf(space.getSpaceSegments().size());
                                    }
                                    IgawLogger.Logging(context, str2, stringBuilder.append(obj4).toString(), 3);
                                    if (space.getSpaceSegments() != null) {
                                        List list = null;
                                        for (SpaceSegment spaceSegment : space.getSpaceSegments()) {
                                            if (spaceSegment.getCampaignType() == promotion.getCampaignType()) {
                                                list = spaceSegment.getSegments();
                                                break;
                                            }
                                        }
                                        IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Space Segment size =  " + (r3 == null ? "null" : Integer.valueOf(r3.size())), 3);
                                        if (r3 == null || r3.size() <= 0) {
                                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Space Check : Segment not exist", 3, false);
                                        } else {
                                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Space Segment Check Start.", 3);
                                            for (Segment segment : r3) {
                                                obj4 = ConditionChecker.getUserValue(context, requestParameter, 2, space.getSpaceKey(), segment.getScheme(), segment.getKey());
                                                val = segment.getVal();
                                                if (segment.getVal() instanceof Collection) {
                                                    if (!(obj4 instanceof String)) {
                                                        obj4 = null;
                                                    }
                                                    obj = new ArrayList();
                                                    obj.add((String) obj4);
                                                } else {
                                                    obj = obj4;
                                                }
                                                if (!(val == null || obj == null)) {
                                                    op = segment.getOp();
                                                    z = segment.getScheme().equals("app") && segment.getKey().equals("package");
                                                    if (ConditionChecker.isMatch(context, op, val, obj, z)) {
                                                        IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Space Segment check passed : " + segment.getScheme() + " / " + segment.getKey() + " / " + segment.getOp() + " / " + (val == null ? "null" : val.toString()) + ", UserValue = " + (obj == null ? "null" : obj.toString()), 3);
                                                    }
                                                }
                                                IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Space Segment check failed : ", 3, false);
                                                IgawLogger.Logging(context, "IGAW_QA", segment.getScheme() + " / " + segment.getKey() + " / " + segment.getOp() + " / " + (val == null ? "null" : val.toString()) + ", UserValue = " + (obj == null ? "null" : obj.toString()), 3);
                                                obj4 = null;
                                                if (obj4 == null) {
                                                    continue;
                                                }
                                            }
                                            obj4 = 1;
                                            if (obj4 == null) {
                                                continue;
                                            }
                                        }
                                    }
                                    if (!notAvailableCampaign.contains(Integer.valueOf(intValue2))) {
                                        if (promotion.getTargetAppScheme() != null && promotion.getTargetAppScheme().length() > 0 && promotion.isIsFilterAlreadyInstalled() && promotion.getDisplay().getStepReward().size() == 0) {
                                            if (ConditionChecker.checkInstalled(context, promotion.getTargetAppScheme())) {
                                            }
                                        }
                                        arrayList.add(Integer.valueOf(intValue2));
                                        if (promotion.getSegments() == null) {
                                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Promotion Check : Segment not exist", 3, false);
                                            break;
                                        }
                                        IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Promotion Segment Check Start", 3);
                                        for (Segment segment2 : promotion.getSegments()) {
                                            obj4 = ConditionChecker.getUserValue(context, requestParameter, 1, new StringBuilder(String.valueOf(promotion.getCampaignKey())).toString(), segment2.getScheme(), segment2.getKey());
                                            val = segment2.getVal();
                                            if (segment2.getVal() instanceof Collection) {
                                                if (!(obj4 instanceof String)) {
                                                    obj4 = null;
                                                }
                                                obj = new ArrayList();
                                                obj.add((String) obj4);
                                            } else {
                                                obj = obj4;
                                            }
                                            if (!(val == null || obj == null)) {
                                                op = segment2.getOp();
                                                z = segment2.getScheme().equals("app") && segment2.getKey().equals("package");
                                                if (ConditionChecker.isMatch(context, op, val, obj, z)) {
                                                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Promotion Segment check passed : " + segment2.getScheme() + " / " + segment2.getKey() + " / " + segment2.getOp() + " / " + val.toString() + ", UserValue = " + obj.toString(), 3);
                                                }
                                            }
                                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Promotion Segment check failed : ", 3, false);
                                            IgawLogger.Logging(context, "IGAW_QA", segment2.getScheme() + " / " + segment2.getKey() + " / " + segment2.getOp() + " / " + (val == null ? "null" : val.toString()) + ", UserValue = " + (obj == null ? "null" : obj.toString()), 3);
                                            obj4 = null;
                                            promotion.setVisible(false);
                                            if (obj4 != null) {
                                                IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > not matched promotion", 3);
                                                if (nextCampaigns.containsKey(space.getSpaceKey()) && ((Integer) nextCampaigns.get(space.getSpaceKey())).intValue() == promotion.getCampaignKey()) {
                                                    nextCampaigns.remove(space.getSpaceKey());
                                                }
                                            } else {
                                                IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > matched promotion", 3);
                                                if (i == 0) {
                                                    if (i2 == 0) {
                                                        i2 = promotion.getCampaignKey();
                                                    }
                                                    if (!nextCampaigns.containsKey(space.getSpaceKey()) && ((Integer) nextCampaigns.get(space.getSpaceKey())).intValue() == promotion.getCampaignKey()) {
                                                        i = ((Integer) nextCampaigns.get(space.getSpaceKey())).intValue();
                                                        if (promotion.getCampaignKey() == ((Integer) campaignList.get(campaignList.size() - 1)).intValue()) {
                                                            nextCampaigns.remove(space.getSpaceKey());
                                                        }
                                                    } else if (!nextCampaigns.containsKey(space.getSpaceKey())) {
                                                        i = promotion.getCampaignKey();
                                                    }
                                                    if (i > 0 && (!nextCampaigns.containsKey(space.getSpaceKey()) || (nextCampaigns.containsKey(space.getSpaceKey()) && ((Integer) nextCampaigns.get(space.getSpaceKey())).intValue() == promotion.getCampaignKey()))) {
                                                        obj2 = 1;
                                                        obj3 = 1;
                                                    }
                                                } else if (obj2 != null) {
                                                    nextCampaigns.put(space.getSpaceKey(), Integer.valueOf(promotion.getCampaignKey()));
                                                    obj2 = null;
                                                    obj3 = 1;
                                                }
                                                obj3 = 1;
                                            }
                                        }
                                        obj4 = 1;
                                        if (obj4 != null) {
                                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > matched promotion", 3);
                                            if (i == 0) {
                                                if (i2 == 0) {
                                                    i2 = promotion.getCampaignKey();
                                                }
                                                if (!nextCampaigns.containsKey(space.getSpaceKey())) {
                                                }
                                                if (nextCampaigns.containsKey(space.getSpaceKey())) {
                                                    i = promotion.getCampaignKey();
                                                }
                                                obj2 = 1;
                                                obj3 = 1;
                                            } else if (obj2 != null) {
                                                nextCampaigns.put(space.getSpaceKey(), Integer.valueOf(promotion.getCampaignKey()));
                                                obj2 = null;
                                                obj3 = 1;
                                            }
                                            obj3 = 1;
                                        } else {
                                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > not matched promotion", 3);
                                            nextCampaigns.remove(space.getSpaceKey());
                                        }
                                    }
                                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > not available campaign - promotion skipped : " + promotion.getDisplay().getTitle(), 3);
                                    break;
                                }
                            }
                        }
                        if (nextCampaigns.containsKey(space.getSpaceKey())) {
                            if (!arrayList.contains(nextCampaigns.get(space.getSpaceKey()))) {
                                nextCampaigns.remove(space.getSpaceKey());
                            }
                        }
                        if (obj3 != null) {
                            int i3;
                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > has visible promotion", 3);
                            if (i != 0 || i2 <= 0) {
                                i3 = i;
                            } else {
                                i3 = i2;
                            }
                            if (promotionActionListener != null) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        PromotionHandler.promotionActionListener.onOpenDialog();
                                    }
                                });
                            }
                            showPromotion(context, space, arrayList, i3);
                            obj = 1;
                        } else {
                            if (promotionActionListener != null) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        PromotionHandler.promotionActionListener.onNoADAvailable();
                                    }
                                });
                            }
                            IgawLogger.Logging(context, "IGAW_QA", "ADBrixManager > Promotion Check : not found available campaign", 3, false);
                            obj = 1;
                        }
                    }
                }
                if (obj == null && promotionActionListener != null) {
                    handler.post(new Runnable() {
                        public void run() {
                            PromotionHandler.promotionActionListener.onNoADAvailable();
                        }
                    });
                }
            } else if (promotionActionListener != null) {
                handler.post(new Runnable() {
                    public void run() {
                        PromotionHandler.promotionActionListener.onNoADAvailable();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showPromotion(Context context, Space space, ArrayList<Integer> arrayList, int i) {
        new Handler(Looper.getMainLooper()).post(new AnonymousClass5(context, i, arrayList, space));
    }
}
