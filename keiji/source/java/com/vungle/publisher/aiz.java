package com.vungle.publisher;

import com.vungle.publisher.akf.AnonymousClass1;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: vungle */
public final class aiz<T> implements com.vungle.publisher.ahp.b<List<T>, T> {
    final int a;
    final int b;

    /* compiled from: vungle */
    static final class a<T> extends ahu<T> {
        final ahu<? super List<T>> b;
        final int c;
        List<T> d;

        public a(ahu<? super List<T>> com_vungle_publisher_ahu__super_java_util_List_T, int i) {
            this.b = com_vungle_publisher_ahu__super_java_util_List_T;
            this.c = i;
            a(0);
        }

        public final void a(T t) {
            List list = this.d;
            if (list == null) {
                list = new ArrayList(this.c);
                this.d = list;
            }
            list.add(t);
            if (list.size() == this.c) {
                this.d = null;
                this.b.a((Object) list);
            }
        }

        public final void a(Throwable th) {
            this.d = null;
            this.b.a(th);
        }

        public final void a() {
            List list = this.d;
            if (list != null) {
                this.b.a((Object) list);
            }
            this.b.a();
        }
    }

    /* compiled from: vungle */
    static final class b<T> extends ahu<T> {
        final ahu<? super List<T>> b;
        final int c;
        final int d;
        long e;
        final ArrayDeque<List<T>> f = new ArrayDeque();
        final AtomicLong g = new AtomicLong();
        long h;

        /* compiled from: vungle */
        final class a extends AtomicBoolean implements ahr {
            final /* synthetic */ b a;

            a(b bVar) {
                this.a = bVar;
            }

            public final void a(long j) {
                b bVar = this.a;
                AtomicLong atomicLong = bVar.g;
                Queue queue = bVar.f;
                ahu com_vungle_publisher_ahu = bVar.b;
                aii anonymousClass1 = new AnonymousClass1();
                if (j < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + j);
                }
                Object obj;
                if (j == 0) {
                    obj = (atomicLong.get() & Long.MIN_VALUE) == 0 ? 1 : null;
                } else {
                    long j2;
                    long j3;
                    do {
                        j2 = atomicLong.get();
                        j3 = Long.MIN_VALUE & j2;
                    } while (!atomicLong.compareAndSet(j2, aim.b(Long.MAX_VALUE & j2, j) | j3));
                    if (j2 == Long.MIN_VALUE) {
                        aim.a(atomicLong, queue, com_vungle_publisher_ahu, anonymousClass1);
                        obj = null;
                    } else {
                        obj = j3 == 0 ? 1 : null;
                    }
                }
                if (obj != null && j != 0) {
                    if (get() || !compareAndSet(false, true)) {
                        bVar.a(aim.a((long) bVar.d, j));
                    } else {
                        bVar.a(aim.b(aim.a((long) bVar.d, j - 1), (long) bVar.c));
                    }
                }
            }
        }

        public b(ahu<? super List<T>> com_vungle_publisher_ahu__super_java_util_List_T, int i, int i2) {
            this.b = com_vungle_publisher_ahu__super_java_util_List_T;
            this.c = i;
            this.d = i2;
            a(0);
        }

        public final void a(T t) {
            long j = this.e;
            if (j == 0) {
                this.f.offer(new ArrayList(this.c));
            }
            j++;
            if (j == ((long) this.d)) {
                this.e = 0;
            } else {
                this.e = j;
            }
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                ((List) it.next()).add(t);
            }
            List list = (List) this.f.peek();
            if (list != null && list.size() == this.c) {
                this.f.poll();
                this.h++;
                this.b.a((Object) list);
            }
        }

        public final void a(Throwable th) {
            this.f.clear();
            this.b.a(th);
        }

        public final void a() {
            long j = this.h;
            if (j != 0) {
                if (j > this.g.get()) {
                    this.b.a(new ahy("More produced than requested? " + j));
                    return;
                }
                this.g.addAndGet(-j);
            }
            aim.a(this.g, this.f, this.b);
        }
    }

    /* compiled from: vungle */
    static final class c<T> extends ahu<T> {
        final ahu<? super List<T>> b;
        final int c;
        final int d;
        long e;
        List<T> f;

        /* compiled from: vungle */
        final class a extends AtomicBoolean implements ahr {
            final /* synthetic */ c a;

            a(c cVar) {
                this.a = cVar;
            }

            public final void a(long j) {
                if (j < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + j);
                } else if (j != 0) {
                    c cVar = this.a;
                    if (get() || !compareAndSet(false, true)) {
                        cVar.a(aim.a(j, (long) cVar.d));
                    } else {
                        cVar.a(aim.b(aim.a(j, (long) cVar.c), aim.a((long) (cVar.d - cVar.c), j - 1)));
                    }
                }
            }
        }

        public c(ahu<? super List<T>> com_vungle_publisher_ahu__super_java_util_List_T, int i, int i2) {
            this.b = com_vungle_publisher_ahu__super_java_util_List_T;
            this.c = i;
            this.d = i2;
            a(0);
        }

        public final void a(T t) {
            long j = this.e;
            List list = this.f;
            if (j == 0) {
                list = new ArrayList(this.c);
                this.f = list;
            }
            j++;
            if (j == ((long) this.d)) {
                this.e = 0;
            } else {
                this.e = j;
            }
            if (list != null) {
                list.add(t);
                if (list.size() == this.c) {
                    this.f = null;
                    this.b.a((Object) list);
                }
            }
        }

        public final void a(Throwable th) {
            this.f = null;
            this.b.a(th);
        }

        public final void a() {
            List list = this.f;
            if (list != null) {
                this.f = null;
                this.b.a((Object) list);
            }
            this.b.a();
        }
    }

    public aiz(int i, int i2) {
        if (i <= 0) {
            throw new IllegalArgumentException("count must be greater than 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("skip must be greater than 0");
        } else {
            this.a = i;
            this.b = i2;
        }
    }

    private ahu<? super T> a(ahu<? super List<T>> com_vungle_publisher_ahu__super_java_util_List_T) {
        ahv aVar;
        if (this.b == this.a) {
            aVar = new a(com_vungle_publisher_ahu__super_java_util_List_T, this.a);
            com_vungle_publisher_ahu__super_java_util_List_T.a(aVar);
            com_vungle_publisher_ahu__super_java_util_List_T.a(new ahr(aVar) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final void a(long j) {
                    if (j < 0) {
                        throw new IllegalArgumentException("n >= required but it was " + j);
                    } else if (j != 0) {
                        this.a.a(aim.a(j, (long) this.a.c));
                    }
                }
            });
            return aVar;
        } else if (this.b > this.a) {
            aVar = new c(com_vungle_publisher_ahu__super_java_util_List_T, this.a, this.b);
            com_vungle_publisher_ahu__super_java_util_List_T.a(aVar);
            com_vungle_publisher_ahu__super_java_util_List_T.a(new a(aVar));
            return aVar;
        } else {
            aVar = new b(com_vungle_publisher_ahu__super_java_util_List_T, this.a, this.b);
            com_vungle_publisher_ahu__super_java_util_List_T.a(aVar);
            com_vungle_publisher_ahu__super_java_util_List_T.a(new a(aVar));
            return aVar;
        }
    }
}
