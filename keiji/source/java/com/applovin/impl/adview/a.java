package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;

class a implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ AdViewControllerImpl b;

    a(AdViewControllerImpl adViewControllerImpl, AppLovinAd appLovinAd) {
        this.b = adViewControllerImpl;
        this.a = appLovinAd;
    }

    public void run() {
        try {
            if (this.b.v != null) {
                this.b.v.adReceived(this.a);
            }
        } catch (Throwable th) {
            this.b.d.e("AppLovinSdk", "Exception while running app load callback: " + th.getMessage());
        }
    }
}
