package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinPostbackService;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;

public class PostbackServiceImpl implements AppLovinPostbackService {
    private final AppLovinSdkImpl a;

    PostbackServiceImpl(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
    }

    public void a(String str, AppLovinPostbackListener appLovinPostbackListener) {
        a(str, null, appLovinPostbackListener);
    }

    public void a(String str, Map map, int i, int i2, int i3, AppLovinPostbackListener appLovinPostbackListener) {
        if (AppLovinSdkUtils.d(str)) {
            ca cpVar = new cp(this.a, str, map, appLovinPostbackListener);
            cpVar.b(i2);
            cpVar.a(i);
            cpVar.c(i3);
            this.a.m().a(cpVar, cw.POSTBACKS);
            return;
        }
        this.a.h().d("PostbackService", "Requested a postback dispatch for an empty URL; nothing to do...");
        if (appLovinPostbackListener != null) {
            appLovinPostbackListener.a(str, -900);
        }
    }

    public void a(String str, Map map, AppLovinPostbackListener appLovinPostbackListener) {
        if (AppLovinSdkUtils.d(str)) {
            this.a.m().a(new cp(this.a, str, map, new bu(this, appLovinPostbackListener)), cw.POSTBACKS);
            return;
        }
        this.a.h().a("PostbackService", "Ignoring enqueued postback request to invalid URL");
    }
}
