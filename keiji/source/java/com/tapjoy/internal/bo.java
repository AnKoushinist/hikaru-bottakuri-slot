package com.tapjoy.internal;

import android.graphics.Point;
import android.graphics.Rect;
import twitter4j.TwitterResponse;

public final class bo {
    public static final bn a = new bn() {
        public final /* synthetic */ Object a(bs bsVar) {
            Point point = new Point();
            bsVar.h();
            while (bsVar.j()) {
                String l = bsVar.l();
                if ("x".equals(l)) {
                    point.x = bsVar.r();
                } else if ("y".equals(l)) {
                    point.y = bsVar.r();
                } else {
                    bsVar.s();
                }
            }
            bsVar.i();
            return point;
        }
    };
    public static final bn b = new bn() {
        public final /* synthetic */ Object a(bs bsVar) {
            Rect rect = new Rect();
            switch (AnonymousClass3.a[bsVar.k().ordinal()]) {
                case TwitterResponse.READ /*1*/:
                    bsVar.f();
                    rect.left = bsVar.r();
                    rect.top = bsVar.r();
                    rect.right = bsVar.r();
                    rect.bottom = bsVar.r();
                    while (bsVar.j()) {
                        bsVar.s();
                    }
                    bsVar.g();
                    break;
                case TwitterResponse.READ_WRITE /*2*/:
                    bsVar.h();
                    while (bsVar.j()) {
                        String l = bsVar.l();
                        if ("left".equals(l)) {
                            rect.left = bsVar.r();
                        } else if ("top".equals(l)) {
                            rect.top = bsVar.r();
                        } else if ("right".equals(l)) {
                            rect.right = bsVar.r();
                        } else if ("bottom".equals(l)) {
                            rect.bottom = bsVar.r();
                        } else {
                            bsVar.s();
                        }
                    }
                    bsVar.i();
                    break;
                default:
                    throw new IllegalStateException("Unexpected token: " + bsVar.k());
            }
            return rect;
        }
    };

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[bx.values().length];

        static {
            try {
                a[bx.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[bx.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }
}
