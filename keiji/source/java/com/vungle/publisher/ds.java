package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.cj.c;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ds {
    dn<?> a;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        Provider<ds> a;

        @Inject
        a() {
        }

        public final ds a(dn<?> dnVar) {
            ds dsVar = (ds) this.a.get();
            dsVar.a = dnVar;
            return dsVar;
        }
    }

    @Inject
    ds() {
    }

    public final void a(c cVar, c cVar2) {
        if (cVar2 != cVar && cVar2 != c.failed) {
            Logger.v(Logger.PREPARE_TAG, "resetting prepare_retry_count from " + this.a.i() + " to 0 for " + this.a.z());
            this.a.j();
        }
    }
}
