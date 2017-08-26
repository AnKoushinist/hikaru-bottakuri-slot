package com.e.a.a.a;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import twitter4j.HttpResponseCode;

class q implements n {
    private static final AtomicReference a = new AtomicReference();
    private static final Queue b = new ConcurrentLinkedQueue();
    private static volatile p c = p.OFF;
    private static volatile boolean d = false;
    private static volatile int e = HttpResponseCode.OK;

    q(g gVar) {
        if (a.get() == null) {
            if (a.compareAndSet(null, Executors.newSingleThreadExecutor(new r(this)))) {
                ((ExecutorService) a.get()).submit(new t("TJY", gVar, new s(this), null));
            }
        }
    }

    private void g() {
        synchronized (b) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = b.iterator();
            while (it.hasNext()) {
                o oVar = (o) it.next();
                if (oVar.c()) {
                    it.remove();
                } else if (currentTimeMillis - oVar.d() >= 300000) {
                    it.remove();
                }
            }
            if (b.size() >= 15) {
                for (int i = 0; i < 5; i++) {
                    b.remove();
                }
            }
        }
    }

    public p a() {
        return c;
    }

    public void a(o oVar) {
        g();
        b.add(oVar);
    }

    public boolean b() {
        return d;
    }

    public int c() {
        return e;
    }
}
