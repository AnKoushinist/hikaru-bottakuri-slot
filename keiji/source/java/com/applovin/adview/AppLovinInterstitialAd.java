package com.applovin.adview;

import android.app.Activity;
import android.view.View;
import com.applovin.impl.adview.InterstitialAdDialogCreatorImpl;
import com.applovin.sdk.AppLovinSdk;

public class AppLovinInterstitialAd extends View {
    private AppLovinInterstitialAdDialog a;

    public static AppLovinInterstitialAdDialog a(AppLovinSdk appLovinSdk, Activity activity) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (activity != null) {
            return new InterstitialAdDialogCreatorImpl().a(appLovinSdk, activity);
        } else {
            throw new IllegalArgumentException("No activity specified");
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            this.a.a();
        }
    }
}
