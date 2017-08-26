package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;

class bl implements AppLovinNativeAdPrecacheListener {
    final /* synthetic */ AppLovinNativeAdPrecacheListener a;
    final /* synthetic */ bi b;

    bl(bi biVar, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        this.b = biVar;
        this.a = appLovinNativeAdPrecacheListener;
    }

    public void a(AppLovinNativeAd appLovinNativeAd) {
    }

    public void a(AppLovinNativeAd appLovinNativeAd, int i) {
    }

    public void b(AppLovinNativeAd appLovinNativeAd) {
        this.b.a(this.a, appLovinNativeAd, true);
    }

    public void b(AppLovinNativeAd appLovinNativeAd, int i) {
        this.b.a(this.a, appLovinNativeAd, i, true);
    }
}
