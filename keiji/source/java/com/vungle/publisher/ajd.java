package com.vungle.publisher;

import com.vungle.publisher.ahp.a;

/* compiled from: vungle */
public final class ajd<T> implements a<T> {
    final ahs a;
    final ahp<T> b;

    public final /* synthetic */ void a(Object obj) {
        final ahu com_vungle_publisher_ahu = (ahu) obj;
        final ahv a = this.a.a();
        com_vungle_publisher_ahu.a(a);
        a.a(new aie(this) {
            final /* synthetic */ ajd c;

            public final void d() {
                final Thread currentThread = Thread.currentThread();
                this.c.b.a(new ahu<T>(this, com_vungle_publisher_ahu) {
                    final /* synthetic */ AnonymousClass1 c;

                    public final void a(T t) {
                        com_vungle_publisher_ahu.a((Object) t);
                    }

                    public final void a(Throwable th) {
                        try {
                            com_vungle_publisher_ahu.a(th);
                        } finally {
                            a.b();
                        }
                    }

                    public final void a() {
                        try {
                            com_vungle_publisher_ahu.a();
                        } finally {
                            a.b();
                        }
                    }

                    public final void a(final ahr com_vungle_publisher_ahr) {
                        com_vungle_publisher_ahu.a(new ahr(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public final void a(final long j) {
                                if (currentThread == Thread.currentThread()) {
                                    com_vungle_publisher_ahr.a(j);
                                } else {
                                    a.a(new aie(this) {
                                        final /* synthetic */ AnonymousClass1 b;

                                        public final void d() {
                                            com_vungle_publisher_ahr.a(j);
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    public ajd(ahp<T> com_vungle_publisher_ahp_T, ahs com_vungle_publisher_ahs) {
        this.a = com_vungle_publisher_ahs;
        this.b = com_vungle_publisher_ahp_T;
    }
}
