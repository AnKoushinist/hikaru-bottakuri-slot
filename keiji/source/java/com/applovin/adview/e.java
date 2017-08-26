package com.applovin.adview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.tapjoy.TapjoyConnectCore;

class e implements Runnable {
    final /* synthetic */ AppLovinInterstitialActivity a;

    e(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void run() {
        try {
            if (!this.a.p && this.a.B != null) {
                this.a.p = true;
                this.a.B.setVisibility(0);
                Animation alphaAnimation = new AlphaAnimation(0.0f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER);
                alphaAnimation.setDuration((long) this.a.f.e());
                alphaAnimation.setRepeatCount(0);
                this.a.B.startAnimation(alphaAnimation);
                if (this.a.q() && this.a.C != null) {
                    this.a.C.setVisibility(0);
                    this.a.C.bringToFront();
                }
            }
        } catch (Throwable th) {
            this.a.e.c("AppLovinInterstitialActivity", "Unable to show skip button: " + th);
        }
    }
}
