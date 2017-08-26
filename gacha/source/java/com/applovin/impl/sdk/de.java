package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class de extends by {
    private final AppLovinAdImpl a;

    public de(AppLovinSdkImpl appLovinSdkImpl, AppLovinAd appLovinAd) {
        super("TaskReportReward", appLovinSdkImpl);
        this.a = (AppLovinAdImpl) appLovinAd;
    }

    public void run() {
        String b = z.b();
        String clCode = this.a.getClCode();
        Map hashMap = new HashMap(2);
        if (AppLovinSdkUtils.isValidString(clCode)) {
            hashMap.put("clcode", clCode);
        } else {
            hashMap.put("clcode", "NO_CLCODE");
        }
        if (b != null) {
            hashMap.put("user_id", b);
        }
        bq a = bq.a();
        clCode = a.b(this.a);
        if (clCode != null) {
            hashMap.put("result", clCode);
            Map a2 = a.a(this.a);
            if (a2 != null) {
                hashMap.put("params", a2);
            }
            a("cr", new JSONObject(hashMap), new df(this));
            return;
        }
        this.g.d("TaskReportReward", "No reward result was found for ad: " + this.a);
    }
}
