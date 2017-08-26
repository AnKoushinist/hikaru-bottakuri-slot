package com.adcolony.sdk;

import com.moat.analytics.mobile.tjy.MoatAdEvent;
import jp.co.geniee.gnsrewardadapter.GNSAdapterAdColonyRewardVideoAd;
import org.json.JSONObject;

public class m {
    private String a;
    private String b;
    private String c;
    private String d;
    private int e = 5;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private boolean m;

    m(String str) {
        this.a = str;
    }

    private int c(int i) {
        if (n.b() && !n.a().c() && !n.a().d()) {
            return i;
        }
        e();
        return 0;
    }

    public int a() {
        return this.f;
    }

    public boolean b() {
        return this.m;
    }

    public int c() {
        return c(this.i);
    }

    void a(o oVar) {
        boolean z = true;
        JSONObject b = oVar.b();
        JSONObject e = bb.e(b, "reward");
        this.b = bb.a(e, "reward_name");
        this.j = bb.b(e, "reward_amount");
        this.h = bb.b(e, "views_per_reward");
        this.g = bb.b(e, "views_until_reward");
        this.c = bb.a(e, "reward_name_plural");
        this.d = bb.a(e, "reward_prompt");
        this.m = bb.c(b, "rewarded");
        this.e = bb.b(b, "status");
        this.f = bb.b(b, MoatAdEvent.EVENT_TYPE);
        this.i = bb.b(b, "play_interval");
        this.a = bb.a(b, GNSAdapterAdColonyRewardVideoAd.ZONE_ID_COLUMN_NAME);
        if (this.e == 1) {
            z = false;
        }
        this.l = z;
    }

    void a(int i) {
        this.k = i;
    }

    int d() {
        return this.k;
    }

    void b(int i) {
        this.e = i;
    }

    private void e() {
        bd.g.b((Object) "The AdColonyZone API is not available while AdColony is disabled.");
    }
}
