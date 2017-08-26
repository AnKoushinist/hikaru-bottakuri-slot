package com.applovin.adview;

import com.applovin.impl.adview.ao;

class h implements Runnable {
    final /* synthetic */ ao a;
    final /* synthetic */ AppLovinInterstitialActivity b;

    h(AppLovinInterstitialActivity appLovinInterstitialActivity, ao aoVar) {
        this.b = appLovinInterstitialActivity;
        this.a = aoVar;
    }

    public void run() {
        this.b.a(this.b.H, true, (long) this.a.g());
    }
}
