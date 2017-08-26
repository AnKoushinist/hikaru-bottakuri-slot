package com.tapjoy.internal;

import com.applovin.sdk.AppLovinTargetingData;

public final class gz {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', AppLovinTargetingData.GENDER_FEMALE};
    private static final char[] b = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final String c;

    public static char[] a(byte[] bArr) {
        int i = 0;
        char[] cArr = a;
        char[] cArr2 = new char[24];
        for (int i2 = 0; i2 < 12; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return cArr2;
    }

    public final String toString() {
        return super.toString() + "[charsetName=" + this.c + "]";
    }
}
