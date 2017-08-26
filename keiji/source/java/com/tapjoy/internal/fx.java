package com.tapjoy.internal;

import java.io.File;

public final class fx implements Runnable {
    final gl a;
    ci b;
    private final Object c = this.a;
    private final Thread d;
    private boolean e;

    public fx(File file) {
        this.a = new gl(file);
        new Object[1][0] = Integer.valueOf(this.a.b());
        this.d = new Thread(this, "5Rocks");
        this.d.start();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r13 = this;
        r12 = 100;
        r6 = 0;
        r1 = 1;
        r4 = 0;
        r0 = r1;
    L_0x0007:
        r2 = r6;
    L_0x0008:
        r5 = r13.b;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        if (r5 == 0) goto L_0x0119;
    L_0x000c:
        r5 = r13.a;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r5 = r5.b();	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        if (r5 <= 0) goto L_0x0119;
    L_0x0014:
        r5 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r5 > 0) goto L_0x0119;
    L_0x0018:
        r5 = r13.a;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r5 = r5.b();	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r8 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        if (r5 <= r8) goto L_0x002f;
    L_0x0022:
        r5 = r13.a;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r8 = r13.a;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r8 = r8.b();	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r8 = r8 + -10000;
        r5.a(r8);	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
    L_0x002f:
        r5 = r13.a;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r8 = 0;
        r5 = r5.b(r8);	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        if (r5 == 0) goto L_0x0119;
    L_0x0038:
        r2 = r5.w;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        if (r2 == 0) goto L_0x0049;
    L_0x003c:
        r2 = r2.G;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        if (r2 != 0) goto L_0x0049;
    L_0x0040:
        r2 = com.tapjoy.internal.gn.c;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r8 = 3;
        r3 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r2.await(r8, r3);	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
    L_0x0049:
        r2 = com.tapjoy.internal.y.c();	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        if (r2 != 0) goto L_0x0058;
    L_0x004f:
        r2 = com.tapjoy.internal.gn.b;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r8 = 3;
        r3 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r2.await(r8, r3);	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
    L_0x0058:
        r2 = r13.e;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        if (r2 != 0) goto L_0x0078;
    L_0x005c:
        r2 = r5.n;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r3 = com.tapjoy.internal.eb.APP;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        if (r2 == r3) goto L_0x0078;
    L_0x0062:
        r2 = r13.a;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r2 = r2.b();	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        if (r2 >= r12) goto L_0x0078;
    L_0x006a:
        r2 = r5.p;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r2 = r2.longValue();	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r8 = java.lang.System.currentTimeMillis();	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r2 <= 0) goto L_0x00ad;
    L_0x0078:
        r2 = r6;
    L_0x0079:
        r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r8 > 0) goto L_0x0008;
    L_0x007d:
        r8 = new com.tapjoy.internal.hk;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r8.<init>();	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r8.a(r5);	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r9 = 1;
        r9 = new java.lang.Object[r9];	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r10 = 0;
        r9[r10] = r5;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r5 = r1;
    L_0x008c:
        if (r5 >= r12) goto L_0x00ca;
    L_0x008e:
        r9 = r13.a;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r9 = r9.b();	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        if (r5 >= r9) goto L_0x00ca;
    L_0x0096:
        r9 = r13.a;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r9 = r9.b(r5);	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        if (r9 == 0) goto L_0x00ca;
    L_0x009e:
        r10 = r8.a(r9);	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        if (r10 == 0) goto L_0x00ca;
    L_0x00a4:
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r11 = 0;
        r10[r11] = r9;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r5 = r5 + 1;
        goto L_0x008c;
    L_0x00ad:
        r2 = r5.p;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r2 = r2.longValue();	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r8 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r2 = r2 + r8;
        r8 = java.lang.System.currentTimeMillis();	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r2 = r2 - r8;
        r8 = 0;
        r2 = java.lang.Math.max(r2, r8);	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r8 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r2 = java.lang.Math.min(r2, r8);	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        goto L_0x0079;
    L_0x00ca:
        r5 = r0 + 1;
        r0 = 2;
        r0 = new java.lang.Object[r0];	 Catch:{ Exception -> 0x0100, InterruptedException -> 0x013d }
        r9 = 0;
        r10 = r8.g();	 Catch:{ Exception -> 0x0100, InterruptedException -> 0x013d }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x0100, InterruptedException -> 0x013d }
        r0[r9] = r10;	 Catch:{ Exception -> 0x0100, InterruptedException -> 0x013d }
        r9 = 1;
        r10 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x0100, InterruptedException -> 0x013d }
        r0[r9] = r10;	 Catch:{ Exception -> 0x0100, InterruptedException -> 0x013d }
        r0 = r13.b;	 Catch:{ Exception -> 0x0100, InterruptedException -> 0x013d }
        r0.a(r8);	 Catch:{ Exception -> 0x0100, InterruptedException -> 0x013d }
        r0 = r13.a;	 Catch:{ Exception -> 0x0100, InterruptedException -> 0x013d }
        r9 = r8.g();	 Catch:{ Exception -> 0x0100, InterruptedException -> 0x013d }
        r0.a(r9);	 Catch:{ Exception -> 0x0100, InterruptedException -> 0x013d }
        r0 = 1;
        r0 = new java.lang.Object[r0];	 Catch:{ Exception -> 0x015e, InterruptedException -> 0x013d }
        r5 = 0;
        r9 = r8.g();	 Catch:{ Exception -> 0x015e, InterruptedException -> 0x013d }
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Exception -> 0x015e, InterruptedException -> 0x013d }
        r0[r5] = r9;	 Catch:{ Exception -> 0x015e, InterruptedException -> 0x013d }
        r0 = r4;
        goto L_0x0008;
    L_0x0100:
        r0 = move-exception;
        r2 = r0;
        r0 = r5;
    L_0x0103:
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r5 = 0;
        r8 = r8.g();	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r3[r5] = r8;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r5 = 1;
        r3[r5] = r2;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r2 = 300000; // 0x493e0 float:4.2039E-40 double:1.482197E-318;
        goto L_0x0008;
    L_0x0119:
        r5 = r13.a;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r5.flush();	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r5 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r5 <= 0) goto L_0x013f;
    L_0x0122:
        r5 = r13.c;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        monitor-enter(r5);	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r8 = 0;
        r13.e = r8;	 Catch:{ all -> 0x013a }
        r8 = 1;
        r8 = new java.lang.Object[r8];	 Catch:{ all -> 0x013a }
        r9 = 0;
        r10 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x013a }
        r8[r9] = r10;	 Catch:{ all -> 0x013a }
        r8 = r13.c;	 Catch:{ all -> 0x013a }
        r8.wait(r2);	 Catch:{ all -> 0x013a }
        monitor-exit(r5);	 Catch:{ all -> 0x013a }
        goto L_0x0007;
    L_0x013a:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x013a }
        throw r0;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
    L_0x013d:
        r0 = move-exception;
    L_0x013e:
        return;
    L_0x013f:
        r2 = r13.c;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        monitor-enter(r2);	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
        r3 = 0;
        r13.e = r3;	 Catch:{ all -> 0x0159 }
        r3 = r13.b;	 Catch:{ all -> 0x0159 }
        if (r3 == 0) goto L_0x0151;
    L_0x0149:
        r3 = r13.a;	 Catch:{ all -> 0x0159 }
        r3 = r3.c();	 Catch:{ all -> 0x0159 }
        if (r3 == 0) goto L_0x0156;
    L_0x0151:
        r3 = r13.c;	 Catch:{ all -> 0x0159 }
        r3.wait();	 Catch:{ all -> 0x0159 }
    L_0x0156:
        monitor-exit(r2);	 Catch:{ all -> 0x0159 }
        goto L_0x0007;
    L_0x0159:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0159 }
        throw r0;	 Catch:{ InterruptedException -> 0x013d, Exception -> 0x015c }
    L_0x015c:
        r0 = move-exception;
        goto L_0x013e;
    L_0x015e:
        r0 = move-exception;
        r2 = r0;
        r0 = r4;
        goto L_0x0103;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.fx.run():void");
    }

    final void a(boolean z) {
        synchronized (this.c) {
            this.e = z;
            this.c.notify();
        }
    }

    public final void a() {
        if (this.b != null && !this.a.c()) {
            a(true);
        }
    }
}
