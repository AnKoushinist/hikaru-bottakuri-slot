package com.tapjoy.internal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

public abstract class dd implements dg {
    private final ReentrantLock a = new ReentrantLock();
    private final a b = new a();
    private final a c = new a();
    private com.tapjoy.internal.dg.a d = com.tapjoy.internal.dg.a.NEW;
    private boolean e = false;

    class a extends dc {
        final /* synthetic */ dd a;

        private a(dd ddVar) {
            this.a = ddVar;
        }

        public final /* synthetic */ Object get(long j, TimeUnit timeUnit) {
            return a(j, timeUnit);
        }

        private com.tapjoy.internal.dg.a a(long j, TimeUnit timeUnit) {
            try {
                return (com.tapjoy.internal.dg.a) super.get(j, timeUnit);
            } catch (TimeoutException e) {
                throw new TimeoutException(this.a.toString());
            }
        }
    }

    protected abstract void a();

    protected abstract void b();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.tapjoy.internal.df e() {
        /*
        r2 = this;
        r0 = r2.a;
        r0.lock();
        r0 = r2.d;	 Catch:{ Throwable -> 0x001a }
        r1 = com.tapjoy.internal.dg.a.NEW;	 Catch:{ Throwable -> 0x001a }
        if (r0 != r1) goto L_0x0012;
    L_0x000b:
        r0 = com.tapjoy.internal.dg.a.STARTING;	 Catch:{ Throwable -> 0x001a }
        r2.d = r0;	 Catch:{ Throwable -> 0x001a }
        r2.a();	 Catch:{ Throwable -> 0x001a }
    L_0x0012:
        r0 = r2.a;
        r0.unlock();
    L_0x0017:
        r0 = r2.b;
        return r0;
    L_0x001a:
        r0 = move-exception;
        r2.a(r0);	 Catch:{ all -> 0x0024 }
        r0 = r2.a;
        r0.unlock();
        goto L_0x0017;
    L_0x0024:
        r0 = move-exception;
        r1 = r2.a;
        r1.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.dd.e():com.tapjoy.internal.df");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.tapjoy.internal.df g() {
        /*
        r2 = this;
        r0 = r2.a;
        r0.lock();
        r0 = r2.d;	 Catch:{ Throwable -> 0x0036 }
        r1 = com.tapjoy.internal.dg.a.NEW;	 Catch:{ Throwable -> 0x0036 }
        if (r0 != r1) goto L_0x0025;
    L_0x000b:
        r0 = com.tapjoy.internal.dg.a.TERMINATED;	 Catch:{ Throwable -> 0x0036 }
        r2.d = r0;	 Catch:{ Throwable -> 0x0036 }
        r0 = r2.b;	 Catch:{ Throwable -> 0x0036 }
        r1 = com.tapjoy.internal.dg.a.TERMINATED;	 Catch:{ Throwable -> 0x0036 }
        r0.a(r1);	 Catch:{ Throwable -> 0x0036 }
        r0 = r2.c;	 Catch:{ Throwable -> 0x0036 }
        r1 = com.tapjoy.internal.dg.a.TERMINATED;	 Catch:{ Throwable -> 0x0036 }
        r0.a(r1);	 Catch:{ Throwable -> 0x0036 }
    L_0x001d:
        r0 = r2.a;
        r0.unlock();
    L_0x0022:
        r0 = r2.c;
        return r0;
    L_0x0025:
        r0 = r2.d;	 Catch:{ Throwable -> 0x0036 }
        r1 = com.tapjoy.internal.dg.a.STARTING;	 Catch:{ Throwable -> 0x0036 }
        if (r0 != r1) goto L_0x0040;
    L_0x002b:
        r0 = 1;
        r2.e = r0;	 Catch:{ Throwable -> 0x0036 }
        r0 = r2.b;	 Catch:{ Throwable -> 0x0036 }
        r1 = com.tapjoy.internal.dg.a.STOPPING;	 Catch:{ Throwable -> 0x0036 }
        r0.a(r1);	 Catch:{ Throwable -> 0x0036 }
        goto L_0x001d;
    L_0x0036:
        r0 = move-exception;
        r2.a(r0);	 Catch:{ all -> 0x004e }
        r0 = r2.a;
        r0.unlock();
        goto L_0x0022;
    L_0x0040:
        r0 = r2.d;	 Catch:{ Throwable -> 0x0036 }
        r1 = com.tapjoy.internal.dg.a.RUNNING;	 Catch:{ Throwable -> 0x0036 }
        if (r0 != r1) goto L_0x001d;
    L_0x0046:
        r0 = com.tapjoy.internal.dg.a.STOPPING;	 Catch:{ Throwable -> 0x0036 }
        r2.d = r0;	 Catch:{ Throwable -> 0x0036 }
        r2.b();	 Catch:{ Throwable -> 0x0036 }
        goto L_0x001d;
    L_0x004e:
        r0 = move-exception;
        r1 = r2.a;
        r1.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.dd.g():com.tapjoy.internal.df");
    }

    protected final void c() {
        this.a.lock();
        try {
            if (this.d != com.tapjoy.internal.dg.a.STARTING) {
                Throwable illegalStateException = new IllegalStateException("Cannot notifyStarted() when the service is " + this.d);
                a(illegalStateException);
                throw illegalStateException;
            }
            this.d = com.tapjoy.internal.dg.a.RUNNING;
            if (this.e) {
                g();
            } else {
                this.b.a((Object) com.tapjoy.internal.dg.a.RUNNING);
            }
            this.a.unlock();
        } catch (Throwable th) {
            this.a.unlock();
        }
    }

    protected final void d() {
        this.a.lock();
        try {
            if (this.d == com.tapjoy.internal.dg.a.STOPPING || this.d == com.tapjoy.internal.dg.a.RUNNING) {
                this.d = com.tapjoy.internal.dg.a.TERMINATED;
                this.c.a((Object) com.tapjoy.internal.dg.a.TERMINATED);
                return;
            }
            Throwable illegalStateException = new IllegalStateException("Cannot notifyStopped() when the service is " + this.d);
            a(illegalStateException);
            throw illegalStateException;
        } finally {
            this.a.unlock();
        }
    }

    protected final void a(Throwable th) {
        cq.a((Object) th);
        this.a.lock();
        try {
            if (this.d == com.tapjoy.internal.dg.a.STARTING) {
                this.b.a(th);
                this.c.a(new Exception("Service failed to start.", th));
            } else if (this.d == com.tapjoy.internal.dg.a.STOPPING) {
                this.c.a(th);
            } else if (this.d == com.tapjoy.internal.dg.a.RUNNING) {
                this.c.a(new Exception("Service failed while running", th));
            } else if (this.d == com.tapjoy.internal.dg.a.NEW || this.d == com.tapjoy.internal.dg.a.TERMINATED) {
                throw new IllegalStateException("Failed while in state:" + this.d, th);
            }
            this.d = com.tapjoy.internal.dg.a.FAILED;
        } finally {
            this.a.unlock();
        }
    }

    public final com.tapjoy.internal.dg.a f() {
        this.a.lock();
        try {
            com.tapjoy.internal.dg.a aVar;
            if (this.e && this.d == com.tapjoy.internal.dg.a.STARTING) {
                aVar = com.tapjoy.internal.dg.a.STOPPING;
                return aVar;
            }
            aVar = this.d;
            this.a.unlock();
            return aVar;
        } finally {
            this.a.unlock();
        }
    }

    public String toString() {
        return getClass().getSimpleName() + " [" + f() + "]";
    }
}
