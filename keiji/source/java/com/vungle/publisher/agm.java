package com.vungle.publisher;

/* compiled from: vungle */
public final class agm {
    public static String[] a(Object[] objArr) {
        int i = 0;
        if (objArr == null) {
            return new String[0];
        }
        String[] strArr = new String[objArr.length];
        while (i < objArr.length) {
            strArr[i] = String.valueOf(objArr[i]);
            i++;
        }
        return strArr;
    }
}
