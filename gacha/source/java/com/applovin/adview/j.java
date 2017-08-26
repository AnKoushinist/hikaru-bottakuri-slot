package com.applovin.adview;

import com.applovin.impl.adview.ap;
import com.applovin.impl.adview.ar;

class j implements ar {
    final /* synthetic */ AppLovinInterstitialActivity a;

    j(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void a(ap apVar) {
        this.a.d.d("AppLovinInterstitialActivity", "Clicking through from video button...");
        this.a.c();
    }

    public void b(ap apVar) {
        this.a.d.d("AppLovinInterstitialActivity", "Closing ad from video button...");
        this.a.dismiss();
    }

    public void c(ap apVar) {
        this.a.d.d("AppLovinInterstitialActivity", "Skipping video from video button...");
        this.a.skipVideo();
    }
}
