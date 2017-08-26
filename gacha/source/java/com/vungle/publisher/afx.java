package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.cq.b;
import com.vungle.publisher.cq.c;
import com.vungle.publisher.gm.a;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class afx<A extends cj> extends qe {
    protected A a;
    protected cp<?, ?, ?> b;
    protected cq<?, ?, ?, A> c;
    @Inject
    b d;
    @Inject
    a e;
    @Inject
    afy f;
    @Inject
    vl g;

    protected abstract void a();

    protected final void a(y<A> yVar) {
        a(yVar.a());
    }

    public final void a(A a) {
        if (this.a == null || !this.a.a((cj) a)) {
            Logger.i(Logger.REPORT_TAG, "new ad " + a.z());
            this.a = a;
            this.c = this.d.a((cj) a);
            this.b = this.c.s();
            Logger.d(Logger.REPORT_TAG, "current play: " + this.b.toString());
            a();
            return;
        }
        Logger.v(Logger.REPORT_TAG, "same ad " + a.z());
    }

    public void onEvent(aq<A> aqVar) {
        try {
            Logger.d(Logger.REPORT_TAG, "received play ad start");
            n nVar = aqVar.a;
            cq cqVar = this.c;
            cqVar.a(c.playing);
            cqVar.a(nVar.getExtras());
            boolean isIncentivized = nVar.isIncentivized();
            cqVar.b(isIncentivized);
            if (isIncentivized) {
                cqVar.b(nVar.getIncentivizedUserId());
            }
            String placement = nVar.getPlacement();
            if (ags.a(placement)) {
                cqVar.c(placement);
            }
            cqVar.c(Long.valueOf(aqVar.e));
            cqVar.y();
        } catch (Throwable e) {
            this.e.a(Logger.REPORT_TAG, "error processing ad start event", e);
        }
    }

    public void onEvent(bn bnVar) {
        try {
            Logger.d(Logger.REPORT_TAG, "received destroyed ad end");
            a(bnVar.e);
        } catch (Exception e) {
            Logger.w(Logger.REPORT_TAG, "error processing destroyed ad end");
        }
    }

    protected final void a(ji jiVar) {
        a(jiVar, null);
    }

    protected final void a(ji jiVar, Object obj) {
        try {
            this.b.a(jiVar, obj);
        } catch (Throwable e) {
            this.e.a(Logger.REPORT_TAG, "error reporting event", e);
        }
    }

    protected final void a(long j) {
        unregister();
        cq cqVar = this.c;
        if (cqVar == null) {
            Logger.d(Logger.REPORT_TAG, "no current ad report");
        } else {
            cqVar.a(c.reportable);
            cqVar.a(Long.valueOf(j));
            cqVar.y();
        }
        this.f.a();
        this.a = null;
        this.c = null;
        this.b = null;
        a();
    }
}
