package com.vungle.publisher;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: vungle */
public final class ajp extends AtomicReference<Thread> implements ahv, Runnable {
    final akd a;
    final aie b;

    /* compiled from: vungle */
    final class a implements ahv {
        final /* synthetic */ ajp a;
        private final Future<?> b;

        a(ajp com_vungle_publisher_ajp, Future<?> future) {
            this.a = com_vungle_publisher_ajp;
            this.b = future;
        }

        public final void b() {
            if (this.a.get() != Thread.currentThread()) {
                this.b.cancel(true);
            } else {
                this.b.cancel(false);
            }
        }

        public final boolean c() {
            return this.b.isCancelled();
        }
    }

    /* compiled from: vungle */
    static final class b extends AtomicBoolean implements ahv {
        final ajp a;
        final amc b;

        public b(ajp com_vungle_publisher_ajp, amc com_vungle_publisher_amc) {
            this.a = com_vungle_publisher_ajp;
            this.b = com_vungle_publisher_amc;
        }

        public final boolean c() {
            return this.a.a.b;
        }

        public final void b() {
            if (compareAndSet(false, true)) {
                this.b.b(this.a);
            }
        }
    }

    /* compiled from: vungle */
    static final class c extends AtomicBoolean implements ahv {
        final ajp a;
        final akd b;

        public c(ajp com_vungle_publisher_ajp, akd com_vungle_publisher_akd) {
            this.a = com_vungle_publisher_ajp;
            this.b = com_vungle_publisher_akd;
        }

        public final boolean c() {
            return this.a.a.b;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void b() {
            /*
            r4 = this;
            r0 = 0;
            r1 = 1;
            r0 = r4.compareAndSet(r0, r1);
            if (r0 == 0) goto L_0x001a;
        L_0x0008:
            r1 = r4.b;
            r0 = r4.a;
            r2 = r1.b;
            if (r2 != 0) goto L_0x001a;
        L_0x0010:
            monitor-enter(r1);
            r2 = r1.a;	 Catch:{ all -> 0x0026 }
            r3 = r1.b;	 Catch:{ all -> 0x0026 }
            if (r3 != 0) goto L_0x0019;
        L_0x0017:
            if (r2 != 0) goto L_0x001b;
        L_0x0019:
            monitor-exit(r1);	 Catch:{ all -> 0x0026 }
        L_0x001a:
            return;
        L_0x001b:
            r2 = r2.remove(r0);	 Catch:{ all -> 0x0026 }
            monitor-exit(r1);	 Catch:{ all -> 0x0026 }
            if (r2 == 0) goto L_0x001a;
        L_0x0022:
            r0.b();
            goto L_0x001a;
        L_0x0026:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0026 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.ajp.c.b():void");
        }
    }

    public ajp(aie com_vungle_publisher_aie) {
        this.b = com_vungle_publisher_aie;
        this.a = new akd();
    }

    public ajp(aie com_vungle_publisher_aie, amc com_vungle_publisher_amc) {
        this.b = com_vungle_publisher_aie;
        this.a = new akd(new b(this, com_vungle_publisher_amc));
    }

    public ajp(aie com_vungle_publisher_aie, akd com_vungle_publisher_akd) {
        this.b = com_vungle_publisher_aie;
        this.a = new akd(new c(this, com_vungle_publisher_akd));
    }

    public final void run() {
        try {
            lazySet(Thread.currentThread());
            this.b.d();
        } catch (Throwable e) {
            a(new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", e));
        } catch (Throwable e2) {
            a(new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", e2));
        } finally {
            b();
        }
    }

    private static void a(Throwable th) {
        alp.a(th);
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    public final boolean c() {
        return this.a.b;
    }

    public final void b() {
        if (!this.a.b) {
            this.a.b();
        }
    }

    public final void a(Future<?> future) {
        this.a.a(new a(this, future));
    }
}
