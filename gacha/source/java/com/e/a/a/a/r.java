package com.e.a.a.a;

import java.util.concurrent.ThreadFactory;

class r implements ThreadFactory {
    final /* synthetic */ q a;

    r(q qVar) {
        this.a = qVar;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "MoatStatus");
        thread.setDaemon(true);
        return thread;
    }
}
