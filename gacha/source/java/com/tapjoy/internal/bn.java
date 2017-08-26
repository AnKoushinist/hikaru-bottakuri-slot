package com.tapjoy.internal;

import android.graphics.Point;
import android.graphics.Rect;
import twitter4j.TwitterResponse;

public final class bn {
    public static final bm a = new bm() {
        public final /* synthetic */ Object a(br brVar) {
            Point point = new Point();
            brVar.h();
            while (brVar.j()) {
                String l = brVar.l();
                if ("x".equals(l)) {
                    point.x = brVar.r();
                } else if ("y".equals(l)) {
                    point.y = brVar.r();
                } else {
                    brVar.s();
                }
            }
            brVar.i();
            return point;
        }
    };
    public static final bm b = new bm() {
        public final /* synthetic */ Object a(br brVar) {
            Rect rect = new Rect();
            switch (AnonymousClass3.a[brVar.k().ordinal()]) {
                case TwitterResponse.READ /*1*/:
                    brVar.f();
                    rect.left = brVar.r();
                    rect.top = brVar.r();
                    rect.right = brVar.r();
                    rect.bottom = brVar.r();
                    while (brVar.j()) {
                        brVar.s();
                    }
                    brVar.g();
                    break;
                case TwitterResponse.READ_WRITE /*2*/:
                    brVar.h();
                    while (brVar.j()) {
                        String l = brVar.l();
                        if ("left".equals(l)) {
                            rect.left = brVar.r();
                        } else if ("top".equals(l)) {
                            rect.top = brVar.r();
                        } else if ("right".equals(l)) {
                            rect.right = brVar.r();
                        } else if ("bottom".equals(l)) {
                            rect.bottom = brVar.r();
                        } else {
                            brVar.s();
                        }
                    }
                    brVar.i();
                    break;
                default:
                    throw new IllegalStateException("Unexpected token: " + brVar.k());
            }
            return rect;
        }
    };

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[bw.values().length];

        static {
            try {
                a[bw.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[bw.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }
}
