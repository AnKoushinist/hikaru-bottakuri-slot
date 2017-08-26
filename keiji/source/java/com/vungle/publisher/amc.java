package com.vungle.publisher;

import java.util.HashSet;
import java.util.Set;

/* compiled from: vungle */
public final class amc implements ahv {
    public volatile boolean a;
    private Set<ahv> b;

    public final boolean c() {
        return this.a;
    }

    public final void a(ahv com_vungle_publisher_ahv) {
        if (!com_vungle_publisher_ahv.c()) {
            if (!this.a) {
                synchronized (this) {
                    if (!this.a) {
                        if (this.b == null) {
                            this.b = new HashSet(4);
                        }
                        this.b.add(com_vungle_publisher_ahv);
                        return;
                    }
                }
            }
            com_vungle_publisher_ahv.b();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(com.vungle.publisher.ahv r2) {
        /*
        r1 = this;
        r0 = r1.a;
        if (r0 != 0) goto L_0x000e;
    L_0x0004:
        monitor-enter(r1);
        r0 = r1.a;	 Catch:{ all -> 0x001c }
        if (r0 != 0) goto L_0x000d;
    L_0x0009:
        r0 = r1.b;	 Catch:{ all -> 0x001c }
        if (r0 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
    L_0x000e:
        return;
    L_0x000f:
        r0 = r1.b;	 Catch:{ all -> 0x001c }
        r0 = r0.remove(r2);	 Catch:{ all -> 0x001c }
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        if (r0 == 0) goto L_0x000e;
    L_0x0018:
        r2.b();
        goto L_0x000e;
    L_0x001c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.amc.b(com.vungle.publisher.ahv):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
        r4 = this;
        r1 = 0;
        r0 = r4.a;
        if (r0 != 0) goto L_0x000b;
    L_0x0005:
        monitor-enter(r4);
        r0 = r4.a;	 Catch:{ all -> 0x0038 }
        if (r0 == 0) goto L_0x000c;
    L_0x000a:
        monitor-exit(r4);	 Catch:{ all -> 0x0038 }
    L_0x000b:
        return;
    L_0x000c:
        r0 = 1;
        r4.a = r0;	 Catch:{ all -> 0x0038 }
        r0 = r4.b;	 Catch:{ all -> 0x0038 }
        r2 = 0;
        r4.b = r2;	 Catch:{ all -> 0x0038 }
        monitor-exit(r4);	 Catch:{ all -> 0x0038 }
        if (r0 == 0) goto L_0x000b;
    L_0x0017:
        r2 = r0.iterator();
    L_0x001b:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x003b;
    L_0x0021:
        r0 = r2.next();
        r0 = (com.vungle.publisher.ahv) r0;
        r0.b();	 Catch:{ Throwable -> 0x002b }
        goto L_0x001b;
    L_0x002b:
        r3 = move-exception;
        if (r1 != 0) goto L_0x003f;
    L_0x002e:
        r0 = new java.util.ArrayList;
        r0.<init>();
    L_0x0033:
        r0.add(r3);
        r1 = r0;
        goto L_0x001b;
    L_0x0038:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0038 }
        throw r0;
    L_0x003b:
        com.vungle.publisher.ahx.a(r1);
        goto L_0x000b;
    L_0x003f:
        r0 = r1;
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.amc.b():void");
    }
}
