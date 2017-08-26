package com.vungle.publisher;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: vungle */
final class ama<T> extends AtomicReference<a<T>> implements com.vungle.publisher.ahp.a<T> {
    volatile Object a;
    boolean b = true;
    aif<b<T>> c = aig.a();
    aif<b<T>> d = aig.a();
    aif<b<T>> e = aig.a();
    public final aio<T> f = aio.a();

    /* compiled from: vungle */
    public static final class a<T> {
        static final b[] c = new b[0];
        static final a d = new a(true, c);
        static final a e = new a(false, c);
        final boolean a;
        final b[] b;

        public a(boolean z, b[] bVarArr) {
            this.a = z;
            this.b = bVarArr;
        }
    }

    /* compiled from: vungle */
    public static final class b<T> implements ahq<T> {
        final ahu<? super T> a;
        boolean b = true;
        boolean c;
        List<Object> d;
        boolean e;

        public b(ahu<? super T> com_vungle_publisher_ahu__super_T) {
            this.a = com_vungle_publisher_ahu__super_T;
        }

        public final void a(T t) {
            this.a.a((Object) t);
        }

        public final void a(Throwable th) {
            this.a.a(th);
        }

        public final void a() {
            this.a.a();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        final void b(java.lang.Object r2) {
            /*
            r1 = this;
            r0 = r1.e;
            if (r0 != 0) goto L_0x0022;
        L_0x0004:
            monitor-enter(r1);
            r0 = 0;
            r1.b = r0;	 Catch:{ all -> 0x0028 }
            r0 = r1.c;	 Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x001e;
        L_0x000c:
            r0 = r1.d;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x0017;
        L_0x0010:
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0028 }
            r0.<init>();	 Catch:{ all -> 0x0028 }
            r1.d = r0;	 Catch:{ all -> 0x0028 }
        L_0x0017:
            r0 = r1.d;	 Catch:{ all -> 0x0028 }
            r0.add(r2);	 Catch:{ all -> 0x0028 }
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
        L_0x001d:
            return;
        L_0x001e:
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
            r0 = 1;
            r1.e = r0;
        L_0x0022:
            r0 = r1.a;
            com.vungle.publisher.aio.a(r0, r2);
            goto L_0x001d;
        L_0x0028:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.ama.b.b(java.lang.Object):void");
        }

        final void c(Object obj) {
            if (obj != null) {
                aio.a(this.a, obj);
            }
        }
    }

    public final /* synthetic */ void a(Object obj) {
        ahu com_vungle_publisher_ahu = (ahu) obj;
        final b bVar = new b(com_vungle_publisher_ahu);
        com_vungle_publisher_ahu.a(ame.a(new aie(this) {
            final /* synthetic */ ama b;

            public final void d() {
                this.b.a(bVar);
            }
        }));
        this.c.a(bVar);
        if (!com_vungle_publisher_ahu.a.b) {
            int i;
            a aVar;
            Object obj2;
            do {
                aVar = (a) get();
                if (aVar.a) {
                    this.e.a(bVar);
                    i = 0;
                    break;
                }
                int length = aVar.b.length;
                obj2 = new b[(length + 1)];
                System.arraycopy(aVar.b, 0, obj2, 0, length);
                obj2[length] = bVar;
            } while (!compareAndSet(aVar, new a(aVar.a, obj2)));
            this.d.a(bVar);
            i = 1;
            if (i != 0 && com_vungle_publisher_ahu.a.b) {
                a(bVar);
            }
        }
    }

    public ama() {
        super(a.e);
    }

    final void a(b<T> bVar) {
        a aVar;
        a aVar2;
        do {
            aVar = (a) get();
            if (!aVar.a) {
                b[] bVarArr = aVar.b;
                int length = bVarArr.length;
                if (length == 1 && bVarArr[0] == bVar) {
                    aVar2 = a.e;
                } else if (length == 0) {
                    aVar2 = aVar;
                } else {
                    Object obj = new b[(length - 1)];
                    int i = 0;
                    int i2 = 0;
                    while (i < length) {
                        int i3;
                        b<T> bVar2 = bVarArr[i];
                        if (bVar2 == bVar) {
                            i3 = i2;
                        } else if (i2 == length - 1) {
                            aVar2 = aVar;
                            break;
                        } else {
                            i3 = i2 + 1;
                            obj[i2] = bVar2;
                        }
                        i++;
                        i2 = i3;
                    }
                    if (i2 == 0) {
                        aVar2 = a.e;
                    } else {
                        b[] bVarArr2;
                        if (i2 < length - 1) {
                            bVarArr2 = new b[i2];
                            System.arraycopy(obj, 0, bVarArr2, 0, i2);
                        } else {
                            Object obj2 = obj;
                        }
                        aVar2 = new a(aVar.a, bVarArr2);
                    }
                }
                if (aVar2 == aVar) {
                    return;
                }
            } else {
                return;
            }
        } while (!compareAndSet(aVar, aVar2));
    }

    final b<T>[] b(Object obj) {
        this.a = obj;
        this.b = false;
        if (((a) get()).a) {
            return a.c;
        }
        return ((a) getAndSet(a.d)).b;
    }
}
