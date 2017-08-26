package com.vungle.publisher;

import com.vungle.publisher.vs.b;
import com.vungle.publisher.vs.c;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class vi extends vs {

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.vs.a<vi> implements aii<gg<?>, ahp<vi>> {
        public final /* synthetic */ Object a(Object obj) {
            Object obj2 = (vi) c();
            obj2.b = ((gg) obj).f();
            return akc.a(obj2);
        }

        protected final /* synthetic */ vs b() {
            return new vi();
        }

        @Inject
        a() {
        }
    }

    vi() {
    }

    protected final b b() {
        return b.GET;
    }

    protected final c a() {
        return c.download;
    }

    public final String toString() {
        return "{" + c.download + ": " + this.b + "}";
    }
}
