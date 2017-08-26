package com.vungle.publisher;

import dagger.a.c;

/* compiled from: vungle */
public enum AdConfig_Factory implements c<AdConfig> {
    ;

    private AdConfig_Factory(String str) {
    }

    public final AdConfig get() {
        return new AdConfig();
    }

    public static c<AdConfig> create() {
        return INSTANCE;
    }
}
