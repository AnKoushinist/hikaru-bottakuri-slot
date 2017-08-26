package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.List;

abstract class cl extends ca {
    protected AppLovinNativeAdLoadListener a;
    protected AppLovinNativeAdPrecacheListener b;
    private List c;
    private int d = 0;

    cl(String str, AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super(str, appLovinSdkImpl);
        this.a = appLovinNativeAdLoadListener;
        this.c = list;
    }

    cl(String str, AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super(str, appLovinSdkImpl);
        if (list == null) {
            throw new IllegalArgumentException("Slots cannot be null");
        }
        this.c = list;
        this.b = appLovinNativeAdPrecacheListener;
    }

    private void a(int i) {
        if (this.a != null) {
            this.a.a(i);
        }
    }

    private void a(List list) {
        if (this.a != null) {
            this.a.a(list);
        }
    }

    protected String a(String str, y yVar) {
        if (!AppLovinSdkUtils.d(str)) {
            this.f.h().a(a(), "Asked to cache file with null/empty URL, nothing to do.");
            return null;
        } else if (dm.a(this.f, str)) {
            try {
                String a = yVar.a(this.h, str, true);
                if (a != null) {
                    return a;
                }
                this.g.c(a(), "Unable to cache icon resource " + str);
                return null;
            } catch (Throwable e) {
                this.g.a(a(), "Unable to cache icon resource " + str, e);
                return null;
            }
        } else {
            this.f.h().a(a(), "Domain is not whitelisted, skipping precache for URL " + str);
            return null;
        }
    }

    protected abstract void a(NativeAdImpl nativeAdImpl);

    protected abstract boolean a(NativeAdImpl nativeAdImpl, y yVar);

    public void run() {
        for (NativeAdImpl nativeAdImpl : this.c) {
            y o = this.f.o();
            this.f.h().a(a(), "Beginning resource caching phase...");
            if (a(nativeAdImpl, o)) {
                this.d++;
                a(nativeAdImpl);
            } else {
                this.f.h().d(a(), "Unable to cache resources");
            }
        }
        try {
            if (this.d == this.c.size()) {
                a(this.c);
            } else if (((Boolean) this.f.a(cb.aF)).booleanValue()) {
                this.f.h().d(a(), "Mismatch between successful populations and requested size");
                a(-6);
            } else {
                a(this.c);
            }
        } catch (Throwable th) {
            this.f.h().c(a(), "Encountered exception while notifying publisher code", th);
        }
    }
}
