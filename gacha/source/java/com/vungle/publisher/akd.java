package com.vungle.publisher;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* compiled from: vungle */
public final class akd implements ahv {
    public List<ahv> a;
    public volatile boolean b;

    public akd(ahv... com_vungle_publisher_ahvArr) {
        this.a = new LinkedList(Arrays.asList(com_vungle_publisher_ahvArr));
    }

    public akd(ahv com_vungle_publisher_ahv) {
        this.a = new LinkedList();
        this.a.add(com_vungle_publisher_ahv);
    }

    public final boolean c() {
        return this.b;
    }

    public final void a(ahv com_vungle_publisher_ahv) {
        if (!com_vungle_publisher_ahv.c()) {
            if (!this.b) {
                synchronized (this) {
                    if (!this.b) {
                        List list = this.a;
                        if (list == null) {
                            list = new LinkedList();
                            this.a = list;
                        }
                        list.add(com_vungle_publisher_ahv);
                        return;
                    }
                }
            }
            com_vungle_publisher_ahv.b();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
        r4 = this;
        r1 = 0;
        r0 = r4.b;
        if (r0 != 0) goto L_0x000b;
    L_0x0005:
        monitor-enter(r4);
        r0 = r4.b;	 Catch:{ all -> 0x0038 }
        if (r0 == 0) goto L_0x000c;
    L_0x000a:
        monitor-exit(r4);	 Catch:{ all -> 0x0038 }
    L_0x000b:
        return;
    L_0x000c:
        r0 = 1;
        r4.b = r0;	 Catch:{ all -> 0x0038 }
        r0 = r4.a;	 Catch:{ all -> 0x0038 }
        r2 = 0;
        r4.a = r2;	 Catch:{ all -> 0x0038 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.akd.b():void");
    }
}
