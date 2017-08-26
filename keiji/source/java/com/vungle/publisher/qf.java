package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.bt.b;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class qf extends qe {
    public EventListener a;
    boolean b;
    @Inject
    bt c;
    @Inject
    a d;
    private int e;
    private AtomicBoolean f = new AtomicBoolean();

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        public Provider<qf> a;

        @Inject
        a() {
        }
    }

    @Inject
    qf() {
    }

    public final void onEvent(u uVar) {
        final boolean a = this.d.a();
        if (this.f.compareAndSet(!a, a)) {
            a(new Runnable(this) {
                final /* synthetic */ qf b;

                public final void run() {
                    this.b.a.onAdPlayableChanged(a);
                }
            });
        }
    }

    public final void onEvent(aq<cj> aqVar) {
        Logger.d(Logger.EVENT_TAG, "onAdStart() callback");
        this.e = 0;
        this.b = false;
        a(new Runnable(this) {
            final /* synthetic */ qf a;

            {
                this.a = r1;
            }

            public final void run() {
                this.a.a.onAdStart();
            }
        });
    }

    public final void onEvent(bq<cj> bqVar) {
        a(bqVar.a);
    }

    public final void onEvent(as asVar) {
        this.b = true;
    }

    public final void onEvent(bf bfVar) {
        if (bfVar instanceof bn) {
            Logger.d(Logger.EVENT_TAG, "onAdEnd() - activity destroyed");
        } else {
            Logger.d(Logger.EVENT_TAG, "onAdEnd() - error during playback");
        }
        a(false);
    }

    private void a(final boolean z) {
        Logger.d(Logger.EVENT_TAG, "onAdEnd(" + z + ") callback");
        a(new Runnable(this) {
            final /* synthetic */ qf b;

            public final void run() {
                this.b.a.onAdEnd(this.b.b, z);
            }
        });
    }

    public final void onEvent(bi biVar) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(error) callback");
        a("Error launching ad");
    }

    public final void onEvent(bg bgVar) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(already playing) callback");
        a("Ad already playing");
    }

    public final void onEvent(bj bjVar) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(not initialized) callback");
        a("Vungle Publisher SDK was not successfully initialized - please check the logs");
    }

    public final void onEvent(bk bkVar) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(throttled) callback");
        a("Only " + bkVar.a + " of minimum " + bkVar.b + " seconds elapsed between ads");
    }

    public final void onEvent(bl blVar) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(unavailable) callback");
        a("No cached or streaming ad available");
    }

    public final void onEvent(ad adVar) {
        int i = adVar.a;
        if (i > this.e) {
            Logger.d(Logger.EVENT_TAG, "new watched millis " + i);
            this.e = i;
            return;
        }
        Logger.d(Logger.EVENT_TAG, "shorter watched millis " + i);
    }

    private void a(final String str) {
        a(new Runnable(this) {
            final /* synthetic */ qf b;

            public final void run() {
                this.b.a.onAdUnavailable(str);
            }
        });
    }

    private void a(Runnable runnable) {
        this.c.a(runnable, b.clientEvent);
    }
}
