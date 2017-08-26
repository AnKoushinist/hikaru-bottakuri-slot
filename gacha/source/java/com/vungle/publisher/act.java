package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class act extends acz {

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.acz.a<act, if, ia, ig, hu> {
        @Inject
        a g;
        @Inject
        com.vungle.publisher.adx.a h;

        @Singleton
        /* compiled from: vungle */
        static class a extends com.vungle.publisher.aes.a<ia, ig> {
            @Inject
            a a;

            @Singleton
            /* compiled from: vungle */
            static class a extends com.vungle.publisher.acb.b.a<ig> {
                @Inject
                a() {
                }
            }

            @Inject
            a() {
            }
        }

        public final /* synthetic */ ace a(cq cqVar) {
            if ifVar = (if) cqVar;
            act com_vungle_publisher_act = (act) super.a(ifVar);
            if (com_vungle_publisher_act != null) {
                com_vungle_publisher_act.t = ((ht) ((hu) ifVar.h()).u()).c;
            }
            return com_vungle_publisher_act;
        }

        protected final /* synthetic */ Object b() {
            return new act();
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.acb.a c() {
            return this.g;
        }

        @Inject
        a() {
        }
    }
}
