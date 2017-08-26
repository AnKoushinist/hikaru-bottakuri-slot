package com.vungle.publisher;

import com.tapjoy.TapjoyConstants;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: vungle */
public final class akc<T> extends ahp<T> {
    static final boolean c = Boolean.valueOf(System.getProperty("rx.just.strong-mode", TapjoyConstants.TJC_FALSE)).booleanValue();
    public final T b;

    /* compiled from: vungle */
    static final class a<T> implements com.vungle.publisher.ahp.a<T> {
        final T a;

        public final /* bridge */ /* synthetic */ void a(Object obj) {
            ahu com_vungle_publisher_ahu = (ahu) obj;
            com_vungle_publisher_ahu.a(akc.a(com_vungle_publisher_ahu, this.a));
        }

        a(T t) {
            this.a = t;
        }
    }

    /* compiled from: vungle */
    static final class b<T> implements com.vungle.publisher.ahp.a<T> {
        final T a;
        final aii<aie, ahv> b;

        public final /* synthetic */ void a(Object obj) {
            ahu com_vungle_publisher_ahu = (ahu) obj;
            com_vungle_publisher_ahu.a(new c(com_vungle_publisher_ahu, this.a, this.b));
        }

        b(T t, aii<aie, ahv> com_vungle_publisher_aii_com_vungle_publisher_aie__com_vungle_publisher_ahv) {
            this.a = t;
            this.b = com_vungle_publisher_aii_com_vungle_publisher_aie__com_vungle_publisher_ahv;
        }
    }

    /* compiled from: vungle */
    static final class c<T> extends AtomicBoolean implements ahr, aie {
        final ahu<? super T> a;
        final T b;
        final aii<aie, ahv> c;

        public c(ahu<? super T> com_vungle_publisher_ahu__super_T, T t, aii<aie, ahv> com_vungle_publisher_aii_com_vungle_publisher_aie__com_vungle_publisher_ahv) {
            this.a = com_vungle_publisher_ahu__super_T;
            this.b = t;
            this.c = com_vungle_publisher_aii_com_vungle_publisher_aie__com_vungle_publisher_ahv;
        }

        public final void a(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            } else if (j != 0 && compareAndSet(false, true)) {
                this.a.a((ahv) this.c.a(this));
            }
        }

        public final void d() {
            ahq com_vungle_publisher_ahq = this.a;
            if (!com_vungle_publisher_ahq.a.b) {
                Object obj = this.b;
                try {
                    com_vungle_publisher_ahq.a(obj);
                    if (!com_vungle_publisher_ahq.a.b) {
                        com_vungle_publisher_ahq.a();
                    }
                } catch (Throwable th) {
                    ahx.a(th, com_vungle_publisher_ahq, obj);
                }
            }
        }

        public final String toString() {
            return "ScalarAsyncProducer[" + this.b + ", " + get() + "]";
        }
    }

    /* compiled from: vungle */
    static final class d<T> implements ahr {
        final ahu<? super T> a;
        final T b;
        boolean c;

        public d(ahu<? super T> com_vungle_publisher_ahu__super_T, T t) {
            this.a = com_vungle_publisher_ahu__super_T;
            this.b = t;
        }

        public final void a(long j) {
            if (!this.c) {
                if (j < 0) {
                    throw new IllegalStateException("n >= required but it was " + j);
                } else if (j != 0) {
                    this.c = true;
                    ahq com_vungle_publisher_ahq = this.a;
                    if (!com_vungle_publisher_ahq.a.b) {
                        Object obj = this.b;
                        try {
                            com_vungle_publisher_ahq.a(obj);
                            if (!com_vungle_publisher_ahq.a.b) {
                                com_vungle_publisher_ahq.a();
                            }
                        } catch (Throwable th) {
                            ahx.a(th, com_vungle_publisher_ahq, obj);
                        }
                    }
                }
            }
        }
    }

    static <T> ahr a(ahu<? super T> com_vungle_publisher_ahu__super_T, T t) {
        if (c) {
            return new ajh(com_vungle_publisher_ahu__super_T, t);
        }
        return new d(com_vungle_publisher_ahu__super_T, t);
    }

    public static <T> akc<T> a(T t) {
        return new akc(t);
    }

    private akc(T t) {
        super(alp.a(new a(t)));
        this.b = t;
    }

    public final ahp<T> a(final ahs com_vungle_publisher_ahs) {
        aii anonymousClass1;
        if (com_vungle_publisher_ahs instanceof ajj) {
            final ajj com_vungle_publisher_ajj = (ajj) com_vungle_publisher_ahs;
            anonymousClass1 = new aii<aie, ahv>(this) {
                final /* synthetic */ akc b;

                public final /* synthetic */ Object a(Object obj) {
                    return ((com.vungle.publisher.ajj.b) com_vungle_publisher_ajj.f.get()).a().b((aie) obj, -1, TimeUnit.NANOSECONDS);
                }
            };
        } else {
            anonymousClass1 = new aii<aie, ahv>(this) {
                final /* synthetic */ akc b;

                public final /* synthetic */ Object a(Object obj) {
                    final aie com_vungle_publisher_aie = (aie) obj;
                    final com.vungle.publisher.ahs.a a = com_vungle_publisher_ahs.a();
                    a.a(new aie(this) {
                        final /* synthetic */ AnonymousClass2 c;

                        public final void d() {
                            try {
                                com_vungle_publisher_aie.d();
                            } finally {
                                a.b();
                            }
                        }
                    });
                    return a;
                }
            };
        }
        return ahp.a(new b(this.b, anonymousClass1));
    }

    public final <R> ahp<R> d(final aii<? super T, ? extends ahp<? extends R>> com_vungle_publisher_aii__super_T___extends_com_vungle_publisher_ahp__extends_R) {
        return ahp.a(new com.vungle.publisher.ahp.a<R>(this) {
            final /* synthetic */ akc b;

            public final /* bridge */ /* synthetic */ void a(Object obj) {
                ahu com_vungle_publisher_ahu = (ahu) obj;
                ahp com_vungle_publisher_ahp = (ahp) com_vungle_publisher_aii__super_T___extends_com_vungle_publisher_ahp__extends_R.a(this.b.b);
                if (com_vungle_publisher_ahp instanceof akc) {
                    com_vungle_publisher_ahu.a(akc.a(com_vungle_publisher_ahu, ((akc) com_vungle_publisher_ahp).b));
                } else {
                    com_vungle_publisher_ahp.a(alm.a(com_vungle_publisher_ahu));
                }
            }
        });
    }
}
