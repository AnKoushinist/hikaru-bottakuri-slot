package com.vungle.publisher;

import com.vungle.publisher.vs.b;
import com.vungle.publisher.vs.c;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class aac extends yf {

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.yf.a<aac> {
        @Inject
        com.vungle.publisher.aei.a g;

        protected final /* synthetic */ vs b() {
            return new aac();
        }

        @Inject
        a() {
        }

        protected final aac a(long j, long j2) {
            aac com_vungle_publisher_aac = (aac) a();
            com_vungle_publisher_aac.c.putString("Content-Type", "application/json");
            com_vungle_publisher_aac.b = this.d + "sessionEnd";
            ael a = this.g.a.a(j);
            aei com_vungle_publisher_aei = new aei();
            com_vungle_publisher_aei.b = a;
            com_vungle_publisher_aei.a = Long.valueOf(j2);
            com_vungle_publisher_aac.d = com_vungle_publisher_aei.d();
            return com_vungle_publisher_aac;
        }
    }

    protected aac() {
    }

    protected final c a() {
        return c.sessionEnd;
    }

    protected final b b() {
        return b.POST;
    }
}
