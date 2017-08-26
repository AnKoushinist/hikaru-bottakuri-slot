package com.tapjoy.internal;

import twitter4j.TwitterResponse;

public enum eb implements dq {
    APP(0),
    CAMPAIGN(1),
    CUSTOM(2),
    USAGES(3);
    
    public static final dn ADAPTER = null;
    private final int a;

    static final class a extends dj {
        a() {
            super(eb.class);
        }

        protected final /* bridge */ /* synthetic */ dq a(int i) {
            return eb.a(i);
        }
    }

    static {
        ADAPTER = new a();
    }

    private eb(int i) {
        this.a = i;
    }

    public static eb a(int i) {
        switch (i) {
            case TwitterResponse.NONE /*0*/:
                return APP;
            case TwitterResponse.READ /*1*/:
                return CAMPAIGN;
            case TwitterResponse.READ_WRITE /*2*/:
                return CUSTOM;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return USAGES;
            default:
                return null;
        }
    }

    public final int a() {
        return this.a;
    }
}
