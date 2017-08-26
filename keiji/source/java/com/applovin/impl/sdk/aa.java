package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import java.util.Map;

class aa implements AppLovinAdRewardListener {
    final /* synthetic */ z a;

    aa(z zVar) {
        this.a = zVar;
    }

    public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
        this.a.a.h().a("IncentivizedAdController", "User declined to view");
    }

    public void userOverQuota(AppLovinAd appLovinAd, Map map) {
        this.a.a.h().a("IncentivizedAdController", "User over quota: " + map);
    }

    public void userRewardRejected(AppLovinAd appLovinAd, Map map) {
        this.a.a.h().a("IncentivizedAdController", "Reward rejected: " + map);
    }

    public void userRewardVerified(AppLovinAd appLovinAd, Map map) {
        this.a.a.h().a("IncentivizedAdController", "Reward validated: " + map);
    }

    public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
        this.a.a.h().a("IncentivizedAdController", "Reward validation failed: " + i);
    }
}
