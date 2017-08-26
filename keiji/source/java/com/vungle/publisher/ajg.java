package com.vungle.publisher;

import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: vungle */
public final class ajg<T> extends AtomicInteger implements ahr {
    final ahu<? super T> a;
    T b;

    public ajg(ahu<? super T> com_vungle_publisher_ahu__super_T) {
        this.a = com_vungle_publisher_ahu__super_T;
    }

    public final void a(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        } else if (j != 0) {
            int i;
            while (true) {
                i = get();
                if (i != 0) {
                    break;
                } else if (compareAndSet(0, 2)) {
                    return;
                }
            }
            if (i == 1 && compareAndSet(1, 3)) {
                a(this.a, this.b);
            }
        }
    }

    public final void a(T t) {
        do {
            int i = get();
            if (i == 0) {
                this.b = t;
            } else if (i == 2 && compareAndSet(2, 3)) {
                a(this.a, t);
                return;
            } else {
                return;
            }
        } while (!compareAndSet(0, 1));
    }

    private static <T> void a(ahu<? super T> com_vungle_publisher_ahu__super_T, T t) {
        if (!com_vungle_publisher_ahu__super_T.a.b) {
            try {
                com_vungle_publisher_ahu__super_T.a((Object) t);
                if (!com_vungle_publisher_ahu__super_T.a.b) {
                    com_vungle_publisher_ahu__super_T.a();
                }
            } catch (Throwable th) {
                ahx.a(th, com_vungle_publisher_ahu__super_T, t);
            }
        }
    }
}
