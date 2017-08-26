package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.bt.b;
import com.vungle.publisher.gm.a;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class yc extends vr {
    @Inject
    ql a;
    @Inject
    Lazy<py> b;
    @Inject
    ym c;
    @Inject
    zn d;
    @Inject
    zz e;
    @Inject
    aaf f;
    @Inject
    aal g;
    @Inject
    Lazy<aau> h;
    @Inject
    aba i;
    @Inject
    a j;
    @Inject
    protected bt k;

    @Inject
    yc() {
    }

    public final void a(final cq<?, ?, ?, ?> cqVar) {
        this.k.a(new Runnable(this) {
            final /* synthetic */ yc b;

            public final void run() {
                try {
                    this.b.c.a(cqVar).a();
                } catch (Throwable e) {
                    this.b.j.a(Logger.PROTOCOL_TAG, "error sending report ad", e);
                }
            }
        }, b.reportAd);
    }

    public final void a() {
        this.k.a(new Runnable(this) {
            final /* synthetic */ yc a;

            {
                this.a = r1;
            }

            public final void run() {
                try {
                    this.a.d.a().a();
                } catch (Throwable e) {
                    this.a.j.a(Logger.PROTOCOL_TAG, "error sending request config", e);
                }
            }
        }, b.requestConfig);
    }

    public final void a(final String str, final p pVar) {
        this.k.a(new Runnable(this) {
            final /* synthetic */ yc c;

            public final void run() {
                try {
                    this.c.e.a(str, pVar).a();
                } catch (Throwable e) {
                    this.c.j.b(Logger.PROTOCOL_TAG, "error creating request streaming ad message", e);
                    this.c.b();
                } catch (Throwable e2) {
                    this.c.j.a(Logger.PROTOCOL_TAG, "error requesting streaming ad", e2);
                    this.c.b();
                }
            }
        }, b.requestStreamingAd);
    }

    final void b() {
        this.a.a(new af());
    }

    public final void a(long j, long j2) {
        final long j3 = j;
        final long j4 = j2;
        this.k.a(new Runnable(this) {
            final /* synthetic */ yc c;

            public final void run() {
                try {
                    this.c.f.a(j3, j4).a();
                } catch (Throwable e) {
                    this.c.j.a(Logger.PROTOCOL_TAG, "error sending session end", e);
                }
            }
        }, b.sessionEnd);
    }

    public final void a(final long j) {
        this.k.a(new Runnable(this) {
            final /* synthetic */ yc b;

            public final void run() {
                try {
                    this.b.g.a(j).a();
                } catch (Throwable e) {
                    this.b.j.a(Logger.PROTOCOL_TAG, "error sending session start", e);
                }
            }
        }, b.sessionStart);
    }

    public final void a(final xk xkVar) {
        this.k.a(new Runnable(this) {
            final /* synthetic */ yc b;

            public final void run() {
                try {
                    ((aau) this.b.h.get()).a().a();
                } catch (Throwable e) {
                    this.b.j.a(Logger.PROTOCOL_TAG, "error sending track install", e);
                    this.b.a(xkVar);
                }
            }
        }, b.reportInstall, (long) agv.a(xkVar.b, 5000, 300000));
    }

    public final void c() {
        this.k.a(new Runnable(this) {
            final /* synthetic */ yc a;

            {
                this.a = r1;
            }

            public final void run() {
                try {
                    this.a.i.a(System.currentTimeMillis() / 1000).a();
                } catch (Throwable e) {
                    this.a.j.a(Logger.PROTOCOL_TAG, "error sending unfilled ad", e);
                }
            }
        }, b.unfilledAd);
    }
}
