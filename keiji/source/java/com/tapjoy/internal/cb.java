package com.tapjoy.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public final class cb extends WeakReference {
    public final Object a;

    public cb(Object obj, Object obj2, ReferenceQueue referenceQueue) {
        super(obj2, referenceQueue);
        this.a = obj;
    }
}
