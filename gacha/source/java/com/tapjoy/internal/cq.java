package com.tapjoy.internal;

public final class cq {
    public static void a(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static Object a(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException();
    }
}
