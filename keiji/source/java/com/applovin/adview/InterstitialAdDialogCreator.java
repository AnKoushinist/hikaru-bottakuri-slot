package com.applovin.adview;

import android.app.Activity;
import com.applovin.sdk.AppLovinSdk;

public interface InterstitialAdDialogCreator {
    AppLovinInterstitialAdDialog a(AppLovinSdk appLovinSdk, Activity activity);
}
