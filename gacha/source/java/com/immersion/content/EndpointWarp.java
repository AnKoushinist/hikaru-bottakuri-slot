package com.immersion.content;

import android.content.Context;
import android.util.Log;
import twitter4j.TwitterResponse;

public class EndpointWarp {
    public static int b = 39;
    public static int c = 0;
    public static int d = 2;
    public static int e = 1;
    long a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public EndpointWarp(android.content.Context r3, byte[] r4, int r5) {
        /*
        r2 = this;
        r0 = 1;
        r2.<init>();
    L_0x0004:
        switch(r0) {
            case 0: goto L_0x0004;
            case 1: goto L_0x000b;
            default: goto L_0x0007;
        };
    L_0x0007:
        switch(r0) {
            case 0: goto L_0x0004;
            case 1: goto L_0x000b;
            default: goto L_0x000a;
        };
    L_0x000a:
        goto L_0x0007;
    L_0x000b:
        r0 = b;
        r1 = e;
        r0 = r0 + r1;
        r1 = b;
        r0 = r0 * r1;
        r1 = d;
        r0 = r0 % r1;
        r1 = b();
        if (r0 == r1) goto L_0x0028;
    L_0x001c:
        r0 = a();
        b = r0;
        r0 = a();
        c = r0;
    L_0x0028:
        r0 = r2.createWarp(r3, r4, r5);
        r2.a = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.content.EndpointWarp.<init>(android.content.Context, byte[], int):void");
    }

    public static int a() {
        return 29;
    }

    public static int b() {
        return 0;
    }

    private native long createWarp(Context context, byte[] bArr, int i);

    private native void disposeWarp(long j);

    public static boolean f() {
        try {
            System.loadLibrary("ImmEndpointWarpJ");
            return true;
        } catch (UnsatisfiedLinkError e) {
            if (System.getProperty("java.vm.name").contains("Java HotSpot")) {
                return true;
            }
            Log.e("EndpointWarp", "Unable to load libImmEndpointWarpJ.so.Please make sure this file is in the libs/armeabi folder.");
            if (((a() + e) * a()) % d != c) {
                b = a();
                c = a();
            }
            e.printStackTrace();
            return false;
        }
    }

    private native void startWarp(long j);

    private native void stopWarp(long j);

    private native void updateWarp(long j, byte[] bArr, int i, long j2, long j3);

    public void a(byte[] bArr, int i, long j, long j2) {
        try {
            updateWarp(this.a, bArr, i, j, j2);
            if (((b + e) * b) % d != c) {
                b = a();
                c = a();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void c() {
        try {
            long j = this.a;
            int i = b;
            switch ((i * (e + i)) % d) {
                case TwitterResponse.NONE /*0*/:
                    break;
                default:
                    b = 27;
                    c = a();
                    break;
            }
            try {
                startWarp(j);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public void d() {
        long j = this.a;
        if (((a() + e) * a()) % d != c) {
            b = 12;
            c = a();
        }
        stopWarp(j);
    }

    public void e() {
        while (true) {
            try {
                int[] iArr = new int[-1];
            } catch (Exception e) {
                b = 82;
                try {
                    disposeWarp(this.a);
                    return;
                } catch (Exception e2) {
                    throw e2;
                }
            }
        }
    }
}
