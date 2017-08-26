package com.tapjoy.internal;

import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import com.tapjoy.internal.gd.c.a;
import com.tapjoy.internal.gd.i;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public final class fz {
    final fl a;
    final AtomicBoolean b = new AtomicBoolean();
    ScheduledFuture c;
    private final Runnable d = new Runnable(this) {
        final /* synthetic */ fz a;

        {
            this.a = r1;
        }

        public final void run() {
            if (this.a.b.compareAndSet(true, false)) {
                fj.a("The session ended");
                fl flVar = this.a.a;
                long elapsedRealtime = SystemClock.elapsedRealtime() - flVar.c;
                fp fpVar = flVar.a;
                synchronized (fpVar) {
                    long a = fpVar.c.i.a() + elapsedRealtime;
                    fpVar.c.i.a(a);
                    fpVar.b.b(a);
                }
                a d = flVar.a(i.APP, "session").d(elapsedRealtime);
                flVar.a(d);
                flVar.c = 0;
                fp fpVar2 = flVar.a;
                long j = d.e;
                synchronized (fpVar2) {
                    Editor a2 = fpVar2.c.a();
                    fpVar2.c.j.a(a2, j);
                    fpVar2.c.k.a(a2, elapsedRealtime);
                    a2.commit();
                    fpVar2.b.c(j);
                    fpVar2.b.d(elapsedRealtime);
                }
                fk fkVar = flVar.b;
                if (fkVar.b != null) {
                    fkVar.a();
                    new gt(fkVar) {
                        final /* synthetic */ fk a;

                        {
                            this.a = r1;
                        }

                        protected final boolean a() {
                            return !this.a.a.c();
                        }
                    }.run();
                }
                fkVar.a.flush();
                eo.d.notifyObservers();
            }
        }
    };
    private final Runnable e = new Runnable(this) {
        final /* synthetic */ fz a;

        {
            this.a = r1;
        }

        public final void run() {
        }
    };

    fz(fl flVar) {
        this.a = flVar;
    }

    public final void a() {
        if (!this.b.get()) {
            return;
        }
        if (!Boolean.FALSE.booleanValue()) {
            this.d.run();
        } else if (this.c == null || this.c.cancel(false)) {
            this.c = ga.a.schedule(this.d, 3000, TimeUnit.MILLISECONDS);
        }
    }
}
