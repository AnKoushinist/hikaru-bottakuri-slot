package com.applovin.impl.adview;

import com.applovin.adview.AppLovinInterstitialAdDialog;

class i implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;

    private i(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    public void run() {
        if (this.a.n != null) {
            try {
                this.a.d.a("AppLovinAdView", "Rendering interstitial ad for #" + this.a.n.a() + " over placement: \"" + this.a.f + "\"...");
                AppLovinInterstitialAdDialog a = new InterstitialAdDialogCreatorImpl().a(this.a.b, this.a.a);
                a.a(new d(this.a));
                a.a(new e(this.a));
                a.a(new c(this.a));
                a.a(this.a.n, this.a.f);
            } catch (Throwable th) {
            }
        }
    }
}
