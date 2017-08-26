package com.vungle.publisher;

import com.vungle.log.Logger;

/* compiled from: vungle */
public final class xj implements Runnable {
    private final we a;

    public xj(we weVar) {
        this.a = weVar;
    }

    public final void run() {
        try {
            Logger.d(Logger.NETWORK_TAG, "executing " + this.a);
            this.a.a();
        } catch (Throwable e) {
            Logger.e(Logger.NETWORK_TAG, "error processing transaction: " + this.a, e);
        }
    }
}
