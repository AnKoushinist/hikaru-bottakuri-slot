package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import java.util.List;

public class ck extends cl {
    public ck(AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskCacheNativeAdImages", appLovinSdkImpl, list, appLovinNativeAdLoadListener);
    }

    public ck(AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super("TaskCacheNativeAdImages", appLovinSdkImpl, list, appLovinNativeAdPrecacheListener);
    }

    private boolean b(NativeAdImpl nativeAdImpl) {
        this.g.c("TaskCacheNativeAdImages", "Unable to cache image resource");
        a(nativeAdImpl, !q.a(this.h) ? -103 : -201);
        return false;
    }

    protected void a(NativeAdImpl nativeAdImpl) {
        if (this.b != null) {
            this.b.a(nativeAdImpl);
        }
    }

    protected void a(NativeAdImpl nativeAdImpl, int i) {
        if (this.b != null) {
            this.b.a(nativeAdImpl, i);
        }
    }

    protected boolean a(NativeAdImpl nativeAdImpl, y yVar) {
        this.f.h().a("TaskCacheNativeAdImages", "Beginning slot image caching for ad " + nativeAdImpl.e());
        if (((Boolean) this.f.a(cb.B)).booleanValue()) {
            String a = a(nativeAdImpl.a(), yVar);
            if (a == null) {
                return b(nativeAdImpl);
            }
            nativeAdImpl.a(a);
            a = a(nativeAdImpl.b(), yVar);
            if (a == null) {
                return b(nativeAdImpl);
            }
            nativeAdImpl.b(a);
        } else {
            this.f.h().a("TaskCacheNativeAdImages", "Resource caching is disabled, skipping...");
        }
        return true;
    }

    public /* bridge */ /* synthetic */ void run() {
        super.run();
    }
}
