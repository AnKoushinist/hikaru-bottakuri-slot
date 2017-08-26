package com.vungle.publisher;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Parcelable;
import com.tapjoy.TJAdUnitConstants.String;
import com.vungle.log.Logger;
import dagger.Lazy;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import twitter4j.TwitterResponse;

@Singleton
/* compiled from: vungle */
public final class a extends qe {
    @Inject
    py a;
    @Inject
    Context b;
    @Inject
    pn c;
    @Inject
    ql d;
    @Inject
    Class e;
    @Inject
    Class f;
    @Inject
    public bt g;
    @Inject
    uq h;
    @Inject
    Lazy<b> i;
    @Inject
    public Lazy<a> j;
    @Inject
    Provider<c> k;
    @Inject
    yc l;
    @Inject
    pv m;
    @Inject
    com.vungle.publisher.hu.a n;
    @Inject
    com.vungle.publisher.jp.b o;
    @Inject
    q p;
    @Inject
    pp q;
    @Inject
    com.vungle.publisher.gm.a r;
    @Inject
    com.vungle.publisher.cj.b s;
    @Inject
    com.vungle.publisher.dx.b t;
    @Inject
    aff u;

    /* compiled from: vungle */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ p a;
        final /* synthetic */ a b;

        public AnonymousClass2(a aVar, p pVar) {
            this.b = aVar;
            this.a = pVar;
        }

