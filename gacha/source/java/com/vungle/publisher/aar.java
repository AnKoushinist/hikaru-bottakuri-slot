package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class aar extends vo {
    @Inject
    py a;

    @Inject
    aar() {
    }

    protected final void a(we weVar, vy vyVar) {
        super.a(weVar, vyVar);
        this.a.l.edit().putBoolean("IsVgAppInstalled", true).apply();
    }
}
