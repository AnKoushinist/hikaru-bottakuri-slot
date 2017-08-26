package com.applovin.adview;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class b implements AnimationListener {
    final /* synthetic */ View a;
    final /* synthetic */ boolean b;
    final /* synthetic */ AppLovinInterstitialActivity c;

    b(AppLovinInterstitialActivity appLovinInterstitialActivity, View view, boolean z) {
        this.c = appLovinInterstitialActivity;
        this.a = view;
        this.b = z;
    }

    public void onAnimationEnd(Animation animation) {
        if (!this.b) {
            this.a.setVisibility(4);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
        this.a.setVisibility(0);
    }
}
