package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdUpdateListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

class l implements AppLovinAdLoadListener, AppLovinAdUpdateListener {
    private final WeakReference a;
    private final AppLovinAdService b;
    private final AppLovinLogger c;

    l(AdViewControllerImpl adViewControllerImpl, AppLovinSdk appLovinSdk) {
        if (adViewControllerImpl == null) {
            throw new IllegalArgumentException("No view specified");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            this.a = new WeakReference(adViewControllerImpl);
            this.c = appLovinSdk.h();
            this.b = appLovinSdk.e();
        }
    }

    public void a(AppLovinAd appLovinAd) {
        AdViewControllerImpl adViewControllerImpl = (AdViewControllerImpl) this.a.get();
        if (adViewControllerImpl != null) {
            adViewControllerImpl.b(appLovinAd);
            return;
        }
        this.b.a((AppLovinAdUpdateListener) this, appLovinAd.b());
        this.c.e("AppLovinAdView", "Ad view has been garbage collected by the time an ad was updated");
    }

    public void adReceived(AppLovinAd appLovinAd) {
        AdViewControllerImpl adViewControllerImpl = (AdViewControllerImpl) this.a.get();
        if (adViewControllerImpl != null) {
            adViewControllerImpl.b(appLovinAd);
        } else {
            this.c.e("AppLovinAdView", "Ad view has been garbage collected by the time an ad was recieved");
        }
    }

    public void failedToReceiveAd(int i) {
        AdViewControllerImpl adViewControllerImpl = (AdViewControllerImpl) this.a.get();
        if (adViewControllerImpl != null) {
            adViewControllerImpl.b(i);
        }
    }

    public String toString() {
        return "[AdViewController listener: " + hashCode() + "]";
    }
}
