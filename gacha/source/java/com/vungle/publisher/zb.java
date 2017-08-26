package com.vungle.publisher;

import com.vungle.publisher.bt.b;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class zb extends br {
    @Inject
    a b;

    @Singleton
    /* compiled from: vungle */
    static class a implements Runnable {
        @Inject
        yc a;

        @Inject
        a() {
        }

        public final void run() {
            this.a.a();
        }
    }

    @Inject
    zb() {
    }

    protected final b b() {
        return b.requestConfig;
    }

    protected final Runnable a() {
        return this.b;
    }
}
