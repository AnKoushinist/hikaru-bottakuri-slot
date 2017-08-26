package com.tapjoy.internal;

import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import com.tapjoy.internal.dy.a;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public final class gm {
    final fy a;
    final AtomicBoolean b = new AtomicBoolean();
    ScheduledFuture c;
    private final Runnable d = new Runnable(this) {
        final /* synthetic */ gm a;

        {
            this.a = r1;
        }

        public final void run() {
            if (this.a.b.compareAndSet(true, false)) {
                fw.a("The session ended");
                fy fyVar = this.a.a;
                long elapsedRealtime = SystemClock.elapsedRealtime() - fyVar.c;
                gc gcVar = fyVar.a;
                synchronized (gcVar) {
                    long a = gcVar.c.i.a() + elapsedRealtime;
                    gcVar.c.i.a(a);
                    gcVar.b.i = Long.valueOf(a);
                }
                a a2 = fyVar.a(eb.APP, "session");
                a2.i = Long.valueOf(elapsedRealtime);
                fyVar.a(a2);
                fyVar.c = 0;
                gc gcVar2 = fyVar.a;
                long longValue = a2.e.longValue();
                synchronized (gcVar2) {
                    Editor a3 = gcVar2.c.a();
                    gcVar2.c.j.a(a3, longValue);
                    gcVar2.c.k.a(a3, elapsedRealtime);
                    a3.commit();
                    gcVar2.b.j = Long.valueOf(longValue);
                    gcVar2.b.k = Long.valueOf(elapsedRealtime);
                }
                fx fxVar = fyVar.b;
                if (fxVar.b != null) {
                    fxVar.a();
                    new hm(fxVar) {
                        final /* synthetic */ fx a;

                        {
                            this.a = r1;
                        }

                        protected final boolean a() {
                            return !this.a.a.c();
                        }
                    }.run();
                }
                fxVar.a.flush();
                et.d.notifyObservers();
            }
        }
    };
    private final Runnable e = new Runnable(this) {
        final /* synthetic */ gm a;

        {
            this.a = r1;
        }

        public final void run() {
        }
    };

    gm(fy fyVar) {
        this.a = fyVar;
    }

    public final void a() {
        if (!this.b.get()) {
            return;
        }
        if (!Boolean.FALSE.booleanValue()) {
            this.d.run();
        } else if (this.c == null || this.c.cancel(false)) {
            this.c = gn.a.schedule(this.d, 3000, TimeUnit.MILLISECONDS);
        }
    }
}
