package com.glossomads.Model;

import org.cocos2dx.lib.BuildConfig;

public class GlossomAdsAdReward {
    int amount = 1;
    String name = BuildConfig.FLAVOR;
    boolean success;
    String zoneId;

    public GlossomAdsAdReward(boolean z, String str) {
        this.success = z;
        this.zoneId = str;
    }

    public boolean success() {
        return this.success;
    }

    public String name() {
        return this.name;
    }

    public String getZoneId() {
        return this.zoneId;
    }

    public int amount() {
        return this.amount;
    }

    public String toString() {
        return this.success ? this.zoneId + ":" + this.amount : "no reward";
    }
}
