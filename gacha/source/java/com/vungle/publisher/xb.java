package com.vungle.publisher;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class xb extends vo {
    List<gm> a;
    @Inject
    com.vungle.publisher.gm.a b;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        Provider<xb> a;

        @Inject
        a() {
        }
    }

    @Inject
    xb() {
    }

    protected final void a(we weVar, vy vyVar) {
        super.a(weVar, vyVar);
        this.b.a(this.a);
    }
}
