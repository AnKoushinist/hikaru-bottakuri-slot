package com.vungle.publisher;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: vungle */
public final class ajs extends ahs {
    public static final ajs b = new ajs();

    /* compiled from: vungle */
    static final class a extends com.vungle.publisher.ahs.a implements ahv {
        final AtomicInteger a = new AtomicInteger();
        final PriorityBlockingQueue<b> b = new PriorityBlockingQueue();
        private final amb c = new amb();
        private final AtomicInteger d = new AtomicInteger();

        a() {
        }

        public final ahv a(aie com_vungle_publisher_aie) {
            return a(com_vungle_publisher_aie, System.currentTimeMillis());
        }

        public final ahv a(aie com_vungle_publisher_aie, long j, TimeUnit timeUnit) {
            long currentTimeMillis = System.currentTimeMillis() + timeUnit.toMillis(j);
            return a(new ajr(com_vungle_publisher_aie, this, currentTimeMillis), currentTimeMillis);
        }

        private ahv a(aie com_vungle_publisher_aie, long j) {
            if (this.c.c()) {
                return ame.a();
            }
            final b bVar = new b(com_vungle_publisher_aie, Long.valueOf(j), this.a.incrementAndGet());
            this.b.add(bVar);
            if (this.d.getAndIncrement() != 0) {
                return ame.a(new aie(this) {
                    final /* synthetic */ a b;

                    public final void d() {
                        this.b.b.remove(bVar);
                    }
                });
            }
            do {
                bVar = (b) this.b.poll();
                if (bVar != null) {
                    bVar.a.d();
                }
            } while (this.d.decrementAndGet() > 0);
            return ame.a();
        }

        public final void b() {
            this.c.b();
        }

        public final boolean c() {
            return this.c.c();
        }
    }

    /* compiled from: vungle */
    static final class b implements Comparable<b> {
        final aie a;
        final Long b;
        final int c;

        public final /* synthetic */ int compareTo(Object obj) {
            b bVar = (b) obj;
            int compareTo = this.b.compareTo(bVar.b);
            return compareTo == 0 ? ajs.a(this.c, bVar.c) : compareTo;
        }

        b(aie com_vungle_publisher_aie, Long l, int i) {
            this.a = com_vungle_publisher_aie;
            this.b = l;
            this.c = i;
        }
    }

    public final com.vungle.publisher.ahs.a a() {
        return new a();
    }

    private ajs() {
    }

    static int a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }
}
