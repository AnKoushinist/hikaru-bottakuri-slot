package com.vungle.publisher;

import android.content.ContentValues;
import com.vungle.log.Logger;
import com.vungle.publisher.cq.c;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class InitializationEventListener extends qe {
    @Inject
    a a;
    @Inject
    bt b;
    @Inject
    yc c;
    @Inject
    afy d;
    @Inject
    b e;
    @Inject
    a f;
    @Inject
    py g;
    @Inject
    pn h;
    private final agz i = new agz();
    private final AtomicBoolean j = new AtomicBoolean(false);

    @Singleton
    /* compiled from: vungle */
    static class a extends qe {
        @Inject
        lz a;

        @Inject
        a() {
        }

        public final void onEvent(uu uuVar) {
            this.a.a();
        }

        public final void onEvent(aq<?> aqVar) {
            this.a.a();
        }
    }

    @Singleton
    /* compiled from: vungle */
    static class b extends qe {
        @Inject
        qp a;

        @Inject
        b() {
        }

        public final void onEvent(uu uuVar) {
            unregister();
            qp qpVar = this.a;
            try {
                if (qpVar.b.b()) {
                    Logger.v(Logger.DATA_TAG, "sdk configured to send logged exceptions");
                    List c = qpVar.c.c(10);
                    int size = c.size();
                    if (size > 0) {
                        Logger.d(Logger.DATA_TAG, "sending " + size + " logged exceptions");
                        qpVar.a.a(c);
                        return;
                    }
                    return;
                }
                Logger.v(Logger.DATA_TAG, "sdk not configured to send logged exceptions");
            } catch (Throwable e) {
                Logger.e(Logger.DATA_TAG, "error sending exceptions. irony?", e);
            }
        }
    }

    @Inject
    InitializationEventListener() {
    }

    public void onEvent(qb qbVar) {
        Logger.d(Logger.DEVICE_TAG, "device ID available");
        a(1);
    }

    public void onEvent(ci ciVar) {
        Logger.d(Logger.DATABASE_TAG, "on database ready");
        a(0);
    }

    public void onEvent(qc qcVar) {
        Logger.d(Logger.DEVICE_TAG, "webview user agent updated");
        a(2);
    }

    private void a(int i) {
        agz com_vungle_publisher_agz = this.i;
        int i2;
        int i3;
        do {
            i2 = com_vungle_publisher_agz.a.get();
            if (i < 0 || i > 31) {
                throw new IllegalArgumentException("bit index must be 0-31");
            }
            i3 = (1 << i) | i2;
        } while (!com_vungle_publisher_agz.a.compareAndSet(i2, i3));
        if (i3 == 7) {
            Logger.d(Logger.DEVICE_TAG, "all initialization events complete");
            if (this.j.compareAndSet(false, true)) {
                unregister();
                this.b.a(new Runnable(this) {
                    final /* synthetic */ InitializationEventListener a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        this.a.e.register();
                        this.a.f.register();
                        this.a.g.a(true);
                        afy com_vungle_publisher_afy = this.a.d;
                        com.vungle.publisher.cq.b bVar = com_vungle_publisher_afy.b;
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("status", c.reportable.toString());
                        bVar.a.getWritableDatabase().update("ad_report", contentValues, "status = ?", new String[]{c.playing.toString()});
                        if (com_vungle_publisher_afy.g.l.getBoolean("IsVgAppInstalled", false)) {
                            Logger.v(Logger.REPORT_TAG, "install already reported");
                        } else {
                            Logger.d(Logger.REPORT_TAG, "reporting install");
                            com_vungle_publisher_afy.f.a(new xk());
                        }
                        com_vungle_publisher_afy.a();
                        a aVar = this.a.a;
                        aVar.b(true);
                        ((com.vungle.publisher.a.a) aVar.j.get()).register();
                        aVar.registerOnce();
                        this.a.c.a();
                    }
                }, 2000);
            }
        }
    }
}
