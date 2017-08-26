package com.tapjoy.internal;

import com.unity3d.ads.metadata.MediationMetaData;

final class gl extends gb implements fd {
    public static final bm a = new bm() {
        public final /* synthetic */ Object a(br brVar) {
            String str = null;
            int i = 1;
            brVar.h();
            String str2 = null;
            String str3 = null;
            while (brVar.j()) {
                String l = brVar.l();
                if ("id".equals(l)) {
                    str3 = brVar.m();
                } else if (MediationMetaData.KEY_NAME.equals(l)) {
                    str2 = brVar.m();
                } else if ("quantity".equals(l)) {
                    i = brVar.r();
                } else if ("token".equals(l)) {
                    str = brVar.m();
                } else {
                    brVar.s();
                }
            }
            brVar.i();
            return new gl(str3, str2, i, str);
        }
    };
    private final String b;
    private final String c;
    private final int d;
    private final String e;

    gl(String str, String str2, int i, String str3) {
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
