package com.d.a.a;

/* compiled from: Utils */
class p {
    public static void a(boolean z, String str) {
        if (!z) {
            throw new AssertionError(str);
        }
    }

    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(str + " should not be null!");
    }
}
