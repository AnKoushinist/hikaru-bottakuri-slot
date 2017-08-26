package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.List;

public class cm extends cl {
    public cm(AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskCacheNativeAdVideos", appLovinSdkImpl, list, appLovinNativeAdLoadListener);
    }

    public cm(AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super("TaskCacheNativeAdVideos", appLovinSdkImpl, list, appLovinNativeAdPrecacheListener);
    }

    private boolean b(NativeAdImpl nativeAdImpl) {
        this.g.c("TaskCacheNativeAdVideos", "Unable to cache video resource " + nativeAdImpl.c());
        a(nativeAdImpl, !q.a(this.h) ? -103 : -202);
        return false;
    }

    protected void a(NativeAdImpl nativeAdImpl) {
        if (this.b != null) {
            this.b.b(nativeAdImpl);
        }
    }

    protected void a(NativeAdImpl nativeAdImpl, int i) {
        if (this.b != null) {
            this.b.b(nativeAdImpl, i);
        }
    }

    protected boolean a(NativeAdImpl nativeAdImpl, y yVar) {
        if (AppLovinSdkUtils.d(nativeAdImpl.c())) {
            this.f.h().a("TaskCacheNativeAdVideos", "Beginning slot video caching for ad " + nativeAdImpl.e());
            if (((Boolean) this.f.a(cb.B)).booleanValue()) {
                String a = a(nativeAdImpl.c(), yVar);
                if (a == null) {
                    return b(nativeAdImpl);
                }
                nativeAdImpl.c(a);
            } else {
                this.f.h().a("TaskCacheNativeAdVideos", "Resource caching is disabled, skipping...");
            }
            return true;
        }
        this.f.h().a("TaskCacheNativeAdVideos", "No video attached to ad, nothing to cache...");
        return true;
    }

    public /* bridge */ /* synthetic */ void run() {
        super.run();
    }
}
