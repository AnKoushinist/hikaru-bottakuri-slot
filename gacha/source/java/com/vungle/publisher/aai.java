package com.vungle.publisher;

import com.vungle.publisher.vs.b;
import com.vungle.publisher.vs.c;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class aai extends yf {

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.yf.a<aai> {
        @Inject
        com.vungle.publisher.ael.a g;

        protected final /* synthetic */ vs b() {
            return new aai();
        }

        @Inject
        a() {
        }

        protected final aai a(long j) {
            aai com_vungle_publisher_aai = (aai) a();
            com_vungle_publisher_aai.c.putString("Content-Type", "application/json");
            com_vungle_publisher_aai.b = this.d + "sessionStart";
            com_vungle_publisher_aai.d = this.g.a(j).d();
            return com_vungle_publisher_aai;
        }
    }

    protected aai() {
    }

    protected final c a() {
        return c.sessionStart;
    }

    protected final b b() {
        return b.POST;
    }
}
