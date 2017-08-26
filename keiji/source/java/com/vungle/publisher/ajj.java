package com.vungle.publisher;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: vungle */
public final class ajj extends ahs implements ajq {
    static final int b;
    static final c c;
    static final b d = new b(null, 0);
    final ThreadFactory e;
    public final AtomicReference<b> f = new AtomicReference(d);

    /* compiled from: vungle */
    static final class a extends com.vungle.publisher.ahs.a {
        final akd a = new akd(this.b, this.c);
        private final akd b = new akd();
        private final amc c = new amc();
        private final c d;

        a(c cVar) {
            this.d = cVar;
        }

        public final void b() {
            this.a.b();
        }

        public final boolean c() {
            return this.a.b;
        }

        public final ahv a(final aie com_vungle_publisher_aie) {
            if (this.a.b) {
                return ame.a();
            }
            ajo com_vungle_publisher_ajo = this.d;
            aie anonymousClass1 = new aie(this) {
                final /* synthetic */ a b;

                public final void d() {
                    if (!this.b.a.b) {
                        com_vungle_publisher_aie.d();
                    }
                }
            };
            akd com_vungle_publisher_akd = this.b;
            Runnable com_vungle_publisher_ajp = new ajp(alp.a(anonymousClass1), com_vungle_publisher_akd);
            com_vungle_publisher_akd.a(com_vungle_publisher_ajp);
            com_vungle_publisher_ajp.a(0 <= 0 ? com_vungle_publisher_ajo.b.submit(com_vungle_publisher_ajp) : com_vungle_publisher_ajo.b.schedule(com_vungle_publisher_ajp, 0, null));
            return com_vungle_publisher_ajp;
        }

        public final ahv a(final aie com_vungle_publisher_aie, long j, TimeUnit timeUnit) {
            if (this.a.b) {
                return ame.a();
            }
            ajo com_vungle_publisher_ajo = this.d;
            aie anonymousClass2 = new aie(this) {
                final /* synthetic */ a b;

                public final void d() {
                    if (!this.b.a.b) {
                        com_vungle_publisher_aie.d();
                    }
                }
            };
            amc com_vungle_publisher_amc = this.c;
            Runnable com_vungle_publisher_ajp = new ajp(alp.a(anonymousClass2), com_vungle_publisher_amc);
            com_vungle_publisher_amc.a(com_vungle_publisher_ajp);
            com_vungle_publisher_ajp.a(j <= 0 ? com_vungle_publisher_ajo.b.submit(com_vungle_publisher_ajp) : com_vungle_publisher_ajo.b.schedule(com_vungle_publisher_ajp, j, timeUnit));
            return com_vungle_publisher_ajp;
        }
    }

    /* compiled from: vungle */
    public static final class b {
        final int a;
        final c[] b;
        long c;

        b(ThreadFactory threadFactory, int i) {
            this.a = i;
            this.b = new c[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.b[i2] = new c(threadFactory);
            }
        }

        public final c a() {
            int i = this.a;
            if (i == 0) {
                return ajj.c;
            }
            c[] cVarArr = this.b;
            long j = this.c;
            this.c = 1 + j;
            return cVarArr[(int) (j % ((long) i))];
        }

        public final void b() {
            for (c b : this.b) {
                b.b();
            }
        }
    }

    /* compiled from: vungle */
    public static final class c extends ajo {
        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        int intValue = Integer.getInteger("rx.scheduler.max-computation-threads", 0).intValue();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (intValue <= 0 || intValue > availableProcessors) {
            intValue = availableProcessors;
        }
        b = intValue;
        c cVar = new c(akb.a);
        c = cVar;
        cVar.b();
    }

    public ajj(ThreadFactory threadFactory) {
        this.e = threadFactory;
        b bVar = new b(this.e, b);
        if (!this.f.compareAndSet(d, bVar)) {
            bVar.b();
        }
    }

    public final com.vungle.publisher.ahs.a a() {
        return new a(((b) this.f.get()).a());
    }

    public final void c() {
        b bVar;
        do {
            bVar = (b) this.f.get();
            if (bVar == d) {
                return;
            }
        } while (!this.f.compareAndSet(bVar, d));
        bVar.b();
    }
}
