package com.raizlabs.android.dbflow.config;

import java.util.Comparator;

public class NaturalOrderComparator implements Comparator<Object> {
    public int compare(Object obj, Object obj2) {
        String obj3 = obj.toString();
        String obj4 = obj2.toString();
        int i = 0;
        int i2 = 0;
        while (true) {
            char charAt = charAt(obj3, i2);
            char charAt2 = charAt(obj4, i);
            int i3 = i2;
            char c = charAt;
            int i4 = 0;
            while (true) {
                if (!Character.isSpaceChar(c) && c != '0') {
                    break;
                }
                if (c == '0') {
                    i4++;
                } else {
                    i4 = 0;
                }
                i3++;
                c = charAt(obj3, i3);
            }
            int i5 = i;
            char c2 = charAt2;
            int i6 = 0;
            while (true) {
                if (!Character.isSpaceChar(c2) && c2 != '0') {
                    break;
                }
                if (c2 == '0') {
                    i6++;
                } else {
                    i6 = 0;
                }
                i5++;
                c2 = charAt(obj4, i5);
            }
            if (Character.isDigit(c) && Character.isDigit(c2)) {
                int compareRight = compareRight(obj3.substring(i3), obj4.substring(i5));
                if (compareRight != 0) {
                    return compareRight;
                }
            }
            if (c == '\u0000' && c2 == '\u0000') {
                return i4 - i6;
            }
            if (c < c2) {
                return -1;
            }
            if (c > c2) {
                return 1;
            }
            i2 = i3 + 1;
            i = i5 + 1;
        }
    }

    static char charAt(String str, int i) {
        if (i >= str.length()) {
            return '\u0000';
        }
        return str.charAt(i);
    }

    int compareRight(String str, String str2) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            char charAt = charAt(str, i2);
            char charAt2 = charAt(str2, i);
            if (!Character.isDigit(charAt) && !Character.isDigit(charAt2)) {
                return i3;
            }
            if (!Character.isDigit(charAt)) {
                return -1;
            }
            if (!Character.isDigit(charAt2)) {
                return 1;
            }
            if (charAt < charAt2) {
                if (i3 == 0) {
                    i3 = -1;
                }
            } else if (charAt > charAt2) {
                if (i3 == 0) {
                    i3 = 1;
                }
            } else if (charAt == '\u0000' && charAt2 == '\u0000') {
                return i3;
            }
            i2++;
            i++;
        }
    }
}
