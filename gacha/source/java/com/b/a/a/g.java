package com.b.a.a;

import b.a.a.a.c;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;

/* compiled from: BackgroundManager */
class g {
    final AtomicReference<ScheduledFuture<?>> a = new AtomicReference();
    boolean b = true;
    private final ScheduledExecutorService c;
    private final List<a> d = new ArrayList();
    private volatile boolean e = true;

    /* compiled from: BackgroundManager */
    public interface a {
        void a();
    }

    public g(ScheduledExecutorService scheduledExecutorService) {
        this.c = scheduledExecutorService;
    }

    public void a(boolean z) {
        this.e = z;
    }

    private void c() {
        for (a a : this.d) {
            a.a();
        }
    }

    public void a(a aVar) {
        this.d.add(aVar);
    }

    public void a() {
        this.b = false;
        ScheduledFuture scheduledFuture = (ScheduledFuture) this.a.getAndSet(null);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    public void b() {
        if (this.e && !this.b) {
            this.b = true;
            try {
                this.a.compareAndSet(null, this.c.schedule(new Runnable(this) {
                    final /* synthetic */ g a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.set(null);
                        this.a.c();
                    }
                }, RFLConstants.GPS_TIME, TimeUnit.MILLISECONDS));
            } catch (Throwable e) {
                c.h().a("Answers", "Failed to schedule background detector", e);
            }
        }
    }
}
