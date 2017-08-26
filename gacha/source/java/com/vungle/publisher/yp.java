package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class yp extends yg<ack, fj> {

    @Singleton
    /* compiled from: vungle */
    static class a extends com.vungle.publisher.yg.a<ack, yp, fj> {
        @Inject
        com.vungle.publisher.ack.a g;

        protected final /* synthetic */ vs b() {
            return new yp();
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.ace.a d() {
            return this.g;
        }

        protected final /* synthetic */ yg e() {
            return new yp();
        }

        @Inject
        a() {
        }
    }
}
