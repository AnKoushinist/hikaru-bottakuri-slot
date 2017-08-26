package com.vungle.publisher;

import com.vungle.log.Logger;
import dagger.Lazy;
import javax.inject.Inject;

/* compiled from: vungle */
public final class yj extends wq {
    @Inject
    protected Lazy<afy> a;

    @Inject
    yj() {
    }

    protected final void a(we weVar, vy vyVar) {
        yg ygVar = (yg) weVar.a;
        afy com_vungle_publisher_afy = (afy) this.a.get();
        Logger.i(Logger.REPORT_TAG, "deleting report " + ygVar.f);
        com_vungle_publisher_afy.c.a((Object[]) new Integer[]{r2});
        ygVar.e.o();
    }
}
