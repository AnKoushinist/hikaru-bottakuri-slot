package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;

class bk implements AppLovinNativeAdPrecacheListener {
    final /* synthetic */ AppLovinNativeAdPrecacheListener a;
    final /* synthetic */ bi b;

    bk(bi biVar, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        this.b = biVar;
        this.a = appLovinNativeAdPrecacheListener;
    }

    public void a(AppLovinNativeAd appLovinNativeAd) {
        this.b.a(this.a, appLovinNativeAd, false);
        this.b.b(appLovinNativeAd, this.a);
    }

    public void a(AppLovinNativeAd appLovinNativeAd, int i) {
        this.b.a(this.a, appLovinNativeAd, i, false);
    }

    public void b(AppLovinNativeAd appLovinNativeAd) {
    }

    public void b(AppLovinNativeAd appLovinNativeAd, int i) {
    }
}
