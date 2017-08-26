package com.applovin.adview;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.applovin.impl.sdk.z;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

public class AppLovinIncentivizedInterstitial {
    private z a;

    public AppLovinIncentivizedInterstitial(AppLovinSdk appLovinSdk) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("SDK cannot be null");
        }
        this.a = new z(appLovinSdk);
    }

    public static AppLovinIncentivizedInterstitial a(Context context) {
        return a(AppLovinSdk.c(context));
    }

    public static AppLovinIncentivizedInterstitial a(AppLovinSdk appLovinSdk) {
        return new AppLovinIncentivizedInterstitial(appLovinSdk);
    }

    public void a(Activity activity, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        a(activity, null, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
    }

    public void a(Activity activity, String str, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        this.a.a(activity, str, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
    }

    public void a(AppLovinAdLoadListener appLovinAdLoadListener) {
        if (appLovinAdLoadListener == null) {
            Log.i("AppLovinIncentivizedInterstitial", "AppLovinAdLoadListener was null when preloading incentivized interstitials; using a listener is highly recommended.");
        }
        this.a.a(appLovinAdLoadListener);
    }

    public void a(String str) {
        z.a(str);
    }

    public boolean a() {
        return this.a.a();
    }

    public void b() {
        this.a.d();
    }
}
