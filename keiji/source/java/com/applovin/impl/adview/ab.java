package com.applovin.impl.adview;

import com.applovin.impl.sdk.AppLovinAdImpl;
import com.applovin.impl.sdk.dm;
import com.applovin.sdk.AppLovinAd;

class ab implements Runnable {
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ String b;
    final /* synthetic */ x c;

    ab(x xVar, AppLovinAd appLovinAd, String str) {
        this.c = xVar;
        this.a = appLovinAd;
        this.b = str;
    }

    public void run() {
        this.c.e.a(this.a, this.b);
        this.c.a(((AppLovinAdImpl) this.a).i());
        float f = ((AppLovinAdImpl) this.a).f();
        if (f > 0.0f) {
            this.c.a(dm.c(f));
            return;
        }
        this.c.g.setVisibility(0);
        this.c.g.setClickable(true);
    }
}
