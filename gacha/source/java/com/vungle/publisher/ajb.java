package com.vungle.publisher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: vungle */
public final class ajb<T> implements com.vungle.publisher.ahp.b<T, ahp<? extends T>> {
    final boolean a = false;
    final int b = Integer.MAX_VALUE;

    /* compiled from: vungle */
    public static final class a {
        public static final ajb<Object> a = new ajb();
    }

    /* compiled from: vungle */
    static final class b<T> extends ahu<T> {
        static final int g = (aka.b / 4);
        final d<T> b;
        final long c;
        volatile boolean d;
        volatile aka e;
        int f;

        public b(d<T> dVar, long j) {
            this.b = dVar;
            this.c = j;
        }

        public final void k_() {
            this.f = aka.b;
            a((long) aka.b);
        }

        public final void a(T t) {
            long j;
            Object obj;
            Throwable th;
            Object obj2 = null;
            d dVar = this.b;
            if (dVar.e.get() != 0) {
                synchronized (dVar) {
                    j = dVar.e.get();
                    if (dVar.k || j == 0) {
                        obj = null;
                    } else {
                        dVar.k = true;
                        obj = 1;
                    }
                }
            } else {
                j = 0;
                obj = null;
            }
            if (obj != null) {
                aka com_vungle_publisher_aka = this.e;
                if (com_vungle_publisher_aka == null || com_vungle_publisher_aka.f()) {
                    try {
                        dVar.b.a((Object) t);
                    } catch (Throwable th2) {
                        th = th2;
                        obj2 = 1;
                    }
                    if (j != Long.MAX_VALUE) {
                        dVar.e.addAndGet(-1);
                    }
                    b(1);
                    synchronized (dVar) {
                        if (dVar.l) {
                            dVar.l = false;
                            dVar.g();
                            return;
                        }
                        dVar.k = false;
                        return;
                    }
                }
                d.a(this, t);
                dVar.g();
                return;
            }
            d.a(this, t);
            dVar.f();
            return;
            if (obj2 == null) {
                synchronized (dVar) {
                    dVar.k = false;
                }
            }
            throw th;
        }

        public final void a(Throwable th) {
            this.d = true;
            this.b.e().offer(th);
            this.b.f();
        }

        public final void a() {
            this.d = true;
            this.b.f();
        }

        public final void b(long j) {
            int i = this.f - ((int) j);
            if (i > g) {
                this.f = i;
                return;
            }
            this.f = aka.b;
            i = aka.b - i;
            if (i > 0) {
                a((long) i);
            }
        }
    }

    /* compiled from: vungle */
    static final class c<T> extends AtomicLong implements ahr {
        final d<T> a;

        public c(d<T> dVar) {
            this.a = dVar;
        }

        public final void a(long j) {
            if (j > 0) {
                if (get() != Long.MAX_VALUE) {
                    aim.a((AtomicLong) this, j);
                    this.a.f();
                }
            } else if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
        }
    }

