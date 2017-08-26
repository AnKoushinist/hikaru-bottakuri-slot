package com.vungle.publisher;

import com.vungle.log.Logger;

/* compiled from: vungle */
public abstract class abf<T> {
    public abstract T b();

    public final String d() {
        Object b = b();
        return b == null ? null : b.toString();
    }

    protected static void a(String str, Object obj) {
        if (obj == null) {
            Logger.d(Logger.PROTOCOL_TAG, "null " + str + " is required output");
        }
    }
}
