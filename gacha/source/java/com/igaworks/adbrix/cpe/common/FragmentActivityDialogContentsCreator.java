package com.igaworks.adbrix.cpe.common;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.SparseArray;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.igaworks.adbrix.cpe.activitydialog.PlaceDetailsFragment;
import com.igaworks.adbrix.model.Media;
import com.igaworks.adbrix.model.Promotion;
import com.igaworks.adbrix.util.CPEConstant;
import java.util.List;

public class FragmentActivityDialogContentsCreator extends CommonDialogContentsCreator {
    private static FragmentActivityDialogContentsCreator singleton;

    private FragmentActivityDialogContentsCreator(Context context, Activity activity, Media media, List<Integer> list, SparseArray<Promotion> sparseArray, boolean z, int i, int i2, String str, DialogActionListener dialogActionListener, Handler handler, boolean z2) {
        super(context, activity, media, list, sparseArray, z, i, i2, str, dialogActionListener, handler, z2);
    }

    public static FragmentActivityDialogContentsCreator getInstance(Context context, Activity activity, Media media, List<Integer> list, SparseArray<Promotion> sparseArray, boolean z, int i, int i2, String str, DialogActionListener dialogActionListener, Handler handler, boolean z2, boolean z3) {
        if (z2 || singleton == null) {
            singleton = new FragmentActivityDialogContentsCreator(context, activity, media, list, sparseArray, z, i, i2, str, dialogActionListener, handler, z3);
        } else {
            singleton.isPortrait = z;
        }
        return singleton;
    }

    public void finishDialog() {
        try {
            if (this.adImageSectionLl != null) {
                this.adImageSectionLl.removeAllViews();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.actionListener.finishDialog();
    }

    public void setSlideImageSection() {
        try {
            LayoutParams layoutParams;
            this.slideArea = new FrameLayout(this.context);
            if (this.isPortrait) {
                layoutParams = new LinearLayout.LayoutParams(CPEConstant.convertPixelToDP(this.context, 395, true), CPEConstant.convertPixelToDP(this.context, 246, false));
            } else {
                layoutParams = new LinearLayout.LayoutParams(CPEConstant.convertPixelToDP(this.context, 442, true), CPEConstant.convertPixelToDP(this.context, 275, false));
            }
            this.slideArea.setId(6553);
            this.slideArea.setLayoutParams(layoutParams);
            this.slideArea.setBackgroundColor(-1);
            this.adImageSectionLl.addView(this.slideArea);
            this.actionListener.setSlideArea(((Promotion) this.promotions.get(this.currentCampaignKey)).getCampaignKey(), this.currentSlideNo);
            if (this.currentSlideNo > -1 && PlaceDetailsFragment.pdFragment != null && PlaceDetailsFragment.pdFragment.mPager != null) {
                this.currentSlideNo = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.actionListener.finishDialog();
        }
    }

    public void changePromotionContents() {
        try {
            this.adTitleTv.setText(((Promotion) this.promotions.get(this.currentCampaignKey)).getDisplay().getTitle());
            this.actionListener.setSlideArea(((Promotion) this.promotions.get(this.currentCampaignKey)).getCampaignKey(), this.currentSlideNo);
            setRewardView();
            this.progressModel = null;
            setPlayBtnClickListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
