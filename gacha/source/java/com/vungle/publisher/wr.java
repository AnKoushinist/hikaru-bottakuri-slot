package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.bt.b;
import com.vungle.publisher.gm.a;
import java.util.List;
import javax.inject.Inject;

/* compiled from: vungle */
public final class wr extends vr {
    @Inject
    xg a;
    @Inject
    vc b;
    @Inject
    a c;
    @Inject
    protected bt d;

    @Inject
    wr() {
    }

    public final void a(final List<gm> list) {
        this.d.a(new Runnable(this) {
            final /* synthetic */ wr b;

            public final void run() {
                try {
                    this.b.a.a(list).a();
                } catch (Throwable e) {
                    Logger.w(Logger.DATA_TAG, "error sending logged exceptions", e);
                }
            }
        }, b.reportExceptions);
    }

    public final void a(final ly lyVar) {
        this.d.a(new Runnable(this) {
            final /* synthetic */ wr b;

            public final void run() {
                try {
                    this.b.b.a(lyVar).a();
                } catch (Throwable e) {
                    this.b.c.a(Logger.DATA_TAG, "error sending app fingerprint chunk", e);
                }
            }
        }, b.appFingerprint);
    }
}
