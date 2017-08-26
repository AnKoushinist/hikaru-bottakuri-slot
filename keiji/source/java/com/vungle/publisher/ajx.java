package com.vungle.publisher;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: vungle */
public abstract class ajx<T> implements ajq {
    Queue<T> a;
    final int b;
    final int c;
    private final long d;
    private final AtomicReference<Future<?>> e;

    protected abstract T b();

    public ajx() {
        this((byte) 0);
    }

    private ajx(byte b) {
        this.b = 0;
        this.c = 0;
        this.d = 67;
        this.e = new AtomicReference();
        if (ali.a()) {
            this.a = new akn(Math.max(this.c, 1024));
        } else {
            this.a = new ConcurrentLinkedQueue();
        }
        d();
    }

    public final T a() {
        T poll = this.a.poll();
        if (poll == null) {
            return b();
        }
        return poll;
    }

    public final void c() {
        Future future = (Future) this.e.getAndSet(null);
        if (future != null) {
            future.cancel(false);
        }
    }

    private void d() {
        while (this.e.get() == null) {
            try {
                Future scheduleAtFixedRate = ajk.a().scheduleAtFixedRate(new Runnable(this) {
                    final /* synthetic */ ajx a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        int i = 0;
                        int size = this.a.a.size();
                        if (size < this.a.b) {
                            size = this.a.c - size;
                            while (i < size) {
                                this.a.a.add(this.a.b());
                                i++;
                            }
                        } else if (size > this.a.c) {
                            size -= this.a.c;
                            while (i < size) {
                                this.a.a.poll();
                                i++;
                            }
                        }
                    }
                }, this.d, this.d, TimeUnit.SECONDS);
                if (!this.e.compareAndSet(null, scheduleAtFixedRate)) {
                    scheduleAtFixedRate.cancel(false);
                } else {
                    return;
                }
            } catch (Throwable e) {
                alp.a(e);
                return;
            }
        }
    }
}
