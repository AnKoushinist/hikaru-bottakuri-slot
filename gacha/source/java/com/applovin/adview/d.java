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
            if (this.a.n) {
                this.a.y.setVisibility(0);
                return;
            }
            this.a.n = true;
            if (this.a.o() && this.a.z != null) {
                this.a.z.setVisibility(0);
                this.a.z.bringToFront();
            }
            this.a.y.setVisibility(0);
            this.a.y.bringToFront();
            Animation alphaAnimation = new AlphaAnimation(0.0f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER);
            alphaAnimation.setDuration((long) this.a.e.e());
            alphaAnimation.setRepeatCount(0);
            this.a.y.startAnimation(alphaAnimation);
        } catch (Throwable th) {
            this.a.dismiss();
        }
    }
}
