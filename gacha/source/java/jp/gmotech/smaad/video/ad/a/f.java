package jp.gmotech.smaad.video.ad.a;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class f extends ThreadPoolExecutor {
    final /* synthetic */ d a;

    f(d dVar) {
        this.a = dVar;
        super(2, 6, 2, TimeUnit.SECONDS, new SynchronousQueue(), new g(dVar));
    }
}
