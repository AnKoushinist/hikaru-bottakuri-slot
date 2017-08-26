package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.List;

class bp implements AppLovinNativeAdLoadListener {
    final /* synthetic */ AppLovinNativeAdLoadListener a;
    final /* synthetic */ bi b;

    bp(bi biVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.b = biVar;
        this.a = appLovinNativeAdLoadListener;
    }

    public void a(int i) {
        this.b.a(this.a, i);
    }

    public void a(List list) {
        this.b.a(this.a, list);
    }
}
