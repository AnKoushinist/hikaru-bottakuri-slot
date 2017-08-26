package com.vungle.publisher;

import com.vungle.publisher.ahp.b;
import java.util.concurrent.TimeUnit;

/* compiled from: vungle */
public final class aja<T> implements b<T, T> {
    final long a;
    final TimeUnit b;
    final ahs c;

    public aja(long j, TimeUnit timeUnit, ahs com_vungle_publisher_ahs) {
        this.a = j;
        this.b = timeUnit;
        this.c = com_vungle_publisher_ahs;
    }

    private ahu<? super T> a(final ahu<? super T> com_vungle_publisher_ahu__super_T) {
        final ahv a = this.c.a();
        com_vungle_publisher_ahu__super_T.a(a);
        return new ahu<T>(this, com_vungle_publisher_ahu__super_T) {
            boolean b;
            final /* synthetic */ aja e;

            public final void a() {
                a.a(new aie(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public final void d() {
                        if (!this.a.b) {
                            this.a.b = true;
                            com_vungle_publisher_ahu__super_T.a();
                        }
                    }
                }, this.e.a, this.e.b);
            }

            public final void a(final Throwable th) {
                a.a(new aie(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public final void d() {
                        if (!this.b.b) {
                            this.b.b = true;
                            com_vungle_publisher_ahu__super_T.a(th);
                            a.b();
                        }
                    }
                });
            }

            public final void a(final T t) {
                a.a(new aie(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public final void d() {
                        if (!this.b.b) {
                            com_vungle_publisher_ahu__super_T.a(t);
                        }
                    }
                }, this.e.a, this.e.b);
            }
        };
    }
}
