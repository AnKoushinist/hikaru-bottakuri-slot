package com.vungle.publisher;

import com.vungle.publisher.ahp.b;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: vungle */
public final class ajc<T> implements b<T, T> {
    private final ahs a;
    private final boolean b = false;
    private final int c;

    /* compiled from: vungle */
    static final class a<T> extends ahu<T> implements aie {
        final ahu<? super T> b;
        final com.vungle.publisher.ahs.a c;
        final aio<T> d;
        final boolean e;
        final Queue<Object> f;
        final int g;
        volatile boolean h;
        final AtomicLong i = new AtomicLong();
        final AtomicLong j = new AtomicLong();
        Throwable k;
        long l;

        public a(ahs com_vungle_publisher_ahs, ahu<? super T> com_vungle_publisher_ahu__super_T, boolean z, int i) {
            this.b = com_vungle_publisher_ahu__super_T;
            this.c = com_vungle_publisher_ahs.a();
            this.e = z;
            this.d = aio.a();
            if (i <= 0) {
                i = aka.b;
            }
            this.g = i - (i >> 2);
            if (ali.a()) {
                this.f = new alb(i);
            } else {
                this.f = new akh(i);
            }
            a((long) i);
        }

        public final void a(T t) {
            if (!this.a.b && !this.h) {
                if (this.f.offer(aio.a((Object) t))) {
                    e();
                } else {
                    a(new ahy());
                }
            }
        }

        public final void a() {
            if (!this.a.b && !this.h) {
                this.h = true;
                e();
            }
        }

        public final void a(Throwable th) {
            if (this.a.b || this.h) {
                alp.a(th);
                return;
            }
            this.k = th;
            this.h = true;
            e();
        }

        protected final void e() {
            if (this.j.getAndIncrement() == 0) {
                this.c.a(this);
            }
        }

        public final void d() {
            long j = this.l;
            Queue queue = this.f;
            ahu com_vungle_publisher_ahu = this.b;
            long j2 = 1;
            do {
                long j3 = this.i.get();
                while (j3 != j) {
                    boolean z = this.h;
                    Object poll = queue.poll();
                    boolean z2 = poll == null;
                    if (!a(z, z2, com_vungle_publisher_ahu, queue)) {
                        if (z2) {
                            break;
                        }
                        com_vungle_publisher_ahu.a(aio.c(poll));
                        long j4 = j + 1;
                        if (j4 == ((long) this.g)) {
                            j = aim.b(this.i, j4);
                            a(j4);
                            j4 = 0;
                        } else {
                            j = j3;
                        }
                        j3 = j;
                        j = j4;
                    } else {
                        return;
                    }
                }
                if (j3 != j || !a(this.h, queue.isEmpty(), com_vungle_publisher_ahu, queue)) {
                    this.l = j;
                    j2 = this.j.addAndGet(-j2);
                } else {
                    return;
                }
            } while (j2 != 0);
        }

        private boolean a(boolean z, boolean z2, ahu<? super T> com_vungle_publisher_ahu__super_T, Queue<Object> queue) {
            if (com_vungle_publisher_ahu__super_T.a.b) {
                queue.clear();
                return true;
            }
            if (z) {
                if (!this.e) {
                    Throwable th = this.k;
                    if (th != null) {
                        queue.clear();
                        try {
                            com_vungle_publisher_ahu__super_T.a(th);
                            return true;
                        } finally {
                            this.c.b();
                        }
                    } else if (z2) {
                        try {
                            com_vungle_publisher_ahu__super_T.a();
                            return true;
                        } finally {
                            this.c.b();
                        }
                    }
                } else if (z2) {
                    Throwable th2 = this.k;
                    if (th2 != null) {
                        try {
                            com_vungle_publisher_ahu__super_T.a(th2);
                        } catch (Throwable th3) {
                            this.c.b();
                        }
                    } else {
                        com_vungle_publisher_ahu__super_T.a();
                    }
                    this.c.b();
                }
            }
            return false;
        }
    }

    public final /* synthetic */ Object a(Object obj) {
        ahu com_vungle_publisher_ahu = (ahu) obj;
        if ((this.a instanceof ajm) || (this.a instanceof ajs)) {
            return com_vungle_publisher_ahu;
        }
        ahv aVar = new a(this.a, com_vungle_publisher_ahu, this.b, this.c);
        ahu com_vungle_publisher_ahu2 = aVar.b;
        com_vungle_publisher_ahu2.a(new ahr(aVar) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public final void a(long j) {
                if (j > 0) {
                    aim.a(this.a.i, j);
                    this.a.e();
                }
            }
        });
        com_vungle_publisher_ahu2.a(aVar.c);
        com_vungle_publisher_ahu2.a(aVar);
        return aVar;
    }

    public ajc(ahs com_vungle_publisher_ahs, int i) {
        this.a = com_vungle_publisher_ahs;
        if (i <= 0) {
            i = aka.b;
        }
        this.c = i;
    }
}
