package com.vungle.publisher;

import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: vungle */
public final class ajh<T> extends AtomicBoolean implements ahr {
    final ahu<? super T> a;
    final T b;

    public ajh(ahu<? super T> com_vungle_publisher_ahu__super_T, T t) {
        this.a = com_vungle_publisher_ahu__super_T;
        this.b = t;
    }

    public final void a(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        } else if (j != 0 && compareAndSet(false, true)) {
            ahq com_vungle_publisher_ahq = this.a;
            if (!com_vungle_publisher_ahq.a.b) {
                Object obj = this.b;
                try {
                    com_vungle_publisher_ahq.a(obj);
                    if (!com_vungle_publisher_ahq.a.b) {
                        com_vungle_publisher_ahq.a();
                    }
                } catch (Throwable th) {
                    ahx.a(th, com_vungle_publisher_ahq, obj);
                }
            }
        }
    }
}
