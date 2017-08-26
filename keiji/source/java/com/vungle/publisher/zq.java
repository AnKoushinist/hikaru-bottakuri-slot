package com.vungle.publisher;

import com.vungle.publisher.vs.b;
import com.vungle.publisher.vs.c;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class zq extends yf {
    adn e;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.yf.a<zq> {
        @Inject
        com.vungle.publisher.adn.a g;

        public final /* synthetic */ yf a() {
            return d();
        }

        protected final /* synthetic */ vs b() {
            return new zq();
        }

        public final /* synthetic */ vs c() {
            return d();
        }

        @Inject
        a() {
        }

        public final zq d() {
            try {
                zq zqVar = (zq) super.a();
                zqVar.b = this.d + "requestAd";
                zqVar.c.putString("Content-Type", "application/json");
                adn d = this.g.d();
                zqVar.e = d;
                zqVar.d = d.d();
                return zqVar;
            } catch (Throwable e) {
                throw new sd(e);
            }
        }
    }

    protected zq() {
    }

    protected final c a() {
        return c.requestLocalAd;
    }

    protected final b b() {
        return b.GET;
    }
}
