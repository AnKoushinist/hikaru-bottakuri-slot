package com.tapjoy.internal;

import android.graphics.Point;
import com.tapjoy.TJAdUnitConstants.String;
import java.net.URL;

public final class gv {
    public static final bn d = new bn() {
        public final /* synthetic */ Object a(bs bsVar) {
            gx gxVar = null;
            bsVar.h();
            Point point = null;
            Point point2 = null;
            while (bsVar.j()) {
                String l = bsVar.l();
                if ("image".equals(l)) {
                    l = bsVar.m();
                    if (!ct.c(l)) {
                        gxVar = new gx(new URL(l));
                    }
                } else if (String.LANDSCAPE.equals(l)) {
                    point2 = AnonymousClass1.b(bsVar);
                } else if ("portrait".equals(l)) {
                    point = AnonymousClass1.b(bsVar);
                } else {
                    bsVar.s();
                }
            }
            bsVar.i();
            return new gv(gxVar, point2, point);
        }

        private static Point b(bs bsVar) {
            Point point = null;
            bsVar.h();
            while (bsVar.j()) {
                if ("offset".equals(bsVar.l())) {
                    bsVar.h();
                    int i = 0;
                    int i2 = 0;
                    while (bsVar.j()) {
                        String l = bsVar.l();
                        if ("x".equals(l)) {
                            i2 = bsVar.r();
                        } else if ("y".equals(l)) {
                            i = bsVar.r();
                        } else {
                            bsVar.s();
                        }
                    }
                    bsVar.i();
                    point = new Point(i2, i);
                } else {
                    bsVar.s();
                }
            }
            bsVar.i();
            return point;
        }
    };
    public final gx a;
    public final Point b;
    public final Point c;

    public gv(gx gxVar, Point point, Point point2) {
        this.a = gxVar;
        this.b = point;
        this.c = point2;
    }
}
