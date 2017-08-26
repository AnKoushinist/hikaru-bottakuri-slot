package com.tapjoy.internal;

import java.lang.ref.WeakReference;

public final class cc {
    public WeakReference a;

    public final Object a() {
        WeakReference weakReference = this.a;
        return weakReference != null ? weakReference.get() : null;
    }

    public final void a(Object obj) {
        this.a = new WeakReference(obj);
    }
}
