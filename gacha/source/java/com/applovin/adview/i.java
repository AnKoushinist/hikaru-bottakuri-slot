package com.applovin.adview;

import com.applovin.impl.adview.ao;

class i implements Runnable {
    final /* synthetic */ ao a;
    final /* synthetic */ AppLovinInterstitialActivity b;

    i(AppLovinInterstitialActivity appLovinInterstitialActivity, ao aoVar) {
        this.b = appLovinInterstitialActivity;
        this.a = aoVar;
    }

    public void run() {
        this.b.a(this.b.H, false, (long) this.a.h());
    }
}
