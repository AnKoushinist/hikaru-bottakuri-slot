package com.vungle.publisher;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: vungle */
public final class amb implements ahv {
    static final aie b = new aie() {
        public final void d() {
        }
    };
    final AtomicReference<aie> a;

    public amb() {
        this.a = new AtomicReference();
    }

    private amb(aie com_vungle_publisher_aie) {
        this.a = new AtomicReference(com_vungle_publisher_aie);
    }

    public static amb a(aie com_vungle_publisher_aie) {
        return new amb(com_vungle_publisher_aie);
    }

    public final boolean c() {
        return this.a.get() == b;
    }

    public final void b() {
        if (((aie) this.a.get()) != b) {
            aie com_vungle_publisher_aie = (aie) this.a.getAndSet(b);
            if (com_vungle_publisher_aie != null && com_vungle_publisher_aie != b) {
                com_vungle_publisher_aie.d();
            }
        }
    }
}
