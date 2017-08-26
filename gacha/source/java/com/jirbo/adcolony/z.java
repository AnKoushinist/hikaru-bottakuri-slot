package com.jirbo.adcolony;

import java.util.ArrayList;
import java.util.Iterator;
import org.cocos2dx.lib.BuildConfig;

class z {
    static String a = new String("mutex");
    static ArrayList<a> b = new ArrayList();
    static ArrayList<a> c = new ArrayList();
    static ArrayList<Runnable> d = new ArrayList();
    static ArrayList<Runnable> e = new ArrayList();
    static volatile boolean f;

    static class a extends Thread {
        Runnable a;

        a() {
        }

        public void run() {
            while (true) {
                if (this.a != null) {
                    try {
                        this.a.run();
                    } catch (RuntimeException e) {
                        a.e("Exception caught in reusable thread.");
                        a.e(e + BuildConfig.FLAVOR);
                        e.printStackTrace();
                    }
                    this.a = null;
                }
                if (!z.f) {
                    synchronized (this) {
                        synchronized (z.a) {
                            z.b.add(this);
                        }
                        try {
                            wait();
                        } catch (InterruptedException e2) {
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    z() {
    }

    static void a() {
        c();
        synchronized (a) {
            d.clear();
        }
        b();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void a(java.lang.Runnable r3) {
        /*
        r0 = 0;
        r1 = a;
        monitor-enter(r1);
        r2 = f;	 Catch:{ all -> 0x001b }
        if (r2 == 0) goto L_0x000f;
    L_0x0008:
        r0 = d;	 Catch:{ all -> 0x001b }
        r0.add(r3);	 Catch:{ all -> 0x001b }
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
    L_0x000e:
        return;
    L_0x000f:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        if (r0 != 0) goto L_0x000e;
    L_0x0012:
        r0 = new java.lang.Thread;
        r0.<init>(r3);
        r0.start();
        goto L_0x000e;
    L_0x001b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jirbo.adcolony.z.a(java.lang.Runnable):void");
    }

    static void b() {
        synchronized (a) {
            f = false;
            e.clear();
            e.addAll(d);
            d.clear();
            c.clear();
        }
        Iterator it = e.iterator();
        while (it.hasNext()) {
            a((Runnable) it.next());
        }
    }

    static void c() {
        synchronized (a) {
            f = true;
            Iterator it = b.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                synchronized (aVar) {
                    aVar.notify();
                }
            }
            synchronized (a) {
                b.clear();
            }
        }
    }
}
