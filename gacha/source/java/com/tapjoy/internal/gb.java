package com.tapjoy.internal;

import java.util.Arrays;

abstract class gb implements ey {
    private static final String[] a;

    gb() {
    }

    static {
        String[] strArr = new String[]{"reward", "purchase", "custom_action"};
        a = strArr;
        Arrays.sort(strArr);
    }

    public final void a(ez ezVar) {
        if (this instanceof fc) {
            fc fcVar = (fc) this;
            ezVar.a(fcVar.a(), fcVar.b());
        } else if (this instanceof fd) {
            fd fdVar = (fd) this;
            ezVar.a(fdVar.a(), fdVar.b(), fdVar.c(), fdVar.d());
        }
    }

    public static boolean a(String str) {
        return Arrays.binarySearch(a, str) >= 0;
    }

    public static gb a(String str, br brVar) {
        if ("reward".equals(str)) {
            return (gb) brVar.a(gl.a);
        }
        if ("purchase".equals(str)) {
            return (gb) brVar.a(gj.a);
        }
        return null;
    }
}
