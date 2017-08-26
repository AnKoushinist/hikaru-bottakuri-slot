package com.tapjoy.internal;

import jp.co.vaz.creator.hikaru.R;
import twitter4j.TwitterResponse;

final class eb {
    public static boolean a(byte[] bArr, int i, int i2) {
        return b(bArr, i, i2) == 0;
    }

    public static int b(byte[] bArr, int i, int i2) {
        int i3 = i;
        while (i3 < i2 && bArr[i3] >= (byte) 0) {
            i3++;
        }
        if (i3 >= i2) {
            return 0;
        }
        while (i3 < i2) {
            int i4 = i3 + 1;
            i3 = bArr[i3];
            if (i3 >= 0) {
                i3 = i4;
            } else if (i3 < -32) {
                if (i4 >= i2) {
                    return i3;
                }
                if (i3 >= -62) {
                    i3 = i4 + 1;
                    if (bArr[i4] > (byte) -65) {
                    }
                }
                return -1;
            } else if (i3 < -16) {
                if (i4 >= i2 - 1) {
                    return c(bArr, i4, i2);
                }
                r4 = i4 + 1;
                r3 = bArr[i4];
                if (r3 <= (byte) -65 && ((i3 != -32 || r3 >= (byte) -96) && (i3 != -19 || r3 < (byte) -96))) {
                    i3 = r4 + 1;
                    if (bArr[r4] > (byte) -65) {
                    }
                }
                return -1;
            } else if (i4 >= i2 - 2) {
                return c(bArr, i4, i2);
            } else {
                r4 = i4 + 1;
                r3 = bArr[i4];
                if (r3 <= (byte) -65 && (((i3 << 28) + (r3 + R.styleable.AppCompatTheme_spinnerStyle)) >> 30) == 0) {
                    i4 = r4 + 1;
                    if (bArr[r4] <= (byte) -65) {
                        i3 = i4 + 1;
                        if (bArr[i4] > (byte) -65) {
                        }
                    }
                }
                return -1;
            }
        }
        return 0;
    }

    static int a(int i, int i2) {
        return (i > -12 || i2 > -65) ? -1 : (i2 << 8) ^ i;
    }

    static int a(int i, int i2, int i3) {
        return (i > -12 || i2 > -65 || i3 > -65) ? -1 : ((i2 << 8) ^ i) ^ (i3 << 16);
    }

    private static int c(byte[] bArr, int i, int i2) {
        int i3 = bArr[i - 1];
        switch (i2 - i) {
            case TwitterResponse.NONE /*0*/:
                return i3 > -12 ? -1 : i3;
            case TwitterResponse.READ /*1*/:
                return a(i3, bArr[i]);
            case TwitterResponse.READ_WRITE /*2*/:
                return a(i3, bArr[i], bArr[i + 1]);
            default:
                throw new AssertionError();
        }
    }
}
