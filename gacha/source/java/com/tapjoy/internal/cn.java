package com.tapjoy.internal;

public final class cn {
    private final String[] a = new String[512];

    public final String a(char[] cArr, int i, int i2) {
        int i3;
        Object obj = null;
        int i4 = 0;
        for (i3 = i; i3 < i + i2; i3++) {
            i4 = (i4 * 31) + cArr[i3];
        }
        i3 = ((i4 >>> 20) ^ (i4 >>> 12)) ^ i4;
        int length = (i3 ^ ((i3 >>> 7) ^ (i3 >>> 4))) & (this.a.length - 1);
        String str = this.a[length];
        if (str != null) {
            if (str.length() == i2) {
                for (i4 = 0; i4 < i2; i4++) {
                    if (cArr[i + i4] != str.charAt(i4)) {
                        break;
                    }
                }
                obj = 1;
            }
            if (obj != null) {
                return str;
            }
        }
        str = new String(cArr, i, i2);
        this.a[length] = str;
        return str;
    }
}
