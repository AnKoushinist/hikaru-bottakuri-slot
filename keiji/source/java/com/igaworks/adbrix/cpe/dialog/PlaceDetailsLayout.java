package com.igaworks.adbrix.cpe.dialog;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.f;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.igaworks.adbrix.core.ADBrixHttpManager;
import com.igaworks.adbrix.cpe.common.CirclePageIndicator;
import com.igaworks.adbrix.cpe.common.PageIndicator;
import com.igaworks.adbrix.model.Promotion;
import com.igaworks.adbrix.util.CPEConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlaceDetailsLayout extends LinearLayout {
    public static PlaceDetailsLayout pdLayout;
    private int campaignKey;
    private boolean isFullScreen = false;
    private PlaceSlidesAdapter mAdapter;
    private PageIndicator mIndicator;
    public ViewPager mPager;
    private Promotion promotion;
    private int slideNo = -1;
    private List<Bitmap> usedBitmap;

    public PlaceDetailsLayout(Activity activity, int i, int i2, boolean z) {
        super(activity);
        pdLayout = this;
        setLayoutParams(new LayoutParams(-1, -1));
        this.campaignKey = i;
        this.slideNo = i2;
        this.isFullScreen = z;
        try {
            Promotion promotion;
            Iterator it = ADBrixHttpManager.schedule.getSchedule().getPromotions().iterator();
            do {
                if (!it.hasNext()) {
                    break;
                }
                promotion = (Promotion) it.next();
            } while (promotion.getCampaignKey() != i);
            this.promotion = promotion;
        } catch (Exception e) {
            e.printStackTrace();
            if (PromotionDialog.promotionDialog != null) {
                PromotionDialog.promotionDialog.finishDialog();
            }
        }
        init(activity);
    }

    public void init(Activity activity) {
        View frameLayout = new FrameLayout(activity);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        if (this.promotion == null) {
            PromotionDialog.promotionDialog.finishDialog();
            return;
        }
        this.mAdapter = new PlaceSlidesAdapter(activity, this.promotion.getDisplay().getSlide().getResource(), this.campaignKey, this.isFullScreen);
        this.mPager = new ViewPager(activity);
        this.mPager.setId(10649);
        this.mPager.setAdapter(this.mAdapter);
        frameLayout.addView(this.mPager);
        if (this.promotion.getDisplay().getSlide().getResource() != null && this.promotion.getDisplay().getSlide().getResource().size() > 1) {
            this.mIndicator = new CirclePageIndicator(activity);
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 80);
            this.mIndicator.setViewPager(this.mPager);
            ((CirclePageIndicator) this.mIndicator).setSnap(true);
            int convertPixelToDP = CPEConstant.convertPixelToDP(activity, 4, true);
            ((CirclePageIndicator) this.mIndicator).setPadding(convertPixelToDP, convertPixelToDP, convertPixelToDP, convertPixelToDP);
            ((CirclePageIndicator) this.mIndicator).setLayoutParams(layoutParams);
            this.mIndicator.setOnPageChangeListener(new f() {
                public void onPageSelected(int i) {
                }

                public void onPageScrolled(int i, float f, int i2) {
                }

                public void onPageScrollStateChanged(int i) {
                }
            });
            frameLayout.addView((CirclePageIndicator) this.mIndicator);
        }
        if (this.slideNo > -1) {
            this.mPager.setCurrentItem(this.slideNo);
        }
        addView(frameLayout);
    }

    public void addUsingBitmap(Bitmap bitmap) {
        if (!this.isFullScreen) {
            if (this.usedBitmap == null) {
                this.usedBitmap = new ArrayList();
            }
            if (!this.usedBitmap.contains(bitmap)) {
                this.usedBitmap.add(bitmap);
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            if (this.usedBitmap != null && !this.isFullScreen) {
                this.usedBitmap.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
