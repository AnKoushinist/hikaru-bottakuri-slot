package com.applovin.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;

class l implements AppLovinAdClickListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    l(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void adClicked(AppLovinAd appLovinAd) {
        AppLovinAdClickListener g = this.a.c.g();
        if (g != null) {
            g.adClicked(appLovinAd);
        }
    }
}
