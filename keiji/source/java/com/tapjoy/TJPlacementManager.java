package com.tapjoy;

import android.content.Context;
import android.os.Looper;
import com.tapjoy.internal.be;
import com.tapjoy.internal.ct;
import com.tapjoy.internal.fv;
import com.tapjoy.internal.fv.AnonymousClass1;
import com.tapjoy.internal.ge;
import com.tapjoy.internal.x;
import org.cocos2dx.lib.BuildConfig;

public class TJPlacementManager {
    private static final be a = be.a();
    private static int b = 0;
    private static int c = 0;
    private static int d = 3;
    private static int e = 3;

    public static TJPlacement createPlacement(Context context, String str, boolean z, TJPlacementListener tJPlacementListener) {
        TJCorePlacement a = a(str, null, null, z);
        a.initiatedBySdk = z;
        a.setContext(context);
        return new TJPlacement(a, tJPlacementListener);
    }

    public static TJPlacement a(String str, String str2, String str3, TJPlacementListener tJPlacementListener) {
        TJPlacement tJPlacement;
        synchronized (a) {
            tJPlacement = new TJPlacement(a(str, str2, str3, false), tJPlacementListener);
        }
        return tJPlacement;
    }

    static TJCorePlacement a(String str) {
        TJCorePlacement tJCorePlacement;
        synchronized (a) {
            tJCorePlacement = (TJCorePlacement) a.get(str);
        }
        return tJCorePlacement;
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
        ge geVar = ge.a;
        if (geVar != null) {
            geVar.c();
        }
        fv fvVar = fv.a;
        if (fvVar != null) {
            Object obj;
            Runnable anonymousClass1 = new AnonymousClass1(fvVar);
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

    static TJCorePlacement a(String str, String str2, String str3, boolean z) {
        TJCorePlacement a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(z ? "!SYSTEM!" : BuildConfig.FLAVOR);
        stringBuilder.append(!ct.c(str) ? str : BuildConfig.FLAVOR);
        if (ct.c(str2)) {
            str2 = BuildConfig.FLAVOR;
        }
        stringBuilder.append(str2);
        if (ct.c(str3)) {
            str3 = BuildConfig.FLAVOR;
        }
        stringBuilder.append(str3);
        String stringBuilder2 = stringBuilder.toString();
        TapjoyLog.d("TJPlacementManager", "TJCorePlacement key=" + stringBuilder2);
        synchronized (a) {
            a = a(stringBuilder2);
            if (a == null) {
                a = new TJCorePlacement(str, stringBuilder2);
                a.put(stringBuilder2, a);
                TapjoyLog.d("TJPlacementManager", "Created TJCorePlacement with GUID: " + a.d);
            }
        }
        return a;
    }
}
