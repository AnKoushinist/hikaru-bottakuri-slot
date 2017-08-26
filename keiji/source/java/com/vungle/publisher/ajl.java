package com.vungle.publisher;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: vungle */
enum ajl {
    ;
    
    static final akb a = null;

    static {
        a = new akb("RxScheduledExecutorPool-");
    }

    public static ScheduledExecutorService a() {
        aih a = alp.a();
        if (a == null) {
            return Executors.newScheduledThreadPool(1, a);
        }
        return (ScheduledExecutorService) a.call();
    }
}
