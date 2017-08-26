package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.nativeAds.AppLovinNativeAdService;
import java.util.ArrayList;
import java.util.List;

class bi implements AppLovinNativeAdService {
    private final AppLovinSdkImpl a;
    private final Object b = new Object();

    bi(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
    }

    private List a(AppLovinNativeAd appLovinNativeAd) {
        List arrayList = new ArrayList(1);
        arrayList.add((NativeAdImpl) appLovinNativeAd);
        return arrayList;
    }

    private void a(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, int i) {
        if (appLovinNativeAdLoadListener != null) {
            try {
                appLovinNativeAdLoadListener.a(i);
            } catch (Throwable e) {
                this.a.h().c("WidgetServiceImpl", "Encountered exception whilst notifying user callback", e);
            }
        }
    }

    private void a(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, List list) {
        if (appLovinNativeAdLoadListener != null) {
            try {
                appLovinNativeAdLoadListener.a(list);
            } catch (Throwable e) {
                this.a.h().c("WidgetServiceImpl", "Encountered exception whilst notifying user callback", e);
            }
        }
    }

    private void a(AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener, AppLovinNativeAd appLovinNativeAd, int i, boolean z) {
        if (appLovinNativeAdPrecacheListener == null) {
            return;
        }
        if (z) {
            try {
                appLovinNativeAdPrecacheListener.b(appLovinNativeAd, i);
                return;
            } catch (Throwable e) {
                this.a.h().c("WidgetServiceImpl", "Encountered exception whilst notifying user callback", e);
                return;
            }
        }
        appLovinNativeAdPrecacheListener.a(appLovinNativeAd, i);
    }

    private void a(AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener, AppLovinNativeAd appLovinNativeAd, boolean z) {
        if (appLovinNativeAdPrecacheListener == null) {
            return;
        }
        if (z) {
            try {
                appLovinNativeAdPrecacheListener.b(appLovinNativeAd);
                return;
            } catch (Throwable e) {
                this.a.h().c("WidgetServiceImpl", "Encountered exception whilst notifying user callback", e);
                return;
            }
        }
        appLovinNativeAdPrecacheListener.a(appLovinNativeAd);
    }

    private void b(AppLovinNativeAd appLovinNativeAd, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        if (appLovinNativeAd.g()) {
            appLovinNativeAdPrecacheListener.b(appLovinNativeAd);
            return;
        }
        this.a.m().a(new cm(this.a, a(appLovinNativeAd), new bl(this, appLovinNativeAdPrecacheListener)), cw.MAIN);
    }

    private void b(List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.a.m().a(new ck(this.a, list, new bo(this, appLovinNativeAdLoadListener)), cw.MAIN);
    }

    private void c(List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.a.m().a(new cm(this.a, list, new bp(this, appLovinNativeAdLoadListener)), cw.MAIN);
    }

    public void a(AppLovinNativeAd appLovinNativeAd, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        if (appLovinNativeAd.f()) {
            appLovinNativeAdPrecacheListener.a(appLovinNativeAd);
            b(appLovinNativeAd, appLovinNativeAdPrecacheListener);
            return;
        }
        this.a.m().a(new ck(this.a, a(appLovinNativeAd), new bk(this, appLovinNativeAdPrecacheListener)), cw.MAIN);
    }

    public void a(List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        int intValue = ((Integer) this.a.a(cb.aQ)).intValue();
        if (intValue > 0) {
            list = list;
            int size = list.size();
            if (size != 0) {
                intValue = Math.min(intValue, size);
                List subList = list.subList(0, intValue);
                b(subList, new bm(this, subList, appLovinNativeAdLoadListener, list.subList(intValue, size)));
            } else if (appLovinNativeAdLoadListener != null) {
                appLovinNativeAdLoadListener.a(-700);
            }
        } else if (appLovinNativeAdLoadListener != null) {
            appLovinNativeAdLoadListener.a(list);
        }
    }
}
