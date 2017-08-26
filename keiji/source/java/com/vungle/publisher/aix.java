package com.vungle.publisher;

import com.vungle.publisher.ahp.a;
import java.util.concurrent.TimeUnit;

/* compiled from: vungle */
public final class aix implements a<Long> {
    final long a;
    final TimeUnit b;
    final ahs c;

    public final /* synthetic */ void a(Object obj) {
        final ahu com_vungle_publisher_ahu = (ahu) obj;
        ahv a = this.c.a();
        com_vungle_publisher_ahu.a(a);
        a.a(new aie(this) {
            final /* synthetic */ aix b;

            public final void d() {
                try {
                    com_vungle_publisher_ahu.a((Object) Long.valueOf(0));
                    com_vungle_publisher_ahu.a();
                } catch (Throwable th) {
                    ahx.a(th, com_vungle_publisher_ahu);
                }
            }
        }, this.a, this.b);
    }

    public aix(long j, TimeUnit timeUnit, ahs com_vungle_publisher_ahs) {
        this.a = j;
        this.b = timeUnit;
        this.c = com_vungle_publisher_ahs;
    }
}
