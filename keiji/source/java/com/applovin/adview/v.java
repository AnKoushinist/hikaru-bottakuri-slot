package com.applovin.adview;

import android.view.View;
import android.view.View.OnClickListener;

class v implements OnClickListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    v(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void onClick(View view) {
        this.a.z.performClick();
    }
}
