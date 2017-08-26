package com.igaworks.adbrix.cpe.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.igaworks.adbrix.core.ADBrixHttpManager;

public class FullScreenSlider extends Dialog {
    public static FullScreenSlider slider;
    private Activity activity;
    private int campaignKey;
    private int currentSlideNo = -1;
    private int position;

    public FullScreenSlider(Context context, Activity activity, int i, int i2) {
        super(context, 16973836);
        this.campaignKey = i;
        this.position = i2;
        this.activity = activity;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getWindow().getDecorView().setBackgroundColor(-16777216);
        slider = this;
        if (this.campaignKey == 0) {
            dismiss();
            return;
        }
        if (bundle != null) {
            try {
                this.currentSlideNo = bundle.getInt("slideNo", -1);
            } catch (Exception e) {
                dismiss();
                e.printStackTrace();
            }
        } else {
            this.currentSlideNo = this.position;
        }
        createView();
    }

    private void createView() {
        View frameLayout = new FrameLayout(this.activity);
        frameLayout.setId(27033);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        if (ADBrixHttpManager.schedule == null || ADBrixHttpManager.schedule.getSchedule() == null) {
            dismiss();
            return;
        }
        frameLayout.addView(new PlaceDetailsLayout(this.activity, this.campaignKey, this.currentSlideNo, true));
        addContentView(frameLayout, layoutParams);
    }
}
