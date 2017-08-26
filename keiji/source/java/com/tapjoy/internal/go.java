package com.tapjoy.internal;

import java.util.Arrays;

abstract class go implements fl {
    private static final String[] a;

    go() {
    }

    static {
        String[] strArr = new String[]{"reward", "purchase", "custom_action"};
        a = strArr;
        Arrays.sort(strArr);
    }

    public final void a(fm fmVar) {
        if (this instanceof fp) {
            fp fpVar = (fp) this;
            fmVar.a(fpVar.a(), fpVar.b());
        } else if (this instanceof fq) {
            fq fqVar = (fq) this;
            fmVar.a(fqVar.a(), fqVar.b(), fqVar.c(), fqVar.d());
        }
    }

    public static boolean a(String str) {
        return Arrays.binarySearch(a, str) >= 0;
    }

    public static go a(String str, bs bsVar) {
        if ("reward".equals(str)) {
            return (go) bsVar.a(gy.a);
        }
        if ("purchase".equals(str)) {
            return (go) bsVar.a(gw.a);
        }
        return null;
    }
}
