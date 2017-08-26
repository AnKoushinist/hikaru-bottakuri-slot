package com.adcolony.sdk;

import jp.co.geniee.gnsrewardadapter.GNSAdapterAdColonyRewardVideoAd;
import org.json.JSONObject;

public class k {
    private int a;
    private String b;
    private String c;
    private boolean d;

    k(o oVar) {
        JSONObject b = oVar.b();
        this.a = bb.b(b, "reward_amount");
        this.b = bb.a(b, "reward_name");
        this.d = bb.c(b, "success");
        this.c = bb.a(b, GNSAdapterAdColonyRewardVideoAd.ZONE_ID_COLUMN_NAME);
    }

    public boolean a() {
        return this.d;
    }
}
