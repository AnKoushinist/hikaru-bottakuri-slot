package com.b.a.c;

import android.os.Looper;
import b.a.a.a.c;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* compiled from: CrashlyticsExecutorServiceWrapper */
class g {
    private final ExecutorService a;

    public g(ExecutorService executorService) {
        this.a = executorService;
    }

    <T> T a(Callable<T> callable) {
        try {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                return this.a.submit(callable).get(4, TimeUnit.SECONDS);
            }
            return this.a.submit(callable).get();
        } catch (RejectedExecutionException e) {
            c.h().a("CrashlyticsCore", "Executor is shut down because we're handling a fatal crash.");
            return null;
        } catch (Throwable e2) {
            c.h().e("CrashlyticsCore", "Failed to execute task.", e2);
            return null;
        }
    }

    Future<?> a(final Runnable runnable) {
        try {
            return this.a.submit(new Runnable(this) {
                final /* synthetic */ g b;

                public void run() {
                    try {
                        runnable.run();
                    } catch (Throwable e) {
                        c.h().e("CrashlyticsCore", "Failed to execute task.", e);
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            c.h().a("CrashlyticsCore", "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }

    <T> Future<T> b(final Callable<T> callable) {
        try {
            return this.a.submit(new Callable<T>(this) {
                final /* synthetic */ g b;

                public T call() {
                    try {
                        return callable.call();
                    } catch (Throwable e) {
                        c.h().e("CrashlyticsCore", "Failed to execute task.", e);
                        return null;
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            c.h().a("CrashlyticsCore", "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }
}
