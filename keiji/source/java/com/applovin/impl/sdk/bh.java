package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinSdkUtils;

class bh implements AppLovinNativeAdPrecacheListener {
    final /* synthetic */ bg a;

    bh(bg bgVar) {
        this.a = bgVar;
    }

    public void a(AppLovinNativeAd appLovinNativeAd) {
        if (!AppLovinSdkUtils.d(appLovinNativeAd.d())) {
            this.a.c((bd) appLovinNativeAd);
        }
    }

    public void a(AppLovinNativeAd appLovinNativeAd, int i) {
        this.a.b(NativeAdImpl.c, i);
    }

    public void b(AppLovinNativeAd appLovinNativeAd) {
        this.a.c((bd) appLovinNativeAd);
    }

    public void b(AppLovinNativeAd appLovinNativeAd, int i) {
        this.a.b.c("NativeAdPreloadManager", "Video failed to cache during native ad preload. " + i);
        this.a.c((bd) appLovinNativeAd);
    }
}
