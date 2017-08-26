package com.igaworks.adbrix.cpe.activitydialog;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.m;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.f;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.igaworks.adbrix.core.ADBrixHttpManager;
import com.igaworks.adbrix.cpe.common.CirclePageIndicator;
import com.igaworks.adbrix.cpe.common.PageIndicator;
import com.igaworks.adbrix.model.Promotion;
import com.igaworks.adbrix.util.CPEConstant;
import java.util.ArrayList;
import java.util.List;

public class PlaceDetailsFragment extends m {
    public static PlaceDetailsFragment pdFragment;
    private int campaignKey;
    private boolean isFullScreen = false;
    private PlaceSlidesFragmentAdapter mAdapter;
    private PageIndicator mIndicator;
    public ViewPager mPager;
    private Promotion promotion;
    private int slideNo = -1;
    private List<Bitmap> usedBitmap;

    public static PlaceDetailsFragment newInstance(int i, int i2, boolean z) {
        PlaceDetailsFragment placeDetailsFragment = new PlaceDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("campaignKey", i);
        bundle.putInt("slideNo", i2);
        bundle.putBoolean("isFullScreen", z);
        placeDetailsFragment.setArguments(bundle);
        return placeDetailsFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        pdFragment = this;
        this.campaignKey = getArguments().getInt("campaignKey");
        this.slideNo = getArguments().getInt("slideNo", -1);
        this.isFullScreen = getArguments().getBoolean("isFullScreen", false);
        try {
            for (Promotion promotion : ADBrixHttpManager.schedule.getSchedule().getPromotions()) {
                if (promotion.getCampaignKey() == this.campaignKey) {
                    this.promotion = promotion;
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (PromotionActivityDialog.promotionDialog != null) {
                PromotionActivityDialog.promotionDialog.finish();
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View frameLayout = new FrameLayout(getActivity());
        frameLayout.setLayoutParams(new LayoutParams(-1, -1));
        if (this.promotion == null) {
            PromotionActivityDialog.promotionDialog.finish();
            return frameLayout;
        }
        this.mAdapter = new PlaceSlidesFragmentAdapter(getChildFragmentManager(), this.promotion.getDisplay().getSlide().getResource(), this.campaignKey, this.isFullScreen);
        this.mPager = new ViewPager(getActivity());
        this.mPager.setId(10649);
        this.mPager.setAdapter(this.mAdapter);
        frameLayout.addView(this.mPager);
        if (this.promotion.getDisplay().getSlide().getResource() != null && this.promotion.getDisplay().getSlide().getResource().size() > 1) {
            this.mIndicator = new CirclePageIndicator(getActivity());
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2, 80);
            this.mIndicator.setViewPager(this.mPager);
            ((CirclePageIndicator) this.mIndicator).setSnap(true);
            int convertPixelToDP = CPEConstant.convertPixelToDP(getActivity(), 4, true);
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
        return frameLayout;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
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

    public void onDetach() {
        super.onDetach();
        try {
            if (this.usedBitmap != null && !this.isFullScreen) {
                this.usedBitmap.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