    /* compiled from: vungle */
    static final class d<T> extends ahu<ahp<? extends T>> {
        static final b<?>[] r = new b[0];
        final ahu<? super T> b;
        final boolean c;
        final int d;
        c<T> e;
        volatile Queue<Object> f;
        volatile amc g;
        volatile ConcurrentLinkedQueue<Throwable> h;
        final aio<T> i = aio.a();
        volatile boolean j;
        boolean k;
        boolean l;
        final Object m = new Object();
        volatile b<?>[] n = r;
        long o;
        long p;
        int q;
        final int s;
        int t;

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ void a(java.lang.Object r9) {
            /*
            r8 = this;
            r6 = 0;
            r2 = 1;
            r1 = 0;
            r9 = (com.vungle.publisher.ahp) r9;
            if (r9 == 0) goto L_0x001c;
        L_0x0008:
            r0 = com.vungle.publisher.ain.a();
            if (r9 != r0) goto L_0x0020;
        L_0x000e:
            r0 = r8.t;
            r0 = r0 + 1;
            r2 = r8.s;
            if (r0 != r2) goto L_0x001d;
        L_0x0016:
            r8.t = r1;
            r0 = (long) r0;
            r8.a(r0);
        L_0x001c:
            return;
        L_0x001d:
            r8.t = r0;
            goto L_0x001c;
        L_0x0020:
            r0 = r9 instanceof com.vungle.publisher.akc;
            if (r0 == 0) goto L_0x00cb;
        L_0x0024:
            r9 = (com.vungle.publisher.akc) r9;
            r3 = r9.b;
            r0 = r8.e;
            r4 = r0.get();
            r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r0 == 0) goto L_0x0100;
        L_0x0032:
            monitor-enter(r8);
            r0 = r8.e;	 Catch:{ all -> 0x008d }
            r4 = r0.get();	 Catch:{ all -> 0x008d }
            r0 = r8.k;	 Catch:{ all -> 0x008d }
            if (r0 != 0) goto L_0x00fd;
        L_0x003d:
            r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r0 == 0) goto L_0x00fd;
        L_0x0041:
            r0 = 1;
            r8.k = r0;	 Catch:{ all -> 0x008d }
            r0 = r2;
        L_0x0045:
            monitor-exit(r8);	 Catch:{ all -> 0x008d }
        L_0x0046:
            if (r0 == 0) goto L_0x00c3;
        L_0x0048:
            r0 = r8.f;
            if (r0 == 0) goto L_0x0052;
        L_0x004c:
            r0 = r0.isEmpty();
            if (r0 == 0) goto L_0x00bb;
        L_0x0052:
            r0 = r8.b;	 Catch:{ Throwable -> 0x0090 }
            r0.a(r3);	 Catch:{ Throwable -> 0x0090 }
        L_0x0057:
            r6 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r0 == 0) goto L_0x0067;
        L_0x0060:
            r0 = r8.e;	 Catch:{ all -> 0x00aa }
            r4 = -1;
            r0.addAndGet(r4);	 Catch:{ all -> 0x00aa }
        L_0x0067:
            r0 = r8.t;	 Catch:{ all -> 0x00aa }
            r0 = r0 + 1;
            r3 = r8.s;	 Catch:{ all -> 0x00aa }
            if (r0 != r3) goto L_0x00ac;
        L_0x006f:
            r3 = 0;
            r8.t = r3;	 Catch:{ all -> 0x00aa }
            r4 = (long) r0;	 Catch:{ all -> 0x00aa }
            r8.a(r4);	 Catch:{ all -> 0x00aa }
        L_0x0076:
            monitor-enter(r8);	 Catch:{ all -> 0x00aa }
            r0 = r8.l;	 Catch:{ all -> 0x0080 }
            if (r0 != 0) goto L_0x00af;
        L_0x007b:
            r0 = 0;
            r8.k = r0;	 Catch:{ all -> 0x0080 }
            monitor-exit(r8);	 Catch:{ all -> 0x0080 }
            goto L_0x001c;
        L_0x0080:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x0080 }
            throw r0;	 Catch:{ all -> 0x0083 }
        L_0x0083:
            r0 = move-exception;
            r1 = r2;
        L_0x0085:
            if (r1 != 0) goto L_0x008c;
        L_0x0087:
            monitor-enter(r8);
            r1 = 0;
            r8.k = r1;	 Catch:{ all -> 0x00b8 }
            monitor-exit(r8);	 Catch:{ all -> 0x00b8 }
        L_0x008c:
            throw r0;
        L_0x008d:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x008d }
            throw r0;
        L_0x0090:
            r0 = move-exception;
            r3 = r8.c;	 Catch:{ all -> 0x00aa }
            if (r3 != 0) goto L_0x00a2;
        L_0x0095:
            com.vungle.publisher.ahx.b(r0);	 Catch:{ all -> 0x00aa }
            r1 = r8.a;	 Catch:{ all -> 0x0083 }
            r1.b();	 Catch:{ all -> 0x0083 }
            r8.a(r0);	 Catch:{ all -> 0x0083 }
            goto L_0x001c;
        L_0x00a2:
            r3 = r8.e();	 Catch:{ all -> 0x00aa }
            r3.offer(r0);	 Catch:{ all -> 0x00aa }
            goto L_0x0057;
        L_0x00aa:
            r0 = move-exception;
            goto L_0x0085;
        L_0x00ac:
            r8.t = r0;	 Catch:{ all -> 0x00aa }
            goto L_0x0076;
        L_0x00af:
            r0 = 0;
            r8.l = r0;	 Catch:{ all -> 0x0080 }
            monitor-exit(r8);	 Catch:{ all -> 0x0080 }
            r8.g();
            goto L_0x001c;
        L_0x00b8:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x00b8 }
            throw r0;
        L_0x00bb:
            r8.b(r3);
            r8.g();
            goto L_0x001c;
        L_0x00c3:
            r8.b(r3);
            r8.f();
            goto L_0x001c;
        L_0x00cb:
            r0 = new com.vungle.publisher.ajb$b;
            r2 = r8.o;
            r4 = 1;
            r4 = r4 + r2;
            r8.o = r4;
            r0.<init>(r8, r2);
            r1 = r8.h();
            r1.a(r0);
            r1 = r8.m;
            monitor-enter(r1);
            r2 = r8.n;	 Catch:{ all -> 0x00fa }
            r3 = r2.length;	 Catch:{ all -> 0x00fa }
            r4 = r3 + 1;
            r4 = new com.vungle.publisher.ajb.b[r4];	 Catch:{ all -> 0x00fa }
            r5 = 0;
            r6 = 0;
            java.lang.System.arraycopy(r2, r5, r4, r6, r3);	 Catch:{ all -> 0x00fa }
            r4[r3] = r0;	 Catch:{ all -> 0x00fa }
            r8.n = r4;	 Catch:{ all -> 0x00fa }
            monitor-exit(r1);	 Catch:{ all -> 0x00fa }
            r9.a(r0);
            r8.f();
            goto L_0x001c;
        L_0x00fa:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x00fa }
            throw r0;
        L_0x00fd:
            r0 = r1;
            goto L_0x0045;
        L_0x0100:
            r4 = r6;
            r0 = r1;
            goto L_0x0046;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.ajb.d.a(java.lang.Object):void");
        }

        public d(ahu<? super T> com_vungle_publisher_ahu__super_T, boolean z, int i) {
            this.b = com_vungle_publisher_ahu__super_T;
            this.c = z;
            this.d = i;
            if (i == Integer.MAX_VALUE) {
                this.s = Integer.MAX_VALUE;
                a(Long.MAX_VALUE);
                return;
            }
            this.s = Math.max(1, i >> 1);
            a((long) i);
        }

        final Queue<Throwable> e() {
            Queue<Throwable> queue = this.h;
            if (queue == null) {
                synchronized (this) {
                    queue = this.h;
                    if (queue == null) {
                        queue = new ConcurrentLinkedQueue();
                        this.h = queue;
                    }
                }
            }
            return queue;
        }

        private amc h() {
            amc com_vungle_publisher_amc = this.g;
            if (com_vungle_publisher_amc == null) {
                Object obj;
                synchronized (this) {
                    amc com_vungle_publisher_amc2 = this.g;
                    if (com_vungle_publisher_amc2 == null) {
                        com_vungle_publisher_amc2 = new amc();
                        this.g = com_vungle_publisher_amc2;
                        com_vungle_publisher_amc = com_vungle_publisher_amc2;
                        obj = 1;
                    } else {
                        com_vungle_publisher_amc = com_vungle_publisher_amc2;
                        obj = null;
                    }
                }
                if (obj != null) {
                    a((ahv) com_vungle_publisher_amc);
                }
            }
            return com_vungle_publisher_amc;
        }

        private void i() {
            Collection arrayList = new ArrayList(this.h);
            if (arrayList.size() == 1) {
                this.b.a((Throwable) arrayList.get(0));
            } else {
                this.b.a(new ahw(arrayList, (byte) 0));
            }
        }

        public final void a(Throwable th) {
            e().offer(th);
            this.j = true;
            f();
        }

        public final void a() {
            this.j = true;
            f();
        }

        static void a(b<T> bVar, T t) {
            aka com_vungle_publisher_aka = bVar.e;
            if (com_vungle_publisher_aka == null) {
                com_vungle_publisher_aka = aka.a();
                bVar.a((ahv) com_vungle_publisher_aka);
                bVar.e = com_vungle_publisher_aka;
            }
            try {
                com_vungle_publisher_aka.a(aio.a((Object) t));
            } catch (Throwable e) {
                bVar.a.b();
                bVar.a(e);
            } catch (Throwable e2) {
                if (!bVar.a.b) {
                    bVar.a.b();
                    bVar.a(e2);
                }
            }
        }

        private void b(T t) {
            Queue queue = this.f;
            if (queue == null) {
                int i = this.d;
                if (i == Integer.MAX_VALUE) {
                    queue = new akj(aka.b);
                } else {
                    if ((((i + -1) & i) == 0 ? 1 : null) == null) {
                        queue = new aki(i);
                    } else if (ali.a()) {
                        queue = new alb(i);
                    } else {
                        queue = new akh(i);
                    }
                }
                this.f = queue;
            }
            if (!queue.offer(aio.a((Object) t))) {
                this.a.b();
                a(aic.a(new ahy(), t));
            }
        }

        final void f() {
            synchronized (this) {
                if (this.k) {
                    this.l = true;
                    return;
                }
                this.k = true;
                g();
            }
        }

        final void g() {
            Object obj = null;
            ahu com_vungle_publisher_ahu = this.b;
            while (!j()) {
                Object obj2;
                Object obj3;
                int i;
                Queue queue = this.f;
                long j = this.e.get();
                if (j == Long.MAX_VALUE) {
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                int i2 = 0;
                if (queue != null) {
                    do {
                        i = 0;
                        obj3 = null;
                        while (j > 0) {
                            obj3 = queue.poll();
                            if (!j()) {
                                if (obj3 == null) {
                                    break;
                                }
                                try {
                                    com_vungle_publisher_ahu.a(aio.c(obj3));
                                } catch (Throwable th) {
                                    if (obj == null) {
                                        synchronized (this) {
                                            this.k = false;
                                        }
                                    }
                                }
                                j--;
                                i++;
                                i2++;
                            } else {
                                return;
                            }
                        }
                        if (i > 0) {
                            if (obj2 != null) {
                                j = Long.MAX_VALUE;
                            } else {
                                j = this.e.addAndGet((long) (-i));
                            }
                        }
                        if (j == 0) {
                            break;
                        }
                    } while (obj3 != null);
                }
                long j2 = j;
                int i3 = i2;
                boolean z = this.j;
                Queue queue2 = this.f;
                b[] bVarArr = this.n;
                int length = bVarArr.length;
                if (z && ((queue2 == null || queue2.isEmpty()) && length == 0)) {
                    Queue queue3 = this.h;
                    if (queue3 == null || queue3.isEmpty()) {
                        com_vungle_publisher_ahu.a();
                        return;
                    } else {
                        i();
                        return;
                    }
                }
                Object obj4;
                int i4;
                if (length > 0) {
                    int i5;
                    long j3 = this.p;
                    i2 = this.q;
                    if (length <= i2 || bVarArr[i2].c != j3) {
                        if (length <= i2) {
                            i2 = 0;
                        }
                        for (i5 = 0; i5 < length && bVarArr[i2].c != j3; i5++) {
                            i2++;
                            if (i2 == length) {
                                i2 = 0;
                            }
                        }
                        this.q = i2;
                        this.p = bVarArr[i2].c;
                    }
                    int i6 = 0;
                    i5 = i2;
                    long j4 = j2;
                    i = i3;
                    obj3 = null;
                    j = j4;
                    while (i6 < length) {
                        if (!j()) {
                            b bVar = bVarArr[i5];
                            obj4 = null;
                            do {
                                int i7 = 0;
                                while (j > 0) {
                                    if (!j()) {
                                        aka com_vungle_publisher_aka = bVar.e;
                                        if (com_vungle_publisher_aka == null) {
                                            break;
                                        }
                                        obj4 = com_vungle_publisher_aka.g();
                                        if (obj4 == null) {
                                            break;
                                        }
                                        try {
                                            com_vungle_publisher_ahu.a(aio.c(obj4));
                                            i7++;
                                            j--;
                                        } catch (Throwable th2) {
                                            obj = 1;
                                            ahx.b(th2);
                                            com_vungle_publisher_ahu.a(th2);
                                            return;
                                        } finally {
                                            this.a.b();
                                        }
                                    } else {
                                        return;
                                    }
                                }
                                if (i7 > 0) {
                                    if (obj2 == null) {
                                        j = this.e.addAndGet((long) (-i7));
                                    } else {
                                        j = Long.MAX_VALUE;
                                    }
                                    bVar.b((long) i7);
                                }
                                if (j == 0) {
                                    break;
                                }
                            } while (obj4 != null);
                            z = bVar.d;
                            aka com_vungle_publisher_aka2 = bVar.e;
                            if (z && (com_vungle_publisher_aka2 == null || com_vungle_publisher_aka2.f())) {
                                aka com_vungle_publisher_aka3 = bVar.e;
                                if (com_vungle_publisher_aka3 != null) {
                                    com_vungle_publisher_aka3.e();
                                }
                                this.g.b(bVar);
                                synchronized (this.m) {
                                    Object obj5 = this.n;
                                    int length2 = obj5.length;
                                    i2 = 0;
                                    while (i2 < length2) {
                                        if (bVar.equals(obj5[i2])) {
                                            break;
                                        }
                                        i2++;
                                    }
                                    i2 = -1;
                                    if (i2 < 0) {
                                    } else if (length2 == 1) {
                                        this.n = r;
                                    } else {
                                        obj3 = new b[(length2 - 1)];
                                        System.arraycopy(obj5, 0, obj3, 0, i2);
                                        System.arraycopy(obj5, i2 + 1, obj3, i2, (length2 - i2) - 1);
                                        this.n = obj3;
                                    }
                                }
                                if (!j()) {
                                    i++;
                                    obj3 = 1;
                                } else {
                                    return;
                                }
                            }
                            if (j == 0) {
                                break;
                            }
                            i2 = i5 + 1;
                            if (i2 == length) {
                                i2 = 0;
                            }
                            i6++;
                            i5 = i2;
                        } else {
                            return;
                        }
                    }
                    obj4 = obj3;
                    i4 = i;
                    this.q = i5;
                    this.p = bVarArr[i5].c;
                } else {
                    obj4 = null;
                    i4 = i3;
                }
                if (i4 > 0) {
                    a((long) i4);
                }
                if (obj4 == null) {
                    synchronized (this) {
                        if (this.l) {
                            this.l = false;
                        } else {
                            this.k = false;
                            return;
                        }
                    }
                }
            }
        }

        private boolean j() {
            if (this.b.a.b) {
                return true;
            }
            Queue queue = this.h;
            if (this.c || queue == null || queue.isEmpty()) {
                return false;
            }
            try {
                i();
                return true;
            } finally {
                this.a.b();
            }
        }
    }

    ajb() {
    }

    private ahu<ahp<? extends T>> a(ahu<? super T> com_vungle_publisher_ahu__super_T) {
        ahv dVar = new d(com_vungle_publisher_ahu__super_T, this.a, this.b);
        ahr cVar = new c(dVar);
        dVar.e = cVar;
        com_vungle_publisher_ahu__super_T.a(dVar);
        com_vungle_publisher_ahu__super_T.a(cVar);
        return dVar;
    }
}
