package com.tapjoy.internal;

import android.view.animation.Animation;

public class ai {
    protected final Animation a;

    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] a = new int[a.a().length];

        static {
            try {
                a[a.a - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.b - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.c - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[a.d - 1] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public enum a {
        ;

        public static int[] a() {
            return (int[]) e.clone();
        }

        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = new int[]{a, b, c, d};
        }
    }

    public ai(Animation animation) {
        this.a = animation;
        animation.setDuration(400);
    }

    public Animation a() {
        return this.a;
    }

    public final ai b() {
        this.a.setDuration(600);
        return this;
    }
}
