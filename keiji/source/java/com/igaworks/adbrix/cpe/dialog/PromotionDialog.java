package com.igaworks.adbrix.cpe.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.SparseArray;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import com.igaworks.adbrix.core.ADBrixHttpManager;
import com.igaworks.adbrix.cpe.common.DialogActionListener;
import com.igaworks.adbrix.cpe.common.DialogContentsCreator;
import com.igaworks.adbrix.interfaces.ADBrixCallbackListener;
import com.igaworks.adbrix.interfaces.PromotionActionListener;
import com.igaworks.adbrix.model.Language;
import com.igaworks.adbrix.model.Media;
import com.igaworks.adbrix.model.Promotion;
import com.igaworks.adbrix.model.Theme;
import com.igaworks.adbrix.util.CPEConstant;
import com.igaworks.core.IgawLogger;
import java.util.List;

public class PromotionDialog extends Dialog implements DialogActionListener {
    public static PromotionDialog promotionDialog;
    private Activity activity;
    private LayoutParams containerParam;
    private DialogContentsCreator contentsProvider;
    private int currentCampaignKey;
    private int currentSlideNo = -1;
    private boolean isPortrait;
    private Media media;
    private ADBrixCallbackListener onPlayBtnClickListener;
    private PromotionActionListener promotionActionListener;
    private SparseArray<Promotion> promotions;
    private int windowPadding;

    public PromotionDialog(Context context, int i, List<Integer> list, String str, ADBrixCallbackListener aDBrixCallbackListener, PromotionActionListener promotionActionListener) {
        super(context);
        try {
            this.activity = (Activity) context;
            this.onPlayBtnClickListener = aDBrixCallbackListener;
            this.promotionActionListener = promotionActionListener;
            promotionDialog = this;
            this.media = ADBrixHttpManager.schedule.getSchedule().getMedia();
            if (this.media == null) {
                this.media = new Media();
            }
            if (this.media.getLanguage() == null) {
                this.media.setLanguage(new Language());
            }
            if (this.media.getTheme() == null) {
                this.media.setTheme(new Theme());
            }
            if (list == null || list.size() < 1) {
                dismiss();
                return;
            }
            this.promotions = new SparseArray();
            for (Promotion promotion : ADBrixHttpManager.schedule.getSchedule().getPromotions()) {
                if (list.contains(Integer.valueOf(promotion.getCampaignKey()))) {
                    this.promotions.put(promotion.getCampaignKey(), promotion);
                }
            }
            requestWindowFeature(1);
            this.windowPadding = CPEConstant.convertPixelToDP(this.activity, 10, true);
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.flags = 2;
            attributes.dimAmount = 0.7f;
            attributes.width = -1;
            attributes.height = -1;
            attributes.gravity = 17;
            getWindow().setAttributes(attributes);
            getWindow().setSoftInputMode(16);
            getWindow().getDecorView().setBackgroundColor(0);
            getWindow().getDecorView().setPadding(0, 0, 0, 0);
            getWindow().setFormat(1);
            getWindow().addFlags(4096);
            getWindow().setFlags(1024, 1024);
            getWindow().setGravity(17);
            if (this.activity.getResources().getConfiguration().orientation == 2) {
                this.isPortrait = false;
            } else {
                this.isPortrait = true;
            }
            IgawLogger.Logging(this.activity, "IGAW_QA", String.format("Promotion Dialog Open : primary campaign key = %d, current campaign key = %d, slide no = %d", new Object[]{Integer.valueOf(i), Integer.valueOf(this.currentCampaignKey), Integer.valueOf(this.currentSlideNo)}), 3);
            this.contentsProvider = new DialogContentsCreator(getContext(), this.activity, this.media, list, this.promotions, this.isPortrait, this.currentCampaignKey, i, str, this, new Handler(), true);
            this.containerParam = new LayoutParams(-1, -1);
            addContentView(this.contentsProvider.getRootView(), this.containerParam);
        } catch (Exception e) {
            dismiss();
            e.printStackTrace();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            if (this.contentsProvider != null) {
                this.contentsProvider.onResume();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        finishDialog();
    }

    public void finishDialog() {
        if (this.promotionActionListener != null) {
            this.promotionActionListener.onHideDialog();
        }
        dismiss();
    }

    public void setSlideArea(int i, int i2) {
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            try {
                if (this.contentsProvider != null) {
                    this.contentsProvider.onResume();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onPlayBtnClick() {
        try {
            if (this.promotionActionListener != null) {
                this.promotionActionListener.onPlayButtonClick();
            }
            if (this.onPlayBtnClickListener != null) {
                this.onPlayBtnClickListener.run();
            }
        } catch (Exception e) {
        }
    }
}
