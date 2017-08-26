package com.tapjoy.internal;

import org.cocos2dx.lib.BuildConfig;

final class gj extends gb implements fc {
    public static final bm a = new bm() {
        public final /* synthetic */ Object a(br brVar) {
            String str = BuildConfig.FLAVOR;
            String str2 = BuildConfig.FLAVOR;
            brVar.h();
            while (brVar.j()) {
                String l = brVar.l();
                if ("campaign_id".equals(l)) {
                    str = brVar.c(BuildConfig.FLAVOR);
                } else if ("product_id".equals(l)) {
                    str2 = brVar.c(BuildConfig.FLAVOR);
                } else {
                    brVar.s();
                }
            }
            brVar.i();
            return new gj(str, str2);
        }
    };
    private final String b;
    private final String c;

    gj(String str, String str2) {
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
