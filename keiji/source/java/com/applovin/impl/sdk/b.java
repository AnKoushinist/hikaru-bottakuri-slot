package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class b extends bv {
    public b(AppLovinSdkImpl appLovinSdkImpl) {
        super(appLovinSdkImpl);
    }

    private cd a(AppLovinAdType appLovinAdType, AppLovinAdSize appLovinAdSize) {
        return appLovinAdSize.equals(AppLovinAdSize.a) ? cb.ab : appLovinAdSize.equals(AppLovinAdSize.d) ? cb.ac : appLovinAdSize.equals(AppLovinAdSize.c) ? cb.ad : appLovinAdSize.equals(AppLovinAdSize.b) ? cb.ae : cb.ab;
    }

    c a(bd bdVar) {
        return new c((AppLovinAd) bdVar);
    }

    ca a(c cVar) {
        ca crVar = new cr(cVar.a(), cVar.b(), this, this.a);
        crVar.a(true);
        return crVar;
    }

    Map a() {
        Map hashMap = new HashMap(5);
        for (AppLovinAdSize appLovinAdSize : AppLovinAdSize.d()) {
            hashMap.put(new c(appLovinAdSize, AppLovinAdType.a), new bw(((Integer) this.a.a(a(AppLovinAdType.a, appLovinAdSize))).intValue()));
        }
        hashMap.put(new c(AppLovinAdSize.c, AppLovinAdType.b), new bw(((Integer) this.a.a(cb.af)).intValue()));
        return hashMap;
    }

    public void a(int i) {
    }

    public void a(c cVar, int i) {
        b(cVar, i);
    }

    void a(Object obj, bd bdVar) {
        ((AppLovinAdLoadListener) obj).adReceived((AppLovinAd) bdVar);
    }

    void a(Object obj, c cVar, int i) {
        if (obj instanceof w) {
            ((w) obj).a(cVar, i);
        }
        if (obj instanceof AppLovinAdLoadListener) {
            ((AppLovinAdLoadListener) obj).failedToReceiveAd(i);
        }
    }

    public void a(List list) {
    }

    public void adReceived(AppLovinAd appLovinAd) {
        c((bd) appLovinAd);
    }

    public /* bridge */ /* synthetic */ bd b(c cVar) {
        return super.b(cVar);
    }

    public /* bridge */ /* synthetic */ void b(c cVar, Object obj) {
        super.b(cVar, obj);
    }

    public /* bridge */ /* synthetic */ boolean c(c cVar) {
        return super.c(cVar);
    }

    public /* bridge */ /* synthetic */ void d(c cVar) {
        super.d(cVar);
    }

    public /* bridge */ /* synthetic */ void e(c cVar) {
        super.e(cVar);
    }

    public void failedToReceiveAd(int i) {
    }
}
