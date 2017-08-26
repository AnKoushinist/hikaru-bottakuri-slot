package com.immersion.hapticmediasdk.models;

public class a extends Exception {
    public static int a = 24;
    public static int b = 0;
    public static int c = 1;
    public static int d = 2;
    private int e;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public a(int r3, java.lang.String r4) {
        /*
        r2 = this;
        r2.<init>(r4);
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
        r0 = a;
        r1 = c;
        r0 = r0 + r1;
        r1 = a;
        r0 = r0 * r1;
        r1 = d;
        r0 = r0 % r1;
        r1 = b;
        if (r0 == r1) goto L_0x0023;
    L_0x001b:
        r0 = 74;
        a = r0;
        r0 = 98;
        b = r0;
    L_0x0023:
        r2.e = r3;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.models.a.<init>(int, java.lang.String):void");
    }

    public static int b() {
        return 1;
    }

    public static int c() {
        return 75;
    }

    public int a() {
        if (((a + b()) * a) % d != b) {
            a = c();
            b = c();
        }
        try {
            return this.e;
        } catch (Exception e) {
            throw e;
        }
    }
}
