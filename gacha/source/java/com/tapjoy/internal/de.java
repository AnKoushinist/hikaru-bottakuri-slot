package com.tapjoy.internal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

public final class de {
    private static final Logger a = Logger.getLogger(de.class.getName());
    private final Queue b = new LinkedList();
    private boolean c = false;

    static class a {
        final Runnable a;
        final Executor b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
        r6 = this;
        r1 = r6.b;
        monitor-enter(r1);
        r0 = r6.c;	 Catch:{ all -> 0x004b }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x004b }
    L_0x0008:
        return;
    L_0x0009:
        r0 = 1;
        r6.c = r0;	 Catch:{ all -> 0x004b }
        monitor-exit(r1);	 Catch:{ all -> 0x004b }
    L_0x000d:
        r0 = r6.b;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0008;
    L_0x0015:
        r0 = r6.b;
        r0 = r0.poll();
        r0 = (com.tapjoy.internal.de.a) r0;
        r1 = r0.b;	 Catch:{ RuntimeException -> 0x0025 }
        r2 = r0.a;	 Catch:{ RuntimeException -> 0x0025 }
        r1.execute(r2);	 Catch:{ RuntimeException -> 0x0025 }
        goto L_0x000d;
    L_0x0025:
        r1 = move-exception;
        r2 = a;
        r3 = java.util.logging.Level.SEVERE;
        r4 = new java.lang.StringBuilder;
        r5 = "RuntimeException while executing runnable ";
        r4.<init>(r5);
        r5 = r0.a;
        r4 = r4.append(r5);
        r5 = " with executor ";
        r4 = r4.append(r5);
        r0 = r0.b;
        r0 = r4.append(r0);
        r0 = r0.toString();
        r2.log(r3, r0, r1);
        goto L_0x000d;
    L_0x004b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x004b }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.de.a():void");
    }
}
