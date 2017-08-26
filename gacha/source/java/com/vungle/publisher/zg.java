package com.vungle.publisher;

import com.vungle.publisher.vs.b;
import com.vungle.publisher.vs.c;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class zg extends yf {
    boolean e;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.yf.a<zg> {
        @Inject
        zg g;
        @Inject
        adi h;

        public final /* synthetic */ yf a() {
            return d();
        }

        protected final /* bridge */ /* synthetic */ vs b() {
            return this.g;
        }

        public final /* synthetic */ vs c() {
            return d();
        }

        @Inject
        a() {
        }

        public final zg d() {
            try {
                if (this.g.e) {
                    return this.g;
                }
                zg zgVar = (zg) super.a();
                zgVar.b = this.d + "config";
                zgVar.c.putString("Content-Type", "application/json");
                zgVar.d = this.h.d();
                return zgVar;
            } catch (Throwable e) {
                throw new sd(e);
            }
        }
    }

    @Inject
    zg() {
    }

    protected final b b() {
        return b.GET;
    }

    protected final c a() {
        return c.requestConfig;
    }
}
