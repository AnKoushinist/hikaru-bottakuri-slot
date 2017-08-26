package a.a.a.a.a.c;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: PriorityThreadPoolExecutor */
public class k extends ThreadPoolExecutor {
    private static final int a = Runtime.getRuntime().availableProcessors();
    private static final int b = (a + 1);
    private static final int c = ((a * 2) + 1);

    /* compiled from: PriorityThreadPoolExecutor */
    protected static final class a implements ThreadFactory {
        private final int a;

        public a(int i) {
            this.a = i;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(this.a);
            thread.setName("Queue");
            return thread;
        }
    }

    public /* synthetic */ BlockingQueue getQueue() {
        return b();
    }

    <T extends Runnable & b & l & i> k(int i, int i2, long j, TimeUnit timeUnit, c<T> cVar, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, cVar, threadFactory);
        prestartAllCoreThreads();
    }

    public static <T extends Runnable & b & l & i> k a(int i, int i2) {
        return new k(i, i2, 1, TimeUnit.SECONDS, new c(), new a(10));
    }

    public static k a() {
        return a(b, c);
    }

    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new h(runnable, t);
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new h(callable);
    }

    public void execute(Runnable runnable) {
        if (j.a((Object) runnable)) {
            super.execute(runnable);
        } else {
            super.execute(newTaskFor(runnable, null));
        }
    }

    protected void afterExecute(Runnable runnable, Throwable th) {
        l lVar = (l) runnable;
        lVar.b(true);
        lVar.a(th);
        b().d();
        super.afterExecute(runnable, th);
    }

    public c b() {
        return (c) super.getQueue();
    }
}
