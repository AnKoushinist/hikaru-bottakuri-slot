package com.vungle.publisher;

/* compiled from: vungle */
public final class all<T> implements ahq<T> {
    private final ahq<? super T> a;
    private boolean b;
    private volatile boolean c;
    private a d;
    private final aio<T> e = aio.a();

    /* compiled from: vungle */
    static final class a {
        Object[] a;
        int b;

        a() {
        }

        public final void a(Object obj) {
            Object[] objArr;
            int i = this.b;
            Object obj2 = this.a;
            if (obj2 == null) {
                objArr = new Object[16];
                this.a = objArr;
            } else if (i == obj2.length) {
                objArr = new Object[((i >> 2) + i)];
                System.arraycopy(obj2, 0, objArr, 0, i);
                this.a = objArr;
            } else {
                Object obj3 = obj2;
            }
            objArr[i] = obj;
            this.b = i + 1;
        }
    }

    public all(ahq<? super T> com_vungle_publisher_ahq__super_T) {
        this.a = com_vungle_publisher_ahq__super_T;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(T r8) {
        /*
        r7 = this;
        r1 = 0;
        r6 = 1;
        r0 = r7.c;
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        monitor-enter(r7);
        r0 = r7.c;	 Catch:{ all -> 0x000e }
        if (r0 == 0) goto L_0x0011;
    L_0x000c:
        monitor-exit(r7);	 Catch:{ all -> 0x000e }
        goto L_0x0006;
    L_0x000e:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x000e }
        throw r0;
    L_0x0011:
        r0 = r7.b;	 Catch:{ all -> 0x000e }
        if (r0 == 0) goto L_0x0029;
    L_0x0015:
        r0 = r7.d;	 Catch:{ all -> 0x000e }
        if (r0 != 0) goto L_0x0020;
    L_0x0019:
        r0 = new com.vungle.publisher.all$a;	 Catch:{ all -> 0x000e }
        r0.<init>();	 Catch:{ all -> 0x000e }
        r7.d = r0;	 Catch:{ all -> 0x000e }
    L_0x0020:
        r1 = com.vungle.publisher.aio.a(r8);	 Catch:{ all -> 0x000e }
        r0.a(r1);	 Catch:{ all -> 0x000e }
        monitor-exit(r7);	 Catch:{ all -> 0x000e }
        goto L_0x0006;
    L_0x0029:
        r0 = 1;
        r7.b = r0;	 Catch:{ all -> 0x000e }
        monitor-exit(r7);	 Catch:{ all -> 0x000e }
        r0 = r7.a;	 Catch:{ Throwable -> 0x003f }
        r0.a(r8);	 Catch:{ Throwable -> 0x003f }
    L_0x0032:
        monitor-enter(r7);
        r0 = r7.d;	 Catch:{ all -> 0x003c }
        if (r0 != 0) goto L_0x0048;
    L_0x0037:
        r0 = 0;
        r7.b = r0;	 Catch:{ all -> 0x003c }
        monitor-exit(r7);	 Catch:{ all -> 0x003c }
        goto L_0x0006;
    L_0x003c:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x003c }
        throw r0;
    L_0x003f:
        r0 = move-exception;
        r7.c = r6;
        r1 = r7.a;
        com.vungle.publisher.ahx.a(r0, r1, r8);
        goto L_0x0006;
    L_0x0048:
        r2 = 0;
        r7.d = r2;	 Catch:{ all -> 0x003c }
        monitor-exit(r7);	 Catch:{ all -> 0x003c }
        r2 = r0.a;
        r3 = r2.length;
        r0 = r1;
    L_0x0050:
        if (r0 >= r3) goto L_0x0032;
    L_0x0052:
        r4 = r2[r0];
        if (r4 == 0) goto L_0x0032;
    L_0x0056:
        r5 = r7.a;	 Catch:{ Throwable -> 0x0062 }
        r4 = com.vungle.publisher.aio.a(r5, r4);	 Catch:{ Throwable -> 0x0062 }
        if (r4 == 0) goto L_0x0072;
    L_0x005e:
        r0 = 1;
        r7.c = r0;	 Catch:{ Throwable -> 0x0062 }
        goto L_0x0006;
    L_0x0062:
        r0 = move-exception;
        r7.c = r6;
        com.vungle.publisher.ahx.b(r0);
        r1 = r7.a;
        r0 = com.vungle.publisher.aic.a(r0, r8);
        r1.a(r0);
        goto L_0x0006;
    L_0x0072:
        r0 = r0 + 1;
        goto L_0x0050;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.all.a(java.lang.Object):void");
    }

    public final void a(Throwable th) {
        ahx.b(th);
        if (!this.c) {
            synchronized (this) {
                if (this.c) {
                    return;
                }
                this.c = true;
                if (this.b) {
                    a aVar = this.d;
                    if (aVar == null) {
                        aVar = new a();
                        this.d = aVar;
                    }
                    aVar.a(aio.a(th));
                    return;
                }
                this.b = true;
                this.a.a(th);
            }
        }
    }

    public final void a() {
        if (!this.c) {
            synchronized (this) {
                if (this.c) {
                    return;
                }
                this.c = true;
                if (this.b) {
                    a aVar = this.d;
                    if (aVar == null) {
                        aVar = new a();
                        this.d = aVar;
                    }
                    aVar.a(aio.b());
                    return;
                }
                this.b = true;
                this.a.a();
            }
        }
    }
}
