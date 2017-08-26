package com.vungle.publisher;

import com.vungle.publisher.ahp.a;
import com.vungle.publisher.ama.b;
import java.util.ArrayList;
import java.util.List;

/* compiled from: vungle */
public final class alx<T> extends alz<T, T> {
    private static final Object[] b = new Object[0];
    private final ama<T> c;
    private final aio<T> d = aio.a();

    public static <T> alx<T> b() {
        final Object com_vungle_publisher_ama = new ama();
        com_vungle_publisher_ama.d = new aif<b<T>>() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final /* synthetic */ void a(java.lang.Object r7) {
                /*
                r6 = this;
                r0 = 0;
                r1 = 1;
                r2 = 0;
                r7 = (com.vungle.publisher.ama.b) r7;
                r3 = r0;
                r4 = r3.a;
                monitor-enter(r7);
                r3 = r7.b;	 Catch:{ all -> 0x0040 }
                if (r3 == 0) goto L_0x0012;
            L_0x000e:
                r3 = r7.c;	 Catch:{ all -> 0x0040 }
                if (r3 == 0) goto L_0x0014;
            L_0x0012:
                monitor-exit(r7);	 Catch:{ all -> 0x0040 }
            L_0x0013:
                return;
            L_0x0014:
                r3 = 0;
                r7.b = r3;	 Catch:{ all -> 0x0040 }
                if (r4 == 0) goto L_0x003e;
            L_0x0019:
                r3 = r1;
            L_0x001a:
                r7.c = r3;	 Catch:{ all -> 0x0040 }
                monitor-exit(r7);	 Catch:{ all -> 0x0040 }
                if (r4 == 0) goto L_0x0013;
            L_0x001f:
                r3 = r0;
                r0 = r1;
            L_0x0021:
                if (r3 == 0) goto L_0x0043;
            L_0x0023:
                r3 = r3.iterator();	 Catch:{ all -> 0x0035 }
            L_0x0027:
                r5 = r3.hasNext();	 Catch:{ all -> 0x0035 }
                if (r5 == 0) goto L_0x0043;
            L_0x002d:
                r5 = r3.next();	 Catch:{ all -> 0x0035 }
                r7.c(r5);	 Catch:{ all -> 0x0035 }
                goto L_0x0027;
            L_0x0035:
                r0 = move-exception;
            L_0x0036:
                if (r2 != 0) goto L_0x003d;
            L_0x0038:
                monitor-enter(r7);
                r1 = 0;
                r7.c = r1;	 Catch:{ all -> 0x0061 }
                monitor-exit(r7);	 Catch:{ all -> 0x0061 }
            L_0x003d:
                throw r0;
            L_0x003e:
                r3 = r2;
                goto L_0x001a;
            L_0x0040:
                r0 = move-exception;
                monitor-exit(r7);	 Catch:{ all -> 0x0040 }
                throw r0;
            L_0x0043:
                if (r0 == 0) goto L_0x0049;
            L_0x0045:
                r7.c(r4);	 Catch:{ all -> 0x0035 }
                r0 = r2;
            L_0x0049:
                monitor-enter(r7);	 Catch:{ all -> 0x0035 }
                r3 = r7.d;	 Catch:{ all -> 0x005e }
                r5 = 0;
                r7.d = r5;	 Catch:{ all -> 0x005e }
                if (r3 != 0) goto L_0x005c;
            L_0x0051:
                r0 = 0;
                r7.c = r0;	 Catch:{ all -> 0x005e }
                monitor-exit(r7);	 Catch:{ all -> 0x0056 }
                goto L_0x0013;
            L_0x0056:
                r0 = move-exception;
            L_0x0057:
                monitor-exit(r7);	 Catch:{ all -> 0x0056 }
                throw r0;	 Catch:{ all -> 0x0059 }
            L_0x0059:
                r0 = move-exception;
                r2 = r1;
                goto L_0x0036;
            L_0x005c:
                monitor-exit(r7);	 Catch:{ all -> 0x005e }
                goto L_0x0021;
            L_0x005e:
                r0 = move-exception;
                r1 = r2;
                goto L_0x0057;
            L_0x0061:
                r0 = move-exception;
                monitor-exit(r7);	 Catch:{ all -> 0x0061 }
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.alx.1.a(java.lang.Object):void");
            }
        };
        com_vungle_publisher_ama.e = com_vungle_publisher_ama.d;
        return new alx(com_vungle_publisher_ama, com_vungle_publisher_ama);
    }

    private alx(a<T> aVar, ama<T> com_vungle_publisher_ama_T) {
        super(aVar);
        this.c = com_vungle_publisher_ama_T;
    }

    public final void a() {
        if (this.c.a == null || this.c.b) {
            Object b = aio.b();
            for (b b2 : this.c.b(b)) {
                b2.b(b);
            }
        }
    }

    public final void a(Throwable th) {
        if (this.c.a == null || this.c.b) {
            Object a = aio.a(th);
            List list = null;
            for (b b : this.c.b(a)) {
                try {
                    b.b(a);
                } catch (Throwable th2) {
                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(th2);
                }
            }
            ahx.a(list);
        }
    }

    public final void a(T t) {
        if (this.c.a == null || this.c.b) {
            Object a = aio.a((Object) t);
            ama com_vungle_publisher_ama = this.c;
            com_vungle_publisher_ama.a = a;
            for (b b : ((ama.a) com_vungle_publisher_ama.get()).b) {
                b.b(a);
            }
        }
    }
}
