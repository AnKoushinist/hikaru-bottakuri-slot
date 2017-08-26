package com.tapjoy.internal;

import org.cocos2dx.lib.BuildConfig;

final class gw extends go implements fp {
    public static final bn a = new bn() {
        public final /* synthetic */ Object a(bs bsVar) {
            String str = BuildConfig.FLAVOR;
            String str2 = BuildConfig.FLAVOR;
            bsVar.h();
            while (bsVar.j()) {
                String l = bsVar.l();
                if ("campaign_id".equals(l)) {
                    str = bsVar.c(BuildConfig.FLAVOR);
                } else if ("product_id".equals(l)) {
                    str2 = bsVar.c(BuildConfig.FLAVOR);
                } else {
                    bsVar.s();
                }
            }
            bsVar.i();
            return new gw(str, str2);
        }
    };
    private final String b;
    private final String c;

    gw(String str, String str2) {
        this.b = str;
        this.c = str2;
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.c;
    }
}