        public final void run() {
            Throwable e;
            cj cjVar = null;
            Logger.d(Logger.AD_TAG, "AdManager.playAd()");
            try {
                cj h_;
                Object obj;
                a aVar = this.b;
                p pVar = this.a;
                dn a = aVar.a(false);
                if (a != null) {
                    h_ = a.h_();
                } else {
                    h_ = null;
                }
                cj a2 = aVar.a(h_ == null ? null : (String) h_.w(), pVar);
                if (a2 != null) {
                    h_ = a2;
                }
                Logger.i(Logger.AD_TAG, "next ad " + (h_ == null ? null : h_.z()));
                if (h_ == null) {
                    try {
                        Logger.d(Logger.AD_TAG, "no ad to play");
                        this.b.d.a(new bl());
                        obj = null;
                    } catch (Exception e2) {
                        e = e2;
                        cjVar = h_;
                        try {
                            this.b.r.a(Logger.AD_TAG, "error launching ad", e);
                            this.b.d.a(new bp(cjVar, false));
                        } finally {
                            this.b.l.c();
                        }
                    }
                } else {
                    ((b) this.b.i.get()).register();
                    Intent intent = new Intent(this.b.b, h_ instanceof jk ? this.b.e : this.b.f);
                    intent.addFlags(805306368);
                    Parcelable parcelable = this.a;
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("adConfig", parcelable);
                    intent.putExtra("adConfig", bundle);
                    intent.putExtra(FullScreenAdActivity.AD_ID_EXTRA_KEY, (String) h_.w());
                    intent.putExtra(FullScreenAdActivity.AD_TYPE_EXTRA_KEY, h_.f());
                    this.b.b.startActivity(intent);
                    obj = 1;
                }
                if (obj == null) {
                    this.b.l.c();
                }
            } catch (Exception e3) {
                e = e3;
                this.b.r.a(Logger.AD_TAG, "error launching ad", e);
                this.b.d.a(new bp(cjVar, false));
            }
        }
    }

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[com.vungle.publisher.cj.c.values().length];

        static {
            try {
                a[com.vungle.publisher.cj.c.aware.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[com.vungle.publisher.cj.c.ready.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[com.vungle.publisher.cj.c.viewed.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class a extends qe {
        @Inject
        a a;

        @Inject
        a() {
        }

        public final void onEvent(v vVar) {
            this.a.b(false);
        }
    }

    @Singleton
    /* compiled from: vungle */
    static class b extends qe {
        final String a = Logger.PREPARE_TAG;
        @Inject
        a b;
        @Inject
        com.vungle.publisher.gm.a c;

        @Inject
        b() {
        }

        public final void onEvent(aq<cj> aqVar) {
            try {
                aqVar.a().b(com.vungle.publisher.cj.c.viewed);
            } catch (Throwable e) {
                this.c.a(Logger.PREPARE_TAG, "error processing start play ad event", e);
            }
        }

        public final void onEvent(ae aeVar) {
            Logger.d(Logger.PREPARE_TAG, "sent ad report - unregistering play ad listener");
            unregister();
        }

        public final void onEvent(bh bhVar) {
            Logger.d(Logger.PREPARE_TAG, "play ad failure - unregistering play ad listener");
            unregister();
        }
    }

    /* compiled from: vungle */
    static class c extends qe {
        final String a = Logger.PREPARE_TAG;
        volatile boolean b;
        volatile hu c;
        final long d = System.currentTimeMillis();
        @Inject
        com.vungle.publisher.hu.a e;

        @Inject
        c() {
        }

        final void a() {
            this.b = true;
            synchronized (this) {
                notifyAll();
            }
        }

        public final void onEvent(af afVar) {
            unregister();
            Logger.d(Logger.PREPARE_TAG, "request streaming ad failure after " + (afVar.e - this.d) + " ms");
            a();
        }

        public final void onEvent(ap apVar) {
            unregister();
            long j = apVar.e - this.d;
            aea com_vungle_publisher_aea = (aea) apVar.a;
            if (Boolean.TRUE.equals(com_vungle_publisher_aea.k)) {
                Logger.d(Logger.PREPARE_TAG, "received streaming ad " + com_vungle_publisher_aea.f + " after " + j + " ms");
                String str = com_vungle_publisher_aea.f;
                hu huVar = (hu) this.e.a((Object) str);
                if (huVar == null) {
                    hu a = this.e.a(com_vungle_publisher_aea);
                    this.c = a;
                    Logger.d(Logger.PREPARE_TAG, "inserting new " + a.z());
                    try {
                        a.q();
                    } catch (SQLException e) {
                        Logger.d(Logger.PREPARE_TAG, "did not insert streaming ad - possible duplicate");
                    }
                } else {
                    try {
                        this.e.a((jk) huVar, (aed) com_vungle_publisher_aea);
                    } catch (Throwable e2) {
                        Logger.w(Logger.PREPARE_TAG, "error updating ad " + str, e2);
                    }
                    com.vungle.publisher.cj.c g = huVar.g();
                    switch (AnonymousClass4.a[g.ordinal()]) {
                        case TwitterResponse.READ /*1*/:
                            Logger.w(Logger.PREPARE_TAG, "unexpected ad status " + g + " for " + huVar.z());
                            break;
                        case TwitterResponse.READ_WRITE /*2*/:
                        case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                            break;
                        default:
                            Logger.w(Logger.PREPARE_TAG, "existing " + huVar.z() + " with status " + g + " - ignoring");
                            break;
                    }
                    Logger.d(Logger.PREPARE_TAG, "existing " + huVar.z() + " with status " + g);
                    if (g != com.vungle.publisher.cj.c.ready) {
                        huVar.b(com.vungle.publisher.cj.c.ready);
                    }
                    this.c = huVar;
                }
            } else {
                Logger.d(Logger.PREPARE_TAG, "no streaming ad to play after " + j + " ms");
            }
            a();
        }
    }

    @Inject
    a() {
    }

    public final boolean a() {
        if (!this.q.d.get() && this.q.a()) {
            if (this.t.b() != null) {
                return true;
            }
        }
        return false;
    }

    private void a(ahp<dn<?>> com_vungle_publisher_ahp_com_vungle_publisher_dn_) {
        if (this.a.o.compareAndSet(false, true)) {
            ahq anonymousClass1 = new ahq<dn<?>>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final /* synthetic */ void a(Object obj) {
                    Logger.d(Logger.PREPARE_TAG, "ad observable onNext");
                    this.a.d.a(new ag());
                }

                public final void a() {
                    Logger.d(Logger.PREPARE_TAG, "ad observable onComplete");
                    this.a.a.a();
                    this.a.b(false);
                }

                public final void a(Throwable th) {
                    Logger.d(Logger.PREPARE_TAG, "ad observable onError");
                    this.a.a.a();
                    this.a.b(false);
                }
            };
            if (anonymousClass1 instanceof ahu) {
                ahp.a((ahu) anonymousClass1, (ahp) com_vungle_publisher_ahp_com_vungle_publisher_dn_);
            } else {
                ahp.a(new ajy(anonymousClass1), (ahp) com_vungle_publisher_ahp_com_vungle_publisher_dn_);
            }
        }
    }

    final dn<?> a(boolean z) {
        if (this.c.o()) {
            dn<?> a;
            if (z) {
                a = this.t.a(com.vungle.publisher.cj.c.ready, com.vungle.publisher.cj.c.preparing);
            } else {
                a = this.t.b();
            }
            if (a == null) {
                Logger.d(Logger.PREPARE_TAG, "no local ad available");
                aff com_vungle_publisher_aff = this.u;
                ahp a2 = ahp.a(new aip(new aih<ahp<zq>>(com_vungle_publisher_aff) {
                    final /* synthetic */ aff a;

                    {
                        this.a = r1;
                    }

                    public final /* synthetic */ Object call() {
                        long max = Math.max(0, this.a.k.l.getLong("VgSleepWakeupTime", 0) - System.currentTimeMillis());
                        Logger.d(Logger.PREPARE_TAG, "request ad after sleep delay: " + max);
                        return akc.a(this.a.b.d()).a(new aja(max, TimeUnit.MILLISECONDS, this.a.a));
                    }
                }));
                ahs c = alw.c();
                if (a2 instanceof akc) {
                    a2 = ((akc) a2).a(c);
                } else {
                    a2 = ahp.a(new ajd(a2, c));
                }
                a(ahp.a(new air(a2.a(com_vungle_publisher_aff.c).a(com_vungle_publisher_aff.e).a(com_vungle_publisher_aff.f).b(com_vungle_publisher_aff.g).a(com_vungle_publisher_aff.h), com_vungle_publisher_aff.i)).a(com_vungle_publisher_aff.j).a(com_vungle_publisher_aff.d).c(com_vungle_publisher_aff.l.a(100, "ad prep chain failure")));
                return a;
            }
            com.vungle.publisher.cj.c g = a.g();
            if (g != com.vungle.publisher.cj.c.preparing) {
                if (g == com.vungle.publisher.cj.c.ready) {
                    Logger.v(Logger.PREPARE_TAG, "local ad already available for " + a.d());
                }
                return a;
            } else if (z) {
                Logger.d(Logger.PREPARE_TAG, "local ad partially prepared, restarting preparation for " + a.d());
                a(akc.a((Object) a).a(this.u.d));
                return null;
            } else {
                Logger.i(Logger.PREPARE_TAG, "local ad partially prepared, but not restarting preparation for " + a.d());
                return null;
            }
        }
        Logger.w(Logger.PREPARE_TAG, "unable to fetch local ad -  no external storage available");
        return null;
    }

    final hu a(String str, p pVar) {
        Throwable th;
        hu huVar;
        Throwable th2;
        hu huVar2;
        hu huVar3 = null;
        boolean z = false;
        try {
            if (this.m.b) {
                un a = this.h.a();
                z = this.m.c.contains(a);
                Logger.d(Logger.PREPARE_TAG, "ad streaming " + (z ? String.ENABLED : "disabled") + " for " + a + " connectivity");
            } else {
                Logger.d(Logger.PREPARE_TAG, "ad streaming disabled");
            }
            if (!z) {
                return null;
            }
            Logger.d(Logger.PREPARE_TAG, "requesting streaming ad");
            c cVar = (c) this.k.get();
            cVar.register();
            this.l.a(str, pVar);
            long j = cVar.d;
            int i = this.m.d;
            Logger.d(Logger.CONFIG_TAG, "streaming response timeout config " + i + " ms");
            long j2 = ((long) i) + j;
            synchronized (cVar) {
                while (!cVar.b) {
                    try {
                        long currentTimeMillis = j2 - System.currentTimeMillis();
                        if (currentTimeMillis > 0) {
                            try {
                                cVar.wait(currentTimeMillis);
                            } catch (InterruptedException e) {
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        huVar = null;
                        th2 = th;
                    }
                }
                j2 = System.currentTimeMillis() - j;
                if (cVar.b) {
                    huVar = cVar.c;
                    if (huVar != null) {
                        try {
                            Logger.d(Logger.PREPARE_TAG, "request streaming ad success after " + j2 + " ms " + huVar.z());
                            huVar3 = huVar;
                        } catch (Throwable th4) {
                            th2 = th4;
                            try {
                                throw th2;
                            } catch (Throwable e2) {
                                th2 = e2;
                                huVar2 = huVar;
                            }
                        }
                    } else {
                        huVar3 = huVar;
                    }
                } else {
                    Logger.d(Logger.PREPARE_TAG, "request streaming ad timeout after " + j2 + " ms");
                    cVar.a();
                }
                try {
                    return huVar3;
                } catch (Throwable th32) {
                    th = th32;
                    huVar = huVar3;
                    th2 = th;
                    throw th2;
                }
            }
        } catch (Throwable e22) {
            th = e22;
            huVar2 = null;
            th2 = th;
            this.r.a(Logger.PREPARE_TAG, "error getting streaming ad", th2);
            return huVar2;
        }
    }

    public final void b(boolean z) {
        a(z);
        this.g.a(com.vungle.publisher.bt.b.deleteExpiredAds);
        Long c = this.t.c();
        if (c != null) {
            this.g.a(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final void run() {
                    new com.vungle.publisher.dx.b.a(this.a.t) {
                        final /* synthetic */ b a;

                        {
                            this.a = r2;
                        }

                        final int a(dx<?, ?, ?> dxVar) {
                            return dxVar.a();
                        }
                    }.a();
                }
            }, com.vungle.publisher.bt.b.deleteExpiredAds, c.longValue() - System.currentTimeMillis());
        }
    }

    public final void onEvent(qo qoVar) {
        b(false);
    }
}
