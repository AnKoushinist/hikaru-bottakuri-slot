package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinPostbackListener;

class bu implements AppLovinPostbackListener {
    final /* synthetic */ AppLovinPostbackListener a;
    final /* synthetic */ PostbackServiceImpl b;

    bu(PostbackServiceImpl postbackServiceImpl, AppLovinPostbackListener appLovinPostbackListener) {
        this.b = postbackServiceImpl;
        this.a = appLovinPostbackListener;
    }

    public void a(String str) {
        this.b.a.h().a("PostbackService", "Successfully dispatched postback to URL " + str);
        if (this.a != null) {
            this.a.a(str);
        }
    }

    public void a(String str, int i) {
        this.b.a.h().d("PostbackService", "Failed to dispatch postback to URL " + str + ": " + i);
        if (this.a != null) {
            this.a.a(str, i);
        }
    }
}
