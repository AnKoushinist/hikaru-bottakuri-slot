package com.applovin.adview;

import com.applovin.impl.sdk.AppLovinAdImpl;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class k implements AppLovinAdDisplayListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    k(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        this.a.h = (AppLovinAdImpl) appLovinAd;
        if (!this.a.j) {
            this.a.a(appLovinAd);
        }
    }

    public void adHidden(AppLovinAd appLovinAd) {
        this.a.b(appLovinAd);
    }
}
