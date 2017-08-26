package com.applovin.impl.adview;

import com.applovin.impl.sdk.AppLovinSdkImpl;

class k implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;

    private k(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    public void run() {
        if (this.a.n != null) {
            this.a.d.a("AppLovinAdView", "Rendering advertisement ad for #" + this.a.n.a() + " over placement: \"" + this.a.f + "\"...");
            AdViewControllerImpl.b(this.a.i, this.a.n.b());
            this.a.i.a(this.a.n, this.a.f, (AppLovinSdkImpl) this.a.b);
        }
    }
}
