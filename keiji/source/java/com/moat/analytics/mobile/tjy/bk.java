package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.functional.a;

class bk implements bc {
    private static final a a;

    static {
        a a = a.a();
        try {
            a = a.a(WebAdTracker.class.getMethod("track", new Class[0]));
        } catch (Exception e) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(e);
        }
        a = a;
    }

    bk() {
    }

    public Class a() {
        return WebAdTracker.class;
    }
}
