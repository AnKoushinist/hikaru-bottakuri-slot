package com.vungle.publisher;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class nq extends ne<dm> {
    @Inject
    com.vungle.publisher.agb.a k;
    @Inject
    a l;
    @Inject
    com.vungle.publisher.nh.a m;
    private nh n;

    @Singleton
    /* compiled from: vungle */
    public static class a extends mr<nq> {
        @Inject
        com.vungle.publisher.gm.a b;

        @Singleton
        /* compiled from: vungle */
        public static class a {
            @Inject
            a a;

            @Inject
            a() {
            }

            public final a a(nq nqVar) {
                this.a.a = nqVar;
                return this.a;
            }
        }

        @Inject
        a() {
        }

        public final void onEvent(ty tyVar) {
            Logger.v(Logger.EVENT_TAG, "mraidAd.onClose()");
            this.eventBus.a(new tx(tn.mraidClose));
            ((nq) this.a).a(true, false);
        }

        public final void onEvent(ud udVar) {
            ((nq) this.a).a(udVar.a);
        }

        public final void onEvent(tz tzVar) {
            this.eventBus.a(new tx(tn.mraidOpen));
        }

        public final void onEvent(ua uaVar) {
            ((nq) this.a).a(false, false);
        }

        public final void onEvent(ug ugVar) {
            try {
                to toVar = ugVar.a;
                boolean z = ugVar.b;
                if (toVar != to.NONE) {
                    Logger.v(Logger.EVENT_TAG, "force mraid orientation: " + toVar);
                    ((nq) this.a).a(toVar.d);
                } else if (z) {
                    ((nq) this.a).a(4);
                } else if (agl.a(pj.JELLY_BEAN_MR2)) {
                    ((nq) this.a).a(14);
                } else {
                    ((nq) this.a).a(5);
                }
            } catch (Throwable e) {
                this.b.a(Logger.EVENT_TAG, "error setting mraid orientation", e);
            }
        }
    }

    public final /* synthetic */ void a(FullScreenAdActivity fullScreenAdActivity, cj cjVar, n nVar, Bundle bundle) {
        dm dmVar = (dm) cjVar;
        Logger.d(Logger.AD_TAG, "create mraid ad");
        super.a(fullScreenAdActivity, dmVar, nVar, bundle);
        this.n = (nh) this.m.a(fullScreenAdActivity, (String) dmVar.w(), dmVar.p(), nVar);
        fullScreenAdActivity.setRequestedOrientation(4);
        fullScreenAdActivity.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        if (this.n == null) {
            a(true, false);
        } else {
            a(this.n);
        }
    }

    @Inject
    nq() {
    }

    public final void a(FullScreenAdActivity fullScreenAdActivity) {
        super.a(fullScreenAdActivity);
        if (!this.n.c()) {
            fullScreenAdActivity.finish();
        }
    }

    protected final mr<?> a() {
        return this.l.a(this);
    }

    protected final afx<?> b() {
        com.vungle.publisher.agb.a aVar = this.k;
        aVar.a.a((cj) (dm) this.a);
        return aVar.a;
    }
}
