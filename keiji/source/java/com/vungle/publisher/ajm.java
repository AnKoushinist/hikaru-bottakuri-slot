package com.vungle.publisher;

import java.util.concurrent.TimeUnit;

/* compiled from: vungle */
public final class ajm extends ahs {
    public static final ajm b = new ajm();

    /* compiled from: vungle */
    final class a extends com.vungle.publisher.ahs.a implements ahv {
        final amb a = new amb();
        final /* synthetic */ ajm b;

        a(ajm com_vungle_publisher_ajm) {
            this.b = com_vungle_publisher_ajm;
        }

        public final ahv a(aie com_vungle_publisher_aie, long j, TimeUnit timeUnit) {
            return a(new ajr(com_vungle_publisher_aie, this, ahs.b() + timeUnit.toMillis(j)));
        }

        public final ahv a(aie com_vungle_publisher_aie) {
            com_vungle_publisher_aie.d();
            return ame.a();
        }

        public final void b() {
            this.a.b();
        }

        public final boolean c() {
            return this.a.c();
        }
    }

    private ajm() {
    }

    public final com.vungle.publisher.ahs.a a() {
        return new a(this);
    }
}
