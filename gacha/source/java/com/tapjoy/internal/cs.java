package com.tapjoy.internal;

public final class cs {
    private static void a(Throwable th, Class cls) {
        if (th != null && cls.isInstance(th)) {
            throw ((Throwable) cls.cast(th));
        }
    }

    public static RuntimeException a(Throwable th) {
        Throwable th2 = (Throwable) cq.a((Object) th);
        a(th2, Error.class);
        a(th2, RuntimeException.class);
        throw new RuntimeException(th);
    }
}
