package com.vungle.publisher;

import com.vungle.publisher.vs.c;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class wx extends wu {

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.wu.a<wx> {
        @Inject
        com.vungle.publisher.acf.a a;
        @Inject
        Provider<wx> d;

        protected final /* synthetic */ vs b() {
            return (wx) this.d.get();
        }

        @Inject
        a() {
        }

        public final wx a(List<gm> list) {
            wx wxVar = (wx) d();
            acf com_vungle_publisher_acf = (acf) this.a.a.get();
            com_vungle_publisher_acf.a = list;
            wxVar.d = com_vungle_publisher_acf.d();
            return wxVar;
        }

        protected final String a() {
            return "api/v1/sdkErrors";
        }
    }

    @Inject
    wx() {
    }

    protected final c a() {
        return c.reportExceptions;
    }
}
