package com.tapjoy.internal;

import android.os.Handler;
import android.os.Looper;

public final class x {
    private static Handler a;

    public static synchronized Handler a() {
        Handler handler;
        synchronized (x.class) {
            if (a == null) {
                a = new Handler(Looper.getMainLooper());
            }
            handler = a;
        }
        return handler;
    }

    public static bf a(final Handler handler) {
        return new bf() {
            public final boolean a(Runnable runnable) {
                return handler.post(runnable);
            }
        };
    }
}
