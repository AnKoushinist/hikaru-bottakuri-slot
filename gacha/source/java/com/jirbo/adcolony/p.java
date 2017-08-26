package com.jirbo.adcolony;

import android.os.Handler;
import android.os.Message;
import java.util.Iterator;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;

class p implements Runnable {
    public static final int a = 5;
    public static final int b = 10;
    static String c = "MONITOR_MUTEX";
    static volatile p d;
    static volatile long e;
    long f;

    static class a extends Handler {
        a() {
            a.s = true;
            sendMessageDelayed(obtainMessage(), 1000);
        }

        public void handleMessage(Message message) {
            if (a.P == null || a.b().isFinishing()) {
                a.b("Monitor pinger exiting.");
                a.s = false;
                return;
            }
            if (!a.r) {
                p.a();
            }
            sendMessageDelayed(obtainMessage(), 1000);
        }
    }

    p() {
    }

    static void a() {
        synchronized (c) {
            e = System.currentTimeMillis();
            if (d == null) {
                a.b("Creating ADC Monitor singleton.");
                d = new p();
                new Thread(d).start();
            }
        }
    }

    public void run() {
        a.a(a.n);
        l.a.b((Object) "ADC Monitor Started.");
        a.l.b();
        Object obj = null;
        while (a.P != null && !AdColony.activity().isFinishing()) {
            long j;
            Object obj2;
            long currentTimeMillis = System.currentTimeMillis();
            a.z = false;
            a.l.g();
            if (a.z) {
                j = 50;
            } else {
                j = (long) (obj != null ? 2000 : 250);
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            int i = (int) ((currentTimeMillis2 - e) / 1000);
            a.l.g();
            if (obj == null) {
                if (i >= a) {
                    a.b("AdColony is idle.");
                    obj = 1;
                    a.l.c();
                }
                obj2 = obj;
            } else if (i >= b) {
                break;
            } else {
                if (i < a) {
                    a.l.b();
                    a.b("AdColony is active.");
                    obj2 = null;
                }
                obj2 = obj;
            }
            if (q.c()) {
                if (!a.L) {
                    a.h();
                }
                a.L = true;
            } else {
                if (a.L) {
                    a.h();
                }
                a.L = false;
            }
            if (!(a.l.b.i == null || a.l.b.i.n == null)) {
                a.l.b.i.n.a();
            }
            a(j);
            j = System.currentTimeMillis();
            if (j - currentTimeMillis <= 3000 && j - currentTimeMillis > 0) {
                u uVar = a.l.e;
                uVar.i = (((double) (j - currentTimeMillis)) / 1000.0d) + uVar.i;
                if (a.P != null && currentTimeMillis2 - this.f > 1000) {
                    this.f = System.currentTimeMillis();
                    Iterator it = a.l.b.i.n.a.iterator();
                    while (it.hasNext()) {
                        ad adVar = (ad) it.next();
                        if ((adVar.a() && adVar.q != 0 && System.currentTimeMillis() - adVar.q > adVar.p) || (adVar.q != 0 && System.currentTimeMillis() - adVar.q > adVar.o)) {
                            if (!(a.P == null || a.p)) {
                                a.l.b.a(a.P);
                                a.p = true;
                            }
                        }
                    }
                }
            }
            obj = obj2;
        }
        synchronized (c) {
            d = null;
        }
        if (obj == null) {
            a.l.c();
        }
        if (a.P != null && AdColony.activity().isFinishing()) {
            a.A = true;
            a(RFLConstants.GPS_TIME);
            if (a.A) {
                l.c.b((Object) "ADC.finishing, controller on_stop");
                a.l.d();
                z.a();
            }
            a(RFLConstants.GPS_TIME);
            if (a.A) {
                l.c.b((Object) "Releasing Activity reference");
                a.P = null;
                a.h();
            }
        }
        System.out.println("Exiting monitor");
    }

    void a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
        }
    }
}
