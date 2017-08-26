package com.vungle.publisher;

import com.vungle.publisher.vs.b;
import com.vungle.publisher.vs.c;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class zt extends yf {

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.yf.a<zt> {
        @Inject
        com.vungle.publisher.adx.a g;

        protected final /* synthetic */ vs b() {
            return new zt();
        }

        @Inject
        a() {
        }

        public final zt a(String str, p pVar) {
            try {
                zt ztVar = (zt) super.a();
                ztVar.b = this.d + "requestStreamingAd";
                ztVar.c.putString("Content-Type", "application/json");
                ztVar.d = this.g.a(str, pVar).d();
                return ztVar;
            } catch (Throwable e) {
                throw new sd(e);
            }
        }
    }

    protected zt() {
    }

    protected final c a() {
        return c.requestStreamingAd;
    }

    protected final b b() {
        return b.GET;
    }
}
