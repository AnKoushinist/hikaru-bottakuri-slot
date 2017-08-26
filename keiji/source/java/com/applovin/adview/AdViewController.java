package com.applovin.adview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

public interface AdViewController {
    void a();

    void a(int i);

    void a(ViewGroup viewGroup, Context context, AppLovinAdSize appLovinAdSize, AppLovinSdk appLovinSdk, AttributeSet attributeSet);

    void a(AppLovinAd appLovinAd, String str);

    void a(AppLovinAdClickListener appLovinAdClickListener);

    void a(AppLovinAdDisplayListener appLovinAdDisplayListener);

    void a(AppLovinAdLoadListener appLovinAdLoadListener);

    void a(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener);

    void a(boolean z);

    void b();

    AppLovinAdSize c();

    void d();
}
