package com.tapjoy.internal;

import com.unity3d.ads.metadata.MediationMetaData;

final class gy extends go implements fq {
    public static final bn a = new bn() {
        public final /* synthetic */ Object a(bs bsVar) {
            String str = null;
            int i = 1;
            bsVar.h();
            String str2 = null;
            String str3 = null;
            while (bsVar.j()) {
                String l = bsVar.l();
                if ("id".equals(l)) {
                    str3 = bsVar.m();
                } else if (MediationMetaData.KEY_NAME.equals(l)) {
                    str2 = bsVar.m();
                } else if ("quantity".equals(l)) {
                    i = bsVar.r();
                } else if ("token".equals(l)) {
                    str = bsVar.m();
                } else {
                    bsVar.s();
                }
            }
            bsVar.i();
            return new gy(str3, str2, i, str);
        }
    };
    private final String b;
    private final String c;
    private final int d;
    private final String e;

    gy(String str, String str2, int i, String str3) {
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = str3;
    }

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.c;
    }

    public final int c() {
        return this.d;
    }

    public final String d() {
        return this.e;
    }
}
