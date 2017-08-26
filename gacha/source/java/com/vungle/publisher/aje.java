package com.vungle.publisher;

import com.vungle.publisher.ail.AnonymousClass1;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: vungle */
public final class aje<R> implements com.vungle.publisher.ahp.b<R, ahp<?>[]> {
    final aik<? extends R> a;

    /* compiled from: vungle */
    static final class a<R> extends AtomicLong {
        static final int c = ((int) (((double) aka.b) * 0.7d));
        final ahq<? super R> a;
        final amc b = new amc();
        int d;
        volatile Object[] e;
        AtomicLong f;
        private final aik<? extends R> g;

        /* compiled from: vungle */
        final class a extends ahu {
            final aka b = aka.d();
            final /* synthetic */ a c;

            a(a aVar) {
                this.c = aVar;
            }

            public final void k_() {
                a((long) aka.b);
            }

            public final void a() {
                aka com_vungle_publisher_aka = this.b;
                if (com_vungle_publisher_aka.a == null) {
                    com_vungle_publisher_aka.a = aio.b();
                }
                this.c.a();
            }

            public final void a(Throwable th) {
                this.c.a.a(th);
            }

            public final void a(Object obj) {
                try {
                    this.b.a(obj);
                } catch (Throwable e) {
                    a(e);
                }
                this.c.a();
            }
        }

        public a(ahu<? super R> com_vungle_publisher_ahu__super_R, aik<? extends R> com_vungle_publisher_aik__extends_R) {
            this.a = com_vungle_publisher_ahu__super_R;
            this.g = com_vungle_publisher_aik__extends_R;
            com_vungle_publisher_ahu__super_R.a(this.b);
        }

        final void a() {
            Object[] objArr = this.e;
            if (objArr != null && getAndIncrement() == 0) {
                int length = objArr.length;
                ahq com_vungle_publisher_ahq = this.a;
                AtomicLong atomicLong = this.f;
                while (true) {
                    Object h;
                    Object obj = new Object[length];
                    int i = 1;
                    int i2 = 0;
                    while (i2 < length) {
                        int i3;
                        h = ((a) objArr[i2]).b.h();
                        if (h == null) {
                            i3 = 0;
                        } else if (aka.b(h)) {
                            com_vungle_publisher_ahq.a();
                            this.b.b();
                            return;
                        } else {
                            obj[i2] = aka.c(h);
                            i3 = i;
                        }
                        i2++;
                        i = i3;
                    }
                    if (atomicLong.get() > 0 && i != 0) {
                        try {
                            com_vungle_publisher_ahq.a(this.g.a(obj));
                            atomicLong.decrementAndGet();
                            this.d++;
                            for (Object h2 : objArr) {
                                aka com_vungle_publisher_aka = ((a) h2).b;
                                com_vungle_publisher_aka.g();
                                if (aka.b(com_vungle_publisher_aka.h())) {
                                    com_vungle_publisher_ahq.a();
                                    this.b.b();
                                    return;
                                }
                            }
                            if (this.d > c) {
                                for (Object h22 : objArr) {
                                    ((a) h22).a((long) this.d);
                                }
                                this.d = 0;
                            }
                        } catch (Throwable th) {
                            ahx.a(th, com_vungle_publisher_ahq, obj);
                            return;
                        }
                    } else if (decrementAndGet() <= 0) {
                        return;
                    }
                }
            }
        }
    }

    /* compiled from: vungle */
    static final class b<R> extends AtomicLong implements ahr {
        final a<R> a;

        public b(a<R> aVar) {
            this.a = aVar;
        }

        public final void a(long j) {
            aim.a((AtomicLong) this, j);
            this.a.a();
        }
    }

    /* compiled from: vungle */
    final class c extends ahu<ahp[]> {
        final ahu<? super R> b;
        final a<R> c;
        final b<R> d;
        boolean e;
        final /* synthetic */ aje f;

        public final /* synthetic */ void a(Object obj) {
            int i = 0;
            ahp[] com_vungle_publisher_ahpArr = (ahp[]) obj;
            if (com_vungle_publisher_ahpArr == null || com_vungle_publisher_ahpArr.length == 0) {
                this.b.a();
                return;
            }
            this.e = true;
            a aVar = this.c;
            AtomicLong atomicLong = this.d;
            Object[] objArr = new Object[com_vungle_publisher_ahpArr.length];
            for (int i2 = 0; i2 < com_vungle_publisher_ahpArr.length; i2++) {
                ahv aVar2 = new a(aVar);
                objArr[i2] = aVar2;
                aVar.b.a(aVar2);
            }
            aVar.f = atomicLong;
            aVar.e = objArr;
            while (i < com_vungle_publisher_ahpArr.length) {
                com_vungle_publisher_ahpArr[i].a((a) objArr[i]);
                i++;
            }
        }

        public c(aje com_vungle_publisher_aje, ahu<? super R> com_vungle_publisher_ahu__super_R, a<R> aVar, b<R> bVar) {
            this.f = com_vungle_publisher_aje;
            this.b = com_vungle_publisher_ahu__super_R;
            this.c = aVar;
            this.d = bVar;
        }

        public final void a() {
            if (!this.e) {
                this.b.a();
            }
        }

        public final void a(Throwable th) {
            this.b.a(th);
        }
    }

    public aje(aij com_vungle_publisher_aij) {
        this.a = new AnonymousClass1(com_vungle_publisher_aij);
    }

    private ahu<? super ahp[]> a(ahu<? super R> com_vungle_publisher_ahu__super_R) {
        a aVar = new a(com_vungle_publisher_ahu__super_R, this.a);
        ahr bVar = new b(aVar);
        ahv cVar = new c(this, com_vungle_publisher_ahu__super_R, aVar, bVar);
        com_vungle_publisher_ahu__super_R.a(cVar);
        com_vungle_publisher_ahu__super_R.a(bVar);
        return cVar;
    }
}
