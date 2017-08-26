package com.tapjoy;

import android.os.Looper;
import android.os.SystemClock;
import com.tapjoy.internal.bd;
import com.tapjoy.internal.fi;
import com.tapjoy.internal.fi.AnonymousClass1;
import com.tapjoy.internal.fr;
import com.tapjoy.internal.x;
import com.tapjoy.internal.y;
import java.util.HashMap;
import java.util.Map;

public class TJPlacementManager {
    private static final bd a = bd.a();
    private static int b = 0;
    private static int c = 0;
    private static int d = 3;
    private static int e = 3;
    private static Map f = new HashMap();

    public static TJPlacement get(String str) {
        TJPlacement tJPlacement;
        synchronized (a) {
            tJPlacement = (TJPlacement) a.get(str);
        }
        return tJPlacement;
    }

    public static void put(String str, TJPlacement tJPlacement) {
        synchronized (a) {
            a.put(str, tJPlacement);
        }
    }

    public static void setCachedPlacementLimit(int i) {
        d = i;
    }

    public static void setPreRenderedPlacementLimit(int i) {
        e = i;
    }

    public static int getCachedPlacementLimit() {
        return d;
    }

    public static int getPreRenderedPlacementLimit() {
        return e;
    }

    public static int getCachedPlacementCount() {
        return b;
    }

    public static int getPreRenderedPlacementCount() {
        return c;
    }

    public static boolean canCachePlacement() {
        return getCachedPlacementCount() < getCachedPlacementLimit();
    }

    public static boolean canPreRenderPlacement() {
        return getPreRenderedPlacementCount() < getPreRenderedPlacementLimit();
    }

    public static void incrementPlacementCacheCount() {
        int i = b + 1;
        b = i;
        if (i > d) {
            b = d;
        }
        printPlacementCacheInformation();
    }

    public static void decrementPlacementCacheCount() {
        int i = b - 1;
        b = i;
        if (i < 0) {
            b = 0;
        }
        printPlacementCacheInformation();
    }

    public static void incrementPlacementPreRenderCount() {
        int i = c + 1;
        c = i;
        if (i > e) {
            c = e;
        }
    }

    public static void decrementPlacementPreRenderCount() {
        int i = c - 1;
        c = i;
        if (i < 0) {
            c = 0;
        }
    }

    public static void printPlacementCacheInformation() {
        TapjoyLog.i("TJPlacementManager", "Space available in placement cache: " + b + " out of " + d);
    }

    public static void printPlacementPreRenderInformation() {
        TapjoyLog.i("TJPlacementManager", "Space available for placement pre-render: " + c + " out of " + e);
    }

    public static void dismissContentShowing(boolean z) {
        if (z) {
            TJAdUnitActivity.a();
        }
        fr frVar = fr.a;
        if (frVar != null) {
            frVar.c();
        }
        fi fiVar = fi.a;
        if (fiVar != null) {
            Object obj;
            Runnable anonymousClass1 = new AnonymousClass1(fiVar);
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper == null || mainLooper.getThread() != Thread.currentThread()) {
                obj = null;
            } else {
                obj = 1;
            }
            if (obj != null) {
                anonymousClass1.run();
            } else {
                x.a().post(anonymousClass1);
            }
        }
    }

    static void a(String str, boolean z, long j, long j2) {
        String str2 = str + z;
        if (j2 <= 0) {
            j2 = y.b();
        }
        long j3 = j - j2;
        if (j3 > 0) {
            synchronized (f) {
                f.put(str2, Long.valueOf(j3 + SystemClock.elapsedRealtime()));
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean a(java.lang.String r8, boolean r9) {
        /*
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0 = r0.append(r8);
        r0 = r0.append(r9);
        r1 = r0.toString();
        r2 = f;
        monitor-enter(r2);
        r0 = f;	 Catch:{ all -> 0x0038 }
        r0 = r0.get(r1);	 Catch:{ all -> 0x0038 }
        r0 = (java.lang.Long) r0;	 Catch:{ all -> 0x0038 }
        if (r0 == 0) goto L_0x0035;
    L_0x001e:
        r4 = r0.longValue();	 Catch:{ all -> 0x0038 }
        r6 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x0038 }
        r4 = r4 - r6;
        r6 = 0;
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 <= 0) goto L_0x0030;
    L_0x002d:
        r0 = 1;
        monitor-exit(r2);	 Catch:{ all -> 0x0038 }
    L_0x002f:
        return r0;
    L_0x0030:
        r0 = f;	 Catch:{ all -> 0x0038 }
        r0.remove(r1);	 Catch:{ all -> 0x0038 }
    L_0x0035:
        monitor-exit(r2);	 Catch:{ all -> 0x0038 }
        r0 = 0;
        goto L_0x002f;
    L_0x0038:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0038 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.TJPlacementManager.a(java.lang.String, boolean):boolean");
    }
}
