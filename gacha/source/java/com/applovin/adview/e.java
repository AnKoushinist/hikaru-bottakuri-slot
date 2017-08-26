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
            if (!this.a.o && this.a.A != null) {
                this.a.o = true;
                this.a.A.setVisibility(0);
                Animation alphaAnimation = new AlphaAnimation(0.0f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER);
                alphaAnimation.setDuration((long) this.a.e.e());
                alphaAnimation.setRepeatCount(0);
                this.a.A.startAnimation(alphaAnimation);
                if (this.a.o() && this.a.B != null) {
                    this.a.B.setVisibility(0);
                    this.a.B.bringToFront();
                }
            }
        } catch (Throwable th) {
            this.a.d.w("AppLovinInterstitialActivity", "Unable to show skip button: " + th);
        }
    }
}
