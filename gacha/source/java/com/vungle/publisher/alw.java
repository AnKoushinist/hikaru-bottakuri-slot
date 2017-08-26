package com.vungle.publisher;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: vungle */
public final class alw {
    private static final AtomicReference<alw> d = new AtomicReference();
    private final ahs a = alt.a();
    private final ahs b;
    private final ahs c;

    private static alw d() {
        alw com_vungle_publisher_alw;
        while (true) {
            com_vungle_publisher_alw = (alw) d.get();
            if (com_vungle_publisher_alw == null) {
                com_vungle_publisher_alw = new alw();
                if (d.compareAndSet(null, com_vungle_publisher_alw)) {
                    break;
                }
                com_vungle_publisher_alw.e();
            } else {
                break;
            }
        }
        return com_vungle_publisher_alw;
    }

    private alw() {
        als.a().f();
        alt.d();
        alt.e();
        this.b = alt.b();
        alt.f();
        this.c = alt.c();
    }

    public static ahs a() {
        return ajs.b;
    }

    public static ahs b() {
        return alp.a(d().a);
    }

    public static ahs c() {
        return alp.b(d().b);
    }

    private synchronized void e() {
        if (this.a instanceof ajq) {
            ((ajq) this.a).c();
        }
        if (this.b instanceof ajq) {
            ((ajq) this.b).c();
        }
        if (this.c instanceof ajq) {
            ((ajq) this.c).c();
        }
    }
}
