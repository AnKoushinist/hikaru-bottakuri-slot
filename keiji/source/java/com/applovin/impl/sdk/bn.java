package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import java.util.ArrayList;
import java.util.List;

class bn implements AppLovinNativeAdLoadListener {
    final /* synthetic */ bm a;

    bn(bm bmVar) {
        this.a = bmVar;
    }

    public void a(int i) {
        if (this.a.b != null) {
            this.a.b.a(i);
        }
    }

    public void a(List list) {
        if (this.a.b != null) {
            List arrayList = new ArrayList();
            arrayList.addAll(this.a.a);
            arrayList.addAll(this.a.c);
            this.a.b.a(arrayList);
        }
    }
}
