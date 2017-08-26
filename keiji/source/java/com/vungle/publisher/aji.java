package com.vungle.publisher;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: vungle */
public final class aji extends ahs implements ajq {
    static final c b;
    static final a c;
    private static final TimeUnit f = TimeUnit.SECONDS;
    final ThreadFactory d;
    final AtomicReference<a> e = new AtomicReference(c);

    /* compiled from: vungle */
    static final class a {
        final long a;
        final ConcurrentLinkedQueue<c> b;
        final amc c;
        private final ThreadFactory d;
        private final ScheduledExecutorService e;
        private final Future<?> f;

        a(final ThreadFactory threadFactory, long j, TimeUnit timeUnit) {
            Future scheduleWithFixedDelay;
            ScheduledExecutorService scheduledExecutorService = null;
            this.d = threadFactory;
            this.a = timeUnit != null ? timeUnit.toNanos(j) : 0;
            this.b = new ConcurrentLinkedQueue();
            this.c = new amc();
            if (timeUnit != null) {
                ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new ThreadFactory(this) {
                    final /* synthetic */ a b;

                    public final Thread newThread(Runnable runnable) {
                        Thread newThread = threadFactory.newThread(runnable);
                        newThread.setName(newThread.getName() + " (Evictor)");
                        return newThread;
                    }
                });
                ajo.b(newScheduledThreadPool);
                scheduledExecutorService = newScheduledThreadPool;
                scheduleWithFixedDelay = newScheduledThreadPool.scheduleWithFixedDelay(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        a aVar = this.a;
                        if (!aVar.b.isEmpty()) {
                            long nanoTime = System.nanoTime();
                            Iterator it = aVar.b.iterator();
                            while (it.hasNext()) {
                                c cVar = (c) it.next();
                                if (cVar.a > nanoTime) {
                                    return;
                                }
                                if (aVar.b.remove(cVar)) {
                                    aVar.c.b(cVar);
                                }
                            }
                        }
                    }
                }, this.a, this.a, TimeUnit.NANOSECONDS);
            } else {
                scheduleWithFixedDelay = null;
            }
            this.e = scheduledExecutorService;
            this.f = scheduleWithFixedDelay;
        }

        final c a() {
            if (this.c.a) {
                return aji.b;
            }
            c cVar;
            while (!this.b.isEmpty()) {
                cVar = (c) this.b.poll();
                if (cVar != null) {
                    return cVar;
                }
            }
            cVar = new c(this.d);
            this.c.a(cVar);
            return cVar;
        }

        final void b() {
            try {
                if (this.f != null) {
                    this.f.cancel(true);
                }
                if (this.e != null) {
                    this.e.shutdownNow();
                }
                this.c.b();
            } catch (Throwable th) {
                this.c.b();
            }
        }
    }

    /* compiled from: vungle */
    static final class b extends com.vungle.publisher.ahs.a implements aie {
        final amc a = new amc();
        final AtomicBoolean b;
        private final a c;
        private final c d;

        b(a aVar) {
            this.c = aVar;
            this.b = new AtomicBoolean();
            this.d = aVar.a();
        }

        public final void b() {
            if (this.b.compareAndSet(false, true)) {
                this.d.a(this, 0, null);
            }
            this.a.b();
        }

        public final void d() {
            a aVar = this.c;
            c cVar = this.d;
            cVar.a = System.nanoTime() + aVar.a;
            aVar.b.offer(cVar);
        }

        public final boolean c() {
            return this.a.a;
        }

        public final ahv a(aie com_vungle_publisher_aie) {
            return a(com_vungle_publisher_aie, 0, null);
        }

        public final ahv a(final aie com_vungle_publisher_aie, long j, TimeUnit timeUnit) {
            if (this.a.a) {
                return ame.a();
            }
            ahv b = this.d.b(new aie(this) {
                final /* synthetic */ b b;

                public final void d() {
                    if (!this.b.a.a) {
                        com_vungle_publisher_aie.d();
                    }
                }
            }, j, timeUnit);
            this.a.a(b);
            b.a.a(new b(b, this.a));
            return b;
        }
    }

    /* compiled from: vungle */
    static final class c extends ajo {
        long a = 0;

        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        c cVar = new c(akb.a);
        b = cVar;
        cVar.b();
        a aVar = new a(null, 0, null);
        c = aVar;
        aVar.b();
    }

    public aji(ThreadFactory threadFactory) {
        this.d = threadFactory;
        a aVar = new a(this.d, 60, f);
        if (!this.e.compareAndSet(c, aVar)) {
            aVar.b();
        }
    }

    public final void c() {
        a aVar;
        do {
            aVar = (a) this.e.get();
            if (aVar == c) {
                return;
            }
        } while (!this.e.compareAndSet(aVar, c));
        aVar.b();
    }

    public final com.vungle.publisher.ahs.a a() {
        return new b((a) this.e.get());
    }
}
