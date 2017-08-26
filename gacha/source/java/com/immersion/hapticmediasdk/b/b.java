package com.immersion.hapticmediasdk.b;

import android.util.Log;
import twitter4j.TwitterResponse;

public class b {
    public static int a = 48;
    public static int b = 0;
    public static int c = 1;
    public static int d = 2;

    public static int a() {
        return 1;
    }

    public static void a(String str, String str2) {
    }

    public static int b() {
        return 1;
    }

    public static void b(String str, String str2) {
        int i = a;
        switch ((i * (b() + i)) % d) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                a = 75;
                b = 9;
                break;
        }
        Log.i(str, str2);
    }

    public static void c(String str, String str2) {
        Log.w(str, str2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void d(java.lang.String r2, java.lang.String r3) {
        /*
        android.util.Log.e(r2, r3);
    L_0x0003:
        r0 = 1;
        switch(r0) {
            case 0: goto L_0x0003;
            case 1: goto L_0x000c;
            default: goto L_0x0007;
        };
    L_0x0007:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x000c;
            case 1: goto L_0x0003;
            default: goto L_0x000b;
        };
    L_0x000b:
        goto L_0x0007;
    L_0x000c:
        r0 = a();
        r1 = c;
        r1 = r1 + r0;
        r0 = r0 * r1;
        r1 = d;
        r0 = r0 % r1;
        switch(r0) {
            case 0: goto L_0x0024;
            default: goto L_0x001a;
        };
    L_0x001a:
        r0 = a();
        a = r0;
        r0 = 56;
        b = r0;
    L_0x0024:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.b.b.d(java.lang.String, java.lang.String):void");
    }
}
