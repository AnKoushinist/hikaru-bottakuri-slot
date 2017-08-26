package com.applovin.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;

public interface AppLovinInterstitialAdDialog {
    void a();

    void a(AppLovinAd appLovinAd, String str);

    void a(AppLovinAdClickListener appLovinAdClickListener);

    void a(AppLovinAdDisplayListener appLovinAdDisplayListener);

    void a(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener);

    void a(String str);

    void b();
}
