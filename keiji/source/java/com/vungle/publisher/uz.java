package com.vungle.publisher;

import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class uz extends vo {
    @Inject
    pv a;
    @Inject
    agt b;

    @Inject
    uz() {
    }

    protected final void a(we weVar, vy vyVar) {
        super.a(weVar, vyVar);
        long currentTimeMillis = System.currentTimeMillis();
        Logger.d(Logger.DATA_TAG, "sent fingerprint at time: " + currentTimeMillis);
        this.a.a(currentTimeMillis);
    }
}
