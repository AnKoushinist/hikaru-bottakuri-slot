package com.vungle.publisher;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: vungle */
public final class ajk implements ajq {
    public static final ajk a = new ajk();
    private static final ScheduledExecutorService[] b = new ScheduledExecutorService[0];
    private static final ScheduledExecutorService c;
    private static int e;
    private final AtomicReference<ScheduledExecutorService[]> d = new AtomicReference(b);

    static {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(0);
        c = newScheduledThreadPool;
        newScheduledThreadPool.shutdown();
    }

    private ajk() {
        b();
    }

    private void b() {
        int i = 8;
        int i2 = 0;
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (availableProcessors > 4) {
            availableProcessors /= 2;
        }
        if (availableProcessors <= 8) {
            i = availableProcessors;
        }
        Object obj = new ScheduledExecutorService[i];
        for (availableProcessors = 0; availableProcessors < i; availableProcessors++) {
            obj[availableProcessors] = ajl.a();
        }
        if (this.d.compareAndSet(b, obj)) {
            availableProcessors = obj.length;
            while (i2 < availableProcessors) {
                ScheduledExecutorService scheduledExecutorService = obj[i2];
                if (!ajo.b(scheduledExecutorService) && (scheduledExecutorService instanceof ScheduledThreadPoolExecutor)) {
                    ajo.a((ScheduledThreadPoolExecutor) scheduledExecutorService);
                }
                i2++;
            }
            return;
        }
        for (ScheduledExecutorService shutdownNow : obj) {
            shutdownNow.shutdownNow();
        }
    }

    public final void c() {
        ScheduledExecutorService[] scheduledExecutorServiceArr;
        do {
            scheduledExecutorServiceArr = (ScheduledExecutorService[]) this.d.get();
            if (scheduledExecutorServiceArr == b) {
                return;
            }
        } while (!this.d.compareAndSet(scheduledExecutorServiceArr, b));
        for (ScheduledExecutorService scheduledExecutorService : scheduledExecutorServiceArr) {
            ajo.a(scheduledExecutorService);
            scheduledExecutorService.shutdownNow();
        }
    }

    public static ScheduledExecutorService a() {
        ScheduledExecutorService[] scheduledExecutorServiceArr = (ScheduledExecutorService[]) a.d.get();
        if (scheduledExecutorServiceArr == b) {
            return c;
        }
        int i = e + 1;
        if (i >= scheduledExecutorServiceArr.length) {
            i = 0;
        }
        e = i;
        return scheduledExecutorServiceArr[i];
    }
}
