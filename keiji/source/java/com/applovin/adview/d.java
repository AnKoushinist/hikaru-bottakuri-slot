package com.applovin.adview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.tapjoy.TapjoyConnectCore;

class d implements Runnable {
    final /* synthetic */ AppLovinInterstitialActivity a;

    d(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void run() {
        try {
            if (this.a.o) {
                this.a.z.setVisibility(0);
                return;
            }
            this.a.o = true;
            if (this.a.q() && this.a.A != null) {
                this.a.A.setVisibility(0);
                this.a.A.bringToFront();
            }
            this.a.z.setVisibility(0);
            this.a.z.bringToFront();
            Animation alphaAnimation = new AlphaAnimation(0.0f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER);
            alphaAnimation.setDuration((long) this.a.f.e());
            alphaAnimation.setRepeatCount(0);
            this.a.z.startAnimation(alphaAnimation);
        } catch (Throwable th) {
            this.a.dismiss();
        }
    }
}
