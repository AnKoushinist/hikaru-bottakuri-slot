package com.igaworks.adbrix.cpe.activitydialog;

import android.support.v4.app.Fragment;
import android.support.v4.app.n;
import android.support.v4.app.q;
import java.util.List;

public class PlaceSlidesFragmentAdapter extends q {
    private int campaignKey;
    private List<String> imgUrls;
    private boolean isFullScreen = false;
    private int mCount;

    public PlaceSlidesFragmentAdapter(n nVar, List<String> list, int i, boolean z) {
        super(nVar);
        this.mCount = list.size();
        this.imgUrls = list;
        this.campaignKey = i;
        this.isFullScreen = z;
    }

    public Fragment getItem(int i) {
        return PlaceSlideFragment.newInstance((String) this.imgUrls.get(i), this.campaignKey, i, this.isFullScreen);
    }

    public int getCount() {
        return this.mCount;
    }
}
