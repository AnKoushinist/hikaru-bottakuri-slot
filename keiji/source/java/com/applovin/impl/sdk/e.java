package com.applovin.impl.sdk;

import android.net.Uri;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.sdk.AppLovinPostbackListener;

class e implements AppLovinPostbackListener {
    final /* synthetic */ AdViewControllerImpl a;
    final /* synthetic */ Uri b;
    final /* synthetic */ AppLovinAdImpl c;
    final /* synthetic */ AppLovinAdView d;
    final /* synthetic */ AppLovinAdServiceImpl e;

    e(AppLovinAdServiceImpl appLovinAdServiceImpl, AdViewControllerImpl adViewControllerImpl, Uri uri, AppLovinAdImpl appLovinAdImpl, AppLovinAdView appLovinAdView) {
        this.e = appLovinAdServiceImpl;
        this.a = adViewControllerImpl;
        this.b = uri;
        this.c = appLovinAdImpl;
        this.d = appLovinAdView;
    }

    public void a(String str) {
        this.e.f.post(new f(this));
    }

    public void a(String str, int i) {
        this.e.f.post(new g(this));
    }
}
