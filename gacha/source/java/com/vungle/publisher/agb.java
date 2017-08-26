package com.vungle.publisher;

import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: vungle */
public final class agb extends afx<dm> {
    @Inject
    com.vungle.publisher.ck.a h;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        public agb a;

        @Inject
        a() {
        }
    }

    @Inject
    agb() {
    }

    protected final void a() {
    }

    public final void onEvent(tx txVar) {
        ji jiVar = txVar.a;
        String str = txVar.b;
        Logger.d(Logger.REPORT_TAG, "received mraid user action event: " + jiVar.toString() + (str == null ? BuildConfig.FLAVOR : ", w/ value " + str));
        a(jiVar, str);
        if (tn.mraidOpen.equals(jiVar) || tn.mraidClose.equals(jiVar)) {
            a(txVar.e);
        }
    }

    public final void onEvent(tv tvVar) {
        Logger.d(Logger.REPORT_TAG, "received mraid tpat event: " + tvVar.a.toString());
        ji jiVar = tvVar.a;
        if (this.a == null) {
            Logger.w(Logger.REPORT_TAG, "null ad in AdReportingHandler - cannot track event " + jiVar);
            return;
        }
        Logger.v(Logger.REPORT_TAG, "tpat event " + jiVar.toString());
        this.g.a(this.a, jiVar, ((dm) this.a).a(jiVar));
    }

    public final void onEvent(ub ubVar) {
        com.vungle.publisher.ck.a aVar = this.h;
        Integer num = (Integer) this.c.w();
        String str = ubVar.a;
        ck a = aVar.a();
        a.a = str;
        a.b = num;
        a.v();
    }

    public final void onEvent(aq<dm> aqVar) {
        super.onEvent((aq) aqVar);
        try {
            this.b.c = Long.valueOf(aqVar.e);
            this.b.b_();
        } catch (Throwable e) {
            this.e.a(Logger.REPORT_TAG, "error updating play start millis", e);
        }
    }
}
