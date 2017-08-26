package com.igaworks.adbrix.cpe.activitydialog;

import android.os.Bundle;
import android.support.v4.app.j;
import android.support.v4.app.r;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import com.igaworks.adbrix.core.ADBrixHttpManager;

public class FullScreenSlider extends j {
    public static FullScreenSlider slider;
    private int campaignKey;
    private int currentSlideNo = -1;
    private int position;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        slider = this;
        LayoutParams attributes = getWindow().getAttributes();
        attributes.flags = 2;
        attributes.dimAmount = 0.6f;
        attributes.width = -1;
        attributes.height = -2;
        getWindow().setAttributes(attributes);
        getWindow().setSoftInputMode(16);
        getWindow().getDecorView().setBackgroundColor(-16777216);
        getWindow().setFormat(1);
        getWindow().addFlags(4096);
        this.campaignKey = getIntent().getIntExtra("campaignKey", 0);
        this.position = getIntent().getIntExtra("position", 0);
        if (this.campaignKey == 0) {
            finish();
            return;
        }
        if (bundle != null) {
            try {
                this.currentSlideNo = bundle.getInt("slideNo", -1);
            } catch (Exception e) {
                finish();
                e.printStackTrace();
            }
        } else {
            this.currentSlideNo = this.position;
        }
        createView();
    }

    private void createView() {
        View frameLayout = new FrameLayout(this);
        frameLayout.setId(27033);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        if (ADBrixHttpManager.schedule == null || ADBrixHttpManager.schedule.getSchedule() == null) {
            finish();
            return;
        }
        r a = getSupportFragmentManager().a();
        a.b(27033, PlaceDetailsFragment.newInstance(this.campaignKey, this.currentSlideNo, true));
        a.a(4099);
        a.b();
        addContentView(frameLayout, layoutParams);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (PlaceDetailsFragment.pdFragment != null && PlaceDetailsFragment.pdFragment.mPager != null) {
            bundle.putInt("slideNo", PlaceDetailsFragment.pdFragment.mPager.getCurrentItem());
        }
    }
}
