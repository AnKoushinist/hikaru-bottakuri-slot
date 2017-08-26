package com.applovin.impl.sdk;

import android.content.Context;
import com.applovin.sdk.AppLovinTargetingData;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class m implements AppLovinTargetingData {
    private final AppLovinSdkImpl a;
    private final Context b;

    m(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.j();
    }

    Map a() {
        Map hashMap = new HashMap();
        Map all = this.b.getSharedPreferences("applovin.sdk.targeting", 0).getAll();
        if (all != null && all.size() > 0) {
            for (Entry entry : all.entrySet()) {
                hashMap.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        return hashMap;
    }
}
