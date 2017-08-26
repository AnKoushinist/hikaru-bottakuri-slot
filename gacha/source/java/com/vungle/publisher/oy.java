package com.vungle.publisher;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import twitter4j.TwitterResponse;

/* compiled from: vungle */
public final class oy extends ne<jk<?, ?, ?>> {
    @Inject
    a k;
    @Inject
    com.vungle.publisher.agg.a l;
    @Inject
    com.vungle.publisher.op.a m;
    @Inject
    com.vungle.publisher.ob.a n;
    private op o;
    private ob p;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Orientation.values().length];

        static {
            try {
                a[Orientation.autoRotate.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Orientation.matchVideo.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class a extends mr<oy> {

        @Singleton
        /* compiled from: vungle */
        public static class a {
            @Inject
            a a;

            @Inject
            a() {
            }

            public final a a(oy oyVar) {
                this.a.a = oyVar;
                return this.a;
            }
        }

        @Inject
        a() {
        }

        public final void onEvent(w wVar) {
            boolean z;
            com.vungle.publisher.jl.a aVar = wVar.a;
            Logger.v(Logger.EVENT_TAG, "cta click event: " + aVar);
            oy oyVar = (oy) this.a;
            try {
                String s = ((jk) oyVar.a).s();
                Logger.v(Logger.AD_TAG, "call to action destination " + s);
                if (s != null) {
                    Intent a = agp.a("android.intent.action.VIEW", Uri.parse(s));
                    a.addFlags(268435456);
                    oyVar.g.a(new x(oyVar.a, aVar));
                    oyVar.b.startActivity(a);
                }
                z = true;
            } catch (Throwable e) {
                oyVar.h.a(Logger.AD_TAG, "error loading call-to-action URL " + null, e);
                z = false;
            }
            oyVar.a(z, true);
        }

        public final void onEvent(ai aiVar) {
            Logger.v(Logger.EVENT_TAG, "postRoll.onRepeat()");
            ((oy) this.a).d();
        }

        public final void onEvent(av avVar) {
            Logger.v(Logger.EVENT_TAG, "video.onCancel()");
            ((oy) this.a).c();
        }

        public final void onEvent(aw awVar) {
            Logger.v(Logger.EVENT_TAG, "video.onNext()");
            ((oy) this.a).c();
        }

        public final void onEvent(ah ahVar) {
            Logger.v(Logger.EVENT_TAG, "postRoll.onCancel()");
            ((oy) this.a).a(true, false);
        }
    }

    public final /* synthetic */ void a(FullScreenAdActivity fullScreenAdActivity, cj cjVar, n nVar, Bundle bundle) {
        int i = 10;
        boolean z = true;
        jk jkVar = (jk) cjVar;
        try {
            Logger.d(Logger.AD_TAG, "create video ad");
            fullScreenAdActivity.getWindow().setBackgroundDrawable(new ColorDrawable(-16777216));
            super.a(fullScreenAdActivity, jkVar, nVar, bundle);
            jj u = jkVar.u();
            Orientation orientation = nVar.getOrientation();
            switch (AnonymousClass1.a[orientation.ordinal()]) {
                case TwitterResponse.READ /*1*/:
                    Logger.d(Logger.AD_TAG, "ad orientation " + orientation);
                    break;
                default:
                    boolean z2 = (u.g == null || u.n == null || u.n.intValue() <= u.g.intValue()) ? false : true;
                    if (!z2) {
                        if (u.g == null || u.n == null || u.g.intValue() <= u.n.intValue()) {
                            z = false;
                        }
                        if (!z) {
                            Logger.d(Logger.AD_TAG, "ad orientation " + orientation + " (unknown) --> auto-rotate");
                            break;
                        }
                        Logger.d(Logger.AD_TAG, "ad orientation " + orientation + " (portrait)");
                        i = 7;
                        break;
                    }
                    Logger.d(Logger.AD_TAG, "ad orientation " + orientation + " (landscape)");
                    i = 6;
                    break;
                    break;
            }
            fullScreenAdActivity.setRequestedOrientation(i);
            com.vungle.publisher.op.a aVar = this.m;
            op opVar = (op) fullScreenAdActivity.getFragmentManager().findFragmentByTag("videoFragment");
            if (opVar == null) {
                opVar = (op) aVar.a.get();
            }
            String s = jkVar.s();
            jj u2 = jkVar.u();
            if (u2 != null) {
                opVar.b = nVar;
                opVar.e = u2;
                opVar.H = s;
            } else {
                opVar = null;
            }
            this.o = opVar;
            if (jkVar instanceof eo) {
                ep p = ((eo) jkVar).p();
                if (p != null) {
                    this.p = (ob) this.n.a(fullScreenAdActivity, (String) jkVar.w(), p.j.a(p.B().toURI().toString()), nVar);
                }
            }
            if ("postRollFragment".equals(this.f)) {
                c();
            } else {
                d();
            }
        } catch (Throwable e) {
            this.h.a(Logger.AD_TAG, "error playing video ad", e);
            a(false, false);
        }
    }

    @Inject
    oy() {
    }

    protected final mr<?> a() {
        return this.k.a(this);
    }

    protected final afx<?> b() {
        com.vungle.publisher.agg.a aVar = this.l;
        aVar.a.a((cj) (jk) this.a);
        return aVar.a;
    }

    final void c() {
        if (this.p == null) {
            a(true, false);
            return;
        }
        this.g.a(new aj());
        a(this.p);
    }

    final void d() {
        if (this.o == null) {
            c();
        } else {
            a(this.o);
        }
    }
}
