package com.igaworks.adbrix.cpe.activitydialog;

import android.support.v4.app.m;
import android.support.v4.app.r;
import android.support.v4.app.u;
import java.util.List;

public class PlaceSlidesFragmentAdapter extends u {
    private int campaignKey;
    private List<String> imgUrls;
    private boolean isFullScreen = false;
    private int mCount;

    public PlaceSlidesFragmentAdapter(r rVar, List<String> list, int i, boolean z) {
        super(rVar);
        this.mCount = list.size();
        this.imgUrls = list;
        this.campaignKey = i;
        this.isFullScreen = z;
    }

    public m getItem(int i) {
        return PlaceSlideFragment.newInstance((String) this.imgUrls.get(i), this.campaignKey, i, this.isFullScreen);
    }

    public int getCount() {
        return this.mCount;
    }
}
