package com.tapjoy.internal;

public final class cr {
    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static Object b(Object obj, Object obj2) {
        return obj != null ? obj : cs.a(obj2);
    }
}
