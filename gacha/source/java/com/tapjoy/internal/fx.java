package com.tapjoy.internal;

import android.os.SystemClock;

public abstract class fx {
    protected static a a;
    private static fx b;

    public static class a {
        public final String a;
        public final String b;
        public final long c = SystemClock.elapsedRealtime();
        public final eg d = new eg(60000);

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }
    }

    public abstract void a(a aVar);

    public abstract boolean b();

    public static void a(fx fxVar) {
        synchronized (fx.class) {
            b = fxVar;
            a aVar = a;
            if (aVar != null) {
                a = null;
                fxVar.a(aVar);
            }
        }
    }

    public static void a(String str, String str2) {
        synchronized (fx.class) {
            a aVar = new a(str, str2);
            if (b != null) {
                a = null;
                b.a(aVar);
            } else {
                a = aVar;
            }
        }
    }

    public static boolean c() {
        if (b != null && b.b()) {
            return true;
        }
        a aVar = a;
        if (aVar == null || aVar.d.a()) {
            return false;
        }
        return true;
    }
}
