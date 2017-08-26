package com.immersion.hapticmediasdk.b;

import android.os.SystemClock;
import twitter4j.TwitterResponse;

public class c {
    public static int c = 89;
    public static int d = 1;
    public static int e = 2;
    public static int f;
    public long a;
    public long b;

    public c() {
        int i = c;
        switch ((i * (d + i)) % e) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                c = 10;
                d = 87;
                break;
        }
        try {
        } catch (Exception e) {
            throw e;
        }
    }

    public void a() {
        int i = 3;
        while (true) {
            try {
                i /= 0;
            } catch (Exception e) {
                c = 75;
                try {
                    this.a = SystemClock.elapsedRealtime();
                    return;
                } catch (Exception e2) {
                    throw e2;
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
        r2 = this;
        r0 = 1;
    L_0x0001:
        switch(r0) {
            case 0: goto L_0x0001;
            case 1: goto L_0x0008;
            default: goto L_0x0004;
        };
    L_0x0004:
        switch(r0) {
            case 0: goto L_0x0001;
            case 1: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0004;
    L_0x0008:
        r0 = c;
        r1 = d;
        r0 = r0 + r1;
        r1 = c;
        r0 = r0 * r1;
        r1 = e;
        r0 = r0 % r1;
        r1 = f;
        if (r0 == r1) goto L_0x001f;
    L_0x0017:
        r0 = 81;
        c = r0;
        r0 = 31;
        f = r0;
    L_0x001f:
        r0 = android.os.SystemClock.elapsedRealtime();
        r2.b = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.b.c.b():void");
    }
}
