package com.immersion.content;

import java.nio.ByteBuffer;
import twitter4j.TwitterResponse;

public class HapticHeaderUtils extends a {
    public static int b = 33;
    public static int c = 1;
    public static int d = 2;
    public static int e;
    long a;
    private byte[] i;
    private int j;

    public HapticHeaderUtils() {
        int i = b;
        switch ((i * (c + i)) % d) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                b = 43;
                c = a();
                break;
        }
        try {
        } catch (Exception e) {
            throw e;
        }
    }

    public static int a() {
        return 80;
    }

    private native int calculateBlockRateNative(long j);

    private native int calculateBlockSizeNative(long j);

    private native int calculateByteOffsetIntoHapticDataNative(long j, int i);

    public static int d() {
        return 0;
    }

    private native void disposeNative(long j);

    public static int e() {
        return 1;
    }

    private native int getMajorVersionNumberNative(long j);

    private native int getNumChannelsNative(long j);

    private native long init(byte[] bArr, int i);

    public int a(int i) {
        long j = this.a;
        int a = a();
        switch ((a * (c + a)) % d) {
            case TwitterResponse.NONE /*0*/:
                break;
            default:
                b = 89;
                e = a();
                break;
        }
        return calculateByteOffsetIntoHapticDataNative(j, i);
    }

    public void a(ByteBuffer byteBuffer, int i) {
        this.j = i;
        this.i = new byte[this.j];
        byteBuffer.get(this.i, 0, this.j);
        this.a = init(this.i, this.j);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int b() {
        /*
        r2 = this;
        r0 = b;
        r1 = c;
        r0 = r0 + r1;
        r1 = b;
        r0 = r0 * r1;
        r1 = d;
        r0 = r0 % r1;
        r1 = e;
        if (r0 == r1) goto L_0x0019;
    L_0x000f:
        r0 = a();
        b = r0;
        r0 = 84;
        e = r0;
    L_0x0019:
        r0 = r2.a;
        r0 = r2.calculateBlockSizeNative(r0);
    L_0x001f:
        r1 = 1;
        switch(r1) {
            case 0: goto L_0x001f;
            case 1: goto L_0x0028;
            default: goto L_0x0023;
        };
    L_0x0023:
        r1 = 0;
        switch(r1) {
            case 0: goto L_0x0028;
            case 1: goto L_0x001f;
            default: goto L_0x0027;
        };
    L_0x0027:
        goto L_0x0023;
    L_0x0028:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.content.HapticHeaderUtils.b():int");
    }

    public int c() {
        if (((b + e()) * b) % d != d()) {
            b = a();
            e = 12;
        }
        return calculateBlockRateNative(this.a);
    }

    public int f() {
        return getMajorVersionNumberNative(this.a);
    }

    public int g() {
        if (((b + e()) * b) % d != e) {
            b = 92;
            e = a();
        }
        try {
            return getNumChannelsNative(this.a);
        } catch (Exception e) {
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void h() {
        /*
        r2 = this;
        r0 = 0;
    L_0x0001:
        switch(r0) {
            case 0: goto L_0x0008;
            case 1: goto L_0x0001;
            default: goto L_0x0004;
        };
    L_0x0004:
        switch(r0) {
            case 0: goto L_0x0008;
            case 1: goto L_0x0001;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0004;
    L_0x0008:
        r0 = b;
        r1 = e();
        r0 = r0 + r1;
        r1 = b;
        r0 = r0 * r1;
        r1 = d;
        r0 = r0 % r1;
        r1 = e;
        if (r0 == r1) goto L_0x0023;
    L_0x0019:
        r0 = a();
        b = r0;
        r0 = 92;
        e = r0;
    L_0x0023:
        r0 = r2.a;
        r2.disposeNative(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.content.HapticHeaderUtils.h():void");
    }
}
