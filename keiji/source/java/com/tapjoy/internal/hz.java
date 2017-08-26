package com.tapjoy.internal;

final class hz {
    static hy a;
    static long b;

    private hz() {
    }

    static hy a() {
        synchronized (hz.class) {
            if (a != null) {
                hy hyVar = a;
                a = hyVar.f;
                hyVar.f = null;
                b -= 8192;
                return hyVar;
            }
            return new hy();
        }
    }

    static void a(hy hyVar) {
        if (hyVar.f != null || hyVar.g != null) {
            throw new IllegalArgumentException();
        } else if (!hyVar.d) {
            synchronized (hz.class) {
                if (b + 8192 > 65536) {
                    return;
                }
                b += 8192;
                hyVar.f = a;
                hyVar.c = 0;
                hyVar.b = 0;
                a = hyVar;
            }
        }
    }
}
