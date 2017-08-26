package jp.gmotech.smaad.video.ad.a;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import jp.gmotech.smaad.video.ad.b.a;

class g implements RejectedExecutionHandler {
    final /* synthetic */ d a;

    g(d dVar) {
        this.a = dVar;
    }

    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        a.c("RequestHandler", "[rejectedExecution] Runnable : " + runnable.toString());
        a.c("RequestHandler", "[rejectedExecution] activeCount : " + threadPoolExecutor.getActiveCount());
        a.c("RequestHandler", "[rejectedExecution] corepoolsize : " + threadPoolExecutor.getCorePoolSize());
        a.c("RequestHandler", "[rejectedExecution] largestpoolsize : " + threadPoolExecutor.getLargestPoolSize());
        a.c("RequestHandler", "[rejectedExecution] maxpoolsize : " + threadPoolExecutor.getMaximumPoolSize());
        a.c("RequestHandler", "[rejectedExecution] poolsize : " + threadPoolExecutor.getPoolSize());
        a.c("RequestHandler", "[rejectedExecution] taskCount : " + threadPoolExecutor.getTaskCount());
    }
}
