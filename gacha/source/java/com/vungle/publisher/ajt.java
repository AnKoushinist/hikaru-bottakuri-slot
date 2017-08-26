package com.vungle.publisher;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: vungle */
public final class ajt extends AtomicReference<ahv> implements ahv {
    public final void b() {
        if (((ahv) get()) != aju.a) {
            ahv com_vungle_publisher_ahv = (ahv) getAndSet(aju.a);
            if (com_vungle_publisher_ahv != null && com_vungle_publisher_ahv != aju.a) {
                com_vungle_publisher_ahv.b();
            }
        }
    }

    public final boolean c() {
        return get() == aju.a;
    }
}
