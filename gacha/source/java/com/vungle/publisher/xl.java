package com.vungle.publisher;

import com.vungle.publisher.vs.b;
import com.vungle.publisher.vs.c;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class xl extends vs {
    cj e;
    ji f;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.vs.a<xl> {
        protected final /* synthetic */ vs b() {
            return new xl();
        }

        @Inject
        a() {
        }
    }

    xl() {
    }

    protected final c a() {
        return c.trackEvent;
    }

    protected final b b() {
        return b.GET;
    }

    public final String toString() {
        return "{" + c.trackEvent + ": " + this.b + "}";
    }
}
