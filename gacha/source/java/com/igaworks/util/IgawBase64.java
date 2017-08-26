package com.igaworks.util;

import org.cocos2dx.lib.BuildConfig;

public class IgawBase64 {
    private static char[] map1 = new char[64];
    private static byte[] map2 = new byte[128];

    static {
        int i;
        int i2 = 0;
        char c = 'A';
        int i3 = 0;
        while (c <= 'Z') {
            i = i3 + 1;
            map1[i3] = c;
            c = (char) (c + 1);
            i3 = i;
        }
        c = 'a';
        while (c <= 'z') {
            i = i3 + 1;
            map1[i3] = c;
            c = (char) (c + 1);
            i3 = i;
        }
        c = '0';
        while (c <= '9') {
            i = i3 + 1;
            map1[i3] = c;
            c = (char) (c + 1);
            i3 = i;
        }
        i = i3 + 1;
        map1[i3] = '+';
        i3 = i + 1;
        map1[i] = '/';
        for (int i4 = 0; i4 < map2.length; i4++) {
            map2[i4] = (byte) -1;
        }
        while (i2 < 64) {
            map2[map1[i2]] = (byte) i2;
            i2++;
        }
    }

    public static String encodeString(String str) {
        if (str == null || str.equals(BuildConfig.FLAVOR)) {
            return BuildConfig.FLAVOR;
        }
        return new String(encode(str.getBytes()));
    }

    public static char[] encode(byte[] bArr) {
        return encode(bArr, bArr.length);
    }

    public static char[] encode(byte[] bArr, int i) {
        int i2 = ((i * 4) + 2) / 3;
        char[] cArr = new char[(((i + 2) / 3) * 4)];
        int i3 = 0;
        int i4 = 0;
        while (i4 < i) {
            int i5;
            char c;
            int i6 = i4 + 1;
            int i7 = bArr[i4] & 255;
            if (i6 < i) {
                i5 = bArr[i6] & 255;
                i6++;
            } else {
                i5 = 0;
            }
            if (i6 < i) {
                i4 = i6 + 1;
                i6 = bArr[i6] & 255;
            } else {
                i4 = i6;
                i6 = 0;
            }
            int i8 = i7 >>> 2;
            i7 = ((i7 & 3) << 4) | (i5 >>> 4);
            i5 = ((i5 & 15) << 2) | (i6 >>> 6);
            int i9 = i6 & 63;
            i6 = i3 + 1;
            cArr[i3] = map1[i8];
            i3 = i6 + 1;
            cArr[i6] = map1[i7];
            if (i3 < i2) {
                c = map1[i5];
            } else {
                c = '=';
            }
            cArr[i3] = c;
            i5 = i3 + 1;
            if (i5 < i2) {
                c = map1[i9];
            } else {
                c = '=';
            }
            cArr[i5] = c;
            i3 = i5 + 1;
        }
        return cArr;
    }
}
