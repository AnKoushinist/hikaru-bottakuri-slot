package com.tapjoy.internal;

final class dm {
    static void a(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }
}
