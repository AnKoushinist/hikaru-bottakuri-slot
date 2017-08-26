package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;

class y implements AppLovinAdDisplayListener {
    final /* synthetic */ ah a;
    final /* synthetic */ x b;

    y(x xVar, ah ahVar) {
        this.b = xVar;
        this.a = ahVar;
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        super.show();
        if (!this.b.j) {
            AppLovinAdDisplayListener f = this.a.f();
            if (f != null) {
                f.adDisplayed(appLovinAd);
            }
            this.b.j = true;
        }
    }

    public void adHidden(AppLovinAd appLovinAd) {
        this.b.a.runOnUiThread(this.b.f);
        AppLovinAdDisplayListener f = this.a.f();
        if (!(this.b.k || f == null)) {
            f.adHidden(appLovinAd);
            this.b.k = true;
        }
        this.a.a(false);
    }
}
